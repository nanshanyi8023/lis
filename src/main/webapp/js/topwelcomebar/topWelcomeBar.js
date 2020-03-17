//系统顶部工具栏
!function (global) {
    'use strict';
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
            //TopForm.obj = SystemHome.Layout.obj.cells("a").attachForm(TopForm.config);
        }
    };


    var init = function () {
        TopForm.initobj();
    };

    var TopWelcomeBar = function () {
    };
    TopWelcomeBar.init = init;
    global.TopWelcomeBar = TopWelcomeBar || {};

}(this);