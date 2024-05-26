package npoprob.demo.repository;

import npoprob.demo.entity.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a JOIN FETCH a.books")
    List<Author> findAllWithBooks();

    // 배치 사이즈 조절에 따라 달라짐. 설정 안하면 n+1 문제 발생
    List<Author> findAll();
}



