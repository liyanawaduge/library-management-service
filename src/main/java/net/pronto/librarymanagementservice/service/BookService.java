package net.pronto.librarymanagementservice.service;

import java.util.List;

import net.pronto.librarymanagementservice.dto.BookDTO;
import net.pronto.librarymanagementservice.dto.BookResponseDTO;

public interface BookService {
	
	String addBook(BookDTO bookDTO);
	
	BookResponseDTO updateBook(Long bookId, BookDTO bookDTO);
	
	List<BookResponseDTO> getAllBooks();
	
	List<BookResponseDTO> searchByTitle(String title);
	
    List<BookResponseDTO> searchByAuthor(String author);
	
    BookResponseDTO loanBook(Long bookId);
    
    BookResponseDTO returnBook(Long bookId);

}
