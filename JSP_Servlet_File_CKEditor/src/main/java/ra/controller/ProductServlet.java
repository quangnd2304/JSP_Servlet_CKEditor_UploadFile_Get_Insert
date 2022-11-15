package ra.controller;

import ra.model.entity.Product;
import ra.model.service.ProductService;
import ra.model.serviceImp.ProductServiceImp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10
)
public class ProductServlet extends HttpServlet {
    private ProductService<Product, Integer> productService = new ProductServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("GetAll")) {
            getAllProduct(request, response);
        } else if (action.equals("Detail")) {
            //Lay thong tin chi tiet cua san pham theo id
            int productId = Integer.parseInt(request.getParameter("productId"));
            Product proDetail = productService.findById(productId);
            request.setAttribute("proDetail",proDetail);
            request.getRequestDispatcher("views/productDetail.jsp").forward(request,response);
        }
    }

    public void getAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> listProduct = productService.findAllShortProductInfor();
        request.setAttribute("listProduct",listProduct);
        request.getRequestDispatcher("views/products.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if (action.equals("Create")) {
            //Thuc hien them moi san pham
            //B1. tao thu muc chua anh cung o cung cai dat tomcat
            //Lấy đường dẫn thư mục chứa Tomcat
            String pathRootTomcat = getServletContext().getRealPath("");
            //Tạo đường dẫn thư mục chứa ảnh webapps/images
            //D:\Demo\apache-tomcat-9.0.68\webapps\images
            String pathImageLocation = pathRootTomcat.split("ROOT")[0]+ "images";

            File fileImages = new File(pathImageLocation);
            if (!fileImages.exists()) {
                //Tạo thư mục images nếu chưa có
                fileImages.mkdir();
            }
            //B2. Lay du lieu trong request
            //B2.1. Lay cac du lieu khong phai la file add vao doi tuong product can them moi
            Product proNew = new Product();
            proNew.setProductName(request.getParameter("productName"));
            proNew.setTitle(request.getParameter("title"));
            proNew.setPrice(Float.parseFloat(request.getParameter("price")));
            proNew.setDescriptions(request.getParameter("descriptions"));
            proNew.setProductStatus(Boolean.parseBoolean(request.getParameter("status")));
            //B2.2. Lay anh trong request
            //B2.2.1. set duong link anh vao productNew
            //B2.2.2. ghi anh vao thu muc theo duong dan pathImageLocation
            for (Part part : request.getParts()) {
                if (part.getName().equals("proImage")){
                    //part chua anh chinh san pham
                    String proImageName = part.getSubmittedFileName();
                    //add duong dan anh chinh vao proNew
                    proNew.setProductImage(proImageName);
                    //Ghi anh vao file chua cac anh tren serrver
                    part.write(pathImageLocation+File.separator+proImageName);
                } else if (part.getName().equals("subImages")) {
                    //part chua anh phu cua san pham
                    String subImageName = part.getSubmittedFileName();
                    //add duong dan anh phu vao proNew
                    proNew.getListImageLink().add(subImageName);
                    //ghi anh phu cua san pham vao thu muc tren server
                    InputStream fileContent = part.getInputStream();
                    part.write(pathImageLocation+File.separator+subImageName);
                }
            }
            //Goi sang service thuc hien them moi anh
            boolean rersult = productService.save(proNew);
            if (rersult){
                getAllProduct(request,response);
            }else{

            }
        }
    }
}
