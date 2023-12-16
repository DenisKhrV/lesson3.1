package pro.sky.lesson31.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.lesson31.model.Book;
import pro.sky.lesson31.services.BookService;

import java.util.Collection;

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

    @GetMapping //  GET http://localhost:8080/books
    public ResponseEntity findBooks(@RequestParam(required = false) String name,
                                    @RequestParam(required = false) String author,
                                    @RequestParam(required = false) String namePart) {
        if (name != null && !name.isBlank()) {
        return ResponseEntity.ok(bookService.findByName(name));
        }
        if (author != null && !author.isBlank()) {
        return ResponseEntity.ok(bookService.findByAuthor(author));
        }
        if (namePart != null && !namePart.isBlank()) {
        return ResponseEntity.ok(bookService.findByNamePart(namePart));
        }
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping // POST http://localhost:8080/books
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PutMapping // PUT http://localhost:8080/books
    public ResponseEntity<Book> editBook(@RequestBody Book book) {
        Book foundBook = bookService.editBook(book);
        if (foundBook == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundBook);
    }

    @DeleteMapping("{id}")// DELETE http://localhost:8080/books/2
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
