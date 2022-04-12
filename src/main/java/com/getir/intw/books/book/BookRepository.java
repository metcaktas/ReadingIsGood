package com.getir.intw.books.book;

import com.getir.intw.books.book.dao.CustomBookRepository;
import com.getir.intw.books.customer.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BookRepository extends MongoRepository<Book, String>, CustomBookRepository {

    Optional<Book> findByName(String name);
}
