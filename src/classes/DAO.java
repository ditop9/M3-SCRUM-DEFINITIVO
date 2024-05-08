package classes;

import java.util.List;
import java.util.Objects;

public interface DAO {
    List<Object> read();
    void searchById();
    boolean create();
    boolean delete();
    boolean update();
}
