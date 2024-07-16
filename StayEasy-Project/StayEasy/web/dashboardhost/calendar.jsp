<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="Model.Account" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Host Bookings Timeline</title>
    <link href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/5.10.1/main.min.css' rel='stylesheet'/>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/5.10.1/main.min.js'></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment-timezone/0.5.38/moment-timezone-with-data-10-year-range.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/5.10.1/timeline.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/5.10.1/interaction.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/5.10.1/resource-common.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/5.10.1/resource-timeline.min.js"></script>

    <header>
        <jsp:include page="leftadmin.jsp"></jsp:include>
    </header>
    <jsp:include page="header_right.jsp"></jsp:include>
    <!--Main Navigation-->

    <%
        Account loggedInUser = (Account) session.getAttribute("acc");
        int hostId = loggedInUser != null ? loggedInUser.getUserid() : -1; // default to -1 if not logged in
    %>

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            var calendarEl = document.getElementById('calendar');

            var calendar = new FullCalendar.Calendar(calendarEl, {
                initialView: 'resourceTimelineWeek',
                headerToolbar: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'resourceTimelineWeek,resourceTimelineMonth'
                },
                nowIndicator: true,
                editable: true,
                resourceAreaWidth: '15%',
                resources: {
                    url: 'calendar',
                    method: 'POST',
                    extraParams: {
                        hostId: '<%= hostId %>' 
                    },
                    failure: function () {
                        alert('There was an error while fetching calendar data.');
                    }
                },
                events: function (info, successCallback, failureCallback) {
                    $.ajax({
                        url: 'calendar', 
                        type: 'POST',
                        dataType: 'json',
                        data: {
                            hostId: '<%= hostId %>' 
                        },
                        success: function (data) {
                            var events = [];
                            data.forEach(function (booking) {
                                var event = {
                                    id: booking.billDetailId,
                                    resourceId: booking.houseId,
                                    start: moment(booking.startDate).format('YYYY-MM-DD'),
                                    end: moment(booking.endDate).format('YYYY-MM-DD'),
                                    title: 'Booking ' + booking.billDetailId,
                                    borderColor: 'green' 
                                };
                                events.push(event);
                            });
                            successCallback(events);
                        },
                        error: function () {
                            failureCallback();
                        }
                    });
                }
            });

            calendar.render();
        });
    </script>
</head>
<body>
    <div id='calendar'></div>
</body>
</html>
