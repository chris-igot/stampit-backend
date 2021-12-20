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
	<form class="col-md-6" action="/login" method="POST">
		<h3 class="my-5">Login</h3>
		<div class="mb-3">
			<label for="email" class="form-label mb-0">Email:</label>
			<input id="email" class="form-control" name="email" type="text"></input>
		</div>
		<div class="mb-5">
			<label for="password" class="form-label mb-0">Password:</label>
			<input id="password" class="form-control" name="password" type="password"></input>
		</div>
		<p class="text-danger"><c:out value="${loginError }"/></p>
		<input type="submit" value="Log In" class="btn btn-primary" />
	</form>
	<form:form class="col col-md-6" action="/register" method="POST" modelAttribute="registerForm">
		<h3 class="my-5">Register</h3>
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
	</form:form>
</div>
</t:base>