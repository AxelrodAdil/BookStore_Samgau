package kz.axelrodadil.bookstore_samgau.controller;

import kz.axelrodadil.bookstore_samgau.dto.ApiResponse;
import kz.axelrodadil.bookstore_samgau.model.Book;
import kz.axelrodadil.bookstore_samgau.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookStore/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    @PreAuthorize("hasAuthority('scheduler:read')")
    public ResponseEntity<ApiResponse<List<?>>> getAllBookByBookshelf(
            @RequestParam("bookshelf") String bookshelf
    ) {
        List<Book> bookList = bookService.getAllBookByBookshelf(bookshelf);
        return ResponseEntity.status(200).body(ApiResponse.success(bookList));
    }
}
