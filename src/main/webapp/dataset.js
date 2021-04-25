
var dataSet = [
    [ "20123678293", "TRAVEL", "$500", "2021-04-05 20:26:00", "Approved", "Flight ticket purchase" ],
    [ "34767198908", "LODGING", "$1242", "2021-03-17 05:12:42", "Pending", "Hotel and Accommodations" ],
    [ "76320123478", "FOOD", "$120", "2021-02-01 18:30:12", "Approved", "Food delivery expense" ],
    [ "97238012392", "LODGING", "$5420", "2021-04-20 12:02:45", "Rejected", "Vacation spending in Hawaii" ],
    [ "52973029347", "FOOD", "$175", "2021-03-07 14:23:34", "Approved", "Catering expenses for executives party" ],
];

var click = document.getElementById('view_tickets');
click.addEventListener("click", toggle_table);
//
function toggle_table()
{
    location.reload();
    // $('#example').parents('div.dataTables_wrapper').first().hide();
    var tablewrap = document.getElementById('example').parentNode;
    tablewrap.classList.toggle('hidden');
}

function hide_table()
{
    if (localStorage.getItem('user_cookie') === null)
    {
        localStorage.setItem('user_cookie', 'loggedin_user');
        $('#example').parents('div.dataTables_wrapper').first().hide();
    }
    else
    {
        $('#example').parents('div.dataTables_wrapper').first().show();
    }
}
// $('#employee_dropdown_menu').change(function()
// {
//     var selectedValue = this.val();
//
//     //Depend on Value i.e. 0 or 1 respective function gets called.
//     switch(selectedValue){
//         case "view_tickets":
//             toggle_table();
//             break;
//         case "other":
//             // handlerFunctionB();
//             break;
//         //etc...
//         default:
//             alert("catch default");
//             break;
//     }
// });

// function handlerFunctionA(){
//     alert("do some stuff");
// }
//
// function handlerFunctionB(){
//     alert("Do some other stuff");
// }

// $(document).ready(function() {
//     $('.display td').hide;
//     $('view_tickets').click(function () {
//         $(this).parents('table'.find('td').toggle())
//     });
// // });
//     DataTable( {
//         data: dataSet,
//         columns: [
//             { title: "Ticket ID" },
//             { title: "Reimbursement Type" },
//             { title: "Reimbursement Amount" },
//             { title: "Date Submitted" },
//             { title: "Ticket Status" },
//             { title: "Additional Description" }
//         ]
//     });
// });

$(document).ready(function() {
    $('#example').DataTable( {
        data: dataSet,
        columns: [
            { title: "Ticket ID" },
            { title: "Reimbursement Type" },
            { title: "Reimbursement Amount" },
            { title: "Date Submitted" },
            { title: "Ticket Status" },
            { title: "Additional Description" }
        ]
    });
});