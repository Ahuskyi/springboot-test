<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
    <title>教学督导</title>
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
                // $("#idlabel").hide();
                // $("#id").hide();
                $("#id").attr("readonly","readonly");
                // $("#id").removeAttr("readonly");
            })
            $('#show_tbody').on('click','.edit', function () {
                trIndex = $('.edit', '#show_tbody').index($(this));
                $("#h4").html("编辑");
                $("#idlabel").show();
                $("#id").show();
                $("#id").attr("readonly","readonly");
                addEnter = false;
                $(this).parents('tr').addClass('has_case');
                methods.editHandle(trIndex);
            })
            $('#search_btn').click(function () {
                //methods.seachName();
            })
            $('#back_btn').click(function () {
                $('#search_xingming').val('');
                $('#search_fashengshijian').val('');
                loaddate();
                // methods.resectList();
            })

            $('#renyuan').on('hide.bs.modal',function() {
                addEnter = true;
                $('#show_tbody tr').removeClass('has_case');
                // $('#xztb input').val(' ');
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
                    for (var j=0;j<3;j++) {
                        var placeholder = tarInp.eq(j).placeholder;
                        tarInp.eq(j).val(placeholder)
                    }
                    var now = new Date();
                    //格式化日，如果小于9，前面补0
                    var day = ("0" + now.getDate()).slice(-2);
                    //格式化月，如果小于9，前面补0
                    var month = ("0" + (now.getMonth() + 1)).slice(-2);
                    //拼装完整日期格式
                    var today = now.getFullYear()+"-"+(month)+"-"+(day) ;
                   $("#chuangjianshijian").val(today.toString().trim());
                },
                addHandle: function (the_index) {
                    hasNullMes = false;
                    methods.checkMustMes();
                    if (hasNullMes) {
                        return;
                    }else {
                        methods.checkRepeat();
                        if (noRepeat) {
                            $('#renyuan').modal('hide');
                            methods.resectList();
                            loaddate();
                        }
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
                    // if ($('.jiaoshibianhao').val().trim()==='') {
                    //     bootbox.alert({
                    //         title: "来自火星的提示",
                    //         message: "教师编号为必选项，请填写",
                    //         closeButton:false
                    //     })
                    //     hasNullMes = true;
                    //     return
                    // }
                    // if ($('.xingming').val().trim()==='') {
                    //     bootbox.alert({
                    //         title: "来自火星的提示",
                    //         message: "姓名为必选项，请填写",
                    //         closeButton:false
                    //     })
                    //     hasNullMes = true;
                    //     return
                    // }
                    hasNullMes = true;
                },
                checkRepeat: function () {
                    noRepeat = true;
                }
            }


            var arrJiaoshi=[];
            <#list jiaoshiList as s>
            arrJiaoshi[${s.id}]=[{t:'${s.xingming}',id:${s.id}}];
            </#list>
            var arrJiaoshiid=[];
            <#list jiaoshiList as s>
            arrJiaoshiid[${s.id}]=[{t:'${s.id}',id:${s.id}}];
            </#list>
            document.getElementById('jiaoshibianhao').onchange = function () {
                addOptions(document.getElementById('xingming'), arrJiaoshi[this.value]);
            };
            document.getElementById('xingming').onchange = function () {
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
        // <修改方法>
        function submitData() {
            var data = {
                id : $("#id").val(),
                jiaoshibianhao : $("#jiaoshibianhao").val(),
                xingming : $("#xingming").find("option:selected").text().trim(),
                chufadengji : $("#chufadengji").val(),
                fashengshjian: $("#fashengshjian").val(),
                chufayuanyin: $("#chufayuanyin").val(),
                chuangjianshijian: $("#chuangjianshijian").val(),
                shenhezhuangtai: "未审核",
            };
            var url = "dudaojiluxiugai";
            $.ajax({
                url : url,
                type : "post",
                data : data,
                dataType : "json",
                cache : false,
                success : function (result) {
                    bootbox.alert({
                        title: "来自火星的提示",
                        message:result,
                        closeButton:false
                    })
                    setTimeout(function(){
                        window.location.href="../zhuanjia/jiaoxuedudao";
                    }, 3000);
                },
            })

        }
        //分页方法
        function loaddate(currentPage) {
            // alert("usertest loaddate"+currentPage)
            var data = {
                currentPage : currentPage,
                xingming : $("#search_xingming").val(),
                //shenhezhuangtai : $("#search_shenhezhuangtai").val(),
                fashengshijian : $("#search_fashengshijian").val(),
            }
            var url = "dudaojilufenye"
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
                                '                <td> '+teacher.jiaoshibianhao+'</td>\n' +
                                '                <td> '+teacher.xingming+'</td>\n' +
                                '                <td> '+teacher.chufadengji+'</td>\n' +
                                '                <td> '+teacher.fashengshjian+'</td>\n' +
                                '                <td> '+teacher.chufayuanyin+'</td>\n' +
                                '                <td> '+teacher.chuangjianshijian+'</td>\n' +
                                '            </tr>';
                        lis+=li;
                    }
                    $("#show_tbody").html(lis);
                    $("#xingmingspan").html(pb.denglurenxingming);
                    // $("#id").placeholder=pb.nextid;
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
                        <a href="javascript:;"><i class="my-icon lsm-sidebar-icon icon_2"></i><span>教学督导</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a href="jiaoxuedudao"><span>教学督导</span></a></li>
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
                                <input id="search_xingming" type="text" class="form-control" placeholder="请输入教师姓名">
                            </div>
                        </div>
                        <div class="search_input">
                            <div class="input-group mb-3" >
                                <span>发生时间：</span>
                                <input id="search_fashengshijian" type="text" class="form-control" placeholder="格式如：2019-03">
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
                                                <td class="tb_bg"><label id="idlabel" for=""><font style="font-size: 14px; color: red;">*</font>id</label></td>
                                                <td><input  id="id" name="id" class="id" type="text" placeholder="自动生成"/></td>
                                                <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>教师编号</label>
                                                </td>
                                                <td>
                                                    <select id="jiaoshibianhao" name="jiaoshibianhao" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                        <option>---请选择---</option>
                                                    <#list jiaoshiList as s>
                                                    <option value="${s.id}">${s.id}</option>
                                                    </#list>
                                                    </select>
                                                </td> </tr>
                                            <tr>
                                                <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>教师姓名</label>
                                                </td>
                                                <td>
                                                    <select id="xingming" name="xingming" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                        <option>---请选择---</option>
                                                    <#list jiaoshiList as s>
                                                    <option value="${s.id}">${s.xingming}</option>
                                                    </#list>
                                                    </select>
                                                </td>
                                                <td class="tb_bg"><label for="">处罚等级</label></td>
                                                <td>
                                                    <select id="chufadengji" name="chufadengji" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                        <option>---请选择---</option>
                                                    <#list chufadengjiList as s>
                                                    <option value="${s.chufadengji}">${s.chufadengji}</option>
                                                    </#list>
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="tb_bg"><label for="">发生时间</label></td>
                                                <td><input id="fashengshjian" name="fashengshjian" type="date" /></td>

                                                <td class="tb_bg"><label for="">处罚原因</label></td>
                                                <td><input id="chufayuanyin" name="chufayuanyin" type="text" placeholder="请输入处罚原因"/></td>
                                            </tr>
                                            <tr>
                                                <td class="tb_bg"><label for="">创建时间</label></td>
                                                <td><input id="chuangjianshijian" name="chuangjianshijian" type="date" min="2020-05-1" max="2020-05-30"/></td>
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
                        <th>姓名</th>
                        <th>处罚等级</th>
                        <th>发生时间</th>
                        <th>处罚原因</th>
                        <th>创建时间</th>
                    </tr>
                    </thead>
                    <tbody id="show_tbody">
                    </tbody>
                </table>

            </div>
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


