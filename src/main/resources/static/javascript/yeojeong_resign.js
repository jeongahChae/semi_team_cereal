//비밀번호가 현재 비밀번호와 일치한지 체크
// $("#inputPw").on("keyup",function(){
//     const pwComment = $("#pw_comment");  
//     const pwValue = $("#inputPw").val();
    
//     const mypwValue = $("#memberPwValue").val();
    
   
//     console.log(pwValue);
//     console.log(mypwValue);


//     if(pwValue != mypwValue){
        
//         pwComment.css("display","inline");
//         pwComment.html("비밀번호가 일치하지 않습니다.");
//         pwComment.css("color"," #A52502");
         
       
//     }else{
//         //pwCommnet.css("display","inline");
//         pwComment.hide();
//     }
   
// });
$("#inputPw").on("keyup",function(){
    const pwComment = $("#pw_comment");  
    const pwValue = $("#inputPw").val();
    
    $.ajax({
        type:"post",
        url:"/member/checkPw",
        data:{checkPw:pwValue},
        success:function(result){
            if(result ==0){
                pwComment.css("display","inline");
                pwComment.html("비밀번호가 일치하지 않습니다.");
                 pwComment.css("color"," #A52502");
                console.log(result);
            }else{
                pwComment.hide();
                console.log(result);
            }              
            
        },
        
         
    });

   
	
});