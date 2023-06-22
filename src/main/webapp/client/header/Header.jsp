<%@ page import="entity.OrderDetail" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html;UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.LANG}"/>
<fmt:setBundle basename="app" var="lang"/>
<script type="text/javascript">
    function googleTranslateElementInit() {
        var lang = getCookie("selectedLanguage");
        if (lang) {
            new google.translate.TranslateElement({
                pageLanguage: 'vi',
                includedLanguages: 'en,vi,ko,kor,ja',
                layout: google.translate.TranslateElement.InlineLayout.SIMPLE,
                autoDisplay: true
            }, 'google_translate_element');
            // Đặt ngôn ngữ đã lưu trữ
            googleTranslateElement.setSelectedLanguage(lang);
        }
        else {
            // Nếu không có cookie, sử dụng ngôn ngữ mặc định là tiếng Anh
            new google.translate.TranslateElement({
                pageLanguage: 'vi',
                includedLanguages: 'en,vi',
                layout: google.translate.TranslateElement.InlineLayout.SIMPLE,
                autoDisplay: true
            }, 'google_translate_element');
        }
    }

    // Hàm lấy giá trị của cookie
    function getCookie(name) {
        var cookieArr = document.cookie.split(";");
        for (var i = 0; i < cookieArr.length; i++) {
            var cookiePair = cookieArr[i].split("=");
            if (name === cookiePair[0].trim()) {
                return decodeURIComponent(cookiePair[1]);
            }
        }
        return null;
    }

    // Hàm đặt giá trị của cookie
    function setCookie(name, value, days) {
        var expires = "";
        if (days) {
            var date = new Date();
            date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
            expires = "; expires=" + date.toUTCString();
        }
        document.cookie = name + "=" + encodeURIComponent(value) + expires + "; path=/";
    }

    // Hàm lưu trạng thái ngôn ngữ vào cookie khi người dùng thay đổi
    function saveSelectedLanguage(lang) {
        setCookie("selectedLanguage", lang, 365); // Lưu trong 1 năm
    }

</script>
<script type="text/javascript"
        src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
<!-- Header -->
<!-- Messenger Plugin chat Code -->
<div id="fb-root"></div>

<!-- Your Plugin chat code -->
<div id="fb-customer-chat" class="fb-customerchat">
</div>

<script>
    var chatbox = document.getElementById('fb-customer-chat');
    chatbox.setAttribute("page_id", "102999359507848");
    chatbox.setAttribute("attribution", "biz_inbox");
</script>

