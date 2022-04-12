package com.getir.intw.books.book.dao;

import com.getir.intw.books.book.Book;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class CustomBookRepositoryImpl implements CustomBookRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Book updateBookStockCount(String name, int stock) {
        Query query = new Query(Criteria.where("name").is(name));

        Update update = new Update();
        update.set("stock", stock);

        Book oldBook = mongoTemplate.findAndModify(query, update, Book.class);
        oldBook.setStock(stock);
        return oldBook;
    }
}
