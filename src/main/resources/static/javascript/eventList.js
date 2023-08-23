const eventTabs= $(".event-tab>li>a");
const eventContents=$(".event-content");
$(function(){
    eventTabs.eq(0).click();
});

eventTabs.on("click",function(){
    const index = eventTabs.index(this);
    eventTabs.removeClass("event-active");
    eventTabs.eq(index).addClass("event-active");
    eventContents.hide();
    $(".winnerTr").empty();
    $("#pageNavi").empty();
    eventContents.eq(index).show();

    let reqPage = 1;
    $.ajax({
        url : "/event/winnerBoard",
        type : "get",
        data : {reqPage : reqPage},
        success : function(data){
            for(let i=0 ; i<data.winnerBoardList.length ; i++){
                const winnerBoard = data.winnerBoardList[i];
                const tr = $("<tr>");
                tr.addClass("winnerTr")
                const noTd = $("<td>");
                noTd.text(winnerBoard.winNo);
                const titleTd = $("<td>");
                titleTd.text(winnerBoard.winTitle);
                const writerTd = $("<td>");
                writerTd.text(winnerBoard.winWriter);
                const regDateTd = $("<td>");
                regDateTd.text(winnerBoard.regDate);
                tr.append(noTd).append(titleTd).append(writerTd).append(regDateTd);
                $(".notice-tbl").children("tbody").append(tr);
            }
            $("#pageNavi").append(data.pageNavi);
            const pageTabs = $("#pageNavi>ul>li>a");
            pageTabs.on("click",function(){
                
            });
        }
    });
});

const pageTabs = $("#pageNavi>ul>li>a");
pageTabs.on("click",function(){
    $(".winnerTr").empty();
    $("#pageNavi").empty();

    let reqPage = $(this).text();
    changePage(reqPage);

});

function changePage(reqPage){
    $.ajax({
        url : "/event/winnerBoard",
        type : "get",
        data : {reqPage : reqPage},
        success : function(data){
            for(let i=0 ; i<data.winnerBoardList.length ; i++){
                const winnerBoard = data.winnerBoardList[i];
                const tr = $("<tr>");
                tr.addClass("winnerTr")
                const noTd = $("<td>");
                noTd.text(winnerBoard.winNo);
                const titleTd = $("<td>");
                titleTd.text(winnerBoard.winTitle);
                const writerTd = $("<td>");
                writerTd.text(winnerBoard.winWriter);
                const regDateTd = $("<td>");
                regDateTd.text(winnerBoard.regDate);
                tr.append(noTd).append(titleTd).append(writerTd).append(regDateTd);
                $(".notice-tbl").children("tbody").append(tr);
            }
            $("#pageNavi").append(data.pageNavi);
        }
    });
}