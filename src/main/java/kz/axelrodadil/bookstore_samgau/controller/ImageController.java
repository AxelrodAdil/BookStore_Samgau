package kz.axelrodadil.bookstore_samgau.controller;

import kz.axelrodadil.bookstore_samgau.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@RestController
@RequestMapping("/home/adil/IdeaProjects/BookStore_Samgau")
@RequiredArgsConstructor
public class ImageController {

    @GetMapping
    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> getImage(
            @RequestParam String name
    ) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(name);
        return ResponseEntity.status(200).body(ApiResponse.success(inputStream));
    }
}
