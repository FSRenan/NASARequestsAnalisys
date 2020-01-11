package br.nasa.requests.analisys

import java.text.SimpleDateFormat
import java.time.{LocalDateTime, ZoneId}
import java.time.format.DateTimeFormatter
import java.util.Date

import org.apache.log4j.{Level, Logger}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Main {

  def main(args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("wordCounts").setMaster("local[3]")
    val sc = new SparkContext(conf)

    val lines = sc.textFile("NASAInputFiles/simpleInputTest.txt,NASAInputFiles/simpleInputTest2.txt")


    val format = new java.text.SimpleDateFormat("dd/MM/yyyy")
    val date = format.parse("01/08/1995")

    println("%%%" + date)
    //1 - HOSTS UNICOS
    println("1 - HOSTS unicos: " + uniqueHosts(lines))

    //2 - QUANTIDADE TOTAL DE ERROS 40
    println("2 - Erros 404: " + httpErrorCodeCount(lines))

    //3 - 5 URLS COM MAIS ERROS 404
    println("3 - 5 URLs com mais erros 404: ")
    printHostsWithMostErrors(lines)


    //4 - QUANTIDADE DE ERROS 404 POR DIA
    println("3 - Total de erros 404 por dia: ")
    printErrorsPerDay(lines)
  }

  def uniqueHosts(lines: RDD[String]): Int = {
    val hosts = lines.map(line => line.split(" ")(0))
    val hostsCount = hosts.countByValue()

    hostsCount.count(hostsCount => hostsCount._2 == 1)
  }

  def httpErrorCodeCount(lines: RDD[String]): Long = {
    val httpReturnCodes = lines.map(line => line.split("\"")(2).substring(1, 4))
    val httpReturnCodesCount = httpReturnCodes.countByValue()
    httpReturnCodesCount("404")
  }


  def printHostsWithMostErrors(lines: RDD[String]): Unit = {
    val hostsReturnCodes = lines.map(line => (line.split(" ")(0), line.split("\"")(2).substring(1, 4)))
    val hostsWithErrorReturnCode = hostsReturnCodes.filter(host => host._2.equals("404"))
    val hostsErrorCount = hostsWithErrorReturnCode.countByValue()
    val formattedHostsErrorCount = hostsErrorCount.map(hostErrorCount => (hostErrorCount._1._1, hostErrorCount._2))
    val sortedHostsErrorCount = formattedHostsErrorCount.toSeq.sortBy(_._2)(Ordering[Long].reverse).take(5)

    for ((host, errorCount) <- sortedHostsErrorCount)
      println("       Host: " + host + " | Quantidade de erros 404: " + errorCount)
  }

  def printErrorsPerDay(lines: RDD[String]): Unit = {
    val httpReturnCodesWithDay = lines
      .map(line => {
        val dateTime = line.split("\\[")(1).substring(0, 11)
        val httpCode = line.split("\"")(2).substring(1, 4);

        if (dateTime.contains("Aug")) {
          //Error while casting august month
          val format = new SimpleDateFormat("dd/MM/yyyy")
          (format.parse(dateTime.replace("Aug", "08")), httpCode)

        } else {
          val format = new SimpleDateFormat("dd/MMM/yyyy")
          (format.parse(dateTime), httpCode)

        }
      })

    val httpErrorCodesWithDay = httpReturnCodesWithDay.filter(host => host._2.equals("404"))
    val errorsPerDay = httpErrorCodesWithDay.countByValue()
    val formattedErrorsPerDay = errorsPerDay.map(day => (day._1._1, day._2))
    val sortedErrorsPerDay = formattedErrorsPerDay.toSeq.sortBy(_._1)

    for ((day, errorCount) <- sortedErrorsPerDay)
      println("       Dia: " + day + " | Quantidade de erros 404: " + errorCount)
  }

}
