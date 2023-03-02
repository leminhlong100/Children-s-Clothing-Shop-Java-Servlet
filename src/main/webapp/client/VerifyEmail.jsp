<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");

if (session.getAttribute("acc") != null) {

	response.sendRedirect(request.getContextPath() + "/IndexControl");
}
%>
<fmt:setLocale value="${sessionScope.LANG}" />
<fmt:setBundle basename="web.lang.resource.app" var="lang" />
<!DOCTYPE html>
<html lang="vi">
<script src='https://www.google.com/recaptcha/api.js'></script>

<head>
<title><fmt:message key="log.in.to.your.account"
		bundle="${lang}"></fmt:message></title>
<link rel="icon" type="image" href="../image/HaLoicon.png" />

<jsp:include page="./link/Link.jsp"></jsp:include>
</head>

<body>

	<!-- Load page -->
	<div id="preloder">
		<div class="loader"></div>
	</div>
	<c:url var="login" value="/Login" />
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
						</ol>
					</div>
				</div>
			</div>
		</div>
		<!-- End Main Breadcrumb -->
		<!-- Main Content -->
		<div class="main-content">
			<div class="container">
				<div class="row">
					<div class="col-md-5">
						<div class="form-style form-login">
							<form accept-charset="utf-8" action="SubmitEmail"
								id="VerifyEmailControl" method="post">
								<input name="FormType" type="hidden" value="customer_login"
									required /> <input name="utf8" type="hidden" value="true"
									required />
								<h3 class="form-heading">Xác thực Email</h3>
								<p class="form-description">Vui lòng nhập mã mà chúng tôi đã
									gửi email cho bạn.</p>
								<div class="row">
									<div class="col-md-2">
										<p>
											<fmt:message key="email" bundle="${lang}"></fmt:message>
										</p>
									</div>
									<div class="col-md-10">
										<input type="text" value="${custemp.email}" name=""
											readonly="readonly" />
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<p>Mã xác thực</p>
									</div>
									<div class="col-md-10">
										<input maxlength="8" type="text" value="" name="codeverify" />
									</div>
										<p style="color: red;">
										${timeUp}
									</p>
								</div>

								<div class="g-recaptcha"
									data-sitekey="6LcEOp4kAAAAAKd6WPwrePNY_OkIHz9GO7hDSmcb" data></div>

								<div class="row">
									<p style="color: red;">${error}</p>
								</div>
								<div class="row">
									<div class="col-md-10">
										<button class="btn-cart" type="submit">Xác nhận</button>
									</div>
									<div class="col-md-10">
										<a class="btn-cart"
											href="${pageContext.request.contextPath}/client/Register.jsp">Quay
											lại</a>
									</div>
								</div>
							</form>

						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- End Main Content -->
		<jsp:include page="./footer/Footer.jsp"></jsp:include>
	</div>
</body>

</html>