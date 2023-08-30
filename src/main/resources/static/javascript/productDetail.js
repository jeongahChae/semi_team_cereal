$(function(){
    $(".tab>ul>li").eq(0).addClass("active-tab");
    $(".product-detail").addClass("active-content");
    $(".tab>ul>li").eq(1).addClass("active-tab2");
    $(".tab>ul>li").eq(2).addClass("active-tab2");
    $(".tab>ul>li").eq(3).addClass("active-tab2");
});

const tabs = $(".tab>ul>li");
const contents = $(".tab-content>div");
tabs.on("click",function(){
    tabs.removeClass("active-tab");
    tabs.removeClass("active-tab2");
    contents.removeClass("active-content");
    const index = $(this).index();
    if($(this)){
        $(this).addClass("active-tab");
        contents.eq(index).addClass("active-content");
        tabs.not($(this)).addClass("active-tab2");
    }
});

const select = $(".product-option")
select.change(function(){
    const div = $("<div>");
    const optionDetail = div.addClass("option-detail-box").text($(this).val());
    
    const optionCount = $("<div class='option-count'>");
    const plusButton = $("<button type='button' id='plus'>+</button>");
    const optionPlus = optionCount.append(plusButton);
    const count = $("<div class='count'>1</div>");
    const optionPlusCount = optionPlus.append(count);
    const optionMinus = $("<button type='button' id='minus'>-</button>");
    const optionCountbox = optionPlusCount.append(optionMinus);
    $(".product-detail-info-optionbox").css("display","block");
    $(".product-detail-info-optionbox").append(optionDetail);
    $(".product-detail-info-optionbox").append(optionCountbox);
    
    $("#plus").on("click",function(){
	    const currNum = $(".count").text();
	    $(".count").text(Number(currNum)+1);
	});
	
	$("#minus").on("click",function(){
	    const currNum = $(".count").text();
	    if(Number(currNum) == 1){
	        alert("수량은 1개 이상만 가능합니다.");
	        return;
	    }
	    $(".count").text(Number(currNum)-1);
	});
});



