<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path0" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메인 페이지</title>
</head>
<body>
    <h2>메인 페이지</h2>
    <nav>
        <a href="${paht0}/login.do">로그인 - POST 테스트</a>
        <a href="${paht0}/join.do">회원가입 - POST 테스트</a>
        <a href="${paht0}/logout.do">로그아웃</a>
        <a href="${paht0}/updateForm.do?name=kim">회원정보수정 - PUT 테스트</a>
        <a href="${paht0}/user.do?name=kim">회원정보 - GET 테스트</a>
        <a href="${paht0}/userList.do">회원목록 - GET 테스트</a>
        <a href="${path0}/removeUser.do/lee">회원정보삭제 - DELETE 테스트</a>
    </nav>
    <c:if test="${not empty msg}">
        <p>${msg}</p>
    </c:if>
</body>
</html>
