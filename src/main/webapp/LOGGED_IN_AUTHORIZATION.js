

    // REDIRECT TO LOGIN IF LOG-IN SESSION DOES NOT EXIST

	console.log("LOGGED_IN_AUTHORIZATION");
   
    let sessionURL = "http://localhost:9500/ERS/util/session";
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function(){
        
        if(xhttp.readyState == 4 && xhttp.status == 401){ //checking that process is done and process was successful
            // REDIRECT TO LOG IN IF NOT LOGGED IN
            location.href = "http://localhost:9500/ERS/site/Home";            
        }
    }

    xhttp.open("GET",sessionURL + "?mode=checkIfSessionExists",false);
    xhttp.send();



