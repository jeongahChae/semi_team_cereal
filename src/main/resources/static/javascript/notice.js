function noticeDelete(noticeNo){
    if(confirm("삭제하시겠습니까?")){
        location.href = "/notice/delete?noticeNo="+noticeNo;
    }
}

//모달제작
function validateForm() {
    var noticeTitle = document.getElementsByName("noticeTitle")[0].value;
    var noticeContent = document.getElementById("writeFrm-content").value;

    if (noticeTitle.trim() === "" || noticeContent.trim() === "") {
        // 제목이나 내용이 비어있는 경우 모달 창 표시
        var modal = document.getElementById("myModal");
        modal.style.display = "block";
        return false; // 폼 제출 방지
    }
    
    return true; // 유효성 검사 통과 시 폼 제출
}

// 모달 닫기 버튼 처리
document.getElementsByClassName("close")[0].addEventListener("click", function() {
    var modal = document.getElementById("myModal");
    modal.style.display = "none";
});
