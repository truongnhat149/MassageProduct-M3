$(document).ready(function() {
	
	// load first when coming page
	ajaxGet(1);	
	
	function ajaxGet(page){		
		var data = { trangThai : $('#trangThai').val(), tuNgay: $('#fromDate').val(), denNgay: $('#toDate').val(), idShipper: $('#idShipper').val()  } 
		$.ajax({
			type: "GET",		
			data: data,
			contentType : "application/json",
			url: "http://localhost:8080/shopmall/api/shipper/order/all" + '?page=' + page,
			success: function(result){
				$.each(result.content, function(i, order){
					// tính giá trị đơn hàng
					var sum = 0;
					if(order.statusOrder == "Hoàn thành" || order.statusOrder == "Chờ duyệt" ){
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
					                  '<td><button class="btn btn-primary btnChiTiet" >Chi Tiết</button>';
					     if(order.statusOrder == "Đang giao"){
					    	 orderRow += ' &nbsp;<button class="btn btn-warning btnCapNhat" >Cập Nhật</button> </td>';
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
		
	$(document).on('click', '#btnDuyetOrder', function (event) {
		event.preventDefault();
		resetData();
	});	
	
    
    // event khi click vào phân trang Đơn hàng
	$(document).on('click', '.pageNumber', function (event){
//		event.preventDefault();
		var page = $(this).text();	
    	$('.orderTable tbody tr').remove();
    	$('.pagination li').remove();
    	ajaxGet(page);	
	});
	
    // event khi click vào nhấn phím vào ô tìm kiếm đơn hàng theo id
	$(document).on('keyup', '#searchById', function (event){
		event.preventDefault();
		var orderId = $('#searchById').val();
		console.log(orderId);
		if(orderId != ''){
    	  $('.orderTable tbody tr').remove();
    	  $('.pagination li').remove();
		  var href = "http://localhost:8080/shopmall/api/shipper/order/"+orderId;
		  $.get(href, function(order) {
				// tính giá trị đơn hàng
			 var sum = 0;
				$.each(order.danhSachChiTiet, function(i, chiTiet){
					sum += chiTiet.unitPrice * chiTiet.soLuong;
			 });
							  
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
			
		     if(order.statusOrder == "Đang giao"){
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
	
    // event khi click vào button Chi tiết đơn
	$(document).on('click', '.btnChiTiet', function (event){
		event.preventDefault();
		
		var orderId = $(this).parent().prev().children().val();	
//		console.log(orderId);
		var href = "http://localhost:8080/shopmall/api/shipper/order/"+orderId;
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
				$("#ghiChu").text("Ghi chú: "+ order.ghiChu);
			}
			
			if(order.nguoiDat != null){
				$("#nguoiDat").text("Người đặt: "+ order.nguoiDat.hoTen);
			}
			
			if(order.shipper != null){
				$("#shipper").text("Shipper: "+ order.shipper.hoTen);
			}
			
			var check = order.statusOrder == "Hoàn thành" || order.statusOrder == "Chờ duyệt" ;
			if(check){
				$('.chiTietTable').find('thead tr').append('<th id="soLuongNhanTag" class="border-0 text-uppercase small font-weight-bold"> SỐ LƯỢNG NHẬN </th>');
			}
			// thêm bảng:
			var sum = 0;
			var stt = 1;
			$.each(order.danhSachChiTiet, function(i, chiTiet){
				console.log(chiTiet.soLuongDat);
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
			$("#tongTienCapNhat").text("Tổng : "+ sum);
		});
		$("#chiTietModal").modal();
	});
		
	// event khi ẩn modal chi tiết
	$('#chiTietModal, #capNhatTrangThaiModal').on('hidden.bs.modal', function(e) {
		e.preventDefault();
		$("#chiTietForm p").text(""); // reset text thẻ p
		$("#capNhatTrangThaiForm h4").text(""); // reset text thẻ p
		$('.chiTietTable tbody tr').remove();
    	$('.chiTietTable #soLuongNhanTag').remove();	
		$('.chiTietCapNhatTable tbody tr').remove();
	});
	
    // event khi click vào button cập nhật đơn
	$(document).on('click', '.btnCapNhat', function (event){
		event.preventDefault();
		var orderId = $(this).parent().prev().children().val();	
		$("#orderId").val(orderId);
		var href = "http://localhost:8080/shopmall/api/shipper/order/"+orderId;
		$.get(href, function(order) {
			// thêm bảng:
			var stt = 1;
			$.each(order.danhSachChiTiet, function(i, chiTiet){
				var chiTietRow = '<tr>' +
				'<td>' + stt + '</td>' +
                '<td>' + chiTiet.sanPham.nameProduct + '</td>' +
                '<td>' + chiTiet.unitPrice + '</td>'+
                '<td>' + chiTiet.soLuongDat + '</td>'+
                '<td><input type="number" class="soLuongNhan" style="width: 40px; text-align: center;" value ="'+chiTiet.soLuongDat+'" min="0" max="'+chiTiet.soLuongDat+'" ></td>'+
                '<td><input type="hidden" value="'+chiTiet.id+'" ></td>'
				 $('.chiTietCapNhatTable tbody').append(chiTietRow);
                stt++;
	    	  });		
			var sum = 0;
			$.each(order.danhSachChiTiet, function(i, chiTiet){
				sum += chiTiet.unitPrice * chiTiet.soLuongDat;
			});
			$("#tongTienCapNhat").text("Tổng : "+ sum);
		});
		$("#capNhatTrangThaiModal").modal();
	});
	
	//
	$(document).on('change', '.soLuongNhan', function (event) {
		  var table = $(".chiTietCapNhatTable tbody");
		  sum  = 0;
     	  table.find('tr').each(function (i) {
		      unitPrice = $(this).find("td:eq(2)").text();
              soLuongCapNhat = $(this).find("td:eq(4) input[type='number']").val();
		      sum += unitPrice * soLuongCapNhat;
		    });
          $("#tongTienCapNhat").text("Tổng : "+ sum);

	});	
	
    $(document).on('click', '#btnXacNhan', function (event) {
    	event.preventDefault();
    	ajaxPostCapNhatTrangThaiDon();
		resetData();
    });
    
	// post request cập nhật trạng thái đơn shipper
	function ajaxPostCapNhatTrangThaiDon() { 
   	  
   	     var listChiTietCapNhat = [] ;
		 var table = $(".chiTietCapNhatTable tbody");
     	 table.find('tr').each(function (i) {
		      var chiTietCapNhat = { idChiTiet : $(this).find("td:eq(5) input[type='hidden']").val(),
		    		                  soLuongNhanHang: $(this).find("td:eq(4) input[type='number']").val() };
		      listChiTietCapNhat.push(chiTietCapNhat);
		 });

    	 
    	 var data = { idorder : $("#orderId").val(),
    			      ghiChuShipper: $("#ghiChuShipper").val(), 
    			      danhSachCapNhatChiTietDon: listChiTietCapNhat } ;
//    	 console.log(data);
    	 $.ajax({
     		async:false,
 			type : "POST",
 			contentType : "application/json",
 			url : "http://localhost:8080/shopmall/api/shipper/order/update",
 			enctype: 'multipart/form-data',
 	        
			data : JSON.stringify(data),
            // dataType : 'json',
			success : function(response) {
				$("#capNhatTrangThaiModal").modal('hide');
				alert("Cập nhật giao đơn hàng thành công");
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		}); 
    }	
	
    // reset table after post, put, filter
    function resetData(){   	
    	var page = $('li.active').children().text();
    	$('.orderTable tbody tr').remove();
    	$('.pagination li').remove();
        ajaxGet(page);
    };
});