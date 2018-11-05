<html>
<head><title>Booking</title>

<#include "/common/style.ftl">
    </head>
<body>
<div id="header">
    <H1> Welcome to Booking (sorry - it's still under construction...)</H1>
</div>

<div id="content2">

    <div id="header">
        <H2>User</H2>
    </div>
    <A href="/users">Management</A></BR>
    <H3>Find user by: </H3>
    <form action="/user/email/">
        Email:<br>
        <input type="text" name="email">
        <input type="submit" value="find">
        <br>
    </form>
    <form action="/user/">
        Name:<br>
        <input type="text" name="name">
        <input type="submit" value="find">
        <br>
    </form>


    <div id="header">
        <H2>Auditorium</H2>
    </div>
    <A href="/auditorias">Show all</A></BR>
    <H3>Find by name: </H3>
    <form action="/auditorium/">
        <input type="text" name="name">
        <input type="submit" value="find">
        <br>
    </form>


    <div id="header">
        <H2>Booking</H2>

        <div id="header">
            <H2>Event</H2>
        </div>

        <div id="header">
            <H2>Ticket</H2>
        </div>

</body>
</html>