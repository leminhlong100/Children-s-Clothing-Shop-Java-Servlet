<%--
  Created by IntelliJ IDEA.
  User: RynVi
  Date: 14/06/2023
  Time: 2:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:url value="/admin/assets" var="url"/>
<fmt:setLocale value="${sessionScope.LANG}"/>
<fmt:setBundle basename="app" var="lang"/>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Thêm sản phẩm | Quản trị Admin</title>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <jsp:include page="./header/link-css.jsp" flush="true"/>
  <script src="http://code.jquery.com/jquery.min.js" type="text/javascript"></script>
  <style>
    table {
      border-collapse: collapse;
      width: 100%;
    }
    th, td {
      border: 1px solid black;
      padding: 8px;
      text-align: left;
    }
    .add-button {
      margin-top: 10px;
    }
    #uploadFile,
    #uploadFileMain {
      display: none; /* Ẩn phần input file mặc định */
    }

    .custom-upload,
    .custom-upload-main {
      display: inline-block;
      padding: 8px 12px;
      background-color: #e0e0e0;
      border-radius: 4px;
      cursor: pointer;
    }

    .custom-upload:hover,
    .custom-upload-main:hover {
      background-color: #d0d0d0;
    }

    .custom-upload-label,
    .custom-upload-label-main {
      font-size: 14px;
      color: #333;
    }

    .custom-upload-input,
    .custom-upload-input-main {
      display: none;
    }

    #imageContainer {
      display: grid;
      grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));
      grid-gap: 10px;
      position: relative;
    }

    #imageContainerMain {
      display: grid;
      grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));
      grid-gap: 10px;
      position: relative;
    }
    .thumbnail-image-main{
      position: relative;
      width: 250%;
      height: auto;
    }

    .thumbnail-image {
      position: relative;
      width: 100%;
      height: 150px; /* Chiều cao cố định, thay đổi giá trị tùy ý */
      overflow: hidden;
    }

    .thumbnail-image img {
      width: 100%;
      height: auto;
      object-fit: cover;
    }

    .thumbnail-image:before {
      content: "";
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      box-sizing: border-box;
      border: 5px solid black; /* Màu viền đen, thay đổi giá trị tùy ý */
      z-index: 1;
    }

    .avatar-text {
      position: absolute;
      bottom: 0;
      left: 0;
      width: 100%;
      text-align: center;
      background-color: rgba(0, 0, 0, 0.7);
      color: #fff;
      padding: 5px;
      font-size: 12px;
    }
  </style>
</head>

