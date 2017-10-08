<%@ attribute name="messagecode" required="true" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div role="alert" class="alert text-center alert-success">
	<spring:message var="target_entity" code="${messagecode}" htmlEscape="false" />
	<h2>
		<spring:message code="action.manage.entity" arguments="${target_entity}" />
	</h2>
</div>