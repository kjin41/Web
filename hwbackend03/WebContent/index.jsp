<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ssafy.product.dto.*"%>
<%@ include file="/include/header.jsp" %>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#loginBtn").click(function () {
                if (!$("#id").val()) {
                    alert("아이디 입력!!!");
                    return;
                } else if (!$("#pass").val()) {
                    alert("비밀번호 입력!!!");
                    return;
                } else {
                    $("#loginform").attr("action", "<%= root %>/user").submit();
                }
            });
            
        });
    </script>
</head>
<body>
    <div class="container text-center mt-3">
    <h1>메인 페이지</h1>
    <%
    if (userDto==null) {
    %>
        <div class="col-lg-8 mx-auto jumbotron">
			로그인하여 주세요.
            <form id="loginform" class="text-left mb-3" method="post" action="">
                <input type="hidden" name="act" id="act" value="login">
                <div class="form-group">
                    <label for="id">ID</label>
                    <input type="text" class="form-control" id="id" name="id">
                </div>
                <div class="form-group">
                    <label for="pass">PASSWORD</label>
                    <input type="password" class="form-control" id="pass" name="pass">
                </div>
                <div class="form-group text-center">
                    <button type="button" id="loginBtn" class="btn btn-outline-primary">로그인</button>
                </div>
            </form>
        </div>
        
    <%
    } else {
    %>
    	<%= userDto.getName()%>(<%= userDto.getId()%>)님 안녕하세요.<br><br>
                <div class="form-group text-center">
                    <a href="<%= root %>/user?act=logout" class="btn btn-outline-danger">로그아웃</a>
                </div>
                
        <div class="col-lg-9 mx-auto">
			<a href="<%= root %>/product/regist.jsp" class="col-lg-3">상품 등록</a>
			<a href="<%= root %>/mainservlet?act=list" class="col-lg-3">상품 목록</a>
			<a href="<%= root %>/mainservlet?act=lastproduct" class="col-lg-3">마지막 등록한 상품</a><br>
        </div>
    <%
    }
    %>
    </div>
</body>
</html>
