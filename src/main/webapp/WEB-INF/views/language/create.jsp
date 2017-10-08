<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ taglib tagdir="/WEB-INF/tags/form" prefix="tagsForm" %>
<%@ taglib tagdir="/WEB-INF/tags/header/crud" prefix="tagsHeaderCrud" %>

<tiles:insertDefinition name="defaultTemplate">

    <tiles:putAttribute name="body">
    
    	<tagsHeaderCrud:create messagecode="language"/>
				
		<spring:url value="/language/create" var="url_create"/>				
		<form:form commandName="languageDTO" method="post" action="${url_create}">
		    
    		<!-- name -->
		    <tagsForm:formInput id="idname" path="languageName" messagecode="language.language"/>
		    
		    <br/>
		    
		    <!-- code -->
		    <tagsForm:formInput id="idcode" path="code" messagecode="language.code"/>
		    
		    <br/>
		    
		    <!-- flagCode -->
		    <tagsForm:formInput id="idflagCode" path="flagCode" messagecode="language.flagCode"/>		    
		    
		    <br/>
		    		    
			<!-- submit -->
			<tagsForm:submitCreate/>						
			
		</form:form>
	
	</tiles:putAttribute>
	`
</tiles:insertDefinition>