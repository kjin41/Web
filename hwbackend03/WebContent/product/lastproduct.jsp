<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ssafy.product.dto.*"
    import="java.util.*"
%>
<%@ include file="/include/header.jsp" %>
<%
ProductDto productDto=(ProductDto)request.getAttribute("lastproduct");
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
            <h2 class="p-3 mb-3 shadow bg-light"><mark class="sky">마지막으로 등록한 상품</mark></h2>
            
            <%
            if (productDto!=null) {
            %>
            <table class="table table-active text-left">
                <tbody>
                    <tr class="table-info">
                        <td>상품번호 : <%= productDto.getNo() %></td>
                    </tr>
                    <tr class="table-info">
                        <td>상품명 : <%= productDto.getName() %></td>
                    </tr>
                    <tr class="table-info">
                        <td>상품가격 : <%= productDto.getPrice() %></td>
                    </tr>
                    <tr class="table-info">
                        <td>상품설명 : <%= productDto.getDesc() %></td>
                    </tr>
                </tbody>
            </table>
            
            <div class="col-lg-9 mx-auto">
				<a href="<%= root %>/product/update.jsp" class="col-lg-3">상품 수정</a>
				<a href="<%= root %>/mainservlet?act=delete" class="col-lg-3">상품 삭제</a>
				<a href="<%= root %>/mainservlet?act=list" class="col-lg-3">상품 목록</a><br>
        	</div>
            <%
            } else {
            %>
            <table class="table table-active text-left">
                <tbody>
                	<tr>
                        <td class="table-danger">
                            <strong>등록된 마지막 상품이 없습니다.</strong>
                        </td>
                    </tr>
                </tbody>
            </table>
            
            <div class="col-lg-9 mx-auto">
				<a href="<%= root %>/mainservlet?act=list" class="col-lg-3">상품 목록</a><br>
        	</div>
            <%
            }
            %>
            
            
        </div>
    </div>
</body>
</html>