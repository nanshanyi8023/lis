!function (global) {
    'use strict';
    var Layout = {
        obj: null,

        config: {
            parent: "TopLayoutObj",
            pattern: "1C",
            offsets: {
                top: 2,
                right: 2,
                bottom: 0,
                left: 2
            },
            cells: [
                {
                    id: "a",
                    text: "testA",
                    collapsed_text: "collapsed_textA",   // 折叠栏标题
                    header: false,   //隐藏标题
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
    };

    var TopWelcomeBar = function () {
    };
    TopWelcomeBar.init = init;
    global.TopWelcomeBar = TopWelcomeBar || {};

}(this);