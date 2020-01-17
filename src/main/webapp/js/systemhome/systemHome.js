!function (global) {
    'use strict';
var Layout = {
    obj: null,

    config: {
        parent: "layoutObj",
        pattern: "2U",
        offsets: {          // 调整位置
            top: 5,         // 距离顶部
            right: 5,       // 距离右部
            bottom: 5,      // 距离底部
            left: 5         // 距离左部
        },
        cells: [            //两个单元格
            {
                id: "a",        // 第一个单元格id
                text: "&nbsp&nbsp&nbsp&nbsp功能列表",     // 标题
                collapsed_text: "单击展开功能列表",   // 折叠栏标题
                width: 200,        // 宽度
                collapse: false,       // 初始是否折叠
                fix_size: [true, true] // 固定大小
            },
            {
                id: "b",
                text: "Text B",
                collapsed_text: "Text b",
                header: false,
                collapse: false,
                fix_size: [true, true]
            }
        ]
    },
    initObj: function () {
        Layout.obj = new dhtmlXLayoutObject(Layout.config);
    }
};
var Tree = {
    obj:null,
    config:{
        skin:"toolfile/dhtmlxstand/skins/skyblue/imgs/dhxtreeview_skyblue/",
        items: [
            {id: "inspectManagement", text: "检验管理", open: 1, items: [
                    {id: "sampleAccept", text: "样本接收"},
                    {id: "sampleReturn", text: "样本退回"},
                    {id: "inspectionOperation", text: "检验操作"},
                    {id: "reportQuery", text: "报告查询"},
                    {id: "barCodePrint", text: "条码打印"}
                ]},
            {id:"inspectSettings",text:"检验设置",open: 1,items:[
                    {id:"inspectionItemSettings",text:"检验项目设置"}
                ]
            }
        ]
    },
    initobj:function () {
        Tree.obj = Layout.obj.cells("a").attachTreeView(Tree.config);
        Tree.obj.setItemIcons("inspectManagement",{folder_opened: "inspectManagement", folder_closed: "inspectManagement"});
        Tree.obj.setItemIcons("sampleAccept",{file:"sampleAccept"});
        Tree.obj.setSizes();
    }
}

var init = function () {
    Layout.initObj();
    Tree.initobj();
};

    var SystemHome = function () {
    };
    SystemHome.init = init;
    global.SystemHome = SystemHome || {};

}(this);
