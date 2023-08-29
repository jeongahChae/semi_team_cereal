$(function(){
    $("#month").append($("<option value=''>월</option>"))
    for(let i=1 ; i<13;i++){
        let optionMonth = $("<option>");
        optionMonth.val(i);
        optionMonth.text(i+"월");
        $("#month").append(optionMonth);
    }
    let mainCategory = $("<option value=''>대분류</option>");
    $("#main-category").append(mainCategory);
    $.ajax({
    	url : "/product/category",
        type : "get",
        dataType : "json",
        success: function(data){
        	let main = [];
        	for(let i=0;i<data.length;i++){
	            if(data[i].categoryRef == 0){
		            main.push(data[i]);
		            let opt = $("<option>");
		            let attrValue = opt.attr("value",data[i].categoryNo);
		            let mainOption = attrValue.text(data[i].categoryName);
		            $("#main-category").append(mainOption);
	        	}
        	}
        }
    });
});


	$("#findMonthlySales").on("click", function(){
	    const year = $("#year").val();
	    const month = $("#month").val();
	    const category = $("#main-category").val();
	    if(year==""||month==""){
	    	alert("연도 혹은 월을 선택해주세요");
	    } else if(category == ""){
	    	alert("카테고리 분류를 선택해주세요");	    
	    }
		$.ajax({
			url: "/adminPage/findCategorySales",
			type: "get",
			data: {year : year,
				month : month,
				category : category},
			dataType : "json",
			success: function(data){
				console.log("성공");
				$("#category-chart").empty();
				for(let i=0 ; i<data.length;i++){
					
					$("#category-chart").append(data[i].categoryRef);
					$("#category-chart").append(data[i].categoryNo);
					$("#category-chart").append(data[i].categoryName);
					$("#category-chart").append(data[i].categorySales);

				}				
			},
			error: function(){
				console.log("무언가 잘못됨..");
			}
			
		});
	});

