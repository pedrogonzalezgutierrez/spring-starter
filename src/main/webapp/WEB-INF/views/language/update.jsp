<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ taglib tagdir="/WEB-INF/tags/form" prefix="tagsForm" %>
<%@ taglib tagdir="/WEB-INF/tags/header/crud" prefix="tagsHeaderCrud" %>
<%@ taglib tagdir="/WEB-INF/tags/buttons/go" prefix="tagsButtonGo" %>

<tiles:insertDefinition name="defaultTemplate">

    <tiles:putAttribute name="body">
    
    	<tagsHeaderCrud:update messagecode="language"/>
				
		<spring:url value="/language/update" var="url_update"/>				
		<form:form commandName="languageDTO" method="post" action="${url_update}">
		
			<!-- id -->				
			<form:hidden path="id" />
		    
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
			<tagsForm:submitUpdate/>						
			
		</form:form>
		
		<tagsButtonGo:URL URL="/language/manage" messageCode="action.back" icon="fa fa-backward fa-3x" />
		
	</tiles:putAttribute>
	
</tiles:insertDefinition>