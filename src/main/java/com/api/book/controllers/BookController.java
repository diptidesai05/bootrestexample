package com.api.book.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.entity.Book;
import com.api.book.services.BookService;

//@Controller
@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;

	//@RequestMapping(value="/book", method=RequestMethod.GET)
	//@ResponseBody
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks() {
	
		List<Book> bookList= bookService.getBooks();
		if(bookList.size()<=0) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(bookList);
		
	}
	
	//get single book by id
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
		Book book= bookService.getBookById(id);
		if(book==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}
	
	//Create a book
	@PostMapping("/books")
	public ResponseEntity<Book>  addBook(@RequestBody Book book) {
		Book b=null;
		try {
		  b=bookService.addBook(book);
		  System.out.println(b);
		  return ResponseEntity.of(Optional.of(book));
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//delete a book
	@DeleteMapping("books/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId) {
		
		try {
		   bookService.deleteBook(bookId);
		   return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	//update a book
	@PutMapping("/books/{bookId}")
	public ResponseEntity<Book>  updateBook(@RequestBody Book book, @PathVariable("bookId") int bookId) {
	
		try {
		  bookService.updateBook(book, bookId);
		  return ResponseEntity.ok().body(book);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}

