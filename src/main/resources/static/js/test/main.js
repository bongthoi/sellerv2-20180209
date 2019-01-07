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

$(function () {
	 
	 $("#btntest").click(function(){
		 if(  $("#modal_choose_product .modal-body").text().trim().length==0){
			 $.get("admin/product",function(data){
					if(data!=null){
						 $("#modal_choose_product .modal-body").html(data);
					}
				 },"text");
		 }
		 else{
			 alert(1);
		 }
		 $("#modal_choose_product").modal('show',true);
	 });
 });