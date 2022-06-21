package kz.axelrodadil.bookstore_samgau.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @SequenceGenerator(name = "books_seq", sequenceName = "books_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_seq")
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "bookshelf")
    private String bookshelf;

    @Column(name = "book_price")
    private Double bookPrice;

    @Column(name = "book_published_year")
    private LocalDateTime bookPublishedYear;
}
