/**
 * Publish a Library. The backend will check if it is valid and will respond with a custom object
 */
function publishLibrary(idLibrary) {

    asyncAjax("/library/publish/" + idLibrary, "GET")

    // Success
        .done(function (data) {
            $("#loadingPublishing").empty();
            if (data.success == true) {
                $('#success').css("display", "block");
            } else {
                $.each(data.codeErrors, function (indexCode, codeError) {
                    if (codeError === "error1") {
                        $("#error1").css("display", "block");
                    } else if (codeError === "error2") {
                        $("#error2").css("display", "block");
                    } else if (codeError === "error3") {
                        $("#error3").css("display", "block");
                    } else if (codeError === "error4") {
                        $("#error4").css("display", "block");
                    } else if (codeError === "error5") {
                        $("#error5").css("display", "block");
                    }

                });

            }

            // Error
        }).fail(function () {
        $("#loadingPublishing").empty();
        $('#error').css("display", "block");
    });

}

/**
 * Publish a Game. The backend will check if it is valid and will respond with a custom object
 */
function publishGame(idLibrary) {

    asyncAjax("/game/publish/" + idLibrary, "GET")

        // Success
        .done(function (data) {
            $("#loadingPublishing").empty();
            if (data.success == true) {
                $('#success').css("display", "block");
            } else {
                $.each(data.codeErrors, function (indexCode, codeError) {
					$("#publishErrors").append("<div class='alert alert-danger' role='alert'>"+codeError+"</div><br/>");
                });

            }

        // Error
        }).fail(function () {
            $("#loadingPublishing").empty();
            $('#error').css("display", "block");
        });

}