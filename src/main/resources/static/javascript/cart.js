//등록버튼(모달용)
function checkValue(cartNo, optionNo,productNo, count){
    $(".modal").css("display","flex");
    $(".count2").text(count);
	const newCount = $(".count2").val();
	$(".hidden-cartNo").text(cartNo);
    
    $.ajax({
    	url: "/order/optionList",
    	type:"get",
    	data:{cartNo:cartNo,
    		optionNo:optionNo,
    		productNo:productNo,
    		newCount:newCount},
		success:function(data){
			$(".product-option2").empty();
		    for(let i=0; i<data.length; i++){
	        let option = $("<option>");
	        option.addClass("product-option");
	        option.val(data[i].optionNo);
	        option.text(data[i].optionName);
	        console.log(option);
	        if(data[i].optionNo == optionNo){
	        	option.prop("selected",true);
	        }
	        
	        $(".product-option2").append(option);
    		}
		}
    });
}


//모달창 닫는 용도
$("#closeModal").on("click",function(){
    $(".modal").css("display","none");
});

$("#changeModal").on("click",function(){
	const cartNo = $(".hidden-cartNo").text();
	const newOptionNo = $(".product-option2").val();
	const newCount= $(".count2").text();
		
	$.ajax({
	url: "/order/chgOpt",
	type: "get",
	data:{ cartNo:cartNo,
		newOptionNo:newOptionNo,
		newCount:newCount},
	success:function(data){
			console.log(data);
		if(data>0){
			alert("변경되었습니다.");
		    $("#closeModal").click();
		}
	}
	});
});




$("#cartAll").on("click",function(){      //전체 선택
    const status = $("#cartAll").prop("checked");
    const checkboxes = $(".cartChk");
    checkboxes.prop("checked",status);
});



//모달창에 있는 애들 수량 변경하는 용도
$("#plus").on("click",function(){
	const currNum = $(".count2").text();
    $(".count2").text(Number(currNum)+1);
});

$("#minus").on("click",function(){
	const currNum = $(".count2").text();
    if(Number(currNum) == 1){
        alert("수량은 1개 이상만 가능합니다.");
        return;
    }
    $(".count2").text(Number(currNum)-1);
});