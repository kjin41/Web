<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.ssafy.namecard.dto.*"
	import="java.util.*"%>
<%@ include file="/include/header.jsp"%>

<%
	List<NameCardDto> list = (List<NameCardDto>) request.getAttribute("articlelist");
%>
<script type="text/javascript">
	$(document).ready(function () {
	    $("#mvRegisterBtn").click(function () {
	        location.href = "<%=root%>/namecard/regist.jsp";
		});
	});
</script>
</head>
<body>
	<div class="header">
		<h3>SSAFY JSP</h3>
		<hr color="black">
	</div>
	<div class="container text-center mt-3">
		<div class="col-lg-8 mx-auto">
			<h2 class="p-3 mb-3 shadow bg-light">
				<mark class="sky">명함 목록</mark>
			</h2>
			<div class="m-3 text-right">
				<button type="button" id="mvRegisterBtn" class="btn btn-link">명함
					등록</button>
			</div>

			<%
				if (list != null && !list.isEmpty()) {
					for (NameCardDto nc : list) {
			%>

			<table class="table table-active text-left">
				<tbody>
					<tr class="table-info">
						<td>이름 : <%=nc.getName()%></td>
					</tr>
					<tr class="table-info">
						<td>회사 : <%=nc.getCompany()%></td>
					</tr>
					<tr class="table-info">
						<td>부서 : <%=nc.getPart()%></td>
					</tr>
					<tr class="table-info">
						<td>이메일 : <%=nc.getEmail()%></td>
					</tr>
					<tr class="table-info">
						<td>전화번호 : <%=nc.getPhone1()%>-<%=nc.getPhone2()%>-<%=nc.getPhone3()%></td>
					</tr>
					<tr class="table-info">
						<td>주소 : <%=nc.getAddress1()%> <%=nc.getAddress2()%></td>
					</tr>
				</tbody>
			</table>
			<%
				}
				} else {
			%>
			<table class="table table-active text-left">
				<tbody>
					<tr>
						<td class="table-danger"><strong>명함 목록이 없습니다.</strong></td>
					</tr>
				</tbody>
			</table>
			<%
				}
			%>
		</div>
	</div>

	<%@ include file="/include/footer.jsp"%>