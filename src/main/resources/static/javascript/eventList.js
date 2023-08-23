const eventTabs= $(".event-tab>ul>li");
const eventContents=$(".event-detail");
window.onload = function(){
    eventTabs.removeClass("event-active");
    eventTabs.eq(0).addClass("event-active");
    eventContents.hide();
    eventContents.eq(0).show();
};



