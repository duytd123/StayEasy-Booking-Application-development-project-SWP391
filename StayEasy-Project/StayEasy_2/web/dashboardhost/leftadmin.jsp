<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Sidebar -->
<nav id="sidebarMenu" class="collapse d-lg-block sidebar collapse bg-black" style="padding: 0px; width: 270px; background-color: black">
    <div class="position-sticky" >
        <div class="list-group list-group-flush mx-3 mt-4" style="margin: 0">
            <div class="footer_logo" style="text-align: center; margin-bottom: 0">
                <a href="home"><img src="Images/logo1.png" alt="Logo" style="height: 53px"></a>
            </div>
            
            <a href="host" class="list-group-item list-group-item-action" aria-current="true" style="margin-top: 10px;">
                <i style="margin-right: 10px; font-size: 18px" class="fas fa-duotone fa-house me-3"></i>
                <span style="font-size: 16px; font-weight: 600">Main dashboard</span>
            </a>
            
            <a href="weekrevenue" class="list-group-item list-group-item-action" style="margin-top: 10px">
                <i style="margin-right: 10px; font-size: 18px" class="fas fa-chart-pie fa-fw me-3"></i>
                <span style="font-size: 16px; font-weight: 600">Revenue by week</span>
            </a>
            
            <a href="mothlyrevenue" class="list-group-item list-group-item-action" style="margin-top: 10px">
                <i style="margin-right: 10px; font-size: 18px" class="fas fa-chart-bar fa-fw me-3"></i>
                <span style="font-size: 16px; font-weight: 600">Revenue by month</span>
            </a>
            
            <a href="calendar" class="list-group-item list-group-item-action" style="margin-top: 10px">
                <i style="margin-right: 10px; font-size: 18px" class="fas fa-file-invoice-dollar fa-fw me-3"></i>
                <span style="font-size: 16px; font-weight: 600">Calendar</span></a>

            <a href="commentmanager" class="list-group-item list-group-item-action" style="margin-top: 10px">
                <i style="margin-right: 10px; font-size: 18px" class="fas fa-thin fa-comments me-3"></i>
                <span style="font-size: 16px; font-weight: 600">Comment by User</span>
            </a>
            
            <a href="manager" class="list-group-item list-group-item-action" style="margin-top: 10px">
                <i style="margin-right: 10px; font-size: 18px" <i class="fas fa-thin fa-house-medical me-3"></i>
                <span style="font-size: 16px; font-weight: 600">House</span>
            </a>

            <a href="billhost" class="list-group-item list-group-item-action" style="margin-top: 10px">
                <i style="margin-right: 10px; font-size: 18px" class="fas fa-parachute-box fa-fw me-3"></i>
                <span style="font-size: 16px; font-weight: 600">Bill</span>
            </a>
            <a href="additional1" class="list-group-item list-group-item-action" style="margin-top: 10px">
                <i style="margin-right: 10px; font-size: 18px" class="fas fa-thin fa-bars me-3"></i>
                <span style="font-size: 16px; font-weight: 600">Room Service</span>
            </a>
        </div>
    </div>
</nav>