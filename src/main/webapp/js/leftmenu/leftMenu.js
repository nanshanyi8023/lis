//系统左侧功能栏
!function (global) {
    'use strict';

    var MenuTree = {
        obj: null,
        config: {
            items: [
                {
                    id: "inspectManagement",
                    text: "检验管理",
                    open: 1,
                    items: [
                        {
                            id: "barCodePrint",
                            text: "&nbsp;条码打印",
                            icons: {file: "barCodePrint"}
                        },
                        {
                            id: "sampleRecept",
                            text: "&nbsp;样本接收",
                            icons: {file: "sampleRecept"}
                        },
                        {
                            id: "sampleReturn",
                            text: "&nbsp;样本退回",
                            icons: {file: "sampleReturn"}
                        },
                        {
                            id: "inspectionOperation",
                            text: "&nbsp;检验操作",
                            icons: {file: "inspectionOperation"}
                        },
                        {
                            id: "reportQuery",
                            text: "&nbsp;报告查询",
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
                            text: "检验项目设置"
                        },
                        {
                            id: "checkItemGroupSettings",
                            text: "检验项目组合设置"
                        },
                        {
                            id: "workGroupSettings",
                            text: "工作组设置"
                        },
                        {
                            id: "equipmentSettings",
                            text: "检验设备设置"
                        },
                        {
                            id: "equipmentChannelSettings",
                            text: "检验设备通道设置"
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
                    id: "userInfoSetting",
                    text: "&nbsp;个人信息设置",
                    icons:{file:"userInfoSetting"}
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
                    case "sampleRecept":
                        sampleRecept.init();
                        break;
                    case "sampleReturn":
                        sampleReturn.init();
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
                    case "userInfoSetting":
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
