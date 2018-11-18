<#assign userList = model["userList"]>
<#assign accountList = model["accountList"]>

<table class="datatable">
  <tr>  <th>Name</th>    <th>Email</th>    <th>Birthday</th>  <th>Roles</th> </tr>
    <#list userList as user>
    <tr>  <td>${user.name}</td>    <td>${user.email}</td>   <td>${user.birthday!'N/A'}</td>  <td>${user.roles!' - '}</td> </tr>
</#list>
</table>

<table class="datatable">
    <tr>  <th>Name</th>  <th>Balance</th> </tr>
    <#list accountList as account>
    <tr>  <td>${account.user.name}</td>    <td>${account.balance}</td> </tr>
    </#list>
</table>