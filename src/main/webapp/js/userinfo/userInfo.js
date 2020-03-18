!function (global) {
    'use strict';

    var Layout = {
        obj: null,
        config: {
            pattern: "3J",
            offsets: {
                top: 2,
                right: 2,
                bottom: 2,
                left: 2
            },
            cells: [
                {
                    id: "a",
                    text: "基础信息设置",
                    collapsed_text: "单击展开基础信息设置",   // 折叠栏标题
                    collapse: false,       // 初始是否折叠
                    height: 420,
                    width: 900,
                    fix_size: [true, true],
                },
                {
                    id: "b",
                    text: "密码设置",
                    collapsed_text: "单击展开密码设置",
                    collapse: false,
                    fix_size: [true, true]
                },
                {
                    id: "c",
                    text: "手写签名照设置",
                    collapsed_text: "单击展开手写签名照设置",
                    collapse: false,
                    fix_size: [true, true]
                }
            ]
        },
        initObj: function () {
            Layout.obj = SystemHome.Layout.obj.cells("c").attachLayout(Layout.config);
        }
    };

    //基础信息设置表单
    var BasicSettingsForm = {
        obj: null,
        config: [
            {type: "settings", position: "label-left", offsetLeft: 80, offsetTop: 10},
            {
                type: "block", list: [
                    {
                        type: "input",
                        name: 'name',
                        label: '姓&nbsp&nbsp&nbsp名:',
                        inputWidth: 150,
                        maxLength: 8,
                        offsetTop: 30
                    },
                    {type: "newcolumn"},
                    {type: "input", name: 'jobNumber', label: '工号:', inputWidth: 180, maxLength: 12, offsetTop: 30}
                ]
            },
            {
                type: "block", list: [
                    {type: "label", label: '性&nbsp&nbsp&nbsp别:'},
                    {type: "newcolumn"},
                    {
                        type: "radio",
                        name: "sex",
                        value: "男",
                        label: "男",
                        position: "label-right",
                        inputTop: 2,
                        offsetLeft: 20,
                        checked: true
                    },
                    {type: "newcolumn"},
                    {
                        type: "radio",
                        name: "sex",
                        value: "女",
                        label: "女",
                        position: "label-right",
                        inputTop: 2,
                        offsetLeft: 20
                    },
                    {type: "newcolumn"},
                    {type: "input", name: 'age', label: '年龄:', inputWidth: 180, offsetLeft: 108}
                ]
            },
            {
                type: "block", list: [
                    {type: "input", name: 'phone', label: '手机号:', inputWidth: 150, maxLength: 11},
                    {type: "newcolumn"},
                    {type: "input", name: 'email', label: '邮箱:', inputWidth: 180}   //没有验证是否符合邮箱格式
                ]
            },
            {
                type: "block", list: [
                    {type: "input", name: 'note', label: '备&nbsp&nbsp&nbsp注:', rows: 3, inputWidth: 460}
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
        initobj: function () {
            BasicSettingsForm.obj = Layout.obj.cells("a").attachForm(BasicSettingsForm.config);
            BasicSettingsForm.writeBackForm();     //初始化时填写表单
        },
        initEvent: function () {
            BasicSettingsForm.obj.attachEvent("onButtonClick", function (name) {
                switch (name) {
                    case "saveButton":
                        BasicSettingsForm.saveBasicSettings();
                        break;
                    case "resetButton":
                        BasicSettingsForm.writeBackForm();
                        break;
                    default:
                }
            });
        },
        writeBackForm: function () {
            ajaxUtils.get('userInfo/getUserInfo.json'
            ).then(function (userInfo) {
                BasicSettingsForm.obj.setFormData(userInfo);
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        },
        saveBasicSettings: function () {
            var userInfo = BasicSettingsForm.obj.getFormData();
            ajaxUtils.postBody('userInfo/saveBasicSettings.json',
                userInfo
            ).then(function (data) {
                dhtmlxAlert.alertMsg("修改基础信息成功");
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        }
    };

    //密码修改表单
    var PasswordSettingsForm = {
        obj: null,
        config: [
            {type: "settings", position: "label-top", offsetLeft: 55, offsetTop: 10},
            {
                type: "block", list: [
                    {
                        type: "input",
                        name: 'name',
                        label: '用户名:',
                        inputWidth: 180,
                        maxLength: 12,
                        readonly: true,
                        style: "background:#eaeaea"
                    },
                    {type: "input", name: 'oldPassword', label: '原密码:', inputWidth: 180, maxLength: 12},
                    {type: "password", name: 'newPassword', label: '新密码:', inputWidth: 180, maxLength: 12},
                    {type: "password", name: 'confirmNewPassword', label: '确认新密码:', inputWidth: 180, maxLength: 12},
                ]
            },
            {
                type: "block", list: [
                    {type: "button", name: "saveButton", value: "保存", offsetLeft: 53, offsetTop: 15},
                    {type: "newcolumn"},
                    {type: "button", name: "cancelButton", value: "取消", offsetLeft: 35, offsetTop: 15}
                ]
            }
        ],
        initobj: function () {
            PasswordSettingsForm.obj = Layout.obj.cells("b").attachForm(PasswordSettingsForm.config);
            PasswordSettingsForm.obj.setItemValue("name", $.cookie("loginName"));
        },
        initEvent: function () {
            PasswordSettingsForm.obj.attachEvent("onButtonClick", function (name) {
                switch (name) {
                    case "saveButton":
                        PasswordSettingsForm.savePasswordSettings();
                        break;
                    case "cancelButton":
                        PasswordSettingsForm.cancelPasswordSettings();
                        break;
                    default:
                }
            });
        },
        //保存修改后的密码
        savePasswordSettings: function () {
            var oldPassword = PasswordSettingsForm.obj.getItemValue("oldPassword");
            var newPassword = PasswordSettingsForm.obj.getItemValue("newPassword");
            var confirmNewPassword = PasswordSettingsForm.obj.getItemValue("confirmNewPassword");
            if (isEmpty(oldPassword)) {
                dhtmlxAlert.alertWarningMsg("请输入原密码！");
                return;
            }
            if (isEmpty(newPassword)) {
                dhtmlxAlert.alertWarningMsg("请输入新密码！");
                return;
            }
            if (isEmpty(confirmNewPassword)) {
                dhtmlxAlert.alertWarningMsg("请输入确认新密码！");
                return;
            }
            if (newPassword != confirmNewPassword) {
                dhtmlxAlert.alertWarningMsg("两次输入的新密码不一致，请重新输入！");
                PasswordSettingsForm.obj.setItemValue("newPassword", "");
                PasswordSettingsForm.obj.setItemValue("confirmNewPassword", "");
                return;
            }
            ajaxUtils.postBody('userInfo/savePasswordSettings.json', {
                "oldPassword": oldPassword,
                "newPassword": newPassword
            }).then(function (flag) {
                if (flag == "true") {
                    dhtmlxAlert.alertMsg("修改密码成功");
                    PasswordSettingsForm.cancelPasswordSettings();
                } else if (flag == "false") {
                    dhtmlxAlert.alertWarningMsg("原密码错误！");
                }
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        },

        //清空密码栏
        cancelPasswordSettings: function () {
            PasswordSettingsForm.obj.setItemValue("oldPassword", "");
            PasswordSettingsForm.obj.setItemValue("newPassword", "");
            PasswordSettingsForm.obj.setItemValue("confirmNewPassword", "");
        }
    };

    var init = function () {
        Layout.initObj();
        BasicSettingsForm.initobj();
        BasicSettingsForm.initEvent();
        PasswordSettingsForm.initobj();
        PasswordSettingsForm.initEvent();
    };

    var UserInfo = function () {
    };
    UserInfo.init = init;
    global.UserInfo = UserInfo || {};

}(this);