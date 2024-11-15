package com.api.book.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.book.dao.BookRepository;
import com.api.book.entity.Book;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	private static List<Book> booklist = new ArrayList<Book>();
	
	/*
	static {
		booklist.add(new Book(1,"ghjgjkg", "kogog"));
		booklist.add(new Book(2,"weeww", "ggggg"));
		booklist.add(new Book(3,"wewr", "fgdgfdg"));
	}*/

    // get all books
	public List<Book> getBooks(){
		booklist=(List<Book>)bookRepository.findAll();
		return booklist;
	}
	
	// get single book
	public Book getBookById(int id) {

     Book book =null;	
     try {
	  //book = booklist.stream().filter(e->e.getId()==id).findFirst().get();
    	book=bookRepository.findById(id);
     }catch(Exception e) {
    	 e.printStackTrace();
     }
	 return book;
	}
	
	//create book
	public Book addBook(Book b) {
		//booklist.add(b);
		Book b1=bookRepository.save(b);
		return b1;
	}

	public void deleteBook(int bookId) {
		
		/*booklist=booklist.stream().filter(book->{
			if(book.getId()!=bookId) {
				return true;
			}else {
				return false;
			}
		}).collect(Collectors.toList());*/
		bookRepository.deleteById(bookId);
	}

	public void updateBook(Book book, int bookId) {
		
		/*booklist = booklist.stream().map(b ->{
			if(b.getId()==bookId) {
				b.setId(bookId);
				b.setTitle(book.getTitle());
				b.setAuthor(book.getAuthor());
			}
			return b;
		}).collect(Collectors.toList());*/
		book.setId(bookId);
		bookRepository.save(book);
	}
}
