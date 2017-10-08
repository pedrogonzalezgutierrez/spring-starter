<!-- 
Used for when an entity has OneToMany/ManyToMany with another entity and we want to remove a child of a parent
 -->
<%@ attribute name="entityURL" required="true" %>
<%@ attribute name="idParent" required="true" %>
<%@ attribute name="idChild" required="true" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:url value="/${entityURL}/remove/${idParent}/${idChild}" var="url_remove"/>
<a href="${url_remove}" role="button" class="btn btn-default btn-danger"><span class="glyphicon glyphicon-trash"></span></a>
