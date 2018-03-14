package akka.streams.example11

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import akka.streams.example10.BufferingExample.{Author, Hashtag, Tweet}
import akka.stream.scaladsl._
import scala.concurrent.Future

object MaterializerExample extends App{
  final case class Tweet(author: Author, timestamp: Long, body: String) {
    def hashtags: Set[Hashtag] =
      body.split(" ").collect { case t if t.startsWith("#") => Hashtag(t) }.toSet
  }

  val tweets: Source[Tweet, NotUsed] = Source(
    Tweet(Author("rolandkuhn"), System.currentTimeMillis, "#akka rocks!") ::
      Tweet(Author("patriknw"), System.currentTimeMillis, "#akka !") ::
      Tweet(Author("bantonsson"), System.currentTimeMillis, "#akka !") ::
      Tweet(Author("drewhk"), System.currentTimeMillis, "#akka !") ::
      Tweet(Author("ktosopl"), System.currentTimeMillis, "#akka on the rocks!") ::
      Tweet(Author("mmartynas"), System.currentTimeMillis, "wow #akka !") ::
      Tweet(Author("akkateam"), System.currentTimeMillis, "#akka rocks!") ::
      Tweet(Author("bananaman"), System.currentTimeMillis, "#bananas #good rock!") ::
      Tweet(Author("appleman"), System.currentTimeMillis, "#apples #red rock!") ::
      Tweet(Author("drama"), System.currentTimeMillis, "we compared #apples to #oranges!") ::
      Nil)

  implicit val system = ActorSystem("Materializer-Example")
  implicit val materializer = ActorMaterializer()

  import system.dispatcher

  val countFlow : Flow[Tweet, Int, NotUsed] = Flow[Tweet].map( _ => 1)
  val countSink : Sink[Int, Future[Int]] = Sink.fold[Int, Int](0)(_ + _)
  val counterRunnableGraph: RunnableGraph[Future[Int]] = tweets.via(countFlow).toMat(countSink)(Keep.right)
  val output: Future[Int] = counterRunnableGraph.run()

  output.onComplete( count => {
    print("Count : " + count)
    system.terminate()
  })
}
