<%@ attribute name="messagecode" required="true" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:message var="target_entity" code="${messagecode}" htmlEscape="false" />
<div class="alert alert-warning" role="alert">
	<p class="text-center"><spring:message code="warning.no-items" arguments="${target_entity}"/></p>
</div>
