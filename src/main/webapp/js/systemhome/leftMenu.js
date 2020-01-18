!function (global) {
    'use strict';
    var Layout = {
        obj: null,

        config: {
            parent: "LeftLayoutObj",
            pattern: "1C",
            offsets: {
                top: 2,
                right: 2,
                bottom: 2,
                left: 2
            },
            cells: [
                {
                    id: "a",
                    text: "&nbsp&nbsp&nbsp&nbsp功能列表",
                    collapsed_text: "单击展开功能列表",   // 折叠栏标题
                    width: 200,
                    height:600,
                    collapse: false,       // 初始是否折叠
                    fix_size: [true, true]
                }
            ]
        },
        initObj: function () {
            Layout.obj = new dhtmlXLayoutObject(Layout.config);
        }
    };

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
                        },
                        {
                            id: "barCodePrint",
                            text: "条码打印",
                            icons: {file: "barCodePrint"}
                        }
                    ]
                },
                {
                    id: "inspectSettings",
                    text: "检验设置",
                    open: 1,
                    items: [
                        {
                            id: "inspectionItemSettings",
                            text: "检验项目设置",
                            icons:{file:""}
                        }
                    ]
                },
                {
                    id: "userinfoSetting",
                    text: "个人信息设置",
                    icons:{file:"userinfoSetting"}
                    // icons: {folder_opened: "userinfoSetting", folder_closed: "userinfoSetting"}
                }
            ]
        },
        initobj: function () {
            MenuTree.obj = Layout.obj.cells("a").attachTreeView(MenuTree.config);
            MenuTree.obj.setSizes();
        },
        initEvent: function () {
            MenuTree.obj.attachEvent("onClick", function(id){

            });
        }
    };

    var init = function () {
        Layout.initObj();
        MenuTree.initobj();
        MenuTree.initEvent();
    };

    var LeftMenu = function () {
    };
    LeftMenu.init = init;
    global.LeftMenu = LeftMenu || {};

}(this);
