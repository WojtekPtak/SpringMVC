<#assign userList = model["userList"]>
<table class="datatable">
  <tr>  <th>Name</th>    <th>Email</th>    <th>Birthday</th>  <th>Roles</th> </tr>
    <#list userList as user>
    <tr>  <td>${user.name}</td>    <td>${user.email}</td>   <td>${user.birthday!'N/A'}</td>  <td>${user.roles!' - '}</td> </tr>
</#list>
</table>