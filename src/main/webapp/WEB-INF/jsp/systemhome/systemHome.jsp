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
    <link type="text/css" rel="stylesheet" href="toolfile/pagination/page.css">

    <script type="text/javascript" src="toolfile/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="toolfile/jquery.cookie.js"></script>
    <script type="text/javascript" src="toolfile/dhtmlxstand/codebase/dhtmlx.js"></script>
    <script type="text/javascript" src="toolfile/ajaxUtils.js"></script>
    <script type="text/javascript" src="toolfile/dhtmlxAlert.js"></script>
    <script type="text/javascript" src="toolfile/dhtmlxUtils.js"></script>
    <script type="text/javascript" src="toolfile/JSUtils.js"></script>
    <script type="text/javascript" src="toolfile/dhtmlxCalendarLanguage.js"></script>
    <script type="text/javascript" src="toolfile/lodopFuncs.js"></script>
    <script type="text/javascript" src="toolfile/pagination/jquery.pagination.js"></script>

    <script type="text/javascript" src="js/systemhome/systemHome.js"></script>
    <script type="text/javascript" src="js/topwelcomebar/topWelcomeBar.js"></script>
    <script type="text/javascript" src="js/leftmenu/leftMenu.js"></script>
    <script type="text/javascript" src="js/userinfo/userInfo.js"></script>
    <script type="text/javascript" src="js/checksettings/checkItemSettings.js"></script>
    <script type="text/javascript" src="js/checksettings/checkItemGroupSettings.js"></script>
    <script type="text/javascript" src="js/checksettings/workGroupSettings.js"></script>
    <script type="text/javascript" src="js/checkmanage/barCodePrint.js"></script>
    <script type="text/javascript" src="js/checkmanage/barCodePrintLodop.js"></script>
    <script type="text/javascript" src="js/checkmanage/sampleRecept.js"></script>
    <script type="text/javascript" src="js/checkmanage/sampleReturn.js"></script>


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
        .topFormClass{
            background-color: #333333;
        }

        .sampleRecept {
            background-image: url(images/systemhome/sampleRecept.png);
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
        .userInfoSetting{
            background-image: url(images/systemhome/userInfoSetting.png);
        }

        /*设置左侧菜单栏样式*/
        /*margin*/
        .dhxtreeview_dhx_skyblue div.dhxtreeview_cont div.dhxtreeview_area div.dhxtreeview_item {
            margin-top: 5px;
        }
        /*整体背景颜色   #02224a*/
        .dhxtreeview_dhx_skyblue {
            background-color: #333333;
        }
        /*字的颜色和背景颜色*/
        .dhxtreeview_dhx_skyblue div.dhxtreeview_cont div.dhxtreeview_area div.dhxtreeview_item div.dhxtreeview_item_text div.dhxtreeview_item_label {
            color: #bbb;
            background-color: #333333;
            font-size: 13px;
        }
        /*项目高度及项目间边框*/
        .dhxtreeview_dhx_skyblue div.dhxtreeview_cont div.dhxtreeview_area div.dhxtreeview_item div.dhxtreeview_item_text {
            border: 1px solid #333333 ;
            height: 30px;
        }
        /*选中行背景颜色和边框颜色 #4a90e2*/
        .dhxtreeview_dhx_skyblue div.dhxtreeview_cont div.dhxtreeview_area div.dhxtreeview_item div.dhxtreeview_item_text.dhxtreeview_item_text_selected, .dhxtreeview_dhx_skyblue div.dhxtreeview_cont div.dhxtreeview_area div.dhxtreeview_item div.dhxtreeview_item_text.dhxtreeview_item_text_selected:hover {
            background-color: #333333;
            border-color: #333333;
        }
        /*选中行字的背景颜色，边框颜色和字的颜色*/
        .dhxtreeview_dhx_skyblue div.dhxtreeview_cont div.dhxtreeview_area div.dhxtreeview_item div.dhxtreeview_item_text.dhxtreeview_item_text_selected div.dhxtreeview_item_label, .dhxtreeview_dhx_skyblue div.dhxtreeview_cont div.dhxtreeview_area div.dhxtreeview_item div.dhxtreeview_item_text.dhxtreeview_item_text_selected:hover div.dhxtreeview_item_label {
            background-color: #333333;
            border-color: #333333;
            color: #ffffff;
        }
        /*鼠标悬停时的背景颜色和边框颜色*/
        .dhxtreeview_dhx_skyblue div.dhxtreeview_cont div.dhxtreeview_area div.dhxtreeview_item div.dhxtreeview_item_text:hover {
            background-color: #4c4c4c;
            border-color: #4c4c4c;
        }
        /*鼠标悬停时字的背景颜色和边框颜色*/
        .dhxtreeview_dhx_skyblue div.dhxtreeview_cont div.dhxtreeview_area div.dhxtreeview_item div.dhxtreeview_item_text:hover div.dhxtreeview_item_label {
            border-color: #4c4c4c;
            background-color: #4c4c4c;
        }

        /*设置按钮样式*/
        .dhxform_obj_dhx_skyblue div.dhxform_btn {
            color: #ffffff;
            background: -webkit-linear-gradient(#7190d8, #97bdec);
            border-radius: 5px;
        }
        /*鼠标悬停时的背景颜色*/
        .dhxform_obj_dhx_skyblue div.dhxform_btn.dhxform_btn_over {
            background: -webkit-linear-gradient(#7190d8, #97bdec);
        }
        /*点击按钮时背景颜色*/
        .dhxform_obj_dhx_skyblue div.dhxform_btn.dhxform_btn_pressed {
            background: -webkit-linear-gradient(#7190d8, #97bdec);
        }
        .dhxform_obj_dhx_skyblue div.dhxform_btn:focus {
            outline: 0px dotted #97bdec;
        }
    </style>

</head>
<body>
<div id="SystemHomeObj" style="width: 100%;height:100%"></div>
<script type="text/javascript">
    $(document).ready(function () {
        //设置dhtmlx时钟的语言
        dhtmlxCalendarLanage.setChinese();
        //初始化页面
        SystemHome.init();

       /* $(".dhxlayout_sep").height(0);
        $(".dhx_cell_layout dhx_cell_nested_layout").css("top","70px");*/
    });
</script>

</body>
</html>