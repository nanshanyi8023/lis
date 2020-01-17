<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="no-js">
<head>
<base href="<%=basePath%>"/>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>login</title>
<link rel="stylesheet" type="text/css" href="css/login/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/login/demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="css/login/component.css" />
<!--[if IE]>
<script src="js/html5.js"></script>
<![endif]-->

	<script type="text/javascript" src="toolfile/jquery-3.4.1.js"></script>
	<script  type="text/javascript" src="js/login/login.js"></script>

    <style>   /*解决浏览器记住密码默认填充后改变输入框背景颜色的问题*/
        input:-webkit-autofill , textarea:-webkit-autofill, select:-webkit-autofill {
            -webkit-text-fill-color: #ededed !important;
            -webkit-box-shadow: 0 0 0px 1000px transparent  inset !important;
            background-color:transparent;
            background-image: none;
            transition: background-color 50000s ease-in-out 0s; /*背景色透明  生效时长  过渡效果  启用时延迟的时间*/
        }
        input {
            background-color:transparent;
        }
    </style>
</head>
<body>
		<div class="container demo-1">
			<div class="content">
				<div id="large-header" class="large-header">
					<canvas id="demo-canvas"></canvas>
					<div class="logo_box">
						<h3>云LIS检验系统</h3>
						<form action="#" name="f" method="post">
							<div class="input_outer">
								<span class="u_user"></span>
								<input id="loginName" class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入账户">
							</div>
							<div class="input_outer">
								<span class="us_uer"></span>
								<input id="loginPassword" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="password" placeholder="请输入密码">
							</div>
							<div class="mb2"><a class="act-but submit" id="submit" href="javascript:;" style="color: #FFFFFF" onclick="doLogin()">登录</a></div>
						</form>
					</div>
				</div>
			</div>
		</div><!-- /container -->
		<script  type="text/javascript" src="js/login/TweenLite.min.js"></script>
		<script  type="text/javascript" src="js/login/EasePack.min.js"></script>
		<script  type="text/javascript" src="js/login/rAF.js"></script>
		<script  type="text/javascript" src="js/login/demo-1.js"></script>
	</body>
</html>