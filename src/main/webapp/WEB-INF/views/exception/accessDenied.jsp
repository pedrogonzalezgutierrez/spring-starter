<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
 
<%@ taglib tagdir="/WEB-INF/tags/buttons/go" prefix="tagsButtonGo" %>

<tiles:insertDefinition name="defaultTemplate">

    <tiles:putAttribute name="body">
    
		<div class="alert alert-danger" role="alert">
			<p class="text-center"><i class="fa fa-eye-slash fa-5x"></i></p>
			<h3 class="text-center"><spring:message code="access.denied"/></h3>
		</div>
		
		<tagsButtonGo:URL URL="/" messageCode="navbar.home" icon="fa fa-home fa-2x"/>
		
	</tiles:putAttribute>
	`
</tiles:insertDefinition>