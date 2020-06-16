<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Home</title>
<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>

<!-- Bootstrap -->
<link rel="stylesheet" type="text/css" href="${contextRoot}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${contextRoot}/css/style.css">
<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />

  	
  
</head>
<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <a class="navbar-brand" href="${contextRoot}/"><i class="fas fa-gavel"></i> 4Sale</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
    
      <li class="nav-item">
        <a class="nav-link" href="${contextRoot}/">Home <span class="sr-only"></span></a>
      </li>
    </ul>
    
	<ul class="navbar-nav justify-content-end">
		<sec:authorize access="!isAuthenticated()">
			<li class="nav-item"><a class="nav-link" href="${contextRoot}/login">Login</a></li>
			<li class="nav-item"><a class="nav-link" href="${contextRoot}/register">Sign-in</a></li>
		</sec:authorize>
		
		<sec:authorize access="isAuthenticated()">
		<li class="nav-item dropdown">
	      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
	      <sec:authentication property="principal.firstname"/>
	        <i class="fas fa-user"></i>
	        </a>
	      <div class="dropdown-menu">
	        <a class="dropdown-item" href="${contextRoot}/mysells">My Sells</a>
	        <a class="dropdown-item" href="${contextRoot}/addauction">Add Auction</a>
	      </div>
	    </li>
	    	<li class="nav-item"><a class="nav-link" href="javascript:$('#logoutForm').submit();">Logout</a></li>
	    </sec:authorize>
	</ul>
    <!-- Search  -->
  <!--   <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form> -->
    
  </div>
</nav>

<c:url var="logoutLink" value="/logout" />
	<form id="logoutForm" method="post" action="${logoutLink}">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	
	<div><tiles:insertAttribute name="content"/></div>
		
	
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="${contextRoot}/js/bootstrap.min.js"></script>
	<script src="${contextRoot}/js/scripts.js"></script>
  	<script src="https://kit.fontawesome.com/d3da524840.js" crossorigin="anonymous"></script>
  	<script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>

  	
  


  
</body>
</html>