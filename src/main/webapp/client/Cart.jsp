<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.LANG}"/>
<fmt:setBundle basename="app" var="lang"/>
<!DOCTYPE html>
<html lang="vi">
<%
    response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");

    if (session.getAttribute("acc") == null) {
        response.sendRedirect(request.getContextPath() + "/client/Login.jsp");
    }
%>
<head>
    <meta charset="UTF-8"/>
    <title><fmt:message key="Cart" bundle="${lang}"></fmt:message></title>
    <link rel="icon" type="image" href="../images/HaLoicon.png"/>

    <jsp:include page="./link/Link.jsp"></jsp:include>
</head>


<body>
<div class="page">
    <jsp:include page="./header/Header.jsp"></jsp:include>

    <!-- Main Content -->
    <div class="main-content">
        <div class="container" id="containerId">
            <div class="row cart cart_table">

                <h4>
                    <fmt:message key="Cart" bundle="${lang}"></fmt:message>
                </h4>
                <div class="col-md-12">
                    <div class="table-responsive">
                        <c:if test="${!empty sessionScope.cart}">
                            <table class="table table-bordered cart-table">
                                <thead>
                                <tr>
                                    <th class="text-center"><fmt:message key="PICTURE"
                                                                         bundle="${lang}"></fmt:message></th>
                                    <th class="text-center"><fmt:message
                                            key="PRODUCT'S.NAME" bundle="${lang}"></fmt:message></th>
                                    <th class="text-center"><fmt:message key="UNITPRICE"
                                                                         bundle="${lang}"></fmt:message></th>
                                    <th class="text-center"><fmt:message key="AMOUNT"
                                                                         bundle="${lang}"></fmt:message></th>
                                    <th class="text-center"><fmt:message key="INTO.MONEY"
                                                                         bundle="${lang}"></fmt:message></th>
                                    <th class="text-center"><fmt:message key="ERASE"
                                                                         bundle="${lang}"></fmt:message></th>
                                </tr>
                                </thead>
                                <c:forEach items="${sessionScope.cart.entrySet()}" var="entry">
                                    <c:forEach items="${entry.getValue()}" var="o">
                                        <tbody>
                                        <tr>
                                            <td class="text-center"><a
                                                    href="DetailControl?pid=${o.product.id}"> <img
                                                    style="width: 73px; height: auto;"
                                                    src="./images/${o.product.imageProducts.get(0).getImage()}">
                                            </a></td>
                                            <td class="text-center" valign="middle">
                                                <p class="">
                                                    <a href="DetailControl?pid=${o.product.id}">
                                                            ${o.product.nameProduct}</a>
                                                    <br>${o.productSize}/${o.productColor}
                                                </p>
                                            </td>
                                            <td class="text-center">

                                                <p class="">${o.price }
                                                    <fmt:message key="$" bundle="${lang}"></fmt:message>
                                                </p>
                                            </td>
                                            <td class="text-center"><c:url var="minus"
                                                                           value="MinusBillProductControl"></c:url> <a
                                                    href="${minus }?key=${entry.key}&size=${o.productSize}&color=${o.productColor}">
                                                <button style="border-radius: 50%;background-color: white" name="minus"
                                                        value="minus" type="button">-
                                                </button>
                                            </a><input style="margin-left: 5px" type="text"
                                                       class="item-quantity" value="${o.quantity} "
                                                       name="Lines" id="updates_6383545" min="1" readonly>
                                                <c:url var="add" value="AddBillProductControl"></c:url><a
                                                        href="${add}?key=${entry.key}&size=${o.productSize}&color=${o.productColor}">
                                                    <button style="border-radius: 50%;background-color: white"
                                                            name="add"
                                                            value="add" type="button">+
                                                    </button>
                                                </a></td>
                                            <td class="text-center"><p class="l">
                                                <c:set var="sumALl"
                                                       value="	${o.quantity * o.price}"></c:set>
                                                    ${o.quantity * o.price}
                                            </p></td>
                                            <c:url var="delete" value="DeleteBillControl"></c:url>
                                            <td class="text-center"><a
                                                    class="fa fa-trash-o item-remove"
                                                    href="${delete}?key=${entry.key}&size=${o.productSize}&color=${o.productColor}"></a></td>
                                        </tr>
                                        </tbody>
                                    </c:forEach>
                                </c:forEach>
                            </table>
                        </c:if>
                    </div>
                </div>
                <c:if test="${!empty sessionScope.cart }">
                    <div class="col-md-9 col-xs-12">

                        <a href="IndexControl" class="btn-cart next"> <fmt:message
                                key="BUY.MORE" bundle="${lang}"></fmt:message></a>
                    </div>


                    <div class="col-md-3 col-xs-12">
                        <table class="table table-bordered total-table">
                            <tr>
                                <td class="text-right"><fmt:message key="Total.amount"
                                                                    bundle="${lang}"></fmt:message></td>
                                <td class="text-right">${total}<fmt:message key="$"
                                                                              bundle="${lang}"></fmt:message></td>
                            </tr>
                        </table>
                        <c:url var="payment" value="Payment.jsp"></c:url>
                        <a href="${pageContext.request.contextPath}/${payment}"
                           class="btn-cart pull-right"><fmt:message
                                key="payment.methods" bundle="${lang}"></fmt:message></a>
                        <c:url var="order" value="OrderBillControl"></c:url>
                        <a href="${order}?total=${total}" class="btn-cart pull-right"><fmt:message
                                key="pay" bundle="${lang}"></fmt:message></a>
                    </div>
                </c:if>
                <c:if test="${empty sessionScope.cart }">
                    <p>
                        <fmt:message key="There.are.no.products.in.the.cart"
                                     bundle="${lang}"></fmt:message>
                    </p>
                </c:if>
                <h4>
                    <fmt:message key="Purchase.history" bundle="${lang}"></fmt:message>
                </h4>
                <div class="col-md-12">
                    <div class="table-responsive">
                        <table class="table table-bordered cart-table">
                            <thead>
                            <tr>
                                <th class="text-center"><fmt:message key="STT"
                                                                     bundle="${lang}"></fmt:message></th>
                                <th class="text-center"><fmt:message
                                        key="LIST.OF.PRODUCTS" bundle="${lang}"></fmt:message></th>
                                <th class="text-center"><fmt:message key="TOTAL.PRICE"
                                                                     bundle="${lang}"></fmt:message></th>
                                <th class="text-center"><fmt:message key="PURCHASE.DATE"
                                                                     bundle="${lang}"></fmt:message></th>
                                <th class="text-center"><fmt:message key="ORDER.STATUS"
                                                                     bundle="${lang}"></fmt:message></th>
                                <th class="text-center"><fmt:message key="DETAIL"
                                                                     bundle="${lang}"></fmt:message></th>
                            </tr>
                            </thead>
                            <c:forEach items="${listOrders}" var="o" varStatus="stt">
                                <tbody>
                                <tr>
                                    <td class="text-center" valign="middle">
                                        <p class="">${stt.index}</p>
                                    </td>
                                    <c:forEach var="h" items="${products}">
                                        <c:if test="${stt.index == h.key}">
                                            <td class="text-center" scope="row"><c:forEach var="p"
                                                                                           items="${h.value}">
                                                <p style="font-weight: 500; color: black;">${p.product.nameProduct}</p>
                                            </c:forEach></td>
                                        </c:if>
                                    </c:forEach>

                                    <td class="text-center"><p class="">${o.totalPrice}
                                        <fmt:message
                                                key="$" bundle="${lang}"></fmt:message>
                                    </p></td>
                                    <td class="text-center" valign="middle">
                                        <p class="">${o.createAt}</p>
                                    </td>
                                    <td class="text-center" valign="middle">
                                        <p class="">${o.status}</p>
                                    </td>
                                    <c:url value="DetailBill" var="DetailBill"></c:url>
                                    <td class="text-center" valign="middle"><a
                                            style="color: green; text-decoration: underline;"
                                            href="${DetailBill}?id=${o.id}"> <fmt:message
                                            key="click.to.view" bundle="${lang}"></fmt:message></a></td>
                                </tr>
                                </tbody>
                            </c:forEach>
                        </table>

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