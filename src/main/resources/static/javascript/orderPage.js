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


function payingCreditCard(m){
	const currTotalPrice = $("#currTotalPrice").text();
	let finalPrice = (Number)(currTotalPrice);
	const d = new Date();	//주문번호 겹치지 않게 추가하기 위함
	const date = d.getFullYear()+""+(d.getMonth()+1)+""+d.getDate()+""+d.getHours()+""+d.getMinutes()+""+d.getSeconds();
	const orderNo = (Number)(date);
	console.log(orderNo, isNaN(orderNo));
	IMP.init("imp02326283");
	IMP.request_pay({
		pg : "html5_inicis",			//kg이니시스
		pay_method : "card",
		merchant_uid : "상품번호_" +date,	//상점에서 관리하는 주문번호
		name : "결제테스트",
		amount : finalPrice,					//금액(콤마도없이 저스트 숫자만 들어가야함)
		buyer_email : m.memberEmail,
		buyer_name : m.memberName,
		buyer_tel : m.memberPhone,
		buyer_addr : m.memberAddr+","+m.detail
	}, function(rsp){
		if(rsp.success)	{
			alert("결제성공");
				const usePoint = $("#usePoint").text();
				let point = (Number)(usePoint);
				
				//장바구니 번호 배열
				let cartNos = $(".cartNo");
				const cart = new Array();
				cartNos.each(function(index, item){
					const cartNo = $(item).text();
					cart.push(cartNo);
				});
				let cartStr = cart.join("/");
				location.href="/order/createOrder?cartStr="+cartStr+"&price="+finalPrice+"&usePoint="+point+"&orderNo="+orderNo;
		} else {
			alert("결제 실패");
		}
	});
};


	

