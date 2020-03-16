<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>云LIS检验系统主界面</title>
    <link type="text/css" rel="stylesheet" href="toolfile/dhtmlxstand/codebase/dhtmlx.css" />

    <script type="text/javascript" src="toolfile/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="toolfile/jquery.cookie.js"></script>
    <script type="text/javascript" src="toolfile/dhtmlxstand/codebase/dhtmlx.js"></script>
    <script type="text/javascript" src="toolfile/ajaxUtils.js"></script>
    <script type="text/javascript" src="toolfile/dhtmlxAlert.js"></script>
    <script type="text/javascript" src="toolfile/dhtmlxUtils.js"></script>
    <script type="text/javascript" src="toolfile/isEmpyt.js"></script>

    <script type="text/javascript" src="js/topwelcomebar/topWelcomeBar.js"></script>
    <script type="text/javascript" src="js/leftmenu/leftMenu.js"></script>
    <script type="text/javascript" src="js/userinfo/userInfo.js"></script>
    <script type="text/javascript" src="js/checksettings/checkItemSettings.js"></script>
    <script type="text/javascript" src="js/checksettings/checkItemGroupSettings.js"></script>
    <script type="text/javascript" src="js/checksettings/workGroupSettings.js"></script>
    <script type="text/javascript" src="js/systemhome/systemHome.js"></script>



    <style type="text/css">
        html, body {
            width: 100%;
            height: 100%;
            overflow: hidden;
            margin: 0px;
        }
        .sampleAccept{
            background-image: url(images/systemhome/sampleAccept.png);
        }
        .sampleReturn{
            background-image: url(images/systemhome/sampleReturn.png);
        }
        .inspectionOperation{
            background-image: url(images/systemhome/inspectionOperation.png);
        }
        .reportQuery{
            background-image: url(images/systemhome/reportQuery.png);
        }
        .barCodePrint{
            background-image: url(images/systemhome/barCodePrint.png);
        }
        .userinfoSetting{
            background-image: url(images/systemhome/userInfoSetting.png);
        }
    </style>

</head>
<body>
<div id = "SystemHomeObj"  style="width: 100%;height: 100%;"></div>
<script type="text/javascript">
    $(document).ready(function () {
        //初始化页面
        SystemHome.init();
    });
</script>

</body>
</html>