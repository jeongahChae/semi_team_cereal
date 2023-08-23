$("#main-category").on("change",function(){
    $("#sub-category").css("display","inline-block");
    let subFurniture = ["의자","책상","침대"];
    let subLight = ["스탠드","탁상","벽걸이"];
    let subAcc = ["시계","꽃병","거울"];
    let subFabric = ["커튼","러그","쿠션"];

    let mainCategory = $("#main-category").val();

    let subCategory;

    if(mainCategory == "가구"){
        subCategory = subFurniture;
    }else if(mainCategory == "조명"){
        subCategory = subLight;
    }else if(mainCategory == "악세사리"){
        subCategory = subAcc;
    }else if(mainCategory == "패브릭"){
        subCategory = subFabric;
    }

    $("#sub-category").empty();
    let fixoption = $("<option value=''>소분류</option>");
    $("#sub-category").append(fixoption)

    for(let i=0; i<subCategory.length; i++){
        let option = $("<option>");
        let attr = option.attr("value",subCategory[i]);
        let suboption = attr.text(subCategory[i]);
        console.log(suboption);
        $("#sub-category").append(suboption);
    }
});

$(".option-addBtn").on("click", function(){
    const optionColor = $("<input type='text' class='input-form' name='optionName' placeholder='색'>");
    const optionStock = $("<input type='text' class='input-form' name='optionAmount' placeholder='재고(숫자만)'>");
    const optionDelbtn = $("<button type='button' class='option-delBtn'>삭제</button>");
    $(".product-option-frm>td").append(optionColor).append(optionStock).append(optionDelbtn);
    $(".option-delBtn").on("click", function(){
        $(this).prev().prev().remove();
        $(this).prev().remove();
        $(this).remove();
    });
});


