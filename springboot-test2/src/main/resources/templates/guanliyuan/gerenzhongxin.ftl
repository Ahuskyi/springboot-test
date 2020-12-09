<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
    <title>个人中心</title>
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
            $('#show_tbody').on('click','.edit', function () {
                trIndex = $('.edit', '#show_tbody').index($(this));
                $("#h4").html("编辑");
                //为id设置只读属性
                $("#id").attr("readonly","readonly");
                addEnter = false;
                $(this).parents('tr').addClass('has_case');
                methods.editHandle(trIndex);
            })
            $('#search_btn').click(function () {
                methods.seachName();
            })
            $('#back_btn').click(function () {
                $('#Ktext').val(' ');
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
                    for (var i=0; i<tar.find('td').length-1;i++) {
                        var a = tar.children('td').eq(i).html();
                        nowConArr.push(a);
                    }

                    $('#renyuan').modal('show');

                    for (var j=0;j<4;j++) {
                        tarInp.eq(j).val(nowConArr[j])
                    }
                    // for (var i=5;j<7;j++) {
                    //     tarInp.eq(j).val(nowConArr[i+1])
                    // }
                    var xingbie=nowConArr[4];
                    $("#xingbie").get(0).remove(0);//取id为性别的这个元素
                    $("#xingbie").prepend("<option value='"+xingbie+"' selected='selected'>"+xingbie+"</option>");


                },
                setStr: function () {

                    tdStr = '';
                    for (var a=0; a<tarInp.length; a++) {
                        tdStr+= '<td>' + tarInp.eq(a).val() + '</td>'
                    }
                    for (var b=0; b<tarSel.length; b++) {
                        tdStr+= '<td>' + tarSel.eq(b).val() + '</td>'
                    }
                    tdStr+= '<td><a href="#" class="edit">编辑</a> </td>';

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
                    $('#show_tbody tr').show();
                },
                checkMustMes: function () {

                    if ($('.userid').val().trim()==='') {
                        bootbox.alert({
                            title: "来自火星的提示",
                            message: "id为必选项，请填写",
                            closeButton:false
                        })
                        hasNullMes = true;
                        return
                    }
                    if ($('.username').val().trim()==='') {
                        bootbox.alert({
                            title: "来自火星的提示",
                            message: "用户名为必选项，请填写",
                            closeButton:false
                        })
                        hasNullMes = true;
                        return
                    }
                    if ($('.birthday').val().trim()==='') {
                        bootbox.alert({
                            title: "来自火星的提示",
                            message: "密码为必选项，请填写",
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
        // <修改方法>
        function submitData() {

            var data = {
                id : $("#id").val(),
                yonghuming : $("#yonghuming").val(),
                mima : $("#mima").val(),
                xingming : $("#xingming").val(),
                xingbie : $("#xingbie").val(),
                dianhua : $("#dianhua").val(),
                youxiang : $("#youxiang").val(),
                dizhi : $("#dizhi").val()
            };
            var url = "gerenxinxixiugai";
            $.ajax({
                url : url,
                type : "post",
                data : data,
                dataType : "json",
                cache : false,
                success : function (map) {
                        alert(map.result);
                    setTimeout(function(){
                        window.location.href="../guanliyuan/gerenzhongxin";
                    }, 3000);
                },

            })
        }
        //分页方法
        function loaddate() {
                // alert("usertest loaddate"+currentPage)
                var data = {
                 id:1
                }
                var url = "gerenxinxizhanshi"
                $.ajax({
                    url : url,
                    type : "get",
                    data : data,
                    dataType : "json",
                    cache : false,
                    success : function (map) {
                        user=map.guanliyuan;
                        $("#show_tbody").empty();

                        <#--//列表数据展示-->
                        var lis='<tr>\n' +
                                    '                <td> '+user.id+'</td>\n' +
                                    '                <td> '+user.yonghuming+'</td>\n' +
                                    '                <td> '+user.mima+'</td>\n' +
                                    '                <td> '+user.xingming+'</td>\n' +
                                    '                <td> '+user.xingbie+'</td>\n' +
                                    '                <td> '+user.dianhua+'</td>\n' +
                                    '                <td> '+user.youxiang+'</td>\n' +
                                    '                <td> '+user.dizhi+'</td>\n' +
                                    '                <td>\n' +
                                    '                    <a  href="#" id="edit" class="edit" >编辑</a>\n' +
                                    '                </td>\n' +
                                    '            </tr>';
                        $("#show_tbody").html(lis);
                        $("#xingmingspan").html(user.xingming);
                        // $("#id").readOnly;
                        // $("#id").unselectable;
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
                            <li><a  href="jiaoshiguanli"><span>教师管理</span></a></li>
                            <li><a  href="xueweiguanli"><span>学位管理</span></a></li>
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
                        <div class="title">个人中心</div>
                    </div>
                    <div class="line"></div>
                </div>
                <!--添加按钮及bootstrap的模态框-->
                <div class="export">
                    <#--<button id="new_add" type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#renyuan">-->
                        <#--<img src="${request.contextPath}/img/add_two.png"/>-->
                        <#--<span>添加</span>-->
                    <#--</button>-->
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
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>id</label>
                                            </td>
                                            <td><input  id="id" name="id" class="userid" type="text" placeholder="请输入id"readonly  unselectable="on"/></td>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>用户名</label>
                                            </td>
                                            <td><input  id="yonghuming" name="yonghuming" class="username" type="text" placeholder="请输入用户名"/></td>
                                        </tr>
                                        <tr>
                                            <td class="tb_bg"><label for="">密码</label></td>
                                            <td><input id="mima" name="mima" type="text" class="birthday"placeholder="请输入密码"/></td>
                                            <td class="tb_bg"><label for="">性名</label></td>
                                            <td><input id="xingming" name="xingming" type="text" placeholder="请输入姓名"/></td>
                                        </tr>
                                        <tr>
                                            <td class="tb_bg"><label for="">性别</label></td>
                                            <td>
                                                <select id="xingbie" name="xingbie"  class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                    <option>请选择</option>
                                                    <option value="男">男</option>
                                                    <option value="女">女</option>
                                                </select>
                                            </td>
                                            <td class="tb_bg"><label for="">电话</label></td>
                                            <td><input id="dianhua" name="dianhua" type="text" placeholder="请输入电话"/></td>
                                        </tr>
                                        <tr>
                                            <td class="tb_bg"><label for="">邮箱</label></td>
                                            <td><input id="youxiang" name="youxiang" type="text" placeholder="请输入邮箱"/></td>
                                            <td class="tb_bg"><label for="">地址</label></td>
                                            <td><input id="dizhi" name="dizhi" type="text" placeholder="请输入地址"/></td>
                                        </tr>
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
                        <th>ID</th>
                        <th>用户名</th>
                        <th>密码</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>电话</th>
                        <th>邮箱</th>
                        <th>地址</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="show_tbody">
                    </tbody>
                </table>
            </div>
            <#--<div class="zxf_pagediv"></div>-->

</div>
</body>
</html>


