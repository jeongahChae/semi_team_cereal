//아이디 중복체크 버튼 누르면 체크해서 모달로 띄워주기
$("#idChkBtn").click(function(){
    $()
	$(".modal").fadeIn(500);
});

$(document).mouseup(function (e){
	if($(".modal").has(e.target).length === 0){
		$(".modal").fadeOut(500);
	}
});


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

