<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.LANG}"/>
<fmt:setBundle basename="app" var="lang"/>
<%
    response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");

    if (session.getAttribute("admin") == null) {
        response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
    }
%>
<!-- Start header section -->
<jsp:include page="./header/header.jsp" flush="true"/>
<div class="content-wrapper">
    <div class="container-fluid">

        <div class="row mt-3">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">
                            <fmt:message key="user.management" bundle="${lang}"></fmt:message>
                        </h5>
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th scope="col"><fmt:message key="number"
                                                                 bundle="${lang}"></fmt:message></th>
                                    <th scope="col"><fmt:message key="Full.name"
                                                                 bundle="${lang}"></fmt:message></th>
                                    <th scope="col">Email</th>
                                    <th scope="col"><fmt:message key="Phone.number"
                                                                 bundle="${lang}"></fmt:message></th>
                                    <th scope="col"><fmt:message key="Address"
                                                                 bundle="${lang}"></fmt:message></th>
                                    <c:set var="isDisplayed" value="false"/>
                                    <c:forEach var="o" items="${sessionScope.admin.roles}">
                                        <c:if test="${o.name.equals('admin')}">
                                            <c:if test="${!isDisplayed}">
                                                <th scope="col">
                                                    <fmt:message key="act" bundle="${lang}"/>
                                                </th>
                                                <c:set var="isDisplayed" value="true"/>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.accounts}" var="o" varStatus="stt">
                                    <tr>
                                        <td scope="row">${stt.index+1}</td>
                                        <td>${o.fullName }</td>
                                        <td>${o.email }</td>
                                        <td>${o.phone }</td>
                                        <td>${o.address }</td>
                                        <td>
                                            <c:set var="isDisplayed1" value="false"/>
                                            <c:forEach var="role" items="${sessionScope.admin.roles}">
                                                <c:if test="${role.name.equals('admin')}">
                                                    <c:if test="${!isDisplayed1}">
                                                        <%--                                                        <a--%>
                                                        <%--                                                                href="${pageContext.request.contextPath}/admin-user/UserDelete?uid=${o.id}">--%>
                                                        <button class="btn btn-danger" onclick="deleteUser(${o.id})">
                                                            <fmt:message key="delete"
                                                                         bundle="${lang}"></fmt:message>
                                                        </button>
                                                        <%--                                                        </a>--%>
                                                        <a
                                                                href="${pageContext.request.contextPath}/admin-user/UserUpdate?uid=${o.id}">
                                                            <button class="btn btn-success">
                                                                <fmt:message key="edit" bundle="${lang}"></fmt:message>
                                                            </button>
                                                        </a>
                                                        <c:set var="isDisplayed1" value="true"/>
                                                    </c:if>
                                                </c:if>
                                            </c:forEach></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <script>
                    function deleteUser(id) {
                        let uid = id;
                        $.ajax({
                            url: "${pageContext.request.contextPath}/admin-user/UserDelete",
                            type: "POST",
                            data: {
                                uid: uid
                            },
                            success: function (data) {
                      let listAcc   =  JSON.parse(data).listAccount;
                                    for(let i =0;i<listAcc.length;i++){
                                        console.log(listAcc[i])
                                    }
                            },
                            error: function (data) {
                                // xử lý lỗi nếu có
                            }
                        });
                    }
                </script>
            </div>
        </div>
        <div class="overlay toggle-menu"></div>
    </div>
</div>
<jsp:include page="./footer/footer.jsp" flush="true"/>