package com.getir.intw.books.order;

import com.getir.intw.books.book.Book;
import com.getir.intw.books.customer.Customer;
import com.getir.intw.books.customer.CustomerService;
import com.getir.intw.books.exceptions.ExceptionMessages;
import com.getir.intw.books.order.dao.CustomOrderRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    MongoTemplate mongoTemplate;

    @InjectMocks
    CustomOrderRepositoryImpl customOrderRepositoryImpl;

    @Test
    public void insertNewOrder_IfBookNotExist_ThenReturnNotExistException() {
        Exception expected = new Exception(String.format(ExceptionMessages.BOOK_NOT_EXIST, "book"));
        Order order = new Order();
        order.setOrderedBooks(Arrays.asList(new Book("book", 10, 1)));
        when(mongoTemplate.findOne(any(), any())).thenReturn(null);

        Exception actual = assertThrows(Exception.class, () -> customOrderRepositoryImpl.insertNewOrder(order));
        assertEquals(expected.getMessage(), actual.getMessage());
    }

    @Test
    public void insertNewOrder_IfBookStockIsZero_ThenReturnNoBookLeftException() {
        Exception expected = new Exception(String.format(ExceptionMessages.NO_BOOK_LEFT, "book"));
        Order order = new Order();
        order.setOrderedBooks(Arrays.asList(new Book("book", 10, 1)));
        when(mongoTemplate.findOne(any(), any())).thenReturn(new Book("book", 10, 0));

        Exception actual = assertThrows(Exception.class, () -> customOrderRepositoryImpl.insertNewOrder(order));
        assertEquals(expected.getMessage(), actual.getMessage());
    }
}
