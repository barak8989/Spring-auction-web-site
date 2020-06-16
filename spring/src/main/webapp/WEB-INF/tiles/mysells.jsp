<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jwp:pagination url="${url}" page="${page}" size="4"/>

<div hidden>
		<jsp:useBean id="now" class="java.util.Date"/>   
		<fmt:setLocale value="en_US" scope="session"/>
		<fmt:formatDate pattern="yyyy-MM-dd  'at' HH:mm:ss" value="${now}" var = "nowTime" />
		<p>${nowTime}</p>
</div>

<!----------------------------------- Active Sells ----------------------------------------->
<div class="card">
	<div class="card-header card bg-success text-black text-center"><strong>Active Sells</strong></div> 	
	</div>

<div class = "row">
	<c:forEach var="auctionUpdate" items="${page.content}">
	<c:if test="${auctionUpdate.endDate gt nowTime  && auctionUpdate.seller.id == id }"> 
		 
		<c:url var="bidLink" value="/viewauction?id=${auctionUpdate.id}" />
			<div class="col-sm">
						<div class="card">
						<div class="card-header card bg-light text-black">
						<p><strong>Product Name : </strong>${auctionUpdate.productName}</p>
						</div>
						<div class="card-body">
							<div>
							<p><strong>Number of Bids</strong> : ${auctionUpdate.numOfBids}</p>
							<p><strong>Current bid :</strong>${auctionUpdate.bid}</p>
							<fmt:setLocale value="en_US" scope="session"/>
							<p><strong>End Date</strong> :<fmt:formatDate pattern="EEEE d MMMM y 'at' H:mm" value="${auctionUpdate.endDate}" /></p>
							<div class="edit-links float-right">
							<a href="${bidLink}"><button type="button" class="btn btn-success">View auction</button></a>
							</div>
							</div>
						</div>
					</div>
			</div>
			
		</c:if> 
		</c:forEach> 
</div>
	
	<br>
<!----------------------------------- Finished Sells ----------------------------------------->
	<div class="card">
	<div class="card-header card bg-info text-black text-center"><strong>Finished Sells</strong></div> 	
	</div>
	
	<div class = "row">
	<c:forEach var="auctionUpdate" items="${page.content}">
	<c:if test="${auctionUpdate.endDate lt nowTime  }"> 
	<c:if test="${auctionUpdate.seller.id == id }"> 
	<c:url var="bidLink" value="/viewauction?id=${auctionUpdate.id}" />
		<div class="col-sm">
					<div class="card">
					<div class="card-header card bg-light text-black">
					<p><strong>Product Name : </strong>${auctionUpdate.productName}</p>
					</div>
					<div class="card-body">
						<div>
						<p><strong>Number of bids :</strong>${auctionUpdate.numOfBids}</p>
						<p><strong>Current bid :</strong>${auctionUpdate.bid}</p>
						<fmt:setLocale value="en_US" scope="session"/>
						<p><strong>End Date</strong> :<fmt:formatDate pattern="EEEE d MMMM y 'at' H:mm" value="${auctionUpdate.endDate}" /></p>
						<p><strong>Buyer :</strong>${auctionUpdate.buyerEmail}</p>
						</div>
					</div>
				</div>
		</div>
		</c:if> 
		</c:if> 
		</c:forEach> 
</div>

