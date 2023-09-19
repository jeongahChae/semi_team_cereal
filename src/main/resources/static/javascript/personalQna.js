/* personalQnaList.html */
//tr 클릭 시 slide되는 용도  <- 만약 시간이 된다면 tr이랑 text랑 각각 slideDown과 fadeOut을 줘서 디자인해보기
// tr.showContent 요소를 클릭할 때의 이벤트 핸들러
$("tr.showContent").click(function () {
  // 전체 qnaContent를 숨김
  const qnaContent = $(".qnaContent");
  qnaContent.slideUp();

  // 클릭한 요소 다음에 있는 qnaContent 요소 선택
  const usualQnaContent = $(this).next().find(".qnaContent");
  // 클릭한 요소 다음에 있는 commentBox 선택
  const commentBox = $(this).next().children().children().eq(4);
  // 클릭한 요소 다음에 있는 inputCommentBox 선택
  const inputCommentBox = $(this).next().children().children().eq(5);
  // 클릭한 요소 다음에 있는 fixBtn 선택
  const fixBtn = $(this).next().children().find(".fix-btn");

  // qnaContent가 이미 보이는 경우에는 아무 동작도 하지 않음
  if (usualQnaContent.is(":visible")) {
    // 아무 동작도 하지 않음
  } else {
    // qnaContent를 보이도록 슬라이드 다운 애니메이션 적용
    usualQnaContent.slideDown();

    // 클릭한 요소에서 qna-no 값을 가져옴
    const qnaNo = $(this).find(".qna-no").val();

    // 이미 생성된 <p> 요소가 없는 경우에만 생성
    if (commentBox.find("p").length === 0) {
      // 서버로 Ajax 요청을 보냄
      $.ajax({
        url: "/personalQna/selectQnaNo",
        type: "get",
        data: { qnaNo: qnaNo },
        success: function (data) {
          // 받아온 데이터가 비어있는 경우 아무 동작도 하지 않음
          if (data === "") {
            // 아무 동작도 하지 않음
          } else {
            // <p> 요소를 생성하고 데이터를 추가하여 commentBox에 추가
            const p = $("<p>");
            const commentContent = p.append(data.personalCommentContent);
            commentBox.append(commentContent);
            // inputCommentBox와 fixBtn을 제거
            inputCommentBox.remove();
            fixBtn.remove();
          }
        },
      });
    }
  }
});

//삭제버튼 -> 시간이 난다면 모달창으로 처리하기
function personalQnaDelete(qnaNo) {
  if (confirm("문의를 삭제하시겠습니까?")) {
    location.href = "/personalQna/delete?qnaNo=" + qnaNo;
  }
}

/* personalQnaWriteFrm.html */
//파일 업로드 할 시 작은 이미지 옆으로 생성(미리보기)
$("#photo-picker").on("change", function () {
  //첨부파일은 multiple옵션 사용 시 여러개일 수 있으므로 배열로 처리
  //첨부파일 갯수가 0개가 아니고 && 첫번째 파일이 정상이면
  const photoImg = $(".photo-img");
  const imgBoxAdd = $(".img-box-add");
  if (this.files.length != 0 && this.files[0] != 0) {
    const reader = new FileReader(); //파일정보를 얻어올 수 있는 객체
    //선택한 파일 정보를 읽어옴(비동기처리)
    reader.readAsDataURL(this.files[0]);
    //파일리더가 정보를 다 읽어오면 동작할 함수
    reader.onload = function (e) {
      photoImg.attr("src", e.target.result);
      imgBoxAdd.css("display", "block");
      //추가된 이미지를 클릭했을 때 삭제하는 함수
      imgBoxAdd.on("click", function () {
        photoImg.attr("src", "");
        imgBoxAdd.css("display", "none");
        //input에 value값을 바꿔주는 코드
        $("#photo-picker").val("");
      });
    };
  } else {
    photoImg.attr("src", "");
    imgBoxAdd.css("display", "none");
  }
});
