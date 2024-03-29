package tech.tranuytrieuvi.simpleweb.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.tranuytrieuvi.simpleweb.repo.BookRepo;
import tech.tranuytrieuvi.simpleweb.ResourceNotFoundException;
import tech.tranuytrieuvi.simpleweb.model.Book;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Tag(name = "Book", description = "The Book API")
@RestController
@RequestMapping("/api/v1")
public class BookController {

  @Autowired
  private final BookRepo bookRepo;

  @GetMapping("/books")
  public List<Book> getAllBooks() {
    return bookRepo.findAll();
  }

  @GetMapping("/book/{id}")
  public Book getBookById(@PathVariable(value = "id") UUID bookId) throws ResourceNotFoundException {
    return bookRepo.findById(bookId)
        .orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + bookId));
  }

  @PostMapping("/book")
  public Book createBook(@RequestBody Book book) {
    return bookRepo.save(book);
  }

  @PutMapping("/book/{id}")
  public Book updateBook(
      @PathVariable(value = "id") UUID bookId,
      @RequestBody Book bookDetails) throws ResourceNotFoundException {
    Book book = bookRepo.findById(bookId)
        .orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + bookId));
    book.setTitle(bookDetails.getTitle());
    book.setAuthor(bookDetails.getAuthor());
    book.setDescription(bookDetails.getDescription());
    return bookRepo.save(book);
  }

  @DeleteMapping("/book/{id}")
  public void deleteBook(@PathVariable(value = "id") UUID bookId) throws ResourceNotFoundException {
    Book book = bookRepo.findById(bookId)
        .orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + bookId));
    bookRepo.delete(book);
  }
}
