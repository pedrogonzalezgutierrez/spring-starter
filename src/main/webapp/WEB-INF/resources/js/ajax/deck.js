function addCards(idDeck) {
	var idSelector = function() { return this.id; };
	var idsChecked = $(".libraryCards:checkbox:checked").map(idSelector).get() ;
	
	var URL=domainPrefix()+"/library/publicdeck/add/"+idDeck+"/"+idsChecked;
	
	window.location.href = URL;
	
}

function detachCards(idDeck) {
	var idSelector = function() { return this.id; };
	var idsChecked = $(".deckCards:checkbox:checked").map(idSelector).get() ;
	
	var URL=domainPrefix()+"/library/publicdeck/detach/"+idDeck+"/"+idsChecked;
	
	window.location.href = URL;
	
}