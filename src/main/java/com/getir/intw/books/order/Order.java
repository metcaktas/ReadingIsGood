package com.getir.intw.books.order;

import com.getir.intw.books.book.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;
import java.util.Objects;


@Getter @Setter @NoArgsConstructor
@Document("order")
public class Order {

    @Id
    private String id;

    // reference to customer
    private String email;
    private Instant date = Instant.now();
    private List<Book> orderedBooks;

    public Order(List<Book> orderedBooks) {
        this.orderedBooks = orderedBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id) && email.equals(order.email) && Objects.equals(date, order.date) && Objects.equals(orderedBooks, order.orderedBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, date, orderedBooks);
    }
}