<body class="app sidebar-mini rtl">
<!-- Navbar-->
<jsp:include page="./header/admin-left-sidebar.jsp" flush="true"/>
<main class="app-content">
  <div class="app-title">
    <ul class="app-breadcrumb breadcrumb">
      <li class="breadcrumb-item">Danh sách sản phẩm</li>
      <li class="breadcrumb-item"><a href="#">Thêm sản phẩm</a></li>
    </ul>
  </div>
  <div class="row">
    <div class="col-md-12">
      <div class="tile">
        <h3 class="tile-title">Tạo mới sản phẩm</h3>
        <div class="tile-body">
          <div class="row element-button">
            <div class="col-sm-2">
              <a class="btn btn-add btn-sm" data-toggle="modal" data-target="#exampleModalCenter"><i
                      class="fas fa-folder-plus"></i> Thêm nhà cung cấp</a>
            </div>
            <div class="col-sm-2">
              <a class="btn btn-add btn-sm" data-toggle="modal" data-target="#adddanhmuc"><i
                      class="fas fa-folder-plus"></i> Thêm danh mục</a>
            </div>
          </div>
          <form class="row" enctype="multipart/form-data" method="post"
                action="${pageContext.request.contextPath}/admin-products/ProductAddController">
            <div class="form-group col-md-3">
              <label class="control-label">Tên sản phẩm</label>
              <input class="form-control" type="text" name="nameProduct" required>
            </div>
            <div class="form-group col-md-3">
              <label for="exampleSelect2" class="control-label">Danh mục</label>
              <select class="form-control" id="exampleSelect2" name="category" required>
                <option>-- Chọn danh mục --</option>
                <c:forEach items="${requestScope.listCategory}" var="o">
                  <option>${o.nameCategory}</option>
                </c:forEach>
              </select>
            </div>
            <div class="form-group col-md-3 ">
              <label for="exampleSelect3" class="control-label">Nhà cung cấp</label>
              <select class="form-control" id="exampleSelect3" name="supplier" required>
                <option>-- Chọn nhà cung cấp --</option>
                <c:forEach items="${requestScope.listSupplier}" var="o">
                  <option>${o.nameSupplier}</option>
                </c:forEach>
              </select>
            </div>
            <div class="form-group col-md-3 ">
              <label for="exampleSelect4" class="control-label">Nhà sản xuất</label>
              <select class="form-control" id="exampleSelect4" name="producer" required>
                <option>-- Chọn nhà sản xuất --</option>
                <c:forEach items="${requestScope.listProducer}" var="o">
                  <option>${o.nameProducer}</option>
                </c:forEach>
              </select>
            </div>
            <div class="form-group col-md-3">
              <label class="control-label">Giá bán</label>
              <input class="form-control" type="text" name="price" style="width: 70%" required>
            </div>
            <div class="form-group col-md-3">
              <label class="control-label">% Khuyến mãi</label>
              <input class="form-control" type="text" name="sell" style="width: 70%" required>
            </div>
            <table id="product-table">
              <tr>
                <th>Kích thước</th>
                <th>Màu sắc</th>
                <th>Số lượng</th>
                <th>Xóa</th>
              </tr>
              <tr>
                <td><input type="text" name="size-1"></td>
                <td><input type="text" name="color-1"></td>
                <td><input type="text" name="quantity-1"></td>
                <td><button onclick="deleteRow(this)">Xóa</button></td>
              </tr>
            </table>
            <button class="btn btn-add" type="button" onclick="addRow()">Thêm hàng mới</button>
            <script>
              let rowCount = 1;

              function addRow() {
                rowCount++;

                let table = document.getElementById("product-table");
                let row = table.insertRow(-1);

                let sizeCell = row.insertCell(0);
                sizeCell.innerHTML = '<input type="text" name="size-' + rowCount + '">';

                let colorCell = row.insertCell(1);
                colorCell.innerHTML = '<input type="text" name="color-' + rowCount + '">';

                let quantityCell = row.insertCell(2);
                quantityCell.innerHTML = '<input type="text" name="quantity-' + rowCount + '">';

                let deleteCell = row.insertCell(3);
                deleteCell.innerHTML = '<button onclick="deleteRow(this)">Xóa</button>';
              }

              function deleteRow(button) {
                let table = document.getElementById("product-table");
                let rowCount = table.rows.length;

                if (rowCount > 2) { // Chỉ xóa dòng dưới cùng nếu có nhiều hơn 1 dòng
                  let row = button.parentNode.parentNode;
                  table.deleteRow(row.rowIndex);
                }
              }
            </script>
            <div class="form-group col-md-12">
              <label class="control-label">Ảnh đại diện sản phẩm</label> <br>
              <label for="uploadFileMain" class="custom-upload-main">
                <span class="custom-upload-label-main">Chọn tệp tin</span>
                <input type="file" id="uploadFileMain" name="mainImage" onchange="readURLMain(this);" class="custom-upload-input-main">
              </label>
              <div id="imageContainerMain"></div>
              <label class="control-label">Ảnh mô tả sản phẩm</label> <br>
              <label for="uploadFile" class="custom-upload">
                <span class="custom-upload-label">Chọn tệp tin</span>
                <input type="file" id="uploadFile" name="descriptionImages" onchange="readURL(this);" multiple class="custom-upload-input">
              </label>
              <div id="imageContainer"></div>
              <br>
            </div>
            <div class="form-group col-md-12">
              <label class="control-label">Mô tả sản phẩm</label>
              <textarea class="form-control" name="description" id="mota"></textarea>
            </div>
            <div class="form-group col-md-12">
              <button class="btn btn-save" type="submit">Lưu lại</button>
              <a class="btn btn-cancel" href="#">Hủy bỏ</a>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</main>
<jsp:include page="./header/link-js.jsp" flush="true"/>
<script>
  function readURL(input) {
    if (input.files && input.files.length > 0) {
      let imageContainer = document.getElementById("imageContainer");
      let inputImage = document.getElementById("uploadFile");
      imageContainer.innerHTML = "";

      // Kiểm tra số lượng hình ảnh nằm trong khoảng giới hạn cho phép
      if (input.files.length < 4 || input.files.length > 5) {
        Swal.fire("Vui lòng chọn từ 4 đến 5 hình ảnh.", "", "error");
        inputImage.value = "";
        return;
      }
      for (let i = 0; i < input.files.length; i++) {
        let reader = new FileReader();
        let file = input.files[i];
        reader.readAsDataURL(file);
        reader.onload = function (e) {
          let imgElement = document.createElement("img");
          imgElement.src = e.target.result;
          imgElement.classList.add("thumbnail-image");
          imageContainer.appendChild(imgElement);
        };
      }
    }
  }
  function readURLMain(input) {
    if (input.files && input.files[0]) {
      let imageContainer = document.getElementById("imageContainerMain");
      let inputImage = document.getElementById("uploadFileMain");
      imageContainer.innerHTML = "";

      let reader = new FileReader();
      let file = input.files[0];
      reader.readAsDataURL(file);
      reader.onload = function (e) {
        let imgElement = document.createElement("img");
        imgElement.src = e.target.result;
        imgElement.classList.add("thumbnail-image-main");
        imageContainer.appendChild(imgElement);
      };
    }
  }

</script>
</body>
</html>
