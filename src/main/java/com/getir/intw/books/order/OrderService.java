package com.getir.intw.books.order;


import com.getir.intw.books.book.Book;
import com.getir.intw.books.exceptions.ExceptionMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Order insertNewOrder(Order newOrder) throws Exception {
        if (newOrder.getEmail().isBlank()) {
            throw new Exception(ExceptionMessages.EMPTY_EMAIL);
        }
        for (Book b : newOrder.getOrderedBooks()) {
            if (b.getName().isBlank()){
                throw new Exception(ExceptionMessages.BOOK_NAME_EMPTY);
            }
        }

        return orderRepository.insertNewOrder(newOrder);
    }

    public Order getOrderBy(String id) throws NoSuchElementException {
        return orderRepository.findById(id).get();
    }

    public List<Order> getOrdersBy(Instant fromDate, Instant toDate) {
        return orderRepository.findByDateBetween(fromDate,toDate);
    }

    public Page<Order> getOrdersBy(String email, int page, int size) {
        return orderRepository.findByEmail(email, PageRequest.of(page, size));
    }
}
