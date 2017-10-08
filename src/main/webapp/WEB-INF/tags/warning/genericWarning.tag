<%@ attribute name="messagecode" required="true" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="alert alert-warning" role="alert">
	<p class="text-center"><spring:message code="${messagecode}"/></p>
</div>


