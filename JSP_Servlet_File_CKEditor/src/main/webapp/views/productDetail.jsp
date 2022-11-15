<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: This MC
  Date: 14/11/2022
  Time: 4:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Detail</title>
</head>
<body>
  <div>
    <div><img src="<%=request.getContextPath()%>/images/${proDetail.productImage}" alt="${proDetail.productName}"/></div>
    <div>
      <c:forEach items="${proDetail.listImageLink}" var="link">
        <img src="<%=request.getContextPath()%>/images/${link}" alt="${proDetail.productName}"/>
      </c:forEach>
    </div>
    <p>${proDetail.title}</p>
    <p>${proDetail.price}VNĐ</p>
    <p>${proDetail.descriptions}</p>
  </div>
</body>
</html>
