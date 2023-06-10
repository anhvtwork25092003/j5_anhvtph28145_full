<html>
<body>
<form action="/quen-mat-khau/update" method="post">
    nhap mat khau moi:<input type="password" name="newpass" required>
    nhap lai mat khau moi:<input type="password" name="repass" required>
    <button type="submit"> Dat lai mat khau</button>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
</form>
</body>
</html>