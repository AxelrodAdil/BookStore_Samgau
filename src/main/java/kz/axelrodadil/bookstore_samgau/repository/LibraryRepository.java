package kz.axelrodadil.bookstore_samgau.repository;

import kz.axelrodadil.bookstore_samgau.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

    Library getLibraryByLibraryId(Long libraryId);
}
