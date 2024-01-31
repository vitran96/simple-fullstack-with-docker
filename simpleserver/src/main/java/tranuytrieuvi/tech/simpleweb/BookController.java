package tranuytrieuvi.tech.simpleweb;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
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
            @RequestBody Book bookDetails
    ) throws ResourceNotFoundException {
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + bookId));
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setDescription(bookDetails.getDescription());
        return bookRepo.save(book);
    }
}
