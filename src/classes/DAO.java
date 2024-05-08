package classes;

import java.util.List;

public interface DAO {
    List<Object> read();
    void searchById();
    boolean create();
    boolean delete();
    boolean update();
}
