<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:menu />
<t:base>
	<div class="row justify-content-center">
		<div class="col col-md-6">
			<div class="card p-2 mb-1">
				<h5 class="card-title">Upload a profile pic:</h5>
				<form class="" method="POST" enctype="multipart/form-data"
					action="/home/setimage">
					<div>
						<input class="form-control" type="file" name="file" />
					</div>
					<div>
						<input class="mt-2 btn btn-outline-secondary" type="submit"
							value="Upload" />
					</div>
				</form>
			</div>
			<div class="card p-2">
				<h5 class="card-title">Profile info</h5>
				<form:form class="" action="/home/edit" method="POST"
					modelAttribute="profileEditForm">
					<div class="mb-3">
						<form:label for="title" class="form-label mb-0" path="title">title:</form:label>
						<form:input id="title" class="form-control" path="title"
							type="text" />
						<form:errors path="title" class="text-danger"></form:errors>
					</div>
					<div class="mb-5">
						<form:label for="bio" class="form-label mb-0" path="bio">bio:</form:label>
						<form:input id="bio" class="form-control" path="bio" type="text" />
						<form:errors path="bio" class="text-danger"></form:errors>
					</div>
					<input type="submit" value="Update" class="btn btn-outline-primary" />
				</form:form>
			</div>
		</div>
	</div>
</t:base>