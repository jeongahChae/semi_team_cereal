//아이디 체크
$("#memberId").on("keyup",function(){
    const idComment = $("#id_comment");    
    const idValue = $("#memberId").val();
   
    
    


    if(idValue == ""){
        
        nameComment.css("display","inline");
        nameComment.html("아이디를 입력해주세요");
        nameComment.css("color"," #A52502");
         
       
    }else{
        
        nameComment.hide();
    }
   
});
//이메일 형식입력
$("#memberEmail").on("keyup",function(){
   
    
    const emailComment = $("#email_comment");    
    const emailValue = $("#memberEmail").val();
    const emailReg = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const check = emailReg.test(emailValue);
    
    


    if(!check){
        
        emailComment.css("display","inline");
        emailComment.html("올바른 이메일 형식으로 입력해주세요");
        emailComment.css("color"," #A52502");
          
       
    }else{
        
        emailComment.hide();
    }
   
});
//확인 버튼을 눌렀을 때 아이디와 이메일 조회
$(".searchPw-btn").on("click",function(){
  
    const emailValue = $("#memberEmail").val(); 
    const IdValue= $("#memberId").val();
     
    $.ajax({
        type:"post",
        url:"/member/searchPw",
        data:{checkEmail:emailValue, checkId:IdValue},
       
        success:function(result){
            
            if(result == "not found"){
               
                $("#IEChkModal1").fadeIn(500);
            }else{
                $("#IEChkModal2").fadeIn(500);
                
            }
        },
    });
});
$(document).mouseup(function (e){
  
	if($("#IEChkModal1").has(e.target).length > 0){
        
		$(".modalChk").fadeOut(400);
	}
    else if($("#IEChkModal2").has(e.target).length > 0){
        
		$(".modalChk").fadeOut(400);
	}
    
});