<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:menu/>
<t:base>
<div class="row border border-light p-2 rounded bg-light">
	<div class="col-2">
		<div class="profile-pic" style="">
			<c:choose>
				<c:when test="${profile.image == null }">
					<img class="img-fluid" style="" src="/img/nopic.png" alt="" />
				</c:when>
				<c:when test="${profile.image != null }">
					<img class="img-fluid" style="" src="/img/${profile.image.fileName }" alt="" />
				</c:when>
			</c:choose>
		</div>
	</div>
	<div class="col-10">
		<h5 class="text-dark"><c:out value="${profile.name}"/></h5>
		<c:choose>
			<c:when test="${followed == null }"></c:when>
			<c:when test="${followed == true }">
				<a class="btn btn-outline-dark" href="/profile/unfollow?id=${profile.id }">Followed (<c:out value="${profile.followers.size() }"/>)</a>
			</c:when>
			<c:when test="${followed == false }">
				<a class="btn btn-warning" href="/profile/follow?id=${profile.id }">Follow (<c:out value="${profile.followers.size() }"/>)</a>
			</c:when>
		</c:choose>
	</div>
</div>
<c:choose>
	<c:when test='${profile.title == "" && profile.bio == "" }'>
	</c:when>
	<c:otherwise>
		<div class="row border border-light p-2 my-2 rounded bg-light">
			<h6 class="text-dark"><c:out value="${profile.title}"/></h6>
			<p class="text-dark"><c:out value="${profile.bio}"/></p>
		</div>
	</c:otherwise>
</c:choose>
<div class="row">
<div class="col-12 d-flex flex-wrap">
	<c:forEach items="${profile.posts}" var="post">
		<div class="align-items-center justify-content-center profile-photos-list" style="">
			<a href="/post?id=${post.id }">
				<img class="img-fluid" style="" src="/img/${post.image.fileName }" alt="" />
			</a>
		</div>
	</c:forEach>
</div>
</div>
</t:base>
