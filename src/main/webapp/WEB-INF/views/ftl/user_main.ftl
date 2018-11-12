

<html>
<head><title>Users manager</title>

<#include "./common/style.ftl">
    </head>
<body>
<div id="header">
<H2>
    Users manager
</H2>
<#include "./common/back_home.ftl">
</div>

<div id="content">

  <fieldset>
    <legend>User registration</legend>
    <form name="user" action="/user_register" method="post">
        Name: <input type="text" name="name" />	<br/>
        Email: <input type="text" name="email" />	<br/>
        Birthday: <input type="date" name="birthday" />	<br/>
        <input type="submit" value="   Register   " />
    </form>
</fieldset>

    <div id="content2">
    <h4>Load users from test CSV file!</h4>
    <form method="post" action="/data/csv/users.csv" enctype="multipart/form-data">
        <input type="submit" value="Load test data" />
    </form>
    </div>

  <br/>



<#if model["userList"] ??>
    <#include "common/user_list.ftl">
<#else>
    <div id="error">
    <h3 >
        No users!
    </h3>
    </div>
</#if>

</div>
</body>
</html>