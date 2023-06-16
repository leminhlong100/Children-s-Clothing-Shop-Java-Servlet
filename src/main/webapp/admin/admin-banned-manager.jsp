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
  <title>Danh sách đơn hàng | Quản trị Admin</title>
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
      <li class="breadcrumb-item active"><a href="#"><b>Quản lý nội bộ</b></a></li>
    </ul>
    <div id="clock"></div>
  </div>
  <div class="row">
    <div class="col-md-12">
      <div class="tile">
        <div class="tile-body">
          <div class="row element-button">
            <div class="col-sm-2">

              <a class="btn btn-add btn-sm" href="form-add-bi-cam.html" title="Thêm"><i class="fas fa-plus"></i>
                Tạo mới</a>
            </div>
            <div class="col-sm-2">
              <a class="btn btn-delete btn-sm nhap-tu-file" type="button" title="Nhập" onclick="myFunction(this)"><i
                      class="fas fa-file-upload"></i> Tải từ file</a>
            </div>

            <div class="col-sm-2">
              <a class="btn btn-delete btn-sm print-file" type="button" title="In" onclick="myApp.printTable()"><i
                      class="fas fa-print"></i> In dữ liệu</a>
            </div>
            <div class="col-sm-2">
              <a class="btn btn-delete btn-sm print-file js-textareacopybtn" type="button" title="Sao chép"><i
                      class="fas fa-copy"></i> Sao chép</a>
            </div>

            <div class="col-sm-2">
              <a class="btn btn-excel btn-sm" href="" title="In"><i class="fas fa-file-excel"></i> Xuất Excel</a>
            </div>
            <div class="col-sm-2">
              <a class="btn btn-delete btn-sm pdf-file" type="button" title="In" onclick="myFunction(this)"><i
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
              <th>Họ và Tên</th>
              <th>Ngày sinh</th>
              <th>Chức vụ</th>
              <th>Lý do cấm</th>
              <th>Tình trạng</th>
              <th>Chức năng</th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <td width="10"><input type="checkbox" name="check1" value="1"></td>
              <td>Kiều Thái Anh</td>
              <td>10/09/1998</td>
              <td>Thu Ngân</td>
              <td>Gian lận trong công việc đã lấy trộm tiền của công ty và thái độ không ăn năn hối lỗi</td>
              <td><span class="badge bg-danger">Sa thải</span></td>
              <td><button class="btn btn-primary btn-sm trash" type="button" title="Xóa"><i class="fas fa-trash-alt"></i></button>
                <button class="btn btn-primary btn-sm edit" type="button" title="Sửa"><i class="fa fa-edit"></i></button></td>
            </tr>
            <tr>
              <td width="10"><input type="checkbox" name="check1" value="1"></td>
              <td>Nguyễn Thị Kim Yến</td>
              <td>02/12/1999</td>
              <td>Thu Ngân</td>
              <td>Gian lận trong công việc đã lấy trộm tiền của công ty và thái độ không ăn năn hối lỗi</td>
              <td><span class="badge bg-danger">Sa thải</span></td>
              <td><button class="btn btn-primary btn-sm trash" type="button" title="Xóa"><i class="fas fa-trash-alt"></i></button>
                <button class="btn btn-primary btn-sm edit" type="button" title="Sửa"><i class="fa fa-edit"></i></button></td>
            </tr>
            <tr>
              <td width="10"><input type="checkbox" name="check1" value="1"></td>
              <td>Nguyễn Hoàng Thái Nhật</td>
              <td>07/02/1996</td>
              <td>Quản kho</td>
              <td>Tự ý thay đổi thông tin nhân viên và không báo cáo quản lý</td>
              <td><span class="badge bg-warning">Khóa tài khoản</span></td>
              <td><button class="btn btn-primary btn-sm trash" type="button" title="Xóa"><i class="fas fa-trash-alt"></i></button>
                <button class="btn btn-primary btn-sm edit" type="button" title="Sửa"><i class="fa fa-edit"></i></button></td>
            </tr>
            <tr>
              <td width="10"><input type="checkbox" name="check1" value="1"></td>
              <td>Võ Thành Nam</td>
              <td>19/09/1999</td>
              <td>Dịch vụ</td>
              <td>Phá hỏng 9 sản phẩm của công ty, có kháng cự khi bị bắt (bồi thường 125.000.000 đ)</td>
              <td><span class="badge bg-danger">Sa thải</span></td>
              <td><button class="btn btn-primary btn-sm trash" type="button" title="Xóa"><i class="fas fa-trash-alt"></i></button>
                <button class="btn btn-primary btn-sm edit" type="button" title="Sửa"><i class="fa fa-edit"></i></button></td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</main>
<!-- Essential javascripts for application to work-->
<jsp:include page="./header/link-js.jsp" flush="true"/>
<script type="text/javascript">$('#sampleTable').DataTable();</script>
<script>
  oTable = $('#sampleTable').dataTable();
  $('#all').click(function (e) {
    $('#sampleTable tbody :checkbox').prop('checked', $(this).is(':checked'));
    e.stopImmediatePropagation();
  });

  //EXCEL
  // $(document).ready(function () {
  //   $('#').DataTable({

  //     dom: 'Bfrtip',
  //     "buttons": [
  //       'excel'
  //     ]
  //   });
  // });


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
  //In dữ liệu
  var myApp = new function () {
    this.printTable = function () {
      var tab = document.getElementById('sampleTable');
      var win = window.open('', '', 'height=700,width=700');
      win.document.write(tab.outerHTML);
      win.document.close();
      win.print();
    }
  }
  //     //Sao chép dữ liệu
  //     var copyTextareaBtn = document.querySelector('.js-textareacopybtn');

  // copyTextareaBtn.addEventListener('click', function(event) {
  //   var copyTextarea = document.querySelector('.js-copytextarea');
  //   copyTextarea.focus();
  //   copyTextarea.select();

  //   try {
  //     var successful = document.execCommand('copy');
  //     var msg = successful ? 'successful' : 'unsuccessful';
  //     console.log('Copying text command was ' + msg);
  //   } catch (err) {
  //     console.log('Oops, unable to copy');
  //   }
  // });


  //Modal
  $("#show-emp").on("click", function () {
    $("#ModalUP").modal({ backdrop: false, keyboard: false })
  });
</script>
</body>
</html>