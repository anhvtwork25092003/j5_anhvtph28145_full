<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Giỏ hàng của bạn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body class="container mt-3">
<div>
    <table class="table">
        <thead class="table-success">
        <tr>
            <th>Mã</th>
            <th>Tên</th>
            <th>Số lượng</th>
            <th>Đơn giá</th>
            <th>Thành Tiền</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cart}" var="cartItem">
            <form action="/cart/update" method="post">
                <input type="hidden" name="id" value="${cartItem.maSanPham}">
                <tr>
                    <td>${cartItem.maSanPham}</td>
                    <td>${cartItem.tenSanPham}</td>
                    <td>${cartItem.donGia}</td>
                    <td>
                        <input name="qty" value="${cartItem.soLuong}"
                               onblur="this.form.submit()" style="width: 50px;">
                    </td>
                    <td> ${cartItem.donGia*cartItem.soLuong}</td>
                    <td>
                        <a href="/cart/del/${cartItem.maSanPham}" onclick="return confirm('Bạn xác nhận xóa chứ?')"
                           role="button"
                           class="btn btn-danger">Remove</a>
                    </td>
                </tr>
            </form>

        </c:forEach>
        </tbody>
    </table>
    <p>Tổng Tiền: ${total}</p>
    <a href="/cart/clear" onclick="return confirm('Bạn xác nhận bo het hang trong gio  chứ?')"
       role="button"
       class="btn btn-danger">Thanh toán</a>
    <br>
    <a href="/cart/clear" onclick="return confirm('Bạn xác nhận bo het hang trong gio  chứ?')"
       role="button"
       class="btn btn-danger">Clear Cart</a>
    <a href="/index"
       role="button"
       class="btn btn-danger">Add More</a>
</div>
</body>
</html>
