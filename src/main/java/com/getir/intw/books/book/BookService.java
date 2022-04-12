package com.getir.intw.books.book;

import com.getir.intw.books.exceptions.ExceptionMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book addNewBook(Book newBook) throws Exception {
        if (newBook.getName().isBlank()) {
            throw new Exception(ExceptionMessages.BOOK_NAME_EMPTY);
        }
        if(bookRepository.findByName(newBook.getName()).isPresent()) {
            throw new Exception(String.format(ExceptionMessages.BOOK_ALREADY_EXIST, newBook.getName()));
        }

        bookRepository.save(newBook);
        return newBook;
    }

    public Book updateBookStockCount(String name, int stock) throws Exception {
        if (!bookRepository.findByName(name).isPresent()) {
            throw new Exception(String.format(ExceptionMessages.BOOK_NOT_EXIST, name));
        }

        return bookRepository.updateBookStockCount(name, stock);
    }
}
