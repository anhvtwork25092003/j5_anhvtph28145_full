<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dang nhap</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>
<form  action="/login" method="post">
    username: <input type="text" name="username">
    Password: <input type="password" name="password">
    <button type="submit" value="Login">Dang Nhap</button>
</form>

<a href="/tai-khoan/dang-ki">Dang ki tai khoan</a>
<a href="/quen-mat-khau">Quen mat khau</a>

</body>
</html>