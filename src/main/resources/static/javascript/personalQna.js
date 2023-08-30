/* personalQnaList.html */
//tr 클릭 시 slide되는 용도  <- 만약 시간이 된다면 tr이랑 text랑 각각 slideDown과 fadeOut을 줘서 디자인해보기
$("tr.showContent").click(function() {
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
    }
});

/* personalQnaWriteFrm.html */
//파일 업로드 할 시 작은 이미지 옆으로 생성(미리보기)
$("#photo-picker").on("change",function(){
    //첨부파일은 multiple옵션 사용 시 여러개일 수 있으므로 배열로 처리
    //첨부파일 갯수가 0개가 아니고 && 첫번째 파일이 정상이면
    // const photoImg = $(".photo-img");
    // const imgBoxAdd = $(".img-box-add");
    if(this.files.length != 0 && this.files[0] != 0){
        const reader = new FileReader();//파일정보를 얻어올 수 있는 객체
        //선택한 파일 정보를 읽어옴(비동기처리)
        reader.readAsDataURL(this.files[0]);
        //파일리더가 정보를 다 읽어오면 동작할 함수
        reader.onload = function(e){
            $(".photo-img").attr("src",e.target.result);
            $(".img-box-add").css("display","block");
            //추가된 이미지 삭제하는 함수
            $(".img-box-add").on("click",function(){
                if(confirm("등록된 사진을 삭제하시겠습니까?")){
                    $(".photo-img").attr("src","");
                    $(".img-box-add").css("display","none");
                }
            });
        }
    }else{
        $(".photo-img").attr("src","");
        $(".img-box-add").css("display","none");
    }
});