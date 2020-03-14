
import scala.io._
import scala.collection._

object App
{
  def main (args: Array[String]) :Unit =
  {
    val LineItemMap = Source.fromFile("outputLineItems.csv").getLines.map( line => (line.split(", ")(0),
      (line.split(", ")(1),line.split(", ")(2),line.split(", ")(3)))).toMap

    val StoreMap = Source.fromFile("outputStores.csv").getLines.map( line => (line.split(", ")(0),
      line.split(", ")(5))).toMap

    val SalesMap = Source.fromFile("outputSales.csv").getLines.map( line => (line.split(", ")(0),
      line.split(", ")(3))).toMap

    val ProductMap = Source.fromFile("outputProduct.csv").getLines.map( line => (line.split(", ")(0),
      line.split(", ")(2))).toMap

    val output = LineItemMap.mapValues(x => (StoreMap(SalesMap(x._1)), SalesMap(x._1), x._3.toDouble * ProductMap(x._2).toDouble)).toList
    val test = output.map(x => x._2).groupBy(x => x._2).mapValues(x => x.map(y => y._3)).mapValues(x => x.sum).toList


    val finaloutput = test.map(x => (StoreMap(SalesMap(x._1)), x)).sortBy(x => x._1)

    finaloutput.foreach(x => println(x))

//    Assignment 4 Q1
//    val input = Source.fromFile("test.csv").toList
//    var count = 0
//    input.filter(x => x%3 == 0).foreach(x => count += 1)
//    println(count)

//    Assignment 4 Q2
//    val input = Source.fromFile("test.csv").getLines.map(line => (line.split(", ")(0), line.split(", ")(1))).toList
//    val test = input.groupBy(x => x._1).mapValues(x => x.map(y => y._2).max)
//    test.foreach(x => println(x))


//    Assignment 4 Q3
//    val input = Source.fromFile("test.csv").getLines.map(line => ((line.split(", ")(0), line.split(", ")(1)),
//      (line.split(", ")(2), line.split(", ")(3)))).toList
//
//    val test = input.groupBy(x => x._1).mapValues(x => x.map(y => y._2).sortBy(x => x._1))
//    test.foreach(x => println(x))

  }
}