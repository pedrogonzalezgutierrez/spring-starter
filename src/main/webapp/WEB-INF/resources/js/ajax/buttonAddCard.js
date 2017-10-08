/**
 * Executed when the user clicks in "New Card"
 * Create a new card
 * 
 * @param typeLibrary
 * @param idLanguage
 */
function createCardAndRefreshLanguage(idLibrary, idLanguage) {
	showLoadingArea("ListCards");
	
	var URL="/library/createcard/"+idLibrary;

	asyncAjax(URL, "GET")
		
		// Success
		.done(function(data) {
			// The card was created, just refresh the language
			refreshLanguage(idLibrary, idLanguage);
			
		// Error
		}).fail(function() {
			showErrorArea("ListCards");
		});	
	
}