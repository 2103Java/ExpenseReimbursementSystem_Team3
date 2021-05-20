# Expense Reimbursement System
Expense Reimbursement System
by Ben Markos & David Nhem (Team 3)

With our Expense Reimbursement System (ERS), a user can:

* Register for an account, as an employee or financial manager.
* Sign in to a user home page once they have registered.
* When signed in, a user can submit a request for expense reimbursement in a variety of categories.
* When signed in, a user can view a table of all their requests.
* A financial manager has an additional page they can access in order to view requests and approve or deny them.
* A user can filter their view of tickets by pending, approved, or declined status.

Written in Javascript, HTML, CSS.
Using Bootstrap for styling.
We utilize mostly asynchronous HTTP requests via AJAX, but occasional synchronous requests are made when needing to re-authorize.
Deployed as a .war on an Apache Tomcat Server running locally.
Maven is used for dependencies, including:
* JUnit.
* Mockito.
* Log4j.
* Jdbc, PostgreSQL.

   
