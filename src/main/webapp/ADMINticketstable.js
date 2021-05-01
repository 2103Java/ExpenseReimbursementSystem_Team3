console.log("ticketstable.js START");

var maxRowsPerTable = 10;

var ticketList = [];

window.addEventListener("load",
    function() {
    console.log("----> Window Loaded!");


    
    var username = window.localStorage.getItem("username");
    var accessLevel = window.localStorage.getItem("accessLevel");
    document.getElementById("mainGreeting").innerHTML = "Welcome, " + username+"!";
    document.getElementById("accessLevelDisplayGreeting").innerHTML = "Access Level: " + accessLevel;
    
    var currentPageURL = window.location.href;
    console.log(currentPageURL);
    
    fetchAllTickets();
    
    
    }
);    


document.body.addEventListener('click',function(e) {
    let theButton = e.target;


    // If the clicked element is a button
    if (theButton.tagName == "BUTTON") {
        
        let buttonType = theButton.innerHTML;
        console.log("BUTTON TYPE IS "+buttonType);    
        // And if that button element has innerHTML of "Approve" or "Deny"
        if (buttonType == "Approve" || buttonType == "Deny") {
            // if that button has id == approvedButton OR deniedButton
            let buttonContainer = e.target.parentElement;
            let buttonContainerContainer = buttonContainer.parentElement;
            let cell = buttonContainerContainer.parentElement;
            let row = cell.parentElement;

            let cells = row.children;
            console.log("cells.length = "+cells.length);

            let ticketID = cells[0].innerHTML;
            
            /**
             * Use AJAX to update ticket status
             */
            if (buttonType == "Approve") {
                updateTicketStatus(ticketID,"Approved");
            }
            else if (buttonType == "Deny") {
                updateTicketStatus(ticketID,"Denied");
            }

        }
    }
});




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
            fetchAllTickets();
            
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


function logOut() {
    // Send a GET to this URL
    window.location.href = "http://localhost:9500/ERS/util/changeloginstatus";
  
}


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
    xhttp.setRequestHeader("mode","ticketid")
    xhttp.setRequestHeader("ticketid",id)
    xhttp.send();
}






function fetchAllTickets() {
    let xhttp = new XMLHttpRequest()
    xhttp.onreadystatechange = function() {

        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let tickets = JSON.parse(xhttp.responseText);
            console.log(tickets);
            ticketList = tickets;
            generateTicketTable();
            
        }
    }

    let ticketURL = "http://localhost:9500/ERS/api/ticket";
	

    xhttp.open("GET",ticketURL);
    xhttp.setRequestHeader("mode","all");
    xhttp.send();
}




function generateTicketTable() {

    sortTicketsByIDAscending();

    
    var newTBody = document.createElement('tbody');
    var oldTBody = document.getElementById("tickets_table").getElementsByTagName("tbody")[0];

    console.log("oldTBody = "+oldTBody);
    
    
    var pendingEnabled = document.getElementById("showStatusPending").checked;
    var approvedEnabled = document.getElementById("showStatusApproved").checked;
    var deniedEnabled = document.getElementById("showStatusDenied").checked;


    
    for (let k = 0; k < ticketList.length; k++) {
        
        
        var ticket = ticketList[k];
        //console.log("ticket "+k+" has amount = "+ticket.reimburseAmount);

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
        /**  
         * Create form here
         */
        //var buttonsForm = document.createElement('form');
        //buttonsForm.setAttribute("action","http://localhost:9500/ERS/api/ticket");
        //buttonsForm.setAttribute("method","POST");
        var buttonsForm = document.createElement('div');

        var ticketApiPostMode = document.createElement('input');
        ticketApiPostMode.id = "ticketID";
        ticketApiPostMode.type="hidden";
        ticketApiPostMode.name="mode";
        ticketApiPostMode.value="update";
        var inputTicketID = document.createElement('input');
        inputTicketID.type="hidden";
        inputTicketID.name="ticketid";
        inputTicketID.value=ticketList[k].id;

        var buttonsWrapper = document.createElement('span');
        buttonsWrapper.style="margin-left:52px;";

        var approveButton = document.createElement('button');
        approveButton.type="button";
        approveButton.className="btn btn-success btn-sm btn-block";
        approveButton.innerHTML = "Approve";
        

        var denyButton = document.createElement('button');
        denyButton.type="button";
        denyButton.className="btn btn-danger btn-sm btn-block";
        denyButton.innerHTML = "Deny";
        denyButton.style = "margin-left: 5px;";

        
        
        buttonsWrapper.appendChild(approveButton);
        buttonsWrapper.appendChild(denyButton);
        buttonsWrapper.appendChild(ticketApiPostMode);
        
        
        var statusP = document.createElement('span');
        statusP.innerHTML = ticketList[k].status;
        if (ticketList[k].status == "Approved") {
            statusP.style.color = "rgb(0,165,30)";
        }
        else if (ticketList[k].status == "Denied") {
            statusP.style.color = "rgb(165,0,30)";
        }

        //cell1.appendChild(statusP)
        buttonsForm.appendChild(ticketApiPostMode);
        buttonsForm.appendChild(inputTicketID);
        buttonsForm.appendChild(statusP);
        

        if (ticketList[k].status != "Pending") {
            //buttonsForm.appendChild(buttonsWrapper);
            approveButton.style = "visibility:hidden";
            denyButton.style = "visibility:hidden";
            
        }
        buttonsForm.appendChild(buttonsWrapper);
        /**
         *  Finished creating form here
         */
        
        // Everything for this column is put in the form
        cell1.appendChild(buttonsForm);


        newRow.appendChild(cell1);








        var cell1 = document.createElement('td');
        cell1.innerHTML = "$"+ ticketList[k].reimburseAmount;
        newRow.appendChild(cell1);

        var cell1 = document.createElement('td');
        cell1.innerHTML = convertTimestampToDate(ticketList[k].timeStamp);
        newRow.appendChild(cell1);

        var cell1 = document.createElement('td');
        cell1.innerHTML = ticketList[k].description;
        newRow.appendChild(cell1);

        var cell1 = document.createElement('td');
        cell1.innerHTML = ticketList[k].username;
        newRow.appendChild(cell1)


        newTBody.appendChild(newRow);
        
    }

    console.log("OLD TABLE BODY: "+oldTBody);
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


function updateTicketStatus(id, newStatus) {

    console.log("UPDATING TICKET STATUS");
    // Use AJAX to update ticketID

    let ticketAPIURL = "http://localhost:9500/ERS/api/ticket";

    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {

        if (xhttp.status == 200 && xhttp.readyState == 4) {
            // Received response for updating ticket status, refresh table no matter what
            fetchAllTickets();
        }
    }

    var params = 'mode=update&ticketid='+id+'&status='+newStatus;
    xhttp.open("POST",ticketAPIURL);
    xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhttp.send(params);

}