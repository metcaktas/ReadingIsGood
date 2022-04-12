package com.getir.intw.books.book;

import com.getir.intw.books.rest.Response;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping(value = "/add-book", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response addNewBook(@RequestBody Book newBook) {
        try{
            Book b = bookService.addNewBook(newBook);

            return Response.success(b);
        }
        catch (Exception ex) {
            return Response.fail(ex.getMessage());
        }
    }

    @PostMapping("/update-stock")
    public Response updateBookStockCount(@RequestParam(value = "name") String name, @RequestParam(value = "stock") int stock) {
        try{
            Book b = bookService.updateBookStockCount(name, stock);
            return Response.success(b);
        }
        catch (Exception ex) {
            return Response.fail(ex.getMessage());
        }
    }
}
