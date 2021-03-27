<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Page</title>
	<link rel="stylesheet" href="../../assets/css/bootstrap.min.css" >
	<link rel="stylesheet" href="../../assets/css/font-awesome.min.css" >
<style>
body {
  margin:0;
  padding:0;
  background-image:url("../../assets/images/admin2.jpg");
  background-size: cover;
}
</style>
</head>
<body>
	<nav class="navbar navbar-light bg-dark">
		<form class="form-inline">
			<div >
				<h6>
					<a class="nav-link" href="/user/viewUser" style="color:white" title="View users"><i class="fa fa-users" aria-hidden="true" style="font-size:20px;color:blue"></i></a>
				</h6>
			</div>
			<div>
				<h6>
					<a class="nav-link" href="/book/viewBook" style="color:white" title="View books"><i class="fa fa-book" aria-hidden="true" style="font-size:20px;color:darkorange"></i></a>
				</h6>
			</div>
		</form>
		<form class="form-inline">
		<div align="center" style="color: lightblue">
		<h4>Admin</h4>
	   </div>
	   </form>
		<form class="form-inline my-2 my-lg-0">
			<c:choose>
				<c:when test="${view_id==1}">
					<h6>
						<a href="/user/adduser" class="nav-link" style="color: white" title="Add users"><i class="fa fa-user-plus" aria-hidden="true" style="font-size:20px;color:lightgreen"></i></a>
					</h6>
				</c:when>
				<c:when test="${view_id==2}">
					<div>
					<h6>
						<a href="/book/addbook" class="nav-link" style="color: white" title="Add books"><i class="fa fa-plus-circle" aria-hidden="true" style=font-size:20px;color:lightgreen></i></a>
					</h6>
					</div>
					<div>
						<h6>
							<a href="/book/viewRequest" class="nav-link" style="color: white" title="View User Request"><i class="fa fa-commenting" aria-hidden="true" style=font-size:20px;color:blue></i></a>
						</h6>
					</div>
				</c:when>
			</c:choose>
			<div>
				<h6>
					<a href="/librarian/viewLibrarianRequest" class="nav-link" style="color: white" title="View Librarian Request"><i class="fa fa-commenting" aria-hidden="true" style=font-size:20px;color:blue></i></a>
				</h6>
			</div>
			<div>
				<h6>
					<a class="nav-link" href="/book/adminReplyBox" style="color:white" title="Reply"><i class="fa fa-reply" aria-hidden="true" style="font-size:20px;color:darkorange"></i></a>
				</h6>
			</div>
			<div>
			<h6>
				<a href="/logout" class="nav-link" style="color: white" title="Logout"><i class="fa fa-sign-out" aria-hidden="true" style="font-size:20px;color:red;"></i></a>
			</h6>
			</div>
		</form>
	</nav><br>
	<c:if test="${success_reply != null}">
		<h6 align="center" style="color:green">${success_reply}</h6>
	</c:if>
	<c:choose>
		<c:when test="${view_id== 1}">
			<div class="container" align="center"
				style="margin-top: 2%; margin-left: 8%">
				<form:form action="/user/viewUser" method="get">
					<h5 style="color: darkblue">User Details</h5>

					<c:if test="${deleteMessage != null}">
						<h6 align="center" style="color:red">${deleteMessage}</h6>
					</c:if>
					<c:if test="${success != null}">
						<h6 align="center" style="color:green">${success}</h6>
					</c:if>
					<table class="table table-hover" style="background-color:lightgray" >
						<thead class="thead-dark">
							<tr>
								<th>S.No</th>
								<th>User Name</th>
								<th>Name</th>
								<th>Email Id</th>
								<th>Address</th>
								<th>Contact Number</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody >
							<c:forEach items="${users}" var="user" varStatus="counter">
								<tr>
									<td><c:out value="${counter.count }" /></td>
									<td scope="row"><c:out value="${user.name}" /></td>
									<td scope="row"><c:out value="${user.full_name}" /></td>
									<td><c:out value="${user.email_id}" /></td>
									<td><c:out value="${user.address}" /></td>
									<td><c:out value="${user.contact_number}" /></td>
									<td>
										<a class="btn btn-sm btn-outline-warning" href="/user/updateUser/${user.id}" title="Edit"><i class="fa fa-pencil-square" aria-hidden="true" style="color:blue"></i></a>
										&nbsp;&nbsp;&nbsp;&nbsp; <a
											class="btn btn-sm btn-outline-danger" href="/user/deleteUser/${user.id}" title="Delete"><i class="fa fa-trash" aria-hidden="true" style="color:red"></i></a>
									</td>
								</tr>
								<form type="hidden" name="id" value = ${user.id }>
							</c:forEach>
						</tbody>
					</table>
				</form:form>
			</div>
		</c:when>
		<c:when test="${view_id==2}">
			<div class="container" align="center"
				style="margin-top: 2%; margin-left: 8%">
				<form:form  action="/book/viewBook" method="get">
					<h5 style="color: darkblue">Book Details</h5>
					<c:if test="${deleteMessage != null}">
						<h6 align="center" style="color:red">${deleteMessage}</h6>
					</c:if>
					<c:if test="${success != null}">
						<h6 align="center" style="color:green">${success}</h6>
					</c:if>
					<table class="table table-hover" style="background-color:lightgray">
						<thead class="thead-dark">
							<tr>
								<th>S.No</th>
								<th>Name</th>
								<th>Author Name</th>
								<th>Publisher Name</th>
								<th>Contact Number</th>
								<th>Download Count</th>
								<th>Action</th>
