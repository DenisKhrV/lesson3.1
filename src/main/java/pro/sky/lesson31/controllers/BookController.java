package pro.sky.lesson31.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.lesson31.model.Book;
import pro.sky.lesson31.services.BookService;

@RestController
@RequestMapping("books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("{id}") //  GET http://localhost:8080/books/2
    public ResponseEntity<Book> getBookInfo(@PathVariable Long id) {
        Book book = bookService.findBook(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @PostMapping // POST http://localhost:8080/books
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PutMapping // PUT http://localhost:8080/books
    public ResponseEntity<Book> editBook(@RequestBody Book book) {
        Book foundBook = bookService.editBook(book);
        if (foundBook == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundBook);
    }

    @DeleteMapping("{id}")// DELETE http://localhost:8080/books/2
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        Book deleteBook = bookService.deleteBook(id);
        if (deleteBook == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleteBook);
    }
}
