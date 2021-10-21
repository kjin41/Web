<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#registerBtn").click(function () {
                location.href = "${root}/product/register";
            });
            $("#searchBtn").click(function () {
               	$("#searchform").attr("action", "${root}/product/list").submit();
            });
        });
    </script>
</head>
<body>
    <div class="container text-center mt-3">
        <div class="col-lg-8 mx-auto">
            <h2 class="p-3 mb-3 shadow bg-light"><mark class="sky">상품 목록</mark></h2>
            <div class="m-3 text-right">
                <a href="${root }/" class="btn btn-link">메인으로</a>
                <button type="button" id="registerBtn" class="btn btn-link">추가 등록</button>
            </div>
            
            <form id="searchform" class="text-left mb-3" method="get" action="">
            <!-- <input type="hidden" name="pg" value="1"> -->
	            <select class="form-group" name="key">
	            	<option value="name"> 상품명
	            	<option value="price"> 가격(이하)
                </select>
	            <input type="text" name="word">
                <button type="button" id="searchBtn" class="btn btn-link">검색</button>
            </form>
            
            <c:if test="${!empty articlelist }">
            <c:forEach var="product" items="${articlelist }">
	            <table class="table table-active text-left">
	                <tbody>
	                    <tr class="table-info">
	                        <td>상품번호 : ${product.no }</td>
	                    </tr>
	                    <tr class="table-info">
	                        <td>상품명 : ${product.name }</td>
	                    </tr>
	                    <tr class="table-info">
	                        <td>상품가격 : ${product.price }</td>
	                    </tr>
	                    <tr class="table-info">
	                        <td>상품설명 : ${product.desc }</td>
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
	                            <strong>등록된 상품이 없습니다.</strong>
	                        </td>
	                    </tr>
	                </tbody>
	            </table>
			</c:if>
        </div>
    </div>
</body>
</html>