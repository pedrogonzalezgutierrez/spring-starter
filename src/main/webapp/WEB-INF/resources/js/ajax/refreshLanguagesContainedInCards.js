/**
 * This is executed when the page loads 
 * Refresh the languages that contains at least one card's translation
 * @param idLibrary
 */
function refreshLanguagesContainedInCards(idLibrary) {
	
	ajaxLanguagesContainedInCards(idLibrary)
	
		// Success
		.done(function(data) {
			processDoneLanguagesContainedInCards(data, idLibrary);
			
		// Error
		}).fail(function() {
			showErrorArea("LanguagesContainedInCards");
	});	
	
}

/**
 * This is executed when in the modal windows the user clicks in a Language
 * Refresh the languages the contains at least one translation of a card and then add a new language
 * @param idLibrary
 * @param idLanguage
 * @param flagCode
 */
function refreshLanguagesContainedInCardsAndAddLanguage(idLibrary, idLanguage, flagCode) {
	
	ajaxLanguagesContainedInCards(idLibrary)
	
		// Success
		.done(function(data) {
			processDoneLanguagesContainedInCards(data, idLibrary);
			renderLanguageContainedInCard(idLanguage, flagCode, idLibrary);
			$('#modalLanguagesNotContainedInCards').modal('hide');
			refreshLanguage(idLibrary, idLanguage);
			
		// Error
		}).fail(function() {
			showErrorArea("LanguagesContainedInCards");
		});	

}

/**
 * Clear the div and iterate over the languages for rendering 
 * @param data
 * @param idLibrary
 */
function processDoneLanguagesContainedInCards(data, idLibrary) {
	$("#ajaxLanguagesContainedInCards").empty();
	
	$.each(data, function(i, obj) {
		renderLanguageContainedInCard(obj.id, obj.flagCode, idLibrary);
	});
	showAjaxArea("LanguagesContainedInCards");
}

/**
 * Render a panel in the ajax area
 * @param idLanguage
 * @param flagCode
 * @param idLibrary
 */
function renderLanguageContainedInCard(idLanguage, flagCode, idLibrary) {
	$("#ajaxLanguagesContainedInCards").append(
		"<div class='col-xs-4' onclick=\x22refreshLanguage('"+idLibrary+"','"+idLanguage+"')\x22>" +
			"<div id='languageContainedInCard_"+idLanguage+"'class='languageContainedInCard panel panel-default'>" +
				"<div class='panel-body text-center' style='background:#E0E0E0;'><span class='flag-icon flag-icon-"+flagCode+"'></span></div>" +
			"</div>" +
		"</div>"
	);
}

/**
 * Core ajax call for LanguagesContainedInCards
 * @param idLibrary
 */
function ajaxLanguagesContainedInCards(idLibrary) {
	showLoadingArea("LanguagesContainedInCards");
	
	var ajaxURL="/library/languagescontainedincards/"+idLibrary;
	
	return asyncAjax(ajaxURL, "GET");
}