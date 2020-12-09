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
            $('#add_btn2').click(function () {
                methods.addHandle2()
            })
            $('#new_add').click(function () {
                methods.new()
                $("#h4").html("添加");
                $("#id").attr("readonly","readonly");

            })
            $('#new_add2').click(function () {
                methods.new2()
                $("#h5").html("添加");
                $("#id").attr("readonly","readonly");
            })
            $('#show_tbody').on('click','.edit', function () {
                trIndex = $('.edit', '#show_tbody').index($(this));
                $("#h4").html("编辑");
                $("#id").attr("readonly","readonly");
                addEnter = false;
                $(this).parents('tr').addClass('has_case');
                methods.editHandle(trIndex);
            });
            $('#search_btn').click(function () {
            });
            $('#back_btn').click(function () {
                methods.resectList();
            });
            $('.del').click(function () {
                $(this).parents('tr').remove();
            });
            $('#renyuan').on('hide.bs.modal',function() {
                addEnter = true;
                $('#show_tbody tr').removeClass('has_case');
                $('#xztb input').val('');
                $('#xztb select').find('option:first').prop('selected', true)
            });
            $('#renyuan2').on('hide.bs.modal',function() {
                addEnter = true;
                $('#show_tbody tr').removeClass('has_case');
                $('#xztb2 input').val('');
                $('#xztb2 select').find('option:first').prop('selected', true)
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
                        tarInp.eq(j).val("");
                    }
                    $("#kechengid").get(0).remove(0);
                    $("#kechengmingcheng").get(0).remove(0);
                    $("#banjiid").get(0).remove(0);
                    $("#banjimingcheng").get(0).remove(0);
                    $("#jiaoshiid").get(0).remove(0);
                    $("#jiaoshixingming").get(0).remove(0);
                    $("#xueqi").get(0).remove(0);
                    $("#shangkexingshi").get(0).remove(0);
                    var text="请选择";
                    $("#kechengid").prepend("<option value='"+text+"' selected='selected'>"+text+"</option>");
                    $("#kechengmingcheng").prepend("<option value='"+text+"' selected='selected'>"+text+"</option>");
                    $("#banjiid").prepend("<option value='"+text+"' selected='selected'>"+text+"</option>");
                    $("#banjimingcheng").prepend("<option value='"+text+"' selected='selected'>"+text+"</option>");
                    $("#jiaoshiid").prepend("<option value='"+text+"' selected='selected'>"+text+"</option>");
                    $("#jiaoshixingming").prepend("<option value='"+text+"' selected='selected'>"+text+"</option>");
                    $("#xueqi").prepend("<option value='"+text+"' selected='selected'>"+text+"</option>");
                    $("#shangkexingshi").prepend("<option value='"+text+"' selected='selected'>"+text+"</option>");
                },
                new2:function(){
                    $('#renyuan2').modal('show');
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
                addHandle2: function (the_index) {
                    hasNullMes = false;
                        methods.checkRepeat();
                        if (noRepeat) {
                            $('#renyuan2').modal('hide');
                            methods.resectList();
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
                        tarSel.eq(p).get(0).remove(0);
                        tarSel.eq(p).prepend("<option value='"+nowConArr[the_p]+"' selected='selected'>"+nowConArr[the_p]+"</option>");
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
                    $('#search_jiaoshixingming').val('');
                    $('#search_kechengmingcheng').val('');
                    $('#search_xueqi').val('');
                    loaddate();
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
                    // if ($('#kechengid').val().trim()==='') {
                    //     bootbox.alert({
                    //         title: "来自火星的提示",
                    //         message: "课程编号为必选项，请填写",
                    //         closeButton:false
                    //     })
                    //     hasNullMes = true;
                    //     return
                    // }
                    // if ($('#kechengmingcheng').val().trim()==='') {
                    //     bootbox.alert({
                    //         title: "来自火星的提示",
                    //         message: "课程名称为必选项，请填写",
                    //         closeButton:false
                    //     })
                    //     hasNullMes = true;
                    //     return
                    // }
                    // if ($('#banjiid').val().trim()==='') {
                    //     bootbox.alert({
                    //         title: "来自火星的提示",
                    //         message: "班级编号为必选项，请填写",
                    //         closeButton:false
                    //     })
                    //     hasNullMes = true;
                    //     return
                    // }
                    // if ($('#banjimingcheng').val().trim()==='') {
                    //     bootbox.alert({
                    //         title: "来自火星的提示",
                    //         message: "班级名称为必选项，请填写",
                    //         closeButton:false
                    //     })
                    //     hasNullMes = true;
                    //     return
                    // }
                    // if ($('#jiaoshiid').val().trim()==='') {
                    //     bootbox.alert({
                    //         title: "来自火星的提示",
                    //         message: "任课教师编号为必选项，请填写",
                    //         closeButton:false
                    //     })
                    //     hasNullMes = true;
                    //     return
                    // }
                    // if ($('#jiaoshixingming').val().trim()==='') {
                    //     bootbox.alert({
                    //         title: "来自火星的提示",
                    //         message: "任课教师姓名为必选项，请填写",
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
            /*************************下拉框联动**********************************/
            var arrbanjimingcheng=[];
            <#list banjiList as s>
            arrbanjimingcheng[${s.id}]=[{t:'${s.banjimingcheng}',id:${s.id}}];
            </#list>
            var arrbanjiid=[];
            <#list banjiList as s>
            arrbanjiid[${s.id}]=[{t:'${s.id}',id:${s.id}}];
            </#list>
            var arrkechengmingcheng=[];
            <#list kechengList as s>
            arrkechengmingcheng[${s.id}]=[{t:'${s.kechengmingcheng}',id:${s.id}}];
            </#list>
            var arrkechengid=[];
            <#list kechengList as s>
            arrkechengid[${s.id}]=[{t:'${s.id}',id:${s.id}}];
            </#list>
            var arrjiaoshixingming=[];
            <#list jiaoshiList as s>
            arrjiaoshixingming[${s.id}]=[{t:'${s.xingming}',id:${s.id}}];
            </#list>
            var arrjiaoshiid=[];
            <#list jiaoshiList as s>
            arrjiaoshiid[${s.id}]=[{t:'${s.id}',id:${s.id}}];
            </#list>
            document.getElementById('banjiid').onchange = function () {
                addOptions(document.getElementById('banjimingcheng'),  arrbanjimingcheng[this.value]);
            };
            document.getElementById('banjiid1').onchange = function () {
                addOptions(document.getElementById('banjimingcheng1'),  arrbanjimingcheng[this.value]);
            };
            document.getElementById('banjiid2').onchange = function () {
                addOptions(document.getElementById('banjimingcheng2'),  arrbanjimingcheng[this.value]);
            };
            document.getElementById('banjiid3').onchange = function () {
                addOptions(document.getElementById('banjimingcheng3'),  arrbanjimingcheng[this.value]);
            };
            document.getElementById('kechengid').onchange = function () {
                addOptions(document.getElementById('kechengmingcheng'),  arrkechengmingcheng[this.value]);
            };
            document.getElementById('jiaoshiid').onchange = function () {
                addOptions(document.getElementById('jiaoshixingming'),  arrjiaoshixingming[this.value]);
            };


             document.getElementById('banjimingcheng').onchange = function () {
                 addOptions(document.getElementById('banjiid'),  arrbanjiid[this.value]);
             };
            document.getElementById('banjimingcheng1').onchange = function () {
                addOptions(document.getElementById('banjiid1'),  arrbanjiid[this.value]);
            };
            document.getElementById('banjimingcheng2').onchange = function () {
                addOptions(document.getElementById('banjiid2'),  arrbanjiid[this.value]);
            };
            document.getElementById('banjimingcheng3').onchange = function () {
                addOptions(document.getElementById('banjiid3'),  arrbanjiid[this.value]);
            };
            document.getElementById('kechengmingcheng').onchange = function () {
                addOptions(document.getElementById('kechengid'),  arrkechengid[this.value]);
            };
            document.getElementById('jiaoshixingming').onchange = function () {
                addOptions(document.getElementById('jiaoshiid'),  arrjiaoshiid[this.value]);
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
        // <修改方法>
        function submitData() {

            var data = {
                id : $("#id").val(),
                kechengid: $("#kechengid").val().trim(),
                kechengmingcheng: $("#kechengmingcheng").find("option:selected").text().trim(),
                banjiid: $("#banjiid").val().trim(),
                banjimingcheng: $("#banjimingcheng").find("option:selected").text().trim(),
                jiaoshiid: $("#jiaoshiid").val().trim(),
                jiaoshixingming: $("#jiaoshixingming").find("option:selected").text().trim(),
                xueqi : $("#xueqi").find("option:selected").text().trim(),
                shangkexingshi:$("#shangkexingshi").find("option:selected").text().trim(),
            };
            var url = "shangkebanjixiugai";
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
                    setTimeout(function(){
                        window.location.href="../guanliyuan/shangkeguanli";
                    }, 3000);
                },
            });

        }
        //分页方法
        function loaddate(currentPage) {
                // alert("usertest loaddate"+currentPage)
                var data = {
                    currentPage : currentPage,
                    search_jiaoshixingming: $("#search_jiaoshixingming").val().trim(),
                    search_kechengmingcheng: $("#search_kechengmingcheng").val().trim(),
                    search_xueqi: $("#search_xueqi").val(),
                }
                var url = "shangkebanjifenye"
                $.ajax({
                    url : url,
                    type : "get",
                    data : data,
                    dataType : "json",
                    cache : false,
                    success : function (pb) {
                        $("#show_tbody").empty();
                        $("#fenye").empty();
                        $("#xingmingspan").html(pb.denglurenxingming);
                        if (pb.list!=null){
                        <#--//分页工具条-->
                        $("#totalPage").html(pb.totalPage);//
                        $("#totalCount").html(pb.totalCount);
                        var fenyelis=
                                '            <a class="zxfPagenum" href="javascript:loaddate('+1+')" aria-label="Next">\n' +
                                '                <span aria-hidden="true">首页</span>\n' +
                                '            </a>';

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
                            var shangkebanji=pb.list[i];

                            var li='<tr>\n' +
                                    '                <td> '+shangkebanji.id+'</td>\n' +
                                    '                <td> '+shangkebanji.xueqi+'</td>\n' +
                                    '                <td> '+shangkebanji.kechengid+'</td>\n' +
                                    '                <td> '+shangkebanji.kechengmingcheng+'</td>\n' +
                                    '                <td> '+shangkebanji.banjiid+'</td>\n' +
                                    '                <td> '+shangkebanji.banjimingcheng+'</td>\n' +
                                    '                <td> '+shangkebanji.jiaoshiid+'</td>\n' +
                                    '                <td> '+shangkebanji.jiaoshixingming+'</td>\n' +
                                    '                <td> '+shangkebanji.shangkexingshi+'</td>\n' +
                                    '                <td>\n' +
                                    '                    <a  href="#" id="edit" class="edit" >编辑</a>\n' +
                                    '                    <a  href="shangkebanjidel?shangkebanjiid='+shangkebanji.id+'" id="del" class="del">删除</a>\n' +
                                    '                </td>\n' +
                                    '            </tr>';
                            lis+=li;
                        }
                        $("#show_tbody").html(lis);
                    }}
                })
        }
        function submitData2() {
            var data = {
                banjiid1: $("#banjiid1").val().trim(),
                banjimingcheng1: $("#banjimingcheng1").find("option:selected").text().trim(),
                banjiid2: $("#banjiid2").val().trim(),
                banjimingcheng2: $("#banjimingcheng2").find("option:selected").text().trim(),
                banjiid3: $("#banjiid3").val(),
                banjimingcheng3: $("#banjimingcheng3").find("option:selected").text().trim(),
            };
            var url = "shangkebanjitianjia";
            $.ajax({
                url : url,
                type : "get",
                data : data,
                dataType : "json",
                cache : false,
                success : function () {

                },
            })
            window.location.href="../guanliyuan/shangkeguanli";
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
                            <li><a  href="xueshengguanli"><span>学生管理</span></a></li>
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
                            <li><a class="active" href="peixunziliaoguanli"><span>岗前培训资料管理</span></a></li>
                        </ul>
                    </li>
                    <li class="lsm-sidebar-item">
                        <a href="javascript:;"><i class="my-icon lsm-sidebar-icon icon-chongzhi-copy"></i><span>教学督导</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a  href="dudaojilu"><span>督导记录</span></a></li>
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
                            <li><a  class="active" href="shangkeguanli"><span>上课班级管理</span></a></li>
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
                                <span>教师姓名：</span>
                                <input id="search_jiaoshixingming" name="search_jiaoshixingming" class="form-control select_down" style="font-size: 13px; color: #666;" placeholder="请输入教师姓名" />
                            </div>
                        </div>
                        <div class="search_input">
                            <div class="input-group mb-3" >
                                <span>课程名称：</span>
                                <input id="search_kechengmingcheng" name="search_kechengmingcheng" class="form-control select_down" style="font-size: 13px; color: #666;" placeholder="请输入课程名称" />
                            </div>
                        </div>
                        <div class="search_input">
                            <div class="input-group mb-3" >
                                <span>学期选择：</span>
                                <input id="search_xueqi" name="search_xueqi" class="form-control select_down" style="font-size: 13px; color: #666;"placeholder="格式如：2019-2020-2" />
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
                        <span>添加课程安排</span>
                    </button>
                    <button id="new_add2" type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#renyuan2">
                        <img src="${request.contextPath}/img/add_two.png"/>
                        <span>添加上课班级</span>
                    </button>
                    <div class="modal fade" id="renyuan">
                        <div class="modal-dialog modal-lg modal_position">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 id="h4" class="modal-title">添加课程安排</h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>
                                <div class="modal-body">
                                    <table id="xztb" class="table">
                                        <!--新修改弹窗的样式-->
                                        <tbody>
                                        <tr>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>ID</label></td>
                                            <td><input  id="id" name="id" class="id" type="text" placeholder="自动生成"/></td>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>学期</label></td>
                                            <td>
                                                <select id="xueqi" name="xueqi" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                <option>请选择</option>
                                                    <#list xueqiList as s>
                                                    <option value="${s.text}">${s.text}</option>
                                                    </#list>
                                            </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>课程编号</label></td>
                                            <td>
                                                <select id="kechengid" name="kechengid" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                    <option>请选择</option>
                                                    <#list kechengList as s>
                                                    <option value="${s.id}">${s.id}</option>
                                                    </#list>
                                                </select>
                                            </td>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>课程名称</label></td>
                                            <td>
                                                <select id="kechengmingcheng" name="kechengmingcheng" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                <option>请选择</option>
                                                    <#list kechengList as s>
                                                    <option value="${s.id}">${s.kechengmingcheng}</option>
                                                    </#list>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>班级编号</label></td>
                                            <td>
                                                <select id="banjiid" name="banjiid" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                    <option>请选择</option>
                                                    <#list banjiList as s>
                                                    <option value="${s.id}">${s.id}</option>
                                                    </#list>
                                                </select>
                                            </td>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>班级名称</label></td>
                                            <td>
                                                <select id="banjimingcheng" name="banjimingcheng" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                    <option>请选择</option>
                                                    <#list banjiList as s>
                                                    <option value="${s.id}">${s.banjimingcheng}</option>
                                                    </#list>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>教师编号</label></td>
                                            <td>
                                                <select id="jiaoshiid" name="jiaoshiid" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                    <option>请选择</option>
                                                    <#list jiaoshiList as s>
                                                    <option value="${s.id}">${s.id}</option>
                                                    </#list>
                                                </select>
                                            </td>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>教师姓名</label></td>
                                            <td>
                                                <select id="jiaoshixingming" name="jiaoshixingming" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                    <option>请选择</option>
                                                    <#list jiaoshiList as s>
                                                    <option value="${s.id}">${s.xingming}</option>
                                                    </#list>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>上课形式</label></td>
                                            <td>
                                            <select id="shangkexingshi" name="shangkexingshi" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                <option>请选择</option>
                                                <option>外聘制</option>
                                                <option>派遣制</option>
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

                    <div class="modal fade" id="renyuan2">
                        <div class="modal-dialog modal-lg modal_position">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 id="h5" class="modal-title">添加上课班级</h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>
                                <div class="modal-body">
                                    <table id="xztb2" class="table">
                                        <!--新修改弹窗的样式-->
                                        <tbody>
                                        <tr>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>班级编号</label></td>
                                            <td>
                                                <select id="banjiid1" name="banjiid1" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                    <option>请选择</option>
                                                    <#list banjiList as s>
                                                    <option value="${s.id}">${s.id}</option>
                                                    </#list>
                                                </select>
                                            </td>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>班级名称</label></td>
                                            <td>
                                                <select id="banjimingcheng1" name="banjimingcheng1" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                    <option>请选择</option>
                                                    <#list banjiList as s>
                                                    <option value="${s.id}">${s.banjimingcheng}</option>
                                                    </#list>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>班级编号</label></td>
                                            <td>
                                                <select id="banjiid2" name="banjiid2" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                    <option>请选择</option>
                                                    <#list banjiList as s>
                                                    <option value="${s.id}">${s.id}</option>
                                                    </#list>
                                                </select>
                                            </td>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>班级名称</label></td>
                                            <td>
                                                <select id="banjimingcheng2" name="banjimingcheng2" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                    <option>请选择</option>
                                                    <#list banjiList as s>
                                                    <option value="${s.id}">${s.banjimingcheng}</option>
                                                    </#list>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>班级编号</label></td>
                                            <td>
                                                <select id="banjiid3" name="banjiid3" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                    <option>请选择</option>
                                                    <#list banjiList as s>
                                                    <option value="${s.id}">${s.id}</option>
                                                    </#list>
                                                </select>
                                            </td>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>班级名称</label></td>
                                            <td>
                                                <select id="banjimingcheng3" name="banjimingcheng3" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                    <option>请选择</option>
                                                    <#list banjiList as s>
                                                    <option value="${s.id}">${s.banjimingcheng}</option>
                                                    </#list>
                                                </select>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                                    <button id="add_btn2" onclick="submitData2()" class="btn btn-secondary">创建</button>
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
                        <th>学期</th>
                        <th>课程编号</th>
                        <th>课程名称</th>
                        <th>班级编号</th>
                        <th>班级名称</th>
                        <th>教师编号</th>
                        <th>教师姓名</th>
                        <th>上课形式</th>
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


