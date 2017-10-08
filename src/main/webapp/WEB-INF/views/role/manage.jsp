<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib tagdir="/WEB-INF/tags/header/crud" prefix="tagsHeaderCrud" %>
<%@ taglib tagdir="/WEB-INF/tags/buttons/crud" prefix="tagsButtonCrud" %>
<%@ taglib tagdir="/WEB-INF/tags/buttons/crud/table" prefix="tagsButtonTable" %>
<%@ taglib tagdir="/WEB-INF/tags/warning" prefix="tagsWarning" %>
<%@ taglib tagdir="/WEB-INF/tags/paginator" prefix="tagsPaginator" %>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
    
    	<tagsHeaderCrud:manage messagecode="role.plural"/>
    	
    	<tagsButtonCrud:create messageCode="role" entityURL="/role/createForm"/>
		<br/>
		
		<c:choose>
			<c:when test="${not empty items.content}">
									
				<div class="table-responsive">
					<table class="table table-hover table-condensed">
						<thead>
							<tr class="active">
								<th class="text-center"><spring:message code="id"/></th>
	            				<th class="text-center"><spring:message code="role.rolename"/></th>
	            				<th class="text-center"><spring:message code="action.view"/></th>
	            				<th class="text-center"><spring:message code="action.update"/></th>
	            				<th class="text-center"><spring:message code="action.delete"/></th>
							</tr>
						</thead>
				
						<tbody>
							<c:forEach var="role" items="${items.content}">
								<tr>
								
		            				<th scope="row" class="text-center">${role.id}</th>
		            				
		            				<td class="text-center">${role.rolename}</td>
		            				
									<td class="text-center">
										<tagsButtonTable:view entityURL="role" id="${role.id}"/>
									</td>
									
									<td class="text-center">
										<tagsButtonTable:update entityURL="role" id="${role.id}"/>
									</td>
									
		            				<td class="text-center">
		            					<tagsButtonTable:delete entityURL="role" id="${role.id}"/>
									</td>
		            			</tr>								
							</c:forEach>
						</tbody>
					</table>
				</div>
										
			</c:when>

			<c:otherwise>
				<tagsWarning:noItems messagecode="role.plural"/>
			</c:otherwise>
		</c:choose>
		
		<tagsPaginator:show baseURL="/role/manage" page="${items}" />
		
	</tiles:putAttribute>
</tiles:insertDefinition>