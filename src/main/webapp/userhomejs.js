console.log("IN USERHOME.JS FILE");



var ticketList = [];

var ticketModal = document.getElementById("submit_form_modal");
console.log("ticketModal = "+ticketModal);
ticketModal.addEventListener('show.bs.modal',
function(event) {
    console.log("MODAL TICKET BOX SHOWN");
    var ticketDescriptionE = document.querySelector("#submit_form_modal #description_text");
    
    console.log("ticketDescriptionE = "+ticketDescriptionE);
}
)


function submitTicket() {
    console.log("CLICKED SUBMIT TICKET");
    
    /**
     * Using AJAX to create a new ticket
     */
    
    let newTicketURL = "http://localhost:9500/ERS/api/ticket";
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {

        if (xhttp.readyState == 4 & xhttp.status == 200) {

            // Fetch tickets for this user and redraw the table
            fetchAllTicketsForUsername(window.localStorage.getItem("username"));
            
        }

    }

    xhttp.open("POST",newTicketURL);
    
    /** *
     * Get the new Ticket attributes from the modal form
     *  */ 
    var inputBoxElement = document.querySelector("#submit_form_modal #select_box");
    var amountElement = document.querySelector("#submit_form_modal #reimburse_amount");
    var descriptionElement = document.querySelector("#submit_form_modal #description_text");

    console.log("inputBoxElement = "+inputBoxElement);
    console.log("amountElement = "+amountElement);
    console.log("descriptionElement = "+descriptionElement);
    console.log("type = "+inputBoxElement.value);
    console.log("amount = "+amountElement.value);
    console.log("description = "+descriptionElement.value);

    var params = 'mode=create&type='+inputBoxElement.value+'&amount='+amountElement.value+'&description='+descriptionElement.value;
    xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

    xhttp.send(params);

}


// Use AJAX to get Username for User logged in
window.addEventListener("load",
function() {
    var sessionURL = "http://localhost:9500/ERS/util/session";

    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {


        if (xhttp.readyState == 4 && xhttp.status == 200) {

            var user = JSON.parse(xhttp.responseText);
            
            document.getElementById("mainGreeting").innerHTML = "Welcome, "+user.username+"!";

            let accessLevelToDisplay;
            if (user.accessLevel == "customer") {
                accessLevelToDisplay = "employee"
            }
            else if (user.accessLevel == "admin") {
                accessLevelToDisplay = "finance manager"
            }
            document.getElementById("accessLevelDisplayGreeting").innerHTML = "Access Level: " + accessLevelToDisplay;
            window.localStorage.setItem("accessLevel",user.accessLevel);
            window.localStorage.setItem("username",user.username);
            

            // Conditionally append a "view all tickets" list item if admin is logged in, then only one "user home page" is needed
            if (localStorage.getItem("accessLevel") == "admin") {
                var newLI = document.createElement("li");
                var newAnchor = document.createElement("a");
                newAnchor.className = "dropdown-item";
                newAnchor.setAttribute("href", "http://localhost:9500/ERS/site/AllTickets");
                newAnchor.id = "view_all_tickets";
                newAnchor.style = "visibility: visible;";
                newAnchor.innerHTML = "Approve/Deny Tickets (FINANCE MANAGER MODE)";
                newLI.appendChild(newAnchor);

                document.getElementById("view_tickets").insertAdjacentElement("afterend", newLI);
            }

            
                var currentPageURL = window.location.href;
                console.log(currentPageURL);
            
                fetchAllTicketsForUsername(window.localStorage.getItem("username"));

                document.getElementById("userCorner").innerHTML = window.localStorage.getItem("username");
                        



        }
    };

    xhttp.open("GET",sessionURL);
    xhttp.setRequestHeader("mode","getUserInfo");
    xhttp.send();
}
);



function logOut() {
    // Send a GET to this URL
    window.location.href = "http://localhost:9500/ERS/util/changeloginstatus";
  
}










// Table stuff

function fetchTicketByID(id) {

    let xhttp = new XMLHttpRequest()
    xhttp.onreadystatechange = function() {

        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let ticket = JSON.parse(xhttp.responseText);
            console.log(ticket);
            document.getElementById("tickets_table").style.visibility="visible";
            return ticket;
        }
    }

    let ticketURL = "http://localhost:9500/ERS/api/ticket";
	

    xhttp.open("GET",ticketURL);
    var params = 'mode=ticketid&ticketid='+id;
    xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhttp.send(params);
}




