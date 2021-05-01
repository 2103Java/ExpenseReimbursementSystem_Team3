console.log("REGISTER.JS START");

function registerNewUser() {

    console.log("REGISTER BUTTON CLICKED")

    // Using AJAX for new User registration
    let registerURL = "http://localhost:9500/ERS/api/user"

    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {

        if (xhttp.readyState == 4) {
            
            if (xhttp.status == 213) {
                // Could not register (username taken)
                document.getElementById("registerStatusMessage").style.visibility = "visible";
                document.getElementById("registerStatusMessage").style.color="rgb(220,0,10)";
                document.getElementById("registerStatusMessage").innerHTML = "Username is already in use.";
                

            }
            else if (xhttp.status == 217) {
                // Could not register (Incorrect access code)
                document.getElementById("registerStatusMessage").style.visibility = "visible";
                document.getElementById("registerStatusMessage").style.color="rgb(220,0,10)";
                document.getElementById("registerStatusMessage").innerHTML = "Please enter a correct access code.";

            }
            else if (xhttp.status == 200) {
                document.getElementById("registerStatusMessage").style.visibility = "visible";
                document.getElementById("registerStatusMessage").style.color="rgb(0,185,5)";
                document.getElementById("registerStatusMessage").innerHTML = "REGISTRATION SUCCESSFUL!.";
                document.getElementById("registerStatusMessage").style.fontSize="25px";
                document.getElementById("registerStatusMessage").style.fontWeight="bold";

                window.setTimeout(function() {
                    window.location.href = "http://localhost:9500/ERS/site/Home";
                },
                1500);
                
            }

        }
    }

    let tryUsername = document.getElementById("usernameID").value;
    let tryPassword = document.getElementById("passwordID").value;
    let tryFirstName = document.getElementById("firstNameID").value;
    let tryLastName = document.getElementById("lastNameID").value;
    //let tryDOB = document.getElementById("dOBID").value;
    let tryAccessCode = document.getElementById("accessCodeID").value;
    
    if (tryUsername == "" || tryPassword == "" || tryFirstName == "" || tryLastName == "" || tryAccessCode == "") {
                document.getElementById("registerStatusMessage").style.visibility = "visible";
                document.getElementById("registerStatusMessage").style.color="rgb(220,0,10)";
                document.getElementById("registerStatusMessage").innerHTML = "Do not leave any fields blank.";
                return;
    }
    

    xhttp.open("POST",registerURL);
    xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    var params = 'username=' + tryUsername + '&password=' + tryPassword + '&firstName=' + tryFirstName + 
                '&lastName=' + tryLastName + '&accessCode=' +tryAccessCode;


    xhttp.send(params);

    document.getElementById("registerStatusMessage").style.visibility = "visible";
    document.getElementById("registerStatusMessage").style.color="rgb(170,4,180)";
    document.getElementById("registerStatusMessage").innerHTML = "Attempting to register...";
}