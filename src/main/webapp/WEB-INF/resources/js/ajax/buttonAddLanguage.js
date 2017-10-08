/**
 * This executes when the user clicks in "Add Language"
 * It opens a modal window and retrieves the languages that doesnt contain any card's translation
 * If the user click in a language then it will call to the function refreshLanguagesContainedInCardsAndAddLanguage(....)  
 * @param idLibrary
 */
function buttonAddLanguage(idLibrary) {
	showLoadingArea("LanguagesNotContainedInCards");
	$('#modalLanguagesNotContainedInCards').modal('show');
	
	var URL="/library/languagesnotcontainedincards/"+idLibrary;
	
	asyncAjax(URL, "GET")
	
		// Success
		.done(function(data) {
			$("#ajaxLanguagesNotContainedInCards").empty();
			
			if( jQuery.isEmptyObject(data) ) {
				showEmptyArea("LanguagesNotContainedInCards");
			} else {
				$.each(data, function(i, obj) {
					renderLanguageNotContainedInCard(obj.id, obj.languageName, obj.flagCode, idLibrary);
				});
				showAjaxArea("LanguagesNotContainedInCards");
			}
			
		// Error
		}).fail(function() {
			showErrorArea("LanguagesNotContainedInCards");
	});	
	
}

/**
 * Render a panel in the ajax area
 * @param idLanguage
 * @param nameLanguage
 * @param flagCode
 * @param idLibrary
 */
function renderLanguageNotContainedInCard(idLanguage, nameLanguage, flagCode, idLibrary) {
	$("#ajaxLanguagesNotContainedInCards").append(
		"<div class='col-xs-6 col-md-3'>" +
			"<div class='panel panel-default' onclick=\x22refreshLanguagesContainedInCardsAndAddLanguage('"+idLibrary+"','"+idLanguage+"','"+flagCode+"')\x22>" +
				"<div class='panel-body'>" +
					"<div class='row'>" +
						"<div class='col-xs-12 text-center'><span class='flag-icon flag-icon-"+flagCode+"'></span></div>" +
						"<div class='col-xs-12 text-center'>"+nameLanguage+"</div>" +
					"</div>" +
				"</div>" +
			"</div>" +
		"</div>");
}