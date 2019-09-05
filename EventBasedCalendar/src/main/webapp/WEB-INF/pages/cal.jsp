<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.1.0/fullcalendar.print.min.css' rel='stylesheet'
        media='print' />
    <meta name="viewport" content="width=device-width, initial-scale=1">
   <!--   <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.7/semantic.min.js"></script>-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.17.1/moment.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.1.0/fullcalendar.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.1.0/fullcalendar.min.css" rel="stylesheet" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.7/semantic.min.css" rel="stylesheet" />
<title>Insert title here</title>
<title>Client Itinerary</title>
   

    
</head>
<body>
	<br />
    <div class="ui container">
        <div class="ui grid">
            <div class="ui sixteen column">
                <div id="calendar"></div>
            </div>
        </div>
    </div>
    
</body>

<script>
	var queryArr = [];
	var arrDate;
	$.ajax({ //starts here
		url : "http://localhost:2521/EventBasedCalendar/viewAllEvents",
   		success : function(data) {
   		$.each(data, function(index, obj) {
      		queryStr = { "title" : obj.title,"start":new Date(obj.start),"end":new Date(obj.end)};
      		queryArr.push(queryStr);
   		});
   		
   		arrDate=data[0].cli.arrivalDate;
   		
   		
   		
   	}
	});//end starts
    
	$(document).ready(function () {
            $('#calendar').fullCalendar({
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,basicWeek,basicDay'
            },
            defaultDate: arrDate,
            navLinks: true, // can click day/week names to navigate views
            editable: true,
            eventLimit: true, // allow "more" link when too many events
            events: queryArr
            	
            
    });
    });
</script>
</html>