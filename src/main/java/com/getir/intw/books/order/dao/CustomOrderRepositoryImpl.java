package com.getir.intw.books.order.dao;

import com.getir.intw.books.book.Book;
import com.getir.intw.books.exceptions.ExceptionMessages;
import com.getir.intw.books.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.transaction.annotation.Transactional;

public class CustomOrderRepositoryImpl implements CustomOrderRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Transactional
    @Override
    public Order insertNewOrder(Order newOrder) throws Exception {
        for (Book b : newOrder.getOrderedBooks()) {
            Query query = new Query(Criteria.where("name").is(b.getName()));
            Book dbBook = mongoTemplate.findOne(query, Book.class);
            if (dbBook == null) {
                throw new Exception(String.format(ExceptionMessages.BOOK_NOT_EXIST, b.getName()));
            }

            if (dbBook.getStock() == 0) {
                throw new Exception(String.format(ExceptionMessages.NO_BOOK_LEFT, dbBook.getName()));
            }

            Update update = new Update();
            update.set("stock", dbBook.getStock() - 1);
            mongoTemplate.findAndModify(query, update, Book.class);
        }

        return mongoTemplate.save(newOrder);
    }
}
