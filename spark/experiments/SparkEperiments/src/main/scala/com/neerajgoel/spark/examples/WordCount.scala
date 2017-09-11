package com.neerajgoel.spark.examples

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by negoel on 5/9/2017.
  */
object WordCount {
  def main(args:Array[String]) : Unit = {
    val logFile = "C:\\code\\mywork\\spark\\scala\\SparkEperiments\\src\\main\\scala\\com\\neerajgoel\\spark\\examples\\WordCount.scala"
    val conf = new SparkConf().setAppName("WordCount Application").setMaster("local[4]")
    val sc = new SparkContext(conf)
    val logData = sc.textFile(logFile, 2).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println(s"Lines with a: $numAs, Lines with b: $numBs")
    sc.stop()
  }
}
