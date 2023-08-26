const checkArr = [false,false,false, false,false,false,false,false];
const memberArr = ['user011','user0222','user033'];
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
//유효성 검사

const idComment = $("#id_comment");
const idReg = /^[a-z0-9]{6,16}$/;
const idValue = $("#memberId").val();

$("#memberId").on("keyup",function(){
    const index = idReg.indexOf(idValue);
    if(index == -1){
        idComment.innerText = "6자이상 16자 이하의 영어 소문자와 숫자를 조합";
        idComment.style.color = "#1f4787";     
       
    }else{
        idComment.innerText.hide();
        
    }
    
});






































    


    