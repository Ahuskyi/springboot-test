<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
    <title>工资管理</title>
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
            });
            $('#new_add').click(function () {
                methods.new()
                $("#h4").html("添加");
                $("#id").attr("readonly","readonly");
                $("#id").removeAttr("readonly");
                // $("#jiaoshibianhao").prop("disabled",false);
                // $("#zhuanzhanghuming").prop("disabled",false);
                // $("#pinyongxingshi").prop("disabled",false);
                // $("#hetongleixing").prop("disabled",false);
                // $("#chuangjianshijian").removeAttr("readonly");
            });
            $('#show_tbody').on('click','.edit', function () {
                trIndex = $('.edit', '#show_tbody').index($(this));
                $("#h4").html("编辑");
                $("#id").attr("readonly","readonly");

                // $("#jiaoshibianhao").attr("disable","disable");
                // $("#jiaoshibianhao").prop("disabled",true);
                // $("#zhuanzhanghuming").prop("disabled",true);
                // $("#pinyongxingshi").prop("disabled",true);
                // $("#hetongleixing").prop("disabled",true);
                // $("#chuangjianshijian").attr("readonly","readonly");
                addEnter = false;
                $(this).parents('tr').addClass('has_case');
                methods.editHandle(trIndex);
            });
            $('#search_btn').click(function () {
                // methods.seachName();
            });
            $('#back_btn').click(function () {

                methods.resectList();
            });
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
                    $('#renyuan').modal('hide');
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
                    //alert(nowConArr);
                    //alert(nowConArr[14]);
                    $('#renyuan').modal('show');
                    tarInp.eq(0).val(nowConArr[0]);
                    tarInp.eq(1).val(nowConArr[3]);
                    tarSel.eq(0).get(0).remove(0);
                   tarSel.eq(0).prepend("<option value='"+nowConArr[8]+"' selected='selected'>"+nowConArr[8]+"</option>");

                    // var da=nowConArr[14];
                    // var ai=nowConArr[1];
                    // var strtwo = da.split('-').join('/');
                    // var datetwo =new Date(strtwo);
                    // tarInp.eq(4).val(datetwo);
                    // $("#jiaoshibianhao").attr("value",ai);
                    // for (var j=0;j<tarInp.length;j++) {
                    //     tarInp.eq(j).val(nowConArr[j])
                    // }
                    // for (var p=0;p<tarSel.length;p++) {
                    //     var the_p = p+tarInp.length;
                    //     tarSel.eq(p).val(nowConArr[the_p]);
                    // }

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
                    $("#search_jiaoshibianhao").val('');
                    $("#search_jiaoshixingming").val('');
                    loaddate();
                    // $('#show_tbody tr').show();
                },
                checkMustMes: function () {
                    hasNullMes = true;
                },
                checkRepeat: function () {
                    noRepeat = true;
                }
            }
                /******************************************************/

        });
        // <添加方法>
        function submitData() {


            var data = {
                id : $("#id").val(),
                yingfajine : $("#yingfajine").val(),
                fafangzhuangtai:  $("#fafangzhuangtai").find("option:selected").text().trim(),
            };
            var url = "gongzixiugai";
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
                window.location.href="../guanliyuan/gongziguanli";
            }, 3000);

        }
        //分页方法
        function loaddate(currentPage) {
                // alert("usertest loaddate"+currentPage)
                var data = {
                    currentPage : currentPage,
                    jiaoshibianhao: $("#search_jiaoshibianhao").val().trim(),
                    jiaoshixingming: $("#search_jiaoshixingming").val().trim(),
                }
                var url = "gongzifenye"
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
                        var size=0;
                        var brea=0;
                        var t1="";
                        var tt='';
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
                                    '                    <a  href="#" id="edit" class="edit" >编辑</a>\n' +
                                    '                </td>\n' +
                                    // '                <td >\n' +
                                    // '                    <a  href="gongzishengcheng?gongziid='+gongzi.id+'" id="shengcheng" class="">生成工资文档</a>\n' +
                                    // '                </td>\n' +
                                    '            </tr>';
                            lis+=li;
                        }
                        $("#show_tbody").html(lis);
                        $("#xingmingspan").html(pb.denglurenxingming);
                    }
                })
        }
        function yijianshengcheng() {
            var data = {
            };
            var url = "yijiangongzishengcheng";
            $.ajax({
                url : url,
                type : "post",
                data : data,
                dataType : "json",
                cache : false,
                success : function (result) {

                },

            })
        }
    </script>
</head>
<#--</分页>-->
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
                            <li><a   href="jiaoshiguanli"><span>教师管理</span></a></li>
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
                            <li><a  class="active" href="gongziguanli"><span>工资管理</span></a></li>
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
                                <span>教师编号：</span>
                                <input id="search_jiaoshibianhao" type="text" class="form-control" placeholder="请输入教师编号">
                            </div>
                        </div>
                        <div class="search_input">
                            <div class="input-group mb-3" >
                                <span>教师姓名：</span>
                                <input id="search_jiaoshixingming" type="text" class="form-control" placeholder="请输入教师姓名">
                            </div>
                        </div>
                        <div class="search_input">
                            <button class="btn btn-primary search_btn" type="button" id="search_btn" onclick="loaddate(1)">查询</button>
                            <button class="btn btn-primary search_btn" type="button" id="back_btn">重置</button>
                        </div>
                        <div class="search_input">
                            <a class="btn btn-primary search_btn" style="width:200px;text-align: center;color: #fff8f9;line-height: 35px" type="button" href="yijiangongzishengcheng" >一键生成工资报表</a>
                        </div>
<#--                        <div class="search_input">-->
<#--                            <a class="btn btn-primary search_btn" style="width:200px;text-align: center;color: #fff8f9;line-height: 35px" type="button" href="yijianfafang" >一键发放</a>-->
<#--                        </div>-->
                    </div>
                    <div class="line"></div>
                </div>
                <!--添加按钮及bootstrap的模态框-->
                <div class="export">

                    <div class="modal fade" id="renyuan">
                        <div class="modal-dialog modal-lg modal_position">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 id="h4" class="modal-title">编辑</h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>
                                <div class="modal-body">
                                    <table id="xztb" class="table">
                                        <!--新修改弹窗的样式-->
                                        <tbody>
                                        <tr>
                                            <td class="tb_bg"><label for="id"><font style="font-size: 14px; color: red;">*</font>工资编号</label></td>
                                            <td><input   id="id" name="id" class="id " type="text" placeholder="自动生成" readonly="readonly"/></td>

                                        </tr>
                                        <tr>
                                            <td class="tb_bg"><label for="yingfajine">应发金额</label></td>
                                            <td><input id="yingfajine" name="yingfajine" type="text" placeholder="请输入应发金额"/></td>
                                        </tr>

                                        <tr>
                                            <td class="tb_bg"><label for="fafangzhuangtai">发放状态</label></td>
                                            <td>
                                                <select id="fafangzhuangtai" name="fafangzhuangtai" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                    <option  >请选择</option>
                                                    <option value="未发放" selected>未发放</option>
                                                    <option value="已发放" >已发放</option>
                                                </select>
                                            </td>
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
<#--                        <th>更多操作</th>-->
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


