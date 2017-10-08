<!-- 
Used for when an entity has OneToMany/ManyToMany with another entity and we want to add a child to a parent
 -->
<%@ attribute name="entityURL" required="true" %>
<%@ attribute name="idParent" required="true" %>
<%@ attribute name="idChild" required="true" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:url value="/${entityURL}/add/${idParent}/${idChild}" var="url_add"/>
<a href="${url_add}" role="button" class="btn btn-default btn-success"><span class="glyphicon glyphicon-plus"></span></a>
