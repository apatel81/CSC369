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
    val sales = inputSales.map(line => (line.split(", ")(0), line.split(", ")(1), line.split(", ")(3)))
    val stores = inputStores.map(line => (line.split(", ")(0), (line.split(", ")(1), line.split(", ")(3))))
    val lineItems = inputLineItems.map(line => (line.split(", ")(2), (line.split(", ")(1),line.split(", ")(3))))

    val new_sales = sales.map(x => (x._3, (x._2.split("/")(0) + "-" + x._2.split("/")(1), x._1)))
    val sales_join_store = new_sales.join(stores)
    val sales_join_store_formatted = sales_join_store.map(
      {case (storeID, ((date, saleID),(storeName, city))) => (saleID, (storeID, date, storeName, city))})


    val line_join_product = lineItems.join(products)
    val line_join_product_formatted = line_join_product.map(
      {case (productID, ((saleID, quantity), price)) => (saleID, quantity.toDouble * price.toDouble)})

    val final_join = sales_join_store_formatted.join(line_join_product_formatted)
    val final_join_formatted = final_join.map(
      {case (saleID, ((storeID, date, storeName, city), totalSales)) => ((date, storeName, city), totalSales)})

    val test = final_join_formatted.groupByKey().sortByKey().mapValues(_.toList).mapValues(x => x.sum)
    
    val test_formatted = test.map({case ((date, storeName, city), totalSales) => (date, (storeName, city, totalSales))})
    val finaloutput = test_formatted.groupByKey().sortByKey().mapValues(_.toList.sortBy(x => -x._3))

    finaloutput.collect().foreach(x => println(x._1, x._2.take(10)))



//    Assignment 6 Q1
//    val students = sc.textFile("students.csv").map(x => (x.split(", ")(0), x.split(", ")(1))).persist()
//    val studentCourse = sc.textFile("studentCourseGrade.csv").map(x => (x.split(", ")(0), x.split(", ")(1))).persist()
//    val diff = sc.textFile("courseDiff.csv").map(x => (x.split(", ")(0), x.split(", ")(1))).persist()
//    val students_join_course = students.join(studentCourse).map({case (studentID, (studentName, course)) => (course, studentName)})
//    val courseStudent_join_diff = students_join_course.join(diff).map({case (course, (name, diff)) => (name, course, diff)})
//
//    val topStudent = courseStudent_join_diff.sortBy(x => -1 * x._3.toInt).take(1)
//
//    val topDiff = topStudent.map(x => x._2)
//    courseStudent_join_diff.filter(x => x._2 == topDiff(0)).collect().foreach(x => println(x._1))


//    Assignment 6 Q2
//    val students = sc.textFile("students.csv").map(x => (x.split(", ")(0), x.split(", ")(1))).persist()
//    val studentCourse = sc.textFile("studentCourseGrade.csv").map(x => (x.split(", ")(0), x.split(", ")(1))).persist()
//    val diff = sc.textFile("courseDiff.csv").map(x => (x.split(", ")(0), x.split(", ")(1))).persist()
//    val students_join_course = students.leftOuterJoin(studentCourse).map({
//            case (k1, (name, None)) => (name, name)
//            case (k1, (name, Some(course))) => (course, name)
//    })
//    val courseStudent_join_diff = students_join_course.leftOuterJoin(diff).map({
//            case (course, (name, Some(diff))) => (name, diff.toDouble)
//            case (name, (name2, None)) => (name, 0.0)
//    })
//    courseStudent_join_diff.groupByKey().mapValues(x => x.toList).collect.foreach(x => println(x._1, x._2.sum / x._2.length))



//    Assignment 6 Q3
//    val courseDiff = sc.textFile("courseDiff.csv").map(x => (x.split(", ")(1).toInt, x.split(", ")(0))).persist()
//    courseDiff.map(x => (x._1 * -1, x._2)).sortByKey().top(5).foreach(x => println(x._2))

//    Assignment 6 Q4
//    val students = sc.textFile("students.csv").map(x => (x.split(", ")(0), x.split(", ")(1))).persist()
//    val studentCourse = sc.textFile("studentCourseGrade.csv").map(x => (x.split(", ")(0), x.split(", ")(2))).persist()
//
//    def convertGrade (grade : String): Double =
//      {
//        if (grade == "A") {return 4.0}
//        else if (grade == "B") {return 3.0}
//        else if (grade == "C") {return 2.0}
//        else if (grade == "D") {return 1.0}
//        else {return 0.0}
//      }
//
//    val students_join_course = students.leftOuterJoin(studentCourse).map({
//              case (k1, (name, None)) => (name, 0.0)
//              case (k1, (name, Some(grade))) => (name, convertGrade(grade))
//      })
//
//    students_join_course.groupByKey().mapValues(x => x.toList).collect.foreach(x => println(x._1, x._2.sum / x._2.length))


  }
}