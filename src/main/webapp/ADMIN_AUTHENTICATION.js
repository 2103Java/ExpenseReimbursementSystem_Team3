

    // REDIRECT TO LOGIN IF LOG-IN SESSION DOES NOT EXIST

	console.log("LOGGED_IN_AUTHORIZATION");
   
    let sessionURL = "http://localhost:9500/ERS/util/session";
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function(){
        
        if(xhttp.readyState == 4 && xhttp.status == 200){ //checking that process is done and process was successful
            // REDIRECT TO LOG IN IF NOT LOGGED IN
            
            let accessLevel = xhttp.responseText;
            console.log("Access level: "+accessLevel);
            if (accessLevel != "admin") {
                location.href = "http://localhost:9500/ERS/site/UserHome";                
            }
        }
    }

    xhttp.open("GET",sessionURL + "?mode=getAccessLevel",false);
    xhttp.send();



