

<html>
<head><title>Users manager</title>

<#include "/common/style.ftl">
    </head>
<body>
<div id="header">
<H2>
    User search
</H2>
<#include "/common/back_home.ftl">
</div>

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
        No users found!
    </h3>
    </div>
</#if>

</div>
</body>
</html>