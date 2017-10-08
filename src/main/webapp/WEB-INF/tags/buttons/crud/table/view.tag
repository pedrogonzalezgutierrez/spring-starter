<%@ attribute name="entityURL" required="true" %>
<%@ attribute name="id" required="true" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:url value="/${entityURL}/view/${id}" var="url_show"/>
<a href="${url_show}" role="button" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a>
