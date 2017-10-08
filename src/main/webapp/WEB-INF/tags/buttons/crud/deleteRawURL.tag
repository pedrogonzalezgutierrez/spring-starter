<%@ attribute name="id" required="true" %>
<%@ attribute name="URL" required="true" %>
<%@ attribute name="messageCodeAction" required="true" %>
<%@ attribute name="messageCodeActionDescription" required="true" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<a><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#confirmAction_${id}"><i class="fa fa-times"></i></button></a>

<div id="confirmAction_${id}" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="confAct_${id}" aria-hidden="true">
	<div class="modal-dialog modal-sm">
    	<div class="modal-content">
			<div class="modal-header">
       			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
       			<h4 class="modal-title" id="myModalLabel"><spring:message code="warning.are-you-sure"/></h4>
     			</div>
     			<div class="modal-body">
				<p><spring:message code="${messageCodeActionDescription}"/></p>
     			</div>
     			<div class="modal-footer">
       			<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="action.no"/></button>
       			<a href="${URL}" role="button" class="btn btn-danger"><spring:message code="action.yes"/></a>
     			</div>
    	</div>
  </div>
</div>

