<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ taglib tagdir="/WEB-INF/tags/profile" prefix="tagsProfile" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
    
    	<sec:authentication property="principal.username" var="username" />
		<div class="well well-lg text-center"></div>
		<div class="alert alert-success" role="alert"><h3 class="text-center"><spring:message code="welcome.user" arguments="${username}"/></h3></div>
		
		<br/>
		
		<tagsProfile:editorArea/>
		
		<br/>
		
    	<tagsProfile:adminArea/>
   		
	</tiles:putAttribute>
</tiles:insertDefinition>