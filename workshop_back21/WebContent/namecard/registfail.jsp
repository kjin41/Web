<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#mvRegisterBtn").click(function () {
                location.href = "<%= root%>/namecard/regist.jsp";
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
            <div class="jumbotron">
                <h1 class="text-danger">명함등록 실패 T.T</h1>
                <p class="mt-4"><button type="button" id="mvRegisterBtn" class="btn btn-outline-dark">명함 등록 페이지로 이동</button>
                </p>
            </div>
        </div>
    </div>
    
<%@ include file="/include/footer.jsp" %>