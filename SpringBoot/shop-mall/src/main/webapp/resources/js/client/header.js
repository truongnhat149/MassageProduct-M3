$(document).ready(function(){
	ajaxGet2();
	function ajaxGet2(){
		$.ajax({
			type: "GET",		
			url: "http://localhost:8080/shopmall/api/category/allForReal",
			success: function(result){
				$.each(result, function(i, danhmuc){
					var content = '<li><a href="/shopmall/store?brand='+danhmuc.nameCategory+'"><span style=" font-size: 16px; font-weight: 900; ">'+danhmuc.nameCategory+'</span></a></li>';
					var content2 ='<li><a  style="padding-right: 100px" href="<%=request.getContextPath()%>/store?brand='+danhmuc.nameCategory+'">'+danhmuc.nameCategory+'</a></li>'
					$('#danhmuc').append(content);		
					$('#danhmuc2').append(content2);	
				})					
			}	
		});
			
	}
})
