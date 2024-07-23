<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Host Bookings Timeline</title>
        <link rel="icon" href="Images/logo1.png"/>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/> 

        <link href="css/fullcalendar.min.css" rel="stylesheet" type="text/css"/> 

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">

        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/bootstrap.min.css">

        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/mdb-pro.min.css">

        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/mdb.ecommerce.min.css">

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="css/style.css" rel="stylesheet" type="text/css"/> 

        <link rel="stylesheet" href="dashboardhost/newcss.css">
        <style>
            body {
                margin: 0;
                padding: 0;
            }

            select option {
                font-size: 16px;
                padding: 5px;
            }

            select {
                width: 32.3%;
                margin: 0;
                font-size: 16px;
                padding: 7px 10px;
                font-family: Segoe UI, Helvetica, sans-serif;
                border: 1px solid #D0D0D0;
                -webkit-box-sizing: border-box;
                -moz-box-sizing: border-box;
                box-sizing: border-box;
                border-radius: 10px;
                outline: none;
            }
        </style>
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"><link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&amp;display=swap"><link rel="stylesheet" type="text/css" href="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/css/mdb5/3.8.1/compiled.min.css"><link rel="stylesheet" type="text/css" href="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/css/mdb-plugins-gathered.min.css"><style>
            body {
                background-color: #fbfbfb;
            }
            @media (min-width: 991.98px) {
                main {
                    padding-left: 240px;
                }
            }
            .text_page_head{
                font-size: 18px;
                font-weight: 600;
            }
            .text_page {
                font-size: 14px;
                font-weight: 600;
            }

            .sidebar {
                position: fixed;
                top: 0;
                bottom: 0;
                left: 0;
                padding: 58px 0 0;
                box-shadow: 0 2px 5px 0 rgb(0 0 0 / 5%), 0 2px 10px 0 rgb(0 0 0 / 5%);
                width: 240px;
                z-index: 600;
            }

            @media (max-width: 991.98px) {
                .sidebar {
                    width: 100%;
                }
            }
            .sidebar .active {
                border-radius: 5px;
                box-shadow: 0 2px 5px 0 rgb(0 0 0 / 16%), 0 2px 10px 0 rgb(0 0 0 / 12%);
            }

            .sidebar-sticky {
                position: relative;
                top: 0;
                height: calc(100vh - 48px);
                padding-top: 0.5rem;
                overflow-x: hidden;
                overflow-y: auto;
            }
            .container {
                max-width: 1200px;
                margin: auto;
                padding: 20px;
            }

            h1, h3 {
                color: #333; /* Dark grey color for headings */
            }

            .card-body {
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            .container-calendar {
                display: flex;
                justify-content: space-between;
                flex-wrap: wrap;
            }

            #left, #right {
                flex: 1;
                margin: 10px;
            }

            #reminder-section {
                margin-top: 20px;
            }

            #reminderList li {
                background-color: #e9ecef;
                margin: 5px 0;
                padding: 10px;
                border-radius: 4px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                border-left: 5px solid #007bff; /* Blue bar on the left to highlight items */
            }

            .delete-event {
                background-color: #dc3545;
                color: #fff;
                border: none;
                padding: 5px 10px;
                border-radius: 4px;
                cursor: pointer;
            }

            .button-container-calendar {
                display: flex;
                justify-content: space-between;
                margin: 10px 0;
            }

            .button-container-calendar button {
                background-color: #007bff;
                color: #fff;
                border: none;
                padding: 10px;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .button-container-calendar button:hover {
                background-color: #0056b3; /* Darker blue on hover */
            }

            .table-calendar {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }

            .table-calendar th, .table-calendar td {
                border: 1px solid #ddd;
                padding: 10px;
                text-align: center;
            }

            .table-calendar th {
                background-color: #007bff; /* Blue header */
                color: #fff; /* White text */
            }

            .table-calendar td {
                background-color: #fff;
            }

            .footer-container-calendar {
                display: flex;
                justify-content: flex-start;
                align-items: center;
                margin-top: 20px;
            }

            .footer-container-calendar label, .footer-container-calendar select {
                margin-right: 10px;
            }

            .footer-container-calendar select {
                padding: 5px;
                border: 1px solid #ced4da;
                border-radius: 4px;
            }

            .block-date-button {
                display: block;
                margin-top: 5px;
                padding: 3px 5px;
                font-size: 12px;
                background-color: #dc3545; /* Red button */
                color: white;
                border: none;
                cursor: pointer;
            }

            .block-date-button:hover {
                background-color: #c82333; /* Darker red on hover */
            }

            .event-tooltip {
                position: absolute;
                background: #fff;
                border: 1px solid #ddd;
                padding: 10px;
                max-width: 300px; 
                white-space: nowrap; 
                overflow-x: auto; 
                text-overflow: ellipsis; /* Ellipsis for overflowed text */
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Shadow for better visibility */
                z-index: 1000; /* Ensure it is above other elements */
            }

            .event-tooltip p {
                margin: 0;
                padding: 0;
                color: #333; 
            }

            
        </style>
    </head>

    <body>
        <header>
            <jsp:include page="leftadmin.jsp"></jsp:include>
            </header>
        <jsp:include page="header_right.jsp"></jsp:include>
            <main>
                <div class="container pt-4" style="max-width: 1200px">
                    <section class="mb-4">
                        <div class="card">

                            <h1>Calendar Booking</h1>
                            <div id="reminder-section">                                  
                                <ul id="reminderList"></ul>


                                <div id="right">
                                    <h3 id="monthAndYear"></h3>
                                    <div class="button-container-calendar">
                                        <button id="previous" onclick="previous()">&lt;</button>
                                        <button id="next" onclick="next()">&gt;</button>
                                    </div>
                                    <table class="table-calendar" id="calendar" data-lang="en">
                                        <thead id="thead-month"></thead>
                                        <tbody id="calendar-body"></tbody>
                                    </table>
                                </div>

                                <div class="footer-container-calendar">
                                    <label for="month">Go To: </label>
                                    <select id="month" onchange="jump()">
                                        <option value="0">Jan</option>
                                        <option value="1">Feb</option>
                                        <option value="2">Mar</option>
                                        <option value="3">Apr</option>
                                        <option value="4">May</option>
                                        <option value="5">Jun</option>
                                        <option value="6">Jul</option>
                                        <option value="7">Aug</option>
                                        <option value="8">Sep</option>
                                        <option value="9">Oct</option>
                                        <option value="10">Nov</option>
                                        <option value="11">Dec</option>
                                    </select>
                                    <select id="year" onchange="jump()"></select>
                                </div>

                            </div>
                        </div>
                    </section>
                </div>
                <script>
                    var bookings = JSON.parse('${bookings}');
            </script>
            <script src="dashboardhost/newjavascript.js"></script>
        </main>
    </body>

</html>

