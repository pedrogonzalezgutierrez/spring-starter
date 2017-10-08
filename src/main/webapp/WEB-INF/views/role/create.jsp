<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ taglib tagdir="/WEB-INF/tags/form" prefix="tagsForm" %>
<%@ taglib tagdir="/WEB-INF/tags/header/crud" prefix="tagsHeaderCrud" %>

<tiles:insertDefinition name="defaultTemplate">

    <tiles:putAttribute name="body">
    
    	<tagsHeaderCrud:create messagecode="role"/>
				
		<spring:url value="/role/create" var="url_create"/>				
		<form:form commandName="roleDTO" method="post" action="${url_create}">
		    
		    <!-- rolename -->
		    <tagsForm:formInput id="idrolename" path="rolename" messagecode="role.rolename"/>
		    
		    <br/>
		    		    
			<!-- submit -->
			<tagsForm:submitCreate/>						
			
		</form:form>
	
	</tiles:putAttribute>
	`
</tiles:insertDefinition>