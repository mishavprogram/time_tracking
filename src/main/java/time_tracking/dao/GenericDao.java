package time_tracking.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {
    void create(T entity);

    Optional<T> findById(long id);

    List<T> findAll();

    void update(T entity);

    void delete(int id);

    void close();
}
