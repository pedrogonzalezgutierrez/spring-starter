<%@ attribute name="id" required="true" %>
<%@ attribute name="path" required="true" %>
<%@ attribute name="messagecode" required="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="input-group">
	<span class="input-group-addon" for="${id}"><spring:message code="${messagecode}" /></span>
	<form:input id="${id}" path="${path}" cssClass="form-control" htmlEscape="true" />
</div>

<c:set var="errors">
	<form:errors path="${path}"/>
</c:set>
<c:if test="${not empty errors}">
	<div class="alert alert-danger" role="alert">
		${errors}
	</div>			
</c:if>