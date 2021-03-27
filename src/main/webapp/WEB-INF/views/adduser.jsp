<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Add User</title>
    <link rel="stylesheet" href="../../assets/css/bootstrap.min.css" >
    <link rel="stylesheet" href="../../assets/css/font-awesome.min.css" >
</head>
<body>
<div class="container mt-4 ">
    <div class="row justify-content-center">
        <div class="col-md-5">
            <div class="card">
                <div class="card-header" align="center">
                    <c:choose>
                        <c:when test="${users.id!= null}">
                            <h3>Edit User</h3>
                        </c:when>
                        <c:otherwise>
                            <h3>Add User</h3>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="card-body">
                    <form class="form-horizontal" action="/user/addOrUpdateUser" method="post">
                        <div class="form-group">
                            <label for="full_name" class="cols-sm-2 control-label">Name</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span> <input type="text"
                                                                                   class="form-control" name="full_name"
                                                                                   id="full_name"
                                                                                   placeholder="Enter Your Name" required
                                                                                   value="${users.full_name }"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email_id" class="cols-sm-2 control-label">Email
                                Id</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span> <input type="email"
                                                                                   class="form-control" name="email_id"
                                                                                   id="email_id"
                                                                                   placeholder="Enter Email Id" required
                                                                                   value="${users.email_id }"/>
                                </div>
                            </div>
                        </div>
                        <h6 align="center" style="color:red"><c:out value="${MailError}"/></h6>
                        <div class="form-group">
                            <label for="address" class="cols-sm-2 control-label">Address</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span> <input type="text"
                                                                                   class="form-control" name="address"
                                                                                   id="address"
                                                                                   placeholder="Enter Address" required
                                                                                   value="${users.address }"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="contact_number" class="cols-sm-2 control-label">Contact
                                Number</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span> <input type="number"
                                                                                   min="0" class="form-control"
                                                                                   name="contact_number"
                                                                                   id="contact_number"
                                                                                   placeholder="Contact Number" required
                                                                                   value="${users.contact_number }"/>
                                </div>
                            </div>
                        </div>
                        <h6 align="center" style="color:red"><c:out value="${ContactNumberError}"/></h6>
                        <div class="form-group">
                            <label for="name" class="cols-sm-2 control-label">User Name</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span> <input type="text"
                                                                                   class="form-control" name="name"
                                                                                   id="name"
                                                                                   placeholder="Enter User Name" required
                                                                                   value="${users.name }"/>
                                </div>
                            </div>
                        </div>
                        <h6 align="center" style="color:red"><c:out value="${NameError}"/></h6>
                        <c:if test="${users.id == null}">
                            <div class="form-group">
                                <label for="password" class="cols-sm-2 control-label">Password</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"></span> <input
                                            type="password" class="form-control" name="password"
                                            id="password" placeholder="Enter Password" required />
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="confirm_password" class="cols-sm-2 control-label">Confirm Password</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"></span> <input
                                            type="text" class="form-control" name="confirm_password"
                                            id="confirm_password" placeholder="Enter Confirm Password" required />
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <h6 align="center" style="color:red"><c:out value="${PasswordError}"/></h6>
                        <c:if test="${users.id!= null}">
                            <input type="hidden"
                                   class="form-control" name="password" id="password"
                                   placeholder="Enter Password" required
                                   value="${users.password }" />
                            <input type="hidden"
                                   class="form-control" name="password" id="password"
                                   placeholder="Enter Password" required
                                   value="${users.confirm_password }" />
                        </c:if>
                        <c:if test="${users.id == null}">
                            <div class="form-group " align="center" class="row">
                                <input type="submit" name="redirectValue" value="Add"
                                       class="btn btn-primary">
                                <div class="form-group " align="right">
                                    <a href="viewUser?redirectValue=view&id=1" title="Back"><i
                                            class="fa fa-arrow-circle-left" aria-hidden="true" style="font-size:25px"></i></a>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${users.id!= null}">
                            <div class="form-group " align="center" class="row">
                                <input type="hidden" name="id" value=${users.id }><input
                                    type="submit" name="redirectValue" value="Update"
                                    class="btn btn-primary">
                                <div class="form-group " align="right">
                                    <a href="/user/viewUser" title="Back"><i
                                            class="fa fa-arrow-circle-left" aria-hidden="true" style="font-size:25px"></i></a>
                                </div>
                            </div>
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>