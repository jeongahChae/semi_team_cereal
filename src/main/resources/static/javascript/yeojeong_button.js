//아이디 중복체크 버튼 누르면 체크해서 모달로 띄워주기
// $("#idChkBtn").click(function(){
//     if($("#memberId").val()==""){
//         $(".modal").children("#idconditionChk").fadeIn(500);
//         console.log(1);
//     }
	
// });
$("#idChkBtn").click(function(){
    const idValue = $("#memberId").val();
    const idReg = /^[a-z0-9]{6,16}$/;   
    const check = idReg.test(idValue);
   

    if(check){
        $.ajax({
            type:"post",
            url:"/member/checkId",
            data:{checkId:idValue},
            success:function(result){
                if(result ==0){
                    $("#idChkModal3").fadeIn(500);
                   
                }else{
                    $("#idChkModal2").fadeIn(500);
                   
                }              
                
            },
            
             
        });
    }else{
        $("#idChkModal1").fadeIn(500);              
    }   
	
});

$(document).mouseup(function (e){
    console.log(e);
	if($("#idChkModal1").has(e.target).length > 0){
        
		$(".modalChk").fadeOut(400);
	}
    else if($("#idChkModal2").has(e.target).length > 0){
        
		$(".modalChk").fadeOut(400);
	}
    else if($("#idChkModal3").has(e.target).length > 0){
        
		$(".modalChk").fadeOut(400);
        $("#idChkBtn").css("color","#ccc");
        $("#idChkBtn").attr("disabled",true);
        
	}
});

//이메일 중복확인 버튼 누르면 체크해서 모달로 띄워주기
$("#emailChkBtn").click(function(){
    var emailValue = $("#memberEmail").val();
    const emailReg = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const check = emailReg.test(emailValue);
    var emailRe = $("#memberEmailre");
   

    if(check){
        $.ajax({
            type:"post",
            url:"/member/checkEmail",
            data:{checkEmail:emailValue},
            success:function(result){
                if(result ==0){
                    $("#emailChkModal3").fadeIn(500);
                    emailRe.val(emailValue);
                    //console.log(emailRe);
                    //console.log(emailValue);
                }else{
                    $("#emailChkModal2").fadeIn(500);
                    //console.log(result);
                }
                
                
            },
            
             
        });
    }else{
        $("#emailChkModal1").fadeIn(500);              
    }   
	
});

$(document).mouseup(function (e){
    
	if($("#emailChkModal1").has(e.target).length > 0){
        
		$(".modalChk").fadeOut(400);
	}
    else if($("#emailChkModal2").has(e.target).length > 0){
        
		$(".modalChk").fadeOut(400);
	}
    else if($("#emailChkModal3").has(e.target).length > 0){
        
		$(".modalChk").fadeOut(400);
        $("#emailChkBtn").css("color","#ccc");
        $("#emailChkBtn").attr("disabled",true);
        
	}
});

//이메일 인증번호 받기 버튼 누르면 인증받아서 번호입력

let authCode = null;

$("#emailauthBtn").click(function(){
    const email = $("#memberEmailre").val();
    $.ajax({
        url : "/member/auth",
        data : {email : email},
        type : "post",
        success : function(data){
            console.log(data);
            authCode = data;           
	        authTime();
        }
    });
    
});
let intervalId = null;
		function authTime(){
			$("#timeZone").html("<span id='min'>3</span> : <span id ='sec'>00</span>");
			intervalId = window.setInterval(function(){
				const min = $("#min").text();
				const sec = $("#sec").text();
				if(sec == "00"){
					if(min == "0"){
						window.clearInterval(intervalId);
						authCode = null;
						$("#authMsg").text("인증 시간 만료");
						$("#authMsg").css("color","#A52502");
					}else{
						const newMin = Number(min)-1;
						$("#min").text(newMin);
						$("#sec").text(59);
						
					}
					
				}else{
					const newSec = Number(sec) -1;
					if(newSec < 10){
						$("#sec").text("0"+newSec);
					}else{
						$("#sec").text(newSec);						
					}
					
				}
			},1000);
		}
        
$("#authCode").change(function(){
    if(authCode != null){
        const inputCode = $("#authCode").val();
        if(authCode == inputCode){
            $("#auth_comment").text("인증번호 일치");
            $("#auth_comment").css("color","#154B52");
            window.clearInterval(intervalId);
            $("#timeZone").empty();
        }else{
            $("#auth_comment").text("메일코드를 확인하세요");
            $("#auth_comment").css("color","#A52502");
        }
    }
});

