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
	})
</script>

<h2>ajax json 데이터 요청</h2>
<a id="requestData">데이터 가져오기</a><br>
page : <span id="page"></span><br>
pageSize : <span id="pageSize"></span> <br>

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

</div>
