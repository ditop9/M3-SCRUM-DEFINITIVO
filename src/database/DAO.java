package database;

import java.util.List;

public interface DAO<T> {
    List<T> read();
    T searchById(int id);
    boolean create(T t);
    boolean delete(int id);
    boolean update(T t, int id);
}
