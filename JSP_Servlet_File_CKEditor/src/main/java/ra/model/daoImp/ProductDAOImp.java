package ra.model.daoImp;

import ra.model.dao.ProductDAO;
import ra.model.entity.Product;
import ra.model.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImp implements ProductDAO<Product, Integer> {
    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Product findById(Integer id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Product pro = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getProductById(?)}");
            callSt.setInt(1,id);
            ResultSet rs = callSt.executeQuery();
            pro = new Product();
            if (rs.next()){
                pro.setProductId(rs.getInt("ProductId"));
                pro.setProductName(rs.getString("ProductName"));
                pro.setPrice(rs.getFloat("Price"));
                pro.setTitle(rs.getString("Title"));
                pro.setDescriptions(rs.getString("Descriptions"));
                pro.setProductImage(rs.getString("ProductImage"));
                pro.setProductStatus(rs.getBoolean("ProductStatus"));
                //Lay tat ca link anh phu cua san pham
                CallableStatement callSt2 = conn.prepareCall("{call proc_getImageByProductId(?)}");
                callSt2.setInt(1,id);
                ResultSet rs2 = callSt2.executeQuery();
                while (rs2.next()){
                    pro.getListImageLink().add(rs2.getString("ImageLink"));
                }
                callSt2.close();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return pro;
    }

    @Override
    public boolean save(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = true;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_insertProduct(?,?,?,?,?,?,?)}");
            callSt.setString(1, product.getProductName());
            callSt.setFloat(2, product.getPrice());
            callSt.setString(3, product.getTitle());
            callSt.setString(4, product.getDescriptions());
            callSt.setString(5, product.getProductImage());
            callSt.setBoolean(6, product.isProductStatus());
            callSt.registerOutParameter(7, Types.INTEGER);
            callSt.execute();
            int productId = callSt.getInt(7);
            //Them anh phu cho san pham - 1 doi tuong callSt goi nhieu procedure
            for (String proLink : product.getListImageLink()) {
                CallableStatement callSt2 = conn.prepareCall("{call proc_insertImages(?,?)}");
                callSt2.setString(1,proLink);
                callSt2.setInt(2,productId);
                callSt2.executeUpdate();
                callSt2.close();
            }

        } catch (SQLException ex) {
            result = false;
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public List<Product> findAllShortProductInfor() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> listProduct = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getAllProductShortInfo()}");
            ResultSet rs = callSt.executeQuery();
            listProduct = new ArrayList<>();
            while (rs.next()){
                Product pro = new Product();
                pro.setProductId(rs.getInt("ProductId"));
                pro.setProductName(rs.getString("ProductName"));
                pro.setPrice(rs.getFloat("Price"));
                pro.setTitle(rs.getString("Title"));
                listProduct.add(pro);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return listProduct;
    }
}
