
var dataSet = [
    [ "20123678293", "TRAVEL", "$500", "2021-04-05 20:26:00", "Approved", "Flight ticket purchase", "Approve/Deny" ],
    [ "34767198908", "LODGING", "$1242", "2021-03-17 05:12:42", "Pending", "Hotel and Accommodations", "Approve/Deny" ],
    [ "76320123478", "FOOD", "$120", "2021-02-01 18:30:12", "Approved", "Food delivery expense", "Approve/Deny" ],
    [ "97238012392", "LODGING", "$5420", "2021-04-20 12:02:45", "Rejected", "Vacation costs in Hawaii", "Approve/Deny" ],
    [ "52973029347", "FOOD", "$175", "2021-03-07 14:23:34", "Approved", "Catering expenses for executives party", "Approve/Deny" ],
];

var click = document.getElementById('view_tickets');
click.addEventListener("click", toggle_table);
//
function toggle_table()
{
    location.reload();
    var tablewrap = document.getElementById('tickets_table').parentNode;
    tablewrap.classList.toggle('hidden');
}

function hide_table()
{
    if (localStorage.getItem('user_cookie') === null)
    {
        localStorage.setItem('user_cookie', 'loggedin_user');
        $('#tickets_table').parents('div.dataTables_wrapper').first().hide();
    }
    else
    {
        $('#tickets_table').parents('div.dataTables_wrapper').first().show();
    }
}

$(document).ready(function() {
    $('#tickets_table').DataTable( {
        data: dataSet,
        columns: [
            { title: "Ticket ID" },
            { title: "Reimbursement Type" },
            { title: "Reimbursement Amount" },
            { title: "Date Submitted" },
            { title: "Ticket Status" },
            { title: "Additional Description" },
            { title : "For Finance Manager Only"}
        ]
    });
});
