/**
 * 弹出提示
 * @param msg
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
 * 弹出错误提示
 * @param msg
 */
var alertErrorMsg = function (msg, f) {
    dhtmlx.alert({
        type: 'alert-error',
        text: msg,
        title: '错误提示',
        ok: '确定',
        callback: typeof f === 'function' ? f : function () {
        }
    });
};