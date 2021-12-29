<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:menu />
<t:base>
	<div class="row justify-content-center">
		<div class="col col-md-6">
			<div class="card p-2">
				<form class="col-md-6-auto" action="/search" method="POST">
					<div class="mb-3">
						<label for="search" class="form-label mb-0">Search</label> <input
							id="search" class="form-control" name="search" type="text"></input>
						<input type="submit" value="Search" class="btn btn-primary" />
					</div>
				</form>
				<c:choose>
					<c:when test="${results == null }">
					</c:when>
					<c:when test="${results != null }">
						<h6>Results:</h6>
					</c:when>
				</c:choose>
				<table class="table table-hover">
					<c:forEach items="${results}" var="profile">
						<tr>
							<td>
								<div class="profile-pic-sm" style="">
									<c:choose>
										<c:when test="${profile.image == null }">
											<img class="img-fluid" style="" src="/img/nopic.png" alt="" />
										</c:when>
										<c:when test="${profile.image != null }">
											<img class="img-fluid" style=""
												src="/img/${profile.image.fileName }" alt="" />
										</c:when>
									</c:choose>
								</div>
							</td>
							<td><a class="link-dark" href="/profile?id=${profile.id}"><c:out
										value="${profile.name }" /></a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</t:base>