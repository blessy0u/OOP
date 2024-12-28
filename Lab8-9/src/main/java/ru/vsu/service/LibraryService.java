package ru.vsu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.entity.Library;
import ru.vsu.repository.LibraryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final LibraryRepository libraryRepository;

    @Transactional
    public Library saveLibrary(Library library) {
        return libraryRepository.save(library);
    }

    @Transactional
    public Library updateLibrary(Long id, Library libraryDetails) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Library not found with id " + id));

        library.setName(libraryDetails.getName());
        library.setAddress(libraryDetails.getAddress());

        return libraryRepository.save(library);
    }

    @Transactional
    public void deleteLibrary(Long id) {
        if (!libraryRepository.existsById(id)) {
            throw new RuntimeException("Library not found with id " + id);
        }
        libraryRepository.deleteById(id);
    }

    public Optional<Library> getLibraryById(Long id) {
        return libraryRepository.findById(id);
    }

    public List<Library> getAllLibraries() {
        return libraryRepository.findAll();
    }
}