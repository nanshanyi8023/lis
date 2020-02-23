!function (global) {
    'use strict';

    var Layout = {
        obj:null,
        config:{
            parent:"RightLayoutObj",
            pattern: "3U",
            offsets: {
            top: 2,
            right: 2,
            bottom: 2,
            left: 2
            },
            cells:[
                {
                    id:"a",
                    text:"基础设置",
                    collapsed_text: "单击展开基础设置",   // 折叠栏标题
                    collapse: false,       // 初始是否折叠
                    height:420,
                    width:900,
                    fix_size: [true, true],
                },
                {
                    id:"b",
                    text:"手写签名照设置",
                    collapsed_text: "单击展开手写签名照设置",
                    collapse: false,
                    fix_size: [true, true]
                },
                {
                    id:"c",
                    text:"密码设置",
                    collapsed_text: "单击展开密码设置",
                    collapse: false,
                    fix_size: [true, true]
                }
            ]
        },
        initObj: function () {
            Layout.obj = new dhtmlXLayoutObject(Layout.config);
        }
    };

    var BasicSettingsForm = {
        obj:null,
        config:[
            {type:"settings",position:"label-left", offsetLeft: 80, offsetTop: 10},
            {
                type: "block", list: [
                    {type: "input", name: 'name', label: '姓&nbsp&nbsp&nbsp名:',inputWidth:150, maxLength:8, offsetTop:30},
                    {type: "newcolumn"},
                    {type: "input", name: 'jobNumber', label: '工号:', inputWidth:180, maxLength:12, offsetTop:30}
                ]
            },
            {
                type: "block", list: [
                    {type: "label", label: '性&nbsp&nbsp&nbsp别:'},
                    {type: "newcolumn"},
                    {type: "radio", name: "sex", value: "男", label: "男", position: "label-right", inputTop:2, offsetLeft: 20,checked:true},
                    {type: "newcolumn"},
                    {type: "radio", name: "sex", value: "女", label: "女", position: "label-right", inputTop:2, offsetLeft: 20},
                    {type: "newcolumn"},
                    {type: "input", name: 'age', label: '年龄:',inputWidth:180,offsetLeft: 108},
                ]
            },
            {
                type: "block", list: [
                    {type: "input", name: 'phone', label: '手机号:',inputWidth:150,maxLength:11},
                    {type: "newcolumn"},
                    {type: "input", name: 'email', label: '邮箱:', inputWidth:180}   //没有验证是否符合邮箱格式
                ]
            },
            {
                type:"block",list:[
                    {type: "input", name: 'note', label: '备&nbsp&nbsp&nbsp注:', rows:3 ,inputWidth:460}
                ]
            },
            {
                type: "block", list: [
                    {type: "button", name: "saveButton", value: "保存", offsetLeft: 200, offsetTop: 15},
                    {type: "newcolumn"},
                    {type: "button", name: "resetButton", value: "重置", offsetLeft: 100, offsetTop: 15}
                ]
            }
        ],
        initobj:function () {
            BasicSettingsForm.obj = Layout.obj.cells("a").attachForm(BasicSettingsForm.config);
            BasicSettingsForm.writeBackForm();     //初始化时填写表单
        },
        initEvent:function () {
            BasicSettingsForm.obj.attachEvent("onButtonClick",function (name) {
                switch (name) {
                    case "saveButton":
                        BasicSettingsForm.saveBasicSettings();
                        break;
                    case "resetButton":
                        BasicSettingsForm. writeBackForm();
                        break;
                    default:
                }
            });
        },
        writeBackForm:function(){
            ajaxUtils.get('userInfo/getUserInfo.json'
            ).then(function (userInfo) {
                BasicSettingsForm.obj.setFormData(userInfo);
            }).catch(function (reason) {
                alertErrorMsg(reason);
            }).finally(function () {
            });
        },
        saveBasicSettings:function () {
            var userInfo = BasicSettingsForm.obj.getFormData();
            ajaxUtils.postBody('userInfo/saveBasicSettings.json',
                userInfo
            ).then(function (data) {
                dhtmlx.alert("保存成功");
            }).catch(function (reason) {
                alertErrorMsg(reason);
            }).finally(function () {
            });
        }
    };


    var init = function () {
        Layout.initObj();
        BasicSettingsForm.initobj();
        BasicSettingsForm.initEvent();
    };

    var UserInfo = function () {
    };
    UserInfo.init = init;
    global.UserInfo = UserInfo || {};

}(this);