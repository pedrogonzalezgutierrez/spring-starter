<!-- 
Used for when we want to delete an entity from database
 -->
<%@ attribute name="entityURL" required="true" %>
<%@ attribute name="id" required="true" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<button type="button" class="btn btn-default btn-danger" data-toggle="modal" data-target="#confirmDelete_${id}"><span class="glyphicon glyphicon-trash"></span></button>

<div id="confirmDelete_${id}" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="confDel_${id}" aria-hidden="true">
	<div class="modal-dialog modal-sm">
    	<div class="modal-content">
			<div class="modal-header">
       			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
       			<h4 class="modal-title" id="myModalLabel"><spring:message code="warning.are-you-sure"/></h4>
     			</div>
     			<div class="modal-body">
				<p><spring:message code="warning.data-lose"/></p>
     			</div>
     			<div class="modal-footer">
       			<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="action.no"/></button>
       			<spring:url value="/${entityURL}/delete/${id}" var="url_delete"/>
       			<a href="${url_delete}" role="button" class="btn btn-danger"><spring:message code="action.yes"/></a>
     			</div>
    	</div>
  </div>
</div>
