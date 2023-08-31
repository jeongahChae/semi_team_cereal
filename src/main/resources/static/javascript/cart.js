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

//모달창에서 옵션 변경용
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
		if(data>0){
		alert("변경되었습니다.");
	    $("#closeModal").click();
	    location.href = "/order/cart";
		}
	}
	});
});

      //전체 선택
$("#cartAll").on("click",function(){
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

//카트에서 삭제하기
$(".del-cart").on("click", function(){

		const check = $(".cartChk:checked");
		if(check.length == 0){
			alert("선택된 상품이 없습니다.");
			return;
		}
		const no = new Array();		//체크된 카트 번호를 저장할 배열

		check.each(function(index,item){
			const cartNo = $(item).next().val();
			no.push(cartNo);
		});
		location.href="/order/delCart?no="+no.join("/");//js에서 배열을 구분자를 써서 하나의 긴 문자열로 빼주는 함수^^..
	});
	


//이젠...주문하고싶다
//1)선택상품주문
$("#partialOrder").on("click", function(){

	const check = $(".cartChk:checked");
	if(check.length == 0){
		alert("선택된 상품이 없습니다.");
		return;
	}
	const no = new Array();		//체크된 카트 번호를 저장할 배열

	check.each(function(index,item){
		const cartNo = $(item).next().val();
		console.log(cartNo);
		no.push(cartNo);
	});
	location.href="/order/orderChk?no="+no.join("/");//js에서 배열을 구분자를 써서 하나의 긴 문자열로 빼주는 함수^^..
});
//2)전체상품주문
$("#allOrder").on("click", function(){

	const check = $(".cartChk");
	if(check.length == 0){
		alert("선택된 상품이 없습니다.");
		return;
	}
	const no = new Array();		//체크된 카트 번호를 저장할 배열

	check.each(function(index,item){
		const cartNo = $(item).next().val();
		no.push(cartNo);
	});
	location.href="/order/orderChk?no="+no.join("/");//js에서 배열을 구분자를 써서 하나의 긴 문자열로 빼주는 함수^^..
});	
