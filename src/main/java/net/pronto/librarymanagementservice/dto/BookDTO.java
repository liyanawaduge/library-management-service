package net.pronto.librarymanagementservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import net.pronto.librarymanagementservice.util.Constant;

@Data
public class BookDTO {
	
	@NotBlank(message = Constant.BOOK_TITLE_MANDATORY)
	private String title;	
	
	@NotBlank(message = Constant.BOOK_AUTHOR_MANDATORY)
	private String author;	
	
	@NotNull(message = Constant.BOOK_COUNT_MANDATORY)
	@Positive(message = Constant.BOOK_COUNT_VALUE_GREATER_THAN_ZERO)
	private Integer count;
}
