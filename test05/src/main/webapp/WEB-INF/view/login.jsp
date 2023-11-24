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
        <h2>회원 로그인</h2>
    </h1>
</div>
<div class="l-content" style="width:1280px;margin:20px auto;">
    <h2 style="text-align: center;">회원 로그인</h2>
    <form class="pure-form pure-form-aligned" action="${path0}/auth" method="post" style="width:600px;margin:15px auto;">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <fieldset>
            <div class="pure-control-group">
                <label for="aligned-name">Username</label>
                <input type="text" name="name" id="aligned-name" placeholder="Id" required/>
                <span class="pure-form-message-inline">This is a required field.</span>
            </div>
            <div class="pure-control-group">
                <label for="aligned-password">Password</label>
                <input type="password" name="password" id="aligned-password" placeholder="Password" required/>
            </div>
            <div class="pure-controls">
                <button type="submit" class="pure-button pure-button-primary">LOG IN</button>
            </div>
        </fieldset>
    </form>
</div>
<div class="l-content">

</div>
<div class="footer l-box">
    <p>
        <a href="#">Try now</a> for 14 days. No credit card required. Header image courtesy of <a href="http://unsplash.com/">Unsplash</a>.
    </p>
</div>
</body>
</html>