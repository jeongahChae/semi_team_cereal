//썸머노트용
$("#eventContent").summernote({
    //태그를 모두 포함해서 DB에 저장하므로 생각보다 용량을 많이 사용함
    height : 400,
    lang : "ko-KR",
    placeholder : "이벤트 내용을 입력하세요.",
    callbacks : {
        onImageUpload :function(files){
            //에디터 본문에 이미지를 삽입하면 동작하는 함수
            uploadImage(files[0],this);//this : 텍스트에디터 자체
        }
    }
});
function uploadImage(file, editor){

    const form = new FormData();//form태그 역할
    
    form.append("file", file);	//form태그 내부에 <input type="file" name="file">추가
    $.ajax({
        url : "/event/editor",
        type : "post",
        data : form,
        processData : false,	//필수
        contentType : false,	//필수
        success : function(data){
            $(editor).summernote("insertImage",data);
        }

    });
}

//썸네일 미리보기용
$("[name=upfile2]").on("change", function(){
    if(this.files.length != 0 && this.files[0] != 0){
        const reader = new FileReader();
        reader.readAsDataURL(this.files[0]);//비동기요청
        reader.onload = function(e){//다 읽어오면 결과가 매개변수로 들어옴
            $("#img-view").attr("src", e.target.result);
        }
    }else{
        $("#img-view").attr("src","");	//다시 파일선택>취소 누르면 사라짐
    }
});

// 썸네일 파일 변경시 삭제할 파일 이름얻는 함수
$("#file").on("change", function(){
    const delFileName = $(".upload-name").val();
    $(".upload-name").val(event.target.files[0].name);
    const input = $("<input>");
    input.attr("name", "delFileName");
    input.attr("type", "hidden");
    input.val(delFileName);
    $(".filebox").append(input);//form태그에 마지막 자식으로 추가
});

