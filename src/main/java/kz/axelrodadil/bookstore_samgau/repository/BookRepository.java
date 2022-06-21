package kz.axelrodadil.bookstore_samgau.repository;

import kz.axelrodadil.bookstore_samgau.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
