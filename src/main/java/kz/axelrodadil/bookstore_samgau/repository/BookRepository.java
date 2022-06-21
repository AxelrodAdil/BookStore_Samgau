package kz.axelrodadil.bookstore_samgau.repository;

import kz.axelrodadil.bookstore_samgau.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> getAllByBookshelfOrderByBookId(String bookshelf);

    @Query(
            nativeQuery = true,
            value = "select b.book_price from books b where b.book_id = ?"
    )
    Double getBookPrice(Long bookId);
}
