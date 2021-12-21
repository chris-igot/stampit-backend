<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:menu/>
<t:base>
<div class="row">
	<h5>Currently following</h5>
	<c:forEach items="${following}" var="profile">
		<div>
			<table>
				<tr>
					<td>
						<a href="/profile?id=${profile.id}"><c:out value="${profile.name }"/></a>
					</td>
					<td>
						<a class="btn btn-outline-secondary" href="/profile/follows/unfollow?id=${profile.id }">Unfollow</a>
					</td>
				</tr>
			</table>
		</div>
	</c:forEach>
</div>
</t:base>
