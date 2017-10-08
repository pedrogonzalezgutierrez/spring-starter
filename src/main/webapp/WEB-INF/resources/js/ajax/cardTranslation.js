/**
 * Executed when the user clicks in a card of the list
 * @param idCard
 * @param idLanguage
 */
function updateCardTranslation(idLibrary, idCard, idLanguage) {
	showLoadingArea("CardTranslation");
	
	asyncAjax("/library/updatecardtranslation/"+idCard+"/"+idLanguage, "GET")
	
		// Success
		.done(function(data) {
			$("#ajaxCardTranslation").empty();
			renderCardTranslation(data, idLibrary, idLanguage, idCard);
			// Show ajax area
			showAjaxArea("CardTranslation");
			
		// Error
		}).fail(function() {
			showErrorArea("CardTranslation");
			
		});	
	
}

/**
 * Render a card
 * @param data
 * @param idLibrary
 * @param idLanguage
 * @param idCard
 */
function renderCardTranslation(data, idLibrary, idLanguage, idCard) {
	renderHelpText();
	
	renderInput("TextTopLeft", "Text Top Left", "Type the text in your language", data.textTopLeft, 60);
	renderInput("TextTopRight", "Text Top Right", "Type the text in your language", data.textTopRight, 60);
	
	renderIcon(idCard, "Image", idLibrary, idLanguage);
	
	renderInput("TextMediumLeft", "Text Medium Left", "Type the text in your language", data.textMediumLeft, 60);
	renderInput("TextMediumRight", "Text Medium Right", "Type the text in your language", data.textMediumRight, 60);
	
	renderInput("Description", "Description", "Type the text in your language", data.description, 2000);
	
	renderInput("TextBottomLeft", "Text Bottom Left", "Type the text in your language", data.textBottomLeft, 60);
	renderInput("TextBottomRight", "Text Bottom Right", "Type the text in your language", data.textBottomRight, 60);
	
	renderSubmitButton(idLibrary, idLanguage, idCard);
	
}

/**
 * Render the Title or Help Text
 */
function renderHelpText() {
	$("#ajaxCardTranslation").append(
		"<div class='col-xs-12'>" +
			"<h3 class='text-center'>Edit Translation</h3>" +
			"<br/>" +
		"</div>"
	);
}

/**
 * It will render a string input
 * @param id
 * @param title
 * @param placeHolder
 * @param value
 */
function renderInput(id, title, placeHolder, value, maxLength) {
	if( value == null ) {
		value="";
	} else {
		value="value='"+value+"'";
	}
	$("#ajaxCardTranslation").append(
		"<div class='col-xs-12'>" +
			"<div class='form-group'>" +
				"<label for='input_"+id+"'>"+title+"</label>" +
				"<input id='input_"+id+"' "+value+" type='text' maxlength='"+maxLength+"' class='form-control' placeholder='"+placeHolder+"'>" +
			"</div>" +
		"</div>"
	);
}

/**
 * Render the icon of the card
 * It will trigger an ajax call for retrieve it
 * @param id
 * @param title
 */
function renderIcon(idCard, title, idLibrary, idLanguage) {
	$("#ajaxCardTranslation").append(
		"<div class='col-xs-12'>" +
			"<div class='panel panel-default'>" +
				"<div class='panel-body'>" +
					"<p><strong>"+title+"</strong></p>" +
					"<div id='ajaxCardIcon'>" +
						"<img id='cardIcon' src='2324' onerror=\x22getCardIcon('"+idCard+"', '"+idLibrary+"', '"+idLanguage+"')\x22 class='center-block img-responsive' alt='Loading'>" +
					"</div>" +
					"<div id='ajaxcardIconError505' class='alert alert-danger' role='alert' style='display:none;'>" +
						"<p class='text-center alert-link'>Internal error</p>" +
						"<button onclick=\x22getCardIcon('"+idCard+"', '"+idLibrary+"', '"+idLanguage+"')\x22class='btn btn-default'>Try Again</button>" +
					"</div>" +
					"<div id='ajaxError400' class='alert alert-danger' role='alert' style='display:none;'>" +
						"<p class='text-center alert-link'>Internal error</p>" +
					"</div>" +
				"</div>" +
			"</div>" +
		"</div>"
	);
}

