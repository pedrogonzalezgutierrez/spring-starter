<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div id="footer" class="container">

	<div class="panel panel-default">
		<div class="panel-body">
			<p class="text-center"><spring:message code="footer.text"/></p>
			
			<!-- Locale Switch -->
			<spring:url value="/?lang=en" var="switch_english"/>
			<spring:url value="/resources/images/en.png" var="english_flag" />
			<a href="${switch_english}" class="thumbnail pull-right"><img src="${english_flag}" alt="<spring:message code="language.english"/>"></a>
			
			<spring:url value="/?lang=es" var="switch_spanish"/>
			<spring:url value="/resources/images/es.png" var="spanish_flag" />
			<a href="${switch_spanish}" class="thumbnail pull-right"><img src="${spanish_flag}" alt="<spring:message code="language.spanish"/>"></a>
			
		</div>
	</div>
	
</div>