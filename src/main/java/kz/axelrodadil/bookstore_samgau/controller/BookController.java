package kz.axelrodadil.bookstore_samgau.controller;

import com.sun.net.httpserver.Headers;
import kz.axelrodadil.bookstore_samgau.dto.ApiResponse;
import kz.axelrodadil.bookstore_samgau.dto.BookDto;
import kz.axelrodadil.bookstore_samgau.model.Book;
import kz.axelrodadil.bookstore_samgau.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Base64;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/bookStore/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
//    @PreAuthorize("hasAuthority('scheduler:read')")
    public ResponseEntity<ApiResponse<List<?>>> getAllBookByBookshelf(
            @RequestParam("bookshelf") String bookshelf
    ) {
        List<Book> bookList = bookService.getAllBookByBookshelf(bookshelf);
        return ResponseEntity.status(200).body(ApiResponse.success(bookList));
    }

    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('scheduler:read')")
    public ResponseEntity<ApiResponse<List<?>>> getAllBooks() {
        List<Book> bookList = bookService.getAllBooks();
        return ResponseEntity.status(200).body(ApiResponse.success(bookList));
    }

    @GetMapping("/discounts")
//    @PreAuthorize("hasAuthority('scheduler:read')")
    public ResponseEntity<ApiResponse<List<?>>> getAllDiscountPrice(
            @RequestParam("bookshelf") String bookshelf
    ) {
        List<BookDto> bookList = bookService.getAllBookPriceByBookshelf(bookshelf);
        return ResponseEntity.status(200).body(ApiResponse.success(bookList));
    }

    @PutMapping
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> updateAllBookDiscountByBookshelf(
            @RequestParam("bookshelf") String bookshelf,
            @RequestParam("discount") Long discount
    ) {
        bookService.updateAllBookDiscountByBookshelf(bookshelf, discount);
        return ResponseEntity.status(200).body(ApiResponse.success(true));
    }

    @PutMapping("/byId")
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> updateBookDiscountByBookId(
            @RequestParam("id") Long id,
            @RequestParam("discount") Long discount
    ) {
        bookService.updateBookDiscountByBookId(id, discount);
        return ResponseEntity.status(200).body(ApiResponse.success(true));
    }

    @PutMapping("/countById")
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> updateBookCountByBookId(
            @RequestParam("id") Long id,
            @RequestParam("count") Long count
    ) {
        bookService.updateBookCountByBookId(id, count);
        return ResponseEntity.status(200).body(ApiResponse.success(true));
    }

    @GetMapping("/likeAuthorName")
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> getBooksLikeAuthorName(
            @RequestParam("name") String name
    ) {
        var bookList = bookService.getBooksLikeAuthorName(name);
        return ResponseEntity.status(200).body(ApiResponse.success(bookList));
    }

    @GetMapping("/dateAfter")
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> getBooksByDateAfter(
            @RequestParam("date") String date
    ) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive()
                .append(DateTimeFormatter.ofPattern("yyyy-MMM-dd")).toFormatter();
        try {
            LocalDate datetime = LocalDate.parse(date, formatter);
            var bookList = bookService.getBooksByDateAfter(datetime);
            return ResponseEntity.status(200).body(ApiResponse.success(bookList));
        } catch (DateTimeParseException e) {
            log.error("Error - DateTimeParseException", e);
        }
        return ResponseEntity.status(400).body(ApiResponse.success(null));
    }

    @GetMapping("/dateBefore")
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> getBooksByDateBefore(
            @RequestParam("date") String date
    ) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive()
                .append(DateTimeFormatter.ofPattern("yyyy-MMM-dd")).toFormatter();
        try {
            LocalDate datetime = LocalDate.parse(date, formatter);
            var bookList = bookService.getBooksByDateBefore(datetime);
            return ResponseEntity.status(200).body(ApiResponse.success(bookList));
        } catch (DateTimeParseException e) {
            log.error("Error - DateTimeParseException", e);
        }
        return ResponseEntity.status(400).body(ApiResponse.success(null));
    }

    @GetMapping("/dateBetween")
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> getBooksByDateBetween(
            @RequestParam("date1") String date1,
            @RequestParam("date2") String date2
    ) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive()
                .append(DateTimeFormatter.ofPattern("yyyy-MMM-dd")).toFormatter();
        try {
            LocalDate datetime1 = LocalDate.parse(date1, formatter);
            LocalDate datetime2 = LocalDate.parse(date2, formatter);
            var bookList = bookService.getBooksByDateBetween(datetime1, datetime2);
            return ResponseEntity.status(200).body(ApiResponse.success(bookList));
        } catch (DateTimeParseException e) {
            log.error("Error - DateTimeParseException", e);
        }
        return ResponseEntity.status(400).body(ApiResponse.success(null));
    }

    @GetMapping("/byPublisher")
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> updateBookDiscountByBookId(
            @RequestParam("publisher") String publisher
    ) {
        var bookList = bookService.getBooksByPublisher(publisher);
        return ResponseEntity.status(200).body(ApiResponse.success(bookList));
    }

    @PostMapping(
            path = "/upload-image",
            consumes = "multipart/form-data",
            produces = "application/json"
    )
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> uploadImage(
            @RequestParam Long id,
            @RequestParam(value = "image", required = false) MultipartFile imageFile
    ) {
        var book = bookService.getBookById(id);
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(convertMultiPartToFile(imageFile, book.getBookName()));
            String encodedString = Base64
                    .getEncoder()
                    .encodeToString(fileContent);
            bookService.uploadImage(encodedString, book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(200).body(ApiResponse.success(null));
    }

    private File convertMultiPartToFile(MultipartFile file, String name) throws IOException {
        File convFile = new File(name);
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    @GetMapping("/getImage")
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> getImage(
            @RequestParam Long id
    ) throws IOException {
        var book = bookService.getBookById(id);
        byte[] decodedBytes = Base64.getDecoder().decode(book.getBookImage());
        ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
        BufferedImage img = ImageIO.read(bis);
        String pathName = book.getBookName() + ".jpg";
        File outputfile = new File(pathName);
        ImageIO.write(img, "jpg", outputfile);
        return ResponseEntity.status(200).body(ApiResponse.success(outputfile));
    }

    @GetMapping("/getImage/all")
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> getImage() throws IOException {
        var bookImageDtoList = bookService.getAllBooksImage();
        return ResponseEntity.status(200).body(ApiResponse.success(bookImageDtoList));
    }

    @GetMapping("/sum-book-price-by-author-name")
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> getReportBookByAuthor(
            @RequestParam String authorName
    ) {
        var bookReportDtoList = bookService.getReportBookByAuthor(authorName);
        return ResponseEntity.status(200).body(ApiResponse.success(bookReportDtoList));
    }

    @GetMapping("/sum-book-prices")
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> getReportBooks() {
        var bookReportDtoList = bookService.getReportBooks();
        return ResponseEntity.status(200).body(ApiResponse.success(bookReportDtoList));
    }

    @GetMapping("/max-book-price-by-author-name")
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> getMaxReportBookByAuthor(
            @RequestParam String authorName
    ) {
        var bookReportDtoList = bookService.getMaxReportBookByAuthor(authorName);
        return ResponseEntity.status(200).body(ApiResponse.success(bookReportDtoList));
    }

    @GetMapping("/max-book-prices")
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> getMaxReportBooks() {
        var bookReportDtoList = bookService.getMaxReportBooks();
        return ResponseEntity.status(200).body(ApiResponse.success(bookReportDtoList));
    }

    @GetMapping("/min-book-price-by-author-name")
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> getMinReportBookByAuthor(
            @RequestParam String authorName
    ) {
        var bookReportDtoList = bookService.getMinReportBookByAuthor(authorName);
        return ResponseEntity.status(200).body(ApiResponse.success(bookReportDtoList));
    }

    @GetMapping("/min-book-prices")
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> getMinReportBooks() {
        var bookReportDtoList = bookService.getMinReportBooks();
        return ResponseEntity.status(200).body(ApiResponse.success(bookReportDtoList));
    }

    @GetMapping("/avg-book-price-by-author-name")
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> getAvgReportBookByAuthor(
            @RequestParam String authorName
    ) {
        var bookReportDtoList = bookService.getAvgReportBookByAuthor(authorName);
        return ResponseEntity.status(200).body(ApiResponse.success(bookReportDtoList));
    }

    @GetMapping("/avg-book-prices")
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> getAvgReportBooks() {
        var bookReportDtoList = bookService.getAvgReportBooks();
        return ResponseEntity.status(200).body(ApiResponse.success(bookReportDtoList));
    }
}
