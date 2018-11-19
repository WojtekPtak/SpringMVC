<html>
<head><title>Ticket manager</title>
<#include "./common/style.ftl">
    </head>
<body>
<div id="header">
    <H2>
        Ticket manager
    </H2>
<#include "./common/back_home.ftl">
</div>

<div id="content">

    <fieldset>
        <legend>Ticket registration</legend>
        <form name="event" action="/buy_ticket" method="post">
            Event: <select name="auditorium">
                    <#list model["events"] as ev>
                        <option value="${ev}">"${ev.name?capitalize}" in "${ev.auditorium.name?capitalize}" on ${ev.dateTime?datetime.iso?date} at ${ev.dateTime?datetime.iso?time}</option>
                    </#list>
                    </select>
                <br/>
            <input type="submit" value="   Buy!   " />
        </form>
    </fieldset>

    <br/>

</div>
</body>
</html>