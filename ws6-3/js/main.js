window.onload=function(){
  var loginDiv = document.querySelector(".login");
  var logoutDiv = document.querySelector(".logout");
  var beforeProfile = document.querySelector(".before-profile");
  var afterProfile = document.querySelector(".after-profile");
  logoutDiv.style.display="none";
  afterProfile.style.display="none";

  // 로그인
  document.getElementById("id-login").addEventListener("click", function(){
    var id = prompt("아이디 입력", "ssafy");
    var pwd = prompt("비밀번호 입력", "1234");

    if (id=="ssafy" && pwd=="1234"){
      alert("로그인 성공");
      logoutDiv.style.display="";
      loginDiv.style.display = "none";
      afterProfile.style.display="";
      beforeProfile.style.display = "none";

    } else{
      alert("아이디 혹은 비밀번호가 일치하지 않습니다.");
    }
  });

  // 로그아웃
  document.getElementById("id-logout").addEventListener("click", function(){
    logoutDiv.style.display="none";
    loginDiv.style.display = "";
    afterProfile.style.display="none";
    beforeProfile.style.display = "";
  });

  // 관리자
  var storagePoll = localStorage.getItem("poll");
  var pollContent;

  if (storagePoll){
    var vote = JSON.parse(storagePoll);
    var sdate = vote.start_date;
    var edate = vote.end_date;
    var question = vote.question;
    var answers = vote.answers;

    
    pollContent = `
      <div class="text-center text-secondary mb-3"><h4>[ 당신의 선택 ]</h4></div>
      <div class="font-weight-bold">${question}</div>
      <div class="text-left p-3">
        <ul class="nav flex-column">
    `;
    for (var i = 0; i < answers.length; i++) {
      pollContent += `
          <li class="nav-item">
            <label><input type="radio" name="vote-answer" value="${answers[i]}" /> ${answers[i]}</label>
          </li>
      `;
    }
    pollContent += `
        </ul>
      </div>
      <div class="text-right">
        <button class="btn btn-outline-primary btn-sm" id="btn-poll">투표하기</button>
        <button class="btn btn-outline-danger btn-sm">결과보기</button>
      </div>
      <div class="text-right text-secondary mt-1 poll-date">투표기간 : ${sdate}~${edate}</div>
    `;

    $('#vote-area').html(pollContent);
    $('#vote-btn-area').css('display', 'none');
    $('#vote-area').css('display', '');
    // console.log("ok");

  } else{
    pollContent="진행중인 투표가 없습니다.";
    $('#vote-area').html(pollContent);
    $('#vote-btn-area').css('display', '');
    $('#vote-area').css('display', 'none');
  }

  $(document).on("click", "#btn-poll", function(){
    var setItem = $('input[name="vote-answer"]:checked').val();
    alert(setItem+" 을(를) 선택했습니다.");
  })

  $('#btn-add').click(function(){
    var div = $(`<div class="form-inline mb-1 poll-answer-item">`)
      .append(`<input type="text" class="form-control col-lg-10 mr-3" name="answer">`)
      .append(`<button type="button" class="btn btn-outline-danger btn-sm btn-remove">삭제</button>`);
    $(`#poll-answer-list`).append(div);  
  })

  $(document).on("click", ".btn-remove", function(){
    $(this).parent('poll-answer-itme').remove();
  })

  $('#btn-make').click(function(){
    var sdate = $('#start-date').val();
    var edate = $('#end-date').val();

    if (!sdate || !edate){
      alert("설문 기간 입력하세요.");
      return;
    }

    if (sdate>edate){
      alert("시작일이 종료일보다 늦을 수 없습니다.");
      return;
    }

    var question = $('#question').val();
    if(!question){
      alert("질문 내용 입력!");
    }

    var isValid = true;
    var answers=[];
    $('input[name="answer"]').each(function(){
      if (!$(this).val()){
        alert("답변 항목 입력!");
        isValid=false;
        return false;
      }
      answers.push($(this).val());
    })

    if (!isValid){
      return;
    }

    var poll={
      start_date: sdate,
      end_date: edate,
      question: question,
      answers: answers
    };

    localStorage.setItem("poll", JSON.stringify(poll));
    alert("투표 생성 완료!");
    location.reload();
    $('#vote-modal').modal('hide');
  })

  //전국 매장 관리
  var storeOpen = document.querySelector(".store-open");
  var storeClose = document.querySelector(".store-close");
  storeClose.style.display="none";

  $(".store-area-detail").css("display", "none");

  // 매장 over
  $('.all-store').mouseover(function(){
    $(this).css("background-color", "rgb(230, 230, 230)");
  })

  $('.all-store').mouseout(function(){
    $(this).css("background-color", "rgb(167, 167, 167)");
  })

  $('.store-area').mouseover(function(){
    $(this).css("background-color", "rgb(230, 230, 230)");
  })

  $('.store-area').mouseout(function(){
    $(this).css("background-color", "rgb(196, 196, 196)");
  })

  // 전국 매장 펼치기
  document.querySelector(".store-open").addEventListener("click", function(){
    storeOpen.style.display = "none";
    storeClose.style.display="";
    $('.store-area-detail').css("display", "");
  })
  
  // 전국 매장 접기
  document.querySelector(".store-close").addEventListener("click", function(){
    storeOpen.style.display = "";
    storeClose.style.display="none";
    $('.store-area-detail').css("display", "none");
  })

  // 지역 접고 펼치기 : 서울, 대전, 구미, 광주, 부울경
  $(".store-area").click(function(){
    if ($(this).find('.store-area-detail').css("display") == "none"){
      $(this).find('.store-area-detail').css("display", "");
    } else{
      $(this).find('.store-area-detail').css("display", "none");
    }
  })


  // programming.xml 파싱
  $.ajax({
    url: './data/programming.xml',
    type: 'GET',
    dataType: 'xml',
    success: function(response){
      makeProgList(response);
    },
    error: function(xhr, status, msg){
      console.log('상태값 : '+status+"HTTP 에러메세지 : "+msg);
    },

  })

  function makeProgList(data){
    var progBookList = `<ul>`;
    $(data).find('book').each(function(){
      progBookList+=`<li>
      <div><img class="book" src="./img/book/${$(this).find('isbn').text()}.png" alt""></div>
      <div>${$(this).find('title').text()} (${$(this).find('price').text()}원)</div>
      </li>`;
    })
    progBookList += `</ul>`;
    $(".programming-language-menu").html(progBookList);
  }

  // essay 파싱
  $.ajax({
    url: './data/essay.json',
    type: 'GET',
    contentType: 'application/json;charset=utf-8',
    dataType: 'json',
    success: function(response){
      makeEssayList(response);
    },
    error: function(xhr, status, msg){
      console.log('상태값 : '+status+"HTTP 에러메세지 : "+msg);
    },

  })

  function makeEssayList(data){
    var essayList = `<ul>`;
    $.each(data, function(index, item){
      essayList+=`<li>
      <div><img class="book" src="./img/book/${item.isbn}.png" alt""></div>
      <div>${item.title} (${item.price}원)</div>
      </li>`;
    })
    essayList += `</ul>`;
    console.log(essayList);
    $(".essay-menu").html(essayList);
  }
};