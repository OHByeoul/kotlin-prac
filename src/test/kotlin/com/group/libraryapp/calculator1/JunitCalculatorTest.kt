package com.group.libraryapp.calculator1

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

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
}