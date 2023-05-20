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
  <title>Danh sách sản phẩm | Quản trị Admin</title>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <jsp:include page="./header/link-css.jsp" flush="true"/>
</head>

<body onload="time()" class="app sidebar-mini rtl">
<!-- Navbar-->
<jsp:include page="./header/admin-left-sidebar.jsp" flush="true"/>
<main class="app-content">
  <div class="app-title">
    <ul class="app-breadcrumb breadcrumb side">
      <li class="breadcrumb-item active"><a href="#"><b>Danh sách sản phẩm</b></a></li>
    </ul>
    <div id="clock"></div>
  </div>
  <div class="row">
    <div class="col-md-12">
      <div class="tile">
        <div class="tile-body">
          <div class="row element-button">
            <div class="col-sm-2">

              <a class="btn btn-add btn-sm" href="form-add-san-pham.html" title="Thêm"><i
                      class="fas fa-plus"></i>
                Tạo mới sản phẩm</a>
            </div>
            <div class="col-sm-2">
              <a class="btn btn-delete btn-sm nhap-tu-file" type="button" title="Nhập"
                 onclick="myFunction(this)"><i
                      class="fas fa-file-upload"></i> Tải từ file</a>
            </div>

            <div class="col-sm-2">
              <a class="btn btn-delete btn-sm print-file" type="button" title="In"
                 onclick="myApp.printTable()"><i
                      class="fas fa-print"></i> In dữ liệu</a>
            </div>
            <div class="col-sm-2">
              <a class="btn btn-delete btn-sm print-file js-textareacopybtn" type="button"
                 title="Sao chép"><i
                      class="fas fa-copy"></i> Sao chép</a>
            </div>

            <div class="col-sm-2">
              <a class="btn btn-excel btn-sm" href="" title="In"><i class="fas fa-file-excel"></i> Xuất
                Excel</a>
            </div>
            <div class="col-sm-2">
              <a class="btn btn-delete btn-sm pdf-file" type="button" title="In"
                 onclick="myFunction(this)"><i
                      class="fas fa-file-pdf"></i> Xuất PDF</a>
            </div>
            <div class="col-sm-2">
              <a class="btn btn-delete btn-sm" type="button" title="Xóa" onclick="myFunction(this)"><i
                      class="fas fa-trash-alt"></i> Xóa tất cả </a>
            </div>
          </div>
          <table class="table table-hover table-bordered" id="sampleTable">
            <thead>
            <tr>
              <th width="10"><input type="checkbox" id="all"></th>
              <th>Mã sản phẩm</th>
              <th>Tên sản phẩm</th>
              <th>Ảnh</th>
              <th>Size</th>
              <th>Tình trạng</th>
              <th>Nha San Xuat</th>
              <th>Giá tiền</th>
              <th>Giam gia</th>

              <th>Danh mục</th>
              <th>Chức năng</th>
            </tr>
            </thead>
            <tbody id ="afterupdate">
            <c:forEach var="list" items="${renderproduct}" varStatus="stt">
              <tr>

                <td width="10"><input type="checkbox" name="check1" value="1"></td>
                <td>${list.id}</td>
                <td>${list.nameProduct}</td>

                <td><img
                        src="../images/${list.imageProducts.get(0).getImage()}"
                        alt="./images/${list.imageProducts.get(0).getImage()}"
                        class="img-responsive" width="80px"></td>

                <td>
                  <select>
                    <c:forEach var="size" items="${list.colorSizes}">
                      <option value="${size}" onclick="changesize()">${size}</option>
                    </c:forEach>
                  </select>
                </td>
                <td><span class="badge bg-success">Còn hàng</span></td>
                <td></td>
                <td>${list.listPrice}</td>
                <td>${list.discountPrice}</td>
                <td>${list.category}</td>
                <td>
                  <button class="btn btn-primary btn-sm trash" type="button" title="Xóa"
                          onclick="deleteProduct(${list.id} )"><i class="fas fa-trash-alt"></i>
                  </button>
                  <button class="btn btn-primary btn-sm edit" type="button" title="Sửa" id="show-emp"
                          data-toggle="modal"
                          data-target="#ModalUP" onclick="UpdateProduct(${list.id} )"><i
                          class="fas fa-edit"></i></button>
                </td>
              </tr>
            </c:forEach>

            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</main>

<!--
  MODAL
-->
<div id="showproduct"></div>
<!--
MODAL
-->

<!-- Essential javascripts for application to work-->
<jsp:include page="./header/link-js.jsp" flush="true"/>
<script type="text/javascript">$('#sampleTable').DataTable();</script>
<script type="text/javascript">
  oTable = $('#sampleTable').DataTable();
  $('#all').click(function (e) {
    $('#sampleTable tbody :checkbox').prop('checked', $(this).is(':checked'));
    e.stopImmediatePropagation();
  });
  $('#sampleTable').DataTable();

  //Thời Gian
  function time() {
    var today = new Date();
    var weekday = new Array(7);
    weekday[0] = "Chủ Nhật";
    weekday[1] = "Thứ Hai";
    weekday[2] = "Thứ Ba";
    weekday[3] = "Thứ Tư";
    weekday[4] = "Thứ Năm";
    weekday[5] = "Thứ Sáu";
    weekday[6] = "Thứ Bảy";
    var day = weekday[today.getDay()];
    var dd = today.getDate();
    var mm = today.getMonth() + 1;
    var yyyy = today.getFullYear();
    var h = today.getHours();
    var m = today.getMinutes();
    var s = today.getSeconds();
    m = checkTime(m);
    s = checkTime(s);
    nowTime = h + " giờ " + m + " phút " + s + " giây";
    if (dd < 10) {
      dd = '0' + dd
    }
    if (mm < 10) {
      mm = '0' + mm
    }
    today = day + ', ' + dd + '/' + mm + '/' + yyyy;
    tmp = '<span class="date"> ' + today + ' - ' + nowTime +
            '</span>';
    document.getElementById("clock").innerHTML = tmp;
    clocktime = setTimeout("time()", "1000", "Javascript");

    function checkTime(i) {
      if (i < 10) {
        i = "0" + i;
      }
      return i;
    }
  }
</script>
<script>
  function deleteProduct(id, button) {
    let row = $(button).closest('tr');
    let idproduct = id;
    let text = "";
    Swal.fire({
      title: 'Bạn có muốn xóa sản phẩm này không?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes',
      cancelButtonText: 'No',
    }).then((result) => {
      if (result.isConfirmed) {
        $.ajax({
          url: "${pageContext.request.contextPath}/admin-products/ProductListController",
          type: "POST",
          data: {
            idproduct: idproduct
          },
          success: function (data) {
            let isdelete = JSON.parse(data).isDel;
            if (isdelete) {
              var currentPage = oTable.page(); // lưu trang hiện tại
              oTable.row(row).remove().draw();
              oTable.page(currentPage).draw(false); // thiết lập lại trang hiện tại sau khi vẽ lại bảng dữ liệu
            }
            Swal.fire('Xóa sản phẩm thành công', '', 'success');
          },
          error: function (data) {
            console.log(data)
          }
        });
      } else if (result.isDenied) {
        Swal.fire('Không xóa sản phẩm ', '', 'info');
      }
    })
  }
</script>
</body>

</html>