<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
    <title>教师管理</title>
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
        //预加载方法
        $(function () {
            loaddate();
            $('#add_btn').click(function () {

                methods.addHandle()
            });
            $('#new_add').click(function () {
                methods.new()
                $("#h4").html("添加");
                $("#id").attr("readonly","readonly");

            });
            $('#show_tbody').on('click','.edit', function () {
                trIndex = $('.edit', '#show_tbody').index($(this));
                $("#h4").html("编辑");
                $("#id").attr("readonly","readonly");
                addEnter = false;
                $(this).parents('tr').addClass('has_case');
                methods.editHandle(trIndex);


            });
            $('#search_btn').click(function () {
                methods.seachName();
            });
            $('#back_btn').click(function () {
                $('#search_id').val('');
                $('#search_yonghuming').val('');
                $('#search_xingming').val('');
                loaddate();
                //methods.resectList();
            });
            $('#renyuan').on('hide.bs.modal',function() {
                addEnter = true;
                $('#show_tbody tr').removeClass('has_case');
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
                    for (var j=0;j<6;j++) {
                        tarInp.eq(j).val("");
                    }
                    var now = new Date();
                    //格式化日，如果小于9，前面补0
                    var day = ("0" + now.getDate()).slice(-2);
                    //格式化月，如果小于9，前面补0
                    var month = ("0" + (now.getMonth() + 1)).slice(-2);
                    //拼装完整日期格式
                    var today = now.getFullYear()+"-"+(month)+"-"+(day) ;
                    //完成赋值
                    $('#chuangjianshijian').attr("value",today.toString().trim());
                    tarInp.eq(7).val("");
                    $("#xingbie").get(0).remove(0);
                    $("#xueweizheng").get(0).remove(0);
                    $("#xuelizheng").get(0).remove(0);
                    $("#zhichengzheng").get(0).remove(0);
                    var text="请选择";
                    $("#xingbie").prepend("<option  selected='selected'>"+text+"</option>");
                    $("#xueweizheng").prepend("<option  selected='selected'>"+text+"</option>");
                    $("#xuelizheng").prepend("<option  selected='selected'>"+text+"</option>");
                    $("#zhichengzheng").prepend("<option  selected='selected'>"+text+"</option>");
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
                            //$('#show_tbody').append('<tr>' + tdStr + '</tr>');
                            $('#renyuan').modal('hide');
                    }
                    }else{
                        methods.setStr();
                        //$('#show_tbody tr').eq(trIndex).empty().append(tdStr);
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
                            tarInp.eq(0).val(nowConArr[0]);
                            tarInp.eq(1).val(nowConArr[1]);
                            tarInp.eq(2).val(nowConArr[2]);
                            tarInp.eq(3).val(nowConArr[3]);
                            tarInp.eq(4).val(nowConArr[5]);
                            tarInp.eq(5).val(nowConArr[6]);
                            tarInp.eq(6).attr("value",nowConArr[7].toString().trim());
                            tarInp.eq(7).val(nowConArr[8]);
                    var xingbie=nowConArr[4].toString().trim();
                    var xiewei=nowConArr[9].toString().trim();
                    var xueli=nowConArr[10].toString().trim();
                    var zhicheng=nowConArr[11].toString().trim();
                    $("#xingbie").get(0).remove(0);
                    $("#xueweizheng").get(0).remove(0);
                    $("#xuelizheng").get(0).remove(0);
                    $("#zhichengzheng").get(0).remove(0);
                    $("#xingbie").prepend("<option value='"+xingbie+"' selected='selected'>"+xingbie+"</option>");
                    $("#xueweizheng").prepend("<option value='"+xiewei+"' selected='selected'>"+xiewei+"</option>");
                    $("#xuelizheng").prepend("<option value='"+xueli+"' selected='selected'>"+xueli+"</option>");
                    $("#zhichengzheng").prepend("<option value='"+zhicheng+"' selected='selected'>"+zhicheng+"</option>");
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

                },
                resectList: function () {
                    $('#show_tbody tr').show();
                },
                checkMustMes: function () {
                    // if ($('.id').val().trim()==='') {
                    //     bootbox.alert({
                    //         title: "来自火星的提示",
                    //         message: "id为必选项，请填写",
                    //         closeButton:false
                    //     })
                    //     hasNullMes = true;
                    //     return
                    // }
                    // if ($('.yonghuming').val().trim()==='') {
                    //     bootbox.alert({
                    //         title: "来自火星的提示",
                    //         message: "用户名为必选项，请填写",
                    //         closeButton:false
                    //     })
                    //     hasNullMes = true;
                    //     return
                    // }
                    // if ($('.mima').val().trim()==='') {
                    //     bootbox.alert({
                    //         title: "来自火星的提示",
                    //         message: "密码为必选项，请填写",
                    //         closeButton:false
                    //     })
                    //     hasNullMes = true;
                    //     return
                    // }

                },
                checkRepeat: function () {
                    noRepeat = true;
                }
            }

        });
        // <添加方法>
        function submitData() {
            var a=true;
            if ($('#yonghuming').val().trim()==='') {
                bootbox.alert({
                    title: "来自火星的提示",
                    message: "用户名为必选项，请填写",
                    closeButton:false
                })
                a = false;
            }
            if ($('#mima').val().trim()==='') {
                bootbox.alert({
                    title: "来自火星的提示",
                    message: "密码为必选项，请填写",
                    closeButton:false
                })
                a = false;
            }
            if (a){
            var data = {
                id : $("#id").val(),
                yonghuming : $("#yonghuming").val(),
                mima : $("#mima").val(),
                xingming : $("#xingming").val(),
                xingbie : $("#xingbie").val(),
                dianhua : $("#dianhua").val(),
                canjiagongzuoshijian: $("#canjiagongzuoshijian").val(),
                shenfenzheng: $("#shenfenzheng").val(),
                xueweizheng: $("#xueweizheng").val(),
                xuelizheng: $("#xuelizheng").val(),
                zhichengzheng: $("#zhichengzheng").val(),
                chuangjianshijian: $("#chuangjianshijian").val(),
            };
            var url = "jiaoshixinxixiugai";
            $.ajax({
                url : url,
                type : "post",
                data : data,
                dataType : "json",
                cache : false,
                success : function (map) {
                    bootbox.alert({
                        title: "来自火星的提示",
                        message: map.result,
                        closeButton:false
                    })
                },

            })
            setTimeout(function(){
                window.location.href="../guanliyuan/jiaoshiguanli";
            }, 3000);
            }

        }
        //分页方法
        function loaddate(currentPage) {
                // alert("usertest loaddate"+currentPage)
                var data = {
                    currentPage : currentPage,
                    search_id : $("#search_id").val(),
                    search_yonghuming : $("#search_yonghuming").val(),
                    search_xingming : $("#search_xingming").val(),
                }
                var url = "jiaoshixinxifenye"
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
                            var teacher=pb.list[i];

                            var li='<tr>\n' +
                                    '                <td> '+teacher.id+'</td>\n' +
                                    '                <td> '+teacher.yonghuming+'</td>\n' +
                                    '                <td> '+teacher.mima+'</td>\n' +
                                    '                <td> '+teacher.xingming+'</td>\n' +
                                    '                <td> '+teacher.xingbie+'</td>\n' +
                                    '                <td> '+teacher.dianhua+'</td>\n' +
                                    '                <td><a href="${request.contextPath}/images/'+teacher.shenfenzhenglujing+'">'+teacher.shenfenzheng+'</a></td>\n' +
                                    '                <td> '+teacher.chuangjianshijian+'</td>\n' +
                                    '                <td><a href="${request.contextPath}/images/'+teacher.canjiagongzuoshijianlujing+'">'+teacher.canjiagongzuoshijian+'</a></td>\n' +
                                    '                <td><a href="${request.contextPath}/images/'+teacher.xueweizhenglujing+'">'+teacher.xueweizheng+'</a></td>\n' +
                                    '                <td><a href="${request.contextPath}/images/'+teacher.xuelizhenglujing+'">'+teacher.xuelizheng+'</a></td>\n' +
                                    '                <td><a href="${request.contextPath}/images/'+teacher.zhichengzhenglujing+'">'+teacher.zhichengzheng+'</a></td>\n' +
                                    '                <td> '+teacher.pingdingdengji+'</td>\n' +
                                    '                <td><a href="${request.contextPath}/images/'+teacher.zongheshuipingfenshulujing+'">'+teacher.zongheshuipingfenshu+'</a></td>\n' +
                                    '                <td>\n' +
                                    '                    <a  href="#" id="edit" class="edit" >编辑</a>\n' +
                                    '                    <a  href="jiaoshidel?jiaoshiid='+teacher.id+'" id="del" class="del">删除</a>\n' +
                                    '                </td>\n' +
                                    '                <td>\n' +
                                    '                    <a  href="dengjihesuan?jiaoshiid='+teacher.id+'" id="more" class="">等级核算</a>\n' +
                                    '                    <a  href="toUpload?jiaoshiid='+teacher.id+'" id="more" class="">上传附件</a>\n' +
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
                            <#--<li><a href="zhuanjiajilu"><span>专家试听记录管理</span></a></li>-->
                        </ul>
                    </li>
                    <li class="lsm-sidebar-item">
                        <a href="javascript:;"><i class="my-icon lsm-sidebar-icon icon_3"></i><span>教师管理</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a  class="active" href="jiaoshiguanli"><span>教师管理</span></a></li>
                            <li><a   href="xueweiguanli"><span>学位管理</span></a></li>
                            <li><a  href="gongzuonianxianguanli"><span>工作年限管理</span></a></li>
                            <li><a  href="zhichengguanli"><span>职称管理</span></a></li>
                            <li><a  href="zonghepingdingjieguoguanli"><span>综合评定结果</span></a></li>
                            <#--<li><a href="jiaoshizongheguanli"><span>教师综合评定管理</span></a></li>-->
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
                    </li>
                    <#--<li class="lsm-sidebar-item">-->
                        <#--<a href="javascript:;"><i class="my-icon lsm-sidebar-icon icon-users"></i><span>评教管理</span><i class="my-icon lsm-sidebar-more"></i></a>-->
                        <#--<ul>-->
                            <#--<li><a href="tikuguanli"><span>题库管理</span></a></li>-->
                            <#--<li><a href="pingjiaoguanli"><span>评教活动管理</span></a></li>-->
                        <#--</ul>-->
                    <#--</li>-->
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
                            <#--<li><a  href="hetongdangan"><span>合同档案</span></a></li>-->
                            <li><a href="xinshuiguanli"><span>薪资标准管理</span></a></li>
                        </ul>
                    </li>
                    <li class="lsm-sidebar-item">
                        <a href="javascript:;"><i class="my-icon lsm-sidebar-icon icon_3"></i><span>工资管理</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a href="gongziguanli"><span>工资管理</span></a></li>
                            <li><a href="gongzidangan"><span>工资档案</span></a></li>
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
                                <span>id：</span>
                                <input id="search_id" type="text" class="form-control" placeholder="请输入ID">
                            </div>
                        </div>
                        <div class="search_input">
                            <div class="input-group mb-3" >
                                <span>用户名：</span>
                                <input id="search_yonghuming" type="text" class="form-control" placeholder="请输入用户名">
                            </div>
                        </div>
                        <div class="search_input">
                            <div class="input-group mb-3" >
                                <span>姓名：</span>
                                <input id="search_xingming" type="text" class="form-control" placeholder="请输入姓名">
                            </div>
                        </div>
                        <div class="search_input">
                            <button class="btn btn-primary search_btn" type="button" id="search_btn" onclick="loaddate()">查询</button>
                            <button class="btn btn-primary search_btn" type="button" id="back_btn">重置</button>
                        </div>
                    </div>
                    <div class="line"></div>
                </div>
                <!--添加按钮及bootstrap的模态框-->
                <div class="export">
                    <button id="new_add" type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#renyuan">
                        <img src="${request.contextPath}/img/add_two.png"/>
                        <span>添加</span>
                    </button>
                    <div class="modal fade" id="renyuan">
                        <div class="modal-dialog modal-lg modal_position">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 id="h4" class="modal-title">添加</h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>
                                <div class="modal-body">
                                    <table id="xztb" class="table">
                                        <!--新修改弹窗的样式-->
                                        <tbody>
                                        <tr>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>id</label></td>
                                            <td><input  id="id" name="id" class="id" type="text" placeholder="自动生成"/></td>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>用户名</label></td>
                                            <td><input  id="yonghuming" name="yonghuming" class="yonghuming" type="text" placeholder="请输入用户名"/></td>
                                        </tr>
                                        <tr>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>密码</label>
                                            </td>
                                            <td><input id="mima" name="mima" class="mima" type="text" placeholder="请输入密码"/></td>
                                            <td class="tb_bg"><label for="">姓名</label></td>
                                            <td><input id="xingming" name="xingming" type="text" placeholder="请输入姓名"/></td>
                                        </tr>
                                        <tr>
                                            <td class="tb_bg"><label for="">性别</label></td>
                                            <td>
                                                <select id="xingbie" name="xingbie" class="form-control select_down" style="font-size: 13px; color: #666;">
                                                <option>请选择</option>
                                                    <option>男</option>
                                                    <option>女</option>
                                                </select></td>
                                            <td class="tb_bg"><label for="">电话</label></td>
                                            <td><input id="dianhua" name="dianhua" type="text" placeholder="请输入电话"/></td>
                                        </tr>
                                        <tr>
                                            <td class="tb_bg"><label for="">身份证号</label></td>
                                            <td><input id="shenfenzheng" name="shenfenzheng" type="text" placeholder="请输入身份证号"/></td>
                                            <td class="tb_bg"><label for="">创建时间</label></td>
                                            <td><input id="chuangjianshijian" name="chuangjianshijian" type="date" placeholder="请输入创建时间"/></td>
                                        </tr>
                                        <tr>
                                            <td class="tb_bg"><label for="">参加工作时间</label></td>
                                            <td><input id="canjiagongzuoshijian" name="canjiagongzuoshijian" type="text" placeholder="请输入"/></td>
                                            <td class="tb_bg"><label for="">学位</label></td>
                                            <td>
                                                <select id="xueweizheng" name="xueweizheng"  class="form-control select_down" style="font-size: 13px; color: #666;">
                                                <option>---请选择---</option>
                                                    <#list xueweiList as x>
                                                    <option value="${x.xuewei}">${x.xuewei}</option>
                                                    </#list>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="tb_bg"><label for="">学历</label></td>
                                            <td>
                                                <select id="xuelizheng" name="xuelizheng"  class="form-control select_down" style="font-size: 13px; color: #666;">
                                                    <option>---请选择---</option>
                                                    <option value="本科">本科</option>
                                                    <option value="硕士">硕士</option>
                                                    <option value="博士">博士</option>
                                                    <option value="博士后">博士后</option>
                                                </select>
                                            </td>
                                            <td class="tb_bg"><label for="">职称</label></td>
                                            <td>
                                                <select id="zhichengzheng" name="zhichengzheng"  class="form-control select_down" style="font-size: 13px; color: #666;">
                                                    <option>---请选择---</option>
                                                    <#list zhichengList as x>
                                                    <option value="${x.zhicheng}">${x.zhicheng}</option>
                                                    </#list>
                                                </select>
                                            </td>
                                        </tr>
                                        <#--<tr>-->
                                            <#--<td class="tb_bg"><label for="">评定等级</label></td>-->
                                            <#--<td><input id="pingdingdengji" name="pingdingdengji" type="text" placeholder="请输入"/></td>-->
                                            <#--<td class="tb_bg"><label for="">综合水平评定分数</label></td>-->
                                            <#--<td><input id="zongheshuipingfenshu" name="zongheshuipingfenshu" type="text" placeholder="请输入"/></td>-->
                                            <#--</tr>-->
                                        </tbody>
                                    </table>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                                    <button id="add_btn" onclick="submitData()" class="btn btn-secondary">确定</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--表格列表-->
                <table id="tb" class="table">
                    <thead>
                    <tr>
                        <th style="width: 0px">ID</th>
                        <th style="width: 0px">用户名</th>
                        <th style="width: 0px">密码</th>
                        <th style="width: 180px">姓名</th>
                        <th style="width: 100px">性别</th>
                        <th style="width: 0px">电话</th>
                        <th style="width: 100px">身份证号</th>
                        <th style="width: 130px">创建时间</th>
                        <th style="width: 140px">参加工作时间</th>
                        <th style="width: 110px">学位</th>
                        <th style="width: 110px">学历</th>
                        <th style="width: 110px">职称</th>
                        <th style="width: 100px">评定等级</th>
                        <th style="width: 150px">综合水平分数</th>
                        <th style="width: 120px">操作</th>
                        <th style="width: 250px">更多操作</th>
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


