package net.pronto.librarymanagementservice.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;

import net.pronto.librarymanagementservice.dto.BookDTO;
import net.pronto.librarymanagementservice.dto.BookResponseDTO;
import net.pronto.librarymanagementservice.service.BookService;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

	@Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    void testAddBook() {
        // Mock data
        BookDTO bookDTO = new BookDTO();
        when(bookService.addBook(any())).thenReturn("SUCCESS");

        // Call the controller method
        ResponseEntity<String> response = bookController.addBook(bookDTO);

        // Verify the result
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("SUCCESS", response.getBody());
    }
    
    @Test
    void testUpdateBook() {
        // Mock data
        long bookId = 1L;
        BookDTO bookDTO = new BookDTO();
        BookResponseDTO responseDTO = new BookResponseDTO();
        when(bookService.updateBook(anyLong(), any())).thenReturn(responseDTO);

        // Call the controller method
        ResponseEntity<BookResponseDTO> response = bookController.updateBook(bookId, bookDTO);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }
    
    @Test
    void testGetAllBooks() {
        // Mock data
        List<BookResponseDTO> bookList = new ArrayList<>();
        bookList.add(new BookResponseDTO());
        when(bookService.getAllBooks()).thenReturn(bookList);

        // Call the controller method
        ResponseEntity<List<BookResponseDTO>> response = bookController.getAllBooks();

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookList, response.getBody());
    }

    @Test
    void testSearchByTitle() {
        // Mock data
        String title = "Test Title";
        List<BookResponseDTO> bookList = new ArrayList<>();
        bookList.add(new BookResponseDTO());
        when(bookService.searchByTitle(anyString())).thenReturn(bookList);

        // Call the controller method
        ResponseEntity<List<BookResponseDTO>> response = bookController.searchByTitle(title);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookList, response.getBody());
    }
    
    @Test
    void testSearchByAuthor() {
        // Mock data
        String author = "Test Author";
        List<BookResponseDTO> bookList = new ArrayList<>();
        bookList.add(new BookResponseDTO());
        when(bookService.searchByAuthor(anyString())).thenReturn(bookList);

        // Call the controller method
        ResponseEntity<List<BookResponseDTO>> response = bookController.searchByAuthor(author);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookList, response.getBody());
    }

    @Test
    void testLoanBook() {
        // Mock data
        long bookId = 1L;
        BookResponseDTO responseDTO = new BookResponseDTO();
        when(bookService.loanBook(anyLong())).thenReturn(responseDTO);

        // Call the controller method
        ResponseEntity<BookResponseDTO> response = bookController.loanBook(bookId);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void testReturnBook() {
        // Mock data
        long bookId = 1L;
        BookResponseDTO responseDTO = new BookResponseDTO();
        when(bookService.returnBook(anyLong())).thenReturn(responseDTO);

        // Call the controller method
        ResponseEntity<BookResponseDTO> response = bookController.returnBook(bookId);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }
}
