$(function(){
    $("#month").append($("<option value=''>월</option>"))
    for(let i=1 ; i<13;i++){
        let optionMonth = $("<option>");
        optionMonth.val(i);
        optionMonth.text(i+"월");
        $("#month").append(optionMonth);
    }
});




