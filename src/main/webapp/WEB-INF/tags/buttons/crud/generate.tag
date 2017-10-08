<%@ attribute name="messageCode" required="true" %>
<%@ attribute name="entityURL" required="true" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="text-center">
	<spring:message var="target_entity" code="${messageCode}" htmlEscape="false" />
	<spring:url value="${entityURL}" var="url_create_entity" />
	<a
		href="${url_create_entity}"
		role="button" class="btn btn-success">
		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> <spring:message code="action.generate.item" arguments="${target_entity}" />
	</a>
</div>
