<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#registerBtn").click(function () {
                if (!$("#name").val()) {
                    alert("상품 이름 입력!!!!");
                    return;
                } else if (!$("#price").val()) {
                    alert("가격 입력!!!!");
                    return;
                } else if (!$("#desc").val()) {
                    alert("설명 입력!!!!");
                    return;
                } else {
                    $("#writeform").attr("action", "<%=root%>/mainservlet").submit();
                }
            });
        });
    </script>
</head>
<body>
    <div class="container text-center mt-3">
        <div class="col-lg-8 mx-auto">
            <h2 class="p-3 mb-3 shadow bg-light"><mark class="sky">상품을 등록 해 주세요.</mark></h2>
            <form id="writeform" class="text-left mb-3" method="post" action="">
            <input type="hidden" name="act" value="register">
                <div class="form-group">
                    <label for="name">상품명 </label>
                    <input type="text" class="form-control" id="name" name="name">
                </div>
                <div class="form-group">
                    <label for="price">가격 </label>
                    <input type="text" class="form-control" id="price" name="price">
                </div>
                <div class="form-group">
                    <label for="desc">설명 </label>
                    <input type="text" class="form-control" id="desc" name="desc">
                </div>
                <div class="text-center">
                    <button id="registerBtn" class="btn btn-outline-primary">저장</button>
                    <button type="reset" class="btn btn-outline-danger">취소</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>