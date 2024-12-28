package ru.vsu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.entity.Genre;
import ru.vsu.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    @Transactional
    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Transactional
    public Genre updateGenre(Long id, Genre genreDetails) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found with id " + id));

        genre.setName(genreDetails.getName());

        return genreRepository.save(genre);
    }

    @Transactional
    public void deleteGenre(Long id) {
        if (!genreRepository.existsById(id)) {
            throw new RuntimeException("Genre not found with id " + id);
        }
        genreRepository.deleteById(id);
    }

    public Optional<Genre> getGenreById(Long id) {
        return genreRepository.findById(id);
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
}