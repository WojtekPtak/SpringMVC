

<html>
<head><title>Users manager</title>

<#include "/common/style.ftl">
    </head>
<body>
<div id="header">
<H2>
    Users manager
</H2>
<#include "/common/back_home.ftl">
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
  <#assign userList = model["userList"]>
  <table class="datatable">
  	<tr>  <th>Name</th>    <th>Email</th>    <th>Birthday</th>  </tr>

    <#list userList as user>
	  	<tr>  <td>${user.name}</td>    <td>${user.email}</td>   <td>${user.birthday!'N/A'}</td>  </tr>
    </#list>
  </table>

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