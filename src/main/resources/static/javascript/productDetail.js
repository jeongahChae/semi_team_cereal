$(function(){
    $(".tab>ul>li").eq(0).addClass("active-tab");
    $(".product-detail").addClass("active-content");
    $(".tab>ul>li").eq(1).addClass("active-tab2");
    $(".tab>ul>li").eq(2).addClass("active-tab2");
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