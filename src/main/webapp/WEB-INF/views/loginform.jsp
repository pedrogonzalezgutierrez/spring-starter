<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">

    	<div role="alert" class="alert text-center alert-warning">
			<h3><spring:message code="access.loginform"/></h3>
		</div>
		
		<c:if test="${not empty param.error}">
			<div class="alert alert-danger text-center" role="alert">
				<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
				<span class="sr-only">Error:</span>
				<spring:message code="error.login"/>
			</div>
		</c:if>

		<spring:url value="/login" var="loginURL"/>				
		<form name='loginForm' action="${loginURL}" method='post'>
		
			<!-- username -->
			<div class="input-group">
				<span class="input-group-addon" for="Username"><spring:message code="user.username" /></span>
				<input id="Username" name="username" type="text" class="form-control">
			</div>
			
			<br/>
			
			<!-- password -->
			<div class="input-group">
				<span class="input-group-addon" for="Password"><spring:message code="user.password" /></span>
				<input id="Password" name="password" type="password" class="form-control">
			</div>
			
			<!-- CSRF -->				
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			
			<br/>
			
			<!-- submit -->						
			<input type="submit" value="<spring:message code="access.login" />" class="btn btn-success center-block" />
			
		</form>
	
	</tiles:putAttribute>
</tiles:insertDefinition>