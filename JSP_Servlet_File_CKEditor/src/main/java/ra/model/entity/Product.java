package ra.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int productId;
    private String productName;
    private float price;
    private String title;
    private String descriptions;
    private String productImage;
    private boolean productStatus;
    private List<String> listImageLink = new ArrayList<>();

    public Product() {
    }

    public Product(int productId, String productName, float price, String title, String descriptions, String productImage, boolean productStatus, List<String> listImageLink) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.title = title;
        this.descriptions = descriptions;
        this.productImage = productImage;
        this.productStatus = productStatus;
        this.listImageLink = listImageLink;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public List<String> getListImageLink() {
        return listImageLink;
    }

    public void setListImageLink(List<String> listImageLink) {
        this.listImageLink = listImageLink;
    }
}
