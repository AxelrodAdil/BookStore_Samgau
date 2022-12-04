package kz.axelrodadil.bookstore_samgau.service;

import kz.axelrodadil.bookstore_samgau.dto.BookDto;
import kz.axelrodadil.bookstore_samgau.dto.BookImageDto;
import kz.axelrodadil.bookstore_samgau.dto.BookReportDto;
import kz.axelrodadil.bookstore_samgau.model.Author;
import kz.axelrodadil.bookstore_samgau.model.Book;
import kz.axelrodadil.bookstore_samgau.repository.AuthorRepository;
import kz.axelrodadil.bookstore_samgau.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public List<Book> getAllBookByBookshelf(String bookshelf) {
        return bookRepository.getAllByBookshelfOrderByBookId(bookshelf);
    }

    public List<BookDto> getAllBookPriceByBookshelf(String bookshelf) {
        var bookList = bookRepository.getAllByBookshelfOrderByBookId(bookshelf);
        List<BookDto> bookDtoList = new ArrayList<>();
        bookList.forEach(book -> {
            var authorOptional = authorRepository.findById(book.getAuthorId());
            if (authorOptional.isPresent()) {
                var bookDto = getBookDto(book, authorOptional.get());
                bookDtoList.add(bookDto);
            }
        });
        return bookDtoList;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<BookImageDto> getAllBooksImage() throws IOException {
        var bookList = bookRepository.findAll();
        List<BookImageDto> bookImageDtoList = new ArrayList<>();
        for (var book : bookList) {
            var authorOptional = authorRepository.findById(book.getAuthorId());
            if (authorOptional.isPresent()) {
                var bookImageDto = new BookImageDto();
                bookImageDto.setBookDto(getBookDto(book, authorOptional.get()));
                bookImageDtoList.add(bookImageDto);

                byte[] decodedBytes = Base64.getDecoder().decode(book.getBookImage());
                ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
                BufferedImage img = ImageIO.read(bis);
                String pathName = book.getBookName() + ".jpg";
                File outputfile = new File(pathName);
                ImageIO.write(img, "jpg", outputfile);
                bookImageDto.setFile(outputfile);
            }
        }
        return bookImageDtoList;
    }

    public void updateBookCountByBookId(Long bookId, Long count) {
        var bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            var book = bookOptional.get();
            book.setBookCount(count);
            bookRepository.saveAndFlush(book);
        }
    }

    public void updateAllBookDiscountByBookshelf(String bookshelf, Long discount) {
        var bookList = bookRepository.getAllByBookshelfOrderByBookId(bookshelf);
        for (var book : bookList) {
            book.setBookDiscount(discount);
            bookRepository.saveAndFlush(book);
        }
    }

    public void updateBookDiscountByBookId(Long bookId, Long discount) {
        var bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            var book = bookOptional.get();
            book.setBookDiscount(discount);
            bookRepository.saveAndFlush(book);
        }
    }

    public List<Book> getBooksLikeAuthorName(String name) {
        var authors = authorRepository.findAuthorsByAuthorNameStartingWith(name);
        var books = bookRepository.findAll();
        var ans = new ArrayList<Book>();
        books.forEach(book -> {
            authors.stream().filter(author -> Objects.equals(book.getAuthorId(), author.getAuthorId()))
                    .map(author -> book).forEachOrdered(ans::add);
        });
        return ans;
    }

    public List<Book> getBooksByDateAfter(LocalDate localDate) {
        var ourDate = localDate.atStartOfDay();
        return bookRepository.findBooksByBookPublishedYearAfter(ourDate);
    }

    public List<Book> getBooksByDateBefore(LocalDate localDate) {
        var ourDate = localDate.atStartOfDay();
        return bookRepository.findBooksByBookPublishedYearBefore(ourDate);
    }

    public List<Book> getBooksByDateBetween(LocalDate localDate1, LocalDate localDate2) {
        var ourDate1 = localDate1.atStartOfDay();
        var ourDate2 = localDate2.atStartOfDay();
        return bookRepository.findBooksByBookPublishedYearBetween(ourDate1, ourDate2);
    }

    public List<Book> getBooksByPublisher(String publisher) {
        return bookRepository.findBooksByBookPublisher(publisher);
    }

    public void uploadImage(String encodedString, Book book) {
        book.setBookImage(encodedString);
        bookRepository.saveAndFlush(book);
    }

    public Book getBookById(Long id) {
        return bookRepository.getBookByBookId(id);
    }

    private BookDto getBookDto(Book book, Author author) {
        var bookDto = new BookDto();
        bookDto.setBookId(book.getBookId());
        bookDto.setAuthorFullName(author.getAuthorName() + " " + author.getAuthorSurname());
        bookDto.setBookName(book.getBookName());
        bookDto.setBookshelf(book.getBookshelf());
        bookDto.setBookPublishedYear(book.getBookPublishedYear());
        if (book.getBookCount() >= 75) {
            var bookPrice = book.getBookPrice() - (book.getBookPrice() * 0.5);
            bookDto.setBookPrice(bookPrice);
        } else {
            if (book.getBookDiscount() != 0) {
                var bookPrice = book.getBookPrice() - (book.getBookPrice() * ((double) book.getBookDiscount() / (double) 100));
                bookDto.setBookPrice(bookPrice);
            } else {
                bookDto.setBookPrice(book.getBookPrice());
            }
        }
        return bookDto;
    }

    private BookReportDto getBookReportDto(Book book, Author author) {
        var bookReportDto = new BookReportDto();
        bookReportDto.setAuthorFullName(author.getAuthorName() + " " + author.getAuthorSurname());
        if (book.getBookCount() >= 75) {
            var bookPrice = book.getBookPrice() - (book.getBookPrice() * 0.5);
            bookReportDto.setBookAveragePrice(bookPrice);
        } else {
            if (book.getBookDiscount() != 0) {
                var bookPrice = book.getBookPrice() - (book.getBookPrice() * ((double) book.getBookDiscount() / (double) 100));
                bookReportDto.setBookAveragePrice(bookPrice);
            } else {
                bookReportDto.setBookAveragePrice(book.getBookPrice());
            }
        }
        log.info("Info about current book: {} - {} - {}", book.getBookName(), bookReportDto.getBookAveragePrice(), book.getAuthorId());
        return bookReportDto;
    }

    public List<BookReportDto> getReportBookByAuthor(String authorName) {
        var authorOptional = authorRepository.getAuthorByAuthorName(authorName);
        if (authorOptional.isPresent()) {
            var bookList = bookRepository.findBooksByAuthorId(authorOptional.get().getAuthorId());
            HashMap<String, Double> sumOfBookPrices = new HashMap<>();
            HashMap<String, BookReportDto> bookReportDtoHashMap = new HashMap<>();
            bookList.stream().map(book -> getBookReportDto(book, authorOptional.get())).forEachOrdered(bookReportDto -> {
                sumOfBookPrices.merge(bookReportDto.getAuthorFullName(), bookReportDto.getBookAveragePrice(), Double::sum);
                bookReportDto.setBookAveragePrice(sumOfBookPrices.get(bookReportDto.getAuthorFullName()));
                if (bookReportDtoHashMap.get(bookReportDto.getAuthorFullName()) != null) {
                    bookReportDtoHashMap.remove(bookReportDto.getAuthorFullName());
                    bookReportDtoHashMap.put(bookReportDto.getAuthorFullName(), bookReportDto);
                } else {
                    bookReportDtoHashMap.put(bookReportDto.getAuthorFullName(), bookReportDto);
                }
            });
            return new ArrayList<>(bookReportDtoHashMap.values());
        }
        return null;
    }

    public List<BookReportDto> getReportBooks() {
        var bookList = bookRepository.findAll();
        HashMap<String, Double> sumOfBookPrices = new HashMap<>();
        HashMap<String, BookReportDto> bookReportDtoHashMap = new HashMap<>();
        bookList.forEach(book -> {
            var authorOptional = authorRepository.findById(book.getAuthorId());
            if (authorOptional.isPresent()) {
                var bookReportDto = getBookReportDto(book, authorOptional.get());
                sumOfBookPrices.merge(bookReportDto.getAuthorFullName(), bookReportDto.getBookAveragePrice(), Double::sum);
                bookReportDto.setBookAveragePrice(sumOfBookPrices.get(bookReportDto.getAuthorFullName()));
                if (bookReportDtoHashMap.get(bookReportDto.getAuthorFullName()) != null) {
                    bookReportDtoHashMap.remove(bookReportDto.getAuthorFullName());
                    bookReportDtoHashMap.put(bookReportDto.getAuthorFullName(), bookReportDto);
                } else {
                    bookReportDtoHashMap.put(bookReportDto.getAuthorFullName(), bookReportDto);
                }
            }
        });
        return new ArrayList<>(bookReportDtoHashMap.values());
    }

    public List<BookReportDto> getMaxReportBookByAuthor(String authorName) {
        var authorOptional = authorRepository.getAuthorByAuthorName(authorName);
        if (authorOptional.isPresent()) {
            var bookList = bookRepository.findBooksByAuthorId(authorOptional.get().getAuthorId());
            HashMap<String, Double> maxOfBookPrices = new HashMap<>();
            HashMap<String, BookReportDto> bookReportDtoHashMap = new HashMap<>();
            bookList.stream().map(book -> getBookReportDto(book, authorOptional.get())).forEachOrdered(bookReportDto -> {
                maxOfBookPrices.merge(bookReportDto.getAuthorFullName(), bookReportDto.getBookAveragePrice(), Double::max);
                bookReportDto.setBookAveragePrice(maxOfBookPrices.get(bookReportDto.getAuthorFullName()));
                if (bookReportDtoHashMap.get(bookReportDto.getAuthorFullName()) != null) {
                    bookReportDtoHashMap.remove(bookReportDto.getAuthorFullName());
                    bookReportDtoHashMap.put(bookReportDto.getAuthorFullName(), bookReportDto);
                } else {
                    bookReportDtoHashMap.put(bookReportDto.getAuthorFullName(), bookReportDto);
                }
            });
            return new ArrayList<>(bookReportDtoHashMap.values());
        }
        return null;
    }

    public List<BookReportDto> getMaxReportBooks() {
        var bookList = bookRepository.findAll();
        HashMap<String, Double> maxOfBookPrices = new HashMap<>();
        HashMap<String, BookReportDto> bookReportDtoHashMap = new HashMap<>();
        bookList.forEach(book -> {
            var authorOptional = authorRepository.findById(book.getAuthorId());
            if (authorOptional.isPresent()) {
                var bookReportDto = getBookReportDto(book, authorOptional.get());
                maxOfBookPrices.merge(bookReportDto.getAuthorFullName(), bookReportDto.getBookAveragePrice(), Double::max);
                bookReportDto.setBookAveragePrice(maxOfBookPrices.get(bookReportDto.getAuthorFullName()));
                if (bookReportDtoHashMap.get(bookReportDto.getAuthorFullName()) != null) {
                    bookReportDtoHashMap.remove(bookReportDto.getAuthorFullName());
                    bookReportDtoHashMap.put(bookReportDto.getAuthorFullName(), bookReportDto);
                } else {
                    bookReportDtoHashMap.put(bookReportDto.getAuthorFullName(), bookReportDto);
                }
            }
        });
        return new ArrayList<>(bookReportDtoHashMap.values());
    }

    public List<BookReportDto> getMinReportBookByAuthor(String authorName) {
        var authorOptional = authorRepository.getAuthorByAuthorName(authorName);
        if (authorOptional.isPresent()) {
            var bookList = bookRepository.findBooksByAuthorId(authorOptional.get().getAuthorId());
            HashMap<String, Double> minOfBookPrices = new HashMap<>();
            HashMap<String, BookReportDto> bookReportDtoHashMap = new HashMap<>();
            bookList.stream().map(book -> getBookReportDto(book, authorOptional.get())).forEachOrdered(bookReportDto -> {
                minOfBookPrices.merge(bookReportDto.getAuthorFullName(), bookReportDto.getBookAveragePrice(), Double::min);
                bookReportDto.setBookAveragePrice(minOfBookPrices.get(bookReportDto.getAuthorFullName()));
                if (bookReportDtoHashMap.get(bookReportDto.getAuthorFullName()) != null) {
                    bookReportDtoHashMap.remove(bookReportDto.getAuthorFullName());
                    bookReportDtoHashMap.put(bookReportDto.getAuthorFullName(), bookReportDto);
                } else {
                    bookReportDtoHashMap.put(bookReportDto.getAuthorFullName(), bookReportDto);
                }
            });
            return new ArrayList<>(bookReportDtoHashMap.values());
        }
        return null;
    }

    public List<BookReportDto> getMinReportBooks() {
        var bookList = bookRepository.findAll();
        HashMap<String, Double> minOfBookPrices = new HashMap<>();
        HashMap<String, BookReportDto> bookReportDtoHashMap = new HashMap<>();
        bookList.forEach(book -> {
            var authorOptional = authorRepository.findById(book.getAuthorId());
            if (authorOptional.isPresent()) {
                var bookReportDto = getBookReportDto(book, authorOptional.get());
                minOfBookPrices.merge(bookReportDto.getAuthorFullName(), bookReportDto.getBookAveragePrice(), Double::min);
                bookReportDto.setBookAveragePrice(minOfBookPrices.get(bookReportDto.getAuthorFullName()));
                if (bookReportDtoHashMap.get(bookReportDto.getAuthorFullName()) != null) {
                    bookReportDtoHashMap.remove(bookReportDto.getAuthorFullName());
                    bookReportDtoHashMap.put(bookReportDto.getAuthorFullName(), bookReportDto);
                } else {
                    bookReportDtoHashMap.put(bookReportDto.getAuthorFullName(), bookReportDto);
                }
            }
        });
        return new ArrayList<>(bookReportDtoHashMap.values());
    }

    public List<BookReportDto> getAvgReportBookByAuthor(String authorName) {
        var authorOptional = authorRepository.getAuthorByAuthorName(authorName);
        if (authorOptional.isPresent()) {
            var bookList = bookRepository.findBooksByAuthorId(authorOptional.get().getAuthorId());
            HashMap<String, Double> sumOfBookPrices = new HashMap<>();
            HashMap<String, BookReportDto> bookReportDtoHashMap = new HashMap<>();
            HashMap<String, Long> countHashMap = new HashMap<>();
            bookList.stream().map(book -> getBookReportDto(book, authorOptional.get())).forEachOrdered(bookReportDto -> {
                sumOfBookPrices.merge(bookReportDto.getAuthorFullName(), bookReportDto.getBookAveragePrice(), Double::sum);
                countHashMap.put(bookReportDto.getAuthorFullName(), countHashMap.getOrDefault(bookReportDto.getAuthorFullName(), 0L) + 1L);
                bookReportDto.setBookAveragePrice(sumOfBookPrices.get(bookReportDto.getAuthorFullName()) / countHashMap.get(bookReportDto.getAuthorFullName()));
                if (bookReportDtoHashMap.get(bookReportDto.getAuthorFullName()) != null) {
                    bookReportDtoHashMap.remove(bookReportDto.getAuthorFullName());
                    bookReportDtoHashMap.put(bookReportDto.getAuthorFullName(), bookReportDto);
                } else {
                    bookReportDtoHashMap.put(bookReportDto.getAuthorFullName(), bookReportDto);
                }
            });
            return new ArrayList<>(bookReportDtoHashMap.values());
        }
        return null;
    }

    public List<BookReportDto> getAvgReportBooks() {
        var bookList = bookRepository.findAll();
        HashMap<String, Double> sumOfBookPrices = new HashMap<>();
        HashMap<String, BookReportDto> bookReportDtoHashMap = new HashMap<>();
        HashMap<String, Long> countHashMap = new HashMap<>();
        bookList.forEach(book -> {
            var authorOptional = authorRepository.findById(book.getAuthorId());
            if (authorOptional.isPresent()) {
                var bookReportDto = getBookReportDto(book, authorOptional.get());
                sumOfBookPrices.merge(bookReportDto.getAuthorFullName(), bookReportDto.getBookAveragePrice(), Double::sum);
                countHashMap.put(bookReportDto.getAuthorFullName(), countHashMap.getOrDefault(bookReportDto.getAuthorFullName(), 0L) + 1L);
                bookReportDto.setBookAveragePrice(sumOfBookPrices.get(bookReportDto.getAuthorFullName()) / countHashMap.get(bookReportDto.getAuthorFullName()));
                if (bookReportDtoHashMap.get(bookReportDto.getAuthorFullName()) != null) {
                    bookReportDtoHashMap.remove(bookReportDto.getAuthorFullName());
                    bookReportDtoHashMap.put(bookReportDto.getAuthorFullName(), bookReportDto);
                } else {
                    bookReportDtoHashMap.put(bookReportDto.getAuthorFullName(), bookReportDto);
                }
            }
        });
        return new ArrayList<>(bookReportDtoHashMap.values());
    }
}
