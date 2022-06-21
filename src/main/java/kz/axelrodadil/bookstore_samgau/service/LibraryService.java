package kz.axelrodadil.bookstore_samgau.service;

import kz.axelrodadil.bookstore_samgau.dto.LibraryDto;
import kz.axelrodadil.bookstore_samgau.exception.InternalServerErrorException;
import kz.axelrodadil.bookstore_samgau.model.Library;
import kz.axelrodadil.bookstore_samgau.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibraryService {

    private final LibraryRepository libraryRepository;

    public void bindBookToStudent(Long studentId, Long bookId) {
        Library library = new Library();
        library.setBookId(bookId);
        library.setStudentId(studentId);
        libraryRepository.save(library);
    }

    public List<LibraryDto> getAllLibraryInfo() {
        List<Library> libraryList = libraryRepository.findAll();
        List<LibraryDto> libraryDtoList = new ArrayList<>();
        for (Library library : libraryList) {
            libraryDtoList.add(convertToLibraryDto(library));
        }
        return libraryDtoList;
    }

    public LibraryDto getLibraryById(Long libraryID) {
        try {
            Library library = libraryRepository.getLibraryByLibraryId(libraryID);
            return convertToLibraryDto(library);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    private LibraryDto convertToLibraryDto(Library library) {
        LibraryDto libraryDto = new LibraryDto();
        libraryDto.setStudentId(library.getStudentId());
        libraryDto.setBookId(library.getBookId());
        return libraryDto;
    }
}
