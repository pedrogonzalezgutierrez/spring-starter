<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib tagdir="/WEB-INF/tags/header/crud" prefix="tagsHeaderCrud" %>
<%@ taglib tagdir="/WEB-INF/tags/buttons/crud" prefix="tagsButtonCrud" %>
<%@ taglib tagdir="/WEB-INF/tags/buttons/crud/table" prefix="tagsButtonTable" %>
<%@ taglib tagdir="/WEB-INF/tags/warning" prefix="tagsWarning" %>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
    
    	<tagsHeaderCrud:manage messagecode="language.plural"/>
    	
    	<tagsButtonCrud:create messageCode="language" entityURL="/language/createForm"/>
		
		<br/>
		
		<c:choose>
			<c:when test="${not empty languages}">
									
				<div class="table-responsive">
					<table class="table table-hover table-condensed">
						<thead>
							<tr class="active">
								<th class="text-center"><spring:message code="id"/></th>
	            				<th class="text-center"><spring:message code="language.language"/></th>
	            				<th class="text-center"><spring:message code="language.flag"/></th>
	            				<th class="text-center"><spring:message code="action.view"/></th>
	            				<th class="text-center"><spring:message code="action.update"/></th>
	            				<th class="text-center"><spring:message code="action.delete"/></th>
							</tr>
						</thead>
				
						<tbody>
							<c:forEach var="language" items="${languages}">
								<tr>
								
		            				<th scope="row" class="text-center">${language.id}</th>
		            				
		            				<td class="text-center">${language.languageName}</td>
		            				
		            				<td class="text-center"><span class="flag-icon flag-icon-${language.flagCode}"></span></td>
		            				
									<td class="text-center">
										<tagsButtonTable:view entityURL="language" id="${language.id}"/>
									</td>
									
									<td class="text-center">
										<tagsButtonTable:update entityURL="language" id="${language.id}"/>
									</td>
									
		            				<td class="text-center">
		            					<tagsButtonTable:delete entityURL="language" id="${language.id}"/>
									</td>
		            			</tr>								
							</c:forEach>
						</tbody>
					</table>
				</div>
										
			</c:when>

			<c:otherwise>
				<tagsWarning:noItems messagecode="language.plural"/>
			</c:otherwise>
		</c:choose>
							
			  
	</tiles:putAttribute>
</tiles:insertDefinition>