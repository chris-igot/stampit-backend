<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:menu/>
<t:base>
<div class="row">
	<div class="col-sm-8 d-flex flex-wrap mx-auto">
		<c:forEach items="${posts}" var="post">
			<div class="col-4 card mb-1">
				<a href="/post?id=${post.id}">
	                <img
	                    src="/img/${post.image.fileName }"
	                    class="card-img-bottom"
	                    alt=""
	                />
				</a>
                <div class="card-body p-2">
                    <p class="text-end m-0">
                    	<span style="font-size:0.8rem">posted by</span> <a class="link-dark" href="/profile?id=${post.profile.id }"><c:out value="${post.profile.name }"/></a>
                    </p>
                </div>
            </div>
		</c:forEach>
	</div>
</div>
</t:base>
