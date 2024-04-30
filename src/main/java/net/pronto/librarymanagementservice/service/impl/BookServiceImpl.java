package net.pronto.librarymanagementservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.pronto.librarymanagementservice.adaptor.BookAdaptor;
import net.pronto.librarymanagementservice.dto.BookDTO;
import net.pronto.librarymanagementservice.dto.BookResponseDTO;
import net.pronto.librarymanagementservice.entity.Book;
import net.pronto.librarymanagementservice.exception.BookNotFoundException;
import net.pronto.librarymanagementservice.repository.BookRepository;
import net.pronto.librarymanagementservice.service.BookService;
import net.pronto.librarymanagementservice.util.Constant;

@Service
public class BookServiceImpl implements BookService {

	private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookAdaptor bookAdaptor;

	@Override
	public String addBook(BookDTO bookDTO) {
		logger.info("Started BookServiceImpl::addBook()::title..." + bookDTO.getTitle());

		bookRepository.save(bookAdaptor.adaptAddRequest(bookDTO));
		return Constant.SUCCESS;
	}

	@Override
	public BookResponseDTO updateBook(Long bookId, BookDTO updatedBook) {
		logger.info("Started BookServiceImpl::updateBook()::bookId..." + bookId);

		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new BookNotFoundException(Constant.BOOK_NOT_FOUND));

		book.setTitle(updatedBook.getTitle());
		book.setAuthor(updatedBook.getAuthor());
		book.setIsAvailable(true);
		book.setCount(book.getCount() + updatedBook.getCount());
		bookRepository.saveAndFlush(book);

		logger.info("End BookServiceImpl::updateBook()::bookId..." + bookId);
		return bookAdaptor.adaptBookResponseDTO(book);
	}

	@Override
	public List<BookResponseDTO> getAllBooks() {
		logger.info("Started BookServiceImpl::getAllBooks()...");

		List<Book> bookList = bookRepository.findAll();
		List<BookResponseDTO> bookResponseDTOList = new ArrayList<>();
		if (!bookList.isEmpty()) {
			bookResponseDTOList = bookAdaptor.adaptBookResponseDTOList(bookList);
		}
		logger.info("End BookServiceImpl::getAllBooks()...");
		return bookResponseDTOList;
	}

	@Override
	public List<BookResponseDTO> searchByTitle(String title) {
		logger.info("Started BookServiceImpl::searchByTitle()::title..." + title);
		List<Book> bookList = bookRepository.findByTitleContainingIgnoreCase(title);
		List<BookResponseDTO> bookResponseDTOList = new ArrayList<>();
		if (!bookList.isEmpty()) {
			bookResponseDTOList = bookAdaptor.adaptBookResponseDTOList(bookList);
		}
		logger.info("End BookServiceImpl::searchByTitle()..." + title);
		return bookResponseDTOList;
	}

	@Override
	public List<BookResponseDTO> searchByAuthor(String author) {
		logger.info("Started BookServiceImpl::searchByAuthor()::author..." + author);
		List<Book> bookList = bookRepository.findByAuthorContainingIgnoreCase(author);
		List<BookResponseDTO> bookResponseDTOList = new ArrayList<>();
		if (!bookList.isEmpty()) {
			bookResponseDTOList = bookAdaptor.adaptBookResponseDTOList(bookList);
		}
		logger.info("End BookServiceImpl::searchByAuthor()::author..." + author);
		return bookResponseDTOList;
	}

	@Override
	public BookResponseDTO loanBook(Long bookId) {
		logger.info("Started BookServiceImpl::loanBook()::bookId..." + bookId);
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new BookNotFoundException(Constant.BOOK_NOT_FOUND));

		if (book.getIsAvailable()) {
			book.setCount(book.getCount() - 1);
			if (book.getCount() == 0) {
				book.setIsAvailable(false);
			}
			bookRepository.saveAndFlush(book);
		}
		logger.info("End BookServiceImpl::loanBook()::bookId..." + bookId);
		return bookAdaptor.adaptBookResponseDTO(book);
	}

	@Override
	public BookResponseDTO returnBook(Long bookId) {
		logger.info("Started BookServiceImpl::returnBook()::bookId..." + bookId);
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new BookNotFoundException(Constant.BOOK_NOT_FOUND));

		book.setCount(book.getCount() + 1);
		book.setIsAvailable(true);
		bookRepository.saveAndFlush(book);

		logger.info("End BookServiceImpl::returnBook()::bookId..." + bookId);
		return bookAdaptor.adaptBookResponseDTO(book);
	}

}
