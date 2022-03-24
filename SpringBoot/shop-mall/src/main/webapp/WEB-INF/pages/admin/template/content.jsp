<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="col-md-9 animated bounce">
	<h3 class="page-header">Danh sách công việc</h3>
	<c:if test = "${listCongViec.soOrderMoi > 0}">
         <p>Bạn có: <a href='<c:url value="/admin/order" />'> ${listCongViec.soOrderMoi} đơn hàng mới</a><p>
    </c:if>
    
    <c:if test = "${listCongViec.soOrderChoDuyet > 0}">
         <p>Bạn có: <a href='<c:url value="/admin/order" />'> ${listCongViec.soOrderChoDuyet} đơn hàng chờ duyệt</a><p>
    </c:if>

	<c:if test = "${listCongViec.soContactMoi > 0}">
         <p>Bạn có: <a href='<c:url value="/admin/contact" />'> ${listCongViec.soContactMoi} liên hệ mới</a><p>
    </c:if>
    
</div>
</div>