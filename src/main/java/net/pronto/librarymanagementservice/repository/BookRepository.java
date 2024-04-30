package net.pronto.librarymanagementservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.pronto.librarymanagementservice.entity.Book;
	
public interface BookRepository extends JpaRepository<Book, Long>{
	
	List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContainingIgnoreCase(String author);
    Book findByIdAndIsAvailableTrue(Long id);

}