function fetchAllTicketsForUsername(username) {

    console.log("FETCH ALL TICKETS FOR A USER:");
    console.log("window.localStorage.getItem(username) = "+username);
    
    let xhttp = new XMLHttpRequest()

    xhttp.onreadystatechange = function() {

        if (xhttp.readyState == 4) {
            if (xhttp.status != 200) {
                console.log("RESPONSE RECEIEVED WITH STATUS "+xhttp.status);
            }
            else {
                console.log("ALL TICKETS FOR USERNAME RESPONSE RECEIVED!!!")
                let tickets = JSON.parse(xhttp.responseText);
                console.log(tickets);
                ticketList = tickets;
                generateTicketTable();
            }
            
        }
    }

    let ticketURL = "http://localhost:9500/ERS/api/ticket";
	

    
    var params = 'mode=username' + '&username='+username;
    xhttp.open("GET",ticketURL + "?" + params);
    xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhttp.send();
}






function generateTicketTable() {

    console.log("generateTickets START with ticketList.size = "+ticketList.length);
    
    sortTicketsByIDAscending();

    
    var newTBody = document.createElement('tbody');
    var oldTBody = document.getElementById("tickets_table").getElementsByTagName("tbody")[0];

    //console.log("oldTBody = "+oldTBody);
    
    
    var pendingEnabled = document.getElementById("showStatusPending").checked;
    var approvedEnabled = document.getElementById("showStatusApproved").checked;
    var deniedEnabled = document.getElementById("showStatusDenied").checked;

    if (ticketList.length == 0) {
        var noTicketsMessage = document.getElementById("noTicketsMessage");
        noTicketsMessage.style.visibility = "visible";
        noTicketsMessage.style.fontSize = "22px";
    } 
    else {
        var noTicketsMessage = document.getElementById("noTicketsMessage");
        noTicketsMessage.style.visibility = "hidden";
    }

    for (let k = 0; k < ticketList.length; k++) {
        
        
        var ticket = ticketList[k];

        if (
            (!pendingEnabled && ticket.status == "Pending") ||
            (!approvedEnabled && ticket.status == "Approved") ||
            (!deniedEnabled && ticket.status == "Denied") 
        )  {

            continue;
        }
    

        var newRow = document.createElement('tr');
        
        var cell1 = document.createElement('td');
        cell1.innerHTML = ticketList[k].id;
        newRow.appendChild(cell1);

        var cell1 = document.createElement('td');
        cell1.innerHTML = ticketList[k].type;
        newRow.appendChild(cell1)

        var cell1 = document.createElement('td');
        if (ticketList[k].status == "Approved") {
            cell1.style.color = "rgb(0,165,30)";
        }
        else if (ticketList[k].status == "Denied") {
            cell1.style.color = "rgb(165,0,30)";
        }
        cell1.innerHTML = ticketList[k].status;
        newRow.appendChild(cell1);

        var cell1 = document.createElement('td');
        cell1.innerHTML = "$"+ ticketList[k].reimburseAmount;
        newRow.appendChild(cell1);

        var cell1 = document.createElement('td');
        cell1.innerHTML = convertTimestampToDate(ticketList[k].timeStamp);
        newRow.appendChild(cell1);

        var cell1 = document.createElement('td');
        //cell1.style.maxWidth = "10%";
        cell1.innerHTML = ticketList[k].description;
        newRow.appendChild(cell1);

        var cell1 = document.createElement('td');
        cell1.innerHTML = ticketList[k].username;
        newRow.appendChild(cell1)


        newTBody.appendChild(newRow);
        
    }

    //console.log("OLD TABLE BODY: "+oldTBody);
    oldTBody.innerHTML = newTBody.innerHTML;

    
    
    document.getElementById("tickets_table").style.visibility="visible";

    
}


function convertTimestampToDate(timestamp) {

    var date = new Date(timestamp);
    
    var hours = date.getHours();
    var minutes = "0" + date.getMinutes();
    var seconds = "0" + date.getSeconds();

    var day = date.getDate();
    var month = date.getMonth();
    var year = date.toDateString();

    return year+": "+hours+":"+minutes.substr(-2)+":"+seconds.substr(-2);


}


function sortTicketsByIDAscending() {
    ticketList.sort((a,b) => (a.id > b.id) ? 1 : -1);
}

function sortTicketsByIDDescending() {
    ticketList.sort((a,b) => (a.id > b.id) ? -1 : 1);    
}

function refreshTicketsTable() {
    fetchAllTicketsForUsername("bob23");
}


function getPageArrayIndex() {
    console.log("getPageIndex()");

    return currentPage * maxRowsPerTable;
}