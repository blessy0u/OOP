package ru.vsu.entity;

import javax.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Table(name = "book_index")
@Accessors(chain = true)
public class BookIndex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(length = 100, nullable = false)
    private String keyword;
}
