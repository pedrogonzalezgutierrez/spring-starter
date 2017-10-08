<%@ attribute name="page" required="true" type="org.springframework.data.domain.Page" %>
<%@ attribute name="baseURL" required="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:if test="${page.totalPages > 1}">
	<nav class="text-center">
		<ul class="pagination">
			<c:forEach begin="0" end="${page.totalPages-1}" varStatus="loop">
				<c:choose>
					<c:when test="${loop.index == page.number}">
						<c:set var="isActive" value="active" />
					</c:when>
					<c:otherwise>
						<c:set var="isActive" value="" />
					</c:otherwise>
				</c:choose>

				<spring:url var="url_domain" value="${baseURL}?page=${loop.index}" />
				<li class="${isActive}"><a href="${url_domain}">${loop.index+1}</a></li>
			</c:forEach>
		</ul>
	</nav>
</c:if>
