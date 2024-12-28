package ru.vsu.repository;

import ru.vsu.entity.BookIndex;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookIndexRepository extends JpaRepository<BookIndex, Long> {
}
