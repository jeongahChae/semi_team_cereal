//주문취소/교환/반품 1
//orderCancel-change-return 1.html

//tr을 눌러도 체크가 됨(미사용)
// $("#tableId").on('click', tr, function(e){
//     if( $(e.target).is('input:checkbox') ) return;
//     var chkbox = $(this).find('td:first-child :checkbox');
//     chkbox.prop('checked', !chkbox.prop('checked'));
// });


//체크박스가 체크되었을 때만 '접수'버튼 활성화
/*
    1. 체크박스가 체크되어 있지 않으면 '접수'버튼 비활성화
        1-1. 체크박스 인식 -> js에서 아이디를 가져와 변수를 선언
        1-2. 체크박스가 체크 상태인지, 아닌지 판별
    2. 체크박스가 체크되어 있으면 '접수'버튼 활성화
        2-1. '접수'버튼 인식 -> js에서 변수로 선언
    3. 체크박스를 체크하면 '접수'버튼 생성, 체크 비활성화시, '접수'버튼 삭제
*/

// const registerBtn = document.querySelector(".registerBtn"); //2-1
// registerBtn.addEventListener("click", function(){
//     console.log("접수 버튼 클릭");
// });

//3
function getLink(){
    const registerBtnWrap  = document.getElementsByClassName("registerBtn-wrap")[0];
    registerBtnWrap.innerHTML = "<button type='button' class='registerBtn' onclick=\"window.location.href=\'/myPage/orderCancel-change-return_2?btn=2\';\">접수</button>";
}
function removeLink(){
    const registerBtn = document.querySelector(".registerBtn"); 
    registerBtn.parentNode.innerHTML = "";
}

const checkbox = document.querySelector("#check1"); //1-1
checkbox.addEventListener("click", function(){
    if(checkbox.checked == true){
        // console.log("체크 활성화 "+checkbox.checked);
        getLink();
    }else if(checkbox.checked == false){
        // console.log("체크 비활성화 "+checkbox.checked);
        removeLink();
    }
}); //1-2


