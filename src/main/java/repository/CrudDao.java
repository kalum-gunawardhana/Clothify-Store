package repository;

import java.util.List;

public interface CrudDao<T,ID> extends SuperDao{
    boolean add(T entity);
    T search(ID id);
    boolean update(T entity);
    boolean delete(ID id);
    List<T> getAll();
}
