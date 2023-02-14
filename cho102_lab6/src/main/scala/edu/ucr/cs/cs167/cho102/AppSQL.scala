package edu.ucr.cs.cs167.cho102

import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf

object AppSQL {

  def main(args: Array[String]) {
    val conf = new SparkConf
    if (!conf.contains("spark.master"))
      conf.setMaster("local[*]")
    println(s"Using Spark master '${conf.get("spark.master")}'")

    val spark = SparkSession
      .builder()
      .appName("CS167_Lab6_AppSQL")
      .config(conf)
      .getOrCreate()

    try {
      // Your code will go here
      val input = spark.read.format("csv")
        .option("sep", "\t")
       // .option("inferSchema", "true")
        .option("header", "true")
        .load("nasa_19950801.tsv")

      import spark.implicits._

      input.show()
      input.printSchema()
    } finally {
      spark.stop
    }
  }
}