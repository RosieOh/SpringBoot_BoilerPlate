<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path0" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%--<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">--%>
    <link rel="stylesheet" href="${path0}/resource/css/pure-min.css">
    <link rel="stylesheet" href="${path0}/resource/css/grids-responsive-min.css">
    <link rel="stylesheet" href="${path0}/resource/css/styles.css">
    <title>회원 상세보기</title>
</head>
<body>
<div class="pure-menu pure-menu-horizontal">
    <a href="#" class="pure-menu-heading">Your Logo</a>
    <ul class="pure-menu-list">
        <li class="pure-menu-item"><a href="#" class="pure-menu-link">Home</a></li>
        <li class="pure-menu-item pure-menu-selected"><a href="#" class="pure-menu-link">Pricing</a></li>
        <li class="pure-menu-item"><a href="#" class="pure-menu-link">Contact</a></li>
    </ul>
</div>
<div class="banner">
    <h1 class="banner-head">
        <h2>회원 상세보기</h2>
    </h1>
</div>
<div class="l-content" style="width:1280px;margin:20px auto;">
    <table class="pure-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>REGDATE</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>${user.name}</td>
                <td>${user.username}</td>
                <td>${user.regdate}</td>
            </tr>
        </tbody>
    </table>
</div>
<div class="l-content">
    <button type="button" onclick="location.href='update'">수정하기</button>

    <form action="/logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <button type="submit">로그아웃</button>
    </form>

    <form action="/delete" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <button type="submit">탈퇴하기</button>
    </form>
</div>
<div class="footer l-box">
    <p>
        <a href="#">Try now</a> for 14 days. No credit card required. Header image courtesy of <a href="http://unsplash.com/">Unsplash</a>.
    </p>
</div>
</body>
</html>