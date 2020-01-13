package br.nasa.requests.analisys

import java.text.SimpleDateFormat
import org.apache.log4j.{Level, Logger}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Main extends Serializable {

  def main(args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("wordCounts").setMaster("local[3]")
    val sc = new SparkContext(conf)

    //val lines = sc.textFile("NASAInputFiles/simpleInputTest.txt,NASAInputFiles/simpleInputTest2.txt")
    val lines = sc
      .textFile("NASAInputFiles/access_log_Aug95,NASAInputFiles/access_log_Jul95")
      .filter(line => line.length() > 11)

    //1 - HOSTS UNICOS
    println("1 - HOSTS unicos: " + uniqueHosts(lines))

    //2 - QUANTIDADE TOTAL DE ERROS 404
    println("2 - Erros 404: " + httpErrorCodeCount(lines))

    //3 - 5 URLS COM MAIS ERROS 404
    println("3 - 5 URLs com mais erros 404: ")
    printHostsWithMostErrors(lines)

    //4 - QUANTIDADE DE ERROS 404 POR DIA
    println("4 - Total de erros 404 por dia: ")
    printErrorsPerDay(lines)

    //5 - QUANTIDADE TOTAL DE BYTES RETORNADOS
    println("5 - Total de bytes retornados: " + getBytesSum(lines))
  }

  def uniqueHosts(lines: RDD[String]): Int = {
    val hosts = lines.map(line => line.split(" ")(0))
    val hostsCount = hosts.countByValue()

    hostsCount.count(hostsCount => hostsCount._2 == 1)
  }

  def httpErrorCodeCount(lines: RDD[String]): Long = {
    val httpReturnCodes = lines.map(line => getErrorCodeField(line, "httpErrorCodeCount"))
    val httpReturnCodesCount = httpReturnCodes.countByValue()
    httpReturnCodesCount("404")
  }

  def getBytesSum(lines: RDD[String]): Long = {
    val bytes = lines
      .map(line => getBytesField(line, "getBytesSum"))
      .filter(byte => !byte.equals("-"))
      .map(byte => byte.toLong)

    bytes.sum().toLong
  }

  def printHostsWithMostErrors(lines: RDD[String]): Unit = {
    val hostsReturnCodes = lines
      .map(line => (line.split(" ")(0), getErrorCodeField(line, "printHostsWithMostErrors")))

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
        val dateTime = getDateTimeField(line, "printErrorsPerDay")
        val httpCode = getErrorCodeField(line, "printErrorsPerDay")

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

  def getDateTimeField(line: String, methodName: String): String = {
    try {
      line.split("\\[")(1).substring(0, 11)
    }
    catch {
      case x: Exception => {
        println("[" + methodName + "] Nao foi possivel obter a data da requisicao: linha '" + line + "'")
      }
        "01/Aug/1990"
    }
  }

  def getErrorCodeField(line: String, methodName: String): String = {
    try {
      val itens = line.split("\" ")
      itens(itens.length - 1).split(" ")(0)
    }
    catch {
      case x: Exception => {
        println("[" + methodName + "] Nao foi possivel obter o codigo 404 de erro: linha '" + line + "'")
      }
        ""
    }
  }

  def getBytesField(line: String, methodName: String): String = {
    try {
      val itens = line.split("\" ")
      itens(itens.length - 1).split(" ")(1)
    }
    catch {
      case x: Exception => {
        println("[" + methodName + "] Nao foi possivel obter os bytes: linha '" + line + "'")
      }
        "-"
    }
  }

}
