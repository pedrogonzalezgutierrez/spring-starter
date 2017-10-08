<%@ attribute name="titleCode" required="true"%>
<%@ attribute name="path" required="true" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="panel panel-default">
    <div class="panel-body">
        <h4><spring:message code="${titleCode}"/></h4>

        <div class="radio">
            <label>
                <form:radiobutton path="${path}" value="true"/>
                <spring:message code="action.yes"/>
            </label>
        </div>
        <div class="radio">
            <label>
                <form:radiobutton path="${path}" value="false"/>
                <spring:message code="action.no"/>
            </label>
        </div>
        <c:set var="booleanErrors"><form:errors path="${path}"/></c:set>
        <c:if test="${not empty booleanErrors}">
            <div class="alert alert-danger" role="alert">
                    ${booleanErrors}
            </div>
        </c:if>
    </div>
</div>