

function enableBackButton() {
	$('#backbutton').click(function(){
		parent.history.back();
	    return false;
	});
}