<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ssafy.product.dto.*"
    import="java.util.*"
%>
<%@ include file="/include/header.jsp" %>

<%
List<ProductDto> list=(List<ProductDto>)request.getAttribute("articlelist");
%>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#mvRegisterBtn").click(function () {
                location.href = "<%=root%>/product/regist.jsp";
            });
        });
    </script>
</head>
<body>
    <div class="container text-center mt-3">
        <div class="col-lg-8 mx-auto">
            <h2 class="p-3 mb-3 shadow bg-light"><mark class="sky">상품 목록</mark></h2>
            <div class="m-3 text-right">
                <a href="<%=root %>/index.jsp" class="btn btn-link">메인으로</a>
                <button type="button" id="mvRegisterBtn" class="btn btn-link">추가 등록</button>
            </div>
            
            <%
            if (list!=null && !list.isEmpty()){
            	for (ProductDto product: list){
            
            %>
            
            <table class="table table-active text-left">
                <tbody>
                    <tr class="table-info">
                        <td>상품번호 : <%= product.getNo() %></td>
                    </tr>
                    <tr class="table-info">
                        <td>상품명 : <%= product.getName() %></td>
                    </tr>
                    <tr class="table-info">
                        <td>상품가격 : <%= product.getPrice() %></td>
                    </tr>
                    <tr class="table-info">
                        <td>상품설명 : <%= product.getDesc() %></td>
                    </tr>
                </tbody>
            </table>
            <%	} 
            } else { %>
            	<table class="table table-active text-left">
	                <tbody>
	                	<tr>
	                        <td class="table-danger">
	                            <strong>등록된 상품이 없습니다.</strong>
	                        </td>
	                    </tr>
	                </tbody>
	            </table>
            <% } %>
        </div>
    </div>
</body>
</html>