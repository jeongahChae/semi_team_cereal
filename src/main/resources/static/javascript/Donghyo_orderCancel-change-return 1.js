//주문취소/교환/반품 1
//Donghyo_orderCancel-change-return 1.html
$("#tableId").on('click', 'tr', function(e){
    if( $(e.target).is('input:checkbox') ) return;
    var chkbox = $(this).find('td:first-child :checkbox');
    chkbox.prop('checked', !chkbox.prop('checked'));
});