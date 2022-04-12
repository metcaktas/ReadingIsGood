package com.getir.intw.books.customer;


import com.getir.intw.books.exceptions.ExceptionMessages;
import com.getir.intw.books.order.Order;
import com.getir.intw.books.order.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    OrderService orderService;

    @InjectMocks
    CustomerService customerService;

    @Test
    public void addNewCustomer_IfCreateSuccessful_ThenReturnCustomer() throws Exception {
        Customer expected = new Customer("customer");
        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        Customer actual = customerService.createNewCustomer("customer");
        assertEquals(expected, actual);
    }

    @Test
    public void addNewCustomer_IfCustomerNameIsEmpty_ThenReturnEmptyNameException() {
        Exception expected = new Exception(ExceptionMessages.EMPTY_EMAIL);

        Exception actual = assertThrows(Exception.class, () -> customerService.createNewCustomer(""));
        assertEquals(expected.getMessage(), actual.getMessage());
    }

    @Test
    public void addNewCustomer_IfCustomerNameAlreadyExist_ThenReturnNameAlreadyExistException() {
        Exception expected = new Exception(ExceptionMessages.CUSTOMER_ALREADY_EXIST);
        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.of(new Customer("customer")));

        Exception actual = assertThrows(Exception.class, () -> customerService.createNewCustomer("customer"));
        assertEquals(expected.getMessage(), actual.getMessage());
    }

    @Test
    public void getAllBookOrders_IfCustomerHasOrders_ThenReturnSuccessWithOrdersInfoResponseWith() throws Exception {
        Page<Order> expected = Page.empty();
        when(orderService.getOrdersBy("customer", 0, 10)).thenReturn(expected);

        Page<Order> actual = customerService.getAllBookOrders("customer", 0, 10);
        assertEquals(expected, actual);
    }
}
