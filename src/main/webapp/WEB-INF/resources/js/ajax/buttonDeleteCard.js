/**
 * This executes when the user clicks in "Delete Card"
 * It opens a modal window  
 */
function buttonDeleteCard() {
	$("#modalDeleteCard").modal('show');
}
	
/**
 * Executed when the user clicks in "Yes" in the modal of Delete Card 
 * Create a new card
 * 
 * @param idLibrary
 * @param idLanguage
 */
function deleteCard(idCard, idLibrary, idLanguage) {
	showLoadingArea("DeleteCard");
	
	var URL="/library/deletecard/"+idCard;

	asyncAjax(URL, "GET")
		// Success
		.done(function(data) {
			$("#ajaxCardTranslation").empty();
			showAjaxArea("DeleteCard");
			$("#buttonRemoveCard").prop("onclick",null).off("click");
			$("#buttonRemoveCard").addClass("disabled");
			refreshLanguage(idLibrary, idLanguage);
			$("#modalDeleteCard").modal('hide');
			
		// Error
		}).fail(function() {
			showErrorArea("DeleteCard");
		});	
	
}

/**
 * This executes when the user clicks in "Delete Icon Card"
 * It opens a modal window  
 * @param idLibrary
 */
function buttonDeleteCardIcon(idCard, idLibrary, idLanguage) {
	// Update button "Yes"
	$("#buttonRemoveCardIconConfirmation").prop("onclick",null).off("click");
	$("#buttonRemoveCardIconConfirmation").attr("onclick","deleteIconCard('"+idCard+"', '"+idLibrary+"', '"+idLanguage+"')");
	$('#modalDeleteIconCard').modal('show');
}

/**
 * Executed when the user clicks in "Delete Icon Card" 
 * Create a new card
 * 
 * @param idLibrary
 * @param idLanguage
 */
function deleteIconCard(idCard, idLibrary, idLanguage) {
	var URL="/library/deleteicon/"+idCard;

	asyncAjax(URL, "GET")
		// Success
		.done(function(data) {
			$("#modalDeleteIconCard").modal('hide');
			updateCardTranslation(idLibrary, idCard, idLanguage);
			
		// Error
		}).fail(function() {
			showErrorArea("DeleteCard");
		});	
}