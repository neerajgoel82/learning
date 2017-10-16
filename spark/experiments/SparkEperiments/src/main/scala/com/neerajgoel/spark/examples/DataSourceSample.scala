package com.neerajgoel.spark.examples

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

object DataSourceSample {
  def main(args: Array[String]) {
    val config = new SparkConf().setAppName("Datasource Sample").setMaster("local")
    val sc = new SparkContext(config)
    val sqlContext = new SQLContext(sc)

    val df = sqlContext
      .read
      .format("com.neerajgoel.spark.examples.datasources.sample")
      .load("/Users/negoel/code/mywork/learning/spark/experiments/SparkEperiments/src/main/resources/DataSourceSampleData/input")

    df.write
      .format("com.neerajgoel.spark.examples.datasources.sample")
      .save("/Users/negoel/code/mywork/learning/spark/experiments/SparkEperiments/src/main/resources/DataSourceSampleData/output")
  }
}