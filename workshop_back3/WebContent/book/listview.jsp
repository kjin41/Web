<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ssafy.backend.dto.*"
    import="java.util.*"
%>
<%@ include file="/include/header.jsp" %>

<%
List<Book> list=(List<Book>)request.getAttribute("articlelist");
%>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#mvRegisterBtn").click(function () {
                location.href = "<%=root%>/book/regist.jsp";
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
            
            <%
            if (list!=null && !list.isEmpty()){
            	for (Book book: list){
            
            %>
            
            <table class="table table-active text-left">
                <tbody>
                    <tr class="table-info">
                        <td>도서번호 : <%= book.getIsbn() %></td>
                    </tr>
                    <tr class="table-info">
                        <td>도서명 : <%= book.getTitle() %></td>
                    </tr>
                    <tr class="table-info">
                        <td>저자 : <%= book.getAuthor() %></td>
                    </tr>
                    <tr class="table-info">
                        <td>가격 : <%= book.getPrice() %></td>
                    </tr>
                    <tr class="table-info">
                        <td>설명 : <%= book.getDesc() %></td>
                    </tr>
                </tbody>
            </table>
            <%	} 
            } else { %>
            	<table class="table table-active text-left">
	                <tbody>
	                	<tr>
	                        <td class="table-danger">
	                            <strong>도서 목록이 없습니다.</strong>
	                        </td>
	                    </tr>
	                </tbody>
	            </table>
            <% } %>
        </div>
    </div>
</body>
</html>