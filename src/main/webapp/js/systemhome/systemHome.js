// 系统总页面分成三个部分
!function (global) {
    'use strict';
    var Layout = {
        obj: null,

        config: {
            parent: "SystemHomeObj",
            pattern: "3T",
            offsets: {
                top: 2,
                right: 2,
                bottom: 2,
                left: 2
            },
            cells: [
                {
                    id: "a",
                    text: "textA",
                    header: false,      // 隐藏标题
                    collapsed_text: "collapsed_textA",   // 折叠栏标题
                    collapse: false,       // 初始是否折叠
                    fix_size: [true, true],
                    height:70
                },
                {
                    id: "b",
                    text: "&nbsp&nbsp&nbsp&nbsp功能列表",
                    collapsed_text: "单击展开功能列表",   // 折叠栏标题
                    collapse: false,       // 初始是否折叠
                    fix_size: [true, true],
                    width:300
                },
                {
                    id: "c",
                    text: "textC",
                    header: false,      // 隐藏标题
                    collapsed_text: "collapsed_textC",   // 折叠栏标题
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
        TopWelcomeBar.init();
        LeftMenu.init();
    };

    var SystemHome = function(){};
    SystemHome.Layout = Layout;
    SystemHome.init = init;
    global.SystemHome = SystemHome||{};
}(this);