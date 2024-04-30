package net.pronto.librarymanagementservice.adaptor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import net.pronto.librarymanagementservice.dto.BookDTO;
import net.pronto.librarymanagementservice.dto.BookResponseDTO;
import net.pronto.librarymanagementservice.entity.Book;

@Component
public class BookAdaptor {

	public Book adaptAddRequest(BookDTO bookDTO) {
		Book book = new Book();
		book.setTitle(bookDTO.getTitle());
		book.setAuthor(bookDTO.getAuthor());
		book.setCount(bookDTO.getCount());
		return book;
	}
	

	public BookResponseDTO adaptBookResponseDTO(Book book) {
		BookResponseDTO bookResponseDTO = new BookResponseDTO();
		bookResponseDTO.setId(book.getId());
		bookResponseDTO.setTitle(book.getTitle());
		bookResponseDTO.setAuthor(book.getAuthor());
		bookResponseDTO.setIsAvailable(book.getIsAvailable());
		bookResponseDTO.setCount(book.getCount());
		return bookResponseDTO;
	}


	public List<BookResponseDTO> adaptBookResponseDTOList(List<Book> bookList) {
		return bookList.stream().map(this::adaptBookResponseDTO).collect(Collectors.toList());
	}

}
