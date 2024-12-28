package ru.vsu.entity;

import javax.persistence.*;
import java.util.List;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Table(name = "library")
@Accessors(chain = true)
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String address;

    @OneToMany(mappedBy = "library")
    private List<BookIndex> bookIndices;
}
