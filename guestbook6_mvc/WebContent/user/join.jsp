<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>

    <script type="text/javascript">
        $(document).ready(function () {
        	var isId = false;
        	// 아이디 중복검사
        	$("#userid").keyup(function () {
        		var ckid = $("#userid").val();
        		if(ckid.length < 4 || ckid.length > 12) {
        			$("#idresult").text("아이디는 4자이상 12자이하입니다.").removeClass('text-primary').removeClass('text-danger').addClass('text-dark');
        			isId = false;
        		} else {
	                $.ajax({
	                	url: '${root}/user',
	                	data: {'act': 'idcheck', 'ckid': ckid},
	                  	type: 'GET',
	                  	dataType: 'xml',
	                  	success: function (response) {
	                    	// var cnt = parseInt(response);
	                    	var cnt=parseInt($(response).find('idcnt').text());
	                    	if(cnt == 0) {
	                    		$("#idresult").text(ckid + "는 사용가능합니다.").removeClass('text-dark').removeClass('text-danger').addClass('text-primary');
	                    		isId = true;
	                    	} else {
	                    		$("#idresult").text(ckid + "는 사용할 수 없습니다.").removeClass('text-dark').removeClass('text-primary').addClass('text-danger');
	                    		isId = false;
	                    	}
	                  	}
					});
        		}
			});
        	
        	// 회원가입
            $("#registerBtn").click(function () {
                if (!$("#username").val()) {
                    alert("이름 입력!!!");
                    return;
                } else if (!isId) {
                    alert("아이디 확인!!!");
                    return;
                } else if (!$("#userpwd").val()) {
                    alert("비밀번호 입력!!!");
                    return;
                } else if ($("#userpwd").val() != $("#pwdcheck").val()) {
                    alert("비밀번호 확인!!!");
                    return;
                } else {
                    $("#memberform").attr("action", "${root}/user").submit();
                }
            });

            // $('#zipcode').focusin(function () {
            //     $('#zipModal').modal();
            // });
        });
    </script>

    <div class="container text-center mt-3">
        <div class="col-lg-8 mx-auto">
            <h2 class="p-3 mb-3 shadow bg-light"><mark class="orange">회원가입</mark></h2>
            <form id="memberform" class="text-left mb-3" method="post" action="">
                <input type="hidden" name="act" id="act" value="register">
                <div class="form-group">
                    <label for="username">이름</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="이름입력...">
                </div>
                <div class="form-group">
                    <label for="userid">아이디</label>
                    <input type="text" class="form-control" id="userid" name="userid" placeholder="아이디입력...">
                    <div id="idresult" class="mt-1"></div>
                </div>
                <div class="form-group">
                    <label for="userpwd">비밀번호</label>
                    <input type="password" class="form-control" id="userpwd" name="userpwd" placeholder="비밀번호입력...">
                </div>
                <div class="form-group">
                    <label for="pwdcheck">비밀번호재입력</label>
                    <input type="password" class="form-control" id="pwdcheck" name="pwdcheck" placeholder="비밀번호재입력...">
                </div>
                <div class="form-group">
                    <label for="emailid">이메일</label><br>
                    <div id="email" class="custom-control-inline">
                        <input type="text" class="form-control" id="emailid" name="emailid" placeholder="이메일아이디입력..."
                            size="25"> @
                        <select class="form-control" id="emaildomain" name="emaildomain">
                            <option value="ssafy.com">싸피</option>
                            <option value="naver.com">네이버</option>
                            <option value="kakao.com">카카오</option>
                            <option value="google.com">구글</option>
                        </select>
                    </div>
                </div>
                <!--
                <div class="form-group">
                    <label for="tel1">전화번호</label>
                    <div id="tel" class="custom-control-inline">
                        <select class="form-control" id="tel1" name="tel1">
                            <option value="010">010</option>
                            <option value="02">02</option>
                            <option value="031">031</option>
                            <option value="032">032</option>
                            <option value="041">041</option>
                            <option value="051">051</option>
                            <option value="061">061</option>
                        </select> _
                        <input type="text" class="form-control" id="tel2" name="tel2" placeholder=""> _
                        <input type="text" class="form-control" id="tel3" name="tel3" placeholder="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="zipcode">주소</label><br>
                    <div id="addressdiv" class="custom-control-inline">
                        <input type="text" class="form-control" id="zipcode" name="zipcode" placeholder="우편번호" size="7"
                            maxlength="5" readonly="readonly">
                    </div>
                    <input type="text" class="form-control" id="address" name="address" placeholder="">
                    <input type="text" class="form-control" id="address_detail" name="address_detail" placeholder="">
                </div>
                -->
                <div class="form-group text-center">
                    <button type="button" id="registerBtn" class="btn btn-outline-primary">회원가입</button>
                    <button type="reset" class="btn btn-outline-danger">초기화</button>
                </div>
            </form>
        </div>
    </div>
    <!-- 우편번호 검색 모달 -->
    <!--
    <div id="zipModal" class="modal fade" role="dialog">
        <h5 class="modal-title" id="myModalLabel">우편번호검색</h5>
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header text-center">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body text-center">
                    <form id="zip_codeForm">
                        <div align="center">
                            <label>도로명 주소검색</label>
                        </div>
                        <div class="input-group">
                            <input type="text" class="form-control" id="doro" name="doro"
                                placeholder="검색 할 도로명 입력(예: 대왕판교로, 학하서로)">
                            <span class="input-group-btn">
                                <input type="submit" class="btn btn-warning" value="검색" id="searchBtn">
                            </span>
                        </div>
                    </form>
                    <div style="width:100%; height:200px; overflow:auto">
                        <table class="table text-center">
                            <thead>
                                <tr>
                                    <th style="width:150px;">우편번호</th>
                                    <th style="width:600px;">주소</th>
                                </tr>
                            </thead>
                            <tbody id="zip_codeList"></tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    -->
</body>
</html>