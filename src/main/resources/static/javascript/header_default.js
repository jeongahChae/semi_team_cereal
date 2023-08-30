$(function () {  
  $(document).on("click", ".modal-close", function () {
    console.log(2);
    $(this).parents(".modal-wrap").parent().css("display", "none");
  });  
  //$(".sub-navi").prev().after("<span class='material-icons dropdown'>expand_more</span>");
});
 
 $(".modal-open-btn").click(function(){
    console.log(3);
    $(".modal-bg").fadeIn(500);  
  });
  