<%-- 
    Document   : error-page
    Created on : Jul 17, 2024, 4:11:28 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Error Page</title>
    <!-- Bootstrap CSS link -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            padding: 20px;
        }
        .error-container {
            max-width: 600px;
            margin: 0 auto;
            text-align: center;
            background-color: #f8d7da;
            border-color: #f5c6cb;
            border: 1px solid transparent;
            border-radius: .25rem;
            padding: 20px;
            margin-top: 50px;
        }
    </style>
</head>
<body>
    <div class="container error-container">
        <h1 class="display-4">Error: Invalid Dates</h1>
        <p class="lead">Please enter valid dates for arrival and leaving.</p>
        <a class="btn btn-primary" href="home">Go back to search</a>
    </div>
    
    <!-- Bootstrap JS and jQuery links (optional) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
