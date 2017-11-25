function domainPrefix() {
	// For example: /mvc
	var domainPrefix="";
	return domainPrefix;
}

function staticResourcesURL() {
	var staticResourcesURL="http://dev-static-content.s3-website-eu-west-1.amazonaws.com";
	return domainPrefix;
}

/**
 * Core ajax 
 * @param ajaxURL
 * @param typeRequest
 */
function asyncAjax(ajaxURL, typeRequest) {
	
	return $.ajax({
			url: domainPrefix() + ajaxURL,
			type: typeRequest,
			async: true
	});
	
}
/*

$.ajax({
  statusCode: {
    404: function() {
      alert( "page not found" );
    }
  }
});
https://bugs.jquery.com/ticket/14011
*/
// http://stackoverflow.com/questions/14220321/how-do-i-return-the-response-from-an-asynchronous-call

/**
 * Show ajax area, hide the rest
 * @param divName
 */
function showAjaxArea(divName) {
	$("#ajax"+divName).show();
	$("#error"+divName).hide();
	$("#empty"+divName).hide();
	$("#loading"+divName).hide();
}

/**
 * Show error area, hide the rest
 * @param divName
 */
function showErrorArea(divName) {
	$("#ajax"+divName).hide();
	$("#error"+divName).show();
	$("#empty"+divName).hide();
	$("#loading"+divName).hide();
}

/**
 * Show empty (no items) area, hide the rest
 * @param divName
 */
function showEmptyArea(divName) {
	$("#ajax"+divName).hide();
	$("#error"+divName).hide();
	$("#empty"+divName).show();
	$("#loading"+divName).hide();
	
}

/**
 * Show loading area, hide the rest
 * @param divName
 */
function showLoadingArea(divName) {
	$("#ajax"+divName).hide();
	$("#error"+divName).hide();
	$("#empty"+divName).hide();
	$("#loading"+divName).show();
}

/**
 * Show ajax and error area, hide the rest
 * @param divName
 */
function showAjaxAndErrorArea(divName) {
	$("#ajax"+divName).show();
	$("#error"+divName).show();
	$("#empty"+divName).hide();
	$("#loading"+divName).hide();
}

function setLanguageContainedInCardEnabled(idLanguage) {
	$('.languageContainedInCard').each(function() {
		var idLanguagePanel = this.id.split('_')[1];
		if( idLanguagePanel == idLanguage ) {
			$(this).removeClass("panel-default");
			$(this).addClass("panel-primary");
		} else {
			$(this).removeClass("panel-primary");
			$(this).addClass("panel-default");
		}
	});		
}