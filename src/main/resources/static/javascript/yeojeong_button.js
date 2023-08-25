//아이디 중복체크 버튼 누르면 체크해서 모달로 띄워주기
$("#idChkBtn").click(function(){
    if($("#memberId")=""){
        $(".idconditionChk").fadeIn(500);
    }
	
});

$(document).mouseup(function (e){
	if($("#idChkModal").has(e.target).length === 0){
		$("#idChkModal").fadeOut(500);
	}
});
//이메일 중복확인 버튼 누르면 체크해서 모달로 띄워주기
$("#emailChkBtn").click(function(){
    $()
	$(".modal").fadeIn(500);
});

$(document).mouseup(function (e){
	if($(".modal").has(e.target).length === 0){
		$(".modal").fadeOut(500);
	}
});
//이메일 인증번호 받기 버튼 누르면 인증받아서 번호입력
//배송지 등록 누르면 주소api 누르기

$("#jQ2").on("click",function(){
    const input = $(this).prev();
    const inputValue = input.val();
    console.log(inputValue);
    $.ajax({
        url : "/ajax/test2",
        type : "get",
        data : {input1:inputValue},
        success : function(){
            console.log("서버로 데이터 전송 완료");
            
        }
        
    });
 });

