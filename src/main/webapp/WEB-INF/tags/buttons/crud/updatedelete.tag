<%@ attribute name="entity" required="true" %>
<%@ attribute name="id" required="true" %>

<%@ taglib tagdir="/WEB-INF/tags/buttons/crud" prefix="tagsButtonCrud" %>

<div class="row">

	<div class="col-xs-12">
		<tagsButtonCrud:update entityURL="${entity}" id="${id}" />
		<tagsButtonCrud:delete entityURL="${entity}" id="${id}" />
	</div>
	
</div>