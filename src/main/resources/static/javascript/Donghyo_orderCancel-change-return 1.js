//주문취소/교환/반품 1
//Donghyo_orderCancel-change-return 1.html
$("#tableId").on('click', 'tr', function(e){
    if( $(e.target).is('input:checkbox') ) return;
    var chkbox = $(this).find('td:first-child :checkbox');
    chkbox.prop('checked', !chkbox.prop('checked'));
});


//체크박스가 체크되었을 때만 '접수'버튼 활성화
/*
    1. 체크박스가 체크되어 있지 않으면 '접수'버튼 비활성화
        1-1. 체크박스 인식 -> js에서 아이디를 가져와 변수를 선언
        1-2. 체크박스가 체크 상태인지, 아닌지 판별
    2. 체크박스가 체크되어 있으면 '접수'버튼 활성화
        2-1. '접수'버튼 인식 -> js에서 변수로 선언
    3. <input>의 체크박스 값이 false이면 <button>태그 내부의
        링크값을 빼고, 체크박스 값이 true이면 링크값을 텍스트로 
        삽입
*/
const checkbox = document.querySelector("#check1"); //1-1
checkbox.addEventListener("click", function(){
    if(checkbox.checked == true){
        console.log("체크 활성화 "+checkbox.checked);
    }else if(checkbox.checked == false){
        console.log("체크 비활성화 "+checkbox.checked);
    }
}); //1-2
const registerBtn = document.querySelector(".registerBtn"); //2-1
registerBtn.addEventListener("click", function(){
    console.log("접수 버튼 클릭");
});

