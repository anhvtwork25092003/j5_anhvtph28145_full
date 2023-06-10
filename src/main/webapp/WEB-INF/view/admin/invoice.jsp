<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách hóa đơn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../admin/quan-ly.jsp" />
<div class="container mt-3">
    <h2>Danh sách hóa đơn</h2>
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Ngày tạo</th>
            <th>Người tạo</th>
            <th>Tổng tiền</th>
            <th>Chi tiết</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orders}" var="order">
            <tr>
                <td>${order.id}</td>
                <td>${order.createdDate}</td>
                <td>${order.fullname}</td>
                <td>${order.tongTien}</td>
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
