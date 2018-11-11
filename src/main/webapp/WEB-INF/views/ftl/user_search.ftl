

<html>
<head><title>Users manager</title>

<#include "./common/style.ftl">
    </head>
<body>
<div id="header">
<H2>
    User search
</H2>
<#include "./common/back_home.ftl">
</div>

<#if model["userList"] ??>
    <#include "user_list.ftl">
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