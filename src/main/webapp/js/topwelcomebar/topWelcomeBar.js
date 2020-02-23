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

    var TopForm = {
        obj:null,

        config:[
            {
                type: "image",
                name: "topImage",
                url:  "images/systemhome/topImage.png",
                offsetLeft:20,
                imageWidth: 550,
                imageHeight: 70,
                inputWidth: 560,
                inputHeight:75
            }
        ],
        initobj : function () {
            TopForm.obj = Layout.obj.cells("a").attachForm(TopForm.config);
        }
    };




    var init = function () {
        Layout.initObj();
        TopForm.initobj();
    };

    var TopWelcomeBar = function () {
    };
    TopWelcomeBar.init = init;
    global.TopWelcomeBar = TopWelcomeBar || {};

}(this);