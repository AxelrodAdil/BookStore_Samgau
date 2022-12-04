package kz.axelrodadil.bookstore_samgau.repository;

import kz.axelrodadil.bookstore_samgau.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> getAllByBookshelfOrderByBookId(String bookshelf);

    @Query(
            nativeQuery = true,
            value = "select b.book_price from books b where b.book_id = ?"
    )
    Double getBookPrice(Long bookId);

    Book getBookByBookId(Long id);

    @Query(
            nativeQuery = true,
            value = "select SUM(b.book_price) from books b where b.author_id = ?"
    )
    Double getSumBookPrice(Long authorId);

    @Query(
            nativeQuery = true,
            value = "select AVG(b.book_price) from books b where b.author_id = ?"
    )
    Double getAverageBookPrice(Long authorId);

    List<Book> findBooksByAuthorId(Long authorId);

    List<Book> findBooksByBookPublishedYearAfter(LocalDateTime ourTime);

    List<Book> findBooksByBookPublishedYearBefore(LocalDateTime ourTime);

    List<Book> findBooksByBookPublishedYearBetween(LocalDateTime firstTime, LocalDateTime secondTime);

    List<Book> findBooksByBookPublisher(String publisher);

    List<Book> findBooksByBookPublisherStartingWith(String publisher);
}
