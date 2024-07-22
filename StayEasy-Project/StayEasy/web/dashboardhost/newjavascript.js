let events = [];
let reminderList = document.getElementById("reminderList");

function initializeEvents() {
    events = bookings.flatMap(booking => {
        let startDate = parseDate(booking.startDate);
        let endDate = parseDate(booking.endDate);

        // Adjust for the 2-day discrepancy
        startDate.setUTCDate(startDate.getUTCDate() + 2);
        endDate.setUTCDate(endDate.getUTCDate() + 2);

        // Make sure endDate is inclusive by setting the time to the end of the day
        endDate.setUTCHours(23, 59, 59, 999);

        let eventList = [];

        // Create events for each day in the booking range
        while (startDate <= endDate) {
            eventList.push({
                id: booking.billId,
                date: new Date(startDate.getTime()),
                title: booking.houseName,
                description: `${booking.fullname}. Phone: ${booking.phone}`
            });
            // Move to the next day
            startDate.setUTCDate(startDate.getUTCDate() + 1);
        }

        return eventList;
    });
    showCalendar(currentMonth, currentYear);
}



function displayReminders() {
    reminderList.innerHTML = "";
    let selectedDate = new Date(today.getFullYear(), today.getMonth(), today.getDate());
    let eventsOnSelectedDate = events.filter(event => {
        let eventDate = new Date(event.date);
        return eventDate.getUTCFullYear() === selectedDate.getUTCFullYear() &&
               eventDate.getUTCMonth() === selectedDate.getUTCMonth() &&
               eventDate.getUTCDate() === selectedDate.getUTCDate();
    });

    eventsOnSelectedDate.forEach(event => {
        let listItem = document.createElement("li");
        listItem.innerHTML = `<strong>${event.title}</strong> - ${event.description}`;
        reminderList.appendChild(listItem);
    });
}

function generateYearRange(start, end) {
    let years = "";
    for (let year = start; year <= end; year++) {
        years += `<option value='${year}'>${year}</option>`;
    }
    return years;
}

let today = new Date();
let currentMonth = today.getMonth();
let currentYear = today.getFullYear();
let selectYear = document.getElementById("year");
let selectMonth = document.getElementById("month");

let createYear = generateYearRange(2022, 2024);
selectYear.innerHTML = createYear;

let calendar = document.getElementById("calendar");

let months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
let days = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];

let dataHead = "<tr>";
days.forEach(day => {
    dataHead += `<th data-days='${day}'>${day}</th>`;
});
dataHead += "</tr>";
document.getElementById("thead-month").innerHTML = dataHead;

let monthAndYear = document.getElementById("monthAndYear");
showCalendar(currentMonth, currentYear);

function next() {
    currentYear = currentMonth === 11 ? currentYear + 1 : currentYear;
    currentMonth = (currentMonth + 1) % 12;
    showCalendar(currentMonth, currentYear);
}

function previous() {
    currentYear = currentMonth === 0 ? currentYear - 1 : currentYear;
    currentMonth = currentMonth === 0 ? 11 : currentMonth - 1;
    showCalendar(currentMonth, currentYear);
}

function jump() {
    currentYear = parseInt(selectYear.value);
    currentMonth = parseInt(selectMonth.value);
    showCalendar(currentMonth, currentYear);
}

function parseDate(dateString) {
    let date = new Date(dateString); 
    return date;
}


function showCalendar(month, year) {
    let firstDay = new Date(year, month, 1).getDay();
    let tbl = document.getElementById("calendar-body");
    tbl.innerHTML = "";
    monthAndYear.innerHTML = `${months[month]} ${year}`;
    selectYear.value = year;
    selectMonth.value = month;

    let date = 1;
    for (let i = 0; i < 6; i++) {
        let row = document.createElement("tr");
        for (let j = 0; j < 7; j++) {
            if (i === 0 && j < firstDay) {
                let cell = document.createElement("td");
                cell.appendChild(document.createTextNode(""));
                row.appendChild(cell);
            } else if (date > daysInMonth(month, year)) {
                break;
            } else {
                let cell = document.createElement("td");
                cell.setAttribute("data-date", date);
                cell.setAttribute("data-month", month + 1);
                cell.setAttribute("data-year", year);
                cell.setAttribute("data-month_name", months[month]);
                cell.className = "date-picker";
                cell.innerHTML = `<span>${date}</span>`;

                if (date === today.getDate() && year === today.getFullYear() && month === today.getMonth()) {
                    cell.className = "date-picker selected";
                }

                // Check if there are events on this date
                if (hasEventOnDate(year, month, date)) {
                    cell.classList.add("event-marker");
                    cell.appendChild(createEventTooltip(year, month, date));
                }

                row.appendChild(cell);
                date++;
            }
        }
        tbl.appendChild(row);
    }

    displayReminders();
}


function createEventTooltip(year, month, date) {
    let tooltip = document.createElement("div");
    tooltip.className = "event-tooltip";
    let eventsOnDate = getEventsOnDate(year, month, date);
    eventsOnDate.forEach(event => {
        let eventText = `<strong>${event.title}</strong> - ${event.description}`;
        let eventElement = document.createElement("p");
        eventElement.innerHTML = eventText;
        tooltip.appendChild(eventElement);
    });
    return tooltip;
}


function getEventsOnDate(year, month, date) {
    return events.filter(event => {
        let eventDate = new Date(event.date);
        return eventDate.getDate() === date && eventDate.getMonth() === month && eventDate.getFullYear() === year;
    });
}

function hasEventOnDate(year, month, date) {
    return getEventsOnDate(year, month, date).length > 0;
}

function daysInMonth(iMonth, iYear) {
    return 32 - new Date(iYear, iMonth, 32).getDate();
}

function selectDateForReminder(year, month, date) {
    let selectedDate = `${year}-${String(month + 1).padStart(2, '0')}-${String(date).padStart(2, '0')}`;

    let readableDate = `${months[month]} ${date}, ${year}`;

    today = new Date(year, month, date);

    displayReminders();
}

initializeEvents();
showCalendar(currentMonth, currentYear);
