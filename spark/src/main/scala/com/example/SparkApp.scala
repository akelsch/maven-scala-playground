package com.example

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

// Add VM option for Java 17: --add-exports java.base/sun.nio.ch=ALL-UNNAMED
object SparkApp {

  private val spark = SparkSession.builder()
    .appName("Simple Application")
    .master("local[*]")
    .getOrCreate()

  spark.sparkContext.setLogLevel("WARN")

  def main(args: Array[String]): Unit = {
    val csvPath = getClass.getResource("/people.csv").getPath
    val people = spark.read
      .option("header", "true")
      .csv(csvPath)
    people.show()

    val newPeople = people
      .withColumn("fullname", concat_ws(", ", col("firstname"), col("lastname")))
      .drop("firstname", "lastname")
    newPeople.show()

    spark.stop()
  }
}
