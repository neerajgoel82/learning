package com.neerajgoel.spark.examples

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

object CsvToParquetConverter {
  def main(args: Array[String]) {
    val inputFilePath = "/tmp/test.csv"
    val outputFilePath = "/tmp/testdata"
    val config = new SparkConf().setAppName("Csv to Parquet Converter").setMaster("local")
    val sc = new SparkContext(config)
    val sqlContext = new SQLContext(sc)

    val df = sqlContext.read
      .format("csv")
      .option("header", "true") //reading the headers
      .option("mode", "DROPMALFORMED")
      .load(inputFilePath)

    df.write
      .format("parquet")
      .save(outputFilePath)
  }
}
