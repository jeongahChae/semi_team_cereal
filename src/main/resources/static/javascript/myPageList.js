//마이페이지 리스트
//myPageList.html


//버튼 활성화
function changeBtn(event){
    var btns = document.querySelectorAll(".sideButton");
    btns.forEach(function(btn, i){
        if(event.currentTarget == btn){
            btn.classList.add("active");
        }else{
            btn.classList.remove("active");
        }
    });
    //console.log(event.currentTarget);
}
