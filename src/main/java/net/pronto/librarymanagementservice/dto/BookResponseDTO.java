package net.pronto.librarymanagementservice.dto;

import lombok.Data;

@Data
public class BookResponseDTO {
	
	private Long id;
	
	private String title;
	
	private String author;
	
	private Boolean isAvailable;
	
	private Integer count;
}
