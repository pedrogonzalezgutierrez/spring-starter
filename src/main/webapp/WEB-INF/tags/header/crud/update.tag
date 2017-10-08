<%@ attribute name="messagecode" required="true" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div role="alert" class="alert text-center alert-info">
	<spring:message var="target_entity" code="${messagecode}" htmlEscape="false" />
	<h3>
		<spring:message code="action.update.item" arguments="${target_entity}" />
	</h3>
</div>