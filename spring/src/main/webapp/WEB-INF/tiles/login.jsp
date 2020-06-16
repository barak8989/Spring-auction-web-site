<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="loginUrl" value="/login" />

<div class="row">
	<div class="col-md-8 offset-md-2">
		<c:if test="${param.error != null}">
			<div class="login-error">Incorrect username or password.</div>
		</c:if>

		<div class="card">
			<div class="card-header card bg-dark text-white">
				<div class="card-title">User Log In</div>
			</div>


			<div class="card-body">
				<form method="post" action="${loginUrl}" class="login-form">

					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
						
					<div class="input-group">
						<input type="text" name="username" placeholder="Username"
							class="form-control" />
					</div>

					<div class="input-group">
						<input type="password" name="password" placeholder="Password"
							class="form-control" />
					</div>
					<div class="float-right">
						<button type="submit" class="btn btn-secondary btn btn-outline-dark">Log In</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>