<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ include file="/include/header.jsp" %>

    <div align="center">
      <h3>SSAFY 방명록!!!(mvc)</h3>
      <c:if test="${empty userinfo }">
      <a href="${root}/user?act=mvregister">회원가입</a><br /> 
      <a href="${root}/user?act=mvlogin">로그인</a><br />
      </c:if>
      
      <c:if test="${!empty userinfo }">
      <strong>${userinfo.userName}</strong> (${userinfo.userId})님  안녕하세요.<br />
      <a href="${root}/user?act=logout">로그아웃</a><br />
      <a href="${root}/guestbook?act=list">글목록</a><br />
      <a href="${root}/guestbook/write.jsp">글쓰기</a><br />
      </c:if>
      
    </div>
  </body>
</html>
