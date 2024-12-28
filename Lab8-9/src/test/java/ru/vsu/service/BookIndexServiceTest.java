package ru.vsu.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.vsu.entity.BookIndex;
import ru.vsu.repository.BookIndexRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookIndexServiceTest {

    @InjectMocks
    private BookIndexService bookIndexService;

    @Mock
    private BookIndexRepository bookIndexRepository;

    private BookIndex bookIndex;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        bookIndex = new BookIndex().setId(1L).setKeyword("Keyword");
    }

    @Test
    public void testSaveBookIndex() {
        when(bookIndexRepository.save(any(BookIndex.class))).thenReturn(bookIndex);

        BookIndex savedBookIndex = bookIndexService.saveBookIndex(bookIndex);

        assertNotNull(savedBookIndex);
        assertEquals("Keyword", savedBookIndex.getKeyword());
        verify(bookIndexRepository, times(1)).save(bookIndex);
    }

    @Test
    public void testUpdateBookIndex() {
        when(bookIndexRepository.findById(1L)).thenReturn(Optional.of(bookIndex));
        when(bookIndexRepository.save(any(BookIndex.class))).thenReturn(bookIndex);

        BookIndex updatedBookIndex = new BookIndex().setKeyword("Updated Keyword");
        BookIndex result = bookIndexService.updateBookIndex(1L, updatedBookIndex);

        assertNotNull(result);
        assertEquals("Updated Keyword", result.getKeyword());
        verify(bookIndexRepository, times(1)).findById(1L);
        verify(bookIndexRepository, times(1)).save(any(BookIndex.class));
    }

    @Test
    public void testUpdateBookIndexNotFound() {
        when(bookIndexRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bookIndexService.updateBookIndex(1L, bookIndex);
        });

        assertEquals("BookIndex not found with id 1", exception.getMessage());
        verify(bookIndexRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteBookIndex() {
        when(bookIndexRepository.existsById(1L)).thenReturn(true);

        bookIndexService.deleteBookIndex(1L);

        verify(bookIndexRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteBookIndexNotFound() {
        when(bookIndexRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bookIndexService.deleteBookIndex(1L);
        });

        assertEquals("BookIndex not found with id 1", exception.getMessage());
        verify(bookIndexRepository, times(1)).existsById(1L);
    }

    @Test
    public void testGetBookIndexById() {
        when(bookIndexRepository.findById(1L)).thenReturn(Optional.of(bookIndex));

        Optional<BookIndex> result = bookIndexService.getBookIndexById(1L);

        assertTrue(result.isPresent());
        assertEquals("Keyword", result.get().getKeyword());
        verify(bookIndexRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllBookIndices() {
        when(bookIndexRepository.findAll()).thenReturn(Arrays.asList(bookIndex));

        List<BookIndex> bookIndices = bookIndexService.getAllBookIndices();

        assertNotNull(bookIndices);
        assertEquals(1, bookIndices.size());
        assertEquals("Keyword", bookIndices.get(0).getKeyword());
        verify(bookIndexRepository, times(1)).findAll();
    }
}

