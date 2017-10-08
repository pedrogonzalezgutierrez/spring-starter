<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ taglib tagdir="/WEB-INF/tags/profile" prefix="tagsButtonsProfile" %>

<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_EDITOR')">

    <div class="alert alert-success" role="alert"><h3 class="text-center"><spring:message code="editor.area"/></h3></div>

    <div class="row">

        <spring:message var="title" code="language.plural" htmlEscape="false" />
        <spring:message var="description" code="language.description" htmlEscape="false" />
        <spring:url     var="manage_entity" value="/language/manage" />
        <tagsButtonsProfile:button href="${manage_entity}" icon="fa fa-language fa-5x" title="${title}" description="${description}" />

    </div>

</sec:authorize>