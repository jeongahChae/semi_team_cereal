//이름 체크
$("#memberName").on("keyup",function(){
    const nameComment = $("#name_comment");    
    const nameValue = $("#memberName").val();
   
    
    


    if(nameValue == ""){
        
        nameComment.css("display","inline");
        nameComment.html("이름을 입력해주세요");
        nameComment.css("color"," #A52502");
        console.log(nameComment);    
       
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
//확인 버튼을 눌렀을 때 이름과 이메일 조회
$(".searchId-btn").on("click",function(){
  
    const emailValue = $("#memberEmail").val(); 
    const nameValue= $("#memberName").val();
     
    $.ajax({
        type:"post",
        url:"/member/searchId",
        data:{checkEmail:emailValue, checkName:nameValue},
       
        success:function(result){
            console.log(result);
            if(result == "not found"){
               
                $("#NEChkModal1").fadeIn(500);
            }else{
                $("#NEChkModal2").fadeIn(500);
                $("#foundMemberId").text(result);
            }
        },
    });
});