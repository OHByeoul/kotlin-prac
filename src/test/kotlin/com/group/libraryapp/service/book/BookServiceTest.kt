package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.book.BookType
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookServiceTest @Autowired constructor(
    private val bookService: BookService,
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
) {
    @AfterEach
    fun clean() {
        bookRepository.deleteAll()
        userRepository.deleteAll()
    }
    @Test
    @DisplayName("책 등록 정상 동작")
    fun saveBookTest() {
        //given
        val bookRequest = BookRequest("해리포터",BookType.COMPUTER);

        //when
        bookService.saveBook(bookRequest);

        //then
        val books = bookRepository.findAll()
        assertThat(books).hasSize(1)
        assertThat(books[0].name).isEqualTo("해리포터")
        assertThat(books[0].type).isEqualTo(BookType.COMPUTER)
    }

    @Test
    @DisplayName("책 대출 정상 동작")
    fun loanBookTest() {
        //given
        bookRepository.save(Book.fixture("해리포터"))
        val savedUser = userRepository.save(User("홍길동", null))
        val bookLoanRequest = BookLoanRequest("홍길동", "해리포터")

        //when
        bookService.loanBook(bookLoanRequest)

        //then
        val results = userLoanHistoryRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].bookName).isEqualTo("해리포터")
        assertThat(results[0].user.id).isEqualTo(savedUser.id)
        assertThat(results[0].isReturn).isFalse
    }

    @Test
    @DisplayName("책이 대출되어 있다면, 신규 대출 실패")
    fun loanBookFailTest() {
        //given
        bookRepository.save(Book.fixture("해리포터"))
        val savedUser = userRepository.save(User("홍길동", null))
        userLoanHistoryRepository.save(UserLoanHistory(savedUser, "해리포터", false))
        val bookLoanRequest = BookLoanRequest("홍길동", "해리포터")

        //when
        val message = assertThrows<IllegalArgumentException> {
            bookService.loanBook(bookLoanRequest)
        }.message
        assertThat(message).isEqualTo("이미 대출되어 있는 책입니다")
    }

}