

//이용약관 동의 
const allAgreement = document.querySelector("#allAgreement");
allAgreement.addEventListener("change",function(){
    const must = document.querySelector("#mustAgreement");
    const privacy = document.querySelector("#privacyAgreement");
    const option = document.querySelector("#optionalAgreement");
    const age = document.querySelector("#ageAgreement");
    const status = allAgreement.checked;
    
    must.checked=status;
    privacy.checked=status;
    option.checked=status;
    age.checked=status;
});

//아이디 유효성 검사

$("#memberId").on("keyup",function(){
    $("#idChkBtn").attr("disabled",false);
    $("#idChkBtn").css("color","#1C8394");
    const idComment = $("#id_comment");
    const idReg = /^[a-z0-9]{6,16}$/;
    const idValue = $("#memberId").val();
    const check = idReg.test(idValue);
    console.log(check);
    console.log(idValue);
    


    if(!check){
        //idCommnet.show();
        idComment.css("display","inline");
        idComment.html("6자이상 16자 이하의 영어 소문자와 숫자를 조합");
        idComment.css("color"," #A52502");
        console.log(idComment);    
       
    }else{
        //idCommnet.css("display","inline");
        idComment.hide();
    }
   
});


//비밀번호 유효성 검사
$("#memberPw").on("keyup",function(){
    const pwComment = $("#pw_comment");
    const pwReg = /^(?=.*[A-Za-z0-9])(?=.*[a-zA-Z!@#$%^&*()+=-])(?=.*[0-9!@#$%^&*()+=-]).{10,}$/;
    const pwValue = $("#memberPw").val();
    const check = pwReg.test(pwValue);
    console.log(check);
    console.log(pwValue);
    


    if(!check){
        
        pwComment.css("display","inline");
        pwComment.html("영문/숫자/특수문자(공백 제외)만 허용하면, 2개이상 조합, 최소 10자리 이상");
        pwComment.css("color"," #A52502");
        console.log(pwComment);    
       
    }else{
        //pwComment.css("display","inline");
        pwComment.hide();
    }
   
});

// //비밀번호에서 비밀번호 확인 일치 안되었을때
// $("#memberPw").on("keyup",function(){
//     const pwreComment = $("#pwre_comment");    
//     const pwValue = $("#memberPw").val();
//     const pwreValue = $("#memberPwre").val();
//     console.log(pwValue);
    


//     if(pwValue != pwreValue){
        
//         pwreComment.css("display","inline");
//         pwreComment.html("비밀번호가 일치하지 않습니다.");
//         pwreComment.css("color"," #A52502");
//         console.log(pwreComment);    
       
//     }else{
//         //idCommnet.css("display","inline");
//         pwreComment.hide();
//     }
   
// });

//비밀번호 확인 체크
$("#memberPwre").on("keyup",function(){
    const pwreComment = $("#pwre_comment");    
    const pwValue = $("#memberPw").val();
    const pwreValue = $("#memberPwre").val();
    console.log(pwValue);
    


    if(pwValue != pwreValue){
        
        pwreComment.css("display","inline");
        pwreComment.html("비밀번호가 일치하지 않습니다.");
        pwreComment.css("color"," #A52502");
        console.log(pwreComment);    
       
    }else{
        //idCommnet.css("display","inline");
        pwreComment.hide();
    }
   
});


    $("#memberPw").on("keyup",function(){
        
        const pwreComment = $("#pwre_comment");    
        const pwValue = $("#memberPw").val();
        const pwreValue = $("#memberPwre").val();
        console.log(pwValue);
        
        if(pwreValue===""){
            pwreComment.hide();
        }else{
            if(pwValue != pwreValue){
            
                pwreComment.css("display","inline");
                pwreComment.html("비밀번호가 일치하지 않습니다.");
                pwreComment.css("color"," #A52502");
                console.log(pwreComment);    
               
            }else{
                //idCommnet.css("display","inline");
                pwreComment.hide();
            }
        }
    
        
       
    });

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
//전화번호 체크
$("#memberPhone").on("keyup",function(){
    const phoneComment = $("#phone_comment");    
    const phoneValue = $("#memberPhone").val();
   
    console.log(phoneValue);
    


    if(phoneValue == ""){
        
        phoneComment.css("display","inline");
        phoneComment.html("전화번호를 입력해주세요");
        phoneComment.css("color"," #A52502");
        console.log(phoneComment);    
       
    }else{
        //idCommnet.css("display","inline");
        phoneComment.hide();
    }
   
});
//이메일 형식입력
$("#memberEmail").on("keyup",function(){
    $("#emailChkBtn").attr("disabled",false);
    $("#emailChkBtn").css("color","#1C8394");
    const emailComment = $("#email_comment");    
    const emailValue = $("#memberEmail").val();
    const emailReg = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const check = emailReg.test(emailValue);
    console.log(emailValue);
    


    if(!check){
        
        emailComment.css("display","inline");
        emailComment.html("이메일 형식으로 입력해주세요");
        emailComment.css("color"," #A52502");
        console.log(emailComment);    
       
    }else{
        //idCommnet.css("display","inline");
        emailComment.hide();
    }
   
});

//이메일 확인 체크
$("#memberPwre").on("keyup",function(){
    const pwreComment = $("#pwre_comment");    
    const pwValue = $("#memberPw").val();
    const pwreValue = $("#memberPwre").val();
    console.log(pwValue);
    


    if(pwValue != pwreValue){
        
        pwreComment.css("display","inline");
        pwreComment.html("비밀번호가 일치하지 않습니다.");
        pwreComment.css("color"," #A52502");
        console.log(pwreComment);    
       
    }else{
        //idCommnet.css("display","inline");
        pwreComment.hide();
    }
   
});

// //주소 + 상세주소 합치기
// $("#signinBtn").on("click",function(){
//     var memberAddrValue = $("#memberAddr").val();
//     const detailValue = $("#detail").val();
//     memberAddrValue += detailValue;
//     console.log(memberAddrValue);
//     console.log(detailValue);
// });

//약관보기 클릭하면 모달창 뜨게 하기
$(function () {  
    $(document).on("click", ".agreeBtn", function () {
      console.log(2);
      $(this).parents(".modalAgree-body").parent().css("display", "none");
    });  
   
  });
   
   $(".showMust").click(function(){
      
    $("#mustAgreeModal").fadeIn(500);  
    });
    $(".showPrivacy").click(function(){
       
        $("#privacyAgreeModal").fadeIn(500);  
    });
    $(".showOptional").click(function(){
        
        $("#optionalAgreeModal").fadeIn(500);  
    });




























    


    