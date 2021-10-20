<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>

<c:if test="${empty userinfo}">
    <script type="text/javascript">
		alert("로그인 후 이용 가능합니다.");
        location.href = "${root}/";
    </script>
</c:if>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#registerBtn").click(function () {
                if (!$("#isbn").val()) {
                    alert("도서번호 입력!!!!");
                    return;
                } else if (!$("#title").val()) {
                    alert("도서명 입력!!!!");
                    return;
                } else if (!$("#author").val()) {
                    alert("저자 입력!!!!");
                    return;
                } else if (!$("#price").val()) {
                    alert("가격 입력!!!!");
                    return;
                } else if (!$("#content").val()) {
                    alert("설명 입력!!!!");
                    return;
                } else {
                    $("#writeform").attr("action", "${root}/book/register").submit();
                }
            });
        });
    </script>
</head>
<body>
    <div class="container text-center mt-3">
        <div class="col-lg-8 mx-auto">
            <h2 class="p-3 mb-3 shadow bg-light"><mark class="sky">도서 등록</mark></h2>
            <form id="writeform" class="text-left mb-3" method="post">
            <!-- <input type="hidden" name="act" value="register"> -->
                <div class="form-group">
                    <label for="isbn">도서번호 </label>
                    <input type="text" class="form-control" id="isbn" name="isbn">
                </div>
                <div class="form-group">
                    <label for="title">도서명 </label>
                    <input type="text" class="form-control" id="title" name="title">
                </div>
                <div class="form-group">
                    <label for="author">저자 </label>
                    <input type="text" class="form-control" id="author" name="author">
                </div>
                <div class="form-group">
                    <label for="price">가격 </label>
                    <input type="text" class="form-control" id="price" name="price">
                </div>
                <div class="form-group">
                    <label for="img">이미지 </label>
                    <input type="file" class="form-control" id="img" name="img"></input>
                </div>
                <div class="form-group">
                    <label for="content">설명 </label>
                    <textarea class="form-control" rows="5" id="content" name="content"></textarea>
                </div>
                <div class="text-center">
                    <button type="button" id="registerBtn" class="btn btn-outline-primary">등록</button>
                    <button type="reset" class="btn btn-outline-danger">취소</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>