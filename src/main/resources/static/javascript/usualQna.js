
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
        //아무것도 하지 않는다.
        // usualQnaContent.slideUp();
        // usualQnaContent.fadeOut();
    }else{
        usualQnaContent.fadeIn();
        // usualQnaContent.slideDown();
    }
});

//삭제버튼 -> 시간이 난다면 모달창으로 처리하기
function usualQnaDelete(qnaNo){
    if(confirm("게시글을 삭제하시겠습니까?")){
        location.href = "/usualQna/delete?qnaNo="+qnaNo;
    }
}


/* usualQnaWriteFrm.html */
//등록버튼(모달용)
function checkValue(){
    const titleInput = $("#writeFrmTitle").val();
    const contentInput = $("#writeFrmContent").val();
    const categoryInput = $("#writeFrmCategory").val();

    if((titleInput.trim() === "")||(contentInput.trim() === "")){
        //모달창 띄우기
        $("#writeFrmModal").css("display","flex");
        return false;
    }else{
        if((categoryInput.trim() === "")){
            $("#writeFrmModal2").css("display","flex");
            return false;
        }
    }
}

//모달창 닫는 용도
$("#closeModal").on("click",function(){
    $("#writeFrmModal").css("display","none");
});
$("#closeModal2").on("click",function(){
    $("#writeFrmModal2").css("display","none");
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
$(function(){
    const qnaCategoryVal = $(".usualQna-category").attr("data");
    const option = $(".usualQna-category").children();
    console.log(qnaCategoryVal);
    for(let i=0;i<option.length;i++){
        const optionVal = option.eq(i).val();
        if(qnaCategoryVal == optionVal){
            option.eq(i).prop("selected",true);
            break;
        }

    }
});