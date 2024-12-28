package ru.vsu.entity;

import javax.persistence.*;
import java.util.List;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Table(name = "genres")
@Accessors(chain = true)
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @OneToMany(mappedBy = "genre")
    private List<Book> books;
}
