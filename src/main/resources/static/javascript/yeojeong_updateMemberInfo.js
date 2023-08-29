//비밀번호가 현재 비밀번호와 일치한지 체크
$("#memberPw").on("keyup",function(){
    const pwComment = $("#pw_comment");  
    const pwValue = $("#memberPw").val();
    
    const mypwValue = $("#memberPwValue").val();
    
   
    console.log(pwValue);
    console.log(mypwValue);


    if(pwValue != mypwValue){
        
        pwComment.css("display","inline");
        pwComment.html("비밀번호가 일치하지 않습니다.");
        pwComment.css("color"," #A52502");
         
       
    }else{
        //pwCommnet.css("display","inline");
        pwComment.hide();
    }
   
});
//새로운 비밀번호 유효성 검사
$("#memberPwnew").on("keyup",function(){
    const pwComment = $("#pwnew_comment");
    const pwReg = /^(?=.*[A-Za-z0-9])(?=.*[a-zA-Z!@#$%^&*()+=-])(?=.*[0-9!@#$%^&*()+=-]).{10,}$/;
    const pwValue = $("#memberPwnew").val();
    const check = pwReg.test(pwValue);
    console.log(check);
    console.log(pwValue);
    


    if(!check){
        
        pwComment.css("display","inline");
        pwComment.html("영문/숫자/특수문자(공백 제외)만 허용하면, 2개이상 조합, 최소 10자리 이상");
        pwComment.css("color"," #A52502");
      
    }else{
        //pwComment.css("display","inline");
        pwComment.hide();
    }
   
});

//새로운 비밀번호 일치 체크 
$("#memberPwnew").on("keyup",function(){
    const pwreComment = $("#pwnewre_comment");    
    const pwValue = $("#memberPwnew").val();
    const pwreValue = $("#memberPwNewre").val();
    console.log(pwValue);
    


    if(pwValue != pwreValue){
        
        pwreComment.css("display","inline");
        pwreComment.html("비밀번호가 일치하지 않습니다.");
        pwreComment.css("color"," #A52502");
       
    }else{
        //pwComment.css("display","inline");
        pwreComment.hide();
    }
   
});

//새로운 비밀번호 일치 확인 체크(비밀번호 지웠을때)
$("#memberPwNewre").on("keyup",function(){
    const pwreComment = $("#pwnewre_comment");    
    const pwValue = $("#memberPwnew").val();
    const pwreValue = $("#memberPwNewre").val();
  
    


    if(pwValue != pwreValue){
        
        pwreComment.css("display","inline");
        pwreComment.html("비밀번호가 일치하지 않습니다.");
        pwreComment.css("color"," #A52502");
      
       
    }else{
        //pwCommnet.css("display","inline");
        pwreComment.hide();
    }
   
});
//정보수정 버튼을 눌렀을때 새 비밀번호 입력안해도 기존비밀번호 값이 들어가기
$("#updateBtn").on("click",function(){
    
    const mypwValue = $("#memberPwValue").val();
    const memberPwnewValue= $("#memberPwnew").val();
    if(memberPwnewValue==''){
        $("#memberPwnew").val(mypwValue);
    }
    
});

// $("#addrBtn").click(function(){

// });