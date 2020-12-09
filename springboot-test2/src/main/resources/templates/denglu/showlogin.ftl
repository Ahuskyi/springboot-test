
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>外聘教师管理登录页</title>

<link href="${request.contextPath}/css/login.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${request.contextPath}/js/jquery.min.js"></script>
<#--<script src="${request.contextPath}/js/verificationNumbers.js" tppabs="${request.contextPath}/js/verificationNumbers.js"></script>-->
<style>
.J_codeimg{z-index:-1;position:absolute;}
</style>
<script>
$(document).ready(function() {
  //验证码
  createCode();
});
</script>
    <script>
        function showCheck(a){
            var c = document.getElementById("myCanvas");
            var ctx = c.getContext("2d");
            ctx.clearRect(0,0,1000,1000);
            ctx.font = "80px 'Microsoft Yahei'";
            ctx.fillText(a,0,100);
            ctx.fillStyle = "rgba(255,255,255,.9)";
        }
        var code ;
        function createCode(){
            code = "";
            var codeLength = 4;
            var selectChar = new Array(1,2,3,4,5,6,7,8,9,'a','b','c','d','e','f','g','h','j','k','l','m','n','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z');
            for(var i=0;i<codeLength;i++) {
                var charIndex = Math.floor(Math.random()*60);
                code +=selectChar[charIndex];
            }
            if(code.length != codeLength){
                createCode();
            }
            showCheck(code);
        }
        //验证验证码是否一致
        function validate () {
            var inputCode = document.getElementById("J_codetext").value.toUpperCase();
            var codeToUp=code.toUpperCase();
            if(inputCode.length <=0) {
                document.getElementById("J_codetext").setAttribute("placeholder","请输入验证码");
                createCode();
                return false;
            }
            else if(inputCode != codeToUp ){
                document.getElementById("J_codetext").value="";
                document.getElementById("J_codetext").setAttribute("placeholder","验证码错误");
                createCode();
                return false;
            }
            else {
                //window.open(document.getElementById("J_down").getAttribute("data-link"));
                //document.getElementById("J_codetext").value="";
                createCode();
                var data = {
                    yonghuming : $("#yonghuming").val(),
                    mima : $("#mima").val()
                }
                var url = "login"
                //alert("ajax url" +url)
                $.ajax({
                    url : url,
                    type : "post",
                    data : data,
                    dataType : "json",
                    cache : false,
                    success : function (map) {
                        if (map.errormsg!="null"&&map.errormsg!=null) {
                            alert(map.errormsg);
                        }else {
                            if (map.shenfen=="guanliyuan"){
                                window.location.href="../guanliyuan/showgerenzhongxin?dengluren="+map.dengluren}
                            if (map.shenfen=="xuesheng"){
                                window.location.href="../xuesheng/showgerenzhongxin?dengluren="+map.dengluren}
                            if (map.shenfen=="jiaoshi"){
                                window.location.href="../jiaoshi/showgerenzhongxin?dengluren="+map.dengluren}
                            if (map.shenfen=="zhuanjia"){
                                window.location.href="../zhuanjia/showgerenzhongxin?dengluren="+map.dengluren}
                        }
                    }
                    ,
                })
            }

        }
    </script>
</head>
<body>
<div class="login-box" id="demo">
   <div class="input-content">
     <div class="login_tit">
          <div>
            <i class="tit-bg left"></i>
              外聘教师管理系统
            <i class="tit-bg right"></i>
          </div>
          <p>Happy&nbsp;&nbsp;&nbsp;&nbsp;Everyday</p>
     </div>    
     <p class="p user_icon">
       <input id="yonghuming" name="yonghuming" type="text" placeholder="用户名" autocomplete="off" class="login_txtbx">
     </p> 
     <p class="p pwd_icon">
       <input id="mima" name="mima" type="password" placeholder="密码" autocomplete="off" class="login_txtbx">
     </p>     	
     <div class="p val_icon">
        <div class="checkcode">
          <input type="text" id="J_codetext" placeholder="验证码" autocomplete="off" maxlength="4" class="login_txtbx">
          <canvas class="J_codeimg" id="myCanvas" onclick="createCode()" onselectstart="return false">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>
        </div>
        <a class="ver_btn" onclick="createCode();" onselectstart="return false">看不清，换一张</a>
      </div>      
      <div class="signup">
      	  <a class="gv"  onclick="validate()">登&nbsp;&nbsp;录</a>
          <#--<a class="gv" href="#">清&nbsp;&nbsp;空</a>-->
     </div>
  </div>
  <div class="canvaszz"> </div>
  <canvas id="canvas"></canvas> 
