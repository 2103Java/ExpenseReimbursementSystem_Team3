

window.onload = function() {

    // Uses AJAX

	console.log("USER HOME LOADED");
    /**
     * Get the username from the current HTTP Session & display it as "Welcome, username!" on the userhome.html page
     */
    let sessionURL = "http://localhost:9500/Money_Please/api/session";
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function(){
        /**
         *  0 - request not initalized  1 - server connection established   2 - request received  3 - processing request   4 - request is finished and response is ready
         */
        
        if(xhttp.readyState == 4 && xhttp.status == 200){ //checking that process is done and process was successful            
            let usernameToDisplay = JSON.parse(xhttp.responseText);
            console.log(usernameToDisplay);
            document.getElementById("mainGreeting").innerHTML = "Hello, "+usernameToDisplay+"!";
        }
    }


    xhttp.open("GET",sessionURL);
    xhttp.send();

}


