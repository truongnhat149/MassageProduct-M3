// chờ tải xong mới làm việc
$(document).ready(function() {

	// load first when coming page
	ajaxGet(1);	

	// do get
	function ajaxGet(page){
		// prepare data
   	    var data = $('#searchForm').serialize();	
		$.ajax({
			type: "GET",		
			data: data,
			contentType : "application/json",
			url: "http://localhost:8080/shopmall/api/products/all" + '?page=' + page,
			success: function(result){
				$.each(result.content, function(i, product){
					var productRow = '<tr>' +
					                  '<td>' + '<img src="/shopmall/img/'+product.id+'.png" class="img-responsive" style="height: 50px; width: 50px" />'+'</td>' +
					                  '<td>' + product.nameProduct + '</td>' +
					                  '<td>' + product.category.nameCategory + '</td>' +
					                  '<td>' + product.manufacturer.nameManufacturer + '</td>' +
					                  '<td>' + product.unitPrice + '</td>' +
					                  '<td>' + product.warehouseuUnit + '</td>' +
					                  '<td width="0%">'+'<input type="hidden" id="productId" value=' + product.id + '>'+ '</td>' + 
					                  '<td> <button class="btn btn-warning btnChiTiet" style="margin-right: 6px">Chi tiết</button>' ;
					
					var checkNameCategory = (product.category.nameCategory.toLowerCase()).indexOf("Massage".toLowerCase());
					productRow += ( checkNameCategory != -1)?'<button class="btn btn-primary btnCapNhatMassage" >Cập nhật</button>':'<button class="btn btn-primary btnCapNhatOther" >Cập nhật</button>';
					productRow += '  <button class="btn btn-danger btnDeleteproduct">Xóa</button></td>'+'</tr>';
					$('.productTable tbody').append(productRow);
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
	
	// event khi click vào dropdown chọn danh mục thêm sản phẩm
	$('#categoryDropdown').mouseup(function() {
		var open = $(this).data("isopen");
		if (open) {
			var label = $('#categoryDropdown option:selected').text();
			if ((label.toLowerCase()).indexOf("Massage".toLowerCase()) != -1) {
				$('.massageModal').modal('show');
				$("#idCategoryMassage").val($(this).val());
				$('#MassageForm').removeClass().addClass("addMassageForm");
				$('#MassageForm #btnSubmit').removeClass().addClass("btn btn-primary btnSaveMassageForm");
			} else {
				$('.otherModal').modal('show');
				$("#idCategoryKhac").val($(this).val());
				$('#otherForm').removeClass().addClass("addOtherForm");
				$('#otherForm #btnSubmit').removeClass().addClass("btn btn-primary btnSaveOtherForm");
			}			
            $(".modal-title").text("Thêm mới sản phẩm danh mục "+ label);
			
		}
		$(this).data("isopen", !open);
	});
	
    $(document).on('click', '#btnDuyetProduct', function (event) {
    	event.preventDefault();
    	$('.productTable tbody tr').remove();
    	$('.pagination li').remove();
    	ajaxGet(1);
        
    });
    
    
	// event khi ẩn modal form
	$('.massageModal, .otherModal').on('hidden.bs.modal', function(e) {
		e.preventDefault();
		$("#idCategoryMassage, #idCategoryKhac").val("");
		$("#idProductMassage, #idProductKhac").val("");
			
	    $('#MassageForm').removeClass().addClass("MassageForm");
		$('#MassageForm #btnSubmit').removeClass().addClass("btn btn-primary");
		$('#MassageForm').trigger("reset");
		
		$('#otherForm').removeClass().addClass("otherForm");
		$('#otherForm #btnSubmit').removeClass().addClass("btn btn-primary");
		$('#otherForm').trigger("reset");
		$('input, textarea').next().remove();
	});
	
	// btn Save Form Massage Event
    $(document).on('click', '.btnSaveMassageForm', function (event) {
    	event.preventDefault();
		ajaxPostMassage();
		resetData();
    });
 
    function ajaxPostMassage() {
    	// PREPATEE DATA
    	 var form = $('.addMassageForm')[0];   	 
    	 var data = new FormData(form);
    	 
    	 // do post
    	 $.ajax({
     		async:false,
 			type : "POST",
 			contentType : "application/json",
 			url : "http://localhost:8080/shopmall/api/products/save",
 			enctype: 'multipart/form-data',
 			data : data,
 			
 		    // prevent jQuery from automatically transforming the data into a
			// query string
 	        processData: false,
 	        contentType: false,
 	        cache: false,
 	        timeout: 1000000,
 	        
 			success : function(response) {
 				if(response.status == "success"){
 					$('.massageModal').modal('hide');
 					alert("Thêm thành công");
 				} else {
 			    	$('input, textarea').next().remove();
 		            $.each(response.errorMessages, function(key,value){
 		            	if(key != "id"){
 		   	                $('input[name='+ key +']').after('<span class="error">'+value+'</span>');
 		   	                $('textarea[name='+ key +']').after('<span class="error">'+value+'</span>');
 		            	};
 		              });
 				}
 		    	
 			},
 			error : function(e) {
 				alert("Error!")
 				console.log("ERROR: ", e);
 			}
 		}); 
    }
    
    // btnSaveOtherForm event click
    $(document).on('click', '.btnSaveOtherForm', function (event) {
    	event.preventDefault();
		ajaxPostOther();
		resetData();
    });
 
    function ajaxPostOther() {
    	// PREPATEE DATA
    	 var form = $('.addOtherForm')[0];   	 
    	 var data = new FormData(form);  	 
    	 // do post
    	 $.ajax({
     		async:false,
 			type : "POST",
 			contentType : "application/json",
 			url : "http://localhost:8080/shopmall/api/products/save",
 			enctype: 'multipart/form-data',
 			data : data,
 			
 		    // prevent jQuery from automatically transforming the data into a
			// query string
 	        processData: false,
 	        contentType: false,
 	        cache: false,
 	        timeout: 1000000,
 	        
 			success : function(response) {
 				if(response.status == "success"){
 					$('.otherModal').modal('hide');
 					alert("Thêm thành công");
 				} else {
 					$('input, textarea').next().remove();
 		            $.each(response.errorMessages, function(key,value){
 		            	if(key != "id"){
 		   	                $('input[name='+ key +']').after('<span class="error">'+value+'</span>');
 		   	                $('textarea[name='+ key +']').after('<span class="error">'+value+'</span>');
 		            	};
 		              });
 				}
 		    	
 			},
 			error : function(e) {
 				alert("Error!")
 				console.log("ERROR: ", e);
 			}
 		}); 
    }
    
    
    // click cập nhật button 
    // vs danh mục Massage
    $(document).on("click",".btnCapNhatMassage", function(event){
		event.preventDefault();
		var productId = $(this).parent().prev().children().val();	
		$('#MassageForm').removeClass().addClass("updateMassageForm");
		$('#MassageForm #btnSubmit').removeClass().addClass("btn btn-primary btnUpdateMassageForm");
	
		var href = "http://localhost:8080/shopmall/api/products/"+productId;
		$.get(href, function(product) {
			populate('.updateMassageForm', product);
			$("#idCategoryMassage").val(product.category.id);
			var hangSXId = product.manufacturer.id;
			$("#nhaSXId").val(hangSXId);	
		});
		
		removeElementsByClass("error");		
		$('.updateMassageForm .massageModal').modal();
	});
    
	// btn update Massage form Event
    $(document).on('click', '.btnUpdateMassageForm', function (event) {
    	event.preventDefault();
		ajaxPutMassage();
		resetData();
    });
 
    function ajaxPutMassage() {
    	
   	 var form = $('.updateMassageForm')[0];   	 
	 var data = new FormData(form);
	 console.log(data);
	 
	 // do post
	 $.ajax({
 		async:false,
			type : "POST",
			contentType : "application/json",
			url : "http://localhost:8080/shopmall/api/products/save",
			enctype: 'multipart/form-data',
			data : data,
			
		    // prevent jQuery from automatically transforming the data into a
		// query string
	        processData: false,
	        contentType: false,
	        cache: false,
	        timeout: 1000000,
	        
			success : function(response) {
				if(response.status == "success"){
					$('.massageModal').modal('hide');
					alert("Cập nhật thành công");
				} else {
			    	$('input, textarea').next().remove();
		            	if(key != "id"){
 		   	                $('input[name='+ key +']').after('<span class="error">'+value+'</span>');
 		   	                $('textarea[name='+ key +']').after('<span class="error">'+value+'</span>');
 		            	};
				}
		    	
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		}); 
    }
    
    
    // với danh mục khác
    $(document).on("click",".btnCapNhatOther", function(event){
		event.preventDefault();
		var productId = $(this).parent().prev().children().val();		
		$('#otherForm').removeClass().addClass("updateOtherForm");
		$('#otherForm #btnSubmit').removeClass().addClass("btn btn-primary btnUpdateOtherForm");
	
		var href = "http://localhost:8080/shopmall/api/products/"+productId;
		$.get(href, function(product) {
			populate('.updateOtherForm', product);
			$("#idCategoryKhac").val(product.category.id);
			var hangSXId = product.manufacturer.id;
			$("#nhaSXIdKhac").val(hangSXId);	
		});		
		removeElementsByClass("error");		
		$('.updateOtherForm .otherModal').modal();
	});
    
    // btnUpdateOtherForm event click
    $(document).on('click', '.btnUpdateOtherForm', function (event) {
    	event.preventDefault();
	    ajaxPutOther();
		resetData();
    });
 
    function ajaxPutOther() {
    	// PREPARE DATA
   	     var form = $('.updateOtherForm')[0];   	 
	     var data = new FormData(form);  	
    	 // do put
    	 $.ajax({
      		async:false,
  			type : "POST",
  			contentType : "application/json",
  			url : "http://localhost:8080/shopmall/api/products/save",
  			enctype: 'multipart/form-data',
  			data : data,
  			
  		    // prevent jQuery from automatically transforming the data into a
 			// query string
  	        processData: false,
  	        contentType: false,
  	        cache: false,
  	        timeout: 1000000,
  	        
  			success : function(response) {
  				if(response.status == "success"){
  					$('.otherModal').modal('hide');
  					alert("Cập nhật thành công");
  				} else {
  					$('input, textarea').next().remove();
  		            $.each(response.errorMessages, function(key,value){
 		            	if(key != "id"){
 		   	                $('input[name='+ key +']').after('<span class="error">'+value+'</span>');
 		   	                $('textarea[name='+ key +']').after('<span class="error">'+value+'</span>');
 		            	};
  		            });
  				}
  		    	
  			},
  			error : function(e) {
  				alert("Error!")
  				console.log("ERROR: ", e);
  			}
  		}); 
     }
    
    
	// click vào button xóa
    $(document).on("click",".btnDeleteproduct", function() {
		
    	var productId = $(this).parent().prev().children().val();	
		var workingObject = $(this);
		
		var confirmation = confirm("Bạn chắc chắn xóa sản phẩm này ?");
		if(confirmation){
		  $.ajax({
			  async:false,
			  type : "DELETE",
			  url : "http://localhost:8080/shopmall/api/products/delete/" + productId,
			  success: function(resultMsg){
				  resetDataForDelete();
				  alert("Xóa thành công");
			  },
			  error : function(e) {
				 console.log("ERROR: ", e);
			  }
		  });	
		}
		resetData();
     });
    
	// click vào button chi tiết
    $(document).on("click",".btnChiTiet", function() {
		
    	var productId = $(this).parent().prev().children().val();	
    	console.log(productId);
    	
    	var href = "http://localhost:8080/shopmall/api/products/"+productId;
		$.get(href, function(product) {
			$('.hinhAnh').attr("src", "/shopmall/img/"+product.id+".png");
			$('.nameProduct').html("<span style='font-weight: bold'>Tên sản phẩm: </span> "+ product.nameProduct);
			$('.maProduct').html("<span style='font-weight: bold'> Mã sản phẩm: </span>"+ product.id);
			$('.hangSangXuat').html("<span style='font-weight: bold'>Hãng sản xuất: </span>"+ product.manufacturer.nameManufacturer);
			
			var checkNameCategory = (product.category.nameCategory.toLowerCase()).indexOf("Massage".toLowerCase());
			
			console.log(checkNameCategory != -1);
			if(checkNameCategory != -1){
			  $('.thietKe').html("<span style='font-weight: bold'>Thiết kế: </span>"+ product.thietKe);
			}
			$('.genaralInfomation').html("<span style='font-weight: bold'>Thông tin chung: </span>"+ product.genaralInfomation);
			$('.unitPrice').html("<span style='font-weight: bold'>Đơn giá: </span>"+ product.unitPrice +" VNĐ");
			$('.baoHanh').html("<span style='font-weight: bold'>Bảo hành: </span>"+ product.thongTinBaoHanh);
			$('.warehouseuUnit').html("<span style='font-weight: bold'>Đơn vị trong kho: </span>"+ product.warehouseuUnit);
			$('.sellingUnit').html("<span style='font-weight: bold'>Đơn vị bán: </span>"+ product.sellingUnit);
			
		});
			
    	$('#chiTietModal').modal('show');
    	
    });
    
    // reset table after delete
    function resetDataForDelete(){
       	var count = $('.productTable tbody').children().length;
    	$('.productTable tbody tr').remove();
    	var page = $('li.active').children().text();
    	$('.pagination li').remove();
    	console.log(page);
    	if(count == 1){    	
    		ajaxGet(page -1 );
    	} else {
    		ajaxGet(page);
    	}

    };
    
    // reset table after post, put, filter
    function resetData(){   	
    	var page = $('li.active').children().text();
    	$('.productTable tbody tr').remove();
    	$('.pagination li').remove();
        ajaxGet(page);
    };
    
    // event khi click vào phân trang Sản phẩm
	$(document).on('click', '.pageNumber', function (event){
		event.preventDefault();
		var page = $(this).text();	
    	$('.productTable tbody tr').remove();
    	$('.pagination li').remove();
    	ajaxGet(page);	
	});
	
	
    // event khi click vào nhấn phím vào ô tìm kiếm sản phẩm theo tên
	$(document).on('keyup', '#searchById', function (event){
		event.preventDefault();
		var productId = $('#searchById').val();
		console.log(productId);
		if(productId != ''){
    	  $('.productTable tbody tr').remove();
    	  $('.pagination li').remove();
		  var href = "http://localhost:8080/shopmall/api/products/"+ productId;
		  $.get(href, function(product) {
			  var productRow = '<tr>' +
              '<td>' + '<img src="/shopmall/img/'+product.id+'.png" class="img-responsive" style="height: 50px; width: 50px" />'+'</td>' +
              '<td>' + product.nameProduct + '</td>' +
              '<td>' + product.category.nameCategory + '</td>' +
              '<td>' + product.manufacturer.nameManufacturer + '</td>' +
              '<td>' + product.unitPrice + '</td>' +
              '<td>' + product.warehouseuUnit + '</td>' +
              '<td width="0%">'+'<input type="hidden" id="productId" value=' + product.id + '>'+ '</td>' + 
              '<td><button class="btn btn-warning btnChiTiet" style="margin-right: 6px">Chi tiết</button>'  ;

              var checkNameCategory = (product.category.nameCategory.toLowerCase()).indexOf("Massage".toLowerCase());
                  productRow += ( checkNameCategory != -1)?'  <button class="btn btn-primary btnCapNhatMassage" >Cập nhật</button>':'<button class="btn btn-primary btnCapNhatOther" >Cập nhật</button>';
                  productRow += ' <button class="btn btn-danger btnDeleteproduct">Xóa</button></td>'+'</tr>';
                  $('.productTable tbody').append(productRow);
		  });
		} else {
			resetData();
		}
	});
	
    // fill input form với JSon Object
    function populate(frm, data) {
    	  $.each(data, function(key, value){
    	    $('[name='+key+']', frm).val(value);
    	  });
    	}
    
	// event khi ẩn modal chi tiết
	$('#chiTietModal').on('hidden.bs.modal', function(e) {
		e.preventDefault();
		$(".chiTietForm p").text(""); // reset text thẻ p
	});
    
    // remove element by class name
    function removeElementsByClass(className){
        var elements = document.getElementsByClassName(className);
        while(elements.length > 0){
            elements[0].parentNode.removeChild(elements[0]);
        }
    }
    
    (function ($) {
        $.fn.serializeFormJSON = function () {

            var o = {};
            var a = this.serializeArray();
            $.each(a, function () {
                if (o[this.name]) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        };
    })(jQuery);

});