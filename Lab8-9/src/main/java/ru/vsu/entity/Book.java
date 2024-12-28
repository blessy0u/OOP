package ru.vsu.entity;

import javax.persistence.*;
import java.util.*;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Table(name = "books")
@Accessors(chain = true)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @Temporal(TemporalType.DATE)
    private Date publicationDate;
}
