<!DOCTYPE html>
<head>
    <meta charset="UTF-8" />
    <title>下拉框demo</title>
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/css/left-side-menu.css">
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/font/iconfont.css">
    <script src="${request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/jquery.slimscroll.min.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/left-side-menu.js"></script>
<#--<表单提交>-->
    <script src="${request.contextPath}/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/twitter-bootstrap/4.2.1/css/bootstrap.min.css"/>
    <script src="${request.contextPath}/js/bootbox.min.js"></script>
<#--<分页>-->
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/css/zxf_page.css"/>
    <script>
        window.onload=function () {
            var arrCity=[];
        <#list zhuanjiaList as s>
           arrCity[${s.id}]=[{t:'${s.xingming}',id:${s.id}}];
        </#list>
            document.getElementById('zhuanjiaid').onchange = function () {
                addOptions(document.getElementById('zhuanjiaxingming'),  arrCity[this.value]);
            };
            function addOptions(s, arr, initValue) {
                if (!arr || arr.length == 0) arr = [{ t: '请选择市', id: '' }];
                if (!s) { alert('select对象不存在！'); return false }
                s.options.length = 0;
                var selectedIndex = 0;
                for (var i = 0; i < arr.length; i++) {
                    s.options.add(new Option(arr[i].t, arr[i].id));
                    if (arr[i].id == initValue) selectedIndex = i;
                }
            }
            $("#btn").click(function () {
                var a=$("#zhuanjiaxingming").text();
                alert("value"+a);
            });
        };


        <#--<#list zhuanjiaList as s >-->
        <#--arrCity[s.id]=[{t:s.xingming,id:s.id}]-->
        <#--</#list>-->
        //

    </script>
</head>
<body>
<select id="zhuanjiaid" name="zhuanjiaid" value="3" >
    <option>---请选择---</option>
<#list zhuanjiaList as x>
    <#if  x.id == 3>
<option value="${x.id}" selected="selected">${x.id}</option>
    <#else>
<option value="${x.id}">${x.id}</option>
    </#if>
</#list>
</select>
<#--<select id="city"><option value="">请选择市</option></select>-->
<select id="zhuanjiaxingming" name="zhuanjiaxingming" value="3" >
    <option>---请选择---</option>
    <#list zhuanjiaList as x>
    <option value="${x.id}">${x.xingming}</option>
    </#list>
</select>
<#--<#list zhuanjiaList as x>-->
    <#--<#if  x.id == 3>-->
<#--<option value="${x.id}" selected="selected">${x.xingming}</option>-->
    <#--<#else>-->
<#--<option value="${x.id}">${x.xingming}</option>-->
    <#--</#if>-->
<#--</#list>-->

<button id="btn">点击</button>
</body>
</html>