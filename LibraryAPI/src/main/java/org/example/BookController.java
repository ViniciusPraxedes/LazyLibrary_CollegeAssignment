package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @Autowired
    BookList bookList;

    @GetMapping("/books")
    public Book[] getAllBooks(){
        return bookList.getAllBooks();
    }

    @PostMapping ("book")
    public String addBook(Book book){
        bookList.addBook(book);
        return "Book successfully added";
    }
    @PutMapping("book")
    public ResponseEntity<String> replaceBook(String nameOfTheBookToBeReplaced, Book book) {
        boolean success = bookList.replaceBook(nameOfTheBookToBeReplaced, book);
        if (success) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Book successfully replaced");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to find book");
    }

    @DeleteMapping("book")
    public ResponseEntity<String> deleteBook(String name) {
        boolean success = bookList.deleteBook(name);
        if (success) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successful deletion!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such user.");
    }




}
