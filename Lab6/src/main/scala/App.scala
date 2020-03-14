import org.apache.spark.SparkContext._
import scala.io._
import org.apache.spark.{ SparkConf, SparkContext }
import org.apache.spark.rdd._
import org.apache.log4j.Logger
import org.apache.log4j.Level
import scala.collection._

object App {
  def main(args: Array[String])
  {
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val conf = new SparkConf().setAppName("App").setMaster("local[4]")
    val sc = new SparkContext(conf)
    //parameter to setMaster tells us how to distribute
    // the data, e.g., 4 partitions on the localhost
    // don't use setMaster if running on cluster

    val inputLineItems = sc.textFile("outputLineItems.csv").persist()
    val inputSales = sc.textFile("outputSales.csv").persist()
    val inputProducts = sc.textFile("outputProduct.csv").persist()
    val inputStores = sc.textFile("outputStores.csv").persist()

    val products = inputProducts.map( line => (line.split(", ")(0), line.split(", ")(2)))
    val sales = inputSales.map(line => (line.split(", ")(0), line.split(", ")(3)))
    val stores = inputStores.map(line => (line.split(", ")(0), line.split(", ")(5)))
    val lineItems = inputLineItems.map(line => (line.split(", ")(0),
      (line.split(", ")(1),line.split(", ")(2),line.split(", ")(3))))


    val sales_state = sales.cartesian(stores).filter(x => x._1._2 == x._2._1).map(x => (x._1._1, x._2._2)).distinct().persist()
//    sales_state.foreach(x => println(x))

    val item_product = lineItems.cartesian(products).filter(x => x._1._2._2 == x._2._1).groupBy(x => x._1._2._2).
                        mapValues(x => x.map(y =>  y._1._2._3.toDouble * y._2._2.toDouble)).mapValues(x => x.sum).distinct().persist()
//    item_product.foreach(x => println(x))

    val finaloutput = sales_state.cartesian(item_product).filter(x => x._1._1 == x._2._1).map(x => (x._1._2, x._1._1, x._2._2)).collect()

    finaloutput.foreach(x => println(x))


//    Assignment 5 Q1
//    val lines = sc.textFile("test1.csv")
//    val nums = lines.flatMap(line => line.split(" ")).persist()
//    val input = nums.map(x => x.toInt).filter(x => x%3==0).countByValue()
//    input.foreach(x => println(x))



//    Assignment 5 Q2
//    val emp = sc.textFile("emp.csv").map(line => (line.split(", ")(1), line.split(", ")(0)))
//    val dep = sc.textFile("dep.csv").map(line => (line.split(", ")(0), line.split(", ")(1)))
//    emp.cartesian(dep).filter(x => x._1._1 == x._2._1).foreach(x => println(x._1._2, x._2._2))


//    Assignment 5 Q3
//    val lines = sc.textFile("test3.csv").map(line => (line.split(", ")(0) + " " + line.split(", ")(1),
//                                                             " " + line.split(", ",3)(2)))
//
//    def convertGrade (grade : String): Int =
//      {
//        if (grade == "A") {return 4}
//        else if (grade == "B") {return 3}
//        else if (grade == "C") {return 2}
//        else if (grade == "D") {return 1}
//        else {return 0}
//      }
//
//    def getValue (value : String): Double =
//      {
//        if (value.contains(","))
//          {
//            val gradeCourse = value.split(", ").toList
//            val gradeNum = gradeCourse.map(x => convertGrade(x.trim()(0).toString))
//            val gpa = gradeNum.aggregate((0, 0))((x ,y) => (x._1 + y, x._2 + 1), (x, y) => (x._1 + y._1, x._2 + y._2))
//            return gpa._1 * 1.0 / gpa._2
//          }
//
//        else
//        {
//          return convertGrade(value(1).toString).toDouble
//        }
//      }
//
//    lines.foreach(x=>println(x._1, getValue(x._2)))


  }
}