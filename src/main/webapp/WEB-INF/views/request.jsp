<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>User Request</title>
    <link href="../../assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../assets/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<div class="container" style="margin-top: 10%; margin-left: 8%">
    <div class="row justify-content-center">
        <div class="col-md-5">
            <div class="card">
                <div class="card-header" align="center">
                    <h3>Request Form</h3>
                </div>
                <div class="card-body">
                    <font color="#f21c04">${error}</font>
                    <form class="form-horizontal" action="/book/requestAdd" method="post">
                        <input type="hidden" class="form-control" name="username" value="<c:out value="${sessionScope.currentUser.name}"/>" id="username"  />
                        <div class="form-group">
                            <label for="book_name" class="cols-sm-2 control-label">Book Name</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span> <input type="text"
                                                                                   class="form-control" name="book_name" id="book_name"
                                                                                   placeholder="Enter Book Name" required />
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="author_name" class="cols-sm-2 control-label">Author Name
                                </label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span> <input type="text"
                                                                                   class="form-control" name="author_name" id="author_name"
                                                                                   placeholder="Enter Author Name" required />
                                </div>
                            </div>
                        </div>
                        <div class="form-group row=1" align="center">
                            <button type="submit"
                                    class="btn btn-primary">Submit</button>
                            <div class="form-group" align="right">
                                <a href="/book/userViewBook" title="Back"><i class="fa fa-arrow-circle-left" aria-hidden="true" style="font-size:25px"></i></a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>