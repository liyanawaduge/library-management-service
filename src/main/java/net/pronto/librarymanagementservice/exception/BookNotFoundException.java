package net.pronto.librarymanagementservice.exception;

public class BookNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BookNotFoundException(String ex) {
		super(ex);
	}

}
