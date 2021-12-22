<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:menu/>
<t:base>
<div class="row">
     <div class="col">
         <div id="${post.id }" class="card post">
             <span style="position:relative;overflow:hidden">
             	<c:forEach items="${post.stamps}" var="stamp">
					<div style="width:2rem;height:2rem;margin:1px;position:absolute;left:calc(${stamp.x/100}% - 16px);top:calc(${stamp.y/100}% - 16px)">
							<img class="img-fluid" style="max-width: 100%;max-height: 100%;" src="/img/${stamp.image.fileName }" alt="" />
					</div>
				</c:forEach>
             	<img src="/img/${post.image.fileName }" class="click-target card-img-bottom" style="cursor:pointer;" alt="winston place holder">
             </span>
             <div class="card-body">
             	<a href="/post?id=${post.id }" class="btn btn-outline-secondary">exit</a>
             </div>
         </div>
     </div>
</div>
<script src="/js/stampit.js"></script>
</t:base>
