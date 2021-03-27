<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>User Page</title>
	<link rel="stylesheet" href="../../assets/css/bootstrap.min.css" >
	<link rel="stylesheet" href="../../assets/css/font-awesome.min.css" >
	<style>
		body {
			margin:0;
			padding:0;
			background-image:url("../../assets/images/library1.jpg");
			background-size: cover;
		}
	</style>
</head>
<body>
<nav class="navbar navbar-light bg-dark">
	<form class="form-inline">
		<div>
			<h6>
				<a class="nav-link"
				   href="/book/userViewBook" style="color:white" title="View Books"><i class="fa fa-book" aria-hidden="true" style=font-size:20px;color:darkorange></i></a>
			</h6>
		</div>
		<div>
			<h6>
				<a href="/librarian/viewNotification" class="nav-link" style="color: white" title="view Notification"><i class="fa fa-bell" aria-hidden="true" style=font-size:20px;color:greenyellow></i></a>
			</h6>
		</div>
	</form>
	<form class="form-inline">
		<div style="color: lightblue">
			<h5><c:out value="${sessionScope.currentUser.name}"/></h5>
		</div>
		<font color="red"> ${bookError} </font>
	</form>
	<form class="form-inline my-2 my-lg-0">
		<div>
			<h6>
				<a href="/book/request" class="nav-link" style="color:white" title="Request"><i class="fa fa-commenting"  aria-hidden="true" style="font-size:20px;color:blue;"></i></a>
			</h6>
		</div>
	<div>
		<h6>
			<a href="/logout" class="nav-link" style="color:white" title="Logout"><i class="fa fa-sign-out" aria-hidden="true" style="font-size:20px;color:#ff0000;"></i></a>
		</h6>
	</div>
	</form>
</nav>
<br>
<c:if test="${view_id ==3 }">
	<div class="container" align="center"
		 style="margin-top: 2%; margin-left: 8%">
		<h5 style="color:lightgray">Books</h5>
		<c:if test="${success!= null}">
			<h6 align="center" style="color:green">${success}</h6>
		</c:if>
		<c:if test="${request!= null}">
			<h6 align="center" style="color:green">${request}</h6>
		</c:if>
		<c:if test="${error!= null}">
			<h6 align="center" style="color:red">${error}</h6>
		</c:if>
		<form action="/book/" method="get">
			<table class="table table-hover" style="background-color:dark">
				<thead class="thead-dark">
				<tr>
					<th>S.No</th>
					<th>Name</th>
					<th>Author Name</th>
					<th>Publisher Name</th>
					<th>Contact Number</th>
					<th>Download</th>
				</tr>
				</thead>
				<tbody style="background-color:lightgray">
				<c:forEach var="books" items="${book}" varStatus="counter">
					<tr>
						<td><c:out value="${counter.count }" /></td>
						<td scope="row"><c:out value="${books.name}" /></td>
						<td><c:out value="${books.author_name}" /></td>
						<td><c:out value="${books.publisher_name}" /></td>
						<td><c:out value="${books.contact_number}" /></td>
						<td>
							<a href="/book/download/${books.id}" title="Download"><i class="fa fa-download" aria-hidden="true" style="color:green;"></i></a></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
</c:if>
<c:if test="${view_id ==7 }">
	<div class="container" align="center"
		 style="margin-top: 2%; margin-left: 8%">
		<h5 style="color:lightgray">Notification</h5>

		<form action=" " method="get">
			<table class="table table-hover" style="background-color:dark">
				<thead class="thead-dark">
				<tr>
					<th>S.No</th>
					<th>User Name</th>
					<th>Subject</th>
					<th>Message</th>
				</tr>
				</thead>
				<tbody style="background-color:lightgray">
				<c:forEach var="notify" items="${notification}" varStatus="counter">
					<tr>
						<td><c:out value="${counter.count }" /></td>
						<td scope="row"><c:out value="${notify.username}" /></td>
						<td><c:out value="${notify.subject}" /></td>
						<td><c:out value="${notify.message}" /></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
</c:if>
</body>
</html>