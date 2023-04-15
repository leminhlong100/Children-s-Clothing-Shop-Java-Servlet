
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>

<script>
    const socket = new WebSocket("ws://localhost:8080/haloshop/observer");
    socket.addEventListener("message", (event) => {
        let data = JSON.parse(event.data);
        let idUser = data.idUser;
        let type = data.type;
        if(type==="remove" && ${sessionScope.acc.id} === idUser){
            $.ajax({
                url : "${pageContext.request.contextPath}/client/NotifyAccount",
                type : "post",
                data : {
                    idUser : idUser,
                },
                success : function(data) {
                    window.location.href = "${pageContext.request.contextPath}/IndexControl";
                    Swal.fire('Xóa user thành công', '', 'success');
                    console.log(data);

                },
                error : function(data) {
                    console.log("error")
                }
            });
        }


    });
</script>
