package com.group.libraryapp.calculator1

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class JunitCalculatorTest {
    @Test
    fun addTest() {
        //given
        val calculator = Calculator(5)

        //when
        calculator.add(3)
        //then
        assertThat(calculator.number).isEqualTo(8)
    }

    @Test
    fun minusTest() {
        //given
        val calculator = Calculator(5)

        //when
        calculator.minus(3)
        //then
        assertThat(calculator.number).isEqualTo(2)
    }

    @Test
    fun divideExceptionTest(){
        val calculator = Calculator(5)

        val message = assertThrows<IllegalArgumentException> {
            calculator.divide(0)
        }.message
        assertThat(message).isEqualTo("0으로 나눌 수 없습니다")
    }

    @Test
    fun divideExceptionTest2() {
        val calculator = Calculator(5)

        assertThrows<IllegalArgumentException> {
            calculator.divide(0)
        }.apply {
            assertThat(message).isEqualTo("0으로 나눌 수 없습니다")
        }
    }
}