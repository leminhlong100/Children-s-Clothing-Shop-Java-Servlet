<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:url value="/admin/assets" var="url" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<fmt:setLocale value="${sessionScope.LANG}"/>
<!DOCTYPE html>
<html lang="en">

<head>
  <title>Khôi phục mật khẩu | Website quản trị v2.0</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" type="text/css" href="${url}/css/login.css">
  <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-sweetalert/1.0.1/sweetalert.min.css">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
  <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
  <script src="https://unpkg.com/boxicons@latest/dist/boxicons.js"></script>
  <script src="${url}/js/jquery.min.js"></script>
  <script src="${url}/js/bootstrap.min.js"></script>
  <script src="${url}/js/select2.min.js"></script>
  <script>
    function RegexEmail(emailInputBox) {
      var emailStr = document.getElementById(emailInputBox).value;
      var emailRegexStr = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
      var isvalid = emailRegexStr.test(emailStr);
      if (!isvalid) {
        swal({
          title: "",
          text: "Bạn vui lòng nhập đúng định dạng email...",
          icon: "error",
          close: true,
          button: "Thử lại",
        });

        emailInputBox.focus;
      } else {
        swal({
          title: "",
          text: "Chúng tôi vừa gửi cho bạn email hướng dẫn đặt lại mật khẩu vào địa chỉ cho bạn",
          icon: "success",
          close: true,
          button: "Đóng",
        });
        emailInputBox.focus;
        window.location = "#";
      }
    }
  </script>
</head>

<body>
<div class="limiter">
  <div class="container-login100">
    <div class="wrap-login100">
      <div class="login100-pic js-tilt" data-tilt>
        <img src="${url}/images/fg-img.png" alt="IMG">
      </div>
      <form class="login100-form validate-form">
                    <span class="login100-form-title">
                        <b>KHÔI PHỤC MẬT KHẨU</b>
                    </span>
        <form action="custommer.html">
          <div class="wrap-input100 validate-input"
               data-validate="Bạn cần nhập đúng thông tin như: ex@abc.xyz">
            <input class="input100" type="text" placeholder="Nhập email" name="emailInput"
                   id="emailInput" value="" />
            <span class="focus-input100"></span>
            <span class="symbol-input100">
                                <i class='bx bx-mail-send' ></i>
                            </span>
          </div>
          <div class="container-login100-form-btn">
            <input type="button" onclick="return RegexEmail('emailInput')" value="Lấy mật khẩu" />
          </div>

          <div class="text-center p-t-12">
            <a class="txt2" href="${pageContext.request.contextPath}/admin/admin-login.jsp">
              Trở về đăng nhập
            </a>
          </div>
        </form>
        <div class="text-center p-t-70 txt2">
          Phần mềm quản lý bán hàng <i class="far fa-copyright" aria-hidden="true"></i>
          <script type="text/javascript">document.write(new Date().getFullYear());</script> <a
                class="txt2" href="https://www.facebook.com/truongvo.vd1503/"> Code bởi Trường </a>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>