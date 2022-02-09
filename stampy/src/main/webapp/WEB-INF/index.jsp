<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:base>
	<div class="row justify-content-center">
		<h1 class="text-center mt-3">Login/Register</h1>
	</div>
	<div class="row justify-content-center">
		<form class="col-md-6" action="/login" method="POST">
			<div class="card p-2">
				<h3 class="title-font mb-0">stckIt!</h3>
				<h6 class="mt-0 mb-3">Login</h6>
				<div class="mb-3">
					<label for="email" class="form-label mb-0">Email:</label> <input
						id="email" class="form-control" name="email" type="text"></input>
				</div>
				<div class="mb-5">
					<label for="password" class="form-label mb-0">Password:</label> <input
						id="password" class="form-control" name="password" type="password"></input>
				</div>
				<div>
					<a href="/register">New user? Create an acount</a>
				</div>
				<p class="text-danger">
					<c:out value="${loginError }" />
				</p>
				<input type="submit" value="Log In" class="btn btn-primary" />
			</div>
		</form>
	</div>
</t:base>