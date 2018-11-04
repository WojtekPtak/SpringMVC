<html>
<head><title>Users manager</title>
<#include "/common/style.ftl">
    </head>
<body>
<div id="header">
    <H2>
        Users manager
    </H2>
    <H3>
        <A href="/index">Main menu</A></BR>
    </H3>
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