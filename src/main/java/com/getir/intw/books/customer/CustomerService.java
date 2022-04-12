package com.getir.intw.books.customer;

import com.getir.intw.books.exceptions.ExceptionMessages;
import com.getir.intw.books.order.Order;
import com.getir.intw.books.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderService orderService;

    public Customer createNewCustomer(String email) throws Exception {
        if (email.isBlank()) {
            throw new Exception(ExceptionMessages.EMPTY_EMAIL);
        }
        if(customerRepository.findByEmail(email).isPresent()) {
            throw new Exception(ExceptionMessages.CUSTOMER_ALREADY_EXIST);
        }
        Customer c = new Customer(email);
        customerRepository.save(c);

        return c;
    }

    public Page<Order> getAllBookOrders(String email, int page, int size) throws Exception {
        if (email.isBlank()) {
            throw new Exception(ExceptionMessages.EMPTY_EMAIL);
        }
        return orderService.getOrdersBy(email, page, size);
    }
}
