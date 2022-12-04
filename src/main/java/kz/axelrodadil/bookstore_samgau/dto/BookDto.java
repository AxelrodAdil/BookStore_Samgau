package kz.axelrodadil.bookstore_samgau.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookDto {
    private Long bookId;
    private String authorFullName;
    private String bookName;
    private String bookshelf;
    private Double bookPrice;
    private LocalDateTime bookPublishedYear;
}
