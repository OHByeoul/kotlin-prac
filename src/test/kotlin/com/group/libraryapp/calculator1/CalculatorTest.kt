package com.group.libraryapp.calculator1

import java.lang.Exception


fun main() {
    val calculatorTest = CalculatorTest()
    calculatorTest.addTest()
    calculatorTest.divideTest()
}
class CalculatorTest {
    fun addTest() {
        val calculator = Calculator(5)
        calculator.add(3)

        if (calculator.number != 8) {
            throw IllegalStateException()
        }
    }

    fun divideTest(){
        val calculator = Calculator(5)

        calculator.divide(2)

        if (calculator.number != 2) {
            throw IllegalStateException()
        }
    }

    fun divideExceptionTest() {
        val calculator = Calculator(5)

        try {
            calculator.divide(0)
        } catch (e: IllegalStateException) {
            return
        } catch (e: Exception) {
            throw IllegalStateException()
        }
        throw IllegalStateException("기대하는 예외 발생 안함")
    }
}