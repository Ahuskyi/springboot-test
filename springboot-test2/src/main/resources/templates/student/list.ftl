<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<img src="${request.contextPath}/img/add_two.png">
欢迎  ${age}岁的 ${loginname}
<#if (age<18)>小朋友
<#elseif (age>30)>大叔
<#else>小可爱
</#if>
登录
<br>学生列表<br>
<table border="1">
    <tr>
        <td>ID</td>
        <td>名字</td>
        <td>性别</td>
    </tr>
	  	<#list list?sort_by("id")?reverse as stu>
		 	<tr>
                <td> ${stu.id}</td>
                <td> ${stu.username}</td>
                <td> ${stu.sex}</td>
            </tr>
        </#list>
</table>
<div class="zxf_pagediv"></div>
</body>
</html>