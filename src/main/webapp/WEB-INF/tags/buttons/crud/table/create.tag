<%@ attribute name="entityURL" required="true" %>
<%@ attribute name="idParent" required="true" %>
<%@ attribute name="idChild" required="true" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:url value="/${entityURL}/${idParent}/${idChild}/createForm" var="url_create"/>
<a href="${url_create}" role="button" class="btn btn-default btn-success"><span class="glyphicon glyphicon-plus"></span></a>
