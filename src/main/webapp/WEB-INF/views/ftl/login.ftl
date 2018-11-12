

<html>
<head><title>Login</title>

<#include "./common/style.ftl">
    </head>
<body>
<div id="header">
<H2>
    Login to System
</H2>
</div>

<div id="content">

  <fieldset>
    <legend>User registration</legend>
    <form name="user" action="user_login" method="POST">
        User: <input type="text" name="username" />	<br/>
        Password: <input type="password" name="password" />	<br/>
        <input type="submit" value="   Log in   " />
    </form>
</fieldset>


</div>
</body>
</html>