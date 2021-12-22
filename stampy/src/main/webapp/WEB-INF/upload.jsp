<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:menu/>
<t:base>
<div class="row justify-content-center">
	<div class="col-auto">
		<form class="card p-2" method="POST" enctype="multipart/form-data" action="/post/new">
		<h5>Post a new photo</h5>
		<div><input class="form-control" type="file" name="file" /></div>
		<div><input class="mt-2 btn btn-outline-secondary" type="submit" value="Upload" /></div>
		</form>
		<div >
			<c:out value="${message }"/>
		</div>
	</div>
</div>

</t:base>