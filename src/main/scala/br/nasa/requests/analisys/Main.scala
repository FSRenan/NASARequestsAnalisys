package br.nasa.requests.analisys

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object Main {
  def main(args: Array[String]) {
    val inputFile = "wordCount.txt"
    val outputFile = "output"
    val log = Logger.getLogger(this.getClass.getName)

    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("wordCounts").setMaster("local[3]")
    val sc = new SparkContext(conf)

    val file = sc.textFile(inputFile)
    val counts = file.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)

    log.info(counts.count())
    counts.saveAsTextFile(outputFile)
    counts.collect()

    sc.stop()

  }

  def execute(input: String, output: String, master: Option[String] = Some("local")): Unit = {
    val sc = {
      val conf = new SparkConf().setAppName("Spark WordCount")
      for (m <- master) {
        conf.setMaster(m)
      }
      new SparkContext(conf)
    }

    // Adapted from Word Count example on http://spark-project.org/examples/
    val file = sc.textFile(input)
    val words = file.flatMap(line => tokenize(line))
    val wordCounts = words.map(x => (x, 1)).reduceByKey(_ + _)
    wordCounts.saveAsTextFile(output)
  }

  // Split a piece of text into individual words.
  private def tokenize(text: String): Array[String] = {
    // Lowercase each word and remove punctuation.
    text.toLowerCase.replaceAll("[^a-zA-Z0-9\\s]", "").split("\\s+")
  }
}
