<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add House</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <link rel="stylesheet" href="admin_style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <link rel="stylesheet" href="Admin_style_1.css">
    </head>
    <body>
        <section class="add-products">
            <h1 class="title">Add House</h1>
            <form action="AddHouseServlet" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
                <h2>House Name</h2>
                <input type="text" name="housename" class="box" placeholder="Enter House Name" required />

                <h2>House Price</h2>
                <input type="number" name="houseprice" class="box" placeholder="Enter House Price" required />

                <h2>Address</h2>
                <input type="text" name="address" class="box" placeholder="Enter Address" required />

                <h2>Description</h2>
                <textarea name="description" class="box" placeholder="Enter Description" required></textarea>

                <h2>Location</h2>
                <select class="box" name="location" required>
                    <c:forEach var="location" items="${llist}">
                        <option value="${location.id}">${location.name}</option>
                    </c:forEach>
                </select>

                <h2>Menu</h2>
                <select class="box" name="menu" required>
                    <c:forEach var="menu" items="${mlist}">
                        <option value="${menu.id}">${menu.name}</option>
                    </c:forEach>
                </select>

                <input type="submit" value="Add House" class="btn">
            </form>

            <script>
                function validateForm() {
                    var housePrice = document.getElementsByName("houseprice")[0].value;
                    if (isNaN(housePrice) || parseFloat(housePrice) <= 0) {
                        alert("House price must be a positive number.");
                        return false;
                    }
                    return true;
                }
            </script>
        </section>
    </body>
</html>
