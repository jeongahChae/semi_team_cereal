$(function(){
    let mainCategory = $("<option value=''>대분류</option>");
    $("#main-category").append(mainCategory);
    $.ajax({
        url : "/product/category",
        type : "get",
        dataType : "json",
        success : function(data){
            console.log(data);
            let main = [];
            let furniture = [];
            let light = [];
            let acc = [];
            let fab = [];
            for(let i=0 ; i<data.length;i++){
                if(data[i].categoryRef == 0){
                    main.push(data[i]);
                    let opt = $("<option value='${'data[i].categoryNo'}'>'${'data[i].categoryName'}'</option>");
                    $("#main-category").append(opt);
                } else if(data[i].categoryRef == 1){
                    furniture.push(data[i]);
                } else if(data[i].categoryRef == 2){
                    light.push(data[i]);
                } else if(data[i].categoryRef == 3){
                    acc.push(data[i]);
                } else if(data[i].categoryRef == 4){
                    fab.push(data[i]);
                }
            }
        }
    });
});

/*
function com_parent(){
    let opt = [];
    parents.forEach(data =>{
        opt.push(`<option value="${"data.categoryNo"}">${"data.categoryName"}</option>`);
    });
    $("#main").append(opt);
}

function
*/
/*
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
*/
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


