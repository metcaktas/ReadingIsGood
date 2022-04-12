package com.getir.intw.books.order;

import com.getir.intw.books.book.Book;
import com.getir.intw.books.rest.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/new-order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response insertNewOrder(@RequestBody Order order) {
        try{
            Order o = orderService.insertNewOrder(order);
            return Response.success(o);
        }
        catch (Exception ex) {
            return Response.fail(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Response getOrderBy(@PathVariable String id) {
        try{
            Order o = orderService.getOrderBy(id);
            return Response.success(o);
        }
        catch (Exception ex) {
            return Response.fail(ex.getMessage());
        }
    }

    @GetMapping()
    public Response getOrderBy(@RequestParam(value = "from") Instant from, @RequestParam(value = "to") Instant to) {
        try{
            List<Order> o = orderService.getOrdersBy(from, to);
            return Response.success(o);
        }
        catch (Exception ex) {
            return Response.fail(ex.getMessage());
        }
    }
}
