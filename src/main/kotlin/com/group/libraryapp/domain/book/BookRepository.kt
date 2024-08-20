package com.group.libraryapp.domain.book

import com.group.libraryapp.domain.user.UserRepositoryCustom
import com.group.libraryapp.dto.book.response.BookStatResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface BookRepository : JpaRepository<Book, Long>, UserRepositoryCustom {
    fun findByName(bookName: String): Book?

    @Query("SELECT new  com.group.libraryapp.dto.book.response.BookStatResponse(b.type, COUNT(b.id)) FROM Book b GROUP By b.type")
    fun getStats(): List<BookStatResponse>
}