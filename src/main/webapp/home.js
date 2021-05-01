
 

var form = document.getElementById("loginform");
form.addEventListener('submit', event => {
    // submit event detected
    console.log("-_-_-_-_-_-_-_-_-_-_");
    console.log(" LOGIN FORM SUBMIT")
    console.log("-_-_-_-_-_-_-_-_-_-_");
    event.preventDefault();
    
    handleGoToLogInPageButtonClick();
})

function handleGoToLogInPageButtonClick() {

	console.log(" button clicked 21!");
	let baseURL = "http://localhost:9500/ERS/util/changeloginstatus";

    document.getElementById("loginMessage").style.visibility = "visible"
    document.getElementById("loginMessage").innerHTML = "Logging in...";
    document.getElementById("loginMessage").style.color = "rgb(0, 100, 4)";

    let xhttp = new XMLHttpRequest();

    var tryUsername = document.getElementById("login-username").value;
    var tryPassword = document.getElementById("login-password").value;

    console.log("tryUsername = "+tryUsername);
    console.log("tryUsername = "+tryPassword);

    xhttp.onreadystatechange = function(){
        
        if(xhttp.readyState == 4 && xhttp.status == 200 || xhttp.status == 213){ //checking that process is done and process was successful
            
            if (xhttp.status == 213) {
                // Couldn't log in
                document.getElementById("loginMessage").style.visibility = "visible"
                document.getElementById("loginMessage").innerHTML = "Incorrect username or password";
                document.getElementById("loginMessage").style.color = "rgb(240,2,15)";
            }
            if (xhttp.status == 200) {
                // Could log in
                window.location.href="http://localhost:9500/ERS/site/UserHome";
            }
            
        }
    }

    xhttp.open("POST",baseURL);
    xhttp.setRequestHeader("username",tryUsername);
    xhttp.setRequestHeader("password",tryPassword);
    xhttp.send();
	
}

