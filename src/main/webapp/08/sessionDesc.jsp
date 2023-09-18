<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/includee/preScript.jsp" %>
<title>08/sessionDesc.jsp</title>
</head>
<body>
<h4>HttpSession</h4>
<pre>
	HttpSession : http 프로토콜로 형성된 connection 의 한 세션에 대한 정보를 가진 객체. 
	세션 ? 그래서 보통 웹에서는 시간의 의미로 사용한다.  
		시간(세션은 생성할때 ConnectLess를 사용하면 시간의의미) : 어플리케이션을 사용하기 시작하는 순간부터 사용 종료까지의 기간.  
		통로(세션은 생성할때 ConnectFull를 사용하면 통로의의미) : C/S 사이의 유일한 연결 통로(connection)
	
	세션의 생명주기 
		생성 : 어플리케이션을 대상으로 최초의 요청이 발생한 경우. 
			: 트래킹 모드로 저장되어 전송되는 세션 아이디가 없는 요청인 경우, 최초로 판단.
			--> session id 가 부여됨. <%=session.getId() %> 
		유지(session tracking mode) : C/S가 세션 아이디를 공유하는 방법.
			Cookie : ex) JSESSION 라는 이름의 쿠키를 통해 세션 아이디 공유. 
			URL : <a href="sessionDesc.jsp;jsessionid=<%=session.getId()%>">세션유지</a> <--얘를 하니까 sessionid가 안바뀌네?
				// ;jsessionid : 세션파라미터 -> 편지봉투에 세션파라미터를 같이 적어서 보낸것
			SSL(Secure Socket Layer) : 오고가는 모든 데이터를 암호화시킨다. (request, responser가 암호화된다는 뜻) 
		종료(소멸)
			1. timeout(만료시간 ex)30분 ) : 요청과 다음번 요청 사이의 시간의 간격이 timeout 이상으로 벌어지면 세션 소멸. 
			2. cookie 삭제 : JSESSIONID 쿠키 제거 -> dummy session 잔존  
			3. 브라우저 종료시 : dummy session 잔존 
			4. 명시적인 로그아웃 : invalidate 활용 
			<%--
				session.invalidate(); // 얘를 쓰니까 sessionid가 또 다시 바뀜
			--%>
		세션 생성 시점<%=new Date(session.getCreationTime()) %>
		세션 아이디 : <%=session.getId() %>
		timeout : <%=session.getMaxInactiveInterval() %> //현재 세션의 만료시간(초단위)
		마지막 요청 시점 : <%=new Date(session.getLastAccessedTime()) %> //마지막 요청이 발생한 시간을 나타내는 ms
		
		<h4>타이머 : <span id="timerArea"></span></h4>
		<div id="messageArea" >
			<p>세션 연장 여부 확인</p> 
			<input type="button" value="예" class="controlBtn" id="yesBtn"/>
			<input type="button" value="아니오" class="controlBtn" id="noBtn"/>
		</div>
</pre>
<script type="text/javascript">
	const timeout = <%=session.getMaxInactiveInterval()%>;// 현재 세션의 만료시간
	const speed = 100; // 타이머 갱신 속도 
	//formatting : 1:59
	 
	let timer = timeout; 
	let timeFormat = function(time){
    //formatting : 1:59
    if((time || time==0) && time>=0){
       let minute = Math.trunc(time/60); // time을 60으로 나눈 값 (나머지는 버림 정수만 챙김)
       let second = time % 60; // time을 나누고 난 나머지값. 
       return `\${new String(minute).padStart(2,'0')}:\${new String(second).padStart(2,'0')}`
       // padStart는 문자열을 특정길이로 맞추기 위해 사용되는 javascript내장함수이다. 
       // 2글자로 맞추고, 2글자가 안된다면 앞에 '0'을 붙여서 길이를 맞춰 return한다는 뜻. 
    } else{
       throw new Error("시간 데이터는 0이상의 값이 필요함.");
    }
   };
   
   let timerJob=setInterval(()=>{
	   if(timer<=0){
		   clearInterval(timerJob);
	 	   $messageArea.hide();
	   }else{
		   timerArea.innerHTML = timeFormat(--timer);
	   }
   }, 1*speed);
   
   
   let $messageArea = $(messageArea).on("click",".controlBtn",function(){// 이런구조를 디센던트 구조라고함. click중 controlBtn을 클릭했을때 
// 	   this.id
	let id = $(this).prop("id");// 얘는이제 yesbtn이나 nobtn이겠지
	if(id=="yesBtn"){ 
		let settings={ // 이 요청은 crud를 하는건 아니고 돌이오는 바디가 없기때문에 head메소드 사용
			url: "", // 이걸 비워둔 이유는 현재 브라우저의 로케이션을 그대로 쓴다는 뜻
			method:"head",
		}
		$.ajax(settings).done(()=>{// done 은 비동기처리가 완료되면(resp가 오면)근데 여기선 지금 body가 없다~ 그래서 resp가 없다
			timer = timeout;
			setTimeout(()=>{
				$messageArea.show();
			},(timeout-60)*speed);
		});	
		
	}
	$(this).parents("div:first").hide();// 얘는 messageArea를 의미함. 
   }).hide();

   setTimeout(()=>{
	   $messageArea.show();
   },(timeout-60)*speed);
   
//    let timerJob = setInterval(()=>{
//       if(timer<0){
//          clearInterval(timerJob);// clearInterval은 반복실행 중지하는 함수
//          $('.controlBtn').hide();
//       }else{
//          timerArea.innerHTML= timeFormat(--timer);
//       }
//       console.log(timer);
//       if(timer<=60){
//          $('.controlBtn').show();
//            $('#yesBtn').on('click',function(){
<%--               timer = <%=session.getMaxInactiveInterval()%>; --%>
              
//            });
//             $('#noBtn').on('click',function(){
//                   return;
//               });
//       } else{
//          $('.controlBtn').hide();
//       }
//       $messageArea.show();
//  },(timeout-60)*speed);// 일분대기시간
   
   
</script>
</body>
</html>