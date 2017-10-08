<%@ attribute name="id" required="true" %>
<%@ attribute name="path" required="true" %>
<%@ attribute name="messagecode" required="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="form-group">
	<label for="${id}"><spring:message code="${messagecode}" /></label>
	<form:textarea path="${path}" rows="15" cols="30" cssClass="form-control" htmlEscape="false"/>
</div>
<c:set var="errors">
	<form:errors path="${path}"/>
</c:set>
<c:if test="${not empty errors}">
	<div class="alert alert-danger" role="alert">
		${errors}
	</div>			
</c:if>
