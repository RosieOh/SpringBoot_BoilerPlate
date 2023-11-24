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
    <%--<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">--%>
    <script src="https://code.jquery.com/jquery-latest.js"></script>
    <link rel="stylesheet" href="${path0}/resource/css/pure-min.css">
    <link rel="stylesheet" href="${path0}/resource/css/grids-responsive-min.css">
    <link rel="stylesheet" href="${path0}/resource/css/styles.css">
    <title>회원 가입</title>
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
        <h2>회원 가입</h2>
    </h1>
</div>
<div class="l-content" style="width:1280px;margin:20px auto;">
    <h2>회원 가입</h2>
    <form class="pure-form pure-form-aligned" action="${path0}/update" method="post" onsubmit="return updateConfirm(this)">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <fieldset>
            <legend>회원 가입 정보를 입력하시기 바랍니다.</legend>
            <div class="pure-control-group">
                <label for="name">Id</label>
                <input type="hidden" name="id" id="id" placeholder="Name" value="${user.id}"/>
                <input type="text" name="name" id="name" placeholder="Name" value="${user.name}" readonly />
            </div>
            <div class="pure-control-group">
                <label for="password">Password</label>
                <input type="password" name="password" id="password" placeholder="Password" value="${user.password}" required/>
            </div>
            <div class="pure-control-group">
                <label for="password2">Password Confirm</label>
                <input type="password" name="password2" id="password2" placeholder="Password Confirm" value="${user.password}" required/>
            </div>
            <div class="pure-control-group">
                <label for="username">Username</label>
                <input type="text" name="username" id="username" placeholder="Username" value="${user.username}" required/>
            </div>
            <div class="pure-control-group">
                <label for="email">Email</label>
                <input type="email" name="email" id="email" placeholder="Email" value="${user.email}" readonly />
            </div>
            <div class="pure-control-group">
                <label for="address">Address</label>
                <input type="text" name="address" id="address" placeholder="Address" value="${user.address}" required/>
            </div>
            <div class="pure-control-group">
                <label for="tel">Tel</label>
                <input type="tel" name="tel" id="tel" placeholder="Tel" value="${user.tel}" required />
            </div>
            <div class="pure-controls">
                <button type="submit" class="pure-button pure-button-primary">UPDATE</button>
            </div>
        </fieldset>
    </form>
    <script>
        $(document).ready(function(){
            function updateConfirm(f){
                if(f.password.value!=f.password2.value){
                    alert("비밀번호와 비밀번호 확인이 서로 다릅니다.");
                    f.password.focus();
                    return false;
                }
            }
        });
    </script>
</div>
<div class="footer l-box">
    <p>
        <a href="#">Try now</a> for 14 days. No credit card required. Header image courtesy of <a href="http://unsplash.com/">Unsplash</a>.
    </p>
</div>
</body>
</html>