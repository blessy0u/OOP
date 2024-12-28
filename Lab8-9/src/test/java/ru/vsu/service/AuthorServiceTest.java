package ru.vsu.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.vsu.entity.Author;
import ru.vsu.repository.AuthorRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthorServiceTest {

    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorRepository authorRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        author = new Author().setId(1L).setName("Author Name");
    }

    private Author author;

    @Test
    public void testSaveAuthor() {
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        Author savedAuthor = authorService.saveAuthor(author);

        assertNotNull(savedAuthor);
        assertEquals("Author Name", savedAuthor.getName());
        verify(authorRepository, times(1)).save(author);
    }

    @Test
    public void testUpdateAuthor() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        Author updatedAuthor = new Author().setName("Updated Author Name");
        Author result = authorService.updateAuthor(1L, updatedAuthor);

        assertNotNull(result);
        assertEquals("Updated Author Name", result.getName());
        verify(authorRepository, times(1)).findById(1L);
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    public void testUpdateAuthorNotFound() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authorService.updateAuthor(1L, author);
        });

        assertEquals("Author not found with id 1", exception.getMessage());
        verify(authorRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteAuthor() {
        when(authorRepository.existsById(1L)).thenReturn(true);

        authorService.deleteAuthor(1L);

        verify(authorRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteAuthorNotFound() {
        when(authorRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authorService.deleteAuthor(1L);
        });

        assertEquals("Author not found with id 1", exception.getMessage());
        verify(authorRepository, times(1)).existsById(1L);
    }

    @Test
    public void testGetAuthorById() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        Optional<Author> result = authorService.getAuthorById(1L);

        assertTrue(result.isPresent());
        assertEquals("Author Name", result.get().getName());
        verify(authorRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllAuthors() {
        when(authorRepository.findAll()).thenReturn(Arrays.asList(author));

        List<Author> authors = authorService.getAllAuthors();

        assertNotNull(authors);
        assertEquals(1, authors.size());
        assertEquals("Author Name", authors.get(0).getName());
        verify(authorRepository, times(1)).findAll();
    }
}
