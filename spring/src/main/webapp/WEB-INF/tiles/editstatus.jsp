<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="row">
	<div class="col-md-8 offset-md-2">
		<div class="card">
			<div class="card-header card bg-primary text-white">
				Edit Status
			</div>
			<div class="card-body">
				<form:form modelAttribute="statusUpdate">
				
				<form:input type="hidden" path="id" />
				<form:input type="hidden" path="added" />
				
					<div class="errors">
						<form:errors path="text"/>
					</div>
					<div class="form-group		">
						<form:textarea path="text" rows="5" cols="30" ></form:textarea>
					</div>
					<input class="btn btn-primary stretched-link" type="submit" name="submit" value="Save Status" />				
				</form:form>
			</div>
		</div>
	</div>
</div> 

<script src='//cdn.tinymce.com/4/tinymce.min.js'></script>
<script>
	tinymce.init({
		selector : 'textarea',
		plugins : "link"
	});
</script>