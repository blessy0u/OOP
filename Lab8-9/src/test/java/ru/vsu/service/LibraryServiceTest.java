package ru.vsu.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.vsu.entity.Library;
import ru.vsu.repository.LibraryRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LibraryServiceTest {

    @InjectMocks
    private LibraryService libraryService;

    @Mock
    private LibraryRepository libraryRepository;

    private Library library;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        library = new Library().setId(1L).setName("Library Name").setAddress("Library Address");
    }

    @Test
    public void testSaveLibrary() {
        when(libraryRepository.save(any(Library.class))).thenReturn(library);

        Library savedLibrary = libraryService.saveLibrary(library);

        assertNotNull(savedLibrary);
        assertEquals("Library Name", savedLibrary.getName());
        verify(libraryRepository, times(1)).save(library);
    }

    @Test
    public void testUpdateLibrary() {
        when(libraryRepository.findById(1L)).thenReturn(Optional.of(library));
        when(libraryRepository.save(any(Library.class))).thenReturn(library);

        Library updatedLibrary = new Library().setName("Updated Library Name").setAddress("Updated Address");
        Library result = libraryService.updateLibrary(1L, updatedLibrary);

        assertNotNull(result);
        assertEquals("Updated Library Name", result.getName());
        verify(libraryRepository, times(1)).findById(1L);
        verify(libraryRepository, times(1)).save(any(Library.class));
    }

    @Test
    public void testUpdateLibraryNotFound() {
        when(libraryRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            libraryService.updateLibrary(1L, library);
        });

        assertEquals("Library not found with id 1", exception.getMessage());
        verify(libraryRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteLibrary() {
        when(libraryRepository.existsById(1L)).thenReturn(true);

        libraryService.deleteLibrary(1L);

        verify(libraryRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteLibraryNotFound() {
        when(libraryRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            libraryService.deleteLibrary(1L);
        });

        assertEquals("Library not found with id 1", exception.getMessage());
        verify(libraryRepository, times(1)).existsById(1L);
    }

    @Test
    public void testGetLibraryById() {
        when(libraryRepository.findById(1L)).thenReturn(Optional.of(library));

        Optional<Library> result = libraryService.getLibraryById(1L);

        assertTrue(result.isPresent());
        assertEquals("Library Name", result.get().getName());
        verify(libraryRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllLibraries() {
        when(libraryRepository.findAll()).thenReturn(Arrays.asList(library));

        List<Library> libraries = libraryService.getAllLibraries();

        assertNotNull(libraries);
        assertEquals(1, libraries.size());
        assertEquals("Library Name", libraries.get(0).getName());
        verify(libraryRepository, times(1)).findAll();
    }
}