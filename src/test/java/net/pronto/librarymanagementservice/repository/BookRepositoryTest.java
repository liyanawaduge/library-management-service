package net.pronto.librarymanagementservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import net.pronto.librarymanagementservice.entity.Book;
import net.pronto.librarymanagementservice.service.impl.BookServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BookRepositoryTest {

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private BookServiceImpl bookService; // Assuming this is your service class that uses BookRepository

	@Test
	void testFindByTitleContainingIgnoreCase() {
		// Mock data
		String title = "Test Title";
		List<Book> books = new ArrayList<>();
		when(bookRepository.findByTitleContainingIgnoreCase(any())).thenReturn(books);

		// Call the repository method
		List<Book> result = bookRepository.findByTitleContainingIgnoreCase(title);

		// Verify the result
		assertEquals(books, result);
	}

	@Test
	void testFindByAuthorContainingIgnoreCase() {
		// Mock data
		String author = "Test Author";
		List<Book> books = new ArrayList<>();
		when(bookRepository.findByAuthorContainingIgnoreCase(any())).thenReturn(books);

		// Call the repository method
		List<Book> result = bookRepository.findByAuthorContainingIgnoreCase(author);

		// Verify the result
		assertEquals(books, result);
	}

}
