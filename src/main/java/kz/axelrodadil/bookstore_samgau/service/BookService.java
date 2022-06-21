package kz.axelrodadil.bookstore_samgau.service;

import kz.axelrodadil.bookstore_samgau.model.Book;
import kz.axelrodadil.bookstore_samgau.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBookByBookshelf(String bookshelf) {
        return bookRepository.getAllByBookshelfOrderByBookId(bookshelf);
    }
}
