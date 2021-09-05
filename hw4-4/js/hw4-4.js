window.onload = function(){

  $(".target").css("display", "");
  $(".ringing").css("display", "none");
  setInterval(showTime, 1000);
  var storageAlarm = localStorage.getItem("alarm");
  if (storageAlarm){
    time = JSON.parse(storageAlarm);
    $("#hour").val(time[0]);
    $("#minute").val(time[1]);
  }
  
  $("#alarm").click(function(){
    cnt=0;
    let hour = parseInt($("#hour").val());
    let minute = parseInt($("#minute").val());
    let time=[hour, minute];
    if (hour>=0 && hour<24 && minute>=0 && minute<60){
      localStorage.setItem("alarm", JSON.stringify(time));
      alert(hour+"시 "+minute+"분 알람이 설정되었습니다.");
    } else{
      alert("시간이 유효하지 않습니다.");
    }
  });
};

function formatDate(date) {
  let year = date.getFullYear();
  let month = date.getMonth()+1;
  let day = date.getDate();
  if (month<10){
    month = "0"+month;
  }
  if (day<10){
    day = "0"+day;
  }
  return year+"-"+month+"-"+day;
}

function formatTime(date) {
  let hour = date.getHours();
  let minute = date.getMinutes();
  let second = date.getSeconds();
  if (hour<10)  hour="0"+hour;
  if (minute<10)  minute="0"+minute;
  if (second<10)  second="0"+second;
  
  return hour+":"+minute+":"+second;
}
var cnt=0;
async function showTime(){
  var viewDate = document.getElementById("date");
  var viewDay = document.getElementById("day");
  var viewTime = document.getElementById("time");
  
  var date = new Date();
  
  const WEEKDAY = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];

  viewDate.innerHTML = formatDate(date);
  viewDay.innerHTML = WEEKDAY[date.getDay()];
  viewTime.innerHTML = formatTime(date);

  var storageAlarm = localStorage.getItem("alarm");
  
  if (storageAlarm){
    time = JSON.parse(storageAlarm);
    
    if (cnt<5 && date.getHours()==time[0] && date.getMinutes()==time[1]){
      console.log("ringing!");
      $(".target").css("display", "none");
      $(".ringing").css("display", "");
      await sleep(500);
      console.log("after");
      $(".ringing").css("display", "none");
      cnt++;
    } else{
      $(".target").css("display", "");
      $(".ringing").css("display", "none");

    }
  }
  
}

function sleep(t){
  return new Promise(resolve=>setTimeout(resolve,t));
}

