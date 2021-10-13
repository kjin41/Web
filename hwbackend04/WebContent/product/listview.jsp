<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ssafy.product.dto.*"
    import="java.util.*"
%>
<%@ include file="/include/header.jsp" %>



    <script type="text/javascript">
        $(document).ready(function () {
            $("#mvRegisterBtn").click(function () {
                location.href = "${root}/product/regist.jsp";
            });
            $("#nameSearchBtn").click(function () {
            	$("#searchNameForm").attr("action", "${root}/mainservlet").submit();
            });
            $("#priceSearchBtn").click(function () {
            	$("#searchPriceForm").attr("action", "${root}/mainservlet").submit();
            });
        });
    </script>
</head>
<body>
    <div class="container text-center mt-3">
        <div class="col-lg-8 mx-auto">
            <h2 class="p-3 mb-3 shadow bg-light"><mark class="sky">상품 목록</mark></h2>
            <div class="m-3 text-right">
                <a href="${root }/index.jsp" class="btn btn-link">메인으로</a>
                <button type="button" id="mvRegisterBtn" class="btn btn-link">추가 등록</button>
            </div>
            
            <form id="searchNameForm" class="text-left mb-3" method="post" action="">
            <input type="hidden" name="act" value="searchName">
	            <div class="form-group">
                	상품명 <input type="text" class="form-control" id="name" name="name">
                <button type="button" id="nameSearchBtn" class="btn btn-link">검색</button>
                </div>
            </form>
            <form id="searchPriceForm" class="text-left mb-3" method="post" action="">
            <input type="hidden" name="act" value="searchPrice">
	            <div class="form-group">
                	가격(이하) <input type="text" class="form-control" id="price" name="price">
                <button type="button" id="priceSearchBtn" class="btn btn-link">검색</button>
	            </div>
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