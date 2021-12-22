<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:menu/>
<t:base>
<div class="row">
	<div class="col-2">
		<div class="align-items-center justify-content-center" style="width:5rem;height:5rem;background-color:121212;margin:1px">
			<c:choose>
				<c:when test="${profile.image == null }">
					<div>no image</div>
				</c:when>
				<c:when test="${profile.image != null }">
					<img class="img-fluid" style="max-width: 100%;max-height: 100%;" src="/img/${profile.image.fileName }" alt="" />
				</c:when>
			</c:choose>
		</div>
	</div>
	<div class="col-10">
		<h5><c:out value="${profile.name}"/></h5>
		<c:choose>
			<c:when test="${followed == null }"></c:when>
			<c:when test="${followed == true }">
				<a class="btn btn-outline-secondary" href="/profile/unfollow?id=${profile.id }">Unfollow (<c:out value="${profile.followers.size() }"/>)</a>
			</c:when>
			<c:when test="${followed == false }">
				<a class="btn btn-outline-warning" href="/profile/follow?id=${profile.id }">Follow (<c:out value="${profile.followers.size() }"/>)</a>
			</c:when>
		</c:choose>
	</div>
</div>
<div class="row">
	<h6><c:out value="${profile.title}"/></h6>
	<p><c:out value="${profile.bio}"/></p>
</div>
<div class="row">
<div class="col-12 d-flex flex-wrap">
	<c:forEach items="${profile.posts}" var="post">
		<div class="align-items-center justify-content-center" style="width:15rem;height:15rem;background-color:121212;margin:1px">
			<a href="/post?id=${post.id }">
				<img class="img-fluid" style="max-width: 100%;max-height: 100%;" src="/img/${post.image.fileName }" alt="" />
			</a>
		</div>
	</c:forEach>
</div>
</div>
</t:base>
