//관리자페이지 리스트
//Donghyo_adminPageList.html

function changeBtn(event){
    var btns = document.querySelectorAll(".button");
    btns.forEach(function(btn, i){
        if(event.currentTarget == btn){
            btn.classList.add("active");
        }else{
            btn.classList.remove("active");
        }
    });
    //console.log(event.currentTarget);
}