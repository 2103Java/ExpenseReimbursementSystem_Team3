console.log("ticketstable.js START");

var maxRowsPerTable = 10;

var ticketList = [];

window.addEventListener("load",
    function() {
    console.log("----> Window Loaded!");

    var currentPageURL = window.location.href;
    console.log(currentPageURL);

    fetchAllTicketsForUsername("bob23");
    
    }
);    



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




function fetchAllTicketsForUsername(username) {

    console.log("FETCH ALL TICKETS FOR A USER:");
    console.log("window.localStorage.getItem(username) = "+window.localStorage.getItem("username"));
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
    xhttp.setRequestHeader("mode","username")
    xhttp.setRequestHeader("username",window.localStorage.getItem("username"));
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
        cell1.innerHTML = ticketList[k].status;
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

