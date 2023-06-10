<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thong tin tài khoan</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<form action="/tai-khoan/update/${acc.id}" method="post">
    họ và tên: <input type="text" value="${acc.fullname}" name="fullname">
    username: <input type="text" name="username" value="${acc.username}" readonly>
    mật khẩu: <input type="hidden" value="${acc.password}" name="password">
    email: <input type="email" value="${acc.email}" name="email">
    <input type="hidden" value="${acc.role}" name="role">
    <button type="submit"value="update">Cap Nhat</button>
</form>
</body>
</html>