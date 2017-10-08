/**
 * Executed when the user clicks in a language
 * It will retrieve the cards with only the translations of that language  
 * @param idLibrary
 * @param idLanguage
 */
function refreshLanguage(idLibrary, idLanguage) {
	showLoadingArea("ListCards");
	
	var URL="/library/librarybylanguage/"+idLibrary+"/"+idLanguage;
	
	asyncAjax(URL, "GET")
	
		// Success
		.done(function(data) {
			$("#ajaxListCards").empty();
			$("#ajaxListCards").removeClass("row");
			$("#ajaxListCards").append(
					"<table id='filterTablePagination' class='table table-striped table-bordered' cellspacing='0' width='100%'>" +
						"<thead>" +
							"<tr>" +
								"<th>ID</th>" +
								"<th>Name</th>" +
							"</tr>" +
						"</thead>" +
						"<tbody id='tableListCards'></tbody>" +
					"</table>");
			
			$.each(data.cards, function(indexCard, card) {
				renderCard(data.id, card.id, idLanguage, indexCard+1, card.translations);
			});
			// Activate table
			$('#filterTablePagination').dataTable({
				"dom": "<'row'<'col-sm-12'f>>" + "<'row'<'col-sm-12't>>" + "<'row'<'col-sm-12'p>>"
			});			
			// Set this language enabled
			setLanguageContainedInCardEnabled(idLanguage)
			
			// Update button "New Card"
			$("#buttonAddCard").prop("onclick",null).off("click");
			$("#buttonAddCard").attr("onclick","createCardAndRefreshLanguage('"+idLibrary+"', '"+idLanguage+"')");
			$("#buttonAddCard").removeClass("disabled");
			
			// Show ajax area
			showAjaxArea("ListCards");
			
		// Error
		}).fail(function() {
			showErrorArea("ListCards");
			
		});	

}

/**
 * Set the language enabled (add a blue rectangle)
 * @param idLanguage
 */
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

/**
 * Render a panel in the ajax area
 * 
 * @param idLibrary
 * @param idCard
 * @param idLanguage
 * @param textTopLeft
 */
function renderCard(idLibrary, idCard, idLanguage, counter, translations) {
	if( jQuery.isEmptyObject(translations) ) {
		// This card doesnt contain a translation
		textTopLeft="<p style='color:red;'>No translation <i class='fa fa-exclamation-triangle' aria-hidden='true'></i></p>";
	} else {
		// There is only a Translation in the List, thats why I am iterating
		$.each(translations, function(indexTranslation, translation) {
			textTopLeft=translation.textTopLeft;
		});
	}
	$("#tableListCards").append(
			"<tr id='card_"+counter+"' onclick=\x22focusCard(this.id, '"+idCard+"', '"+idLibrary+"', '"+idLanguage+"');updateCardTranslation('"+idLibrary+"','"+idCard+"','"+idLanguage+"')\x22>" +
				"<td>"+counter+"</td>" +
				"<td>"+textTopLeft+"</td>" +
			"</tr>");
	
}

/**
 * 
 * @param clicked_id
 */
function focusCard(clicked_id, idCard, idLibrary, idLanguage) {
	// Unfocus everything
	$('tr[id^="card_"]').each(function () {
		$("#"+this.id).removeClass("warning");
	});	
	
	// Focus clicked one
	$("#"+clicked_id).addClass("warning");
	
	// Enable Delete Card button
	$("#buttonRemoveCardConfirmation").prop("onclick",null).off("click");
	$("#buttonRemoveCardConfirmation").attr("onclick","deleteCard('"+idCard+"', '"+idLibrary+"', '"+idLanguage+"')");
	$("#buttonRemoveCard").attr("onclick","buttonDeleteCard()");
	$("#buttonRemoveCard").removeClass("disabled");

}


