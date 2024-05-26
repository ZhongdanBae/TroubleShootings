package npoprob.demo.util;

import npoprob.demo.entity.Author;
import npoprob.demo.entity.Book;
import npoprob.demo.repository.AuthorRepository;
import npoprob.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        List<Author> authors = new ArrayList<>();

        for (int i = 1; i <= 25; i++) {
            Author author = new Author();
            author.setName("Author " + i);

            List<Book> books = new ArrayList<>();
            for (int j = 1; j <= 5; j++) {
                Book book = new Book();
                book.setTitle("Book " + j + " by Author " + i);
                book.setAuthor(author);
                books.add(book);
            }

            author.getBooks().addAll(books);
            authors.add(author);
        }

        authorRepository.saveAll(authors);
    }
}
