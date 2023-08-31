$(window).scroll(function () { 
    var scrollValue = $(document).scrollTop(); 
    console.log(scrollValue); 
    if($(this).scrollTop() > 500) {
		$(".product-detail-content3").css('position','fixed');
        $(".product-detail-content3").css('bottom','350px');
        $(".product-detail-content3").css('left','1250px');
	}else{
		$(".product-detail-content3").css('position','');
        $(".product-detail-content3").css('bottom','');
        $(".product-detail-content3").css('left','');
	}
});

$(function(){
    $(".tab>ul>li").eq(0).addClass("active-tab");
    $(".product-detail-img").addClass("active-content");
    $(".tab>ul>li").eq(1).addClass("active-tab2");
    $(".tab>ul>li").eq(2).addClass("active-tab2");
    $(".tab>ul>li").eq(3).addClass("active-tab2");
});

const tabs = $(".tab>ul>li");
const contents = $(".tab-content>div");
tabs.on("click",function(){
    tabs.removeClass("active-tab");
    tabs.removeClass("active-tab2");
    contents.removeClass("active-content");
    const index = $(this).index();
    if($(this)){
        $(this).addClass("active-tab");
        contents.eq(index).addClass("active-content");
        tabs.not($(this)).addClass("active-tab2");
    }
});

$(".subimg-box1").on("click", function(){
    const imgurl = $(".subimg-box1>img").attr("src");
    $(".product-detail-mainimg-box>img").attr("src", imgurl);
});

$(".subimg-box2").on("click", function(){
    const imgurl = $(".subimg-box2>img").attr("src");
    $(".product-detail-mainimg-box>img").attr("src", imgurl);
});

$(".subimg-box3").on("click", function(){
    const imgurl = $(".subimg-box3>img").attr("src");
    $(".product-detail-mainimg-box>img").attr("src", imgurl);
});

$(".subimg-box4").on("click", function(){
    const imgurl = $(".subimg-box4>img").attr("src");
    $(".product-detail-mainimg-box>img").attr("src", imgurl);
});

const select = $(".product-option")
select.change(function(){
    const div = $("<div>");
    const optionDetail = div.addClass("option-detail-box").text($(this).val());
    
    const optionCount = $("<div class='option-count'>");
    const plusButton = $("<button type='button' class='plus'>+</button>");
    const optionPlus = optionCount.append(plusButton);
    const count = $("<div class='count'>1</div>");
    const optionPlusCount = optionPlus.append(count);
    const optionMinus = $("<button type='button' class='minus'>-</button>");
    const optionCountbox = optionPlusCount.append(optionMinus);
    $(".product-detail-info-optionbox").css("display","block");
    $(".product-detail-info-optionbox").append(optionDetail);
    $(".product-detail-info-optionbox").append(optionCountbox);
    
    const countList = $(".count");


    $(".plus").each(function(i, item){
        $(item).on("click",function(){
            const currNum = $(item).next().text();
            $(item).next().text(Number(currNum)+1);
        });
    });
    
	
	$(".minus").on("click",function(){
        const currNum = $(".count").text();
        if(Number(currNum) == 1){
            alert("수량은 1개 이상만 가능합니다.");
            return;
        }
        $(".count").text(Number(currNum)-1);
	});
    let sum = 0;
    $('.count').each(function(){
        var text = $(this).text();
        let text2 = (Number)(text);
        sum += text2;
        
    });
    let finalPrice = $(".product-detail-info-final-price2").val();
    let totalPrice = sum * finalPrice;
    console.log(totalPrice.toLocaleString());
    $(".info-totalPrice").text(totalPrice.toLocaleString()+"원");
});

const select2 = $(".product-option2")
select2.change(function(){
    const div = $("<div>");
    const optionDetail = div.addClass("option-detail-box").text($(this).val());
    
    const optionCount = $("<div class='option-count2'>");
    const plusButton = $("<button type='button' id='plus2'>+</button>");
    const optionPlus = optionCount.append(plusButton);
    const count = $("<div class='count2'>1</div>");
    const optionPlusCount = optionPlus.append(count);
    const optionMinus = $("<button type='button' id='minus2'>-</button>");
    const optionCountbox = optionPlusCount.append(optionMinus);
    $(".product-detail-info-optionbox2").css("display","block");
    $(".product-detail-info-optionbox2").append(optionDetail);
    $(".product-detail-info-optionbox2").append(optionCountbox);
    
    $("#plus2").on("click",function(){
        const currNum = $(".count2").text();
        $(".count2").text(Number(currNum)+1);
	});
	
	$("#minus2").on("click",function(){
        const currNum = $(".count2").text();
        if(Number(currNum) == 1){
            alert("수량은 1개 이상만 가능합니다.");
            return;
        }
        $(".count2").text(Number(currNum)-1);
	});

    let sum = 0;
    $('.count2').each(function(){
        var text = $(this).text();
        let text2 = (Number)(text);
        sum += text2;
        
    });
    let finalPrice = $(".product-detail-info-final-price2").val();
    let totalPrice = sum * finalPrice;
    console.log(totalPrice.toLocaleString());
    $(".info-totalPrice2").text(totalPrice.toLocaleString()+"원");
});






