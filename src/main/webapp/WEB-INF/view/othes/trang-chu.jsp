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
<body>
<%-- header--%>
<header>
    <div class="container-fluid">
        <div class="row justify-content-end">
            <div class="col-auto">
                <a href="#">Đăng nhập</a> |
                <a href="#">Đăng ký</a>
                <a href="/cart" role="button" class="btn btn-primary">Giỏ hàng</a>
            </div>
        </div>
    </div>
</header>


<div class="container">
    <div class="row">
        <div class="col-md-4">
            <form action="/quan-ly/view-all" method="get">
                <!-- Bộ lọc và tìm kiếm input -->
                <div class="mb-3">
                    <label for="searchInput" class="form-label">Tìm kiếm theo tên:</label>
                    <div class="input-group">
                        <input type="text" class="form-control" id="searchInput" name="keyword" value="${param.keyword}"
                               placeholder="Nhập tên đá quý">
                    </div>
                </div>
                <div class="mb-3">
                    <label>Khoảng giá:</label>
                    <div class="form-check">
                        <input type="radio" name="priceRange" id="range1" class="form-check-input"
                               value="" ${param.priceRange == ""?"checked":""}>
                        <label for="range1" class="form-check-label">Tất cả</label>
                    </div>
                    <div class="form-check">
                        <input type="radio" name="priceRange" id="range2" class="form-check-input"
                               value="0,5000000" ${param.priceRange == "0,5000000"?"checked":""}>
                        <label for="range2" class="form-check-label">0 - 5 triệu</label>
                    </div>
                    <div class="form-check">
                        <input type="radio" name="priceRange" id="range3" class="form-check-input"
                               value="5000000,10000000" ${param.priceRange == "5000000,10000000"?"checked":""}>
                        <label for="range3" class="form-check-label">5 - 10 triệu</label>
                    </div>
                    <div class="form-check">
                        <input type="radio" name="priceRange" id="range4" class="form-check-input"
                               value="10000000,20000000" ${param.priceRange == "10000000,20000000"?"checked":""}>
                        <label for="range4" class="form-check-label">10 - 20 triệu</label>
                    </div>
                    <div class="form-check">
                        <input type="radio" name="priceRange" id="range5" class="form-check-input"
                               value="20000000," ${param.priceRange == "20000000,"?"checked":""}>
                        <label for="range5" class="form-check-label">Lớn hơn 20 triệu</label>
                    </div>
                </div>
                <button class="btn btn-success" type="submit">Tìm Kiếm</button>
            </form>
        </div>

        <div class="col-md-8">
            <%-- Table --%>
            <c:if test="${pageDaQuy.isEmpty()}">
                <h2>Không có dữ liệu!!</h2>
            </c:if>

            <c:if test="${not pageDaQuy.isEmpty()}">
                <p>Đang có ${pageDaQuy.getTotalElements()} dữ liệu</p>
                <table class="table">
                    <thead class="table-success">
                    <tr>
                        <th>Tên</th>
                        <th>Số lượng đang có sẵn</th>
                        <th>Đơn giá</th>
                        <th>Trọng lượng</th>
                        <th>Actions</th>
                    </tr>
                    </thead>


                    <c:forEach items="${pageDaQuy.getContent()}" var="lp" varStatus="status">
                        <tr>
                            <td>${lp.ten}</td>
                            <td>${lp.soLuong}</td>
                            <td>${lp.donGia}</td>
                            <td>${lp.trongLuong}</td>
                            <td>
                                <a href="/quan-ly/detail/${lp.id}" role="button" class="btn btn-primary"
                                   data-bs-toggle="modal"
                                   data-bs-target="#myModal${status.index}">Detail</a>
                                <a href="/cart/add/${lp.id}" role="button" class="btn btn-primary"> Them vao gio hang</a>

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
                                            <input type="text" name="id" required readonly value="${lp.id}"
                                                   placeholder="Id tự tăng!"><br>
                                            <label for="ma">Mã:</label>
                                            <input type="text" id="ma" name="ma" required readonly value="${lp.ma}"
                                                   placeholder="Mã tự tăng!"><br>

                                            <label for="ten">Tên:</label>
                                            <input type="text" id="ten" name="ten" required value="${lp.ten}"><br>

                                            <label for="soLuong">Số lượng:</label>
                                            <input type="number" id="soLuong" name="soLuong" required
                                                   value="${lp.soLuong}"><br>

                                            <label for="donGia">Đơn giá:</label>
                                            <input type="number" id="donGia" name="donGia" step="0.1"
                                                   value="${lp.donGia}"
                                                   required><br>

                                            <label for="trongLuong">Trọng lượng:</label>
                                            <input type="number" id="trongLuong" name="trongLuong" step="0.1"
                                                   value="${lp.trongLuong}"
                                                   required><br>

                                            <label for="doSang">moTa:</label>
                                            <input type="text" id="doSang" name="moTa" value="${lp.moTa}" required><br>

                                            <input type="submit" value="Update"
                                                   onclick="return confirm('Xác nhận cập nhật?')">
                                        </form>
                                    </div>
                                    <!-- Modal footer -->
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </table>
            </c:if>

            <div>
                <c:if test="${pageDaQuy.number + 1 > 1}">
                    <a href="?page=${pageDaQuy.number}&keyword=${param.keyword}&priceRange=${param.priceRange}">
                        Previous
                    </a>
                </c:if>
                <span>${pageDaQuy.number + 1} / ${pageDaQuy.totalPages}</span>
                <c:if test="${pageDaQuy.number + 1 < pageDaQuy.totalPages}">
                    <a href="?page=${pageDaQuy.number + 2}&keyword=${param.keyword}&priceRange=${param.priceRange}">
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
