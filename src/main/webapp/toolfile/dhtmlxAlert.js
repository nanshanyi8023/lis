!function (global) {
    'use strict';
    /**
     * 弹出提示
     */
    var alertMsg = function (msg, f) {
        dhtmlx.alert({
            text: msg,
            title: '提示',
            ok: '确定',
            callback: typeof f === 'function' ? f : function () {
            }
        });
    };

    /**
     * 弹出警告提示
     */
    var alertWarningMsg = function (msg, f) {
        dhtmlx.alert({
            type: 'alert-warning',
            text: msg,
            title: '警告',
            ok: '确定',
            callback: typeof f === 'function' ? f : function () {
            }
        });
    };

    /**
     * 弹出错误提示
     */
    var alertErrorMsg = function (msg, f) {
        dhtmlx.alert({
            type: 'alert-error',
            text: msg,
            title: '错误',
            ok: '确定',
            callback: typeof f === 'function' ? f : function () {
            }
        });
    };

    /**
     *  确认信息
     */

    var confirmMsg = function (msg, f) {
        dhtmlx.confirm({
            type: "confirm",
            title: "提示信息",
            text: msg,
            ok: "确认",
            cancel: "取消",
            callback: typeof f === 'function' ? f : function () {
            }
        });
    };

    /**
     *  确认警告信息
     */

    var confirmWarningMsg = function (msg, f) {
        dhtmlx.confirm({
            type: "confirm-warning",
            title: "警告信息",
            text: msg,
            ok: "确认",
            cancel: "取消",
            callback: typeof f === 'function' ? f : function () {
            }
        });
    };

    /**
     *  确认错误信息
     */

    var confirmErrorMsg = function (msg, f) {
        dhtmlx.confirm({
            type: "confirm-error",
            title: "错误信息",
            text: msg,
            ok: "确认",
            cancel: "取消",
            callback: typeof f === 'function' ? f : function () {
            }
        });
    };

    var dhtmlxAlert = function(){
    };
    dhtmlxAlert.alertMsg = alertMsg;
    dhtmlxAlert.alertWarningMsg = alertWarningMsg;
    dhtmlxAlert.alertErrorMsg = alertErrorMsg;
    dhtmlxAlert.confirmMsg = confirmMsg;
    dhtmlxAlert.confirmWarningMsg = confirmWarningMsg;
    dhtmlxAlert.confirmErrorMsg = confirmErrorMsg;

    global.dhtmlxAlert = dhtmlxAlert;
}(this);