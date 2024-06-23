<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Book Your Stay</title>
        <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link rel="stylesheet" href="housepage.css">
        <link href="stylesheet" rel="stylesheet">
        <link rel="stylesheet" href="list.css">
        <link rel="stylesheet" href="housepage.css">
        <link rel="stylesheet" href="css/list_house_main.css">
        <link rel="stylesheet" href="assets/css/style.min.css">
        <link rel="stylesheet" href="assets/css/dist/css/bootstrap.css">
        <link rel="stylesheet" href="assets/css/dist/css/bootstrap_1.css">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="user.jsp">
        <link rel="stylesheet" href="list_house_main.css">
        <link rel="stylesheet" href="StyleSheet.css">
        <link rel="stylesheet" href="css/housepage.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10">
        <style>
            .inputBox {
                margin-bottom: 1rem;
            }

            .inputBox {
                display: inline-block;
                width: calc(50% - 5px);
                margin-right: 10px;
            }
            .btn-custom {
                background-color: #007bff;
                color: #fff;
                border-top-left-radius: 0;
                border-bottom-left-radius: 0;
            }
            .btn-custom:hover {
                background-color: #0056b3;
            }
            .card {
                border: none;
                border-radius: 10px;
            }
            .card-body {
                padding: 2rem;
            }
            .card-title {
                font-size: 1.5rem;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <section class="book py-5" id="book">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-11">
                        <div class="card p-4 shadow-sm">
                            <div class="card-body">
                                <div<h3 class="card-title text-center mb-4">Book Your Stay</h3>
                                    <form action="search-house-main" method="get" class="d-flex" id="bookingForm">
                                        <div class="inputBox">
                                            <input value="${param.whereTo}" required="" type="text" name="whereTo" placeholder="Place name">
                                        </div>
                                        <div class="inputBox"><input value="${param.guests}" required="" type="number" name="guests" placeholder="Number of guests"></div>
                                        <div class="inputBox">
                                            <input value="${param.arrivals}" name="arrivals" type="date">
                                        </div>
                                        <div class="inputBox">
                                            <input required value="${param.leaving}" name="leaving" type="date">
                                        </div>
                                        <div>
                                            <input type="submit" class="btn btn-custom" value="Book now">
                                        </div>

                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </section>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
        
        <script>
            $(document).ready(function () {
                $('#searchForm').submit(function (e) {
                    e.preventDefault();
                    console.log("Search button clicked!");
                });
            });
        </script>
    </body>
</html>
