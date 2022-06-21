package kz.axelrodadil.bookstore_samgau.service;

import kz.axelrodadil.bookstore_samgau.model.Student;
import kz.axelrodadil.bookstore_samgau.repository.LibraryRepository;
import kz.axelrodadil.bookstore_samgau.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final LibraryRepository libraryRepository;

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    public void updateStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }
}
