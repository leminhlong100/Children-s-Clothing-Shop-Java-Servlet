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
							<form accept-charset="utf-8"
								action="${login}?${pageContext.request.queryString}"
								id="customer_login" method="post">
								<input name="FormType" type="hidden" value="customer_login" required />
								<input name="utf8" type="hidden" value="true"  required/>
								<h3 class="form-heading">
									<fmt:message key="log.in" bundle="${lang}"></fmt:message>
								</h3>
								<p class="form-description">
									<fmt:message key="If.you.have.an.account,.please.login."
										bundle="${lang}"></fmt:message>
								</p>
								<div class="row">
									<div class="col-md-2">
										<p>
											<fmt:message key="email" bundle="${lang}"></fmt:message>
										</p>
									</div>
									<div class="col-md-10">
										<input type="email" value="" name="email" />
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<p>
											<fmt:message key="password" bundle="${lang}"></fmt:message>
										</p>
									</div>
									<div class="col-md-10">
										<input type="password" value="" name="password" />
									</div>
								</div>
								<div class="row">
									<p style="color: red;">${error}</p>
								</div>
								<div class="row">
									<div class="col-md-10">
										<button class="btn-cart" type="submit">
											<fmt:message key="log.in" bundle="${lang}"></fmt:message>
										</button>
									</div>
									<div class="col-md-10">
										<a class="btn-cart"
											href="${pageContext.request.contextPath}/client/Register.jsp"><fmt:message
												key="register" bundle="${lang}"></fmt:message></a>
									</div>
								</div>
							</form>
							<script>
								function loginFacebook() {
									var a = {
										client_id : "947410958642584",
										redirect_uri : "https://store.mysapo.net/account/facebook_account_callback",
										state : JSON.stringify({
											redirect_url : window.location.href
										}),
										scope : "email",
										response_type : "code"
									}, b = "https://www.facebook.com/v3.2/dialog/oauth"
											+ encodeURIParams(a, !0);
									window.location.href = b
								}
								function loginGoogle() {
									var a = {
										client_id : "997675985899-pu3vhvc2rngfcuqgh5ddgt7mpibgrasr.apps.googleusercontent.com",
										redirect_uri : "https://store.mysapo.net/account/google_account_callback",
										scope : "email profile https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile",
										access_type : "online",
										state : JSON.stringify({
											redirect_url : window.location.href
										}),
										response_type : "code"
									}, b = "https://accounts.google.com/o/oauth2/v2/auth"
											+ encodeURIParams(a, !0);
									window.location.href = b
								}
								function encodeURIParams(a, b) {
									var c = [];
									for ( var d in a)
										if (a.hasOwnProperty(d)) {
											var e = a[d];
											null != e
													&& c
															.push(encodeURIComponent(d)
																	+ "="
																	+ encodeURIComponent(e))
										}
									return 0 == c.length ? "" : (b ? "?" : "")
											+ c.join("&")
								}
							</script>
							<a href="javascript:void(0)" class="social-login--facebook"
								onclick="loginFacebook()"><img width="129px" height="37px"
								alt="facebook-login-button"
								src="//bizweb.dktcdn.net/assets/admin/images/login/fb-btn.svg"></a>
							<a href="javascript:void(0)" class="social-login--google"
								onclick="loginGoogle()"><img width="129px" height="37px"
								alt="google-login-button"
								src="//bizweb.dktcdn.net/assets/admin/images/login/gp-btn.svg"></a>
						</div>
					</div>
					<div class="col-md-2"></div>
					<div class="col-md-5">
						<div class="form-style form-login">
							<c:url var="foget" value="/ForgetPasswordController" />
							<form accept-charset="utf-8" action="${foget}"
								id="recover_customer_password" method="post">
								<input name="FormType" type="hidden"
									value="recover_customer_password" /> <input name="utf8"
									type="hidden" value="true" />
								<h3 class="form-heading">
									<fmt:message key="forgot.password" bundle="${lang}"></fmt:message>
								</h3>
								<p class="form-description">
									<fmt:message
										key="You.already.have.an.account.but.don't.remember.the.password."
										bundle="${lang}"></fmt:message>
								</p>
								<p>
									<fmt:message
										key="Please.enter.your.Email.below.and.receive.information.via.Email.to.be.able.to.retrieve.your.password."
										bundle="${lang}"></fmt:message>
								</p>
								<p>
									<fmt:message key="Email" bundle="${lang}"></fmt:message>
								</p>
								<input type="email" value="${emailNot}" name="Email" />
								<button type="submit" class="btn-cart">
									<fmt:message key="send.information" bundle="${lang}"></fmt:message>
								</button>
							</form>
							<div class="row">
									<p style="color: red;">${errorForget}</p>
							</div>
							<div class="row">
									<p style="color: green;">${sucForget}</p>
							</div>
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