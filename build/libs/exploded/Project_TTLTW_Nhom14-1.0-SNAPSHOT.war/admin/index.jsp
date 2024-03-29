<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.LANG}"/>
<fmt:setBundle basename="app" var="lang"/>
<!-- Start header section -->
<jsp:include page="./header/header.jsp" flush="true" />
<div class="content-wrapper">
	<div class="container-fluid">
		<div class="card mt-3">
			<div class="card-content">
				<div class="row row-group m-0">
					<div class="col-12 col-lg-6 col-xl-3 border-light">
						<div class="card-body">
							<h5 class="text-white mb-0">${map.get("sumbill")}<span
									class="float-right"><i class="fa fa-shopping-cart"></i></span>
							</h5>
							<div class="progress my-3" style="height: 3px;">
								<div class="progress-bar" style="width: 55%"></div>
							</div>
							<p class="mb-0 text-white small-font">
								<fmt:message key="Order.Number" bundle="${lang}"></fmt:message>
								<span class="float-right">+ 4.5% <i
									class="zmdi zmdi-long-arrow-up"></i></span>
							</p>
						</div>
					</div>
					<div class="col-12 col-lg-6 col-xl-3 border-light">
						<div class="card-body">
							<h5 class="text-white mb-0">${map.get("sumprice")}<span
									class="float-right">VNĐ</span>
							</h5>
							<div class="progress my-3" style="height: 3px;">
								<div class="progress-bar" style="width: 55%"></div>
							</div>
							<p class="mb-0 text-white small-font">
								<fmt:message key="Profit" bundle="${lang}"></fmt:message>
								<span class="float-right">+9.5% <i
									class="zmdi zmdi-long-arrow-up"></i></span>
							</p>
						</div>
					</div>
					<div class="col-12 col-lg-6 col-xl-3 border-light">
						<div class="card-body">
							<h5 class="text-white mb-0">
								300 <span class="float-right"><i class="fa fa-eye"></i></span>
							</h5>
							<div class="progress my-3" style="height: 3px;">
								<div class="progress-bar" style="width: 55%"></div>
							</div>
							<p class="mb-0 text-white small-font">
								<fmt:message key="Visit" bundle="${lang}"></fmt:message>
								<span class="float-right">+5.2% <i
									class="zmdi zmdi-long-arrow-up"></i></span>
							</p>
						</div>
					</div>
					<div class="col-12 col-lg-6 col-xl-3 border-light">
						<div class="card-body">
							<h5 class="text-white mb-0">
								${map.get("sumbill")}<span class="float-right"><i
									class="fa fa-envira"></i></span>
							</h5>
							<div class="progress my-3" style="height: 3px;">
								<div class="progress-bar" style="width: 55%"></div>
							</div>
							<p class="mb-0 text-white small-font">
								<fmt:message key="Sold.out" bundle="${lang}"></fmt:message>
								<span class="float-right">+2.2% <i
									class="zmdi zmdi-long-arrow-up"></i></span>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-12 col-lg-4 col-xl-4">
				<div class="card">
					<div class="card-header">
						<fmt:message key="Hot.selling.list" bundle="${lang}"></fmt:message>
					</div>
					<div class="card-body">
						<div class="chart-container-2">
							<canvas id="chart2"></canvas>
						</div>
					</div>
					<div class="table-responsive">
						<table class="table align-items-center">
							<tbody>
								<tr>
									<td><i class="fa fa-circle text-white mr-2"></i>Giày trẻ
										em</td>
									<td>2.358.000 VNĐ</td>
									<td>+55%</td>
								</tr>
								<tr>
									<td><i class="fa fa-circle text-light-1 mr-2"></i>Váy trẻ
										em</td>
									<td>1.900.000 VNĐ</td>
									<td>+25%</td>
								</tr>
								<tr>
									<td><i class="fa fa-circle text-light-2 mr-2"></i>Đồ bộ</td>
									<td>900.500 VNĐ</td>
									<td>+15%</td>
								</tr>
								<tr>
									<td><i class="fa fa-circle text-light-3 mr-2"></i>Đồ chơi</td>
									<td>310.000 VNĐ</td>
									<td>+5%</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="./footer/footer.jsp" flush="true" />