<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	/* 애니메이션을 구현하는 기술 세가지
	 1. 스크립트 절차를 직접 이용한 애니메이션
	 2. jQuery와 같은 라이브러리를 이용한 애니메이션
	 3. CSS3 애니메이션 기능을 이용한 애니메이션(표준안 동일 x, 호환성) 
	 */
	//1. 스크립트 절차를 직접 이용한 애니메이션
	function animate(target, to) {
		var timer = setInterval(function() {
			var width = parseInt(target.style.width);
			if (width >= to) {
				clearInterval(timer);
				timer = null;
			} else {
				target.style.width = (width + 2) + "px";
			}
		}, 17);
	}

 	window.addEventListener("load", function() {
		var toolButton = document
				.querySelector('#ex1-tool input[type="button"]');
		var item = document.querySelector("#ex1-box .item1");

		toolButton.onclick = function() {
			//animate(item, parseInt(item.parentNode.style.width));
			/*if(timer == null)
				 timer = setInterval(function() {
					var width = parseInt(item.style.width);
					if(width > parseInt(item.parentNode.style.width))
					{
						clearInterval(timer);
						timer = null;
					}else{
						item.style.width = (width+2) + "px";
					}
				}, 17); */
			//3. CSS를 이용한
		}
	});
	//2. jQuery와 같은 라이브러리를 이용한 애니메이션
	/* $(function() {
		var toolButton = $('#ex1-tool input[type="button"]');
		var item =$("#ex1-box .item1");

		toolButton.click(function() {
			//animate(item, parseInt(item.parentNode.style.width));
			//1. 변화를 주는 방법 from -> to
			/* item.css({
				width:"200px"
			}); */
			
			//2. 기간(duration)을 두고 점진적으로 변화를 주는 방법
			/*item.animate({
				width:"200px"
			},2000, 'easeInOutQuint', function() {
				alert("으아라라다러달다ㅓㄹㄷㄹ");
			});
		});
	}); */
</script>
</head>
<body>
	<!-- 1. 너비를 변경하는 애니메이션 -->
	<div id="ex1-tool">
		<input type="button" value="너비늘리기" />
	</div>
	<div id="ex1-box"
		style="width: 500px; height: 300px; background: gray;">
		<div class="item1"
			style="transform:rotate(45deg);width: 100px; height: 100px; background: yellow;"></div>
	</div>
</body>
</html>