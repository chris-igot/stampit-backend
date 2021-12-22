<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:menu/>
<t:base>
<div class="row">
		<form method="POST" enctype="multipart/form-data" action="/stamp/new">
		<h5>File to upload:</h5>
		<div><input type="text" name="name" /></div>
		<div><input type="file" name="file" /></div>
		<div><input type="submit" value="Upload" /></div>
		</form>
		<div >
			<c:out value="${message }"/>
		</div>
</div>

</t:base>
