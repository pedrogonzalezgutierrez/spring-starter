<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
    
       	<spring:message var="application_name" code="application.name" htmlEscape="false" />
    	<h1 class="text-center"><spring:message code="welcome.to" arguments="${application_name}"/></h1>
    	
    	<p><spring:message code="welcome.text"/></p>
    	<p><spring:message code="welcome.text"/></p>
    	<p><spring:message code="welcome.description"/></p>
    	
	</tiles:putAttribute>
</tiles:insertDefinition>