</div>
<script>
//宇宙特效
"use strict";
var canvas = document.getElementById('canvas'),
  ctx = canvas.getContext('2d'),
  w = canvas.width = window.innerWidth,
  h = canvas.height = window.innerHeight,

  hue = 217,
  stars = [],
  count = 0,
  maxStars = 2500;//星星数量

var canvas2 = document.createElement('canvas'),
  ctx2 = canvas2.getContext('2d');
canvas2.width = 100;
canvas2.height = 100;
var half = canvas2.width / 2,
  gradient2 = ctx2.createRadialGradient(half, half, 0, half, half, half);
gradient2.addColorStop(0.025, '#CCC');
gradient2.addColorStop(0.1, 'hsl(' + hue + ', 61%, 33%)');
gradient2.addColorStop(0.25, 'hsl(' + hue + ', 64%, 6%)');
gradient2.addColorStop(1, 'transparent');

ctx2.fillStyle = gradient2;
ctx2.beginPath();
ctx2.arc(half, half, half, 0, Math.PI * 2);
ctx2.fill();

// End cache

function random(min, max) {
  if (arguments.length < 2) {
    max = min;
    min = 0;
  }

  if (min > max) {
    var hold = max;
    max = min;
    min = hold;
  }

  return Math.floor(Math.random() * (max - min + 1)) + min;
}

function maxOrbit(x, y) {
  var max = Math.max(x, y),
    diameter = Math.round(Math.sqrt(max * max + max * max));
  return diameter / 2;
  //星星移动范围，值越大范围越小，
}

var Star = function() {

  this.orbitRadius = random(maxOrbit(w, h));
  this.radius = random(60, this.orbitRadius) / 18; 
  //星星大小
  this.orbitX = w / 2;
  this.orbitY = h / 2;
  this.timePassed = random(0, maxStars);
  this.speed = random(this.orbitRadius) / 500000; 
  //星星移动速度
  this.alpha = random(2, 10) / 10;

  count++;
  stars[count] = this;
}

Star.prototype.draw = function() {
  var x = Math.sin(this.timePassed) * this.orbitRadius + this.orbitX,
    y = Math.cos(this.timePassed) * this.orbitRadius + this.orbitY,
    twinkle = random(10);

  if (twinkle === 1 && this.alpha > 0) {
    this.alpha -= 0.05;
  } else if (twinkle === 2 && this.alpha < 1) {
    this.alpha += 0.05;
  }

  ctx.globalAlpha = this.alpha;
  ctx.drawImage(canvas2, x - this.radius / 2, y - this.radius / 2, this.radius, this.radius);
  this.timePassed += this.speed;
}

for (var i = 0; i < maxStars; i++) {
  new Star();
}

function animation() {
  ctx.globalCompositeOperation = 'source-over';
  ctx.globalAlpha = 0.5; //尾巴
  ctx.fillStyle = 'hsla(' + hue + ', 64%, 6%, 2)';
  ctx.fillRect(0, 0, w, h)

  ctx.globalCompositeOperation = 'lighter';
  for (var i = 1, l = stars.length; i < l; i++) {
    stars[i].draw();
  };

  window.requestAnimationFrame(animation);
}

animation();
</script>

</body>
</html>