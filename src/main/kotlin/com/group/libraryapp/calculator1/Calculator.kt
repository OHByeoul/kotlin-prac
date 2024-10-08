package com.group.libraryapp.calculator1

class Calculator(
    var _number: Int
) {
    val number: Int
        get() = this._number
    fun add(operand: Int) {
        this._number += operand
    }

    fun minus(operand: Int) {
        this._number -= operand
    }

    fun multifly(operand: Int) {
        this._number *= operand
    }

    fun divide(operand: Int) {
        if (operand == 0) {
            throw IllegalArgumentException("0으로 나눌 수 없습니다")
        }
        this._number /= operand
    }
}