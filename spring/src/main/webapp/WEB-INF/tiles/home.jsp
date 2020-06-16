<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div hidden>
		<jsp:useBean id="now" class="java.util.Date"/>   
		<fmt:setLocale value="en_US" scope="session"/>
		<fmt:formatDate pattern="yyyy-MM-dd  'at' HH:mm:ss" value="${now}" var = "nowTime" />
		<p>${nowTime}</p>
</div>

<div class="container">
	<jwp:pagination url="${url}" page="${page}" size="4"/>
		
	
	<div class="card">
	<div class="card-header card bg-light text-black text-center"><strong>Electronics</strong></div> 	
	</div>
	
	<div class = "row">
	<c:forEach var="auctionUpdate" items="${page.content}">
	<c:if test="${auctionUpdate.endDate gt nowTime  && auctionUpdate.category == 'Electronics'}">
	<c:url var="bidLink" value="/viewauction?id=${auctionUpdate.id}" />
		<div class="col-sm">
					<div class="card">
					<div class="card-header card bg-light text-black">
					<p><strong>Product Name : </strong>${auctionUpdate.productName}</p>
					</div>
					<div class="card-body">
						<div>
						<p><strong>Current bid :</strong>${auctionUpdate.bid} &#8362</p>
						<fmt:setLocale value="en_US" scope="session"/>
						<p><strong>End Date</strong> :<fmt:formatDate pattern="EEEE d MMMM y 'at' H:mm" value="${auctionUpdate.endDate}" /></p>
						</div>
						<div class="edit-links float-right">
						<a href="${bidLink}"><button type="button" class="btn btn-success blinking">Bid Now !</button></a>
						</div>
					</div>
				</div>
		</div>
		</c:if>
		</c:forEach> 
	</div>
	
	<br>
	
	<div class="card">
	<div class="card-header card bg-warning  text-white text-center"><strong>Fashion</strong></div> 	
	</div>
	<div class = "row">
	<c:forEach var="auctionUpdate" items="${page.content}">
	<c:if test="${auctionUpdate.endDate gt nowTime  && auctionUpdate.category == 'Fashion'}">
	<c:url var="bidLink" value="/viewauction?id=${auctionUpdate.id}" />
		<div class="col-sm">
					<div class="card">
					<div class="card-header card bg-warning  text-white">
					<p><strong>Product Name : </strong>${auctionUpdate.productName}</p>
					</div>
					<div class="card-body">
						<div>
						<p><strong>Current bid : </strong>${auctionUpdate.bid} &#8362</p>
						<fmt:setLocale value="en_US" scope="session"/>
						<p><strong>End Date : </strong> <fmt:formatDate pattern="EEEE d MMMM y 'at' H:mm" value="${auctionUpdate.endDate}" /></p>
						</div>
						<div class="edit-links float-right">
						<a href="${bidLink}"><button type="button" class="btn btn-success  blinking">Bid Now !</button></a>
						</div>
					</div>
				</div>
		</div>
		</c:if>
		</c:forEach> 
	</div>
	
	<br>
	
	<div class="card">
	<div class="card-header card text-white text-center" style="background-color: purple;"><strong>Health & Beauty</strong></div> 	
	</div>
	<div class = "row">
	<c:forEach var="auctionUpdate" items="${page.content}">
	<c:if test="${auctionUpdate.endDate gt nowTime  && auctionUpdate.category == 'Health & Beauty'}">
	<c:url var="bidLink" value="/viewauction?id=${auctionUpdate.id}" />
		<div class="col-sm">
					<div class="card">
					<div class="card-header card text-white" style="background-color: purple;">
					<p><strong>Product Name : </strong>${auctionUpdate.productName}</p>
					</div>
					<div class="card-body">
						<div>
						<p><strong>Current bid : </strong>${auctionUpdate.bid} &#8362</p>
						<fmt:setLocale value="en_US" scope="session"/>
						<p><strong>End Date : </strong> <fmt:formatDate pattern="EEEE d MMMM y 'at' H:mm" value="${auctionUpdate.endDate}" /></p>
						</div>
						<div class="edit-links float-right">
						<a href="${bidLink}"><button type="button" class="btn btn-success blinking">Bid Now !</button></a>
						</div>
					</div>
				</div>
		</div>
		</c:if>
		</c:forEach> 
	</div>
	
</div>