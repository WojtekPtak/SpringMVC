

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