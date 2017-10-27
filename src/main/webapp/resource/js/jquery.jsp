<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	//window.addEventListener("load", function(){
	$(function() {
		//DOM 객체들
		//var _chButton = document.getElementById("ch-button");
		//jQuery 객체로 바꾸는 방법 1 jQuery 함수 이용하기
		//var chButton = jQuery(_chButton);
		//jQuery 객체로 바꾸는 방법2 $ 함수 이용하기
		var chButton = $("#chButton");
		//jQuery 객체로 바꾸는 방법3 CSS Selector 이용하기

		// }); */
		//$(document).ready(function() {
		/* $("#ch-button").click(function() {
			//$("#p").css("background", "blue");
			$("#ch-button").next().children("#p").css("background", "yellow");
		}); */
		$("#ch-button").on("click", function() {
			//$("#p").css("background", "blue");
			$("#p").css({
				background : "blue",
				"font-size" : "23px"

			});
			//$("#ch-button").next().children("#p").css("background", "yellow");
		});
	});

	//--속성변경예제 --
	$(function() {
		var chImgButton = $("#ch-img-button");
		var container = $("#img-container");
		var img = $("img");
		var imgSrc = $("#img-src");

		chImgButton.click(function() {
			//img.attr("src", imgSrc.attr("value"));
			img.attr("src", imgSrc.val());
		});

	});
	//--자식노드변경예제 -->
	$(function() {
		var chNodeButton = $("#ch-node-button");
		var container = $("#ch-node-container");
		chNodeButton.click(function() {
			//1.text 노드 추가
			//container.textContent = "<h1>testtestset</h1>"
			//container.text("<h1>testtestset</h1>");
			//2.엘리먼트 노드 추가 
			//container.innerHTML = "<h1>testtestset</h1>"
			container.html("<h1>testtestset</h1>");
		});
	});

	$(function() {
		var addTextNodeButton = $("#add-text-node-button");
		var addImgNodeButton = $("#add-img-node-button");
		var removeNodeButton = $("#remove-node-button");
		var container = $("#node-container");

/* 		var remove = function(e) {
			//container.removeChild(e.target);
			container.remove(e.target);
		} */
		var idx = 0;
		addTextNodeButton.click(function() {
			//1.TextNode 생성 
			var span = $('<span>'); //<>을 쓰면 새로 만듦
			var txt = '안녕하세여' + idx++;
			//2. container(부모) 얻기
			//3. 부모에 자식을 추가
			span.append(txt);
			container.append(span);

			span.click(span.remove());

		});
		addImgNodeButton.click(function() {
			/* //How to 1(성능에 좋음)
			//1 Element 생성
			var img = document.createElement('img');
			img.src="http://t1.daumcdn.net/thumb/R1024x0/?fname=http://cfile25.uf.tistory.com/image/2239B038579139CF067E3D";
			//2. container(부모) 얻기
			//3. 부모에 자식을 추가
			container.appendChild(img);
			img.onclick = remove;
			 */
			//How to 1(성능에 좋음)
			//1 Element 생성
			// 이 방법은 너무 DOM을 사용하는 스타일로 구현한 코드
			/* var img = $('<img />');
			img.attr("src","http://t1.daumcdn.net/thumb/R1024x0/?fname=http://cfile25.uf.tistory.com/image/2239B038579139CF067E3D");
			//2. container(부모) 얻기
			//3. 부모에 자식을 추가
			container.append(img);
			img.click(remove);
			 */
			 
			 $("<img src='http://t1.daumcdn.net/thumb/R1024x0/?fname=http://cfile25.uf.tistory.com/image/2239B038579139CF067E3D' />").appendTo(container).click(remove);
			 
			// 부모를 지정
				 
			//How to 2 (성능에 좋지 않지만 편리함.-> 한두개 넣는 상황의 성능에 영향을 주지 않을 거라면 이를 사용)
			//container.innerHTML += '<img onClick="remove()" src="http://t1.daumcdn.net/thumb/R1024x0/?fname=http://cfile1.uf.tistory.com/image/2134514F579B4C7D165741" />';
			//container.html('<img onClick="remove()" src="http://t1.daumcdn.net/thumb/R1024x0/?fname=http://cfile1.uf.tistory.com/image/2134514F579B4C7D165741" />');

		});
		removeNodeButton.click(function() {
			// 1. 내정된 노드를 지우기
			if (container.hasChildNodes()) {
				container.remove(container.lastChild);
			}
			// 2. 선택된 노드를 지우기

		});
	});
</script>
</head>
<body>
	<!--텍스트 노드 추가 예제 -->
	<input id="add-text-node-button" type="button" value="텍스트노드 추가" />
	<input id="add-img-node-button" type="button" value="이미지노드 추가" />
	<input id="remove-node-button" type="button" value="노드 삭제" />
	<div id="node-container"></div>
	<hr />
	<!--자식노드변경예제 -->
	<input id="ch-node-button" type="button" value="자식노드 변경" />
	<div id="ch-node-container">hello</div>
	<hr />
	<!--속성변경예제 -->
	<input type="text" id="img-src" />
	<input id="ch-img-button" type="button" value="이미지 변경" />
	<div id="img-container">
		<img src="http://i.huffpost.com/gen/5524070/thumbs/o-THE-570.jpg?3" />
	</div>
	<hr />
	<!-- 노드 순환 예제 -->
	<input id="ch-button" type="button" value="배경색 변경" />
	<div>
		<div>1</div>
		<div id="p">
			<div>2</div>
		</div>
		<div>3</div>
	</div>


</body>
</html>