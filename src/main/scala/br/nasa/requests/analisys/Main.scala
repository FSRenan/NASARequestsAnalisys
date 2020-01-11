package br.nasa.requests.analisys

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object Main {

  def main(args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("wordCounts").setMaster("local[3]")
    val sc = new SparkContext(conf)

    val lines = sc.textFile("NASAInputFiles/simpleInputTest.txt,NASAInputFiles/simpleInputTest2.txt")

    //CASE 1 HOSTS UNICOS
    val hosts = lines.map(line => line.split(" ")(0))
    val hostsCount = hosts.countByValue()
    val uniqueHosts = hostsCount.count(hostsCount => hostsCount._2 == 1)
    println("1.HOSTS unicos: " + uniqueHosts)

    //CASE 2 404 COUNT
    val errors = lines.map(line => line.split("\"")(2).substring(1, 4))
    for (error <- errors) println("Error: " + error)


    val words = lines.flatMap(line => line.split("\n"))
    //for ((host, count) <- hostsCount) println(host + " : " + count)
    val wordCounts = words.countByValue()
    //  for (word <- words) println(word)

  }


}
