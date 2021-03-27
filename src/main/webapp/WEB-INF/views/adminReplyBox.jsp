<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Admin Reply Box</title>
    <link href="../../assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../assets/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<div class="container" style="margin-top: 10%; margin-left: 8%">
    <div class="row justify-content-center">
        <div class="col-md-5">
            <div class="card">
                <div class="card-header" align="center">
                    <h3>Reply</h3>
                </div>
                <div class="card-body">
                    <form class="form-horizontal" action="/book/adminReplyAdd" method="post">
                        <input type="hidden" class="form-control" name="username" value="<c:out value="${sessionScope.currentUser.name}"/>" id="username"  />
                        <div class="form-group">
                            <label for="subject" class="cols-sm-2 control-label">Subject</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span> <input type="text"
                                                                                   class="form-control" name="subject" id="subject"
                                                                                   placeholder="Subject" required />
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="message" class="cols-sm-2 control-label">message
                            </label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span> <textarea rows="4" cols="55" placeholder="Type message.." name="message" required></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="form-group row=1" align="center">
                            <button type="submit"
                                    class="btn btn-primary">Send</button>
                            <div class="form-group" align="right">
                                <a href="/user/admin" title="Back"><i class="fa fa-arrow-circle-left" aria-hidden="true" style="font-size:25px"></i></a>
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