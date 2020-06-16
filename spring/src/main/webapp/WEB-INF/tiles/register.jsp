<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="loginUrl" value="/login" />

<div class="row">

	<div class="col-md-8 offset-md-2">
		<div class="login-error">
			<form:errors path="user.*"/>
		</div>
		<div class="card">

			<div class="card-header card bg-dark text-white">
				<div class="panel-title">Create an Account</div>
			</div>


			<div class="card-body">
				<form:form method="post" modelAttribute="user" class="login-form">

					<div class="input-group">
						<form:input type="email" path="email" placeholder="Email"
							class="form-control" />
					</div>
					
					<div class="input-group">
						<form:input type="text" path="firstname" placeholder="First Name"
							class="form-control" />
					</div>
					
					<div class="input-group">
						<form:input type="text" path="surename" placeholder="Sure name"
							class="form-control" />
					</div>

					<div class="input-group">
						<form:input type="password" path="plainPassword" placeholder="Password" 
							class="form-control" />
					</div>

					<div class="float-right">
						<button type="submit" class="btn btn-secondary btn btn-outline-dark">Register</button>
					</div>

				</form:form>
			</div>
		</div>

	</div>
</div>