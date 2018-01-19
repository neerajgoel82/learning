package com.neerajgoel.spark.examples

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

object ParquetReader {
  def main(args: Array[String]) {
    val inputFilePath = "/Users/negoel/Downloads/c1bf64c6-644f-4120-b0f4-b5d35cad2350.parquet"
    val config = new SparkConf().setAppName("Csv to Parquet Converter").setMaster("local")
    val sc = new SparkContext(config)
    val sqlContext = new SQLContext(sc)

    val df = sqlContext.read
      .format("parquet")
      .load(inputFilePath)


    print("******Count:" + df.count)
    df.show(10)
  }
}
