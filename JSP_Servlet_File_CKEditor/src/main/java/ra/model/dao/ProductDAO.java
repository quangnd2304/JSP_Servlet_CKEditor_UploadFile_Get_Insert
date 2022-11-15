package ra.model.dao;

import java.util.List;

public interface ProductDAO<T,V> extends AppDao<T,V> {
    List<T> findAllShortProductInfor();
}
