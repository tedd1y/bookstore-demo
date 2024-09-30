package io.github.tedd1y.bookstore.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new IllegalArgumentException("Book with ID " + id + " doesn't exist.");
        }
        bookRepository.deleteById(id);
    }

    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findAll().stream().filter(book -> book.getTitle().toLowerCase()
                .contains(title.toLowerCase())).toList();
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findAll().stream().filter(book -> book.getAuthor().toLowerCase()
                .contains(author.toLowerCase())).toList();
    }

    public List<Book> getBooksByYear(Integer year) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getYear() != null && book.getYear()
                        .equals(year))
                        .toList();
    }

    public List<Book> getBooksByRating(Integer rating) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getRating() != null && book.getRating()
                        .equals(rating))
                        .toList();
    }

    public Book addBook(Book book) {
        bookRepository.save(book);
        return book;
    }

    public Book updateBook(Book updatedBook) {
        Optional<Book> existingBook = bookRepository.findById(updatedBook.getId());
        if (existingBook.isPresent()) {
            Book bookToUpdate = existingBook.get();
            bookToUpdate.setTitle(updatedBook.getTitle());
            bookToUpdate.setAuthor(updatedBook.getAuthor());
            bookToUpdate.setYear(updatedBook.getYear());
            if (updatedBook.getRating() != null && (updatedBook.getRating() >= 1 || updatedBook.getRating() <= 5)) {
                bookToUpdate.setRating(updatedBook.getRating());
            } else {
                bookToUpdate.setRating(null);
            }
            bookToUpdate.setRating(updatedBook.getRating());

            bookRepository.save(bookToUpdate);
            return bookToUpdate;
        }
        return null;
    }

    public Book updateBookRating(Long id, Integer rating) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            Book bookToUpdate = existingBook.get();
            bookToUpdate.setRating(rating);
            bookRepository.save(bookToUpdate);
            return bookToUpdate;
        }
        return null;
    }
}
