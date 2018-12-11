package me.rowkey.trainings.test

import java.nio.file.Files
import java.nio.file.Paths
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

/**
 * 数据类
 *
 */
data class Person(val name: String) {
    var age: Int = 0
}

data class User(val name: String, val age: Int)

/**
 * 受限类
 *
 */
sealed class DataType
data class Card(val number: Double) : DataType()
data class Timeline(val e1: DataType, val e2: DataType) : DataType()
object NotANumber : DataType()

/**
 * 枚举
 *
 */
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

/**
 *
 * 单列模式
 *
 */
object SingleInstance {
    fun test(input: String) = println(input)
}

class Test(val counter: Int, val name: String = "test") {

    companion object {
        const val TYPE = 1
        val title = "haha"

        fun testStatic() {
            println("static method")
        }
    }

    operator fun component1(): Int {
        return counter
    }

    operator fun component2(): String {
        return name
    }

    operator fun component3(): String {
        return name
    }

    fun test1(){

    }

    fun test2(){

    }
}

fun sum(a : Int,b : Int) = a + b

fun parseInt(str: String?): Int? {
    // ……
    if(str == null){
        return null
    }

    return 1
}

fun arrayOfMinusOnes(size: Int): IntArray {
    return IntArray(size).apply { fill(-1) }
}

inline fun <T> lock(lock: Lock, body: () -> T): T {
    return body()
}

fun main(args: Array<String>) {

    lock(ReentrantLock()){

    }

    val r = parseInt(null)
    r?.let {
        println(r)
    }

    val list = listOf<Int>(1,2,3,-4)
    val positives = list.filter { x -> x > 0 }

    val test = Test(1)
    with(test){
        test1()
        test2()
    }
    val stream = Files.newInputStream(Paths.get("/some/file.txt"))
    stream.buffered().reader().use { reader ->
        println(reader.readText())
    }

    val testList = listOf(Test(1),Test(2))
    for((k,v) in testList){

    }
    val x: Int = 20;

    val a = if(x > 0) 1 else 2


    when (x) {
        1 -> print("x == 1")
        2 -> print("x == 2")
        3, 4 -> print("x == 3 or x == 4")
        in 10..99999 -> print("x > 10")
        else -> {
            print("x is neither 1 nor 2")
        }
    }

//    when {
//        x.isOdd() -> print("x is odd")
//        x.isEven() -> print("x is even")
//        else -> print("x is funny")
//    }
}