<%--                                <th>Download</th>--%>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="book" items="${books}" varStatus="counter">
								<tr>
									<td><c:out value="${counter.count }" /></td>
									<td scope="row"><c:out value="${book.name}" /></td>
									<td><c:out value="${book.author_name}" /></td>
									<td><c:out value="${book.publisher_name}" /></td>
									<td><c:out value="${book.contact_number}" /></td>
									<td align="center"><c:out value="${book.download_count}" /></td>
									<td><a
										href="/book/updateBook/${book.id}" title="Edit"><i class="fa fa-pencil-square" aria-hidden="true" style="color:blue"></i></a>
										&nbsp;&nbsp;&nbsp;&nbsp; <a
										href="/book/deleteBook/${book.id}" title="Delete"><i class="fa fa-trash" aria-hidden="true" style="color:red"></i></a>
									</td>
<%--									 <td>--%>
<%--								 <a href="/book/download/${books.id}">Download</a></td>--%>
								</tr>
								<form type="hidden" name="id" value=${book.id }>
							</c:forEach>
						</tbody>
					</table>
				</form:form>
			</div>
		</c:when>
		<c:when test="${view_id==4}">
			<div class="container" align="center"
				 style="margin-top: 2%; margin-left: 8%">
				<form:form action=" " method="get">
					<h5 style="color: darkblue">User Request</h5>
					<table class="table table-hover" style="background-color:lightgray" >
						<thead class="thead-dark">
						<tr>
							<th>S.No</th>
							<th>User Name</th>
							<th>Book Name</th>
							<th>Author Name</th>
						</tr>
						</thead>
						<tbody >
						<c:forEach items="${request}" var="requests" varStatus="counter">
						<tr>
							<td><c:out value="${counter.count }" /></td>
							<td scope="row"><c:out value="${requests.username}" /></td>
							<td><c:out value="${requests.book_name}" /></td>
							<td><c:out value="${requests.author_name}" /></td>
						</tr>
							</c:forEach>
						</tbody>
					</table>
				</form:form>
			</div>
		</c:when>
		<c:when test="${view_id==5}">
			<div class="container" align="center"
				 style="margin-top: 2%; margin-left: 8%">
				<form:form action=" " method="get">
					<h5 style="color: darkblue">Librarian Request</h5>
					<table class="table table-hover" style="background-color:lightgray" >
						<thead class="thead-dark">
						<tr>
							<th>S.No</th>
							<th>User Name</th>
							<th>Subject</th>
							<th>Message</th>
						</tr>
						</thead>
						<tbody >
						<c:forEach items="${request}" var="requests" varStatus="counter">
							<tr>
								<td><c:out value="${counter.count }" /></td>
								<td scope="row"><c:out value="${requests.username}" /></td>
								<td><c:out value="${requests.subject}" /></td>
								<td><c:out value="${requests.message}" /></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</form:form>
			</div>
		</c:when>
	</c:choose>
</body>
</html>
