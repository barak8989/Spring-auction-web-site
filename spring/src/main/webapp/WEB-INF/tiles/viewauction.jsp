<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>



<div class="row status">
	<div class="col-md-8 offset-md-2">
		<div class="card">
				<div class="card-header card bg-dark text-white">
					Auction <c:out value="${auctionUpdate.productName}"/> added on
					<fmt:setLocale value="en_US" scope="session"/>
					<fmt:formatDate pattern="EEEE d MMMM y 'at' H:mm" value="${auctionUpdate.startDate}" />
				</div>
				<div class="card-body">
				<form:form modelAttribute="auctionUpdate">
				<form:input type="hidden" path="id" />
				<form:input type="hidden" path="productName" />	
				<form:input type="hidden" path="category" />
				<form:input type="hidden" path="startDate" />
				<form:input type="hidden" path="endDate" />
				<form:input type="hidden" path="startPrice" />
				<form:input type="hidden" path="description" />
					<div>
					<p><strong>Product Name :</strong>${auctionUpdate.productName}</p>
					<p><strong>Category</strong> : ${auctionUpdate.category}</p>
					<p><strong>Start Date</strong>:<fmt:formatDate pattern="EEEE d MMMM y 'at' H:mm" value="${auctionUpdate.startDate}" /></p>
					<p><strong>End Date</strong> :<fmt:formatDate pattern="EEEE d MMMM y 'at' H:mm" value="${auctionUpdate.endDate}" /></p>
					<p><strong>Start Price</strong> : ${auctionUpdate.startPrice}</p>
					<p><strong>Number of Bids</strong> : ${auctionUpdate.numOfBids}</p>
					<p><strong>Current Bid</strong> : ${auctionUpdate.bid}</p>
					<p><strong>Next Bid</strong> :
					<form:input path="bid" type="text" value="${auctionUpdate.nextBid}" readonly="true" />
					<c:if test="${auctionUpdate.buyer == authId }">
					<p>You can't bid, you are the last bidder!</p>
					</c:if>	
					<c:if test="${auctionUpdate.seller.id == authId }">
					<p>You are the seller,you can't bid on this auction sell!</p>
					</c:if>	
					<c:if test="${auctionUpdate.seller.id != authId }"> 
						<c:if test="${auctionUpdate.buyer != authId  }"> 
							<input class="btn btn-secondary btn btn-outline-dark stretched-link" type="submit" name="submit" value="Bid" />
						</c:if>
					</c:if>
					</p>
					<p><strong>Product Description</strong> : ${auctionUpdate.description}</p>
					</div>	
				</form:form>				
				</div>
			</div>
	</div> 
</div>