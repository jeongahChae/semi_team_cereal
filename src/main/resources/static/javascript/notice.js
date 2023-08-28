

/* noticeView.html */
//삭제버튼 -> 시간이 난다면 모달창으로 처리하기
function noticeDelete(noticeNo){
    if(confirm("게시글을 삭제하시겠습니까?")){
        location.href = "/notice/delete?noticeNo="+noticeNo;
    }
}


/* writeFrm.html */
//등록버튼(모달용)
function checkValue(){
    const titleInput = $("#writeFrmTitle").val();
    const contentInput = $("#writeFrmContent").val();
    if((titleInput.trim() === "")||(contentInput.trim() === "")){
        //모달창 띄우기
        $(".modal").css("display","flex")
        return false;
    }
}
//모달창 닫는 용도
$("#closeModal").on("click",function(){
    $(".modal").css("display","none");
});
//파일 업로드 할 시 작은 이미지 옆으로 생성(미리보기)
$("#photo-picker").on("change",function(){
    //첨부파일은 multiple옵션 사용 시 여러개일 수 있으므로 배열로 처리
    //첨부파일 갯수가 0개가 아니고 && 첫번째 파일이 정상이면
    if(this.files.length != 0 && this.files[0] != 0){
        const reader = new FileReader();//파일정보를 얻어올 수 있는 객체
        //선택한 파일 정보를 읽어옴(비동기처리)
        reader.readAsDataURL(this.files[0]);
        //파일리더가 정보를 다 읽어오면 동작할 함수
        reader.onload = function(e){
            $(".photo-img").attr("src",e.target.result);
            $(".img-box-add").css("display","block")
        }
    }else{
        $(".photo-img").attr("src","");
        $(".img-box-add").css("display","none");
    }
});
//summernote(editor기능 추가)
$("#writeFrmContent").summernote({
    height: 400,
    lang: "ko-KR",
    callbacks :{
        onImageUpload : function(files){
            //에디터 본문에 이미지를 삽입하면 동작하는 함수
            uploadImage(files[0],this);
        }
    }
});
//summernote이미지 삽입
function uploadImage(file,editor){
    //이미지데이터를 바로 분문에 삽입하면 용량이 너무 큼(DB기준)
    //이미지를 ajax로 업로드하고 그 경로를 받아옴
    const form = new FormData();//form태그 역할
    
    form.append("file",file);//form내부에 <input type="file" name="file">추가
    /*
        <form>
            <input type="file" name="file">
        </form>
    */
    $.ajax({
        url : "/notice/noticeEditor",//이미지 업로드를 처리할 서버 엔드포인트
        type : "post",
        data : form,
        processData : false,
        contentType : false,
        success : function(data){
            //서버에서 받아온 이미지 경로(data)를 이용해서 에디터에 이미지 삽입
            $(editor).summernote("insertImage",data);
        }
    });
    //processData : 기본설정이 전송하는 데이터를 기본적으로 문자열을 전송하게 세팅
    // -> 파일 전송을 위해서 기본전송값 문자열을 해제
    //contentType : enctype="application/x-www-form-urlencoded;charSset=UTF-8"
    // -> 파일전송을 위해서 enctype 기본값 제거
}

/* updateFrm.html */
//파일 삭제
function deleteFile(obj,fileNo){
    if(confirm("파일을 삭제하시겠습니까?")){
        const input = $("<input>");
        input.attr("name","delFileNo");
        input.attr("type","hidden");
        input.val(fileNo);
        $(".noticeUpdateFrm").append(input);
        $(obj).parent().remove();
    }
}

/* usualQnaList.html */
//tr 클릭 시 slide되는 용도  <- 만약 시간이 된다면 tr이랑 text랑 각각 slideDown과 fadeOut을 줘서 디자인해보기
$("tr.showContent").click(function() {
    //먼저 전체 슬라이드를 올려주는 코드
    const allContent = $(".qnaContent")
    allContent.fadeOut();
    // allContent.slideUp();
    //내가 원하는 컨텐츠만 내려주는 코드
    const usualQnaContent = $(this).next().find(".qnaContent");
    //컨텐츠가 visible(slideDown상태)인 경우
    if(usualQnaContent.is(':visible')){
        // usualQnaContent.slideUp();
        // usualQnaContent.fadeOut();
        //  
    }else{
        usualQnaContent.fadeIn();
        // usualQnaContent.slideDown();
    }
});

