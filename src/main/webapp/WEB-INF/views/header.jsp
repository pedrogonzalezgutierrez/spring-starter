<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<div id="header" class="container">

	<br/>
	
   	<sec:authorize access="isAnonymous()">
   		<spring:url value="/loginform" var="loginURL"/>
		<a href="${loginURL}" role="button" class="btn btn-success pull-right"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span> <spring:message code="access.login"/></a></li>
	</sec:authorize>
	
   	<sec:authorize access="isAuthenticated()">
		<spring:url value="/logout" var="logoutURL"/>
		<form action="${logoutURL}" method="post" class="form-inline pull-right" role="form">
			<div class="form-group has-feedback">
				<label class="control-label" for="logout"></label>
				<spring:message code="access.logout" var="access_logout"/>
				<input id="logout" type="submit" value="${access_logout}" class="form-control btn btn-danger">
				<span class="glyphicon glyphicon-log-out form-control-feedback"></span>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</div>
		</form>	
	</sec:authorize>

	<div class="row">
		<div class="col-md-12">
			<blockquote>
				<h2><strong><spring:message code="application.name"/></strong></h2>  
				<footer><spring:message code="application.descripcion"/></footer>
			</blockquote>
			<hr>	     	
		</div>	     		
	</div>

</div>
