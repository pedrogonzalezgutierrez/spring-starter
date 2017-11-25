<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html lang="en">
    <head>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	
    	<title><spring:message code="application.name"/></title>
    	
		<!-- Normalize CSS -->
		<!-- Makes browsers render all elements more consistently and in line with modern standards.
			It precisely targets only the styles that need normalizing -->
		<spring:url value="/resources/css/normalize.css" var="normalize_css_url" />
		<link href="${normalize_css_url}" type="text/css" rel="stylesheet" />

		<!-- appMain CSS -->
		<spring:url value="/resources/css/appmain.css" var="appmain_css_url" />
		<link href="${appmain_css_url}" type="text/css" rel="stylesheet" />
		
		<!-- Bootstrap CSS -->
		<spring:url value="/resources/css/bootstrap.min.css" var="bootstrap_css_url" />
		<link href="${bootstrap_css_url}" type="text/css" rel="stylesheet" />
		
		<!-- Font Awesome CSS -->
		<spring:url value="/resources/css/font-awesome.min.css" var="font_awesome_css_url" />
		<link href="${font_awesome_css_url}" type="text/css" rel="stylesheet" />
		
		<!-- flag-icon-css -->
		<spring:url value="/resources/css/flag-icon.min.css" var="flag_icon_css_url" />
		<link href="${flag_icon_css_url}" type="text/css" rel="stylesheet" />
		
		<!-- Attribute for add specific css to a child page -->
		<tiles:insertAttribute name="css" ignore="true" />
				
	</head>
	
	<body>
	    <div id="webapp">
	    
	        <tiles:insertAttribute name="header" />
            <tiles:insertAttribute name="menu" />
            
	        <div id="body" class="container">
				<div class="panel panel-default">
					<div class="panel-body">
			            <tiles:insertAttribute name="body" />
					</div>
				</div>	        
	        </div>
	        
	        <tiles:insertAttribute name="footer" />
	    </div>
	    
		<!-- JQuery -->
		<spring:url value="/resources/js/jquery-3.2.1.min.js" var="jquery_js_url" />
		<script src="${jquery_js_url}" type="text/javascript"><!-- required for FF3 and Opera --></script>
		
		<!-- Bootstrap JS -->
		<spring:url value="/resources/js/bootstrap.min.js" var="bootstrap_js_url" />
		<script src="${bootstrap_js_url}" type="text/javascript"><!-- required for FF3 and Opera --></script>
		
		<!-- App Main JS -->
		<spring:url value="/resources/js/appmain.js" var="appmain_js_url" />
		<script src="${appmain_js_url}" type="text/javascript"><!-- required for FF3 and Opera --></script>
		
		<!-- Attribute for add specific javascript to a child page -->
		<tiles:insertAttribute name="js" ignore="true" />
		
	</body>
	
</html>