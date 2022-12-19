<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<fmt:setLocale value="${sessionScope.LANG}" />
<fmt:setBundle basename="web.lang.resource.app" var="lang" />
<!DOCTYPE html>
<html lang="vi">
<head>
<title>HaLo's Shop</title>
<link rel="icon" type="image" href="./image/HaLoicon.png" />
<jsp:include page="./client/link/Link.jsp"></jsp:include>
</head>
<body>
	<!-- Load page -->
	<div id="preloder">
		<div class="loader"></div>
	</div>
	<div class="page">
		<jsp:include page="./client/header/Header.jsp"></jsp:include>
		<h1 class="hidden-xs hidden-sm hidden-md hidden-lg">HaLo's Shop</h1>
		<div class="sk-home-slider"></div>

		<div class="sk-homepage-layout-leftSidebar">
			<div class="container">
				<div class="row">
					<aside class="col-lg-3 col-md-3 hidden-sm hidden-xs">
						<div class="sk-leftSidebar-widget sellingProducts-widget">
							<div class="sk-widget-title pink-bg-widget">
								<h5>
									<fmt:message key="SELLING.PRODUCTS" bundle="${lang}"></fmt:message>
								</h5>
								<div class="customNavigation">
									<a class="btn prev"><i class="fa fa-angle-left"
										aria-hidden="true"></i></a> <a class="btn next"><i
										class="fa fa-angle-right" aria-hidden="true"></i></a>
								</div>
							</div>
							<div class="slider-items-products">
								<div id="sellingProducts-slider" class="owl-carousel owl-theme">
									<div class="item">
										<c:forEach var="o" items="${listSellProduct}">
											<div class="sellingProducts-item">
												<div class="col-md-5 col-sm-5 sellingProducts-image-wrap">
													<c:url var="detail" value="DetailControl"></c:url>
													<a class="sellingProducts-image"
														href="${detail}?pid=${o.id}"> <img src="${o.image}"
														alt="" class="img-responsive">
													</a>
												</div>
												<div class="col-md-7 col-sm-7">
													<div class="item-info">
														<h3 class="item-title">
															<a href="${detail}?pid=${o.id}">${o.name}</a>
														</h3>
														<div class="price-box">
															<span class="price">${o.price} VNĐ</span> <span
																class="price-compare"> ${o.olePrice} VNĐ</span>
														</div>
													</div>
												</div>
												<div class="clearfix"></div>
											</div>
										</c:forEach>
									</div>
									<div class="item">
										<c:forEach var="o" items="${listSellProductTwo}">
											<div class="sellingProducts-item">
												<div class="col-md-5 col-sm-5 sellingProducts-image-wrap">
													<a class="sellingProducts-image"
														href="${detail}?pid=${o.id}"> <img src="${o.image}"
														alt="" class="img-responsive">
													</a>
												</div>
												<div class="col-md-7 col-sm-7">
													<div class="item-info">
														<h3 class="item-title">
															<a href="${detail}?pid=${o.id}">${o.name}</a>
														</h3>
														<div class="price-box">
															<span class="price">${o.price} VNĐ</span> <span
																class="price-compare"> ${o.olePrice} VNĐ</span>
														</div>
													</div>
												</div>
												<div class="clearfix"></div>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
						<!-- End .sellingProducts-widget -->
						<div class="sk-leftSidebar-widget hotline-widget">
							<div class="hotline-widget-inner text-center">
								<span> Hotline </span> <a href="tel:0364677119">
									0364.677.119 </a>
							</div>
						</div>
						<!-- End .hotline-widget -->
					</aside>
					<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12">
						<section class="sk-home-main-block">
							<div class="row">
								<div
									class="col-md-6 col-sm-6 discounts-product-block discounts-product-block-left">
									<div class="main-block-title">
										<h2>
											<fmt:message key="FEATURED.PRODUCTS" bundle="${lang}"></fmt:message>
										</h2>
									</div>
									<div id="discounts-product" class="owl-carousel owl-theme">
										<c:forEach var="o" items="${listOutstandingProduct}">
											<div class="item">
												<div class="discounts-product-item">
													<div class="discounts-product-image">
														<a href="${detail}?pid=${o.id}"> <img src="${o.image}"
															alt="${o.image}" class="img-responsive">
														</a>
													</div>
													<div class="discounts-product-info text-center">

														<div class="price-box">
															<span class="price"> ${o.price} VNĐ</span> <span
																class="price-compare"> ${o.olePrice} VNĐ </span>
														</div>

														<h3 class="item-title">
															<a href="${detail}?pid=${o.id}" title="Váy">${o.title}</a>
														</h3>
														<div class="text-center">
															<form action="/cart/add" method="post" class="variants"
																id="product-actions-3915726"
																enctype="multipart/form-data">
																<a href="${detail}?pid=${o.id}">
																	<button class="button btn-cart btn-more product-atc"
																		title="Mua hàng" type="button">
																		<span><img src="./image/Muangay.png" alt="" /></span>
																	</button>
																</a>
															</form>
														</div>
													</div>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
								<div class="col-md-6 col-sm-6 discounts-product-block-right">
									<div class="row">
										<c:forEach var="o" items="${listOutstandingProductShoes}">
											<div class="col-md-6 col-sm-6">
												<div class="discounts-product-right">
													<div class="discounts-product-image">
														<a href="${detail}?pid=${o.id}"> <img
															src="${o.image }" alt="${o.image }"
															class="img-responsive">
														</a>

														<div class="price-box">
															<span class="price">${o.price}VNĐ</span>
														</div>

													</div>
													<div class="discounts-product-info">
														<h3 class="item-title">
															<a href="${detail}?pid=${o.id}" title="${o.title }">${o.title}</a>
														</h3>
													</div>
													<div class="text-center">
														<form action="/cart/add" method="post" class="variants"
															id="product-actions-3915714"
															enctype="multipart/form-data">
															<a href="${detail}?pid=${o.id}"><button
																	class="button btn-cart btn-more product-atc"
																	title="Mua hàng" type="button">
																	<span><img src="./image/Muangay.png" alt="" /></span>
																</button></a>
														</form>
													</div>
												</div>
											</div>
										</c:forEach>
									</div>
									<div class="clearfix"></div>
									<div class="sk-viewmore-block-green">
										<div class="sk-viewmore-block-inner text-center">
											<c:url var="ShowProduct" value="ShowProductControl" />
											<a href="${ShowProduct}"><span><fmt:message
														key="VIEW.ALL.PRODUCTS" bundle="${lang}"></fmt:message><i
													class="fa fa-arrow-circle-o-right" aria-hidden="true"></i></span></a>
										</div>
									</div>
								</div>
								<div class="clearfix"></div>
							</div>
						</section>
						<section class="sk-home-main-block sk-tab-product">
							<div class="main-block-title pink-title">
								<h2>
									<fmt:message key="NEW.PRODUCTS" bundle="${lang}"></fmt:message>
								</h2>
								<ul class="nav-pills sk-tab-nav">
									<li class="active"><a href="#1b" data-toggle="tab"> <fmt:message
												key="Boys" bundle="${lang}"></fmt:message></a></li>
									<li><i class="fa fa-pagelines" aria-hidden="true"></i></li>
									<li><a href="#2b" data-toggle="tab"> <fmt:message
												key="Girls" bundle="${lang}"></fmt:message></a></li>
									<li><i class="fa fa-pagelines" aria-hidden="true"></i></li>
									<li><a href="#3b" data-toggle="tab"> <fmt:message
												key="Accessory" bundle="${lang}"></fmt:message></a></li>
								</ul>
								<div class="clearfix"></div>
							</div>
							<div id="exTab3"
								class="sk-tab-product sk-block-with-border-triangle">
								<div class="tab-content clearfix">
									<div class="tab-pane active" id="1b">
										<c:forEach var="o" items="${getNewProductBoy }">
											<div class="col-md-3 col-sm-3 col-xs-12">
												<div class="discounts-product-right">
													<div class="discounts-product-image">
														<a href="${detail}?pid=${o.id}"> <img src="${o.image}"
															alt="${o.image}" class="img-responsive">
														</a>

														<div class="price-box">
															<span class="price">${o.price} VNĐ</span> <span
																class="price-compare"> ${o.olePrice} VNĐ</span>
														</div>

													</div>
													<div class="discounts-product-info">
														<h3 class="item-title">
															<a href="${detail}?pid=${o.id}" title="${o.title }">${o.title }</a>
														</h3>
													</div>
													<div class="text-center">
														<form action="/cart/add" method="post" class="variants"
															id="product-actions-3915812"
															enctype="multipart/form-data">
															<a href="${detail}?pid=${o.id}"><button
																	class="button btn-cart btn-more product-atc"
																	title="Mua hàng" type="button">
																	<span><img src="./image/Muangay.png" alt="" /></span>
																</button></a>
														</form>
													</div>
												</div>
											</div>
										</c:forEach>
									</div>

									<div class="tab-pane" id="2b">
										<c:forEach var="o" items="${listNewProductGirl }">
											<div class="col-md-3 col-sm-3 col-xs-12">
												<div class="discounts-product-right">
													<div class="discounts-product-image">
														<a href="${detail}?pid=${o.id}"> <img src="${o.image}"
															alt="${o.image}" class="img-responsive">
														</a>

														<div class="price-box">
															<span class="price">${o.price} VNĐ</span> <span
																class="price-compare"> ${o.olePrice} VNĐ</span>
														</div>

													</div>
													<div class="discounts-product-info">
														<h3 class="item-title">
															<a href="${detail}?pid=${o.id}" title="${o.title }">${o.title }</a>
														</h3>
													</div>
													<div class="text-center">
														<form action="/cart/add" method="post" class="variants"
															id="product-actions-3915812"
															enctype="multipart/form-data">
															<a href="${detail}?pid=${o.id}"><button
																	class="button btn-cart btn-more product-atc"
																	title="Mua hàng" type="button">
																	<span><img src="./image/Muangay.png" alt="" /></span>
																</button></a>
														</form>
													</div>
												</div>
											</div>
										</c:forEach>
									</div>

									<div class="tab-pane" id="3b">
										<c:forEach var="o" items="${listNewProductAccessory }">
											<div class="col-md-3 col-sm-3 col-xs-12">
												<div class="discounts-product-right">
													<div class="discounts-product-image">
														<a href="${detail}?pid=${o.id}"> <img src="${o.image}"
															alt="${o.image}" class="img-responsive">
														</a>

														<div class="price-box">
															<span class="price">${o.price} VNĐ</span> <span
																class="price-compare"> ${o.olePrice} VNĐ</span>
														</div>

													</div>
													<div class="discounts-product-info">
														<h3 class="item-title">
															<a href="${detail}?pid=${o.id}" title="${o.title }">${o.title }</a>
														</h3>
													</div>
													<div class="text-center">
														<form action="/cart/add" method="post" class="variants"
															id="product-actions-3915812"
															enctype="multipart/form-data">
															<a href="${detail}?pid=${o.id}"><button
																	class="button btn-cart btn-more product-atc"
																	title="Mua hàng" type="button">
																	<span><img src="./image/Muangay.png" alt="" /></span>
																</button></a>
														</form>
													</div>
												</div>
											</div>
										</c:forEach>

									</div>
								</div>
							</div>
						</section>
					</div>
				</div>
			</div>
			<div class="sk-parallax-section">
				<div class="sk-parallax-inner">
					<div class="container">
						<div class="sk-parallax-title">
							<span><fmt:message key="Halo's.Shop's.policies"
									bundle="${lang}"></fmt:message></span>
						</div>
						<div class="sk-parallax-list">
							<div id="sk-parallax-carousel" class="">
								<div class="col-md-6 col-sm-6 sk-parallax-item item">
									<div class="sk-parallax-content">
										<h5>
											<a><fmt:message key="Policy.on.buying" bundle="${lang}"></fmt:message></a>
										</h5>
										<div class="sk-parallax-description">
											<fmt:message key="We.send" bundle="${lang}"></fmt:message>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-sm-6 sk-parallax-item item">
									<div class="sk-parallax-content">
										<h5>
											<a><fmt:message key="Shopping.Guide.Online"
													bundle="${lang}"></fmt:message></a>
										</h5>
										<div class="sk-parallax-description">
											<fmt:message key="You.can" bundle="${lang}"></fmt:message>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-sm-6 sk-parallax-item item">
									<div class="sk-parallax-content">
										<h5>
											<a><fmt:message key="Goods.return.policy"
													bundle="${lang}"></fmt:message></a>
										</h5>
										<div class="sk-parallax-description">
											<fmt:message key="We.always" bundle="${lang}"></fmt:message>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-sm-6 sk-parallax-item item">
									<div class="sk-parallax-content">
										<h5>
											<a><fmt:message key="Gifts" bundle="${lang}"></fmt:message></a>
										</h5>
										<div class="sk-parallax-description">
											<fmt:message key="To.express" bundle="${lang}"></fmt:message>
										</div>
									</div>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			</div>

			<div class="sk-product-care-section">
				<div class="container">
					<div class="section-header">
						<div class="product-care-title">
							<h2>
								<fmt:message key="MAYBE" bundle="${lang}"></fmt:message>
							</h2>
						</div>
						<div class="main-block-description">
							<fmt:message key="You.can.find" bundle="${lang}"></fmt:message>
						</div>
					</div>
					<div class="product-care-wrap">
						<div class="productCareNavigation">
							<a class="btn prev"> <img
								src="//bizweb.dktcdn.net/100/117/632/themes/157694/assets/left-control.png?1564585558451"
								alt="left-control" class="img-responsive" />
							</a> <a class="btn next"> <img
								src="//bizweb.dktcdn.net/100/117/632/themes/157694/assets/right-control.png?1564585558451"
								alt="right-control" class="img-responsive" />
							</a>
						</div>
						<div id="product-care" class="owl-carousel owl-theme">
							<c:forEach var="o" items="${listgetSuperSellProduct }">
								<div class="discounts-product-right">
									<div class="discounts-product-image">
										<a href="${detail}?pid=${o.id}"> <img src="${o.image}"
											alt="Váy liên thân KIDS - KF5" class="img-responsive">
										</a>

										<div class="price-box">
											<span class="price">${o.price} VNĐ</span> <span
												class="price-compare"> ${o.olePrice} VNĐ</span>
										</div>

									</div>
									<div class="discounts-product-info">
										<h3 class="item-title">
											<a href="${detail}?pid=${o.id}" title="${o.title }">${o.title }</a>
										</h3>
									</div>
									<div class="text-center">
										<form action="/cart/add" method="post" class="variants"
											id="product-actions-3915726" enctype="multipart/form-data">
											<a href="${detail}?pid=${o.id}"><button
													class="button btn-cart btn-more product-atc"
													title="Mua hàng" type="button">
													<span><img src="./image/Muangay.png" alt="" /></span>
												</button></a>
										</form>
									</div>
								</div>
							</c:forEach>

						</div>
						<!-- #product-care -->
					</div>
					<!-- End .product-care-wrap -->
				</div>
			</div>
		</div>
		<jsp:include page="./client/footer/Footer.jsp"></jsp:include>
	</div>
</body>

</html>