$(document).ready(function(){
	
	// click event button Them moi danh muc
	$('.btnThemBrand').on("click", function(event) {
		event.preventDefault();
		$('.brandForm #brandModal').modal();
		$('.brandForm #id').prop("disabled", true);
		$('#form').removeClass().addClass("addForm");
		$('#form #btnSubmit').removeClass().addClass("btn btn-primary btnSaveForm");
	});
	
	$('#brandModal').on('hidden.bs.modal', function () {
		$('#form').removeClass().addClass("brandForm");
		$('#form #btnSubmit').removeClass().addClass("btn btn-primary");
		resetText();
	});
	
	// reset text trong form
    function resetText(){
    	$("#id").val("");
    	$("#nameManufacturer").val("");
    };
    

	ajaxGet(1);	
	
	// do get
	function ajaxGet(page){
		$.ajax({
			type: "GET",		
			url: "http://localhost:8080/shopmall/api/brand/all?page=" + page,
			success: function(result){
				$.each(result.content, function(i, brand){
					var brandRow = '<tr style="text-align: center;">' +
					                  '<td width="20%"">' + brand.id + '</td>' +
					                  '<td>' + brand.nameManufacturer + '</td>' +
					                  '<td>'+'<input type="hidden" value=' + brand.id + '>'
					                     + '<button class="btn btn-primary btnCapNhatbrand" >Cập nhật</button>' +
					                     '   <button class="btn btn-danger btnXoabrand">Xóa</button></td>'
				                      '</tr>';
					$('.brandTable tbody').append(brandRow);
				});
				
				if(result.totalPages > 1 ){
					for(var numberPage = 1; numberPage <= result.totalPages; numberPage++) {
						var li = '<li class="page-item "><a class="pageNumber">'+numberPage+'</a></li>';
					    $('.pagination').append(li);
					};				
					
					// active page pagination
			    	$(".pageNumber").each(function(index){	
			    		if($(this).text() == page){
			    			$(this).parent().removeClass().addClass("page-item active");
			    		}
			    	});
				};
			},
			error : function(e){
				alert("Error: ",e);
				console.log("Error" , e );
			}
		});
	};
	
    
    $(document).on('click', '.btnSaveForm', function (event) {
    	event.preventDefault();
		ajaxPost();
		resetData();
    });
    
	function ajaxPost(){
    	// PREPARE FORM DATA
    	var formData = { nameManufacturer : $("#nameManufacturer").val() } ;
    	
    	// DO POST
    	$.ajax({
    		async:false,
			type : "POST",
			contentType : "application/json",
			url : "http://localhost:8080/shopmall/api/brand/save",
			data : JSON.stringify(formData),
            // dataType : 'json',
			success : function(response) {
				if(response.status == "success"){
					$('#brandModal').modal('hide');
					alert("Thêm thành công");
				} else {
			    	$('input').next().remove();
		             $.each(response.errorMessages, function(key,value){
		   	            $('input[id='+ key +']').after('<span class="error">'+value+'</span>');
		               });
				}
		    	
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		}); 
    };
   
    // click edit button
    $(document).on("click",".btnCapNhatbrand",function(){
		event.preventDefault();
		$('.brandForm #id').prop("disabled", true);
		var brandId = $(this).parent().find('input').val();
		
		$('#form').removeClass().addClass("updateForm");
		$('#form #btnSubmit').removeClass().addClass("btn btn-primary btnUpdateForm");
		var href = "http://localhost:8080/shopmall/api/brand/" + brandId;
		$.get(href, function(brand, status) {
			$('.updateForm #id').val(brand.id);
			$('.updateForm #nameManufacturer').val(brand.nameManufacturer);
		});
		
		removeElementsByClass("error");
		
		$('.updateForm #brandModal').modal();
	});
    
    // put request
    
	$(document).on('click', '.btnUpdateForm', function (event) {
	   event.preventDefault();
	   ajaxPut();
	   resetData();
    });

    
    function ajaxPut(){
    	// PREPARE FORM DATA
    	var formData = {
    			id : $('#id').val(),
    			nameManufacturer : $("#nameManufacturer").val(),
    	}    	
    	// DO PUT
    	$.ajax({
    		async:false,
			type : "PUT",
			contentType : "application/json",
			url : "http://localhost:8080/shopmall/api/brand/update",
			data : JSON.stringify(formData),
            // dataType : 'json',
			success : function(response) {
				
				if(response.status == "success"){
					$('#brandModal').modal('hide');
					alert("Cập nhật thành công");
				} else {
			    	$('input').next().remove();
		            $.each(response.errorMessages, function(key,value){
		   	            $('input[id='+ key +']').after('<span class="error">'+value+'</span>');
		               });
				}
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
    };
 
	// delete request
    $(document).on("click",".btnXoabrand", function() {
		
		var brandId = $(this).parent().find('input').val();
		var workingObject = $(this);
		
		var confirmation = confirm("Bạn chắc chắn xóa nhãn hiệu này ?");
		if(confirmation){
		  $.ajax({
			  type : "DELETE",
			  url : "http://localhost:8080/shopmall/api/brand/delete/" + brandId,
			  success: function(resultMsg){
				 resetDataForDelete();
				 alert("Xóa thành công");
			  },
			  error : function(e) {
				 alert("Không thể xóa nhãn hiệu này, hãy kiểm tra lại");
				 console.log("ERROR: ", e);
			  }
		  });
		}
     });
    
    // reset table after post, put
    function resetData(){
    	$('.brandTable tbody tr').remove();
    	var page = $('li.active').children().text();
    	$('.pagination li').remove();
    	var count = $('.brandTable tbody').children().length;
    	ajaxGet(page);
    };
    
    // reset table after post, put
    function resetDataForDelete(){
    	var count = $('.brandTable tbody').children().length;
    	$('.brandTable tbody tr').remove();
    	var page = $('li.active').children().text();
    	$('.pagination li').remove();
    	if(count == 1){    	
    		ajaxGet(page -1 );
    	} else {
    		ajaxGet(page);
    	}

    };
    
    // event khi click vào phân trang Nhãn hiệu
	$(document).on('click', '.pageNumber', function (event){
//		event.preventDefault();
		var page = $(this).text();	
    	$('.brandTable tbody tr').remove();
    	$('.pagination li').remove();
    	ajaxGet(page);	
	});
    
    
    function removeElementsByClass(className){
        var elements = document.getElementsByClassName(className);
        while(elements.length > 0){
            elements[0].parentNode.removeChild(elements[0]);
        }
    }
});