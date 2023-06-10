<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh Sach Tai khoan</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<form action="/tai-khoan/dang-ki/add" method="post">
    họ và tên: <input type="text" value="" name="fullname">
    username: <input type="text" name="username" value="" >
    mật khẩu: <input type="password" value="" name="password">
    email: <input type="email" value="" name="email">
    <button type="submit"value="update">Dang ki</button>
</form>
</body>
</html>