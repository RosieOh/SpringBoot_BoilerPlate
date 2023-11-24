<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/>
</sec:authorize>
<c:set var="path0" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
    <meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
    <script src="https://code.jquery.com/jquery-latest.js"></script>
    <link rel="stylesheet" href="${path0}/resource/css/pure-min.css">
    <link rel="stylesheet" href="${path0}/resource/css/grids-responsive-min.css">
    <link rel="stylesheet" href="${path0}/resource/css/styles.css">
    <title>메인 페이지</title>
</head>
<body>
    <h1>메인 페이지</h1>
<c:if test="${empty principal}">
    <button type="button" onclick="location.href='${path0}/signup'" class="pure-button pure-button-primary">가입하기</button>
    <a href="${path0}/login" class="pure-button pure-button-primary">로그인</a>
</c:if>
<c:if test="${not empty principal}">
    <button type="submit" class="pure-button pure-button-primary" onclick="location.href='${path0}/logout'">로그아웃</button>
    <form action="${path0}/delete" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="hidden" name="name" value="${principal}" />
        <button type="submit" class="pure-button pure-button-primary">탈퇴하기</button>
    </form>
    <button type="button" onclick="location.href='${path0}/update?name=${principal}'" class="pure-button pure-button-primary">수정하기</button>
</c:if>
</body>
</html>
