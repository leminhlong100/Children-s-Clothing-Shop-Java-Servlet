<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.LANG}" />
<fmt:setBundle basename="app" var="lang" /><%
response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");

if (session.getAttribute("acc") == null) {
	response.sendRedirect(request.getContextPath() + "/client/Login.jsp");
}
%>
<!DOCTYPE html>
<html lang="vi">
<head>
<title><fmt:message key="account.information" bundle="${lang}"></fmt:message></title>
<link rel="icon" type="image" href="../images/HaLoicon.png" />
<jsp:include page="./link/Link.jsp"></jsp:include>
</head>
<body>
	<!-- Load page -->
	<div id="preloder">
		<div class="loader"></div>
	</div>
	<div class="page">
		<jsp:include page="./header/Header.jsp"></jsp:include>
		<div id="add_succes" style="display: none;">
			<p>
				<i class="fa fa-check fa-2x"></i>
				<fmt:message key="successfully.added.the.product" bundle="${lang}"></fmt:message>
			</p>
		</div>

		<!-- Main Breadcrumb -->
		<div class="main-breadcrumb">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<ol class="breadcrumb">
							<li><a href="#"> <fmt:message key="Home"
										bundle="${lang}"></fmt:message></a></li>
							<li class="active"><fmt:message key="account"
									bundle="${lang}"></fmt:message></li>
						</ol>
					</div>
				</div>
			</div>
		</div>
		<!-- End Main Breadcrumb -->
		<!-- Main Content -->
		<div class="main-content">
			<div class="container">
				<div class="col-sm-3">
					<h3>
						<fmt:message key="account.information" bundle="${lang}"></fmt:message>
					</h3>
					<div class="">
						<form action="${pageContext.request.contextPath}/account/EditControl?editInfo"
							method="post">
							<table class="table table-bordered">
								<tbody>
									<tr>
										<td><fmt:message key="Full.name" bundle="${lang}"></fmt:message>:</td>
										<td class="colum-account"><label for="input-account-name"></label><input class="input-account"
																												 id="input-account-name" type="text"
																												 value="${sessionScope.acc.fullName}" name="name" size="70"
																												 readonly /></td>
                                        <div class="row">
                                            <p style="color: red; display: none;" class="col-md-11"
                                               id="errorName"></p>
                                        </div>
									</tr>

									<tr>
										<td class="colum-account"><fmt:message key="Email"
												bundle="${lang}"></fmt:message>:</td>
										<td><label for="input-account-email"></label><input class="input-account" id="input-account-email"
																							type="text" value="${sessionScope.acc.email}" name="email"
																							readonly size="70" /></td>
									</tr>

									<tr>
										<td class="colum-account"><fmt:message key="Address"
												bundle="${lang}"></fmt:message>:</td>
										<td><label for="input-account-address"></label><textarea name="address" id="input-account-address"
																								 rows="9" cols="70" class="textarea-account" readonly> ${sessionScope.acc.address}
																</textarea></td>
                                        <div class="row">
                                            <p style="color: red; display: none;" class="col-md-11"
                                               id="errorAddress"></p>
                                        </div>
									</tr>

									<tr>
										<td class="colum-account"><fmt:message key="Phone.number"
												bundle="${lang}"></fmt:message>:</td>
										<td><label for="input-account-phoneNumber"></label><input class="input-account" type="text"
																								  id="input-account-phoneNumber"
																								  value="${sessionScope.acc.phone}" size="70"
																								  name="phoneNumber" readonly /></td>
                                        <div class="row">
                                            <p style="color: red; display: none;" class="col-md-11"
                                               id="errorNumberPhone"></p>
                                        </div>
									</tr>
								</tbody>
								<div class="row">
									<p style="color: green;">${sucinfo}</p>
								</div>
							</table>
							<ul class="checkout">
								<li>
									<button id="btn-change-info" type="button" class="btn-cart"
										title="Thay đổi thông tin">
										<span><fmt:message key="Change.information"
												bundle="${lang}"></fmt:message></span>
									</button>
								</li>
								<li>
									<button type="button" id="btn-change-pass" class="btn-cart"
										title="Thay đổi Mật khẩu">
										<span><fmt:message key="Change.the.password"
												bundle="${lang}"></fmt:message></span>
									</button>

								</li>
								<li>
									<button type="submit" id="btn-confirm1" style="display: none;"
										class="btn-cart" title="Cập nhật">
										<span><fmt:message key="update" bundle="${lang}"></fmt:message></span>
									</button>
								</li>
							</ul>
						</form>
						<form
							action="${pageContext.request.contextPath}/account/EditControl?editPassword"
							method="post">
							<table id="table-pass" style="display: none;"
								class="table table-bordered">
								<tbody>
									<tr>
										<td class="colum-account"><fmt:message key="old.password"
												bundle="${lang}"></fmt:message>:</td>
										<td><label>
											<input class="input-account"  type="password" value=""
												name="oldpass" required="required" size="70" />
										</label></td>
									</tr>

									<tr>
										<td class="colum-account"><fmt:message key="new.password"
												bundle="${lang}"></fmt:message>:</td>
										<td><input class="input-account" type="password"
											size="70" value="" id="newpass" name="newpass" required="required" /></td>
									</tr>
									<tr>
										<td class="colum-account"><fmt:message
												key="enter.new.password" bundle="${lang}"></fmt:message>:</td>
										<td class="colum-account"><input size="70"
											class="input-account" type="password" value=""
											name="renewpass" id="renewpass" required="required" /></td>
									</tr>
                                    <div class="row">
                                        <p style="color: red; display: none;" class="col-md-11"
                                           id="errorPass"></p>
                                    </div>
                                    <div class="row">
                                        <p style="color: red;" class="col-md-11" id="errorpass2">${errorpass}</p>
                                    </div>
									<div class="row">
										<p style="color: red;">${error}</p>
									</div>
									<div class="row">
										<p style="color: green;">${suc}</p>
									</div>
								</tbody>
							</table>
							<button type="submit" id="btn-confirm" style="display: none;"
								class="btn-cart" title="Cập nhật">
								<span><fmt:message key="update" bundle="${lang}"></fmt:message></span>
							</button>
						</form>
					</div>
					<!--inner-->
				</div>
			</div>
		</div>
	</div>
	<!-- End Main Content -->
	<jsp:include page="./footer/Footer.jsp"></jsp:include>
	<script type="text/javascript">
		$("#btn-change-pass").click(function() {
			$("#table-pass").css("display", "table");
			$("#btn-change-pass").css("display", "none");
			$("#btn-confirm").css("display", "block");
		});
		$("#btn-change-info").click(function() {
			$("#btn-confirm1").css("display", "block");
			$("#btn-change-info").css("display", "none");
			$("#input-account-name").attr('readonly', false);
			$("#input-account-address").attr('readonly', false);
			$("#input-account-phoneNumber").attr('readonly', false);
		});
        $(document)
            .ready(
                function() {
                    $('#input-account-name')
                        .blur(
                            function() {
                                var name = $('#input-account-name').val();
                                if (name != '') {
                                    if (name.length < 5) {
                                        $('#errorName')
                                            .text(
                                                "<fmt:message key="Please.enter.your.full.name" bundle="${lang}"></fmt:message>");
                                        $('#errorName').css(
                                            "display",
                                            "block");
                                    } else {
                                        $('#errorName')
                                            .text("");
                                        $('#errorName').css(
                                            "display",
                                            "none");
                                    }
                                } else {
                                    $('#errorName')
                                        .text(
                                            "<fmt:message key="Please.enter.full.information" bundle="${lang}"></fmt:message>");
                                    $('#errorName').css(
                                        "display", "block");
                                }
                            });
                    $('#input-account-email')
                        .blur(
                            function() {
                                var email = $('#input-account-email').val();
                                var vnf_regex = /^[a-zA-Z0-9.!#$%&*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
                                if (email != '') {
                                    if (vnf_regex.test(email) == false) {
                                        $('#errorEmail')
                                            .text(
                                                "<fmt:message key="Please.enter.the.correct.email.format.abc@def.jhi" bundle="${lang}"></fmt:message>");
                                        $('#errorEmail').css(
                                            "display",
                                            "block");
                                        $('#erroremail').text("");
                                        $('#erroremail').hide();
                                    } else {
                                        $('#errorEmail').text(
                                            "");
                                        $('#errorEmail').css(
                                            "display",
                                            "none");
                                        $('#erroremail').text("");
                                        $('#erroremail').hide();
                                    }
                                } else {
                                    $('#errorEmail')
                                        .text(
                                            "<fmt:message key="Please.enter.full.information" bundle="${lang}"></fmt:message>");
                                    $('#errorEmail').css(
                                        "display", "block");

                                    $('#erroremail').text("");
                                    $('#erroremail').hide();

                                }
                            });
                    $('#input-account-address')
                        .blur(
                            function() {
                                var address = $('#input-account-address')
                                    .val();
                                if (address != '') {
                                    if (address.length < 25) {
                                        $('#errorAddress')
                                            .text(
                                                "<fmt:message key="Please.enter.the.full.address.house.number,.street.name,.ward,.city,.province" bundle="${lang}"></fmt:message>");
                                        $('#errorAddress').css(
                                            "display",
                                            "block");
                                    } else {
                                        $('#errorAddress')
                                            .text("");
                                        $('#errorAddress').css(
                                            "display",
                                            "none");
                                    }
                                } else {
                                    $('#errorAddress')
                                        .text(
                                            "<fmt:message key="Please.enter.full.information" bundle="${lang}"></fmt:message>");
                                    $('#errorAddress').css(
                                        "display", "block");
                                }
                            });
                    $('#input-account-phoneNumber')
                        .blur(
                            function() {
                                var mobile = $('#input-account-phoneNumber').val();
                                var vnf_regex = /((09|03|07|08|05)+([0-9]{8})\b)/g;
                                if (mobile != '') {
                                    if (vnf_regex.test(mobile) == false
                                        || mobile.length > 10) {
                                        $('#errorNumberPhone')
                                            .text(
                                                "<fmt:message key="Please.enter.the.correct.phone.number.format" bundle="${lang}"></fmt:message>");
                                        $('#errorNumberPhone')
                                            .css("display",
                                                "block");
                                    } else {
                                        $('#errorNumberPhone')
                                            .text("");
                                        $('#errorNumberPhone')
                                            .css("display",
                                                "none");
                                    }
                                } else {
                                    $('#errorNumberPhone')
                                        .text(
                                            "<fmt:message key="Please.enter.full.information" bundle="${lang}"></fmt:message>");
                                    $('#errorNumberPhone').css(
                                        "display", "block");
                                }
                            });
                    $('#newpass')
                        .blur(

                            function() {
                                var pass = $('#newpass').val();
                                var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+[\]{};':"\\|,.<>\/?])[A-Za-z\d!@#$%^&*()_+[\]{};':"\\|,.<>\/?]{8,}$/;
                                if (pass != '') {
                                    if (regex.test(pass) == false
                                        || pass.length < 8) {
                                        $('#errorPass')
                                            .text(
                                                "<fmt:message key="Please.enter.a.password.with.at.least.1.uppercase.and.1.numeric.character" bundle="${lang}"></fmt:message>");
                                        $('#errorPass').show();

                                    } else {
                                        $('#errorPass')
                                            .text("");
                                        $('#errorPass').hide();

                                    }
                                } else {
                                    $('#errorPass')
                                        .text(
                                            "<fmt:message key="Please.enter.full.information" bundle="${lang}"></fmt:message>");
                                    $('#errorPass').show();

                                    $('#errorpass2').text("");
                                    $('#errorpass2').hide();
                                }
                            });
                    $('#renewpass')
                        .blur(
                            function() {
                                var repass = $('#renewpass').val();
                                var pass = $('#pass').val();
                                if (repass != '') {
                                    if (repass != pass) {
                                        $('#errorRepass')
                                            .text(
                                                "<fmt:message key="Please.enter.the.matching.password" bundle="${lang}"></fmt:message>");
                                        $('#errorRepass').css(
                                            "display",
                                            "block");
                                    } else {
                                        $('#errorRepass').text(
                                            "");
                                        $('#errorRepass').css(
                                            "display",
                                            "none");
                                    }
                                } else {
                                    $('#errorRepass')
                                        .text(
                                            "<fmt:message key="Please.enter.full.information" bundle="${lang}"></fmt:message>");
                                    $('#errorRepass').css(
                                        "display", "block");
                                }
                            });
                });
	</script>
</body>
</html>