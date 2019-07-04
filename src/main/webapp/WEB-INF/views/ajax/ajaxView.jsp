<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	#requestData{
		cursor: hand;
	}
</style>
<script>
	$(document).ready(function(){
		console.log("ready");
		
		//requestData 클릭시 이벤트
		$("#requestData").on("click", function(){
			$.ajax({
				url : "/ajax/requestData",
				method : "post",
				success:function(data){
					console.log(data);
					$("#page").text(data.pageVO.page);
					$("#pageSize").text(data.pageVO.pageSize);
				}
			});
		})
		
		//requestDataResponseBody 클릭시 이벤트
		$("#requestDataResponseBody").on("click", function(){
			$.ajax({
				url : "/ajax/requestDataResponseBody",
				method : "post",
				success:function(data){
					console.log(data);
					// data : {page : 5, pageSize:10}
					// data.pageVO : {pageVO : {page:5, pageSize:10}}
					$("#pageResponseBody").text(data.page);
					$("#pageSizeResponseBody").text(data.pageSize);
				}
			});
		})
		
		//유저 클릭시 이벤트 핸들러
		$("#user").on("click", function(){
			if($("#userId").val()==""){
				alert("사용자 ID를 입력해주세요.")
				return;
			}
			
			$.ajax({
				url : "/ajax/user",
				method : "get",
				data : "userId=" + $("#userId").val(),
				success:function(data){
					if(data.userVO==null){
						alert("사용자 정보가 없습니다.");
						return;
					}
					/*
						name : <input id="name" type="text" readonly/><br><br>
						alias : <input id="alias" type="text" readonly/><br><br>
						birth : <input id="birth" type="text" pattern="yyyy-MM-dd" readonly/>
						$("#name").val(data.userVO.name);
						$("#alias").val(data.userVO.alias);
						$("#birth").val(data.userVO.birth);
					*/
					
					var html="";
					html = html + "name : <input type=\"text\" id=\"name\" readonly value=\""+data.userVO.name + "\"/><br><br>";
					html = html + "alias : <input type=\"text\" id=\"alias\" readonly value=\""+data.userVO.alias + "\"/><br><br>";
					html = html + "birth : <input type=\"text\" id=\"birth\" readonly value=\""+data.userVO.birth + "\"/><br><br>";
					
					$("#userJsonInfo").html(html);
				}
			})
		})
		
		// userHtml 클릭시
		$("#userHtml").on("click", function(){
			$.ajax({
				url:"/ajax/userHtml",
				method : "post",
				data : $("#frm").serialize(),
				success : function(data){
// 					document.getElementById("userInfo").innerHTML(data);
					$("#userInfo").html(data);
				}
			})
		})
		
		//전송할 json 객체를 준비
		/*public class UserVO{
			private String userId;
			private String pass;
			public String userId(){...};
		}*/
		var user = {userId : "brown", pass : "brown1234"};
		//JSON.stringify(user) : 자바스크립트 객체를 json 문자열로 생성
		// JSON.parse("json문자열") : json 문자열을 자바스크립트 객체로 변경
		$("#userFormString").text("userId=brown&pass=brown1234");
		$("#userJsonString").text(JSON.stringify(user));
		
		
		//ResponseBody 데이터 전송
		$("#userJsonStringBtn").on("click", function(){
			$.ajax({
				url : "/ajax/requestBody",
				method : "post",
				contentType  : "application/json",	// ajax를 통해서 보내는 데이터 형식이 json임을 알려준다.
				dataType : "json",					// server 측으로부터 받고자 하는 데이터타입(Accept 헤더)
// 				dataType : "xml",
				data : JSON.stringify(user),
				success : function(data){
					console.log(data);
					//xml
// 					$("#userJsonResult .userId").text(data.getElementsByTagName("userId")[0].childNodes[0]);
// 					$("#userJsonResult .pass").text(data.getElementsByTagName("pass")[0].childNodes[0]);
					
					//json
					$("#userJsonResult .userId").text(data.userId);
					$("#userJsonResult .pass").text(data.pass);
				}
			});
		})
	})
</script>

<h2>ajax json 데이터 요청</h2>
<a id="requestData">데이터 가져오기</a><br>
page : <span id="page"></span><br>
pageSize : <span id="pageSize"></span> <br>

<h2>ajax json 데이터 요청(@ResponseBody)</h2>
<a id="requestDataResponseBody">데이터 가져오기</a><br>
page : <span id="pageResponseBody"></span><br>
pageSize : <span id="pageSizeResponseBody"></span> <br>

<h2>ajax json 데이터 요청(user)</h2>
<a id="user">데이터 가져오기</a><br>
userId : <input id="userId" value="brown" type="text"/><br><br>
<div id="userJsonInfo"></div>


<h2>ajax HTML 데이터 요청(user)</h2>
<a id="userHtml">데이터 가져오기</a><br>
<div id="userInfo">
<form id="frm" action="" method="post">
userId : <input id="userIdHtml" name="userId" value="brown" type="text"/><br><br>
</form>


<h2>ajax json 데이터 보내기</h2>
<a id="userJsonStringBtn">데이터 보내기</a><br>
요청 보내는 데이터(기존) : <div id="userFormString"></div>
요청 보내는 데이터 : <div id="userJsonString"></div>
받는 데이터 : <div id=""></div>
<div id="userJsonResult">
	userId : <span class="userId"></span><br>
	pass : <span class="pass"></span>
</div>
</div>
