//주문하기 모달
function changeAddr(memberAddr, detail, memberNo){
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
	        if(data[i].optionNo == optionNo){
	        	option.prop("selected",true);
	        }
	        
	        $(".product-option2").append(option);
    		}
		}
    });
}

//주소변경
function searchAddress(){
	new daum.Postcode({
		oncomplete : function(data){
			console.log(data);					
			$("#memberAddr").text(data.roadAddress);
			$("#detail").empty();
			$("#detail").focus();
		}
	}).open();
}