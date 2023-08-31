/* personalQnaList.html */
//tr 클릭 시 slide되는 용도  <- 만약 시간이 된다면 tr이랑 text랑 각각 slideDown과 fadeOut을 줘서 디자인해보기
$("tr.showContent").click(function() {
    // console.log($(this).next().children().children().eq(5));
    //먼저 전체 슬라이드를 올려주는 코드
    const allContent = $(".qnaContent")
    // allContent.fadeOut();
    allContent.slideUp();
    //내가 원하는 컨텐츠만 내려주는 코드
    const usualQnaContent = $(this).next().find(".qnaContent");
    //컨텐츠가 visible(slideDown상태)인 경우
    if(usualQnaContent.is(':visible')){
        usualQnaContent.slideUp();
        // usualQnaContent.fadeOut();
        //  
    }else{
        // usualQnaContent.fadeIn();
        usualQnaContent.slideDown();
        //댓글이 있는 경우에만 qnaNo에 맞는 댓글 열어주는 코드
        const qnaNo = $(this).find(".qna-no").val();
        $.ajax({
            url : "/personalQna/selectQnaNo",
            type : "get",
            data : {qnaNo : qnaNo},
            success : function(data){
                // console.log(data);
                console.log(data=="");
                console.log(qnaNo);
                if(data == ""){
                }else{
                    const commentBox = $(this).next().children().children().eq(4);
                    const result = $("<div>").append(data.personalCommentContent);
                    commentBox.append(result);
                    const inputCommentBox = $(this).next().children().children().eq(5);
                    // inputCommentBox.remove();
                }
            }
        });
    }
});
//삭제버튼 -> 시간이 난다면 모달창으로 처리하기
function personalQnaDelete(qnaNo){
    if(confirm("문의를 삭제하시겠습니까?")){
        location.href = "/personalQna/delete?qnaNo="+qnaNo;
    }
}

$("#delete-inputFrm").on


/* personalQnaWriteFrm.html */
//파일 업로드 할 시 작은 이미지 옆으로 생성(미리보기)
$("#photo-picker").on("change",function(){
    //첨부파일은 multiple옵션 사용 시 여러개일 수 있으므로 배열로 처리
    //첨부파일 갯수가 0개가 아니고 && 첫번째 파일이 정상이면
    const photoImg = $(".photo-img");
    const imgBoxAdd = $(".img-box-add");
    if(this.files.length != 0 && this.files[0] != 0){
        const reader = new FileReader();//파일정보를 얻어올 수 있는 객체
        //선택한 파일 정보를 읽어옴(비동기처리)
        reader.readAsDataURL(this.files[0]);
        //파일리더가 정보를 다 읽어오면 동작할 함수
        reader.onload = function(e){
            photoImg.attr("src",e.target.result);
            imgBoxAdd.css("display","block");
            //추가된 이미지를 클릭했을 때 삭제하는 함수
            imgBoxAdd.on("click",function(){
                photoImg.attr("src","");
                imgBoxAdd.css("display","none");
                //input에 value값을 바꿔주는 코드
                $("#photo-picker").val("");
            });
        }
    }else{
        photoImg.attr("src","");
        imgBoxAdd.css("display","none");
    }
});