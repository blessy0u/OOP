package ru.vsu.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.vsu.entity.Book;
import ru.vsu.repository.BookRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    private Book book;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        book = new Book().setId(1L).setTitle("Book Title");
    }

    @Test
    public void testSaveBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book savedBook = bookService.saveBook(book);

        assertNotNull(savedBook);
        assertEquals("Book Title", savedBook.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testUpdateBook() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book updatedBook = new Book().setTitle("Updated Book Title");
        Book result = bookService.updateBook(1L, updatedBook);

        assertNotNull(result);
        assertEquals("Updated Book Title", result.getTitle());
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void testUpdateBookNotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bookService.updateBook(1L, book);
        });

        assertEquals("Book not found with id 1", exception.getMessage());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteBook() {
        when(bookRepository.existsById(1L)).thenReturn(true);

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteBookNotFound() {
        when(bookRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bookService.deleteBook(1L);
        });

        assertEquals("Book not found with id 1", exception.getMessage());
        verify(bookRepository, times(1)).existsById(1L);
    }

    @Test
    public void testGetBookById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> result = bookService.getBookById(1L);

        assertTrue(result.isPresent());
        assertEquals("Book Title", result.get().getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book));

        List<Book> books = bookService.getAllBooks();

        assertNotNull(books);
        assertEquals(1, books.size());
        assertEquals("Book Title", books.get(0).getTitle());
        verify(bookRepository, times(1)).findAll();
    }
}
