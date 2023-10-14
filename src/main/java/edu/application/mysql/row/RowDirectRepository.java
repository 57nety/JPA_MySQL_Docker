package edu.application.mysql.row;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RowDirectRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Transactional
    public void deleteByIds(List<Long> ids) {
        Query query = entityManager.createNativeQuery("DELETE FROM rows_data WHERE id IN:ids");
        query.setParameter("ids", ids);
        query.executeUpdate();
    }

    public List<Row> getByIds(List<Long> ids) {
        List<Row> result = new ArrayList<>();
        StringBuilder builder = new StringBuilder("SELECT id, title FROM rows_data WHERE id IN:ids");
        Query query = entityManager.createNativeQuery(builder.toString());
        query.setParameter("ids", ids);
        List<Object[]> objects = (List<Object[]>) query.getResultList();
        for (Object[] item : objects) {
            Row row = new Row();
            row.setId(Long.valueOf(String.valueOf(item[0])));
            row.setTitle(String.valueOf(item[1]));
            result.add(row);
        }
        return result;
    }
}
