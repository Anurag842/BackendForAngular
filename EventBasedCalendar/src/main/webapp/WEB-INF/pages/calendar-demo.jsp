<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.1.0/fullcalendar.print.min.css' rel='stylesheet'
        media='print' />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.7/semantic.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.17.1/moment.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.1.0/fullcalendar.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.1.0/fullcalendar.min.css" rel="stylesheet" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.7/semantic.min.css" rel="stylesheet" />
<title>View Events</title>

</head>
<body>
  <br />
    <div class="ui container">
    <h1> Client's Name is  ${clientName}</h1>
        <div class="ui grid">
            <div class="ui sixteen column">
                <div id="calendar"></div>
            </div>
        </div>
    </div>

    </div>
</body>

<script>
    $(document).ready(function () {

        $('#calendar').fullCalendar({
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,basicWeek,basicDay'
            },
            defaultDate: '2016-12-12',
            navLinks: true, // can click day/week names to navigate views
            editable: true,
            eventLimit: true, // allow "more" link when too many events
            events: [
                {
                    title: 'Cab',
                    start: '2016-12-09T16:30:00',
                    end: '2016-12-09T17:30:00'
                },
                {
                    title: 'Long Event',
                    start: '2016-12-07',
                    end: '2016-12-10'
                },
                {
                    title: 'Demo 2',
                    start: '2016-12-07',
                    end: '2016-12-10'
                },
                {
                    id: 999,
                    title: 'Repeating Event',
                    start: '2016-12-09T16:00:00'
                },
                {
                    id: 999,
                    title: 'Repeating Event',
                    start: '2016-12-16T16:00:00'
                },
                {
                    title: 'Conference',
                    start: '2016-12-11',
                    end: '2016-12-13'
                },
                {
                    title: 'Meeting',
                    start: '2016-12-12T10:30:00',
                    end: '2016-12-12T12:30:00'
                },
                {
                    title: 'Breakfast',
                    start: '2016-12-12T12:00:00'
                },
                {
                    title: 'Meeting',
                    start: '2016-12-12T14:30:00'
                },
                {
                    title: 'Happy Hour',
                    start: '2016-12-12T17:30:00'
                },
                {
                    title: 'Dinner',
                    start: '2016-12-12T20:00:00'
                },
                {
                    title: 'Birthday Party',
                    start: '2016-12-13T07:00:00'
                },
                {
                    title: 'Click for Google',
                    url: 'https://google.com/',
                    start: '2016-12-28'
                }
            ]
        });

    });
</script>
</html>