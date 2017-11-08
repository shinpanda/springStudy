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

			//span.click(span.remove());

		});
		addImgNodeButton
				.click(function() {
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

					$(
							"<img src='http://t1.daumcdn.net/thumb/R1024x0/?fname=http://cfile25.uf.tistory.com/image/2239B038579139CF067E3D' />")
							.appendTo(container).click(remove);

					// 부모를 지정

					//How to 2 (성능에 좋지 않지만 편리함.-> 한두개 넣는 상황의 성능에 영향을 주지 않을 거라면 이를 사용)
					//container.innerHTML += '<img onClick="remove()" src="http://t1.daumcdn.net/thumb/R1024x0/?fname=http://cfile1.uf.tistory.com/image/2134514F579B4C7D165741" />';
					//container.html('<img onClick="remove()" src="http://t1.daumcdn.net/thumb/R1024x0/?fname=http://cfile1.uf.tistory.com/image/2134514F579B4C7D165741" />');

				});
		removeNodeButton.click(function() {
			// 1. 내정된 노드를 지우기
			/* 	if (container.hasChildNodes()) {
					container.remove(container.lastChild);
				} */
			//container.children().last().remove();
			//$("#node-container :last-child").remove();
			//
			//$("#node-container img").eq(0).remove();
			//$("#node-container img").get(0).src ="http://i.huffpost.com/gen/5524070/thumbs/o-THE-570.jpg?3";//DOM객체로 꺼내쓸 때 get()
			/* $("#node-container img").each(function(index){	
				$(this).remove();
			}); */
			container.children().each(function(index) {
				$(this).remove();
			});
			// 2. 선택된 노드를 지우기

		});
	});
	//!--노드 바꾸기 예제 -->
	$(function() {
		var swapNodeButton = $("#swap-node-button");
		var container = $("#swap-node-container");

		swapNodeButton.click(function() {
			//var nodes = $("#swap-node-container img");
			var nodes = container.find("img");
			var node1 = nodes.eq(0);
			var node2 = nodes.eq(1);
			var node3 = nodes.eq(2);
			/* var nodes = container.find("img");
			
			var node1 = nodes.eq(0);
			var node2 = nodes.eq(1);
			var node3 = nodes.eq(2);
			 */
			/* var node1 = container.firstElementChild;
			var node3 = container.lastElementChild; */
			//var oldNode = container.replaceChild(node1, node3);
			//container.insertBefore(node3, container.firstChild);
			//var old = container.find("img").first().replaceWith(container.find("img").last());
			//container.children().last().after(old);
			//DOM 기능을 이용한 코드
			// var oldNode = container.replaceChild(node2, node3);
			//container.insertBefore(node3, container.firstElementChild);
			//jQuery 기능을 이용한 코드
			/* var oldNode = node3.replaceWith(node2);
			node1.before(oldNode); */

			//jQuery 스러운 코드
			node3.replaceWith(node2).insertBefore(node1);
		});
	});
	//!--노드 바꾸기 예제 2 -->
	$(function() {
		var moveUpButton = $("#move-up-button");
		//var radioButton = document.
		var container = $("#move-up-container");

		/* var moveUpButton = document.querySelector("#move-up-button");
		//var radioButton = document.
		var container = document.querySelector("#move-up-container");
		 */
		var checkedId = 0;

		var tbody = container.find("tbody");
		//var td = tbody.querySelector("tr:first-child td:last-child")
		var td = tbody.children().first().children().last();
		//var td = tbody.children(":first-child").children(":last-child");
		var tr = null;

		td.click(function(e) {
			//e.stopPropagation();

			console.log("td");
			//e.target.style.background = "green";
			$(e.target).css("background", "green");
		});

		tbody.click(function(e) {
			//if ($(e.target).prop("nodeName") == "INPUT") {
			if ($(e.target).prop("nodeName") == "INPUT") {
				tr = $(e.target).parent().parent();
				//tr = $(e.target).parents("tr");

				//var oldNode = container.replaceChild(tr1, tr2);
				//container.insertBefore(oldNode, tr3);

			}

			//console.log("tbody");
			//alert("target : " + e.target.nodeName + ", current : " + e.currentTarget.nodeName);
			//e.target.style.background = "pink";
			//e.target.style.background = "pink";
		});

		/* var trs = container.querySelectorAll("tbody tr");
		
		for(var i=0; i<trs.length; i++)
		   trs[i].onclick = function(e){
		      e.target.style.background = "pink";
		   }; */

		moveUpButton.click(function() {

			if (tr.get(0) == null)
				return;

			var container = tr.parent();

			var bf = tr.prev();
			var af = tr.next();

			if (bf.get(0) == null)
				bf = container.children().last();

			//container.replaceChild(tr, bf);
			var oldNode = bf.replaceWith(tr);
			//if (af.get(0) == null){
			if (af.length == 0) {
				container.append(bf);
			} else {
				af.before(bf);
				//bf.insertBefore(af);
				//container.insertBefore(bf, af);
			}
		});
	});
	/* --- 엘리먼트의 기본 행위 막기 ------------------------------------------------- */
	$(function() {
		var titleText = $("form input[name]");
		var submitButton = $("form input[type='submit']");
		var cancelButton = $("form a");

		submitButton.click(function(e) {
			if (titleText.val() == "") {
				alert("제목을 입력하세요.");
				e.preventDefault();
			}
		});

		cancelButton.click(function(e) {
			if (titleText.val() != "") {
				if (!confirm("작성중이던 입력을 취소하시겠습니까?"))
					e.preventDefault();
			}
		});
	});
	/* --- 노드복제 예제----------------------------------------------------------------- */

	$(function(){   
	   var cloneButton = $("#ex-clone input[value='단순복제']");
	   var tbody = $("#ex-clone tbody");
	      
	   cloneButton.click(function(e){
	      //var tr = tbody.find("tr");
	      
	      /* var clone = tr.clone(true);
	      tbody.append(clone); */
	      tbody.find("tr").clone(true).appendTo(tbody);
	   });
	   
	});
	$(function(){   
		   var cloneButton =$("#ex2-clone input[value='단순복제']");
		   var ajaxButton = $("#ex2-clone input[value='Ajax요청']");
		   var tbody =$("#ex2-clone tbody");
		   var template = $("#ex2-clone template");
		   var container = $("#ex2-clone div:first-child");
		   
		   var data = [
		      {id:"1", title:"자바스크립트 야호~", writerId:"newlec"},
		      {id:"2", title:"자바도 야호~", writerId:"dragon"},
		      {id:"3", title:"둘다 ~", writerId:"wa~~~"}
		   ];
		   
		   ajaxButton.click(function(e){
			   /* 3. 비동기형으로 문서를 요청한 방식 */
				/* $.get("../../customer/notice-ajax", function(data) {
					alert(data);
				});	*/
// 				 $.get("../../customer/notice-ajax", function(data) {
// 					 var json = JSON.parse(data);
// 					 alert(json[0].writerName);
// 					});
				$.ajaxSetup({
					 scriptCharset: "utf-8" ,
					 contentType: "application/json; charset=UTF-8",
				});
				//json만 쓸거라 전제조건하에
				$.getJSON("../../customer/notice-ajax")
				.done(function(json){
					alert(json[0].writerName);
					////console.log("공통부분");
				})
				.fail(function(data){
					//console.log("공통부분");	
				})
				.always(function(data) {
					console.log("공통부분");
				})
				
					
		      /* 2. 비동기형으로 문서를 요청한 방식 */
		      //var xhr = new XMLHttpRequest();
		      /* xhr.onreadystatechange = function(e){
		         if(xhr.readyState == 4)
		            data = eval(xhr.responseText);
		      }; */
		      /* xhr.load(function(){
		         //alert("tt");
		         data = JSON.parse(xhr.responseText);
		         // 2. ajax icon 제거
		         container.removeChild(container.lastChild);
		      });
		      xhr.error(function(e){
		         alert("예기치 못한 오류가 발생하였습니다.");
		      });
		      xhr.open("GET", "../../customer/notice-ajax", true);
		      xhr.send(); */   
		      // 1. ajax icon 추가
		      /* var img = $("<img>");
		      img.src = "../images/ajax-loader.gif";
		      container.append(img);
		       */
		      /* 1. 동기형으로 문서를 요청한 방식 */
		      /* var xhr = new XMLHttpRequest();
		      xhr.open("GET", "../../customer/notice-ajax", false);
		      xhr.send();
		      data = eval(xhr.responseText); */      
		      
		      
		   });
		   
		   cloneButton.click(function(e){
		      
		      // 현재 브라우저가 template 태그를 지원하는지에 대한 확인
		      //if('content' in template){
		   if('content' in template.get(0)){
		         // 1. template의 content에 값을 설정하고 노드를 복제 하는 경우
		         /* var tds = template.content.querySelectorAll("td");
		         tds[0].appendChild(document.createTextNode("1"));
		         tds[1].textContent = "test title";
		         tds[2].textContent = "newlec";
		         
		         var clone = document.importNode(template.content, true); */         
		         
		         // 2. 복제를 한 후에 content를 설정하는 경우         
		         for(var i=0; i<data.length;i++)
		         {//jquery가 지원하지 않는 기능....
		            var clone = $(document.importNode(template.prop("content"), true));
		            //var clone = template.clone(true);
		            
		            var tds = clone.find("td");
		            //tds.eq(0).html(tds.eq(0).html() + data[i].id);
		            tds.eq(0).append(data[i].id);
		            tds.eq(1).text(data[i].title); 
		            tds.eq(2).text(data[i].writerId);

		            // 복제된 clone(tr)을 노드 트리에 추가
		            tbody.append(clone);         
		         }
		         
		      }
		      
		      /* var obj = {kor:30, eng:40, math:50};
		      
		      obj.com = 60;
		      
		      if( 'com' in obj)
		         alert(obj.kor + obj.com); */      
		   });
		   
		});
