<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
 
<%@ taglib tagdir="/WEB-INF/tags/buttons/go" prefix="tagsButtonGo" %>

<tiles:insertDefinition name="defaultTemplate">

    <tiles:putAttribute name="body">

		<div class="alert alert-danger" role="alert">
			<p class="text-center"><i class="fa fa-exclamation-circle fa-5x"></i></p>
			<h3 class="text-center"><spring:message code="exception.persistence-problem"/></h3>
		</div>

		<tagsButtonGo:back/>
			  
	</tiles:putAttribute>

	<tiles:putAttribute name="js">
		<script type="text/javascript">
			$(document).ready(function(){
				enableBackButton();
			});
		</script>
	</tiles:putAttribute>
	`
</tiles:insertDefinition>