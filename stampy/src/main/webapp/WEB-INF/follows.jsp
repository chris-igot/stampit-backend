<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:menu/>
<t:base>
<div class="row justify-content-center">
	<div class="h5 text-center">Currently following</div>
	  <div class="col-auto">
		<c:forEach items="${following}" var="profile">
			<table class ="table table-hover">
				<tr>
					<td>
						<a href="/profile?id=${profile.id}"><c:out value="${profile.name }"/></a>
					</td>
					<td>
						<a class="btn btn-outline-dark" href="/profile/follows/unfollow?id=${profile.id }">Unfollow</a>
					</td>
				</tr>
			</table>
		</c:forEach>
	  </div>
</div>
</t:base>
