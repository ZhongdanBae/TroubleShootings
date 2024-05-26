// AuthorService.java
package npoprob.demo.service;

import npoprob.demo.dto.AuthorDTO;
import npoprob.demo.dto.BookDTO;
import npoprob.demo.entity.Author;
import npoprob.demo.entity.Book;
import npoprob.demo.repository.AuthorRepository;
import npoprob.demo.repository.AuthorRepositoryCustomImpl;
import npoprob.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorRepositoryCustomImpl authorRepositoryCustomImpl;

    @Autowired
    private BookRepository bookRepository;

    public List<AuthorDTO> getAllAuthorsWithNPlusOne() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<AuthorDTO> getAllAuthorsWithFetchJoin() {
        List<Author> authors = authorRepository.findAllWithBooks();
        return authors.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<AuthorDTO> getAllAuthorsWithBatchSize() {
        // Batch size를 사용하여 작성
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(this::convertToDto).collect(Collectors.toList());

    }

    public List<AuthorDTO> getAllAuthorsWithEntityGraph() {
        List<Author> authors = authorRepositoryCustomImpl.findAllWithEntityGraphCustom();
        return authors.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private AuthorDTO convertToDto(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        List<BookDTO> bookDTOs = author.getBooks().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        authorDTO.setBooks(bookDTOs);
        return authorDTO;
    }

    private BookDTO convertToDto(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        return bookDTO;
    }
}