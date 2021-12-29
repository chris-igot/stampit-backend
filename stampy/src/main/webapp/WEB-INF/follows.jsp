<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:menu />
<t:base>
	<div class="row justify-content-center">
		<div class="col col-md-6">
			<div class="card">
				<h5 class="h5 card-title p-2">Currently following</h5>
				<table class="table table-hover">
					<c:forEach items="${following}" var="profile">
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
							<td><a class="btn btn-outline-dark float-end"
								href="/profile/follows/unfollow?id=${profile.id }">Unfollow</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</t:base>
