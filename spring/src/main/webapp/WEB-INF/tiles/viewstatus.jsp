<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp" %>
     
<div class="row status">
	<div class="col-md-8 offset-md-2">
	
	<jwp:pagination url="${url}" page="${page}" size="5"/>
		<c:forEach var="statusUpdate" items="${page.content}">
		
		<c:url var="editLink" value="/editstatus?id=${statusUpdate.id}" />
		<c:url var="deleteLink" value="/deletestatus?id=${statusUpdate.id}" />
			
			<div class="card">
				<div class="card-header card bg-dark text-white">
					Status update added on
					<fmt:setLocale value="en_US" scope="session"/>
					<fmt:formatDate pattern="EEEE d MMMM y 'at' H:mm:s" value="${statusUpdate.added}" />
				</div>
				<div class="card-body">
					<div>${statusUpdate.text}</div>

					<div class="edit-links float-right">
					<a href="${editLink}"><button type="button" class="btn btn-info">Edit</button></a>
					<a onclick="return confirm('Really delete this status update?');" 
						href="${deleteLink}"><button type="button" class="btn btn-danger">Delete</button></a>
						</div>
					
				</div>
			</div>
		</c:forEach>
	</div>
</div>