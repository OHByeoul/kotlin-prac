package com.group.libraryapp.util

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

fun fail(): Nothing {
    throw IllegalArgumentException()
}

fun <T, Id> CrudRepository<T, Id>.findByIdOrNullThrow(id: Id): T {
    return this.findByIdOrNull(id) ?: fail()
}