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
    <script type="text/javascript" src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
    <title>Create Product</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/ProductServlet" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>Product Name</td>
            <td><input type="text" name="productName"/></td>
        </tr>
        <tr>
            <td>Price</td>
            <td><input type="number" name="price"/></td>
        </tr>
        <tr>
            <td>Title</td>
            <td><input type="text" name="title"/></td>
        </tr>
        <tr>
            <td>Description</td>
            <td><textarea name="descriptions" id="descriptions"></textarea></td>
        </tr>
        <tr>
            <td>Product Image</td>
            <td><input type="file" name="proImage" id="proImage"/></td>
        </tr>
        <tr>
            <td>Product Sub Images</td>
            <td><input type="file" name="subImages" id="subImages" multiple/></td>
        </tr>
        <tr>
            <td>Product Status</td>
            <td>
                <input type="radio" name="status" id="active" value="true"/><label for="active">Active</label>
                <input type="radio" name="status" id="inactive" value="false"/><label for="inactive">Inactive</label>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Create" name="action"/>
            </td>
        </tr>
    </table>
</form>
<script>
    CKEDITOR.replace("descriptions");
</script>
</body>
</html>
