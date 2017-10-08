function loadBundles() {
	/*
	jQuery.i18n.properties({
        name: 'messages', 
        callback: function(){ alert( "library" ); }
    });
    */
	jQuery.i18n.properties({

		name:'messages', 
		path:'../resources/',
		mode:'both',
		language:lang,
		callback: function(){
		    console.log(jQuery.i18n.prop('check_receiver'))
		}
		});
}
