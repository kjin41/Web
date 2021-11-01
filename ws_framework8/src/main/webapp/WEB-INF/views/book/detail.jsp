<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<c:if test="${empty userinfo}">
    <script type="text/javascript">
		alert("로그인 후 이용 가능합니다.");
        location.href = "${root}/";
    </script>
</c:if>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#registerBtn").click(function () {
                location.href = "${root}/book/register";
            });
        });
        $(document).ready(function () {
            $("#listBtn").click(function () {
                location.href = "${root}/book/list?pg=1&key=&word=&col=isbn&sort=asc";
            });
        });
    </script>
</head>
<body>
    <div class="container text-center mt-3">
        <div class="col-lg-8 mx-auto">
            <!-- <h2 class="p-3 mb-3 shadow bg-light"><mark class="sky">도서 목록</mark></h2> -->
            
            <c:if test="${!empty article}">
            	<h2>도서 등록 결과</h2>
	            <table class="table table-active text-left">
	            	<thead>
	            		<tr class="table-info">
	            			<th>항목</th>
	            			<th>내용</th>
	            		</tr>
	            	</thead>
	                <tbody>
	                    <tr class="table-info">
	                        <td>도서번호</td> 
	                        <td>${article.isbn}</td>
	                    </tr>
	                    <tr class="table-info">
	                        <td>도서명</td> 
	                        <td>${article.title}</td>
	                    </tr>
	                    <tr class="table-info">
	                        <td>저자</td> 
	                        <td>${article.author}</td>
	                    </tr>
	                    <tr class="table-info">
	                        <td>가격</td> 
	                        <td>${article.price}</td>
	                    </tr>
	                    <%-- <tr class="table-info">
	                        <td>이미지</td> 
	                        <td>${article.img}</td>
	                    </tr> --%>
	                    <tr class="table-info">
	                        <td>설명</td> 
	                        <td>${article.content}</td>
	                    </tr>
	                </tbody>
	            </table>
            </c:if>
            
            <div class="m-3 text-right">
                <button type="button" id="registerBtn" class="btn btn-link">추가 등록</button>
                <button type="button" id="listBtn" class="btn btn-link">목록 보기</button>
            </div>
            
        </div>
    </div>
</body>
</html>