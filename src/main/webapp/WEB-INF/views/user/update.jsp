<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ taglib tagdir="/WEB-INF/tags/form" prefix="tagsForm" %>
<%@ taglib tagdir="/WEB-INF/tags/header/crud" prefix="tagsHeaderCrud" %>
<%@ taglib tagdir="/WEB-INF/tags/buttons/go" prefix="tagsButtonGo" %>
<%@ taglib tagdir="/WEB-INF/tags/buttons/crud/table" prefix="tagsButtonTable" %>
<%@ taglib tagdir="/WEB-INF/tags/warning" prefix="tagsWarning" %>

<tiles:insertDefinition name="defaultTemplate">

    <tiles:putAttribute name="body">
    
    	<tagsHeaderCrud:update messagecode="user"/>

		<div class="panel panel-primary">
			<div class="panel-heading text-center">
				<h3 class="panel-title"><spring:message code="user.roles"/></h3>
			</div>
			<div class="panel-body">
				<c:choose>
					<c:when test="${not empty userDTO.roles}">
						<div class="table-responsive">
							<table class="table table-hover table-condensed">
								<thead>
									<tr class="active">
										<th class="text-center"><spring:message code="role.rolename"/></th>
				            			<th class="text-center"><spring:message code="action.delete"/></th>
									</tr>
								</thead>
							
								<tbody>
									<c:forEach var="role" items="${userDTO.roles}">
										<tr>
				            				<td class="text-center">${role.rolename}</td>
				            				<td class="text-center">
				            					<tagsButtonTable:remove entityURL="user" idParent="${userDTO.id}" idChild="${role.id}"/>
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
			</div>
		</div>				

		<div class="panel panel-primary">
			<div class="panel-heading text-center">
				<h3 class="panel-title"><spring:message code="role.plural"/></h3>
			</div>
			<div class="panel-body">
				<c:choose>
					<c:when test="${not empty roles}">
						<div class="table-responsive">
							<table class="table table-hover table-condensed">
								<thead>
									<tr class="active">
										<th class="text-center"><spring:message code="role.rolename"/></th>
			            				<th class="text-center"><spring:message code="action.add"/></th>
									</tr>
								</thead>
						
								<tbody>
									<c:forEach var="role" items="${roles}">
										<tr>
										
				            				<td class="text-center">${role.rolename}</td>
				            				<td class="text-center">
				            				<tagsButtonTable:add entityURL="user" idParent="${userDTO.id}" idChild="${role.id}"/>
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
			</div>
		</div>				
		
	
				
				
		<spring:url value="/user/update" var="url_update"/>				
		<form:form commandName="userDTO" method="post" action="${url_update}">
		
		    <!-- username -->
		    <tagsForm:formInput id="idusername" path="username" messagecode="user.username"/>
		    
		    <br/>
		    
		    <!-- password -->
		    <tagsForm:formInput id="idpassword" path="password" messagecode="user.password"/>
		    
		    <br/>
		    
		    <!-- enabled -->
		    <tagsForm:formBoolean path="enabled" messagecode="user.enabled"/>
		    
		    <br/>
		    
			<!-- submit -->
			<tagsForm:submitUpdate/>						
			
		</form:form>

		<tagsButtonGo:URL URL="/user/manage" messageCode="action.back" icon="fa fa-backward fa-3x" />
				
	</tiles:putAttribute>
	`
</tiles:insertDefinition>