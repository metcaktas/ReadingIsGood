package com.getir.intw.books.book;

import com.getir.intw.books.customer.Customer;
import com.getir.intw.books.exceptions.ExceptionMessages;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    @Test
    public void addNewBook_IfCreateSuccessful_ThenReturnBook() throws Exception {
        Book expected = new Book("book", 10, 1);
        when(bookRepository.findByName(anyString())).thenReturn(Optional.empty());

        Book actual = bookService.addNewBook(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void addNewBook_IfBookNameIsEmpty_ThenReturnEmptyNameException() {
        Exception expected = new Exception(ExceptionMessages.BOOK_NAME_EMPTY);

        Exception actual = assertThrows(Exception.class, () -> bookService.addNewBook(new Book("", 1, 1)));
        assertEquals(expected.getMessage(), actual.getMessage());
    }

    @Test
    public void addNewBook_IfBookNameAlreadyExist_ThenReturnNameAlreadyExistException() {
        Book book = new Book("book", 10, 1);
        Exception expected = new Exception(String.format(ExceptionMessages.BOOK_ALREADY_EXIST, "book"));
        when(bookRepository.findByName(anyString())).thenReturn(Optional.of(book));

        Exception actual = assertThrows(Exception.class, () -> bookService.addNewBook(book));
        assertEquals(expected.getMessage(), actual.getMessage());
    }

    @Test
    public void updateBookStockCount_IfBookNotInDB_ThenReturnNotExistException() {
        Exception expected = new Exception(String.format(ExceptionMessages.BOOK_NOT_EXIST, "book"));
        when(bookRepository.findByName(anyString())).thenReturn(Optional.empty());

        Exception actual = assertThrows(Exception.class, () -> bookService.updateBookStockCount("book", 2));
        assertEquals(expected.getMessage(), actual.getMessage());
    }
}
