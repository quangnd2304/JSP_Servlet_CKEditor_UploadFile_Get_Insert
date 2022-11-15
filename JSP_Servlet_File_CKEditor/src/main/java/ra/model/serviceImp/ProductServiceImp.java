package ra.model.serviceImp;

import ra.model.dao.ProductDAO;
import ra.model.daoImp.ProductDAOImp;
import ra.model.entity.Product;
import ra.model.service.ProductService;

import java.util.List;

public class ProductServiceImp implements ProductService<Product,Integer> {
    private ProductDAO<Product,Integer> productDAO = new ProductDAOImp();
    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productDAO.findById(id);
    }

    @Override
    public boolean save(Product product) {
        return productDAO.save(product);
    }

    @Override
    public boolean update(Product product) {
        return productDAO.update(product);
    }

    @Override
    public boolean delete(Integer id) {
        return productDAO.delete(id);
    }

    @Override
    public List<Product> findAllShortProductInfor() {
        return  productDAO.findAllShortProductInfor();
    }
}
