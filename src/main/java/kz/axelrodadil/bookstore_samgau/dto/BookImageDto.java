package kz.axelrodadil.bookstore_samgau.dto;

import lombok.Data;

import java.io.File;

@Data
public class BookImageDto {
    private BookDto bookDto;
    private File file;
}
