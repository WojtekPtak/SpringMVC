<html>
<head><title>Users manager</title>

    <style>
        body, input {
            font-family: Calibri, Arial;
            margin: 0px;
            padding: 0px;
        }
        #header h2 {
            color: white;
            background-color: #3275A8;
            height: 50px;
            padding: 5px 0 0 5px;
            font-size: 20px;
        }

        .datatable {margin-bottom:5px;border:1px solid #eee;border-collapse:collapse;width:400px;max-width:100%;font-family:Calibri}
        .datatable th {padding:3px;border:1px solid #888;height:30px;background-color:#B2D487;text-align:center;vertical-align:middle;color:#444444}
        .datatable tr {border:1px solid #888}
        .datatable tr.odd {background-color:#eee}
        .datatable td {padding:2px;border:1px solid #888}
        #content { padding 5px; margin: 5px; text-align: center}

        fieldset { width: 300px; padding: 5px; margin-bottom: 0px; }
        legend { font-weight: bold; }
    </style>

<body>
<div id="header">
    <H2>
        Users manager
    </H2>
</div>

<div id="content">

    <fieldset>
        <legend>Event registration</legend>
        <form name="event" action="/event_register" method="post">
            Name: <input type="text" name="name" />
                <br/>
            Rate: <select name="rate">
                    <#list model["eventRates"] as rate>
	  	            <option value="${rate}">${rate}</option>
                    </#list>
                    </select>
                <br/>
            Base price: <input type="double" name="basePrice" />
                <br/>
            Auditorium: <select name="auditorium">
                    <#list model["audList"] as ad>
                        <option value="${ad}">${ad.getName()}</option>
                    </#list>
                    </select>
                <br/>
            <input type="submit" value="   Register   " />
        </form>
    </fieldset>

    <br/>

    <table class="datatable">
        <tr>
            <th>Name</th>  <th>Rate</th>  <th>Base price</th> <th>Date</th> <th>Auditorium</th>
        </tr>
    <#list model["eventList"] as event>
	  	<tr>
            <td>X</td><td>${event.name}</td> <td>${event.rate}</td> <td>${event.basePrice}</td> <td>${event.dateTime}</td> <td>${event.auditorium.name}</td>
        </tr>
	</#list>
    </table>

</div>
</body>
</html>