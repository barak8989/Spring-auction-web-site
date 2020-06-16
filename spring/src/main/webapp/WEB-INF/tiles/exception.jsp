<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">

	<div class="col-md-8 offset-md-2 text-center">

		<div class="message">
			<c:out value="${message}" />
		</div>
		
		<!-- 
		
		Exception: <c:out value="${exception}" />
		Failed URL: <c:out value="${url}" />
		Exception message: <c:out value="${exception.message}" />
		
		<c:forEach var="line" items="${exception.stackTrace}">
			<c:out value="${line}" />
		</c:forEach>
		
		 -->

	</div>

</div>