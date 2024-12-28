package ru.vsu.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.vsu.entity.Genre;
import ru.vsu.repository.GenreRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GenreServiceTest {

    @InjectMocks
    private GenreService genreService;

    @Mock
    private GenreRepository genreRepository;

    private Genre genre;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        genre = new Genre().setId(1L).setName("Genre Name");
    }

    @Test
    public void testSaveGenre() {
        when(genreRepository.save(any(Genre.class))).thenReturn(genre);

        Genre savedGenre = genreService.saveGenre(genre);

        assertNotNull(savedGenre);
        assertEquals("Genre Name", savedGenre.getName());
        verify(genreRepository, times(1)).save(genre);
    }

    @Test
    public void testUpdateGenre() {
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
        when(genreRepository.save(any(Genre.class))).thenReturn(genre);

        Genre updatedGenre = new Genre().setName("Updated Genre Name");
        Genre result = genreService.updateGenre(1L, updatedGenre);

        assertNotNull(result);
        assertEquals("Updated Genre Name", result.getName());
        verify(genreRepository, times(1)).findById(1L);
        verify(genreRepository, times(1)).save(any(Genre.class));
    }

    @Test
    public void testUpdateGenreNotFound() {
        when(genreRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            genreService.updateGenre(1L, genre);
        });

        assertEquals("Genre not found with id 1", exception.getMessage());
        verify(genreRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteGenre() {
        when(genreRepository.existsById(1L)).thenReturn(true);

        genreService.deleteGenre(1L);

        verify(genreRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteGenreNotFound() {
        when(genreRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            genreService.deleteGenre(1L);
        });

        assertEquals("Genre not found with id 1", exception.getMessage());
        verify(genreRepository, times(1)).existsById(1L);
    }

    @Test
    public void testGetGenreById() {
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));

        Optional<Genre> result = genreService.getGenreById(1L);

        assertTrue(result.isPresent());
        assertEquals("Genre Name", result.get().getName());
        verify(genreRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllGenres() {
        when(genreRepository.findAll()).thenReturn(Arrays.asList(genre));

        List<Genre> genres = genreService.getAllGenres();

        assertNotNull(genres);
        assertEquals(1, genres.size());
        assertEquals("Genre Name", genres.get(0).getName());
        verify(genreRepository, times(1)).findAll();
    }
}
