<%@ attribute name="messagecode" required="true" %>
<%@ attribute name="path" required="true" %>
<%@ attribute name="items" required="true" type="java.util.List" %>
<%@ attribute name="itemLabel" required="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<div class="input-group">
	<span class="input-group-addon" for="${path}"><spring:message code="${messagecode}" /></span>
	<form:select
		id="${path}"
		path="${path}"
		items="${items}"
		itemValue="id"
		itemLabel="${itemLabel}"
		multiple="false"
		cssClass="form-control" />          
</div>
<c:set var="listErrors"><form:errors path="${path}"/></c:set>
<c:if test="${not empty listErrors}">
	<div class="alert alert-danger" role="alert">
		${listErrors}
	</div>			
</c:if>


