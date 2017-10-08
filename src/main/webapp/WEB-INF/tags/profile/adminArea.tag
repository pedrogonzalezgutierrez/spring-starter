<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ taglib tagdir="/WEB-INF/tags/profile" prefix="tagsButtonsProfile" %>

<sec:authorize access="hasRole('ROLE_ADMIN')">

    <div class="alert alert-success" role="alert">
        <h3 class="text-center"><spring:message code="admin.area"/></h3>
    </div>

    <div class="row">

        <spring:message var="title" code="user.plural" htmlEscape="false"/>
        <spring:message var="description" code="user.description" htmlEscape="false"/>
        <spring:url var="manage_entity" value="/user/manage"/>
        <tagsButtonsProfile:button
                href="${manage_entity}"
                icon="fa fa-users fa-5x"
                title="${title}"
                description="${description}"/>

        <spring:message var="title" code="role.plural" htmlEscape="false"/>
        <spring:message var="description" code="role.description" htmlEscape="false"/>
        <spring:url var="manage_entity" value="/role/manage"/>
        <tagsButtonsProfile:button
                href="${manage_entity}"
                icon="fa fa-star-o fa-5x"
                title="${title}"
                description="${description}"/>

    </div>

</sec:authorize>