function getCardIcon(idCard, idLibrary, idLanguage) {
	$("#cardIcon").attr("src",domainPrefix() + "/resources/images/loading.gif");
	
	var token = $("input[name='_csrf']").val();
	
	asyncAjax("/library/iconcard/"+idCard, "GET")
		// Success
		.done(function(data) {
			// Image retrieved successfully, set it in the <img src> and show a delete icon card button
			$("#ajaxcardIconError505").hide();
			$("#ajaxCardIcon").show();
			$("#ajaxCardIcon").append(
				"<br/>" +
				"<a onclick=\x22buttonDeleteCardIcon('"+idCard+"', '"+idLibrary+"', '"+idLanguage+"')\x22 type='button' class='btn btn-danger center-block'>" +
					"<i class='fa fa-times'></i>" +
				"</a>");
			
			$("#cardIcon").prop("onerror",null).off("click");
			$("#cardIcon").attr("src","data:image/png;base64,"+data);
			
		// Error
		}).fail(function(jqXHR, textStatus, errorThrown) {
			if( jqXHR.status == 404 ) {
				// There is no icon, show the form in order to upload a new one
				$("#ajaxCardIcon").show();
				$("#ajaxcardIconError505").hide();
				
				$("#cardIcon").prop("onerror",null).off("click");
				$("#ajaxCardIcon").empty();
				$("#ajaxCardIcon").append(
					"<form id='cardIconForm' method='POST' enctype='multipart/form-data' action='"+domainPrefix()+"/library/uploadcardicon?_csrf="+token+"'>" +
						"<div class='form-group'>" +
							"<label for='inputFile'>Select a file</label>" +
							"<input type='file' name='file' id='inputFile'/>" +
							"<p class='help-block'>PNG, JPG, JPEG</p>" +
							"<input type='submit' value='Upload'/>" +
						"</div>" +
					"</form>");
				$('#cardIconForm').on('submit', function(e) {
			        e.preventDefault();
			        formUploadCardIcon(idLibrary, idCard, idLanguage);
			    });
			} else if( jqXHR.status == 500 ) {
				// Error retrieving image. Try again
				$("#ajaxcardIconError505").show();
				$("#ajaxCardIcon").hide();
			}
		});
}

function formUploadCardIcon(idLibrary, idCard, idLanguage) {
	
	var form = $('#cardIconForm');
	var formData = new FormData(form);
	formData.append('file', document.getElementById('inputFile').files[0]); 
	
	var token = $("input[name='_csrf']").val();
	$.ajax({
	    url: domainPrefix() + "/library/uploadcardicon?_csrf="+token,
	    type: "POST",
	    data: formData,
	    // THIS MUST BE DONE FOR FILE UPLOADING
	    contentType: false,
	    processData: false,
	}).done(function(data) {
		updateCardTranslation(idLibrary, idCard, idLanguage);
	
	}).fail(function(jqXHR, textStatus, errorThrown) {
		$("#ajaxError400").show();
	
	});
}


/**
 * Render the submit button
 * It will set the onclick for calling persistCardTranslation
 * @param idLibrary
 * @param idLanguage
 */
function renderSubmitButton(idLibrary, idLanguage, idCard) {
	$("#ajaxCardTranslation").append(
		"<div class='col-xs-12'>" +
			"<input onclick=\x22persistCardTranslation('"+idLibrary+"', '"+idLanguage+"', '"+idCard+"')\x22 value='Save' class='btn btn-success btn-lg btn-block' />" +
		"</div>"
	);
}

/**
 * Executed when the user clicks in the button "Save" when editing a translation
 * Save the translation in the DB
 * @param idLibrary
 * @param idLanguage
 */
function persistCardTranslation(idLibrary, idLanguage, idCard) {
	showLoadingArea("CardTranslation");
	
    var json = {
    	"textTopLeft" : $('#input_TextTopLeft').val(),
    	"textTopRight" : $('#input_TextTopRight').val(),
    	"textMediumLeft" : $('#input_TextMediumLeft').val(),
    	"textMediumRight" : $('#input_TextMediumRight').val(),
    	"description" : $('#input_Description').val(),
    	"textBottomLeft" : $('#input_TextBottomLeft').val(),
    	"textBottomRight" : $('#input_TextBottomRight').val()
    };
	
	$.ajax({
		url: domainPrefix() + "/library/updatecardtranslation",
		type: "GET",
		data: json,
		async: true
	})
		// Success
		.done(function(data) {
			showAjaxArea("CardTranslation");
			refreshLanguage(idLibrary, idLanguage);
			updateCardTranslation(idLibrary, idCard, idLanguage);
			
		// Error
		}).fail(function() {
			showAjaxAndErrorArea("CardTranslation");
	});	
    
}
