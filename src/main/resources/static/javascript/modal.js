$(".modal-open-btn").click(function(){
	$(".modal").fadeIn(500);
});

$(document).mouseup(function (e){
	if($(".modal").has(e.target).length === 0){
		$(".modal").fadeOut(500);
	}
});