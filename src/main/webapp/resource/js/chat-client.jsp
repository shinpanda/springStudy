<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="id" value="sp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.addEventListener("load", function(){
	var id = '${id}';
	var chatText = document.querySelector("#chat-text");
	var sendButton = document.querySelector("#send-button");
	var chatList = document.querySelector("#chat-list");
	var socket = null;
	var connButton = document.querySelector("#conn-button");
	
	sendButton.onclick = function() {
		var chat = {id:id, text:chatText.value};
		socket.send(JSON.stringify(chat));
		/* alert(chatText.value); */
	}
	connButton.onclick = function(e) {
		//socket = new WebSocket("ws://211.238.142.93/SpringWeb/resource/chat-server");
		socket = new WebSocket("ws://211.238.142.72/SpringMVC/resource/chat-server");
		socket.onopen = function(evt){
			alert("연결되었습니다.");
			var li = document.createElement("li");
			li.textContent = "접속되었습니다.";
			
			chatList.querySelector("ul").appendChild(li);
		}
		socket.onmessage = function(evt){
			var data = JSON.parse(evt.data);
			var li = document.createElement("li");
			li.textContent = data.id+" : "+ data.text;
			
			chatList.querySelector("ul").appendChild(li);
		}
		socket.onclose = function(evt){
			var li = document.createElement("li");
			li.textContent = "서버와의 연결이 종료되었습니다";
			
			chatList.querySelector("ul").appendChild(li);
		}
	};
});
</script>
</head>
<body>
	<div style="border:1px solid #e9e9e9; width:300px; height:500px;padding:10px; background:orange;">
		<div style="border:1px solid #e9e9e9; background:white;">	
			<input id="conn-button" type="button" value="연결">
		</div>
		<div id="chat-list"  style="border:1px solid #e9e9e9; background:white; height:430px">
			<ul>
				<li></li>
			</ul>
		</div>
		<div  style="border:1px solid #e9e9e9; background:white;height:50px;">
			<textarea id="chat-text"></textarea>
			<input id="send-button" type="button" value="button"/>
		</div>
	</div>
</body>
</html>