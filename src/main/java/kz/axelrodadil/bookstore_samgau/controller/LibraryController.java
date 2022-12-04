package kz.axelrodadil.bookstore_samgau.controller;

import kz.axelrodadil.bookstore_samgau.dto.ApiResponse;
import kz.axelrodadil.bookstore_samgau.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookStore/library")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @GetMapping
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<List<?>>> getAllLibraryInfo() {
        var libraryDtoList = libraryService.getAllLibraryInfo();
        return ResponseEntity.status(200).body(ApiResponse.success(libraryDtoList));
    }

    @GetMapping("{library_id}")
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> getLibraryById(
            @PathVariable("library_id") Long libraryId
    ) {
        var libraryDto = libraryService.getLibraryById(libraryId);
        return ResponseEntity.status(200).body(ApiResponse.success(libraryDto));
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('scheduler:read')")
    public ResponseEntity<ApiResponse<?>> getLibraryById(
            @RequestParam("student_id") Long studentId,
            @RequestParam("book_id") Long bookId
    ) {
        libraryService.bindBookToStudent(studentId, bookId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
