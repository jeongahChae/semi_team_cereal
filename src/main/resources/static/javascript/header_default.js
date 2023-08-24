$(function () {
  $(document).on("click", ".modal-open-btn", function () {
    $($(this).attr("target")).css("display", "flex");
  });
  $(document).on("click", ".modal-close", function () {
    $(this).parents(".modal-wrap").parent().css("display", "none");
  });  
  $(".sub-navi").prev().after("<span class='material-icons dropdown'>expand_more</span>");
});
 
 $(".modal-open-btn").click(function(){
    console.log(5);
    $(".modal-bg").fadeIn(500);
  
  });
  
  $(document).mouseup(function (e){
    if($(".modal").has(e.target).length === 0){
      $(".modal").fadeOut(500);
    }
  });
