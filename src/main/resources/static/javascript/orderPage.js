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

//적립금 사용
function usePoint(point, memberNo){
	let usePoint = $("#currPoint").val();
	if(usePoint === ""){
		usePoint = 0;
	}
	const currTotalPrice = (Number)($("#currTotalPrice").text());
	
	let regExp = /^[0-9]+$/;
	const check = regExp.test(usePoint);
	if(check){
		if(usePoint>point){
			alert("사용 가능한 적립금을 확인하세요.");
		} else if(usePoint<5000){
			alert("적립금은 5000원 이상부터 사용이 가능합니다.");
		} else if(usePoint>currTotalPrice){
			alert("적립금은 상품 금액 이내에서 사용이 가능합니다.");
		}else{
			$("#usePoint").text(usePoint+"P");
			$("#final-price").text((currTotalPrice-usePoint));
			$("#currTotalPrice").text((currTotalPrice-usePoint));
			$("#price").text((currTotalPrice-usePoint));
			alert(usePoint+"P 사용");
		}
	} else {
		alert("적립금을 숫자로만 입력해주세요.");
	}
	

}

//결제 버튼 눌렀을 때
$("#pay-btn").on("click", function(){
	const currTotalPrice = $("#currTotalPrice").text();
	let finalPrice = (Number)(currTotalPrice);
	const usePoint = $(#usePoint).text();
	let point = (Number)(usePoint);
	
	//장바구니 번호 배열
	let cartNos = $(".cartNo");
	const cart = new Array();
	cartNos.each(function(index, item){
		const cartNo = $(item).text();
		cart.push(cartNo);
	});
	let cartStr = cart.join("/");


    $.ajax({
	url: "/order/createOrder",
	type:"post",
	data:{cart:cartStr,	//카트번호 들은 스트링배열
		price:finalPrice,
		usePoint:point},
	success:function(data){

	}
	});
	
	
	

});