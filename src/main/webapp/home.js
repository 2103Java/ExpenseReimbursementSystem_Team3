
 

function handleGoToLogInPageButtonClick() {

	console.log(" button clicked 21!");
	 // let baseURL = "http://localhost:9500/Money_Please/site/HomeLogIn";
    let baseURL = "http://localhost:80/Money_Please/site/HomeLogIn";
    let xhttp = new XMLHttpRequest();


    xhttp.onreadystatechange = function(){
        /**
         *  0 - request not initalized  1 - server connection established   2 - request received  3 - processing request   4 - request is finished and response is ready
         */

        console.log("Changing my readystate " + xhttp.readyState);
        
        console.log(xhttp.statusText);
        if(xhttp.readyState == 4 && xhttp.status == 200){ //checking that process is done and process was successful
        	console.log("readyState 4 && status 200");
            
            let responseHeaders = xhttp.getAllResponseHeaders();
            
            let aResponse = xhttp.responseText;
            console.log(aResponse);
            
            //console.log("response head = "+responseHeaders);

			
			// Convert the HTML string into a document object
			var parser = new DOMParser();
			var doc = parser.parseFromString(aResponse, 'text/html');
			console.log("doc = "+doc);
			//let loginHeaderElement = doc.getElementById("goToLogInPage_Button");
            console.log("Login h1 says: "+doc.getElementById("loginHeader").innerHTML);
			
			
            //document.title = doc.title;
			//document.body = doc.body;
			//document.head.innerHTML = doc.head.innerHTML;

            document.open()
            document.write(aResponse);
            document.close();
            
            
            
            //window.location.href = baseURL;
            
            
            
        }
    }

    xhttp.open("GET",baseURL);
    xhttp.send();
	
}

