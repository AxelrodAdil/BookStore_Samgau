package kz.axelrodadil.bookstore_samgau.repository;

import kz.axelrodadil.bookstore_samgau.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findById(Long authorId);

    List<Author> findAuthorsByAuthorNameStartingWith(String name);

    Optional<Author> getAuthorByAuthorName(String authorName);
}
