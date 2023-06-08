<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi Tiết Hóa Đơn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">

    <table class="table">
        <thead>
        <tr>
            <th>STT</th>
            <th>tên Sản phẩm</th>
            <th>Đon giá sản phẩm</th>
            <th>số lượng đã mua</th>
            <th>Thành tiền/
            <th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${order}" var="order" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${order.daQuy.ten}</td>
                <td>${order.donGia}</td>
                <td>${order.soluong}</td>
                <td>${order.soluong*order.donGia}</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>