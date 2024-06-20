<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">

        <title>bs5 profile billing page - Bootdey.com</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css" integrity="sha256-2XFplPlrFClt0bIdPgpz8H7ojnk10H69xRqd9+uTShA=" crossorigin="anonymous" />

        <div class="container-xl px-4 mt-4">
            <hr class="mt-0 mb-4">
            <div class="row">
                <div class="col-lg-4 mb-4">
                    <!-- Billing card 1-->
                    <div class="card h-100 border-start-lg border-start-primary">
                        <div class="card-body">
                            <div class="small text-muted">Current monthly bill</div>
                            <div class="h3"><fmt:formatNumber value="${rs}" pattern="$#,###" /></div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Billing history card-->
            <p class="text-danger">${mess}</p>
            <div class="card mb-4">
                <div class="card-header">Billing</div>
                <div class="card-body p-0">
                    <!-- Billing history table-->
                    <div class="table-responsive table-billing-history">
                        <table class="table mb-0">
                            <thead>
                                <tr>
                                    <th style="text-align: center" class="border-gray-200" scope="col">Bill ID</th>
                                    <th style="text-align: center" class="border-gray-200" scope="col">Date</th>
                                    <th style="text-align: center" class="border-gray-200" scope="col">Total</th>
                                    <th style="text-align: center" class="border-gray-200" scope="col">Status</th>
                                    <th style="text-align: center" class="border-gray-200" scope="col">View | Reject</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${list}" var="l" varStatus="loop" >
                                    <tr>
                                        <td style="text-align: center">${l.billid}</td>
                                        <td style="text-align: center">${l.date}</td>
                                        <td style="text-align: center"><fmt:formatNumber value="${l.total}" pattern="$#,###" /></td>
                                        <td style="text-align: center">
                                            <c:if test="${l.status == 1}">
                                                <span class="badge bg-success">Paid</span>
                                            </c:if>
                                            <c:if test="${l.status == 0}">
                                                <span class="badge bg-light text-dark">Pending</span>
                                            </c:if>
                                        </td>
                                        <td style="text-align: center">
                                            <form action="UserServlet" method="POST">
                                                <input type="hidden" name="userid" value="${l.userid}">
                                                <input type="hidden" name="billid" value="${l.billid}">
                                                <button type="submit" class="badge bg-light text-dark" name="sub" value="view">view</button>
                                                <c:if test="${l.status == 0}">
                                                    <a onclick="showConfirmModal(${l.billid}, ${l.userid})" class="badge bg-success" type="button">X</a>
                                                </c:if>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <script>
            function showConfirmModal(billid, userid) {
                $('#yesOption').attr('href', 'UserServlet?sub=reject&billid=' + billid + '&userid=' + userid);
                $('#confirmationID').modal('show');
            }
        </script>   

        <!-- Modal Notice -->
        <div class="modal fade" id="confirmationID" role="dialog" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Confirmation</h5>
                    </div>
                    <div class="modal-body">
                        Do you want to delete this bill?
                    </div>
                    <div class="modal-footer">
                        <a id="yesOption" type="button" class="btn btn-primary">Yes</a>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>


        <style type="text/css">
            body{
                margin-top:20px;
                background-color:#f2f6fc;
                color:#69707a;
            }
            .img-account-profile {
                height: 10rem;
            }
            .rounded-circle {
                border-radius: 50% !important;
            }
            .card {
                box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
            }
            .card .card-header {
                font-weight: 500;
            }
            .card-header:first-child {
                border-radius: 0.35rem 0.35rem 0 0;
            }
            .card-header {
                padding: 1rem 1.35rem;
                margin-bottom: 0;
                background-color: rgba(33, 40, 50, 0.03);
                border-bottom: 1px solid rgba(33, 40, 50, 0.125);
            }
            .form-control, .dataTable-input {
                display: block;
                width: 100%;
                padding: 0.875rem 1.125rem;
                font-size: 0.875rem;
                font-weight: 400;
                line-height: 1;
                color: #69707a;
                background-color: #fff;
                background-clip: padding-box;
                border: 1px solid #c5ccd6;
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                border-radius: 0.35rem;
                transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
            }

            .nav-borders .nav-link.active {
                color: #0061f2;
                border-bottom-color: #0061f2;
            }
            .nav-borders .nav-link {
                color: #69707a;
                border-bottom-width: 0.125rem;
                border-bottom-style: solid;
                border-bottom-color: transparent;
                padding-top: 0.5rem;
                padding-bottom: 0.5rem;
                padding-left: 0;
                padding-right: 0;
                margin-left: 1rem;
                margin-right: 1rem;
            }
            .fa-2x {
                font-size: 2em;
            }

            .table-billing-history th, .table-billing-history td {
                padding-top: 0.75rem;
                padding-bottom: 0.75rem;
                padding-left: 1.375rem;
                padding-right: 1.375rem;
            }
            .table > :not(caption) > * > *, .dataTable-table > :not(caption) > * > * {
                padding: 0.75rem 0.75rem;
                background-color: var(--bs-table-bg);
                border-bottom-width: 1px;
                box-shadow: inset 0 0 0 9999px var(--bs-table-accent-bg);
            }

            .border-start-primary {
                border-left-color: #0061f2 !important;
            }
            .border-start-secondary {
                border-left-color: #6900c7 !important;
            }
            .border-start-success {
                border-left-color: #00ac69 !important;
            }
            .border-start-lg {
                border-left-width: 0.25rem !important;
            }
            .h-100 {
                height: 100% !important;
                font-family: Arial;
            }
        </style>

    </body>
</html>