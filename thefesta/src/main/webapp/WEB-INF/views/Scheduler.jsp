<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Scheduler</title>
<link rel="stylesheet" href="../resources/css/Scheduler.css">
</head>
<body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
	<script>
	
		// 페이지 오픈 시 컴파일
    	$(document).ready(function(){
    		
			// 달력 만들기
			var countyValue = $("#countyValue").val();
    		var districtValue = $("#districtValue").val();
    		var keyword = $("#keyword").val();
    		calendar(countyValue, districtValue, keyword);
    		
	    	// 시, 도 지역 배열
		    const countySelectOptions = [
		        {acode:1, aname:"서울"},
		        {acode:2, aname:"인천"},
		        {acode:3, aname:"대전"},
		        {acode:4, aname:"대구"},
		        {acode:5, aname:"광주"},
		        {acode:6, aname:"부산"},
		        {acode:7, aname:"울산"},
		        {acode:8, aname:"세종특별자치시"},
		        {acode:31, aname:"경기도"},
		        {acode:32, aname:"강원특별자치도"},
		        {acode:33, aname:"충청북도"},
		        {acode:34, aname:"충청남도"},
		        {acode:35, aname:"경상북도"},
		        {acode:36, aname:"경상남도"},
		        {acode:37, aname:"전라북도"},
		        {acode:38, aname:"전라남도"},
		        {acode:39, aname:"제주도"}
		    ]
		    
		    // 시, 도 지역 SELECT BOX OPTION DOM
		    var countyOptionDom = ""
		    for (var i = 0; i < countySelectOptions.length; i++) {
		    	countyOptionDom += "<option value="+countySelectOptions[i].acode+">"+countySelectOptions[i].aname+"</option>"
			}
			$("#countyOptions").append(countyOptionDom);
			
			// 시, 도 지역 변경 시 지역에 맞는 군, 구 데이터 호출 및 DOM 생성
			$("#countyOptions").on("change",function(){
				const county = this.value;
				var countyValue = county;
				$("#countyValue").val(countyValue);
				if (county == "0") {
					$("#districtOptions").val("0");
				}
				var keyword = $("#keyword").val();
				calendar(countyValue, districtValue, keyword);
				$.ajax({
			        type:"GET",
			        url:"districtSelectOption",
			        dataType: "text",
			        data:{
			        	"acode": county
			        	},
			        success:function(data){
			        	var responseData = JSON.parse(data)
		        		var districtList = responseData.districtList;
			        	var districtOptionDom = ""
			        	for (var i = 0; i < districtList.length; i++) {
			        		districtOptionDom+="<option value="+districtList[i].scode+">"+districtList[i].sname+"</option>";
						}
			        	$("#districtOptions").append(districtOptionDom);
			        	// 군, 구 지역 변경 시
			        	$("#districtOptions").on("change",function(){
			        		const district = this.value;
			        		var districtValue = district;
			        		$("#districtValue").val(districtValue);
			        		var keyword = $("#keyword").val();
			        		calendar( countyValue, districtValue, keyword);
			        	})
			        	if(districtList.length == 0){
			        		$("#districtOptions").attr("disabled",true);
			        	}
			        	else{
			        		$("#districtOptions").attr("disabled",false);
			        	}
			        },
			        error: function(jqXHR, textStatus, errorThrown) {
		               if(textStatus=="timeout") {
		                alert("시간이 초과되어 데이터를 수신하지 못하였습니다.");
		               } else {
		                alert("데이터 전송에 실패했습니다. 다시 시도해 주세요");
		               } 
        				return false;
		            }
			    });
			})
    	})
	
   		// 검색 기능
		$(document).on("click", "#searchBtn", function(){
			$("#keyword").val($("#searchBox").val());
			var countyValue = $("#countyValue").val();
    		var districtValue = $("#districtValue").val();
    		var keyword = $("#keyword").val();
    		calendar(countyValue, districtValue, keyword);
		});
		$(document).on("keydown", "#searchBox", function(e){
			if (e.keyCode==13) {
				$("#keyword").val($("#searchBox").val());
				var countyValue = $("#countyValue").val();
	    		var districtValue = $("#districtValue").val();
	    		var keyword = $("#keyword").val();
	    		calendar(countyValue, districtValue, keyword);
			}
		});
		
    	// 달력 넘기기
    	$(document).on("click", ".turnMomentBtn", function(e){
    		if (this.value=="-1") {
    			if (parseInt($("#selectedMonth").val())-1 > 0) {
	    			$("#selectedMonth").val(parseInt($("#selectedMonth").val())-1);
				}
    			else{
    				$("#selectedYear").val(parseInt($("#selectedYear").val())-1);
    				$("#selectedMonth").val(12);
    			}
			}
    		else if (this.value=="+1") {
    			if (parseInt($("#selectedMonth").val())+1 <= 12) {
	    			$("#selectedMonth").val(parseInt($("#selectedMonth").val())+1);
				}
    			else{
    				$("#selectedYear").val(parseInt($("#selectedYear").val())+1);
    				$("#selectedMonth").val(1);
    			}
    		}
    		var countyValue = $("#countyValue").val();
    		var districtValue = $("#districtValue").val();
    		var keyword = $("#keyword").val();
    		calendar(countyValue, districtValue, keyword);
    	});
		
    	// 년도 모달 오픈
    	$(document).on("click", "#selectMomentBtn", function(e){
    		$(".schedulerBox").css('display', 'none');
    		$(".yearModalBack").css('display', 'flex');
    		// 년도 모달이 활성화 되었을 때
    		if($(".yearModalBack").css('display') == 'flex'){
    			let selectedYear = $("#selectedYear").val();
    			const yearModal = document.querySelector('#yearModal');
    			yearFunction(selectedYear);
    			// 년도 모달에 스크롤 이벤트가 발생했을 때
    			yearModal.addEventListener('scroll', function() {
    				if ($('#yearModal').scrollTop() == 0) {
    					$("#selectedYear").val(parseInt($("#selectedYear").val())-1);
    					$(yearModalScroll()).one();
					}
    				else if ($('#yearModal').scrollTop() >= $('.year').height()*2) {
    					$("#selectedYear").val(parseInt($("#selectedYear").val())+1);
    					$(yearModalScroll()).one();
					}
	    			selectedYear = $("#selectedYear").val();
	    			yearModalCreate(selectedYear);
// 	    			console.log("모달 높이 : "+ parseInt($(".yearModalBack").css("height")) - offset*2 + ", 스크롤 높이 : " + $('#yearModal').scrollTop()+", 다음으로 넘어가는 스크롤 높이 : " + $('.year').height()*2);
    			})
    		}
    		
			// 년도 선택 모달 높이 지정
			var offset = parseInt($(".year").css("height"));  
    		var yearModalHeight = offset*7;
    		$("#yearModal").css("height", yearModalHeight+"px");
    	});
    	
   		//년도 선택
   		$(document).on("click", ".yearBtn", function(e){
   			$("#selectedYear").val(this.value);
   			monthFunction(1);
   			$(".monthModalBack").css('display', 'flex');
    		$(".yearModalBack").css('display', 'none');
    		
    		// 월 선택 모달 높이 지정
    		var monthOffset = parseInt($(".month").css("height"));  
//     		alert(monthOffset)
//     		alert(parseInt($(".monthModal").css("height")))
    		var monthModalBack = monthOffset*5;
//     		alert(monthModalBack)
    		$("#monthModal").css("height", monthModalBack+"px");
    		
    		// 월 모달이 활성화 되었을 때
    		if($(".monthModalBack").css('display') == 'flex'){
    			let selectedMonth = $("#selectedMonth").val();
    			const monthModal = document.querySelector('#monthModal');
//     			alert("ok");
    			// 월 모달에 스크롤 이벤트가 발생했을 때
    			monthModal.addEventListener('scroll', function() {
    				if ($('#monthModal').scrollTop() == 0) {
    					if(parseInt($("#selectedMonth").val()) > 1){
	    					$("#selectedMonth").val(parseInt($("#selectedMonth").val())-1);
    					}
    					$(monthModalScroll()).one();
					}
    				else if ($('#monthModal').scrollTop() >= $('.month').height()*2) {
    					if(parseInt($("#selectedMonth").val()) < 12){
	    					$("#selectedMonth").val(parseInt($("#selectedMonth").val())+1);
    					}
    					$(monthModalScroll()).one();
					}
    				selectedMonth = $("#selectedMonth").val();
    				$(monthModalCreate(parseInt(selectedMonth))).one();
	    			console.log("모달 높이 : "+ (parseInt($("#monthModal").css("height")) - monthOffset*2) + ", 스크롤 높이 : " + $('#monthModal').scrollTop()+", 다음으로 넘어가는 스크롤 높이 : " + $('.month').height());
	    			console.log("$('#selectedMonth').val() : "+ $("#selectedMonth").val());
    			})
    		}
   		});
		
		// 월 선택
    	$(document).on("click", ".monthBtn", function(e){
    		$("#selectedMonth").val(this.value);
    		$(".monthModalBack").css('display', 'none');
    		var countyValue = $("#countyValue").val();
    		var districtValue = $("#districtValue").val();
    		var keyword = $("#keyword").val();
    		calendar(countyValue, districtValue, keyword);
    		$(".schedulerBox").css('display', 'flex');
    	});
		
		// 달력 만들기
		function calendar(countyValue, districtValue, keyword) {
			$("#calendarBody").empty();
			
    		// 현재 날짜 정보 지정
    		const today = moment();	
    		
    		// 지정한 날짜 정보가 있을 경우
    		const calendarYear = $("#selectedYear").val();
    		const calendarMonth = $("#selectedMonth").val();
    		console.log("selectedYear : "+calendarYear+", selectedMonth : "+calendarMonth);
    		if (calendarYear!='0'||calendarMonth!='0') {
    			selectDate = today.year(calendarYear);
    			selectDate = today.month(calendarMonth-1);
			}
    		else if(calendarYear=='0'||calendarMonth=='0'){
    			$("#selectedYear").val(today.format("YYYY"));
        		$("#selectedMonth").val(today.format("MM"));
    		}
    		
//     		today.month(10);
    		
	      	const firstWeek = today.clone().startOf('month').week();
	        const lastWeek = today.clone().endOf('month').week() === 1 ? 53 : today.clone().endOf('month').week();
	        $("#selectMomentBtn").text(today.format("YYYY")+"년 "+today.format("MM")+"월");
            
            // 달력 날짜별 DOM 생성
	        let week = firstWeek;
            let daysDom = ""
	        for ( week; week <= lastWeek; week++) {
	        	daysDom += "<tr>";
	        	for (var i = 0; i < 7; i++) {
	        		let days = today.clone().startOf('year').week(week).startOf('week').add(i, 'day');
	        		date = days.format('YYYYMMDD')
	                let textColor = "black";
	        		let dateFestaCnt = festaCnt(date, countyValue, districtValue, keyword);
	                if(i === 0 || i === 6){
	                  textColor = "red";
	                }
	                if (dateFestaCnt === 0) {
	                	if(moment().format('YYYYMMDD') === days.format('YYYYMMDD')){
		                	daysDom += "<td class='dayBlock' id='todayBlock' >";
		                	daysDom += "<span class='dayDateText' style='color:"+textColor+"'>"+days.format('D')+"</span>";
		                	daysDom += "<button id='festaCnt' style='background:gray;box-shadow:none;top:2.5vh;' disabled>"+dateFestaCnt+"</button>"
		                	daysDom += "</td>";
		                }
		                else if(days.format('MM') !== today.format('MM')){
		                	daysDom += "<td class='dayBlock' id='notNowMonth' >";
		                	daysDom += "<span class='dayDateText' style='color:"+textColor+"'>"+days.format('D')+"</span>";
		                	daysDom += "<button id='festaCnt' style='background:gray;box-shadow:none;top:2.5vh;' disabled>"+dateFestaCnt+"</button>"
		                	daysDom += "</td>";
		                }
		                else{
		                	daysDom += "<td class='dayBlock'>";
		                	daysDom += "<span class='dayDateText' style='color:"+textColor+"'>"+days.format('D')+"</span>";
		                	daysDom += "<button id='festaCnt' style='background:gray;box-shadow:none;top:2.5vh;' disabled>"+dateFestaCnt+"</button>"
		                	daysDom += "</td>";
		                }
					}
	                else{
	                	if(moment().format('YYYYMMDD') === days.format('YYYYMMDD')){
		                	daysDom += "<td class='dayBlock' id='todayBlock' >";
		                	daysDom += "<span class='dayDateText' style='color:"+textColor+"'>"+days.format('D')+"</span>";
		                	daysDom += "<button id='festaCnt' value='"+days.format('YYYYMMDD')+"'>"+dateFestaCnt+"</button>"
		                	daysDom += "</td>";
		                }
		                else if(days.format('MM') !== today.format('MM')){
		                	daysDom += "<td class='dayBlock' id='notNowMonth' >";
		                	daysDom += "<span class='dayDateText' style='color:"+textColor+"'>"+days.format('D')+"</span>";
		                	daysDom += "<button id='festaCnt' value='"+days.format('YYYYMMDD')+"'>"+dateFestaCnt+"</button>"
		                	daysDom += "</td>";
		                }
		                else{
		                	daysDom += "<td class='dayBlock'>";
		                	daysDom += "<span class='dayDateText' style='color:"+textColor+"'>"+days.format('D')+"</span>";
		                	daysDom += "<button id='festaCnt' value='"+days.format('YYYYMMDD')+"'>"+dateFestaCnt+"</button>"
		                	daysDom += "</td>";
		                }
	                }
				}
	        	daysDom += "</tr>";
	        }
	        $("#calendarBody").append(daysDom);
	        
	        var selectedYear = parseInt(today.format("YYYY"));
	        $("#selectedYear").val(selectedYear);
		}
		
		// 날짜별 축제 개수 받아오기
		function festaCnt(date, countyValue, districtValue, keyword){
			var result;
			$.ajax({
		        type:"GET",
		        url:"festaCnt",
		        dataType: "text",
		        data:{
		        	"date": date,
                    "countyValue": countyValue,
                    "districtValue": districtValue,
                    "keyword": keyword
		        	},
	        	async :false,
		        success:function(data){
		        	var responseData = JSON.parse(data)
		        	result = responseData.festaCnt;
		        },
		        error: function(jqXHR, textStatus, errorThrown) {
	               if(textStatus=="timeout") {
	                alert("시간이 초과되어 데이터를 수신하지 못하였습니다.");
	               } else {
	                alert("데이터 전송에 실패했습니다. 다시 시도해 주세요");
	               } 
    				return false;
	            }
		    });
			return result;
		}
		
		// 년도 모달 생성 및 스크롤바 위치 지정
		function yearFunction(selectedYear){
			yearModalCreate(selectedYear);
			setTimeout( function() {
				$(yearModalScroll()).one();
		    }, 50);
		}
		
		// 년도 선택 모달 생성
		function yearModalCreate(selectedYear){
			$("#yearModalSelectBox").empty();
	        let yearsDom = "";
        	let standard = 4;
	        let year = selectedYear-standard;
	        for (var i = 0; i <= (standard*2); i++) {
				if ((year+i) == moment().format('YYYY')) {
					if (i == standard) {
						yearsDom += "<li class='year' id='selectYear'><button class='yearBtn' id='nowYear' style='color:#FF0000' value='"+(year+i)+"'>"+(year+i)+"</button></li>";
						continue;
					}
					else if (i == (standard-2)||i == (standard-1)||i == (standard+1)||i == (standard+2)) {
						yearsDom += "<li class='year' style='background-color:#50bcdf80;'><button class='yearBtn' id='nowYear' style='color:#FF0000;' value='"+(year+i)+"'>"+(year+i)+"</button></li>";
						continue;
					}
					else if (i == (standard-3)||i == (standard+3)) {
						yearsDom += "<li class='year'><button class='yearBtn id='nowYear'' style='color:#FF0000;' value='"+(year+i)+"'>"+(year+i)+"</button></li>";
						continue;
					}
					yearsDom += "<li class='year'><button class='yearBtn' value='"+(year+i)+"' style='color:#FF0000' id='nowYear' disabled>"+(year+i)+"</button></li>";
					continue;
				}
				if (i == standard) {
					yearsDom += "<li class='year' id='selectYear'><button class='yearBtn' style='color:#000000' value='"+(year+i)+"'>"+(year+i)+"</button></li>";
					continue;
				}
				else if (i == (standard-2)||i == (standard-1)||i == (standard+1)||i == (standard+2)) {
					yearsDom += "<li class='year' style='background-color:#50bcdf80;'><button class='yearBtn' style='color:#00000080;' value='"+(year+i)+"'>"+(year+i)+"</button></li>";
					continue;
				}
				else if (i == (standard-3)||i == (standard+3)) {
					yearsDom += "<li class='year'><button class='yearBtn' style='color:#00000040;' value='"+(year+i)+"'>"+(year+i)+"</button></li>";
					continue;
				}
				yearsDom += "<li class='year'><button class='yearBtn' style='color:#00000040;' value='"+(year+i)+"'>"+(year+i)+"</button></li>";
			}
			$("#yearModalSelectBox").append(yearsDom);
		}
		
		// 년도 선택 모달 스크롤 위치 지정
		function yearModalScroll(){
    		var offset = parseInt($(".year").css("height"));
    		const yearModal = document.querySelector('#yearModal');
    		yearModal.scrollTop=parseInt(offset);
		}
		
		// 월 모달 생성 및 스크롤바 위치 지정
		function monthFunction(selectedYear){
			monthModalCreate(selectedYear);
			setTimeout( function() {
				$(monthModalScroll()).one();
		    }, 50);
		}
		
		// 월 선택 모달 생성
		function monthModalCreate(month){
			$("#monthModalSelectBox").empty();
			var monthDom = "";
			$("#selectedMonth").val(month);
			var startMonthValue = month - 3;
	        for (var i = startMonthValue; i <= month + 3; i++) {
	        	if (i<1) {
		        	monthDom += "<li class='month' style='visibility:hidden'><button class='monthBtn' disabled>"+i+"</button></li>";
		        	continue;
				}
	        	if (i==month) {
		        	monthDom += "<li class='month' id='selectMonth'><button class='monthBtn' id='selectMonthBtn' value='"+i+"'>"+i+"</button></li>";
				}
	        	else{
		        	monthDom += "<li class='month'><button class='monthBtn' value='"+i+"'>"+i+"</button></li>";
	        	}
	        	if (i==12) {
		        	monthDom += "<li class='month' style='visibility:hidden'><button class='monthBtn' disabled>"+i+"</button></li>";
		        	monthDom += "<li class='month' style='visibility:hidden'><button class='monthBtn' disabled>"+i+"</button></li>";
		        	monthDom += "<li class='month' style='visibility:hidden'><button class='monthBtn' disabled>"+i+"</button></li>";
		        	break;
				}
			}
			$("#monthModalSelectBox").append(monthDom);
		}
		
		// 월 선택 모달 스크롤 위치 지정
		function monthModalScroll(){
    		var offset = parseInt($(".month").css("height"));
    		const monthModal = document.querySelector('#monthModal');
    		monthModal.scrollTop=parseInt(offset);
		}
		
		// 축제 리스트 모달 생성
		$(document).on("click", "#festaCnt", function(){
			$("#festaListBox").empty();
// 			alert(this.value);
			var date = this.value;
			var countyValue = $("#countyValue").val();
    		var districtValue = $("#districtValue").val();
    		var keyword = $("#keyword").val();
			console.log(festaList(date, countyValue, districtValue, keyword));
			var festaData = festaList(date, countyValue, districtValue, keyword);
			var festaDom = "";
			for (var i = 0; i < festaData.length; i++) {
				if (festaData[i].eventstartdate == date) {
					festaDom += "<li class='festaList'><div style='display:inline-block;width:100%;overflow:hidden; white-space:nowrap;'>○"+festaData[i].title+"</div></li>";
				}
				else if (festaData[i].eventenddate == date) {
					festaDom += "<li class='festaList'><div style='display:inline-block;width:100%;overflow:hidden; white-space:nowrap;'>□"+festaData[i].title+"</div></li>";
					
				}
				else{
					festaDom += "<li class='festaList'><div style='display:inline-block;width:100%;overflow:hidden; white-space:nowrap;'>☆"+festaData[i].title+"</div></li>";
				}
			}
			$("#festaListBox").append(festaDom);
			$(".schedulerBox").css('display', 'none');
			$("#festaListModalBack").css('display', 'flex');
		})
		
		function festaList(date, countyValue, districtValue, keyword){
			var result;
			$.ajax({
		        type:"GET",
		        url:"festaList",
		        dataType: "text",
		        data:{
		        	"date": date,
                    "countyValue": countyValue,
                    "districtValue": districtValue,
                    "keyword": keyword
		        	},
	        	async :false,
		        success:function(data){
		        	var responseData = JSON.parse(data)
		        	result = responseData.festaList;
		        },
		        error: function(jqXHR, textStatus, errorThrown) {
	               if(textStatus=="timeout") {
	                alert("시간이 초과되어 데이터를 수신하지 못하였습니다.");
	               } else {
	                alert("데이터 전송에 실패했습니다. 다시 시도해 주세요");
	               } 
    				return false;
	            }
		    });
			return result;
		}
    </script>
	<div class="schedulerBox">
		<div id='filterLine'>
			<div id='selectLine'>
				<div class='selectMenu' id='countyMenu'>
					<select class="selectOptions" id="countyOptions">
						<option value="0" selected>전국</option>
					</select>
				</div>
				<div class='selectMenu' id='districtMenu'>
					<select class="selectOptions" id="districtOptions" disabled>
						<option value="0" selected>전체</option>
					</select>
				</div>
			</div>
			<div id='searchLine'>
				<input id='searchBox' placeholder="원하시는 축제를 입력해주세요."></input>
				<button id='searchBtn'>검색</button>
			</div>
		</div>
		<div class='scheduler'>
			<div class="control">
				<button class="turnMomentBtn" value="-1"><</button>
				<button id="selectMomentBtn"></button>
				<button class="turnMomentBtn" value="+1">></button>
			</div>
			<table id='schedulerCalendar'>
				<thead>
					<tr>
						<td class='dayWeekBlock' style="color:red">일</td>
						<td class='dayWeekBlock'>월</td>
						<td class='dayWeekBlock'>화</td>
						<td class='dayWeekBlock'>수</td>
						<td class='dayWeekBlock'>목</td>
						<td class='dayWeekBlock'>금</td>
						<td class='dayWeekBlock' style="color:red">토</td>
					</tr>
				</thead>
				<tbody id="calendarBody">
				</tbody>
			</table>
			<div id='document'>
				<span>○시작하는 축제</span>
				<span>/</span>
				<span>☆진행중인 축제</span>
				<span>/</span>
				<span>□종료하는 축제</span>
			</div>
		</div>
	</div>
	<div class="yearModalBack">
		<div id="yearModal">
			<ul id="yearModalSelectBox">
			</ul>
		</div>
	</div>
	<div class="monthModalBack">
		<div id="monthModal">
			<ul id="monthModalSelectBox">
			</ul>
		</div>
	</div>
	<div id="festaListModalBack">
		<div id="festaListModal">
			<ul id="festaListBox">
			</ul>
		</div>
	</div>
	<input type="hidden" id="selectedYear" value='0'></input>
	<input type="hidden" id="selectedMonth" value='0'></input>
	<input type="hidden" id="keyword" value=''></input>
	<input type="hidden" id="countyValue" value='0'></input>
	<input type="hidden" id="districtValue" value='0'></input>
</body>
</html>