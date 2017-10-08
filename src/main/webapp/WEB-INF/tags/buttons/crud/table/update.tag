<%@ attribute name="entityURL" required="true" %>
<%@ attribute name="id" required="true" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:url value="/${entityURL}/updateForm/${id}" var="url_update"/>
<a href="${url_update}"><button type="button" class="btn btn-default btn-info"><span class="glyphicon glyphicon-edit"></span></button></a>