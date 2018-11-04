<html>
<head><title>Auditorias</title>

<#include "/common/style.ftl">
</head>
<body>
<div id="header">
    <H2>
        List of auditorias
    </H2>
    <H3>
        <A href="/index">Main menu</A></BR>
    </H3>
</div>

<div id="content">

    <br/>

<#if model["auditoriumList"]??>

<div id="content2">
    <form action="/auditorium/pdf_all">
        <input type="submit" value="Show as PDF" />
    </form>
</div>

    <table class="datatable">
        <tr>
            <th>Name</th>
            <th>Num seats</th>
            <th>VIP seats</th>
        </tr>

        <#list model["auditoriumList"] as auditorium>
            <tr>
                <td>${auditorium.name}</td>  <td>${auditorium.seatsNumber}</td>  <td>${auditorium.vipSeats}</td>
            </tr>
        </#list>
    </table>

<#else>
    <div id="error">
        <h3>
            No auditorium found!...
        </h3>
    </div>
 </#if>



</div>
</body>
</html>