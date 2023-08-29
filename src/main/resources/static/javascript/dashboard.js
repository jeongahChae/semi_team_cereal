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
     // ----- 가로막대그래프 그리기 -----
    google.charts.load('current', {packages: ['corechart', 'bar']});
    google.charts.setOnLoadCallback(drawBar1);

    function drawBar1(categorySalesList) {
        // 그래프 상에 표현할 데이터
        var data = google.visualization.arrayToDataTable(categorySalesList);
        /*
        var data = google.visualization.arrayToDataTable([
            ['카테고리', '판매수량',],
            ['테이블', 231],
            ['의자', 175],
            ['커튼', 31],
            ['조명', 379],
            ['러그', 26]
        ]);
*/
        // 그래프 그리기 옵션
        var options = {
            title: '카테고리별 판매 수량',
            chartArea: {width: '50%'},
            hAxis: {
                title: '총 판매수량',
                minValue: 0
            },
            vAxis: {
                title: '카테고리'
            }
        };

        // 그래프를 그려넣을 요소 선택 후 데이터, 옵션을 매개변수로 넣어 그리기
        var chart = new google.visualization.BarChart(document.getElementById('chart_div1'));
        chart.draw(data, options);
    }
	
    $("#findMonthlySales").on("click", function(){
		const year = $("#year").val();
	    const month = $("#month").val();
	    const category = $("#main-category").val();
	    if(month==""){
	    	alert("조회하고 싶은 월을 선택하세요.");
	    } else if(category == ""){
	    	alert("카테고리 분류를 선택하세요.");	    
	    }
        $.ajax({
			url: "/adminPage/findCategorySales",
			type: "get",
			data: {year : year,
				month : month,
				category : category},
			dataType : "json",
			success: function(data){
				$("#category-chart").empty();
                let categorySalesList = [];
                categorySalesList.push(['카테고리', '판매수량',]);
                for(let i=0 ; i<data.length;i++){
                	if(category == data[i].categoryRef){
                	const testArr = new Array();
                	testArr.push(data[i].categoryName);
                	testArr.push(data[i].categorySales);
                    categorySalesList.push(testArr);
					}                	
                }
                console.log(categorySalesList);
                drawBar1(categorySalesList);
            }
        });
        
    });







	
	
	
	
   	 
    /*
    $("#findMonthlySales").on("click", function(){
		const year = $("#year").val();
	    const month = $("#month").val();
	    const category = $("#main-category").val();
	    if(month==""){
	    	alert("조회하고 싶은 월을 선택하세요.");
	    } else if(category == ""){
	    	alert("카테고리 분류를 선택하세요.");	    
	    }
		$.ajax({
			url: "/adminPage/findCategorySales",
			type: "get",
			data: {year : year,
				month : month,
				category : category},
			dataType : "json",
			success: function(data){
				$("#category-chart").empty();
                let categorySalesList = [];
                for(let i=0 ; i<data.length;i++){
                	if(category == data[i].categoryRef){
                    categorySalesList.push(data[i]);
					}                	
                }
                console.log(categorySalesList);
				google.charts.load('current', {packages: ['bar']});
        		google.charts.setOnLoadCallback(drawChart);
        		function drawChart(){
        		// Create the data table.
        var chartData = new google.visualization.DataTable();
        data.addColumn('string', 'Topping');
        data.addColumn('number', 'Slices');
        data.addRows([
          ['Mushrooms', 3],
          ['Onions', 1],
          ['Olives', 1],
          ['Zucchini', 1],
          ['Pepperoni', 2]
        ]);

        // Set chart options
        var options = {'title':'How Much Pizza I Ate Last Night',
                       'width':400,
                       'height':300};
        			
    			var chart = new google.visualization.PieChart(document.getElementById('chart_div1'));
                chart.draw(chartData, options);
            		
				}
        						
			},
			error: function(){
				console.log("조회;무언가 잘못됨..");
			}
			
		});
	});
	*/
});


	
