<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
    <title>三级导航</title>
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
        //预加载
        $(function () {
            loaddate();
            $('#add_btn').click(function () {
                methods.addHandle()
            })
            $('#new_add').click(function () {
                methods.new()
                $("#h4").html("添加");
                $("#id").removeAttr("readonly");
            })
            $('#show_tbody').on('click','.edit', function () {
                trIndex = $('.edit', '#show_tbody').index($(this));
                $("#h4").html("编辑");
                $("#id").attr("readonly","readonly");
                addEnter = false;
                $(this).parents('tr').addClass('has_case');
                methods.editHandle(trIndex);
            })
            $('#search_btn').click(function () {
                methods.seachName();
            })
            $('#back_btn').click(function () {
                $('#search_ziliaomingcheng').val('');
                $('#search_id').val('');
                methods.resectList();
            })
            $('.del').click(function () {
                $(this).parents('tr').remove();
            })
            $('#renyuan').on('hide.bs.modal',function() {
                addEnter = true;
                $('#show_tbody tr').removeClass('has_case');
                $('#xztb input').val(' ');
                $('#xztb select').find('option:first').prop('selected', true)
            });
            var addEnter = true,
                    noRepeat = true,
                    jobArr = [],
                    phoneArr = [],
                    tdStr = '',
                    trIndex,
                    hasNullMes = false,
                    tarInp = $('#xztb input'),
                    tarSel = $('#xztb select');
            var methods = {
                new:function(){
                    $('#renyuan').modal('show');
                    for (var j=0;j<tarInp.length;j++) {
                        var placeholder = tarInp.eq(j).placeholder;
                        tarInp.eq(j).val(placeholder)
                    }
                    for (var p=0;p<tarSel.length;p++) {
                        var the_p = p+tarInp.length;
                        tarSel.eq(p).val();
                    }
                },
                addHandle: function (the_index) {
                    hasNullMes = false;
                    methods.checkMustMes();
                    if (hasNullMes) {
                        return;
                    }
                    if (addEnter) {
                        methods.checkRepeat();
                        if (noRepeat) {
                            methods.setStr();
                            $('#show_tbody').append('<tr>' + tdStr + '</tr>');
                            $('#renyuan').modal('hide');
                        }
                    }else{
                        methods.setStr();
                        $('#show_tbody tr').eq(trIndex).empty().append(tdStr);
                        $('#renyuan').modal('hide');
                    }
                },
                editHandle: function (the_index) {

                    var tar = $('#show_tbody tr').eq(the_index);
                    var nowConArr = [];
                    var a;
                    for (var i=0; i<tar.find('td').length-1;i++) {
                        if (tar.children('td').eq(i).children('a').html()!=""&&tar.children('td').eq(i).children('a').html()!=null){
                            a = tar.children('td').eq(i).children('a').html();
                        } else {
                            a = tar.children('td').eq(i).html();
                        }
                        nowConArr.push(a);
                    }

                    $('#renyuan').modal('show');

                    for (var j=0;j<tarInp.length;j++) {
                        tarInp.eq(j).val(nowConArr[j])
                    }
                    for (var p=0;p<tarSel.length;p++) {
                        var the_p = p+tarInp.length;
                        tarSel.eq(p).val(nowConArr[the_p]);
                    }

                },
                setStr: function () {

                    tdStr = '';
                    for (var a=0; a<tarInp.length; a++) {
                        tdStr+= '<td>' + tarInp.eq(a).val() + '</td>'
                    }
                    for (var b=0; b<tarSel.length; b++) {
                        tdStr+= '<td>' + tarSel.eq(b).val() + '</td>'
                    }
                    tdStr+= '<td><a href="#" class="edit">编辑</a> <a href="#" class="del">删除</a></td>';

                },
                seachName: function () {

                    var a = $('#show_tbody tr');
                    var nameVal = $('#Ktext').val().trim();
                    var nameStr = '',
                            nameArr = [];

                    if (nameVal==='') {
                        bootbox.alert({
                            title: "来自火星的提示",
                            message: "搜索内容不能为空",
                            closeButton:false
                        })
                        return;
                    }

                    for (var c=0;c<a.length;c++) {
                        var txt = $('td:first', a.eq(c)).html().trim();
                        nameArr.push(txt);
                    }

                    a.hide();
                    for (var i=0;i<nameArr.length;i++) {
                        if (nameArr[i].indexOf(nameVal)>-1) {
                            a.eq(i).show();
                        }
                    }
                },
                resectList: function () {
                    loaddate();
                    // $('#show_tbody tr').show();
                },
                checkMustMes: function () {
                    if ($('.id').val().trim()==='') {
                        bootbox.alert({
                            title: "来自火星的提示",
                            message: "id为必选项，请填写",
                            closeButton:false
                        })
                        hasNullMes = true;
                        return
                    }
                    if ($('.ziliaomingcheng').val().trim()==='') {
                        bootbox.alert({
                            title: "来自火星的提示",
                            message: "资料名称为必选项，请填写",
                            closeButton:false
                        })
                        hasNullMes = true;
                        return
                    }
                },
                checkRepeat: function () {
                    noRepeat = true;
                }
            }

        });
        // <添加方法>

        //分页方法
        function loaddate(currentPage) {
                // alert("usertest loaddate"+currentPage)
                var data = {
                    currentPage : currentPage,
                    id : $("#search_id").val(),
                    ziliaomingcheng: $("#search_ziliaomingcheng").val(),
                }
                var url = "ziliaofenye"
                $.ajax({
                    url : url,
                    type : "get",
                    data : data,
                    dataType : "json",
                    cache : false,
                    success : function (pb) {
                        $("#show_tbody").empty();
                        $("#fenye").empty();
                        <#--//分页工具条-->
                        $("#totalPage").html(pb.totalPage);
                        $("#totalCount").html(pb.totalCount);
                        var fenyelis=
                                '            <a class="zxfPagenum" href="javascript:loaddate('+1+')" aria-label="Next">\n' +
                                '                <span aria-hidden="true">首页</span>\n' +
                                '            </a>'

                        if(pb.currentPage-1<1){
                            fenyelis +=
                                    '            <a href="javascript:loaddate('+1+')" class="disabled zxfPagenum" aria-label="Previous">\n' +
                                    '                <span aria-hidden="true">&laquo;</span>\n' +
                                    '            </a>'

                        }else {
                            fenyelis +=
                                    '            <a href="javascript:loaddate('+(currentPage-1)+')" class="prebtn zxfPagenum" aria-label="Previous">\n' +
                                    '                <span aria-hidden="true">&laquo;</span>\n' +
                                    '            </a>'

                        }
                        var min;
                        var max;
                        if(pb.totalPage<10){
                            min=1
                            max=pb.totalPage;
                        }
                        else {
                            min=pb.currentPage-5
                            max=pb.currentPage+4;
                            if(min<1){
                                min=1;
                                max=min+9;
                            }
                            if(max>pb.totalPage){
                                max=pb.totalPage;
                                min=max-9;
                            }
                        }
                        for (var i =min; i <=max ; i++) {
                            if(i==pb.currentPage){
                                li='<a class="current zxfPagenum" href="javascript:loaddate('+i+')">'+i+'</a>';
                            }
                            li='<a class="zxfPagenum" href="javascript:loaddate('+i+')">'+i+'</a>';
                            fenyelis+=li;
                        }
                        if(pb.currentPage+1>pb.totalPage){
                            fenyelis +=
                                    '            <a class="disabled zxfPagenum"href="javascript:loaddate('+pb.totalPage+')" aria-label="Next">\n' +
                                    '                <span aria-hidden="true">&raquo;</span>\n' +
                                    '            </a>'

                        }
                        else {
                            fenyelis +=
                                    '            <a class="zxfPagenum" href="javascript:loaddate('+(currentPage+1)+')" aria-label="Next">\n' +
                                    '                <span aria-hidden="true">&raquo;</span>\n' +
                                    '            </a>'


                        }
                        fenyelis +=
                                '            <a class="zxfPagenum" href="javascript:loaddate('+pb.totalPage+')" aria-label="Next">\n' +
                                '                <span aria-hidden="true">末页</span>\n' +
                                '            </a>'

                        $("#fenye").html(fenyelis);
                        <#--//列表数据展示-->
                        <#--&lt;#&ndash;//列表数据&ndash;&gt; ,'+user.username+','+user.birthday+','+user.sex+','+user.address+','+user.password+'  onclick="updateData(\''+user.id+'\')" data-toggle="modal" data-target="#renyuan"-->
                        var lis='';
                        for (var i = 0; i < pb.list.length; i++) {
                            var ziliao=pb.list[i];

                            var li='<tr>\n' +
                                    '                <td> '+ziliao.id+'</td>\n' +
                                    '                <td><a href="${request.contextPath}/images/'+ziliao.ziliaolujing+'"  download>'+ziliao.ziliaomingcheng+'</a></td>\n' +
                                    '            </tr>';
                            lis+=li;
                        }
                        $("#show_tbody").html(lis);
                        $("#xingmingspan").html(pb.denglurenxingming);
                    }
                })
        }
    </script>
