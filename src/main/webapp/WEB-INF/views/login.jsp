<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>loginPage</title>
<link href="../../assets/css/bootstrap.min.css" rel="stylesheet">
<link href="../../assets/css/font-awesome.min.css" rel="stylesheet">
<style>
body {
    background-color:powderblue;
}
</style>
</head>
<body>
<%--<c:set var="error" scope="request" value="${error}"/>--%>
<%--<c:out value="${error}"/>--%>
	<div class="container" align="center" style="margin-top: 10rem">
		<div class=" col-4 justify-content-center">
			<div class="card">
				<div class="card-header" style="color:darkblue">
					<h3>Login Form</h3>
				</div>
				<div class="card-body">
					<%--<c:if test="${message != null}">
						<h6 align="center" style="color:green">${message}</h6>
					</c:if>--%>
<%--					<h6 style="color:green">${message}</h6>--%>
					<h6 style="color:green"><c:out value="${reset}"/></h6>
					<h6 style="color:red"><c:out value="${errormsg}"/></h6>
					<h6 style="color:green"><c:out value="${register}"/></h6>
					<form action="/session" method="post">
						<div class="input-group form-group">						
							<input type="text" name="name" class="form-control"
								placeholder="Username" required>
						</div>
						<div class="input-group form-group">
							<input type="password" name="password" class="form-control" 
								placeholder="Password" required>
						  </div>						  
						<div class="form-group">
							<button type="submit"
									class="btn btn-primary">Login</button>
							</div>			
						<div class="form-group"><a href="/email/resetPassword" style="margin-left:1px;color:darkgreen">Forget Password ?</a>
						
							<a href="/user/register" style="margin-left:60px;color:darkgreen">New User?</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>    
</body>
</html>