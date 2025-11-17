package inventory.interfaces;

public interface CrudRepository<T> {
    T create(T entity);
    T findById(int id);
    boolean update(int id, T entity);
    boolean delete(int id);
}