</head>
<body>
<div class ="top"  style="width: 100%;height: 25px;background-color: gray">
<div class="nav" style="padding-left: 10px">
    <div ><span>外聘教师管理系统</span></div>
</div>
</div>
<div >
<div class="left-side-menu" style="position: absolute">
    <div class="lsm-expand-btn">
        <div class="lsm-mini-btn">
            <label style="width:50px">
                <input type="checkbox" checked="checked">
                <svg viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
                    <circle cx="50" cy="50" r="30" />
                    <path class="line--1" d="M0 40h62c18 0 18-20-17 5L31 55" />
                    <path class="line--2" d="M0 50h80" />
                    <path class="line--3" d="M0 60h62c18 0 18 20-17-5L31 45" />
                </svg>
            </label>
        </div>
    </div>
    <div class="lsm-container">
        <div class="lsm-scroll" >
            <div class="lsm-sidebar">
                <ul>
                    <li class="lsm-sidebar-item">
                        <a href="javascript:;"><i class="my-icon lsm-sidebar-icon " style="background-image: url('${request.contextPath}/img/add_two.png')"></i><span id="xingmingspan">用户姓名</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a class="active" href="gerenzhongxin?dengluren=''"><span>个人中心</span></a></li>
                            <li><a   href="../login/showlogin"><span>退出</span></a></li>
                        </ul>
                    </li>
                    <li class="lsm-sidebar-item">
                        <a href="javascript:;"><i class="my-icon lsm-sidebar-icon icon_2"></i><span>岗前培训资料</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a class="active" href="peixunziliaoguanli"><span>岗前培训资料</span></a></li>

                        </ul>
                    </li>
                    <#--<li class="lsm-sidebar-item">-->
                        <#--<a href="javascript:;"><i class="my-icon lsm-sidebar-icon icon_3"></i><span>评教统计</span><i class="my-icon lsm-sidebar-more"></i></a>-->
                        <#--<ul>-->
                            <#--<li><a href="pingjiao"><span>评教统计</span></a></li>-->
                        <#--</ul>-->
                    <#--</li>-->
                </ul>
            </div>
        </div>
    </div>
