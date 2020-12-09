<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
    <title>试讲档案管理</title>
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
                //methods.seachName();
            });
            $('#back_btn').click(function () {
                //$('#Ktext').val(' ');
                methods.resectList();
            });
            $('#renyuan').on('hide.bs.modal',function() {
                addEnter = true;
                $('#show_tbody tr').removeClass('has_case');
                //$('#xztb input').val(' ');
                //$('#xztb select').find('option:first').prop('selected', true)
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
                    tarInp.eq(0).val('');
                    tarInp.eq(2).val('');
                    tarInp.eq(3).val('');
                    var now = new Date();
                    //格式化日，如果小于9，前面补0
                    var day = ("0" + now.getDate()).slice(-2);
                    //格式化月，如果小于9，前面补0
                    var month = ("0" + (now.getMonth() + 1)).slice(-2);
                    //拼装完整日期格式
                    var today = now.getFullYear()+"-"+(month)+"-"+(day) ;
                    tarInp.eq(1).attr("value",today.toString().trim());
                    $("#shijiangneirong").val('');
                    $("#zhuanjiaid").get(0).remove(0);
                    $("#zhuanjiaxingming").get(0).remove(0);
                    $("#jiaoshiid").get(0).remove(0);
                    $("#jiaoshixingming").get(0).remove(0);
                    $("#shijiangpingjia").get(0).remove(0);
                    $("#xingshi").get(0).remove(0);
                    $("#shijiangkecheng").get(0).remove(0);
                    var text="请选择";
                    $("#zhuanjiaid").prepend("<option value='"+text+"' selected='selected'>"+text+"</option>");
                    $("#zhuanjiaxingming").prepend("<option value='"+text+"' selected='selected'>"+text+"</option>");
                    $("#jiaoshiid").prepend("<option value='"+text+"' selected='selected'>"+text+"</option>");
                    $("#jiaoshixingming").prepend("<option value='"+text+"' selected='selected'>"+text+"</option>");
                    $("#shijiangpingjia").prepend("<option value='"+text+"' selected='selected'>"+text+"</option>");
                    $("#xingshi").prepend("<option value='"+text+"' selected='selected'>"+text+"</option>");
                    $("#shijiangkecheng").prepend("<option value='"+text+"' selected='selected'>"+text+"</option>");
                },
                addHandle: function (the_index) {
                    hasNullMes = false;
                    methods.checkMustMes();
                    if (hasNullMes) {
                        return;
                    }else {
                        methods.checkRepeat();
                        if (noRepeat) {
                            //methods.setStr();
                            //$('#show_tbody').append('<tr>' + tdStr + '</tr>');
                            $('#renyuan').modal('hide');
                            methods.resectList();
                        }
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
                    tarInp.eq(1).attr("value",nowConArr[1].toString().trim());
                    tarInp.eq(2).val(nowConArr[8]);
                    tarInp.eq(3).val(nowConArr[10]);
                    $("#shijiangneirong").val(nowConArr[7])
                    // tarInp.eq(4).val(nowConArr[8]);
                    // tarInp.eq(5).val(nowConArr[10]);
                    var zhuanjiaid=nowConArr[2].toString().trim();
                    var zhuanjiaxingming=nowConArr[3].toString().trim();
                    var jiaoshiid=nowConArr[4].toString().trim();
                    var jiaoshixingming=nowConArr[5].toString().trim();
                    var shijiangkecheng=nowConArr[6].toString().trim();
                    var shijiangpingjia=nowConArr[9].toString().trim();
                    var xingshi=nowConArr[11].toString().trim();
                    $("#zhuanjiaid").get(0).remove(0);
                    $("#zhuanjiaxingming").get(0).remove(0);
                    $("#jiaoshiid").get(0).remove(0);
                    $("#jiaoshixingming").get(0).remove(0);
                    $("#shijiangpingjia").get(0).remove(0);
                    $("#xingshi").get(0).remove(0);
                    $("#shijiangkecheng").get(0).remove(0);
                    $("#zhuanjiaid").prepend("<option value='"+zhuanjiaid+"' selected='selected'>"+zhuanjiaid+"</option>");
                    $("#zhuanjiaxingming").prepend("<option value='"+zhuanjiaxingming+"' selected='selected'>"+zhuanjiaxingming+"</option>");
                    $("#jiaoshiid").prepend("<option value='"+jiaoshiid+"' selected='selected'>"+jiaoshiid+"</option>");
                    $("#jiaoshixingming").prepend("<option value='"+jiaoshixingming+"' selected='selected'>"+jiaoshixingming+"</option>");
                    $("#shijiangpingjia").prepend("<option value='"+shijiangpingjia+"' selected='selected'>"+shijiangpingjia+"</option>");
                    $("#xingshi").prepend("<option value='"+xingshi+"' selected='selected'>"+xingshi+"</option>");
                    $("#shijiangkecheng").prepend("<option value='"+shijiangkecheng+"' selected='selected'>"+shijiangkecheng+"</option>");



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
                    $("#search_id").val('');
                    $("#search_jiaoshixingming").val('');
                    $("#search_shijiangkecheng").val('');
                    loaddate();
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
                    // if ($('#jiaoshiid').val().trim()==='') {
                    //     bootbox.alert({
                    //         title: "来自火星的提示",
                    //         message: "教师编号为必选项，请填写",
                    //         closeButton:false
                    //     })
                    //     hasNullMes = true;
                    //     return
                    // }
                    // if ($('#zhuanjiaid').val().trim()==='') {
                    //     bootbox.alert({
                    //         title: "来自火星的提示",
                    //         message: "专家编号为必选项，请填写",
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

            /*-------------------------------------------------*/
            var arrCity=[];
            <#list zhuanjiaList as s>
            arrCity[${s.id}]=[{t:'${s.xingming}',id:${s.id}}];
            </#list>
            var arrid=[];
            <#list zhuanjiaList as s>
            arrid[${s.id}]=[{t:'${s.id}',id:${s.id}}];
            </#list>
            var arrJiaoshi=[];
            <#list jiaoshiList as s>
            arrJiaoshi[${s.id}]=[{t:'${s.xingming}',id:${s.id}}];
            </#list>
            var arrJiaoshixingming=[];
            <#list jiaoshiList as s>
            arrJiaoshixingming[${s.id}]=[{t:'${s.id}',id:${s.id}}];
            </#list>
            document.getElementById('zhuanjiaid').onchange = function () {
                addOptions(document.getElementById('zhuanjiaxingming'),  arrCity[this.value]);
            };
            document.getElementById('zhuanjiaxingming').onchange = function () {
                addOptions(document.getElementById('zhuanjiaid'),  arrid[this.value]);
            };
            document.getElementById('jiaoshixingming').onchange = function () {
                addOptions(document.getElementById('jiaoshiid'),  arrJiaoshixingming[this.value]);
            };
            document.getElementById('jiaoshiid').onchange = function () {
                addOptions(document.getElementById('jiaoshixingming'),  arrJiaoshi[this.value]);
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
        // <添加方法>
        function submitData() {
            var a=true;

            if ($('#shijiangneirong').val().trim()==='') {
                bootbox.alert({
                    title: "来自火星的提示",
                    message: "试讲内容为必选项，请填写",
                    closeButton:false
                })
                a = false;
            }
            if ($('#shijiangtigang').val().trim()==='') {
                bootbox.alert({
                    title: "来自火星的提示",
                    message: "试讲提纲为必选项，请填写",
                    closeButton:false
                })
                a = false;
            }
            if ($('#shijiangfenshu').val().trim()==='') {
                bootbox.alert({
                    title: "来自火星的提示",
                    message: "试讲分数为必选项，请填写",
                    closeButton:false
                })
                a = false;
            }
            if (a) {
                var data = {
                    id: $("#id").val(),
                    zhuanjiaid: $("#zhuanjiaid").val(),
                    zhuanjiaxingming: $("#zhuanjiaxingming").find("option:selected").text().trim(),
                    jiaoshiid: $("#jiaoshiid").val(),
                    jiaoshixingming: $("#jiaoshixingming").find("option:selected").text().trim(),
                    shijiangkecheng: $("#shijiangkecheng").find("option:selected").text().trim(),
                    shijiangneirong: $("#shijiangneirong").val(),
                    shijiangtigang: $("#shijiangtigang").val(),
                    shijiangpingjia: $("#shijiangpingjia").find("option:selected").text().trim(),
                    shijiangfenshu: $("#shijiangfenshu").val(),
                    xingshi: $("#xingshi").find("option:selected").text().trim(),
                    chuangjianshijiang: $("#chuangjianshijiang").val(),
                };
                var url = "shijiangxiugai";
                $.ajax({
                    url: url,
                    type: "post",
                    data: data,
                    dataType: "json",
                    cache: false,
                    success: function (map) {
                        bootbox.alert({
                            title: "来自火星的提示",
                            message: map.result,
                            closeButton: false
                        })
                        setTimeout(function () {
                            window.location.href = "../guanliyuan/shijiangguanli";
                        }, 3000);
                    },
                })
            }
        }
        //分页方法
        function loaddate(currentPage) {
                // alert("usertest loaddate"+currentPage)
                var data = {
                    currentPage : currentPage,
                    search_id : $("#search_id").val(),
                    search_jiaoshixingming : $("#search_jiaoshixingming").val(),
                    search_shijiangkecheng : $("#search_shijiangkecheng").val(),
                }
                var url = "shijiangfenye"
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
                                    '                <td> '+teacher.chuangjianshijiang+'</td>\n' +
                                    '                <td> '+teacher.zhuanjiaid+'</td>\n' +
                                    '                <td> '+teacher.zhuanjiaxingming+'</td>\n' +
                                    '                <td> '+teacher.jiaoshiid+'</td>\n' +
                                    '                <td> '+teacher.jiaoshixingming+'</td>\n' +
                                    '                <td> '+teacher.shijiangkecheng+'</td>\n' +
                                    '                <td> '+teacher.shijiangneirong+'</td>\n' +
                                    '                <td> '+teacher.shijiangtigang+'</td>\n' +
                                    '                <td> '+teacher.shijiangpingjia+'</td>\n' +
                                    '                <td><a href="${request.contextPath}/images/'+teacher.shijiangfenshuurl+'">'+teacher.shijiangfenshu+'</a></td>\n' +
                                    '                <td><a href="${request.contextPath}/images/'+teacher.shijiangfenshuurl+'" download>'+teacher.xingshi+'</a></td>\n' +

                                    '                <td>\n' +
                                    '                    <a  href="#" id="edit" class="edit" >编辑</a>\n' +
                                    '                    <a  href="shijiangdel?shijiangid='+teacher.id+'" id="del" class="del">删除</a>\n' +
                                    '                </td>\n' +
                                    '                <td>\n' +
                                    '                    <a  href="shijiangUpload?shijiangid='+teacher.id+'" id="more" class="">上传试讲听课记录</a>\n' +
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
                            <li><a class="active" href="shijiangguanli"><span>试讲档案管理</span></a></li>
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
                                <span>教师姓名：</span>
                                <input id="search_jiaoshixingming" type="text" class="form-control" placeholder="请输入教师姓名">
                            </div>
                        </div>
                        <div class="search_input">
                            <div class="input-group mb-3" >
                                <span>试讲课程：</span>
                                <input id="search_shijiangkecheng" type="text" class="form-control" placeholder="请输入试讲课程">
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
                                            <td class="tb_bg"><label for="">创建时间</label></td>
                                            <td><input id="chuangjianshijiang" name="chuangjianshijiang" type="date" /></td>
                                        </tr>
                                        <tr>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>专家编号</label></td>
                                            <td>
                                                <select id="zhuanjiaid" name="zhuanjiaid" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                    <option>---请选择---</option>
                                                    <#list zhuanjiaList as s>
                                                    <option value="${s.id}">${s.id}</option>
                                                    </#list>
                                                </select>
                                            </td>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>专家姓名</label>
                                            </td>
                                            <td>
                                                <select id="zhuanjiaxingming" name="zhuanjiaxingming"  class="form-control select_down" style="font-size: 13px; color: #666;">
                                                    <option>---请选择---</option>
                                                    <#list zhuanjiaList as x>
                                                    <option value="${x.id}">${x.xingming}</option>
                                                    </#list>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>教师编号</label>
                                            </td>
                                            <td>
                                                <select id="jiaoshiid" name="jiaoshiid" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                    <option>---请选择---</option>
                                                    <#list jiaoshiList as s>
                                                    <option value="${s.id}">${s.id}</option>
                                                    </#list>
                                                </select>
                                            </td>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>教师姓名</label>
                                            </td>
                                            <td>
                                                <select id="jiaoshixingming" name="jiaoshixingming" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                    <option>---请选择---</option>
                                                    <#list jiaoshiList as s>
                                                    <option value="${s.id}">${s.xingming}</option>
                                                    </#list>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>试讲课程</label></td>
                                            <td><select id="shijiangkecheng" name="shijiangkecheng" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                <option>---请选择---</option>
                                                    <#list kechengList as s>
                                                    <option value="${s.id}">${s.kechengmingcheng}</option>
                                                    </#list>
                                            </select></td>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>试讲内容</label></td>
                                            <td><textarea id="shijiangneirong" name="shijiangneirong" cols="10" placeholder="请输入试讲内容,200字以内"></textarea></td>
                                        </tr>
                                        <tr>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>试讲提纲</label></td>
                                            <td><input id="shijiangtigang" name="shijiangtigang" type="text" placeholder="请输入"/></td>
                                            <td class="tb_bg"><label for="">试讲评价</label></td>
                                            <td><select id="shijiangpingjia" name="shijiangpingjia" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                <option >请选择</option>
                                                <option >优秀</option>
                                                <option >良好</option>
                                                <option >一般</option>
                                            </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>试讲分数</label></td>
                                            <td><input id="shijiangfenshu" name="shijiangfenshu" type="text" placeholder="请输入试讲分数"/></td>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>形式</label></td>
                                            <td><select id="xingshi" name="xingshi" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                <option >请选择</option>
                                                <option >电子版</option>
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
                        <th>创建时间</th>
                        <th>专家编号</th>
                        <th>专家姓名</th>
                        <th>教师编号</th>
                        <th>教师姓名</th>
                        <th>试讲课程</th>
                        <th>试讲内容</th>
                        <th>试讲提纲</th>
                        <th>试讲评价</th>
                        <th>试讲分数</th>
                        <th>形式</th>
                        <th>操作</th>
                        <th>更多操作</th>
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


