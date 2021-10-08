<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>

    <script type="text/javascript">
        $(document).ready(function () {
        	console.log("start");
            $("#registerBtn").click(function () {
            	console.log("btn");
                if (!$("#name").val()) {
                    alert("이름 입력!!!!");
                    return;
                } else if (!$("#company").val()) {
                    alert("회사 입력!!!!");
                    return;
                } else if (!$("#part").val()) {
                    alert("부서 입력!!!!");
                    return;
                } else if (!$("#email").val()) {
                    alert("이메일 입력!!!!");
                    return;
                } else if (!$("#phone1").val()) {
                    alert("핸드폰 번호 입력!!!!");
                    return;
                } else if (!$("#phone2").val()) {
                    alert("핸드폰 번호 입력!!!!");
                    return;
                } else if (!$("#phone3").val()) {
                    alert("핸드폰 번호 입력!!!!");
                    return;
                } else if (!$("#address1").val()) {
                    alert("주소 입력!!!!");
                    return;
                } else if (!$("#address2").val()) {
                    alert("주소 입력!!!!");
                    return;
                } else {
                    $("#writeform").attr("action", "<%=root%>/ncservlet").submit();
                }
            });
        });
    </script>
</head>
<body>
  <div class="header">
    <h3>SSAFY JSP</h3>
    <hr color="black">
  </div>
  <div class="content">
    <h3>명함 등록</h3>
    <form id="writeform" action="" method=post>
      <input type="hidden" name="act" value="register">
      <table>
        <tr>
          <td>이름</td>
          <td><input type="text" name="name" id="name"></td>
        </tr>
        <tr>
          <td>회사명</td>
          <td><input type="text" name="company" id="company"></td>
          <td>부서명 <input type="text" name="part" id="part"></td>
        </tr>
        <tr>
          <td>이메일</td>
          <td><input type="email" name="email" id="email"></td>
        </tr>
        <tr>
          <td>휴대폰</td>
          <td>
            <input class="phone" type="text" name="phone1" id="phone1"> -
            <input class="phone" type="text" name="phone2" id="phone2"> -
            <input class="phone" type="text" name="phone3" id="phone3">
          </td>
        </tr>
        <tr>
          <td>주소</td>
          <td colspan="2">
            <input class="address" type="text" name="address1" id="address1">
            <input type="text" name="address2" id="address2">
          </td>
        </tr>
      </table>
      <button type="button" id="registerBtn">확인</button>
      <button type="reset">초기화</button>
    </form>
  </div>
  
  
<%@ include file="/include/footer.jsp" %>