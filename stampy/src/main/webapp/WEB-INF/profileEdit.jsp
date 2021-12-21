<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:menu/>
<t:base>
<div class="row">
	<h3 class="my-5">Profile info</h3>
</div>
<div class="row">
	<form method="POST" enctype="multipart/form-data" action="/home/setimage">
		<h5>Upload a profile pic:</h5>
		<div><input type="file" name="file" /></div>
		<div><input type="submit" value="Upload" /></div>
	</form>
</div>
<div class="row">
	<form:form class="col col-md-6" action="/home/edit" method="POST" modelAttribute="profileEditForm">
	    
	    <div class="mb-3">		
	        <form:label for="title" class="form-label mb-0" path="title">title:</form:label>
	        <form:input id="title" class="form-control" path="title" type="text"/>
	        <form:errors path="title" class="text-danger"></form:errors>
	    </div>
	    <div class="mb-5">
	        <form:label for="bio" class="form-label mb-0" path="bio">bio:</form:label>
	        <form:input id="bio" class="form-control" path="bio" type="text"/>
	        <form:errors path="bio" class="text-danger"></form:errors>
	    </div>
	    <input type="submit" value="Update" class="btn btn-primary" />
	</form:form>
</div>
</t:base>