</script>
</head>
<body>
	<!--Template 태그를 이용한 노드복제 and Ajax 요청 예제 -->
   <div id="ex2-clone">
      <div>
         <input type="button" value="단순복제" />
         <input type="button" value="Ajax요청" />
      </div>
      <div id="clone-container">
         <table border="1">
            <thead>
               <tr>
                  <td></td>
                  <td>코드</td>
                  <td>제목</td>
               </tr>
            </thead>
            <tbody>            
            </tbody>
         </table>         
         <template>
            <tr>
               <td><input name="id" type="radio" value="1" /></td>
               <td></td>
               <td></td>
            </tr>   
         </template>
      </div>
   </div>
   <hr />
	<!--노드복제 예제 -->
	<div id="ex-clone">
		<div>
			<input type="button" value="단순복제" />
		</div>
		<div id="clone-container">
			<table border="1">
				<thead>
					<tr>
						<td></td>
						<td>코드</td>
						<td>제목</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input name="id" type="radio" value="1" /></td>
						<td>1</td>
						<td>아~ 괜히 하자고 했지?</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<hr />
	<!--기본 행위 막기 예제 -->
	<form>
		<input type="text" name="title" /><br /> <input type="submit"
			value="전송" /> <a href="">취소</a>
	</form>
	<hr />
	<!--노드 바꾸기 예제 2 + 이벤트 심화 : 버블링과 캡처링 -->
	<input id="move-up-button" type="button" value="위로 옮기기" />
	<div id="move-up-container">
		<table border="1">
			<thead>
				<tr>
					<td></td>
					<td>코드</td>
					<td>제목</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input name="id" type="radio" value="1" /></td>
					<td>1</td>
					<td>아~ 괜히 하자고 했지?</td>
				</tr>
				<tr>
					<td><input name="id" type="radio" value="2" /></td>
					<td>2</td>
					<td>졸ㄹ려~~</td>
				</tr>
				<tr>
					<td><input name="id" type="radio" value="3" /></td>
					<td>3</td>
					<td>아~ 졸ㄹ려~~</td>
				</tr>
				<tr>
					<td><input name="id" type="radio" value="4" /></td>
					<td>4</td>
					<td>정말 졸ㄹ려~~</td>
				</tr>
				<tr>
					<td><input name="id" type="radio" value="5" /></td>
					<td>5</td>
					<td>미쳐버리게 졸ㄹ려~~</td>
				</tr>
			</tbody>
		</table>

		<img src="../images/w3c.png" /> <img src="../images/microsoft.png" />
	</div>
	<hr />
	<!--노드 바꾸기 예제 -->
	<input id="swap-node-button" type="button" value="노드 바꾸기" />
	<div id="swap-node-container">
		<img src="../images/answeris.png" /><img src="../images/notepubs.png" /><img
			src="../images/microsoft.png" />
	</div>
	<hr />
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