<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#drop-zone {
   width: 500px;
   height: 400px;
   border: 1px solid red;
}

#drag-here {
   
}
</style>
<script>
   window.addEventListener("load", function(event){
      
      var dropZone = document.querySelector("#drop-zone");
      
      dropZone.addEventListener("dragenter", function(e){
         /* e.stopPropagation();
         e.preventDefault(); */
         
         //dropZone.style.background="pink";
         e.target.style.background="pink";
         
         console.log("enter");
      });
      
      dropZone.addEventListener("dragover", function(e){
         e.stopPropagation();
         e.preventDefault();
         
         console.log("over");
      });
      
      dropZone.addEventListener("dragleave", function(e){
         /* e.stopPropagation();
         e.preventDefault(); */
         
         //dropZone.style.background="white";
         e.target.style.background="white";
         
         console.log("leave");
      });
      
      dropZone.addEventListener("drop", function(e){
         e.stopPropagation();
         e.preventDefault();
         dropZone.style.background="white";
         
         console.log("drop");
         
         for(var key in e.dataTransfer.types[0])
            console.log(key);      // 속성명을 알아볼 수도 있다
         
         console.log(e.dataTransfer.types[0]);
          /* console.log(typeof e.dataTransfer.types[0]); */
            
         var isValidate = e.dataTransfer
                       && e.dataTransfer.types
                       && e.dataTransfer.types[0].indexOf("Files") >= 0;
            
         if(!isValidate){
            alert("파일 형식이 올바르지 않습니다.");
            return;
         };
         
         var files = e.dataTransfer.files;
         
         var count = e.dataTransfer.files.length;
         if(count > 1){
            alert("파일은 하나식 전송할 수 있습니다.");
            return;
         };

         var size = e.dataTransfer.files[0].size;
         
         if(size > 300*1024*1024){
            alert("죄송합니다. 300MByte를 넘는 파일은 전송할 수 없습니다.");
            return;
         };
         
         
         //   String ***
          // key=value&key=value&key=value
          
          var formData = new FormData();         //   서버전송을 위한 객체
          formData.append("file", files[0]);
          
          var xhr = new XMLHttpRequest();
          
          //   진행상황표시
          xhr.upload.addEventListener("progress", function(evt){
             if(evt.lengthComputable){
                dropZone.textContent = "진척도 : " + Math.round(evt.loaded*100/evt.total)+"%";
             }
          })
          xhr.addEventListener("load", function(evt){
             //서버에 있는 파일 목록을 다시 가져와서 현재 페이지 목록에 포함시켜야 한다.
          });
          
          xhr.open("POST", "../../upload?${_csrf.parameterName}=${_csrf.token}");
          xhr.send(formData);
         //for(var key in files)
            
         //   FormData ***
         // ---------------------------
         //   filesize : ..
         //   filename : ...
         //    body : 01010111101010111010010100101
      });
   });
      

</script>
</head>
<body>
   <div id="drop-zone">
      <strong id="drag-here">Drag here!!</strong>
   </div>
</body>
</html>