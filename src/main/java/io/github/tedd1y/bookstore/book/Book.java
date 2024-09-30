package io.github.tedd1y.bookstore.book;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private Integer year;
    private Integer rating;

    public Book() {
    }

    public Book(Long id, String title, String author, Integer rating, Integer year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.year = year;
    }

    public Book(String title, String author, Integer rating, Integer year) {
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
