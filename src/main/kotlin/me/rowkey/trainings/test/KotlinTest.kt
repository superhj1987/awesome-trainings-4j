package me.rowkey.trainings.test

data class Person(val name: String) {
    var age: Int = 0
}

data class User(val name: String, val age: Int)

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

}

sealed class DataType
data class Card(val number: Double) : DataType()
data class Timeline(val e1: DataType, val e2: DataType) : DataType()
object NotANumber : DataType()

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

object SingleInstance {
    fun test(input: String) = println(input)
}

fun main(args: Array<String>) {
    val x: Int = 20;

    when (x) {
        1 -> print("x == 1")
        2 -> print("x == 2")
        3, 4 -> print("x == 3 or x == 4")
        in 10..99999 -> print("x > 10")
        else -> { // 注意这个块
            print("x is neither 1 nor 2")
        }
    }

//    when {
//        x.isOdd() -> print("x is odd")
//        x.isEven() -> print("x is even")
//        else -> print("x is funny")
//    }
}