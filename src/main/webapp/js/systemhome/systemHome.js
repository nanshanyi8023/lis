// 系统总页面分成三个部分
!function (global) {
    'use strict';
    var Layout = {
        obj: null,

        config: {
            parent: "rightHomeObj",
            pattern: "1C",
            offsets: {
                top: 0,
                right: 0,
                bottom: 0,
                left: 0
            },
            cells: [
                {
                    id: "a",
                    text: "textA",
                    header: false,      // 隐藏标题
                    collapsed_text: "collapsed_textA",   // 折叠栏标题
                    collapse: false,       // 初始是否折叠
                    fix_size: [true, true]
                }
            ]
        },
        initObj: function () {
            Layout.obj = new dhtmlXLayoutObject(Layout.config);
        }
    };
    var init = function () {
        Layout.initObj();
        LeftMenu.init();
    };

    var SystemHome = function(){};
    SystemHome.Layout = Layout;
    SystemHome.init = init;
    global.SystemHome = SystemHome||{};
}(this);