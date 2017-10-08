<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<div id="menu" class="container">
	
    <nav class="navbar navbar-default" role="navigation">
   		
		<div class="container-fluid">
   			
		    <div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-simples">
			        <span class="sr-only"><spring:message code="navbar.toggle.navigation"/></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
				</button>
				
				<spring:url value="/" var="homepage"/>
				<a class="navbar-brand" href="${homepage}" style="color:green"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> <spring:message code="navbar.home"/></a>
		    </div>
		
		    <div class="collapse navbar-collapse" id="navbar-simples">
			    
				<ul class="nav navbar-nav">						
					<li><a href="" style="color:darkred"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> <spring:message code="navbar.search"/></a></li>
				</ul>
				
				<sec:authorize access="isAuthenticated()">
					<sec:authentication var="principal" property="principal" />
					
					<ul class="nav navbar-nav navbar-right">
						<spring:url value="/profile/show" var="profile"/>
						<li><a href="${profile}" style="color:darkred"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${principal.username}</a></li>
	       			</ul>
       			</sec:authorize>
       									
		    </div>
		    
		</div>
	</nav>

</div>
