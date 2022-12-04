package kz.axelrodadil.bookstore_samgau.controller;

import kz.axelrodadil.bookstore_samgau.dto.ApiResponse;
import kz.axelrodadil.bookstore_samgau.model.Student;
import kz.axelrodadil.bookstore_samgau.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookStore/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
//    @PreAuthorize("hasAuthority('scheduler:read')")
    public ResponseEntity<ApiResponse<List<?>>> getAllStudent() {
        var studentList = studentService.getAllStudent();
        return ResponseEntity.status(200).body(ApiResponse.success(studentList));
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> createStudent(@RequestBody Student student) {
        studentService.createStudent(student);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @PutMapping
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @DeleteMapping("{student_id}")
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> deleteStudent(
            @PathVariable("student_id") Long studentId
    ) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping("{student_id}")
//    @PreAuthorize("hasAuthority('scheduler:write')")
    public ResponseEntity<ApiResponse<?>> calculatePrice(
            @PathVariable("student_id") Long studentId
    ) {
        Double calculatedPrice = studentService.calculatePrice(studentId);
        return ResponseEntity.status(200).body(ApiResponse.success(calculatedPrice));
    }
}
