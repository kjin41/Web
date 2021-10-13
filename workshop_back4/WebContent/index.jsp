<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#registerBtn").click(function () {
            	location.href="${root}/book/regist.jsp";
            });
            
            $("#listBtn").click(function () {
                location.href="${root}/bookservlet?act=list";
            });
        });
    </script>

<div align="center">
<button type="button" id="registerBtn" class="btn btn-outline-primary">도서 등록</button>
<button type="button" id="listBtn" class="btn btn-outline-primary">도서 목록</button>
</div>
</body>
</html>