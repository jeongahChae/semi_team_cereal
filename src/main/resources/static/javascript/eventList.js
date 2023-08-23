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
    eventContents.eq(index).show();
});