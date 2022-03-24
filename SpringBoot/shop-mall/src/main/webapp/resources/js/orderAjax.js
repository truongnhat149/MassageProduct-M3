$(document).ready(function() {
	
	// load first when coming page
	ajaxGet(1);	
	
	function ajaxGet(page){		
		var data = { trangThai : $('#trangThai').val(), tuNgay: $('#fromDate').val(), denNgay: $('#toDate').val()  } 
		$.ajax({
			type: "GET",		
			data: data,
			contentType : "application/json",
			url: "http://localhost:8080/shopmall/api/order/all" + '?page=' + page,
			success: function(result){
				$.each(result.content, function(i, order){
					// tính giá trị đơn hàng\
					var sum = 0;
					var check = order.statusOrder == "Hoàn thành" || order.statusOrder == "Chờ duyệt";
					if(check){
						$.each(order.danhSachChiTiet, function(i, chiTiet){
							sum += chiTiet.unitPrice * chiTiet.soLuongNhanHang;
						});
					} else {
						$.each(order.danhSachChiTiet, function(i, chiTiet){
							sum += chiTiet.unitPrice * chiTiet.soLuongDat;
						});
					}

					var orderRow = '<tr>' +
					                  '<td>' + order.id+ '</td>' +
					                  '<td>' + order.hoTenNguoiNhan + '</td>' +
					                  '<td>' + order.statusOrder + '</td>' +
					                  '<td>' + sum + '</td>' +
					                  '<td>' + order.orderDate + '</td>' +
					                  '<td>' + order.ngayGiaoHang + '</td>' +
					                  '<td>' + order.ngayNhanHang + '</td>' +
					                  '<td width="0%">'+'<input type="hidden" class="orderId" value=' + order.id + '>'+ '</td>'+
					                  '<td><button class="btn btn-warning btnChiTiet" >Chi Tiết</button>';
					     if(order.statusOrder == "Đang chờ giao" || order.statusOrder == "Đang giao"){
					    	 orderRow += ' &nbsp;<button class="btn btn-primary btnPhanCong">Phân công</button>'+
					    	               ' &nbsp;<button class="btn btn-danger btnHuy">Hủy đơn</button>' ;
					     } else if (order.statusOrder == "Chờ duyệt"){
					         orderRow += ' &nbsp;<button class="btn btn-primary btnCapNhat" >Cập Nhật</button> </td>';
					     }
					                  
					$('.orderTable tbody').append(orderRow);
					
					$('td').each( function(i){
						if ($(this).html() == 'null'){
							$(this).html('');
						}
					});
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
	
	
    // event khi click vào button Chi tiết đơn
	$(document).on('click', '.btnPhanCong', function (event){
		event.preventDefault();
		var orderId = $(this).parent().prev().children().val();	
		$("#orderId").val(orderId);
		console.log(orderId);
		$("#phanCongModal").modal();
	});
	
	$(document).on('click', '#btnPhanCongSubmit', function (event) {
		var email = $("select[name=shipper]").val();
		var orderId = $("#orderId").val();
        console.log(orderId);
		ajaxPostPhanCongorder(email, orderId)

	});	
	
	function ajaxPostPhanCongorder(email, orderId) { 

    	 $.ajax({
     		async:false,
 			type : "POST",
 			contentType : "application/json",
 			url : "http://localhost:8080/shopmall/api/order/assign?shipper="+email+"&orderId="+orderId,
 			enctype: 'multipart/form-data',
 	        
 			success : function(response) {
 				alert("Phân công đơn hàng thành công");
 				var trangThai = $('#trangThai').val();
			    location.href = window.location.href;
 			},
 			error : function(e) {
 				alert("Error!")
 				console.log("ERROR: ", e);
 			}
 		}); 
    }	
		
	$(document).on('click', '#btnDuyetOrder', function (event) {
		event.preventDefault();
		resetData();
	});	
	
    // reset table after post, put, filter
    function resetData(){   	
    	var page = $('li.active').children().text();
    	$('.orderTable tbody tr').remove();
    	$('.pagination li').remove();
        ajaxGet(page);
    };
    
    // event khi click vào phân trang Đơn hàng
	$(document).on('click', '.pageNumber', function (event){
//		event.preventDefault();
		var page = $(this).text();	
    	$('.orderTable tbody tr').remove();
    	$('.pagination li').remove();
    	ajaxGet(page);	
	});
	
    // event khi click vào button Chi tiết đơn
	$(document).on('click', '.btnChiTiet', function (event){
		event.preventDefault();
		
		var orderId = $(this).parent().prev().children().val();	
//		console.log(orderId);
		var href = "http://localhost:8080/shopmall/api/order/"+orderId;
		$.get(href, function(order) {
			$('#maOrder').text("Mã đơn hàng: "+ order.id);
			$('#hoTenNguoiNhan').text("Người nhận: "+ order.hoTenNguoiNhan);
			$('#sdtNhanHang').text("SĐT: "+ order.sdtNhanHang);
			$('#diaChiNhan').text("Địa chỉ: "+ order.diaChiNhan);
			$('#statusOrder').text("Trạng thái đơn: "+ order.statusOrder);
			$("#orderDate").text("Ngày đặt: "+ order.orderDate);
			
			if(order.ngayGiaoHang != null){
				$("#ngayShipHang").text("Ngày giao: "+ order.ngayGiaoHang);
			}
			
			if(order.ngayNhanHang != null){
				$("#ngayNhanHang").text("Ngày nhận: "+ order.ngayNhanHang);
			}
			
			if(order.ghiChu != null){
				$("#ghiChu").html("<strong>Ghi chú</strong>:<br> "+ order.ghiChu);
			}
			
			if(order.nguoiDat != null){
				$("#nguoiDat").html("<strong>Người đặt</strong>:  "+ order.nguoiDat.hoTen);
			}
			
			if(order.shipper != null){
				$("#shipper").html("<strong>Shipper</strong>: "+ order.shipper.hoTen);
			}
			 
			var check = order.statusOrder == "Hoàn thành" || order.statusOrder == "Chờ duyệt" ;
			if(check){
				$('.chiTietTable').find('thead tr').append('<th id="soLuongNhanTag" class="border-0 text-uppercase small font-weight-bold"> SỐ LƯỢNG NHẬN </th>');
			}
			// thêm bảng:
			var sum = 0; // tổng giá trị đơn
			var stt = 1;
			$.each(order.danhSachChiTiet, function(i, chiTiet){
				var chiTietRow = '<tr>' +
				'<td>' + stt + '</td>' +
                '<td>' + chiTiet.sanPham.nameProduct + '</td>' +
                '<td>' + chiTiet.unitPrice + '</td>'+
                '<td>' + chiTiet.soLuongDat+ '</td>';

				if(check){
					chiTietRow += '<td>' + chiTiet.soLuongNhanHang + '</td>';
					sum += chiTiet.unitPrice * chiTiet.soLuongNhanHang;
				} else {
	                sum += chiTiet.unitPrice * chiTiet.soLuongDat;
				}
	             
				$('.chiTietTable tbody').append(chiTietRow);
                stt++;
	    	  });		

			$("#tongTien").text("Tổng : "+ sum);
		});
		$("#chiTietModal").modal();
	});
	
	
    // event khi click vào nhấn phím vào ô tìm kiếm đơn hàng theo id
	$(document).on('keyup', '#searchById', function (event){
		event.preventDefault();
		var orderId = $('#searchById').val();
		console.log(orderId);
		if(orderId != ''){
    	  $('.orderTable tbody tr').remove();
    	  $('.pagination li').remove();
		  var href = "http://localhost:8080/shopmall/api/order/"+orderId;
		  $.get(href, function(order) {
				// tính giá trị đơn hàng
			  var sum = 0;
			  var check = order.statusOrder == "Hoàn thành" || order.statusOrder == "Chờ duyệt";
			  
				if(check){
					$.each(order.danhSachChiTiet, function(i, chiTiet){
						sum += chiTiet.unitPrice * chiTiet.soLuongNhanHang;
					});
				} else {
					$.each(order.danhSachChiTiet, function(i, chiTiet){
						sum += chiTiet.unitPrice * chiTiet.soLuongDat;
					});
				}	  
				
			 var orderRow = '<tr>' +
             '<td>' + order.id+ '</td>' +
             '<td>' + order.hoTenNguoiNhan + '</td>' +
             '<td>' + order.statusOrder + '</td>' +
             '<td>' + sum + '</td>' +
             '<td>' + order.orderDate + '</td>' +
             '<td>' + order.ngayGiaoHang + '</td>' +
             '<td>' + order.ngayNhanHang + '</td>' +
             '<td width="0%">'+'<input type="hidden" id="orderId" value=' + order.id + '>'+ '</td>'+
             '<td><button class="btn btn-primary btnChiTiet" >Chi Tiết</button>';
			
			 if(order.statusOrder == "Đang chờ giao" || order.statusOrder == "Đang giao"){
		    	 orderRow += ' &nbsp;<button class="btn btn-danger btnPhanCong">Phân công</button>';
		     } else if (order.statusOrder == "Chờ duyệt"){
		         orderRow += ' &nbsp;<button class="btn btn-warning btnCapNhat" >Cập Nhật</button> </td>';
		     }
            
             $('.orderTable tbody').append(orderRow);
			 $('td').each( function(i){
				if ($(this).html() == 'null'){
					$(this).html('');
				}
			 });             
		  });
		} else {
			resetData();
		}
	});
	
    // event khi click vào button cập nhật đơn
	$(document).on('click', '.btnCapNhat', function (event){
		event.preventDefault();
		var orderId = $(this).parent().prev().children().val();	
		$("#idOrderXacNhan").val(orderId);
		var href = "http://localhost:8080/shopmall/api/order/"+orderId;
		$.get(href, function(order) {
			// thêm bảng:
			var stt = 1;
			$.each(order.danhSachChiTiet, function(i, chiTiet){
				var chiTietRow = '<tr>' +
				'<td>' + stt + '</td>' +
                '<td>' + chiTiet.sanPham.nameProduct + '</td>' +
                '<td>' + chiTiet.unitPrice + '</td>'+
                '<td>' + chiTiet.soLuongDat + '</td>'+
                '<td>' + chiTiet.soLuongNhanHang + '</td>'+
                '<td><input type="hidden" value="'+chiTiet.id+'" ></td>'
				 $('.chiTietTable tbody').append(chiTietRow);
                stt++;
	    	  });		
			var sum = 0;
			$.each(order.danhSachChiTiet, function(i, chiTiet){
				sum += chiTiet.unitPrice * chiTiet.soLuongNhanHang;
			});
			$("#tongTienXacNhan").text("Tổng : "+ sum);
		});
		$("#capNhatTrangThaiModal").modal();
	});
		
    $(document).on('click', '#btnXacNhan', function (event) {
    	event.preventDefault();
    	ajaxPostXacNhanHoanThanh();
		resetData();
    });
    
	// post request xác nhận hoàn thành đơn hàng
	function ajaxPostXacNhanHoanThanh() { 
    	 $.ajax({
     		async:false,
 			type : "POST",
 			contentType : "application/json",
 			url : "http://localhost:8080/shopmall/api/order/update?orderId="+$("#idOrderXacNhan").val()+"&ghiChu="+$("#ghiChuAdmin").val(),
 			enctype: 'multipart/form-data',
			success : function(response) {
				$("#capNhatTrangThaiModal").modal('hide');
				alert("Xác nhận hoàn thành đơn hàng thành công");
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		}); 
    }	
	
    $(document).on('click', '.btnHuy', function (event) {
    	event.preventDefault();
		var orderId = $(this).parent().prev().children().val();
		var confirmation = confirm("Bạn chắc chắn hủy đơn hàng này ?");
		if(confirmation){	 
    	    ajaxPostHuyDon(orderId);
    		resetData();
		}
    });
    
	// post request xác nhận hủy đơn hàng
	function ajaxPostHuyDon(orderId) { 
    	 $.ajax({
     		async:false,
 			type : "POST",
 			contentType : "application/json",
 			url : "http://localhost:8080/shopmall/api/order/cancel?orderId="+orderId,
			success : function(response) {
				alert("Hủy đơn hàng thành công");
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		}); 
    }	
	
	// event khi ẩn modal chi tiết
	$('#chiTietModal,#capNhatTrangThaiModal').on('hidden.bs.modal', function(e) {
		e.preventDefault();
		$("#chiTietForm p").html(""); // reset text thẻ p
		$("#capNhatTrangThaiForm h4").text(""); 
		$("#ghiChuAdmin").text("");
		$('.chiTietTable #soLuongNhanTag').remove();		
		$('.chiTietTable tbody tr').remove();
		$('.chiTietCapNhatTable tbody tr').remove();
	});
});