package com.getir.intw.books.customer;

import com.getir.intw.books.exceptions.ExceptionMessages;
import com.getir.intw.books.order.Order;
import com.getir.intw.books.rest.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping(value = "/register")
    public Response addNewCustomer(@RequestParam(value = "email") String email) {
        try{
            Customer c = customerService.createNewCustomer(email);
            return Response.success(c);
        }
        catch (Exception ex) {
            return Response.fail(ex.getMessage());
        }
    }

    @GetMapping("/orders")
    public Response getAllBookOrders(@RequestParam(value = "email") String email, @RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
        try{
            Pageable pageable = PageRequest.of(page, size != 0 ? size : 10);
            Page<Order> result = customerService.getAllBookOrders(email, page, size);
            return Response.success(result);
        }
        catch (Exception ex) {
            return Response.fail(ex.getMessage());
        }

    }
}
