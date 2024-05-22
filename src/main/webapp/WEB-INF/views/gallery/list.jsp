<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">

</head>


<body>
   <div id="wrap">

      <c:import url="/WEB-INF/views/include/header.jsp"></c:import>
      <!-- //header -->
      <!-- //nav -->

      <div id="container" class="clearfix">
         <div id="aside">
            <h2>갤러리</h2>
            <ul>
               <li><a href="">일반갤러리</a></li>
               <li><a href="">파일첨부연습</a></li>
            </ul>
         </div>
         <!-- //aside -->
         <div id="content">

            <div id="content-head">
               <h3>갤러리</h3>
               <div id="location">
                  <ul>
                     <li>홈</li>
                     <li>갤러리</li>
                     <li class="last">갤러리</li>
                  </ul>
               </div>
               <div class="clear"></div>
            </div>
            <!-- //content-head -->


            <div id="gallery">
               <div id="list">


                  <button id="btnImgUpload">이미지올리기</button>
                  <div class="clear"></div>


                  <ul id="viewArea">

                     <!-- 이미지반복영역 -->
                     <li>
                        <div class="view">
                           <img class="imgItem" src="">
                           <div class="imgWriter">
                              작성자: <strong>유재석</strong>
                           </div>
                        </div>
                     </li>
                     <!-- 이미지반복영역 -->


                  </ul>
               </div>
               <!-- //list -->
            </div>
            <!-- //board -->
         </div>
         <!-- //content  -->
      </div>
      <!-- //container  -->

	
      <c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
      <!-- //footer -->

   </div>
   <!-- //wrap -->

   <!-- 이미지등록 팝업(모달)창 -->
   <div id="addModal" class="modal">
      <div class="modal-content">
         <form id="uploadForm" action="${pageContext.request.contextPath}/gallery/upload" method="post" enctype="multipart/form-data">
            <div class="closeBtn">×</div>
            <div class="m-header">간단한 타이틀</div>
            <div class="m-body">
               <div>
                  <label class="form-text">글작성</label> <input id="addModalContent" type="text" name="" value="">
               </div>
               <div class="form-group">
                  <label class="form-text">이미지선택</label> <input id="file" type="file" name="file" value="">
               </div>
            </div>
            <div class="m-footer">
               <button type="submit">저장</button>
            </div>
         </form>
      </div>
   </div>


   <!-- 이미지보기 팝업(모달)창 -->
   <div id="viewModal" class="modal">
      <div class="modal-content">
         <div class="closeBtn">×</div>
         <div class="m-header">간단한 타이틀</div>
         <div class="m-body">
            <div>
               <img id="viewModelImg" src="">
               <!-- ajax로 처리 : 이미지출력 위치-->
            </div>
            <div>
               <p id="viewModelContent"></p>
            </div>
         </div>
         <div class="m-footer">
            <button>삭제</button>
         </div>
      </div>
   </div>
</body>

<script type="text/javascript">
//DOM tree 생성되었을때
document.addEventListener("DOMContentLoaded", function(){
	//저장버튼 클릭했을때(데이터만 받을거야)//////////////////////////////////////
	let uploadForm = document.querySelector("#uploadForm");
	uploadForm.addEventListener("submit", function(event){
		event.preventDefault();
		console.log("저장 클릭");
		
		//데이터 수집(폼에 있는 데이터 가져오기)
		// FormData 객체를 사용하여 폼 데이터 수집
        let formData = new FormData(uploadForm);
		
        let fileInput = document.querySelector('[name="file"]');
        formData.set("file", fileInput.files[0]); // 파일 데이터 추가 + [0]이 첫번째 파일이기때문에

		
		//서버로 데이터 전송
		axios({
			method: 'post', // put, post, delete
			url: '${pageContext.request.contextPath}/api/gallerys' ,
			headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
			//params: guestVo, //post방식 파라미터로 값이 전달 @ModelAttribute 와 조합
			data: formData, //put, post, delete 방식 자동으로 JSON으로 변환 전달 파람이랑 데이터 둘중 하나만 사용.@RequestBody 와 매치.

			responseType: 'json' //수신타입
		}).then(function (response) {
			console.log(response); //수신데이타
			console.log(response.data);
			let galleryVo = response.data;
			
			
		}).catch(function (error) {
			console.log(error);

		
		
	});
	}
   
</script>




</html>
