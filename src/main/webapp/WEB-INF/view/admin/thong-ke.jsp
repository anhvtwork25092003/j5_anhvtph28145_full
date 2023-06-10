<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thong ke</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

</head>
<body>
<jsp:include page="../admin/quan-ly.jsp" />
<div class="container mt-3">
    <h2>Top 10 mat hang ban chay nhat</h2>
    <table class="table">
        <thead>
        <tr>
            <th>ten San pham</th>
            <th>So luong mua</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${hangBanChayList.getContent()}" var="order">
            <tr>
                <td>${order.tenSanPham}</td>
                <td>${order.soLuongDaBan}</td>

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
