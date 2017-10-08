<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib tagdir="/WEB-INF/tags/header/crud" prefix="tagsHeaderCrud" %>
<%@ taglib tagdir="/WEB-INF/tags/buttons/go" prefix="tagsButtonGo" %>
<%@ taglib tagdir="/WEB-INF/tags/warning" prefix="tagsWarning" %>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
    
    	<tagsHeaderCrud:view messagecode="language"/>
    	<dl>
			<dt><spring:message code="id"/></dt>
			<dd>${languageDTO.id}</dd>
			<br/>
			<dt><spring:message code="language.language"/></dt>
			<dd>${languageDTO.languageName}</dd>
		</dl>
		
		<tagsButtonGo:back/>
    	
	</tiles:putAttribute>
	
	<tiles:putAttribute name="js">
		<script type="text/javascript">
			$(document).ready(function(){
				enableBackButton();
			});
		</script>
	</tiles:putAttribute>
	
	
</tiles:insertDefinition>