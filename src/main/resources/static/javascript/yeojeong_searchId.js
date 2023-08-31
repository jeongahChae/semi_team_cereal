//이름 체크
$("#memberName").on("keyup",function(){
    const nameComment = $("#name_comment");    
    const nameValue = $("#memberName").val();
   
    console.log(nameValue);
    


    if(nameValue == ""){
        
        nameComment.css("display","inline");
        nameComment.html("이름을 입력해주세요");
        nameComment.css("color"," #A52502");
        console.log(nameComment);    
       
    }else{
        //idCommnet.css("display","inline");
        nameComment.hide();
    }
   
});
//이메일 형식입력
$("#memberEmail").on("keyup",function(){
   
    
    const emailComment = $("#email_comment");    
    const emailValue = $("#memberEmail").val();
    const emailReg = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const check = emailReg.test(emailValue);
    console.log(emailValue);
    


    if(!check){
        
        emailComment.css("display","inline");
        emailComment.html("올바른 이메일 형식으로 입력해주세요");
        emailComment.css("color"," #A52502");
        console.log(emailComment);    
       
    }else{
        //idCommnet.css("display","inline");
        emailComment.hide();
    }
   
});
//확인 버튼을 눌렀을 때 이름과 이메일 조회
$("#searchId-btn").on("click",function(){
    var emailValue = $("#memberEmail").val(); 
    const emailComment = $("#email_comment");   
    $.ajax({
        type:"post",
        url:"/member/checkEmail",
        data:{checkEmail:emailValue},
        success:function(result){
            if(result ==0){
                
            }else{
                
                //console.log(result);
            }
            
            
        },
        
         
    }); 
   
	
});