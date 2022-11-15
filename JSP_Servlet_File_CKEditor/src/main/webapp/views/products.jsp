<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: This MC
  Date: 14/11/2022
  Time: 2:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Product</title>
</head>
<body>
    <table border="1">
        <thead>
            <tr>
                <th>Product Id</th>
                <th>Product Name</th>
                <th>Price</th>
                <th>Title</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listProduct}" var="pro">
                <tr>
                    <td>${pro.productId}</td>
                    <td>${pro.productName}</td>
                    <td>${pro.price}</td>
                    <td>${pro.title}</td>
                    <td>
                        <a href="<%=request.getContextPath()%>/ProductServlet?action=Detail&&productId=${pro.productId}">Detail</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="views/newProduct.jsp">Create New Product</a>
</body>
</html>
