package npoprob.demo.repository;

import npoprob.demo.entity.Author;

import java.util.List;

public interface AuthorRepositoryCustom {
    List<Author> findAllWithEntityGraphCustom();
}