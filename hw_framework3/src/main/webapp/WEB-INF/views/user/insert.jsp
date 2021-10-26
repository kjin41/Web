<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#insertBtn").click(function () {
                if (!$("#id").val()) {
                    alert("아이디 입력!!!");
                    return;
                } else if (!$("#pass").val()) {
                    alert("이름 입력!!!");
                    return;
                } else if (!$("#pass").val()) {
                    alert("비밀번호 입력!!!");
                    return;
                } else if (!$("#pass2").val()) {
                    alert("비밀번호 확인 입력!!!");
                    return;
                } else if ($("#pass").val()!=$("#pass2").val()) {
                    alert("비밀번호가 일치하지 않습니다.");
                    return;
                } else {
                    $("#insertform").attr("action", "${root}/user/insert").submit();
                }
            });
            
        });
    </script>
</head>
<body>
    <div class="container text-center mt-3">
    <h1>회원가입</h1>
        <div class="col-lg-8 mx-auto jumbotron">
			내용을 입력해 주세요.
            <form id="insertform" class="text-left mb-3" method="post">
                <!-- <input type="hidden" name="act" id="act" value="login"> -->
                <div class="form-group">
                    <label for="id">ID</label>
                    <input type="text" class="form-control" id="id" name="id">
                </div>
                <div class="form-group">
                    <label for="name">NAME</label>
                    <input type="text" class="form-control" id="name" name="name">
                </div>
                <div class="form-group">
                    <label for="pass">PASSWORD</label>
                    <input type="password" class="form-control" id="pass" name="pass">
                </div>
                <div class="form-group">
                    <label for="pass">PASSWORD Again</label>
                    <input type="password" class="form-control" id="pass2" name="pass2">
                </div>
                <div class="form-group text-center">
                    <button type="button" id="insertBtn" class="btn btn-outline-primary">회원가입</button>
                    <a href="${root }/" class="btn btn-outline-primary">취소</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
