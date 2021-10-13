<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ssafy.backend.dto.*"
    import="java.util.*"
%>
<%@ include file="/include/header.jsp" %>

<c:if test="${empty userinfo}">
    <script type="text/javascript">
		alert("로그인 후 이용 가능합니다.");
        location.href = "${root}/index.jsp";
    </script>
</c:if>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#mvRegisterBtn").click(function () {
                location.href = "${root}/book/regist.jsp";
            });
        });
    </script>
</head>
<body>
    <div class="container text-center mt-3">
        <div class="col-lg-8 mx-auto">
            <h2 class="p-3 mb-3 shadow bg-light"><mark class="sky">도서 목록</mark></h2>
            <div class="m-3 text-right">
                <button type="button" id="mvRegisterBtn" class="btn btn-link">추가 등록</button>
            </div>
            
            <c:if test="${!empty articlelist}">
	            <c:forEach var="book" items="${articlelist }">
	            <table class="table table-active text-left">
	                <tbody>
	                    <tr class="table-info">
	                        <td>도서번호 : ${book.isbn}</td>
	                    </tr>
	                    <tr class="table-info">
	                        <td>도서명 : ${book.title }</td>
	                    </tr>
	                    <tr class="table-info">
	                        <td>저자 : ${book.author }</td>
	                    </tr>
	                    <tr class="table-info">
	                        <td>가격 : ${book.price }</td>
	                    </tr>
	                    <tr class="table-info">
	                        <td>설명 : ${book.desc }</td>
	                    </tr>
	                </tbody>
	            </table>
	            </c:forEach>
            </c:if>
            
            <c:if test="${empty articlelist }">
            	<table class="table table-active text-left">
	                <tbody>
	                	<tr>
	                        <td class="table-danger">
	                            <strong>도서 목록이 없습니다.</strong>
	                        </td>
	                    </tr>
	                </tbody>
	            </table>
            </c:if>
        </div>
    </div>
</body>
</html>