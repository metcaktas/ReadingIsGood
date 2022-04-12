package com.getir.intw.books.book;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Getter @Setter @NoArgsConstructor
@Document("book")
public class Book {

    @Id
    private String id;

    private String name;
    private double price;
    private int stock;

    public Book(String name, double price, int stockCount) {
        this.name = name;
        this.price = price;
        this.stock = stockCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Double.compare(book.price, price) == 0 && stock == book.stock && id.equals(book.id) && Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, stock);
    }
}
