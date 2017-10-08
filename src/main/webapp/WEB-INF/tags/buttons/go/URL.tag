<%@ attribute name="URL" required="true" %>
<%@ attribute name="icon" required="true" %>
<%@ attribute name="messageCode" required="true" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:url value="${URL}" var="domain_url"/>
<a href="${domain_url}" role="button" class="btn btn-warning btn-lg btn-block"><spring:message code="${messageCode}"/></a>