

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
    <legend>User registration</legend>
    <form name="user" action="/user_register" method="post">
        Name: <input type="text" name="name" />	<br/>
        Email: <input type="text" name="email" />	<br/>
        Birthday: <input type="date" name="birthday" />	<br/>
        <input type="submit" value="   Register   " />
    </form>
</fieldset>

    <h4>Load users from CSV file</h4>
    <form method="post" action="/uploadUsers" enctype="multipart/form-data">
        <input type="file" name="file" value="CSV"/>
        <input type="submit" value="Load" />
    </form>


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