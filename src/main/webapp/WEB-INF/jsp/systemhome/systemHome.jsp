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
    <link type="text/css" rel="stylesheet" href="toolfile/pagination/page.css" />
    <link type="text/css" rel="stylesheet" href="toolfile/dhtmlxcustomize/dhtmlxInit.css" />
    <link type="text/css" rel="stylesheet" href="css/topwelcombebar/topInit.css"/>

    <script type="text/javascript" src="toolfile/jquery/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="toolfile/jquery/jquery.cookie.js"></script>
    <script type="text/javascript" src="toolfile/dhtmlxstand/codebase/dhtmlx.js"></script>
    <script type="text/javascript" src="toolfile/ajax/ajaxUtils.js"></script>
    <script type="text/javascript" src="toolfile/dhtmlxcustomize/dhtmlxAlert.js"></script>
    <script type="text/javascript" src="toolfile/dhtmlxcustomize/dhtmlxUtils.js"></script>
    <script type="text/javascript" src="toolfile/JSUtils.js"></script>
    <script type="text/javascript" src="toolfile/dhtmlxcustomize/dhtmlxInit.js"></script>
    <script type="text/javascript" src="toolfile/lodop/lodopFuncs.js"></script>
    <script type="text/javascript" src="toolfile/pagination/jquery.pagination.js"></script>

    <script type="text/javascript" src="js/systemhome/systemHome.js"></script>
    <script type="text/javascript" src="js/topwelcomebar/topWelcomeBar.js"></script>
    <script type="text/javascript" src="js/leftmenu/leftMenu.js"></script>
    <script type="text/javascript" src="js/userinfo/userInfo.js"></script>
    <script type="text/javascript" src="js/checksettings/checkItemSettings.js"></script>
    <script type="text/javascript" src="js/checksettings/checkItemGroupSettings.js"></script>
    <script type="text/javascript" src="js/checksettings/equipmentSettings.js"></script>
    <script type="text/javascript" src="js/checkmanage/barCodePrint.js"></script>
    <script type="text/javascript" src="js/checkmanage/barCodePrintLodop.js"></script>
    <script type="text/javascript" src="js/checkmanage/sampleRecept.js"></script>
    <script type="text/javascript" src="js/checkmanage/sampleReturn.js"></script>
    <script type="text/javascript" src="js/checkmanage/reportEntry.js"></script>



    <object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
        <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
    </object>

    <style type="text/css">
        html, body {
            width: 100%;
            height: 100%;
            overflow: hidden;
            margin: 0px;
        }

        .sampleRecept {
            background-image: url(images/systemhome/sampleRecept.png);
        }
        .sampleReturn{
            background-image: url(images/systemhome/sampleReturn.png);
        }
        .reportEntry{
            background-image: url(images/systemhome/reportEntry.png);
        }
        .reportQuery{
            background-image: url(images/systemhome/reportQuery.png);
        }
        .barCodePrint{
            background-image: url(images/systemhome/barCodePrint.png);
        }
        .userInfoSetting{
            background-image: url(images/systemhome/userInfoSetting.png);
        }
    </style>

</head>
<body>
<div style="width: 100%;height:100%">
    <div id="TopObj" style="width: 100%;height:7%" class="topHeader">
        <div class="topHeader-logo"></div>
        <div class="topHeader-user"></div>
        <div class="topHeader-tools"></div>
    </div>
    <div id="SystemHomeObj" style="width: 100%;height: 93%;" >
        <div id="leftHomeObj" style="width: 13%;height: 100%;float:left;"></div>
        <div id="rightHomeObj" style="width: 87%;height: 100%;float:left;"></div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        //设置dhtmlx时钟的语言
        dhtmlxInit.setCalendarChinese();
        //初始化页面
        SystemHome.init();
    });
</script>

</body>
</html>