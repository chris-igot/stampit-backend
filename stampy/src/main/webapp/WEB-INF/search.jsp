<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:menu/>
<t:base>
<div class="row">
	<form class="col-md-6" action="/search" method="POST">
		<div class="mb-3">
			<label for="search" class="form-label mb-0">Search</label>
			<input id="search" class="form-control" name="search" type="text"></input>
			<input type="submit" value="Search" class="btn btn-primary" />
		</div>
	</form>
</div>
<div class="row">
	<c:forEach items="${results}" var="profile">
		<div>
			<a href="/profile?id=${profile.id}">
				<c:out value="${profile.name }"/>
			</a>
		</div>
	</c:forEach>
</div>
</t:base>
