	<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="row">

	<div class="col col-lg-8 offset-lg-2">
		<div class="card">
			<div class="card-header card bg-dark text-white">
				<div class="panel-title">Add Auction</div>
			</div>
			
			<div class="card-body">
			<form:form modelAttribute="auctionUpdate">
			<div class="errors">
					<form:errors path="productName" />
				</div>
				
					<div class="input-group">
						<form:input path="productName" type="text" placeholder="Product Name"
							class="form-control"/>
					</div>
					
					<div class="form-group">
					    <label for="exampleFormControlSelect1" class="font-weight-bold">Category</label>
					    <form:select path="category" class="form-control" id="exampleFormControlSelect1">
					    <c:forEach items="${categories}" var="category">
					        <form:option value="${category.name}" itemLabel="${category.name}"/>
					     </c:forEach>
					    </form:select>
					 </div>
					 
					 <label class="font-weight-bold">Start Price</label>
					<div class="input-group">
						<form:input path="startPrice" type="text" placeholder="Start Price"
							class="form-control" />
					</div>
					
					<div class="row">
						<div class="col-2"><strong>End Date:</strong></div>
						 <div class="col-5"><form:input id="datefield" min='2020-05-11' type="date" path="endDate" value="2018-07-22"/></div>
						 <div class="col-5"><form:input type="time" path="endTime" value="20:00"/></div>
					</div>
					
				<div class="errors">
					<form:errors path="description" />
				</div>
					<div class="form-group">
						<strong>Description:</strong>
						<form:textarea path="description" rows="5" cols="30" placeholde="Please enter description here"></form:textarea>
					</div>
					<input class="btn btn-secondary btn btn-outline-dark stretched-link" type="submit" name="submit" value="Add Auction" />
					</form:form>
			</div>
		</div>
	</div>
</div>


<script>var today = new Date();
var dd = today.getDate()+1;
var mm = today.getMonth()+1; //January is 0!
var yyyy = today.getFullYear();
 if(dd<10){
        dd='0'+dd
    } 
    if(mm<10){
        mm='0'+mm
    } 

today = yyyy+'-'+mm+'-'+dd;
document.getElementById("datefield").setAttribute("value", today);
document.getElementById("datefield").setAttribute("min", today);
</script>

<script src='//cdn.tinymce.com/4/tinymce.min.js'></script>
<script>
	tinymce.init({
		selector : 'textarea',
		plugins : "link"
	});
</script>
