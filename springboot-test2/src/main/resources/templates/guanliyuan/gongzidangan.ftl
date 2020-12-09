<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
    <title>合同历史档案</title>
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
            loaddate(1);
            $('#back_btn').click(function () {
                methods.resectList();
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
                resectList: function () {
                    $('#show_tbody tr').show();
                    $("#jiaoshibianhao").val("");
                    $("#jiaoshixingming").val("");
                    $("#time").val("");
                    loaddate();
                },
                checkMustMes: function () {
                    hasNullMes = true;
                },
                checkRepeat: function () {
                    noRepeat = true;
                }
            };
            /******************************************************/

            var arrJiaoshi = [];
            <#list jiaoshiList as s>
            arrJiaoshi[${s.id}] = [{t: '${s.xingming}', id:${s.id}}];
            </#list>
            var arrJiaoshiid = [];
            <#list jiaoshiList as s>
            arrJiaoshiid[${s.id}] = [{t: '${s.id}', id:${s.id}}];
            </#list>
            document.getElementById('jiaoshibianhao').onchange = function () {
                addOptions(document.getElementById('jiaoshixingming'), arrJiaoshi[this.value]);
            };
            document.getElementById('jiaoshixingming').onchange = function () {
                addOptions(document.getElementById('jiaoshibianhao'), arrJiaoshiid[this.value]);
            };
            function addOptions(s, arr, initValue) {
                if (!arr || arr.length == 0) arr = [{ t: '请选择', id: '' }];
                if (!s) { alert('select对象不存在！'); return false }
                s.options.length = 0;
                var selectedIndex = 0;
                for (var i = 0; i < arr.length; i++) {
                    s.options.add(new Option(arr[i].t, arr[i].id));
                    if (arr[i].id == initValue) selectedIndex = i;
                }
            }
        });
            //分页方法
            function loaddate(currentPage) {
                // alert("usertest loaddate"+currentPage)
                var data = {
                    currentPage: currentPage,
                    jiaoshibianhao: $("#jiaoshibianhao").val().trim(),
                    jiaoshixingming: $("#jiaoshixingming").val().trim(),
                    time: $("#time").val().trim(),
                }
                var url = "gongzidanganfenye"
                $.ajax({
                    url: url,
                    type: "get",
                    data: data,
                    dataType: "json",
                    cache: false,
                    success: function (pb) {
                        $("#show_tbody").empty();
                        $("#fenye").empty();
                    <#--//分页工具条-->
                        $("#totalPage").html(pb.totalPage);
                        $("#totalCount").html(pb.totalCount);
                        var fenyelis =
                                '            <a class="zxfPagenum" href="javascript:loaddate(' + 1 + ')" aria-label="Next">\n' +
                                '                <span aria-hidden="true">首页</span>\n' +
                                '            </a>'

                        if (pb.currentPage - 1 < 1) {
                            fenyelis +=
                                    '            <a href="javascript:loaddate(' + 1 + ')" class="disabled zxfPagenum" aria-label="Previous">\n' +
                                    '                <span aria-hidden="true">&laquo;</span>\n' +
                                    '            </a>'

                        } else {
                            fenyelis +=
                                    '            <a href="javascript:loaddate(' + (currentPage - 1) + ')" class="prebtn zxfPagenum" aria-label="Previous">\n' +
                                    '                <span aria-hidden="true">&laquo;</span>\n' +
                                    '            </a>'

                        }
                        var min;
                        var max;
                        if (pb.totalPage < 10) {
                            min = 1
                            max = pb.totalPage;
                        }
                        else {
                            min = pb.currentPage - 5
                            max = pb.currentPage + 4;
                            if (min < 1) {
                                min = 1;
                                max = min + 9;
                            }
                            if (max > pb.totalPage) {
                                max = pb.totalPage;
                                min = max - 9;
                            }
                        }
                        for (var i = min; i <= max; i++) {
                            if (i == pb.currentPage) {
                                li = '<a class="current zxfPagenum" href="javascript:loaddate(' + i + ')">' + i + '</a>';
                            }
                            li = '<a class="zxfPagenum" href="javascript:loaddate(' + i + ')">' + i + '</a>';
                            fenyelis += li;
                        }
                        if (pb.currentPage + 1 > pb.totalPage) {
                            fenyelis +=
                                    '            <a class="disabled zxfPagenum"href="javascript:loaddate(' + pb.totalPage + ')" aria-label="Next">\n' +
                                    '                <span aria-hidden="true">&raquo;</span>\n' +
                                    '            </a>'

                        }
                        else {
                            fenyelis +=
                                    '            <a class="zxfPagenum" href="javascript:loaddate(' + (currentPage + 1) + ')" aria-label="Next">\n' +
                                    '                <span aria-hidden="true">&raquo;</span>\n' +
                                    '            </a>'


                        }
                        fenyelis +=
                                '            <a class="zxfPagenum" href="javascript:loaddate(' + pb.totalPage + ')" aria-label="Next">\n' +
                                '                <span aria-hidden="true">末页</span>\n' +
                                '            </a>'

                        $("#fenye").html(fenyelis);
                    <#--//列表数据展示-->
                    <#--&lt;#&ndash;//列表数据&ndash;&gt; ,'+user.username+','+user.birthday+','+user.sex+','+user.address+','+user.password+'  onclick="updateData(\''+user.id+'\')" data-toggle="modal" data-target="#renyuan"-->
                        var lis = '';
                        var size = 0;
                        var brea = 0;
                        var t1 = "";
                        var tt = '';
                        // var siz=2;
                        for (var i = 0; i < pb.list.length; i++) {
                            var gongzi=pb.list[i];
                            var time=pb.time[i];
                            var li='<tr>\n' +
                                    '                <td > '+gongzi.id+'</td>\n' +
                                    '                <td > '+gongzi.jiaoshibianhao+'</td>\n' +
                                    '                <td > '+gongzi.jiaoshixingming+'</td>\n' +
                                    '                <td > '+gongzi.yingfajine+'</td>\n' +
                                    '                <td > '+gongzi.fakuanjine+'</td>\n' +
                                    '                <td > '+gongzi.shifajine+'</td>\n' +
                                    '                <td > '+gongzi.shengyujine+'</td>\n' +
                                    '                <td > '+gongzi.shijian+'</td>\n' +
                                    '                <td > '+gongzi.fafangzhuangtai+'</td>\n' +t1+
                                    '                <td > '+gongzi.biaoti+'</td>\n' +
                                    '                <td > '+time+'</td>\n' +
                                    '                <td >\n' +
                                    '                    <a  href="gongzishengcheng?gongziid='+gongzi.id+'" id="shengcheng" class="">生成工资报表</a>\n' +
                                    '                </td>\n' +
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
    <a href="${request.contextPath}/images/"></a>
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
                        <a href="javascript:;"><i class="my-icon lsm-sidebar-icon " ></i><span id="xingmingspan">用户姓名</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a href="gerenzhongxin"><span>个人中心</span></a></li>
                            <li><a href="../login/showlogin"><span>退出</span></a></li>
                        </ul>
                    </li>
                    <li class="lsm-sidebar-item">
                        <a href="javascript:;"><i class="my-icon lsm-sidebar-icon icon_2"></i><span>专家管理</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a href="zhuajiaguanli"><span>专家管理</span></a></li>
<#--                            <li><a href="zhuanjiajilu"><span>专家试听记录管理</span></a></li>-->
                        </ul>
                    </li>
                    <li class="lsm-sidebar-item">
                        <a href="javascript:;"><i class="my-icon lsm-sidebar-icon icon_3"></i><span>教师管理</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a   href="jiaoshiguanli"><span>教师管理</span></a></li>
                            <li><a   href="xueweiguanli"><span>学位管理</span></a></li>
                            <li><a  href="gongzuonianxianguanli"><span>工作年限管理</span></a></li>
                            <li><a  href="zhichengguanli"><span>职称管理</span></a></li>
                            <li><a  href="zonghepingdingjieguoguanli"><span>综合评定结果</span></a></li>
<#--                            <li><a href="jiaoshizongheguanli"><span>教师综合评定管理</span></a></li>-->
                        </ul>
                    </li>
                    <li class="lsm-sidebar-item">
                        <a href="javascript:;"><i class="my-icon lsm-sidebar-icon icon-iconset0308"></i><span>学生管理</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a href="xueshengguanli"><span>学生管理</span></a></li>
                        </ul>
                    </li>
                    <li class="lsm-sidebar-item">
                        <a href="javascript:;"><i class="my-icon lsm-sidebar-icon icon-chongzhi1"></i><span>课程管理</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a href="kechengguanli"><span>课程管理</span></a></li>

                        </ul>
                    </li>
                    <li class="lsm-sidebar-item">
                        <a href="javascript:;"><i class="my-icon lsm-sidebar-icon icon-chongzhi1"></i><span>班级管理</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a href="banjiguanli"><span>班级管理</span></a></li>

                        </ul>
                    </li>
                    <li class="lsm-sidebar-item">
                        <a href="javascript:;"><i class="my-icon lsm-sidebar-icon icon-chongzhi"></i><span>岗前培训资料管理</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a href="peixunziliaoguanli"><span>岗前培训资料管理</span></a></li>
                        </ul>
                    </li>
                    <li class="lsm-sidebar-item">
                        <a href="javascript:;"><i class="my-icon lsm-sidebar-icon icon-chongzhi-copy"></i><span>教学督导</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a href="dudaojilu"><span>督导记录</span></a></li>
                            <li><a href="chufaguanli"><span>处罚等级管理</span></a></li>
                        </ul>
<#--                    </li>-->
<#--                    <li class="lsm-sidebar-item">-->
<#--                        <a href="javascript:;"><i class="my-icon lsm-sidebar-icon icon-users"></i><span>评教管理</span><i class="my-icon lsm-sidebar-more"></i></a>-->
<#--                        <ul>-->
<#--                            <li><a href="tikuguanli"><span>题库管理</span></a></li>-->
<#--                            <li><a href="pingjiaoguanli"><span>评教活动管理</span></a></li>-->
<#--                        </ul>-->
<#--                    </li>-->
                    <li class="lsm-sidebar-item">
                        <a href="javascript:;"><i class="my-icon lsm-sidebar-icon icon_3"></i><span>试讲档案管理</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a href="shijiangguanli"><span>试讲档案管理</span></a></li>
                        </ul>
                    </li>
                    <li class="lsm-sidebar-item">
                        <a href="javascript:;"><i class="my-icon lsm-sidebar-icon icon_3"></i><span>合同管理</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a  href="hetongguanli"><span>合同管理</span></a></li>
<#--                            <li><a  href="hetongdangan"><span>合同档案</span></a></li>-->
                            <li><a  href="xinshuiguanli"><span>薪资标准管理</span></a></li>
                        </ul>
                    </li>
                    <li class="lsm-sidebar-item">
                        <a href="javascript:;"><i class="my-icon lsm-sidebar-icon icon_3"></i><span>工资管理</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a href="gongziguanli"><span>工资管理</span></a></li>
                            <li><a class="active" href="gongzidangan"><span>工资档案</span></a></li>
                        </ul>
                    </li>
                    <li class="lsm-sidebar-item">
                        <a href="javascript:;"><i class="my-icon lsm-sidebar-icon icon_3"></i><span>上课班级管理</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a href="shangkeguanli"><span>上课班级管理</span></a></li>
                        </ul>
                    </li>

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
                                <span>教师编号：</span>
                                <input id="jiaoshibianhao" type="text" class="form-control" placeholder="请输入教师编号">
                            </div>
                        </div>
                        <div class="search_input">
                            <div class="input-group mb-3" >
                                <span>教师姓名：</span>
                                <input id="jiaoshixingming" type="text" class="form-control" placeholder="请输入教师姓名">
                            </div>
                        </div>
                        <div class="search_input">
                            <div class="input-group mb-3" >
                                <span>日期选择：</span>
                                <input id="time" type="text" class="form-control" placeholder="格式如：2020-03">
                            </div>
                        </div>
                        <div class="search_input">
                            <button class="btn btn-primary search_btn" type="button" id="search_btn" onclick="loaddate(1)">查询</button>
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
                        <th>教师编号</th>
                        <th>教师姓名</th>
                        <th>应发金额</th>
                        <th>罚款金额</th>
                        <th>实发金额</th>
                        <th>剩余金额</th>
                        <th>工资时间</th>
                        <th>发放状态</th>
                        <th>工资标题</th>
                        <th>创建时间</th>
                        <th>操作</th>
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


