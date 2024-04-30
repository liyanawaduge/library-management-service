package net.pronto.librarymanagementservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;

import net.pronto.librarymanagementservice.adaptor.BookAdaptor;
import net.pronto.librarymanagementservice.dto.BookDTO;
import net.pronto.librarymanagementservice.dto.BookResponseDTO;
import net.pronto.librarymanagementservice.entity.Book;
import net.pronto.librarymanagementservice.repository.BookRepository;
import net.pronto.librarymanagementservice.util.Constant;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {
	
	@Mock
    private BookRepository bookRepository;

    @Mock
    private BookAdaptor bookAdaptor;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void testAddBook() {
        // Mock data
        BookDTO bookDTO = new BookDTO();
        when(bookAdaptor.adaptAddRequest(any())).thenReturn(new Book());
        when(bookRepository.save(any())).thenReturn(new Book());

        // Call the service method
        String result = bookService.addBook(bookDTO);

        // Verify the result
        assertEquals(Constant.SUCCESS, result);
        verify(bookAdaptor).adaptAddRequest(any());
        verify(bookRepository).save(any());
    }
    
    @Test
    void testUpdateBook() {
        // Mock data
        long bookId = 1L;
        BookDTO updatedBookDTO = new BookDTO();
        updatedBookDTO.setTitle("Updated Title");
        updatedBookDTO.setAuthor("Updated Author");
        updatedBookDTO.setCount(2);

        Book book = new Book();
        book.setId(bookId);
        book.setTitle("Original Title");
        book.setAuthor("Original Author");
        book.setCount(1);

        // Mocking bookRepository.findById()
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));

        // Mocking bookAdaptor.adaptBookResponseDTO()
        when(bookAdaptor.adaptBookResponseDTO(any())).thenReturn(new BookResponseDTO());

        // Call the service method
        BookResponseDTO responseDTO = bookService.updateBook(bookId, updatedBookDTO);

        // Verify the result
        assertEquals(bookId, book.getId());
        assertEquals(updatedBookDTO.getTitle(), book.getTitle());
        assertEquals(updatedBookDTO.getAuthor(), book.getAuthor());
        assertEquals(true, book.getIsAvailable());
        assertEquals(3, book.getCount()); // 1 (original count) + 2 (updated count)

        verify(bookRepository).findById(bookId);
        verify(bookRepository).saveAndFlush(book);
        verify(bookAdaptor).adaptBookResponseDTO(book);
    }
    
    @Test
    void testGetAllBooks() {
        // Mock data
        List<Book> mockBookList = new ArrayList<>();
        mockBookList.add(new Book());
        mockBookList.add(new Book());

        List<BookResponseDTO> mockBookResponseDTOList = new ArrayList<>();
        mockBookResponseDTOList.add(new BookResponseDTO());
        mockBookResponseDTOList.add(new BookResponseDTO());

        // Mocking bookRepository.findAll()
        when(bookRepository.findAll()).thenReturn(mockBookList);

        // Mocking bookAdaptor.adaptBookResponseDTOList()
        when(bookAdaptor.adaptBookResponseDTOList(mockBookList)).thenReturn(mockBookResponseDTOList);

        // Call the service method
        List<BookResponseDTO> responseDTOList = bookService.getAllBooks();

        // Verify the result
        assertEquals(mockBookResponseDTOList, responseDTOList);

        verify(bookRepository).findAll();
        verify(bookAdaptor).adaptBookResponseDTOList(mockBookList);
    }
    
    @Test
    void testSearchByTitle() {
        // Mock data
        String title = "Test Title";
        List<Book> mockBookList = new ArrayList<>();
        mockBookList.add(new Book());
        mockBookList.add(new Book());

        List<BookResponseDTO> mockBookResponseDTOList = new ArrayList<>();
        mockBookResponseDTOList.add(new BookResponseDTO());
        mockBookResponseDTOList.add(new BookResponseDTO());

        // Mocking bookRepository.findByTitleContainingIgnoreCase()
        when(bookRepository.findByTitleContainingIgnoreCase(title)).thenReturn(mockBookList);

        // Mocking bookAdaptor.adaptBookResponseDTOList()
        when(bookAdaptor.adaptBookResponseDTOList(mockBookList)).thenReturn(mockBookResponseDTOList);

        // Call the service method
        List<BookResponseDTO> responseDTOList = bookService.searchByTitle(title);

        // Verify the result
        assertEquals(mockBookResponseDTOList, responseDTOList);

        verify(bookRepository).findByTitleContainingIgnoreCase(title);
        verify(bookAdaptor).adaptBookResponseDTOList(mockBookList);
    }

    @Test
    void testSearchByAuthor() {
        // Mock data
        String author = "Test Author";
        List<Book> mockBookList = new ArrayList<>();
        mockBookList.add(new Book());
        mockBookList.add(new Book());

        List<BookResponseDTO> mockBookResponseDTOList = new ArrayList<>();
        mockBookResponseDTOList.add(new BookResponseDTO());
        mockBookResponseDTOList.add(new BookResponseDTO());

        // Mocking bookRepository.findByAuthorContainingIgnoreCase()
        when(bookRepository.findByAuthorContainingIgnoreCase(author)).thenReturn(mockBookList);

        // Mocking bookAdaptor.adaptBookResponseDTOList()
        when(bookAdaptor.adaptBookResponseDTOList(mockBookList)).thenReturn(mockBookResponseDTOList);

        // Call the service method
        List<BookResponseDTO> responseDTOList = bookService.searchByAuthor(author);

        // Verify the result
        assertEquals(mockBookResponseDTOList, responseDTOList);

        verify(bookRepository).findByAuthorContainingIgnoreCase(author);
        verify(bookAdaptor).adaptBookResponseDTOList(mockBookList);
    }
	
    @Test
    void testLoanBook() {
        // Mock data
        long bookId = 1L;
        Book mockBook = new Book();
        mockBook.setId(bookId);
        mockBook.setCount(1);
        mockBook.setIsAvailable(true);

        // Mocking bookRepository.findById()
        when(bookRepository.findById(bookId)).thenReturn(java.util.Optional.of(mockBook));

        // Mocking bookAdaptor.adaptBookResponseDTO()
        when(bookAdaptor.adaptBookResponseDTO(any())).thenReturn(new BookResponseDTO());

        // Call the service method
        BookResponseDTO responseDTO = bookService.loanBook(bookId);

        // Verify the result
        assertNotNull(responseDTO);
        assertFalse(mockBook.getIsAvailable());
        assertEquals(0, mockBook.getCount());

        verify(bookRepository).findById(bookId);
        verify(bookRepository).saveAndFlush(mockBook);
        verify(bookAdaptor).adaptBookResponseDTO(mockBook);
    }

    @Test
    void testReturnBook() {
        // Mock data
        long bookId = 1L;
        Book mockBook = new Book();
        mockBook.setId(bookId);
        mockBook.setCount(0);
        mockBook.setIsAvailable(false);

        // Mocking bookRepository.findById()
        when(bookRepository.findById(bookId)).thenReturn(java.util.Optional.of(mockBook));

        // Mocking bookAdaptor.adaptBookResponseDTO()
        when(bookAdaptor.adaptBookResponseDTO(any())).thenReturn(new BookResponseDTO());

        // Call the service method
        BookResponseDTO responseDTO = bookService.returnBook(bookId);

        // Verify the result
        assertNotNull(responseDTO);
        assertTrue(mockBook.getIsAvailable());
        assertEquals(1, mockBook.getCount());

        verify(bookRepository).findById(bookId);
        verify(bookRepository).saveAndFlush(mockBook);
        verify(bookAdaptor).adaptBookResponseDTO(mockBook);
    }

}
