<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<%-- <c:if test="${empty userinfo}">
    <script type="text/javascript">
		alert("로그인 후 이용 가능합니다.");
        location.href = "${root}/";
    </script>
</c:if> --%>

    <script type="text/javascript">
        $(document).ready(function () {
        	
        	if("${key}")
        		$("#skey").val("${key}").prop("selected", true);
        	
        	if("${col}")
        		$("#scolumn").val("${col}").prop("selected", true);
        	
        	if("${sort}")
        		$("#ssort").val("${sort}").prop("selected", true);
        	
            $("#mvRegisterBtn").click(function () {
                location.href = "${root}/book/register";
            });
            
            $("#searchBtn").click(function () {
                $("#searchform").attr("action", "${root}/book/list").submit();
            });
            
            $(".page-item").click(function() {
				$("#pg").val(($(this).attr("data-pg")));
				$("#pageform").attr("action", "${root}/book/list").submit();
			});
            
          //file download
            $('.filedown').click(function() {
        		// alert("원본 :  " + $(this).attr('ofile') + "      실제 :  " + $(this).attr('sfile'));
        		$(document).find('[name="sfolder"]').val($(this).attr('sfolder'));
        		$(document).find('[name="ofile"]').val($(this).attr('ofile'));
        		$(document).find('[name="sfile"]').val($(this).attr('sfile'));
        		$('#downform').attr('action', '${root}/book/download').attr('method', 'get').submit();
        	});
        });
    </script>
</head>
<body>
    <form name="pageform" id="pageform" method="GET" action="">
		<input type="hidden" name="pg" id="pg" value="">
		<input type="hidden" name="key" id="key" value="${key}">
		<input type="hidden" name="word" id="word" value="${word}">
		<input type="hidden" name="col" id="col" value="${col}">
		<input type="hidden" name="sort" id="sort" value="${sort}">
	</form>
	<form id="downform">
		<input type="hidden" name="sfolder">
		<input type="hidden" name="ofile">
		<input type="hidden" name="sfile">
	</form>
	
    <div class="container text-center mt-3">
        <div class="col-lg-8 mx-auto">
            <h2 class="p-3 mb-3 shadow bg-light"><mark class="sky">도서 목록</mark></h2>
            <div class="m-3 text-right">
                <button type="button" id="mvRegisterBtn" class="btn btn-link">추가 등록</button>
            </div>
            
            <div class="m-3 row justify-content-end">
            	<form id="searchform" class="form-inline" method="get">
            		<input type="hidden" name="pg" value="1">
	            	<select id="skey" name="key" class="form-control">
	            		<option value="title"> 도서명
	            		<option value="author"> 저자
	            	</select>
	            	<input type="text" class="ml-1 form-control" id="sword" name="word" value="${word}">
	            	
	            	<select id="scolumn" name="col" class="form-control">
	            		<option value="isbn"> 도서번호
	            		<option value="title"> 도서명
	            		<option value="author"> 저자
	            		<option value="price"> 가격
	            	</select>
	            	<select id="ssort" name="sort" class="form-control">
	            		<option value="asc"> 오름차순
	            		<option value="desc"> 내림차순
	            	</select>
	            	<button type="button" id="searchBtn" class="ml-1 btn btn-outline-primary">검색</button>
            	</form>
            </div>
            
            <c:if test="${!empty articlelist}">
	            <table class="table table-active text-left">
	            <thead>
	            	<tr class="table-info" align="center">
	            		<th>도서번호</th>
	            		<th>도서명</th>
	            		<th>저자</th>
	            		<th>가격</th>
	            		<th>설명</th>
	            	</tr>
	            </thead>
	                <tbody>
	            <c:forEach var="book" items="${articlelist }">
	                    <tr>
	                        <td>${book.isbn}</td>
	                        <td>${book.title }</td>
	                        <td>${book.author }</td>
	                        <td>${book.price }</td>
	                        <td>${book.content }</td>
	                    </tr>
	                    <tr>
	                    <c:if test="${!empty book.fileInfoDtos }">
	                        <td>
		                        <ul>
									<c:forEach var="file" items="${book.fileInfoDtos}">
										<li>${file.originFile} <a href="#" class="filedown" sfolder="${file.saveFolder}" sfile="${file.saveFile}" ofile="${file.originFile}">[다운로드]</a>
									</c:forEach>
								</ul>
							</td>
	                    </c:if>
	                    </tr>
	            </c:forEach>
	                </tbody>
	            </table>
	            <table class="text-center">
			  	<tr>
				  	<td>${navigation.navigator}</td>
			  	</tr>
			  	</table>
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