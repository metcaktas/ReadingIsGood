package com.getir.intw.books.book.dao;

import com.getir.intw.books.book.Book;

public interface CustomBookRepository {

    Book updateBookStockCount(String name, int stock);
}
