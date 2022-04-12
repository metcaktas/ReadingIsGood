package com.getir.intw.books.order.dao;

import com.getir.intw.books.book.Book;
import com.getir.intw.books.order.Order;

public interface CustomOrderRepository {

    Order insertNewOrder(Order newOrder) throws Exception;
}
