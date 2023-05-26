<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Form và Table</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body class="container mt-3">
<div class="container">
    <div class="row">
        <div class="col-md-4">
            <!-- Bộ lọc và tìm kiếm input -->
        </div>
        <div class="col-md-8">
            <!-- Các nút và table-->
            <%--button add--%>
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModalAdd">
                Thêm mới
            </button>
            <c:if test="${not empty blankError}">
                <div class="alert alert-danger">${blankError}</div>
            </c:if>
            <%-- modal add--%>
            <!-- The Modal -->
            <div class="modal" id="myModalAdd">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title">Chi Tiết Sản Phẩm</h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <!-- Modal body -->
                        <div class="modal-body">
                            <form action="/quan-ly/add" method="post">
                                <label>Mã:</label>
                                <input type="text" name="maAdd" required readonly><br>

                                <label>Tên:</label>
                                <input type="text" name="tenAdd" required><br>

                                <label>Số lượng:</label>
                                <input type="number" name="soLuongAdd" required><br>

                                <label>Đơn giá:</label>
                                <input type="number" name="donGiaAdd" step="0.1" required><br>

                                <label>Trọng lượng:</label>
                                <input type="number" name="trongLuongAdd" step="0.1"
                                       required><br>

                                <label>moTa</label>
                                <input type="text" name="moTaAdd" required><br>

                                <input type="submit" value="thêm mới">
                            </form>
                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <%-- Table --%>
            <table class="table">
                <thead class="table-success">
                <tr>
                    <th>Mã</th>
                    <th>Tên</th>
                    <th>Số lượng</th>
                    <th>Đơn giá</th>
                    <th>Trọng lượng</th>
                    <th>Mô Tả</th>
                    <th>Actions</th>
                </tr>
                </thead>


                <c:forEach items="${pageDaQuy.getContent()}" var="lp" varStatus="status">
                    <tr>
                        <td>${lp.ma}</td>
                        <td>${lp.ten}</td>
                        <td>${lp.soLuong}</td>
                        <td>${lp.donGia}</td>
                        <td>${lp.trongLuong}</td>
                        <td>${lp.moTa}</td>
                        <td>
                            <a href="/quan-ly/delete/${lp.id}" onclick="return confirm('Bạn xác nhận xóa chứ?')"
                               role="button"
                               class="btn btn-primary">Delete</a>
                            <a href="/quan-ly/detail/${lp.id}" role="button" class="btn btn-primary"
                               data-bs-toggle="modal"
                               data-bs-target="#myModal${status.index}">Detail</a>
                        </td>
                    </tr>

                    <%--        <!-- The Modal -->--%>
                    <div class="modal" id="myModal${status.index}">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <!-- Modal Header -->
                                <div class="modal-header">
                                    <h4 class="modal-title">Chi Tiết Sản Phẩm</h4>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                </div>
                                <!-- Modal body -->
                                <div class="modal-body">
                                    <form action="/quan-ly/update" method="post">
                                        <label>ID:</label>
                                        <input type="text" name="id" required readonly value="${lp.id}"><br>
                                        <label for="ma">Mã:</label>
                                        <input type="text" id="ma" name="ma" required readonly value="${lp.ma}"><br>

                                        <label for="ten">Tên:</label>
                                        <input type="text" id="ten" name="ten" required value="${lp.ten}"><br>

                                        <label for="soLuong">Số lượng:</label>
                                        <input type="number" id="soLuong" name="soLuong" required value="${lp.soLuong}"><br>

                                        <label for="donGia">Đơn giá:</label>
                                        <input type="number" id="donGia" name="donGia" step="0.1" value="${lp.donGia}"
                                               required><br>

                                        <label for="trongLuong">Trọng lượng:</label>
                                        <input type="number" id="trongLuong" name="trongLuong" step="0.1"
                                               value="${lp.trongLuong}"
                                               required><br>

                                        <label for="doSang">moTa:</label>
                                        <input type="text" id="doSang" name="moTa" value="${lp.moTa}" required><br>

                                        <input type="submit" value="Update">
                                    </form>
                                </div>
                                <!-- Modal footer -->
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
                                </div>

                            </div>
                        </div>
                    </div>
                </c:forEach>
            </table>
            <div>
                <c:if test="${pageDaQuy.getNumber() + 1 > 1}">
                    <a href="?page=${pageDaQuy.getNumber()}">
                        Previous
                    </a>
                </c:if>
                <span> ${pageDaQuy.getNumber() + 1} / ${pageDaQuy.getTotalPages()} </span>
                <c:if test="${pageDaQuy.getNumber() + 1 lt pageDaQuy.getTotalPages()}">
                    <a href="?page=${pageDaQuy.getNumber() + 2}">
                        Next
                    </a>
                </c:if>
            </div>

            <%----%>
        </div>
    </div>
</div>


</body>
</html>