<!-- Your SDK code -->
<script>
    window.fbAsyncInit = function() {
        FB.init({
            xfbml            : true,
            version          : 'v17.0'
        });
    };

    (function(d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s); js.id = id;
        js.src = 'https://connect.facebook.net/vi_VN/sdk/xfbml.customerchat.js';
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));
</script>
<header class="header">
    <div class="navbar navbar-default sk-top-menu" role="navigation">
        <div class="container">
            <div class="row">
                <ul class="nav navbar-nav navbar-left sk-top-nav-left"
                    style="position: relative; z-index: 9;">
                    <c:url var="logout" value="/LogoutControl"></c:url>
                    <c:if test="${sessionScope.acc != null}">
                        <li><a href="${pageContext.request.contextPath}/client/Account.jsp"><i
                                class="fa fa-user"></i> <fmt:message key="Hello"
                                                                     bundle="${lang}"></fmt:message>: ${sessionScope.acc.fullName}
                        </a></li>
                        <li><a href="${logout}"><i class="fa fa-sign-out"></i> <fmt:message
                                key="log.out" bundle="${lang}"></fmt:message> </a></li>
                    </c:if>
                    <c:if test="${empty sessionScope.acc}">
                        <li><a href="${pageContext.request.contextPath}/client/Login.jsp"><i class="fa fa-user"></i>
                            <fmt:message
                                    key="log.in" bundle="${lang}"></fmt:message> </a></li>
                        <li><a href="${pageContext.request.contextPath}/client/Register.jsp"><i
                                class="fa fa-user-plus"></i>
                            <fmt:message key="Register" bundle="${lang}"></fmt:message></a></li>
                    </c:if>

                </ul>
                <ul class="nav navbar-nav navbar-right sk-top-nav-desktop"
                    style="position: relative; z-index: 9;">
                    <li>
                        <%--                        <c:set var="query" value="${pageContext.request.queryString}"></c:set>--%>
                        <%--                        <c:if test="${param.lang==null}">--%>
                        <%--                            <c:if test="${sessionScope.LANG == 'en_US' || sessionScope.LANG == null}"><a--%>
                        <%--                                    href="?${query}&&lang=vi_VN">Tiếng Việt</a></c:if>--%>
                        <%--                            <c:if test="${sessionScope.LANG == 'vi_VN' }"><a--%>
                        <%--                                    href="?${query}&&lang=en_US">English</a></c:if>--%>
                        <%--                        </c:if>--%>
                        <%--                        <c:if test="${param.lang!=null}">--%>
                        <%--                            <c:if test="${sessionScope.LANG == 'en_US' }"><a--%>
                        <%--                                    href="?${fn:substring(query, 0, query.length()-12)}&&lang=vi_VN">Tiếng--%>
                        <%--                                Việt</a></c:if>--%>
                        <%--                            <c:if test="${sessionScope.LANG == 'vi_VN' }"><a--%>
                        <%--                                    href="?${fn:substring(query, 0, query.length()-12)}&&lang=en_US">English</a></c:if>--%>
                        <%--                        </c:if>--%>
                        <div id="google_translate_element" style="margin-top: 11px"></div>
                    </li>
                    <li><c:url var="searchct" value="SearchControl"></c:url>
                        <form action="${pageContext.request.contextPath}/${searchct}" method="get"
                              class="form-inline sk-search-in-nav">
                            <div class="form-group">

                                <input type="text" class="form-control sk-search-field"
                                       placeholder="<fmt:message key="Find" bundle="${lang}"></fmt:message>"
                                       value="${requestScope.search}" name="search">
                            </div>
                            <button type="submit" class="sk-search-btn">
                                <i class="fa fa-search"></i>
                            </button>
                        </form>
                    </li>
                    <c:url var="cart" value="cart/CartControl"></c:url>
                    <li><a href="${pageContext.request.contextPath}/${cart}"><i
                            class="fa fa-shopping-cart"></i> <span class="cart-icon"><c:if
                            test="${!empty sessionScope.cart }">${sessionScope.cartTotalQuantity}</c:if>
								<c:if test="${empty sessionScope.cart }">0</c:if></span></a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="mobile-logo-wrap text-center">
        <c:url var="index" value="IndexControl"></c:url>
        <a class="" href="${pageContext.request.contextPath}/${index}"> <img
                src="${pageContext.request.contextPath}/images/logo.png"
                alt="HaLoShop">
        </a>
    </div>
    <nav class="navbar navbar-default sk-main-menu" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span> <span
                        class="icon-bar"></span> <span class="icon-bar"></span> <span
                        class="icon-bar"></span>
                </button>
                <ul class="sk-top-nav-mobile"
                    style="position: relative; z-index: 9;">
                    <li>
                        <form action="${searchct}" method="get"
                              class="form-inline sk-search-in-nav">
                            <div class="form-group sk-search-input">
                                <input id="sk-input" type="text"
                                       class="form-control sk-search-field"
                                       placeholder="<fmt:message key="Find" bundle="${lang}"></fmt:message>"
                                       value="${requestScope.search}" name="query">
                            </div>
                            <button id="sk-button" type="button" class="sk-search-btn">
                                <i class="fa fa-search"></i>
                            </button>
                        </form>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/${cart}"><i
                            class="fa fa-shopping-cart"></i> <span class="cart-icon"><c:if
                            test="${!empty sessionScope.cart }">${sessionScope.cartTotalQuantity}</c:if>
								<c:if test="${empty sessionScope.cart }">0</c:if></span></a></li>

                </ul>
            </div>
            <a class="navbar-brand sk-logo-desktop"
               href="${pageContext.request.contextPath}/${index}"> <img
                    src="${pageContext.request.contextPath}/images/logo.png"
                    alt="Kidshop">
            </a>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse sk-main-menu-inner"
                 id="navbar-collapse-1">

                <ul class="nav navbar-nav navbar-left">
                    <li class="active"><a
                            href="${pageContext.request.contextPath}/${index}"><fmt:message
                            key="Home" bundle="${lang}"></fmt:message></a></li>

                    <li class="">
                        <a href="${pageContext.request.contextPath}/client/Introduce.jsp">
                            <%--                            <fmt:message--%>
                            <%--                                key="Introduce" bundle="${lang}"></fmt:message>--%>
                            GIỚI THIỆU
                        </a>
                    </li>
                    <li class="">
                        <a href="${pageContext.request.contextPath}/client/Service.jsp"><fmt:message
                                key="service" bundle="${lang}"></fmt:message></a></li>

                </ul>

                <ul class="nav navbar-nav navbar-right">

                    <li class=" dropdown"><a href="#" class="dropdown-toggle"
                                             data-toggle="dropdown"><fmt:message key="product"
                                                                                 bundle="${lang}"></fmt:message><b
                            class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <c:url var="showProduct" value="ShowProductControl"></c:url>
                            <li class=""><a
                                    href="${pageContext.request.contextPath}/${showProduct}?cid=0"><fmt:message
                                    key="All.products" bundle="${lang}"></fmt:message></a></li>

                            <li class=""><a
                                    href="${pageContext.request.contextPath}/${showProduct}?cid=1"><fmt:message
                                    key="Boy's.corner" bundle="${lang}"></fmt:message></a></li>

                            <li class=""><a
                                    href="${pageContext.request.contextPath}/${showProduct}?cid=2"><fmt:message
                                    key="Girl's.corner" bundle="${lang}"></fmt:message></a></li>

                            <li class=""><a
                                    href="${pageContext.request.contextPath}/${showProduct}?cid=3"><fmt:message
                                    key="Accessory" bundle="${lang}"></fmt:message></a></li>

                            <li class=""><a
                                    href="${pageContext.request.contextPath}/${showProduct}?cid=4"><fmt:message
                                    key="Promotion" bundle="${lang}"></fmt:message></a></li>
                            <li class=""><a
                                    href="${pageContext.request.contextPath}/${showProduct}?cid=5"><fmt:message
                                    key="Toys" bundle="${lang}"></fmt:message></a></li>
                        </ul>
                    </li>

                    <li class=""><a
                            href="https://vnexpress.net/tag/thoi-trang-tre-em-92352"><fmt:message
                            key="news" bundle="${lang}"></fmt:message></a></li>

                    <li class="">
                        <a href="${pageContext.request.contextPath}/client/Contact.jsp"><fmt:message
                                key="Contact" bundle="${lang}"></fmt:message></a></li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- End .container -->
    </nav>
    <!-- End .sk-main-menu -->
</header>
<script type="text/javascript">
    $("#languageSelectFilter").on("change", function () {
        $("#dichButton").click();
    })
</script>