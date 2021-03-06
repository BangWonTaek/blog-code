package com.jojoldu.blogcode.querydsl.domain.book;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jojoldu@gmail.com on 01/08/2020
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @AfterEach
    void after() {
        bookRepository.deleteAllInBatch();
    }

    @Test
    void test_트랜잭션() throws Exception {
        //given
        Long bookId = bookRepository.save(Book.builder()
                .name("a")
                .build())
                .getId();

        //when
        String result = "b";
        bookService.update(bookId, result);

        //then
        Book book = bookRepository.findById(bookId).get();

        assertThat(book.getName()).isEqualTo(result);
    }
}