</div>
        <div class="box" style="margin-left: 240px;">
            <div class="content">
                <!--搜索输入框及查询、重置按钮-->
                <div class="container content_width">
                    <div class="person_search">
                        <div class="search_input">
                            <div class="input-group mb-3">
                                <span>ID：</span>
                                <input id="search_id" type="text" class="form-control" placeholder="请输入ID">
                            </div>
                        </div>
                        <div class="search_input">
                            <div class="input-group mb-3" >
                                <span>资料名称：</span>
                                <input id="search_ziliaomingcheng" type="text" class="form-control" placeholder="请输入资料名称">
                            </div>
                        </div>
                        <div class="search_input">
                            <button class="btn btn-primary search_btn" type="button" id="search_btn"onclick="loaddate()">查询</button>
                            <button class="btn btn-primary search_btn" type="button" id="back_btn">重置</button>
                        </div>
                    </div>
                    <div class="line"></div>
                </div>
                <!--添加按钮及bootstrap的模态框-->

                <!--表格列表-->
                <table id="tb" class="table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>资料名称</th>
                    </tr>
                    </thead>
                    <tbody id="show_tbody">
                    </tbody>
                </table>
            </div>
            <#--<div class="zxf_pagediv"></div>-->
            <div class="zxf_pagediv">
                <div id="fenye">
                </div>
                <div style="font-size: 20px;color: #843534">
                    <li></li>共
                    <span id="totalPage"></span>页<span id="totalCount"></span>条
                </div>
            </div>
        </div>
</div>
</body>
</html>


