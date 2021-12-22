<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:base>
<div class="row justify-content-center">
<h1 class="text-center mt-3">Login/Register</h1>
</div>
<div class="row justify-content-center">
	<form:form class="col col-md-6" action="/register" method="POST" modelAttribute="registerForm">
		<div class="card p-2">
			<h3 class="title-font mb-0">stckIt!</h3>
			<h6 class="mt-0 my-3">Register</h6>
			<div class="mb-3">
				<form:label class="form-label mb-0" path="name" for="name">Name: </form:label>
				<form:input type="text" class="form-control" path="name" id="name"></form:input>
				<form:errors path="name" class="text-danger"></form:errors>
			</div>
			<div class="mb-3">
				<form:label class="form-label mb-0" path="email" for="email">Email: </form:label>
				<form:input type="text" class="form-control" path="email" id="email"></form:input>
				<form:errors path="email" class="text-danger"></form:errors>
			</div>
			<div class="mb-3">
				<form:label class="form-label mb-0" path="password" for="password">Password: </form:label>
				<form:input type="password" class="form-control" path="password" id="password"></form:input>
				<form:errors path="password" class="text-danger"></form:errors>
			</div>
			<div class="mb-5">
				<form:label class="form-label mb-0" path="passwordConfirm" for="passwordConfirm">Confrim Password: </form:label>
				<form:input type="password" class="form-control" path="passwordConfirm" id="passwordConfirm"></form:input>
				<form:errors path="passwordConfirm" class="text-danger"></form:errors>
			</div>
			<input type="submit" value="Submit" class="btn btn-primary" />
		</div>
	</form:form>
</div>
</t:base>