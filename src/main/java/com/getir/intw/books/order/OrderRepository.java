package com.getir.intw.books.order;

import com.getir.intw.books.order.dao.CustomOrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>, CustomOrderRepository {

    List<Order> findByDateBetween(Instant from, Instant to);
    Page<Order> findByEmail(String email, Pageable pageable);
}
