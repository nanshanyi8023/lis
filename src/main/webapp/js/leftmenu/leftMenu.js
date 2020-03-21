//系统左侧功能栏
!function (global) {
    'use strict';

    var MenuTree = {
        obj: null,
        config: {
            skin: "toolfile/dhtmlxstand/skins/skyblue/imgs/dhxtreeview_skyblue/",
            items: [
                {
                    id: "inspectManagement",
                    text: "检验管理",
                    open: 1,
                    // icons: {folder_opened: "inspectManagement", folder_closed: "inspectManagement"},
                    items: [
                        {
                            id: "barCodePrint",
                            text: "条码打印",
                            icons: {file: "barCodePrint"}
                        },
                        {
                            id: "sampleAccept",
                            text: "样本接收",
                            icons: {file: "sampleAccept"}
                        },
                        {
                            id: "sampleReturn",
                            text: "样本退回",
                            icons: {file: "sampleReturn"}
                        },
                        {
                            id: "inspectionOperation",
                            text: "检验操作",
                            icons: {file: "inspectionOperation"}
                        },
                        {
                            id: "reportQuery",
                            text: "报告查询",
                            icons: {file: "reportQuery"}
                        }
                    ]
                },
                {
                    id: "inspectSettings",
                    text: "检验设置",
                    open: 1,
                    items: [
                        {
                            id: "checkItemSettings",
                            text: "检验项目设置",
                            icons:{file:""}
                        },
                        {
                            id: "checkItemGroupSettings",
                            text: "检验项目组合设置",
                            icons:{file:""}
                        },
                        {
                            id: "workGroupSettings",
                            text: "工作组设置",
                            icons:{file:""}
                        },
                        {
                            id: "equipmentSettings",
                            text: "检验设备设置",
                            icons:{file:""}
                        },
                        {
                            id: "equipmentChannelSettings",
                            text: "检验设备通道设置",
                            icons:{file:""}
                        }
                    ]
                },
                {
                    id: "inspectionOrder",
                    text: "检验医嘱",
                    open: 0,
                    items: [
                        {
                            id: "issueInspectionOrder",
                            text: "开具检验医嘱",
                            icons:{file:""}
                        },
                        {
                            id: "inspectionItemGroup",
                            text: "检验医嘱项目分组",
                            icons:{file:""}
                        }
                    ]
                },
                {
                    id: "userinfoSetting",
                    text: "个人信息设置",
                    icons:{file:"userinfoSetting"}
                }
            ]
        },
        initobj: function () {
            MenuTree.obj = SystemHome.Layout.obj.cells("b").attachTreeView(MenuTree.config);
            MenuTree.obj.setSizes();
        },
        initEvent: function () {
            MenuTree.obj.attachEvent("onSelect", function(id){
                SystemHome.Layout.obj.cells("c").detachObject();    //清空右边内容
                switch (id) {
                    case "barCodePrint":
                        barCodePrint.init();
                        break;
                    case "checkItemSettings":
                        checkItemSettings.init();
                        break;
                    case "checkItemGroupSettings":
                        checkItemGroupSettings.init();
                        break;
                    case "workGroupSettings":
                        workGroupSettings.init();
                        break;
                    case "userinfoSetting":
                        UserInfo.init();
                        break;
                }
            });
        }
    };

    var init = function () {
        MenuTree.initobj();
        MenuTree.initEvent();
    };

    var LeftMenu = function () {
    };
    LeftMenu.init = init;
    global.LeftMenu = LeftMenu || {};

}(this);
