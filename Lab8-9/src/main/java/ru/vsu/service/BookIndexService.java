package ru.vsu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.entity.BookIndex;
import ru.vsu.repository.BookIndexRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookIndexService {

    private final BookIndexRepository bookIndexRepository;

    @Transactional
    public BookIndex saveBookIndex(BookIndex bookIndex) {
        return bookIndexRepository.save(bookIndex);
    }

    @Transactional
    public BookIndex updateBookIndex(Long id, BookIndex bookIndexDetails) {
        BookIndex bookIndex = bookIndexRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BookIndex not found with id " + id));

        bookIndex.setBook(bookIndexDetails.getBook());
        bookIndex.setKeyword(bookIndexDetails.getKeyword());

        return bookIndexRepository.save(bookIndex);
    }

    @Transactional
    public void deleteBookIndex(Long id) {
        if (!bookIndexRepository.existsById(id)) {
            throw new RuntimeException("BookIndex not found with id " + id);
        }
        bookIndexRepository.deleteById(id);
    }

    public Optional<BookIndex> getBookIndexById(Long id) {
        return bookIndexRepository.findById(id);
    }

    public List<BookIndex> getAllBookIndices() {
        return bookIndexRepository.findAll();
    }
}