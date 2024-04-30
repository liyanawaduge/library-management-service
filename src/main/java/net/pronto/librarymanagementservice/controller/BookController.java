package net.pronto.librarymanagementservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import net.pronto.librarymanagementservice.dto.BookDTO;
import net.pronto.librarymanagementservice.dto.BookResponseDTO;
import net.pronto.librarymanagementservice.service.BookService;
import net.pronto.librarymanagementservice.util.Constant;
import net.pronto.librarymanagementservice.util.SwaggerDocConstant;

@RestController
@Validated
@RequestMapping("/api/book")
public class BookController {

	private static final Logger logger = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookService bookService;

	@Operation(summary = SwaggerDocConstant.ADD_BOOK_API)
	@PostMapping("/add/v1")
	public ResponseEntity<String> addBook(@Valid @RequestBody BookDTO bookDTO) {
		logger.info("Started BookController::addBook()::title..." + bookDTO.getTitle());
		String status = bookService.addBook(bookDTO);
		return new ResponseEntity<>(status, HttpStatus.CREATED);

	}

	@Operation(summary = SwaggerDocConstant.UPDATE_BOOK_API)
	@PutMapping("/update/v1/{bookId}")
	public ResponseEntity<BookResponseDTO> updateBook(@PathVariable @Positive Long bookId,
			@Valid @RequestBody BookDTO bookDTO) {
		logger.info("Started BookController::updateBook()::id..." + bookId);
		BookResponseDTO bookResponseDTO = bookService.updateBook(bookId, bookDTO);
		return new ResponseEntity<>(bookResponseDTO, HttpStatus.OK);

	}

	@Operation(summary = SwaggerDocConstant.GET_ALL_BOOKS_API)
	@GetMapping("/get-all/v1")
	public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
		logger.info("Started BookController::getAllBooks()...");
		List<BookResponseDTO> bookList = bookService.getAllBooks();
		return new ResponseEntity<>(bookList, HttpStatus.OK);

	}

	@Operation(summary = SwaggerDocConstant.SEARCH_BY_TITLE_API)
	@GetMapping("/search-by/title/v1")
	public ResponseEntity<List<BookResponseDTO>> searchByTitle(
			@RequestParam @NotBlank(message = Constant.BOOK_TITLE_CANNOT_BLANK) String title) {
		logger.info("Started BookController::searchByTitle()::title..." + title);
		List<BookResponseDTO> bookList = bookService.searchByTitle(title);
		return new ResponseEntity<>(bookList, HttpStatus.OK);

	}

	@Operation(summary = SwaggerDocConstant.SEARCH_BY_AUTHER_API)
	@GetMapping("/search-by/author/v1")
	public ResponseEntity<List<BookResponseDTO>> searchByAuthor(
			@RequestParam @NotBlank(message = Constant.BOOK_AUTHOR_CANNOT_BLANK) String author) {
		logger.info("Started BookController::searchByAuthor()::author..." + author);
		List<BookResponseDTO> bookList = bookService.searchByAuthor(author);
		return new ResponseEntity<>(bookList, HttpStatus.OK);

	}

	@Operation(summary = SwaggerDocConstant.LOAN_BOOK_API)
	@PutMapping("/loan/v1/{bookId}")
	public ResponseEntity<BookResponseDTO> loanBook(@PathVariable @Positive Long bookId) {
		logger.info("Started BookController::loanBook()::bookId..." + bookId);
		BookResponseDTO bookResponseDTO = bookService.loanBook(bookId);
		return new ResponseEntity<>(bookResponseDTO, HttpStatus.OK);

	}

	@Operation(summary = SwaggerDocConstant.RETURN_BOOK_API)
	@PutMapping("/return/v1/{bookId}")
	public ResponseEntity<BookResponseDTO> returnBook(@PathVariable @Positive Long bookId) {
		logger.info("Started BookController::returnBook()::bookId..." + bookId);
		BookResponseDTO bookResponseDTO = bookService.returnBook(bookId);
		return new ResponseEntity<>(bookResponseDTO, HttpStatus.OK);

	}

}
