package npoprob.demo.repository;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

import npoprob.demo.entity.Author;
import npoprob.demo.repository.AuthorRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AuthorRepositoryCustomImpl implements AuthorRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAllWithEntityGraphCustom() {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("author-entity-graph");
        TypedQuery<Author> query = entityManager.createQuery("SELECT a FROM Author a", Author.class);
        query.setHint("jakarta.persistence.loadgraph", entityGraph);
        return query.getResultList();
    }
}