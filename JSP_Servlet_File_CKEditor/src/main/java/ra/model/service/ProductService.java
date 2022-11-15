package ra.model.service;

import java.util.List;

public interface ProductService<T,V> extends AppService<T,V>{
    List<T> findAllShortProductInfor();
}
