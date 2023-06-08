<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thong ke</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-3">
    <h2>Top 10 mat hang ban e</h2>
    <table class="table">
        <thead>
        <tr>
            <th>ten San pham</th>
            <th>So luong ton kho</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${HangE.getContent()}" var="order">
            <tr>
                <td>${order.tenSanPham}</td>
                <td>${order.soLuongTonKho}</td>

                <td>
                    <a href="/invoices/hoa-don-chi-tiet/${order.id}">Xem chi tiet</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
