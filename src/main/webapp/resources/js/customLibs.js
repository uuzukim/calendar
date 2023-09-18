/**
 * 
 */
	$.fn.serializeObject = function(){
		let data = {};
		let nameSet = new Set();
		//this is jQuery Object

		this.find(":input[name]").each((idx,ipt)=>{
			nameSet.add(ipt.name)
		});
	  let $formThis = this;
	  nameSet.forEach(n=>{ // 화살표함수가 가르키는 것은 윈도우.
		  let $ipt = $formThis.find(`:input[name=${n}]`);
	  	  let type = $ipt.attr("type");
	  	  let value = null;
	  	  if(type=="radio"){
	  		  value = $ipt.filter((idx, rdoIpt)=>{return rdoIpt.checked;}).val();
	  	  }else if(type =="checkbox"){
	  		  value = $ipt.filter((idx, chkIpt)=>chkIpt.checked)
	  		 			 .get()
	  		 			 .map(i=>i.value);	  	  
	  	  }else if(type=="number"){
	  		  value = $ipt.val(); // value 속성 값을 반환(attribute는 String타입 )
	  		  value = parseInt(value);
	  	  }else{
	  		  value = $ipt.val();// value 속성 값을 반환(attribute는 String타입 )
	  	  }
			  data[n] = value;	  		  

	  });	  							  
	  return data;
  }
	$.timeFormat = function(time){
    //formatting : 1:59
    if((time || time==0) && time>=0){
       let minute = Math.trunc(time/60); // time을 60으로 나눈 값 (나머지는 버림 정수만 챙김)
       let second = time % 60; // time을 나누고 난 나머지값. 
       return `${new String(minute).padStart(2,'0')}:${new String(second).padStart(2,'0')}`
       // padStart는 문자열을 특정길이로 맞추기 위해 사용되는 javascript내장함수이다. 
       // 2글자로 맞추고, 2글자가 안된다면 앞에 '0'을 붙여서 길이를 맞춰 return한다는 뜻. 
    } else{
       throw new Error("시간 데이터는 0이상의 값이 필요함.");
    }
   };

	const speed = 100;
	
	$.fn.timer=function(){
		this.each(function(index, element){
			const $element = $(this);
			const timeout = $element.data("timeout");			
			if(!timeout || !/^\d+$/.test(timeout)) throw new Error(`타이머(${timeout})는 숫자로 구성함.`);
			/*타이머의 조건 
			1. 타이머 초기값 
			2. 1초마다 타이머를 차감
				1). 차감된 값을 유지할 수 있는 프로퍼티
				2). 차감을 시키기 위한 스케줄링 작업
			3. 차감되는 타이머 값은 매 시점마다 타이머의 영역에 출력. 
			4. 타이머의 값은 02:00 과 같은 형태로 출력.
			5. optinal : 필요하다면 타이머 영역 다음에 메시지 창을 생성함. 해당 메시지는 1분남은 시점에 랜더링한다. 
				1). 메시지 영역 프로퍼티 추가 
				2). 메시지 처리를 위한 지연 작업 스케쥴링.
				3).
			*/ 
			
			function TimerInfo(timeout,timerArea, msgFlag=false){ //함수 앞글자가 대문자면 객체 생성자함수로 사용됨
				this.timeout = timeout-1;// 여기서 this는 아래 timerInfo를 가르킴. 
				this.timerArea = timerArea;

				let _makeMessageDiv = function(element){
					let $messageDiv = $("<div>").addClass("message-area").append(
						$("<p>").html("세션 연장 여부 확인"),
						$("<input>").attr({
							type:"button",
							value:"예",
						}).addClass("controlBtn").data("role","yesBtn"),
						$("<input>").attr({
							type:"button",
							value:"아니오"
						}).addClass("controlBtn").data("role","noBtn")
					).on("click",".controlBtn" ,function(event){
						let $btn = $(event.target);
						let role = $btn.data('role');
						if(role == 'yesBtn'){
							
							$.ajax({
								url:element.dataset.requestUrl??"",
								method:"head"
							}).done(()=>{// 응답데이터가 돌아오면 
								// 타이머값도 초기화, 메시지 작업도 초기화. 
								element._timerInfo.init();
							});
						}
						$btn.parents(".message-area").remove();	
						/*delete element._timerInfo.$messageArea 
						delete element._timerInfo.messageJob*/
						
					}).hide().insertAfter(element);
					return $messageDiv;
				}
				
				this.init = function(){
					this.timer= timeout;
					if(msgFlag){
					this.$messageArea = _makeMessageDiv(this.timerArea);
					this.messageJob = setTimeout((function(){
						this.$messageArea.show();
					}).bind(this), (this.timeout-60)*speed);
				}
				}
				this.init();
				
				this.destory = function(){
					clearInterval(this.timerJob);
					if(this.$messageArea){
						this.$messageArea.remove();
						clearTimeout(this.messageJob);
					}
					console.log("delete 전 : ", this.timerArea._timerInfo);
					delete this.timerArea._timerInfo;
					console.log("delete 후 : ", this.timerArea._timerInfo);
				}				
				this.timerJob = setInterval((function(){
					if(this.timer<=0){
						this.destory();
					}else{
						this.timerArea.innerHTML =$.timeFormat(--this.timer);						
					}
				}).bind(this), 1*speed); // bind함수는 현재 함수의 주인객체를 변경한다. 
			}
			let msgFlag = $element.data("msgFlag");
			element._timerInfo = new TimerInfo(timeout,element,msgFlag); // 전역말고 함수내에서 사용하고 싶은 이름은 아래 _를 사용한다. 
			console.log(element._timerInfo);
		});
		return this;
	}
	/*
	$(document).ready(function(){});
	$(document).on("ready",function(){});
	*/
	$(function(){
		$("[data-timeout]").timer();
	});