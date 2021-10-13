<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>도서관리</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        mark.sky {
            background: linear-gradient(to top, #54fff9 20%, transparent 30%);
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
                } else if (!$("#desc").val()) {
                    alert("설명 입력!!!!");
                    return;
                } else {
                    $("#writeform").attr("action", "<%=root%>/bookservlet").submit();
                }
            });
        });
    </script>
</head>
<body>
    <div class="container text-center mt-3">
        <div class="col-lg-8 mx-auto">
            <h2 class="p-3 mb-3 shadow bg-light"><mark class="sky">도서 등록</mark></h2>
            <form id="writeform" class="text-left mb-3" method="post" action="">
            <input type="hidden" name="act" value="register">
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
                    <label for="desc">설명 </label>
                    <textarea class="form-control" rows="5" id="desc" name="desc"></textarea>
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