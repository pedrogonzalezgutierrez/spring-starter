<%@ attribute name="URL" required="true" %>
<%@ attribute name="buttonClass" required="true" %>
<%@ attribute name="buttonIcon" required="true" %>
<%@ attribute name="codeAction" required="true" %>
<%@ attribute name="codeElement" required="true" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:message code="${codeElement}" var="element" />
<a href="${URL}" role="button" class="btn btn-${buttonClass}"><i class="${buttonIcon}"></i> <spring:message code="${codeAction}" arguments="${element}" /></a>