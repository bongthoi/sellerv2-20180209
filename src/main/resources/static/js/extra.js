function searchProductAjax(e){
	var frmSearch=$(e).closest("form");
	var pdata=frmSearch.serialize();
		$.get(frmSearch.attr("action"),pdata,function(data){
			if(data!=null){
				$(e).closest(".modal-body").html(data);
			}
		 },"text");
} 
function js_request_pagging(e,action){
	$.get(action,function(data){
		if(data!=null){
			$(e).closest(".modal-body").html(data);
		}
	 },"text");
}
function PressNumber(evt){

	 evt = (evt) ? evt : window.event;
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	        return false;
	    }
	    return true;
}