<%@ attribute name="messagecode" required="true" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:message var="target_entity" code="${messagecode}" htmlEscape="false" />
<div class="alert alert-info text-center" role="alert"><strong><spring:message code="action.view.item" arguments="${target_entity}" /></strong></div>