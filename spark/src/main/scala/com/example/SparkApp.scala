package com.example

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

// Add VM option for Java 17: --add-exports java.base/sun.nio.ch=ALL-UNNAMED
object SparkApp extends App {

  val spark = SparkSession.builder()
    .appName("Simple Application")
    .master("local[*]")
    .getOrCreate()

  spark.sparkContext.setLogLevel("WARN")

  val csvFile = Thread.currentThread().getContextClassLoader.getResource("people.csv")
  val people = spark.read.option("header", "true").csv(csvFile.toString)
  people.show()

  val newPeople = people.withColumn("fullname", concat_ws(", ", people("firstname"), people("lastname")))
    .drop("firstname", "lastname")
  newPeople.show()

  spark.stop()
}
