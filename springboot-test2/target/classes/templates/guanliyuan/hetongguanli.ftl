<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
    <title>本学期合同管理</title>
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
<style>
    .table td, .table th {
        padding: .30rem;
        vertical-align: top;
        border-top: 1px solid #dee2e6;

    }
</style>
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
                $("#id").prop("readonly",true)
                // $("#jiaoshibianhao")
                // $("#jiaoshixingming")
                // $("#shenfenzheng")
                // $("#hetongleixing")
                // $("#pinyongxingshi")
                // $("#xueqi")
            });
            $('#show_tbody').on('click','.edit', function () {
                trIndex = $('.edit', '#show_tbody').index($(this));
                $("#h4").html("编辑");
                addEnter = false;
                $(this).parents('tr').addClass('has_case');
                methods.editHandle(trIndex);
            });
            $('#search_btn').click(function () {
                //methods.seachName();
            });
            $('#back_btn').click(function () {
                methods.resectList();
                loaddate();
            });
            $('#renyuan').on('hide.bs.modal',function() {
                addEnter = true;
                $('#show_tbody tr').removeClass('has_case');
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
                    for (var j=0;j<5;j++) {
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
                    $('#chuangjianshijian').removeAttr("readonly");
                    $('#chuangjianshijian').attr("value",today.toString().trim());
                    $("#jiaoshibianhao").get(0).remove(0);
                    $("#jiaoshixingming").get(0).remove(0);
                    $("#shenfenzheng").get(0).remove(0);
                    $("#hetongleixing").get(0).remove(0);
                    $("#pinyongxingshi").get(0).remove(0);
                    $("#xueqi").get(0).remove(0);
                    var text="请选择";
                    $("#jiaoshibianhao").prepend("<option value='"+text+"' selected='selected'>"+text+"</option>");
                    $("#jiaoshixingming").prepend("<option value='"+text+"' selected='selected'>"+text+"</option>");
                    $("#shenfenzheng").prepend("<option value='"+text+"' selected='selected'>"+text+"</option>");
                    $("#hetongleixing").prepend("<option value='"+text+"' selected='selected'>"+text+"</option>");
                    $("#pinyongxingshi").prepend("<option value='"+text+"' selected='selected'>"+text+"</option>");
                    $("#xueqi").prepend("<option value='"+text+"' selected='selected'>"+text+"</option>");
                    $("#jiaoshibianhao").prop("disabled",false);
                    $("#jiaoshixingming").prop("disabled",false);
                    $("#shenfenzheng").prop("disabled",false);
                    $("#hetongleixing").prop("disabled",false);
                    $("#pinyongxingshi").prop("disabled",false);
                    $("#xueqi").prop("disabled",false);
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
                    $('#renyuan').modal('show');
                    tarInp.eq(0).val(nowConArr[0]);
                    tarInp.eq(1).val(nowConArr[3]);
                    tarInp.eq(2).val(nowConArr[5]);
                    tarInp.eq(3).val(nowConArr[6]);
                    tarInp.eq(4).val(nowConArr[7]);
                    tarInp.eq(0).attr("readonly","readonly");

                    var jiaoshibianhao=nowConArr[1].toString();
                    var jiaoshixingming=nowConArr[2].toString();
                    var shenfenzheng=nowConArr[4].toString();
                    var hetongleixing=nowConArr[8].toString();
                    var pinyongxingshi=nowConArr[9].toString();
                    var xueqi=nowConArr[17].toString();
                    var chuangjianshijian=nowConArr[18].toString().trim();
                    $("#chuangjianshijian").attr("value",chuangjianshijian);
                    $("#chuangjianshijian").attr("readonly","readonly");
                    $("#jiaoshibianhao").get(0).remove(0);
                    $("#jiaoshixingming").get(0).remove(0);
                    $("#shenfenzheng").get(0).remove(0);
                    $("#hetongleixing").get(0).remove(0);
                    $("#pinyongxingshi").get(0).remove(0);
                    $("#xueqi").get(0).remove(0);
                    $("#jiaoshibianhao").prepend("<option value='"+jiaoshibianhao+"' selected='selected'>"+jiaoshibianhao+"</option>");
                    $("#jiaoshixingming").prepend("<option value='"+jiaoshixingming+"' selected='selected'>"+jiaoshixingming+"</option>");
                    $("#shenfenzheng").prepend("<option value='"+shenfenzheng+"' selected='selected'>"+shenfenzheng+"</option>");
                    $("#hetongleixing").prepend("<option value='"+hetongleixing+"' selected='selected'>"+hetongleixing+"</option>");
                    $("#pinyongxingshi").prepend("<option value='"+pinyongxingshi+"' selected='selected'>"+pinyongxingshi+"</option>");
                    $("#xueqi").prepend("<option value='"+xueqi+"' selected='selected'>"+xueqi+"</option>");
                    $("#jiaoshibianhao").prop("disabled",true);
                    $("#jiaoshixingming").prop("disabled",true);
                    $("#shenfenzheng").prop("disabled",true);
                    $("#hetongleixing").prop("disabled",true);
                    $("#pinyongxingshi").prop("disabled",true);
                    $("#xueqi").prop("disabled",true);
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
                    $('#search_jiaoshixingming').val('');
                    $('#search_jiaoshibianhao').val('');
                    $('#time').val('');
                },
                checkMustMes: function () {
                    hasNullMes = true;
                },
                checkRepeat: function () {
                    noRepeat = true;
                }
            }
                /******************************************************/
            var arrJiaoshi=[];//下拉框赋值
            //for(int i=0;i<jiaoshiList.size();i++){s=jiaoshiList.get(i) s.id>>jiaoshiList.get(i).getid()}
            <#list jiaoshiList as s>
            arrJiaoshi[${s.id}]=[{t:'${s.xingming}',id:${s.id}}];
            </#list>
            //<option value= >text </option>
            var arrJiaoshiid=[];
            <#list jiaoshiList as s>
            arrJiaoshiid[${s.id}]=[{t:'${s.id}',id:${s.id}}];
            </#list>
            var  arrJiaoshishenfenzheng=[]
            <#list jiaoshiList as s>
            arrJiaoshishenfenzheng[${s.id}]=[{t:'${s.shenfenzheng}',id:${s.id}}];
            </#list>
            var arrxingshi=[];
            arrxingshi[1]=[{t:'派遣制',id:1}];
            arrxingshi[2]=[{t:'外聘制',id:2}];
            var arrleixing=[];
            arrleixing[1]=[{t:'派遣制合同',id:1}];
            arrleixing[2]=[{t:'外聘制合同',id:1}];
            //联动
            document.getElementById('jiaoshibianhao').onchange = function () {
                addOptions(document.getElementById('jiaoshixingming'),  arrJiaoshi[this.value]);
                addOptions(document.getElementById('shenfenzheng'),  arrJiaoshishenfenzheng[this.value]);
            };
            document.getElementById('jiaoshixingming').onchange = function () {
                addOptions(document.getElementById('jiaoshibianhao'),  arrJiaoshiid[this.value]);
                addOptions(document.getElementById('shenfenzheng'),  arrJiaoshishenfenzheng[this.value]);
            };
            document.getElementById('hetongleixing').onchange = function () {
                addOptions(document.getElementById('pinyongxingshi'),  arrxingshi[this.value]);
            };
            document.getElementById('pinyongxingshi').onchange = function () {
                addOptions(document.getElementById('hetongleixing'),  arrleixing[this.value]);
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

            if ($('#zhuanzhanghuming').val().trim()==='') {
                bootbox.alert({
                    title: "来自火星的提示",
                    message: "转账户名为必选项，请填写",
                    closeButton:false
                })
                a = false;
            }
            if ($('#yinhangkahao').val().trim()==='') {
                bootbox.alert({
                    title: "来自火星的提示",
                    message: "银行卡号为必选项，请填写",
                    closeButton:false
                })
                a = false;
            }if ($('#yinhang').val().trim()==='') {
                bootbox.alert({
                    title: "来自火星的提示",
                    message: "所属银行为必选项，请填写",
                    closeButton:false
                })
                a = false;
            }
            if ($('#kaihuhanghuhao').val().trim()==='') {
                bootbox.alert({
                    title: "来自火星的提示",
                    message: "开户行户号为必选项，请填写",
                    closeButton:false
                })
                a = false;
            }
            if (a) {
                $("#jiaoshibianhao").prop("disabled", false);
                $("#zhuanzhanghuming").prop("disabled", false);
                $("#pinyongxingshi").prop("disabled", false);
                $("#hetongleixing").prop("disabled", false);
                var dt1 = $("#chuangjianshijian").val();
                var odate = new Date(dt1);
                //alert(odate);
                if ($("#jiaoshibianhao").val() === null) {
                    $("#jiaoshibianhao").val(0);
                }
                var data = {
                    id: $("#id").val(),
                    jiaoshibianhao: $("#jiaoshibianhao").val(),
                    jiaoshixingming: $("#jiaoshixingming").find("option:selected").text().trim(),
                    zhuanzhanghuming: $("#zhuanzhanghuming").val(),
                    shenfenzheng: $("#shenfenzheng").find("option:selected").text().trim(),
                    yinhangkahao: $("#yinhangkahao").val(),
                    yinhang: $("#yinhang").val(),
                    kaihuhanghuhao: $("#kaihuhanghuhao").val(),
                    hetongleixing: $("#hetongleixing").find("option:selected").text().trim(),
                    pinyongxingshi: $("#pinyongxingshi").find("option:selected").text().trim(),
                    xueqi: $("#xueqi").find("option:selected").text().trim(),
                    chuangjianshijian: odate
                };
                var url = "hetongxiugai";
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
                            window.location.href = "../guanliyuan/hetongguanli";
                        }, 3000);
                    },

                });
            }
        }
        //分页方法
        function loaddate(currentPage) {
                // alert("usertest loaddate"+currentPage)
                var data = {
                    currentPage: currentPage,
                    jiaoshibianhao: $("#search_jiaoshibianhao").val().trim(),
                    jiaoshixingming: $("#search_jiaoshixingming").val().trim(),
                    time: $("#time").val().trim(),
                }
                var url = "hetongdanganfenye"
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
                        $("#xingmingspan").html(pb.denglurenxingming);
                        if (pb.list!=null){

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

                        // var siz=2;
                        for (var i = 0; i < pb.list.length; i++) {
                            var t1='';
                            var tt='';
                            var tp='';
                            var hetong=pb.list[i];
                            var time=pb.time[i];
                            var lent=100;
                            for (size; size <pb.hetonginfoList.length ; size++) {
                                //var td='';
                                var hetonginfoList=pb.hetonginfoList[size];
                                var siz=hetonginfoList.size;
                                var show=size-brea;
                                console.log(show);
                                if (show==lent){
                                    brea=brea+lent;
                                    break;
                                }
                                var kechengming='<td> '+hetonginfoList.kechengmingcheng+'</td>\n';
                                var renkeban='<td> '+hetonginfoList.renkebanji+'</td>\n';
                                if (show==0){
                                    var kechengbian='<td> '+hetonginfoList.kechengbianhao+'</td>\n';
                                    var zongxue='<td> '+hetonginfoList.zongxueshi+'</td> \n';
                                    t1=kechengbian+kechengming+renkeban+zongxue;
                                }else {
                                    var kechengbian='<tr><td> '+hetonginfoList.kechengbianhao+'</td>\n';
                                    var zongxue='<td class="edit">'+hetonginfoList.zongxueshi+'</td></tr>\n';
                                    tt+=kechengbian+kechengming+renkeban+zongxue;
                                    console.log(tt);
                                }
                                lent=siz;
                               // siz=
                            }
                            tp=tt;
                            tt='';
                            var bl='';
                            $("#caozuo").hide();

                            if (hetong.xueqi===pb.xueqi) {
                                bl=  '                <td rowspan="'+lent+'" style="vertical-align: middle">\n' +
                                        '                    <a  href="#" id="edit" class="edit" >编辑</a>\n' +
                                        '                    <a  href="hetongdel?hetongid='+hetong.id+'" id="del" class="del">删除</a>\n' +
                                        '                </td>\n' ;
                                $("#caozuo").show();
                            }
                            var li='<tr>\n' +
                                    '                <td rowspan="'+lent+'" style="vertical-align: middle;font-size: 15px"> '+hetong.id+'</td>\n' +
                                    '                <td rowspan="'+lent+'" style="vertical-align: middle;font-size: 15px"> '+hetong.jiaoshibianhao+'</td>\n' +
                                    '                <td rowspan="'+lent+'" style="vertical-align: middle;font-size: 15px"> '+hetong.jiaoshixingming+'</td>\n' +
                                    '                <td rowspan="'+lent+'" style="vertical-align: middle"> '+hetong.zhuanzhanghuming+'</td>\n' +
                                    '                <td rowspan="'+lent+'" style="vertical-align: middle"> '+hetong.shenfenzheng+'</td>\n' +
                                    '                <td rowspan="'+lent+'" style="vertical-align: middle"> '+hetong.yinhangkahao+'</td>\n' +
                                    '                <td rowspan="'+lent+'" style="vertical-align: middle"> '+hetong.yinhang+'</td>\n' +
                                    '                <td rowspan="'+lent+'" style="vertical-align: middle"> '+hetong.kaihuhanghuhao+'</td>\n' +
                                    '                <td rowspan="'+lent+'" style="vertical-align: middle"> '+hetong.hetongleixing+'</td>\n' +
                                    '                <td rowspan="'+lent+'" style="vertical-align: middle"> '+hetong.pinyongxingshi+'</td>\n' +
                                    '                <td rowspan="'+lent+'" style="vertical-align: middle"> '+hetong.keshidanjia+'</td>\n' +t1+
                                    '                <td rowspan="'+lent+'" style="vertical-align: middle"> '+hetong.zhuangzhangjine+'</td>\n' +
                                    '                <td rowspan="'+lent+'" style="vertical-align: middle"><a href="${request.contextPath}/images/'+hetong.hetongurl+'">'+hetong.hetongname+'</a></td>\n' +
                                    '                <td rowspan="'+lent+'" style="vertical-align: middle"> '+hetong.xueqi+'</td>\n' +
                                    '                <td rowspan="'+lent+'" style="vertical-align: middle"> '+time+'</td>\n' + bl+

                                    '                <td rowspan="'+lent+'" style="vertical-align: middle">\n' +
                                    '                    <a  href="shengcheng2?hetongid='+hetong.id+'" id="shengcheng" class="">生成文档</a>\n' +
                                    '                    <a  href="tohetongUpload?hetongid='+hetong.id+'" id="tohetongUpload" class="">上传附件</a>\n' +
                                    '                </td>\n' +
                                    '            </tr>'+tp;
                            lis+=li;

                        }
                        $("#show_tbody").html(lis);

                    } }
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
                            <li><a href="shijiangguanli"><span>试讲档案管理</span></a></li>
                        </ul>
                    </li>
                    <li class="lsm-sidebar-item">
                        <a href="javascript:;"><i class="my-icon lsm-sidebar-icon icon_3"></i><span>合同管理</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a class="active" href="hetongguanli"><span>合同管理</span></a></li>
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
                                <div class="input-group mb-3" >
                                    <span>学期选择：</span>
                                    <input id="time" name="time" class="form-control select_down" style="font-size: 13px; color: #666;"placeholder="学期如：2019-2020-2" />
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
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>合同编号</label></td>
                                            <td><input   id="id" name="id" class="id " type="text" placeholder="自动生成" readonly="readonly"/></td>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>教师编号</label>
                                            </td>
                                            <td>
                                                <select id="jiaoshibianhao" name="jiaoshibianhao" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                    <option>请选择</option>
                                                    <#list jiaoshiList as s>
                                                    <option value="${s.id}">${s.id}</option>
                                                    </#list>
                                                </select>
                                            </td>   </tr>
                                        <tr>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>教师姓名</label>
                                            </td>
                                            <td>
                                                <select id="jiaoshixingming" name="jiaoshixingming" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                    <option>请选择</option>
                                                    <#list jiaoshiList as s>
                                                    <option value="${s.id}">${s.xingming}</option>
                                                    </#list>
                                                </select>
                                            </td><td class="tb_bg"><label for="">转账户名</label></td>
                                            <td><input id="zhuanzhanghuming" name="zhuanzhanghuming" type="text" placeholder="请输入转账户名"/></td>
                                        </tr>
                                        <tr>
                                            <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>身份证</label>
                                            </td>
                                            <td>
                                                <select id="shenfenzheng" name="shenfenzheng" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                    <option>请选择</option>
                                                    <#list jiaoshiList as s>
                                                    <option value="${s.id}">${s.shenfenzheng}</option>
                                                    </#list>
                                                </select>
                                            </td><td class="tb_bg"><label for="">银行卡号</label></td>
                                            <td><input id="yinhangkahao" name="yinhangkahao" type="text" placeholder="请输入银行卡号"/></td>
                                        </tr>
                                        <tr>
                                            <td class="tb_bg"><label for="">所属银行</label></td>
                                            <td><input id="yinhang" name="yinhang" type="text" placeholder="请输入所属银行"/></td>
                                            <td class="tb_bg"><label for="">开户行户号</label></td>
                                            <td><input id="kaihuhanghuhao" name="kaihuhanghuhao" type="text" placeholder="请输入开户行户号"/></td>
                                        </tr>
                                        <tr>
                                            <td class="tb_bg"><label for="">合同类型</label></td>
                                            <td>
                                                <select id="hetongleixing" name="hetongleixing" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                    <option>请选择</option>
                                                    <option value="1" >派遣制合同</option>
                                                    <option value="2" >外聘制合同</option>
                                                </select>
                                            </td>
                                            <td class="tb_bg"><label for="">聘用形式</label></td>
                                            <td>
                                                <select id="pinyongxingshi" name="pinyongxingshi" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                    <option>请选择</option>
                                                    <option value="1" >派遣制</option>
                                                    <option value="2" >外聘制</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="tb_bg"><label for="">学期</label></td>
                                            <td>
                                                <select id="xueqi" name="xueqi" class="form-control select_down" style="font-size: 13px; color: #666;" >
                                                    <option>请选择</option>
                                                    <#list xueqiList as s>
                                                    <option value="${s.text}">${s.text}</option>
                                                    </#list>
                                                </select>
                                            </td>
                                            <td class="tb_bg"><label for="">起效时间</label></td>
                                            <td><input id="chuangjianshijian" name="chuangjianshijian" type="date" /></td>
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
                        <th >ID</th>
                        <th style="width: 163px">教师编号</th>
                        <th style="width: 130px">教师姓名</th>
                        <th style="width: 90px">转账户名</th>
                        <th >身份证号</th>
                        <th >银行卡号</th>
                        <th style="width: 220px">所属银行</th>
                        <th style="width: 90px">开户行户号</th>
                        <th style="width: 175px">合同类型</th>
                        <th style="width: 155px">聘用形式</th>
                        <th style="width: 120px">工资单价</th>
                        <th style="width: 153px">课程编号</th>
                        <th style="width: 150px">课程名称</th>
                        <th style="width: 160px">任课班级</th>
                        <th style="width: 110px">总学时</th>
                        <th style="width: 121px">转账金额</th>
                        <th style="width: 139px">已上传合同</th>
                        <th style="width: 160px">学期</th>
                        <th style="width: 150px">起效时间</th>
                        <th id="caozuo" style="width: 70px">操作</th>
                        <th style="width: 160px">更多操作</th>
                    </tr>
                    </thead>
                    <tbody id="show_tbody">
                    </tbody>
                </table>
            </div>
            <#--<div class="zxf_pagediv"></div>-->
            <#--<input id="yincang" type="text"  hidden="hidden">-->
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


