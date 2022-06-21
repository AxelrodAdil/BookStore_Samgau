package kz.axelrodadil.bookstore_samgau.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "libraries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Library {

    @Id
    @SequenceGenerator(name = "libraries_seq", sequenceName = "libraries_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "libraries_seq")
    @Column(name = "library_id")
    private Long libraryId;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "book_id")
    private Long bookId;
}
