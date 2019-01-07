$(function () {	 
	
	
	 $('.tb-product input[type="checkbox"]').iCheck({
	      checkboxClass: 'icheckbox_flat-blue',
	      radioClass: 'iradio_flat-blue'
	    });

	    //Enable check and uncheck all functionality
	   $(".checkbox-toggle88").click(function () {
	      var clicks = $(this).data('clicks');
	      if (clicks) {
	        //Uncheck all checkboxes
	        $(".tb-product input[type='checkbox']").iCheck("uncheck");
	        $(".fa", this).removeClass("fa-check-square-o").addClass('fa-square-o');
	      } else {
	        //Check all checkboxes
	        $(".tb-product input[type='checkbox']").iCheck("check");
	        $(".fa", this).removeClass("fa-square-o").addClass('fa-check-square-o');
	      }
	      $(this).data("clicks", !clicks);
	    });
	   
	   

	   $("#do_active_product").click(function(){
		   var ids = [];
		     $.each($("#frm-table-product input[type='checkbox']:checked"), function(){            
		    	 ids.push($(this).val());
		     });
		     if(ids.length>0){
		    	 $("#frm-table-product").attr('action',"admin/product/active");
		    	 $("#frm-table-product").submit();
		     }else{
		    	 return;
		     }
	   });
	   
	   
	   $("#do_disabled_product").click(function(){
		   var ids = [];
		     $.each($("#frm-table-product input[type='checkbox']:checked"), function(){            
		    	 ids.push($(this).val());
		     });
		     if(ids.length>0){
		    	 $("#frm-table-product").attr('action',"admin/product/disabled");
		    	 $("#frm-table-product").submit();
		     }else{
		    	 return;
		     }
	   });
	   
	   
	   $("#do_delete_product").click(function(){
		   var ids = [];
		     $.each($("#frm-table-product input[type='checkbox']:checked"), function(){            
		    	 ids.push($(this).val());
		     });
		     if(ids.length>0){
		    	 $("#frm-table-product").attr('action',"admin/product/delete");
		    	 $("#frm-table-product").submit();
		     }else{
		    	 return;
		     }
	   });
	   
	   
	   
	   
	tinymce.init({
		  selector: '#ProductDes',
		  height: 300,
		  menubar: 'file edit insert view format table tools',
		 /* plugins: [
		    'advlist autolink lists link image charmap print preview anchor',
		    'searchreplace visualblocks code fullscreen',
		    'insertdatetime media table contextmenu paste code'
		  ],*/
		   plugins: ["image","link","textcolor","code","charmap","searchreplace","visualblocks","preview","fullscreen","table","lists"],
		  toolbar: 'undo redo | bold italic | fontselect fontsizeselect | forecolor backcolor |alignleft aligncenter alignright  alignjustify | bullist numlist indent outdent | link unlink image',
		  content_css: '//www.tinymce.com/css/codepen.min.css',
		  file_browser_callback_types: 'image',
		    file_browser_callback: function(field_name, url, type, win) {
		        if(type=='image'){
		        	$('#my_form_uploadproduct input').click();
		        }
		    }
	});
	$("#btnsaveproduct").click(function(){
			//var pdata=$("#frm-about").serialize();
					var MCEeditor1=tinymce.get('ProductDes');
					var content1=MCEeditor1.getContent();		
					$("#ProductDes").val(content1);
					
					//var pdata=$("#frm_about_edit").serialize();
					$("#frmproduct").submit();
			
	});
	
	$('#txtimage_product').ajaxfileupload({
		'valid_extensions' : ['gif','png','jpg','jpeg'],
	    'action': "upload/publicimg/?folder=product",
	    'onComplete': function(response) {	
	        if (response.status==false) {
	        	$.messager.alert('Error',response.message,'error');
	        	 //$("#strongmessage").html("<font color='red'>" + JSON.stringify(response.message) + " </font>");
	        }
	        if (response.status==true) {
	        	//$.messager.alert('Info',response.message,'info');
	            var pram = response.pram;
	            var url_img=baseurl+"/upload/product/"+pram;
	            top.$('.mce-btn.mce-open').parent().find('.mce-textbox').val(url_img);
	           // $("#id_uploadsuccess").val(pram);
	            //$("#branchimg_@index").attr('src','upload/branch/'+pram);
	           // $("#strongmessage").html("<font color='green'>" + JSON.stringify(response.message) + " </font>");
	        }
	    },
	    'onStart': function() {
	    
	    }
	});
	
	$("#feature_image_btn").click(function(){
		 $("#txtimage_product_feature").click();
	});
	 $("#txtimage_product_feature").ajaxfileupload({
		   'action': "upload/publicimg/?folder=product",
	        'valid_extensions' : ['jpg','png','gif'],
	        'onComplete': function(response) {   
	        
	        		 if (response.status==false) {
	                     alert(response.message);
	                 }
	                 if (response.status==true) {
	                     var pram = response.pram;
	                     var fullpath= baseurl+"/upload/product/" +pram;
	                     var imgtag='<img alt="" src="'+fullpath+'" class="img img-responsive">';
	                     $("#feature_image_input").val(pram);
	                     $("#feature_image_div").html(imgtag);
	                 }
	        },
	        'onStart': function() {
	        }
	    });
	 
	 $("#btnGenbarcode").click(function(){
		$.get("admin/product/get/barcode",function(data){
			if(data!=null){
				if(data.length==13){
					$("#idproduct").val(data);
				}else{
					alert("Genarate barcode Error, please try again!");
				}
			}
		},"text") 
	 });
});