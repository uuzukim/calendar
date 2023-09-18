/**
 * 
 */
/*	let settings = {
		url:"",
		//method:"get", // get이여서 생락가능함.
		//data:"",
		dataType:"json",
	};
	$.ajax(settings)
		.done(resp=>{// success함수 역활함
아래코드가 더 간결해서 아래코드로 사용			
		});*/
$(function(){
	let fn_modifyListBody = function(resp){
			let propList = resp.propList;// controller에서 보내준 propList
			let trTags = null;
			if(propList.length > 0){ // proplist값이 있으면 그 값만큼 
				trTags = [];
				$.each(propList, function(index, prop){ //each가 json에서 for문같은 역학을 한다. 값을 list에 출력. function(index, prop매개변수) 
					let tr = $("<tr>").append( // $("<tr>")이건 tr태그를 새로 만들겠다는 뜻이지만 $("tr")이거는 tr태그를 말하는거다. 
						$("<td>").html(prop.propertyName), // td에 propertyName을 넣고, 
						$("<td>").html(prop.propertyValue), // td에 propertyvalue를 넣고 , 알아서 짝맞춰서 </td> 이렇게 닫힘. 
						$("<td>").html(prop.description), 
						$("<td>").html(
							$("<button type='button'>")
								.addClass("delBtn")
								.text("삭제")
						)
					).addClass("datatr") // 이건 tr태그에 <tr class="datatr"> 이렇게 클래스이름 추가해주겠다는 뜻
					.prop("id", prop.propertyName)
					.data("source",prop); // 이건 data-source="prop"이렇게 넣어주겠다는 뜻. 어디에? tr에 왜이렇게해? 중요하고, 은밀하게 값을 넣어줄때 쓴다. key값은 source, value값은 prop
					trTags.push(tr); //그리고 trTag에 이 tr을 넣겠다는 뜻. 
				});
			}else{
				trTags = `<tr><td colspan="4">프로퍼티 없음.</td></tr>`;
			}
			$(listBody).html(trTags); // listbody는 singleViewCase2에 있지롱
		
	}
	
	$.getJSON(location.href)
		.done(fn_modifyListBody);
	
	$(searchForm).on("submit", function(event){
		event.preventDefault();
		let what = this.propertyName.value;// this는 searchform을 가르킴
		$.getJSON("?what="+what) 
			.done(resp=>{
				resp.propList = [];
				resp.propList.push(resp.prop);
				
				fn_modifyListBody(resp);
			});
	});
	
	$(listBody).on("click","tr.datatr", function(event){
		let prop = $(this).data("source");
		//console.log(prop);
		// 상세 조회용 새로운 요청 발생 
		//location.href = "?"; // 이건 동기요청. 현재 url에다가 파라미터 같이 보내겠다는 뜻 
		searchForm.propertyName.value = prop.propertyName;		
		//searchForm.submit(); // 오류남 submit()은. 대신에 requestsubmit써야해
		//$(searchForm).submit(); // 이 3개가 무슨 차이인거야? 
		searchForm.requestSubmit();// 이건 html
		
		
	}).on("click",".delBtn", function(){
		let prop = $(this).parents("tr.datatr").data("source");
		prop.propertyName
		let settings = {
			url:location.href,
			method:"delete",
			data:JSON.stringify( {//이거뭐지? 마샬린!
				propertyName : prop.propertyName
			} ),
			contentType : "application/json",
			dataType : "json"
		};
		
		$.ajax(settings)
			.done(resp=>{
				let propertyName = resp.result.prop.propertyName;
				if(resp.result.success){
					$(`#${propertyName}`).remove();
				}else{
					alert(`${propertyName} 삭제 실패`);
				}
			});
	});
		
		// 폼에대해서 submit을 가로챈다.비동기처리
		let $insertForm = $(insertForm).on("submit", function(event){
			//모든 이벤트는 고유의 액션을 가지고 있따. 
			event.preventDefault(); //submit을 자동제출을 막아줌? 왜? 아래 비동기 처리할거니까 그래서 이거 가지고 있어. 
			
			// 요청 보내기 근데 비동기로 보내야하지? 그럼 ajax사용 
			let settings = {
				url : insertForm.action,
				method:insertForm.method,
				data: JSON.stringify($insertForm.serializeObject()),
				contentType:"application/json;charset=utf-8",
				dataType:"json"
			};
			$.ajax(settings)
				.done(resp=>{ 
					if(resp.propList) {// 성공했으면. resp에 응답 데이터가 있으면. 
						fn_modifyListBody(resp);
						insertForm.reset();// $insertForm은 제이쿼리 객체. 제이쿼리객체는 reset메서드가 없어 그래서 앞에 $ 빼 						
					}else{
						alert(`${resp.result.prop} 등록 실패`);
					}
				});
		});	
});