package com.bheternal.jhome.scala

import scala.collection.mutable.ArrayBuffer

class ScalaDemo {


}

object ScalaDemo {

  def main(args: Array[String]): Unit = {

    // 块表达式
    val block = {
      val a = 1
      val b = 2
      a + b
    }

    println(block)


    // for 循环
    // for (i <- 表达式/数组/集合 if i条件){
    //  表达式
    // }

    val nums = 1.to(4)
    println(nums)

    for (i <- nums) print(", 循环序列: " + i)
    println("")
    for (i <- nums; j <- nums) print(", 双循环序列: " + i * j)
    println("")
    for (i <- nums; j <- 1 to i) print(s", 双循环序列: ${i}*${j}=${i * j}")
    println("")
    for (i <- nums if i > 2) print(", >2的循环序列: " + i)
    println("")

    val a = for (i <- nums if i > 2) yield i * 10
    println("推导结果" + a)


    // while 循环
    // while(返回值为布尔类型的表达式) {
    //  表达式
    // }
    var x = 8
    while (x > 5) {
      print(s", while循环: ${x}")
      x -= 1
    }
    println("")


    // 方法
    def method1(first: Int): Int = {
      first * 2
    }

    def method2(first: Int) = {
      first * 2
    }

    def method3(first: Int) {
      first * 2
    }

    def method4 = 10

    println(s"method1: ${method1(10)}")
    println(s"method2: ${method2(10)}")
    println(s"method3: ${method3(10)}")
    println(s"method4: ${method4}")


    // 函数
    val fun1 = (x: Int, y: Int) => {
      x * y
    }
    // (x: Int, y: Int) => x * y
    val fun2: Int => Int = x => x * x
    val fun3: (Int, String) => (String, Int) = (x, y) => (y, x)
    val fun4 = method1 _

    println(s"fun1: ${fun1} ${fun1(2, 10)}")
    println(s"fun2: ${fun2} ${fun2(2)}")
    println(s"fun3: ${fun3} ${fun3(2, "aaaaaa")}")
    println(s"fun4: ${fun4} ${fun4(2)}")


    // 数组
    // 定长数组
    val arr1 = new Array[String](10)
    val arr2 = Array(1, 2, 3)

    println(s"arr1: ${arr1} ${arr1(0)}")
    println(s"arr2: ${arr2} ${arr2(0)}")

    arr2(0) = 10
    println(s"arr2: ${arr2} ${arr2(0)}")

    //val arr3 = arr2.+:(100)
    val arr3 = 100 +: arr2
    //val arr31 = arr2.:+(100)
    val arr31 = arr2 :+ 100
    println(s"arr2: ${arr2} ${arr2.mkString("Array(", ", ", ")")}")
    println(s"arr3: ${arr3} ${arr3.mkString("Array(", ", ", ")")}")
    println(s"arr31: ${arr31} ${arr31.mkString("Array(", ", ", ")")}")

    val arr4 = arr2 ++ arr3
    println(s"arr4: ${arr4} ${arr4.mkString("Array(", ", ", ")")}")
    val arr5 = arr2 ++: arr3
    println(s"arr4: ${arr5} ${arr5.mkString("Array(", ", ", ")")}")

    // 变长数组
    val barr = ArrayBuffer(1, 2, 3)
    barr += 10
    println(s"barr1: ${barr}")
    //barr.+=:(10)
    10 +=: barr
    println(s"barr2: ${barr}")

    // 遍历数组
    for (i <- 0 until barr.length) print(s", ${i}")
    println("")

    for (i <- barr.indices) print(s", ${i}")
    println("")

    for (i <- 0 until(barr.length, 2)) print(s", ${i}")
    println("")

    // 数组操作
    val oparr = Array(10, 1, 2, 3, 4)
    println(s"oparr sum ${oparr.sum}")
    println(s"oparr max ${oparr.max}")
    println(s"oparr min ${oparr.min}")
    println(s"oparr product ${oparr.product}")
    println(s"oparr sorted ${oparr.sorted.mkString("Array(", ", ", ")")}")
    println(s"oparr sorted reverse ${oparr.sorted.reverse.mkString("Array(", ", ", ")")}")


    val oparr1 = Array("big", "spark", "abnormal", "3", "4")
    println(s"oparr1 max ${oparr1.max}")
    println(s"oparr1 min ${oparr1.min}")


    // 元组
    val tuple1 = (1, 2)
    println(s"tuple1 ${tuple1}")
    val tuple2 = (1, 2, 3) -> (10, 100, 111) -> 2 -> 3
    println(s"tuple2 ${tuple2}")
    println(s"tuple2._1 ${tuple2._1}")
    println(s"tuple2._2 ${tuple2._2}")


    // Map
    // 不可变Map
    val map1 = Map("k1" -> "v1", "k2" -> "v2")
    val map2 = Map(("k1", "v1"), ("k2", "v2"))
    println(s"map1 ${map1}")
    println(s"map2 ${map2}")

    // 可变Map
    val map3 = scala.collection.mutable.Map("k1" -> "v1", "k2" -> "v2")
    map3.put("k3", "v3")
    map3("k3") = "v33"
    println(s"map3 ${map3}")

    // Map操作
    val map4 = map1 + ("k4" -> "v4")
    println(s"map1 ${map1}")
    println(s"map4 ${map4}")

    map3 += ("k4" -> "v4", "k5" -> "v5")
    map3 -= "k1"
    println(s"map3 ${map3}")

    println(s"map3 keys ${map3.keys}")
    println(s"map3 keySet ${map3.keySet}")
    println(s"map3 values ${map3.values}")

    for (e <- map3) print(s", ${e}")
    println()
    for ((k, v) <- map3) print(s", ${k -> v}")
    println()


    // Set集合
    // 不可变Set
    val set = Set(10, 1, 2, 3, 4)
    println(s"set ${set}")
    println(s"set contains 3 ${set contains 3}")
    println(s"Set(1, 2) subsetOf set ${Set(1, 2) subsetOf set}")

    val set1 = Set(1, 2, 3, 4, 5, 6)
    // 并
    println(s"set | set1 ${set | set1}")
    println(s"set union set1 ${set union set1}")
    // 交
    println(s"set & set1 ${set & set1}")
    println(s"set intersect set1 ${set intersect set1}")
    // 差
    println(s"set diff set1 ${set diff set1}")
    println(s"set -- set1 ${set -- set1}")
    // 取反
    println(s"set ~& set1 ${set &~ set1}")


    // 函数式编程
    // 遍历 foreach
    val list = List(10, 1, 2, 3, 4)
    list.foreach(x => println(x))
    list foreach println

    // 映射 map
    //val listMapped = list.map(x => x * 10)
    val listMapped = list map (_ * 10)
    println(s"list mapped ${listMapped}")

    // 拉平映射 flatMap
    val list1 = List("a b c d e", "c f", "1 2 a b")
    val listFlatMapped = list1 flatMap (_ split " ")
    //val listFlatMapped = list1 flatMap (_ split " ")
    println(s"list1 flat mapped ${listFlatMapped}")

    // 过滤 filter 分区 partition
    println(s"list filter > 3 ${list filter (_ > 3)}")
    println(s"list filter not > 3 ${list filterNot (_ > 3)}")
    println(s"list filter partition % 2 ${list partition (_ % 2 == 0)}")

    // 排序 sorted
    println(s"list sorted ${list sorted}")
    println(s"list sort by ${list sortBy (_ % 2)}")
    println(s"list sort with ${list sortWith (_ > _)}")

    // 聚合 reduce
    println(s"list reduce ${list reduce (_ + _ + 1)}")

    // 折叠 fold
    println(s"list fold ${(list fold 0) (_ + _ + 1)}")
    println(s"list fold ${(0 /: list) (_ + _ + 1)}")
    println(s"list fold ${(list :\ 0) (_ + _ + 1)}")


    // 高阶函数
    val hfun1 = (x: Int, y: Int) => x + y
    val hfun2 = (x: Int) => (y: Int) => x + y

    def hmethod1(x: Int)(y: Int, z: Int) = x + y + z

    println(s"hfun1 ${hfun1(1, 2)}")
    println(s"hfun2 ${hfun2(1)(2)}")
    println(s"hmethod1 ${hmethod1(1)(2, 3)}")



    // 面向对象
    // 类 class

    class Custom {
      val test: String = "test"
      val sayFun: String => Unit = println
      var name: String = _
      var age: Int = _

      def say(text: String): Unit = println(text)
    }

    val custom = new Custom
    custom.name = "zain"
    custom.age = 18

    custom say "哈哈哈哈哈"
    custom sayFun "o"

    class Custom1(val name: String, val age: Int) {
      def this(name: String) = this(name, 18)
    }
    val custom1 = new Custom1("zain")
    println(s"custom1 name ${custom1 name}")

    // 对象 object
    object Custom1 {
      def apply(name: String): Custom1 = new Custom1(name)
    }
    val custom11 = Custom1("zain")


    // 继承
    class Ex1 extends Custom {
      override val test: String = "李四"
      private[this] val security = "security"

      override def say(text: String): Unit = super.say(text)
    }
    object Ex2 extends Custom
    class Ex3(name: String, val work: String) extends Custom1(name)

    val ex1: Custom = new Ex1
    if (ex1.isInstanceOf[Ex1] && ex1.getClass == classOf[Ex1]) {
      val ex = ex1.asInstanceOf[Ex1]
      println(s"ex1 ${ex.test}")
    }



    // 特质 trait

    trait A {
      println("trait A")
    }
    trait B {
      println("trait B")
    }
    trait C {
      println("trait C")
    }
    trait D {
      println("trait D")
    }
    class TraitDemo extends A
      with B
      with C {

    }

    object TraitDemo {
      println("TraitDemo")

      def apply(): TraitDemo = new TraitDemo()
    }

    val traitDemo = TraitDemo()
    val traitDemo2 = new TraitDemo with D


    // 模式匹配
    // 匹配字符串、匹配类型、匹配数组、匹配集合、匹配元组
    val matchStr = "spark"
    matchStr match {
      case "spark" => println("ok")
      case "flink" => println("not ok")
      case _ => println("oh no")
    }

    // 样例类
    case class Tmp()

    // Option
    // Some、None
    val some = Some("a")
    val none = None

    // 偏函数
    println(s"list ${list}")
    println(s"list ${
      list filter {
        case 1 => true
        case 2 => false
        case 10 => true
        case _ => false
      }
    }")


    // 异常处理
    try {
      throw new NullPointerException("NPE")
    } catch {
      case ex: NullPointerException => println("ok")
      case _ => println("not ok")
    } finally {
      println("finished")
    }


  }


}
