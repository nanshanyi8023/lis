/**
 * 封装常用的js工具类
 */

!function (global) {
    'use strict';

    //判空
    function isEmpty(obj) {
        if (typeof obj === "undefined" || obj == null || obj === "" || obj.length <= 0) {
            return true;
        } else {
            return false;
        }
    }

    //判断非空
    function isNotEmpty(obj) {
        return !this.isEmpty(obj);
    }

    //判断字符串是否全部为数字(全部由0-9构成)
    function isNum(val) {
        var isnum = /^\d+$/.test(val);
        return isnum;
    }

    function isNotNum(val) {
        return !this.isNum(val);
    }

    //得到当前天("2016-02-12")
    function getToday() {
        var date = new Date();
        var seperator1 = "-";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        return date.getFullYear() + seperator1 + month + seperator1 + strDate;
    }

    var JSUtils = function () {
    };
    JSUtils.isEmpty = isEmpty;
    JSUtils.isNotEmpty = isNotEmpty;
    JSUtils.isNum = isNum;
    JSUtils.isNotNum = isNotNum;
    JSUtils.getToday = getToday;

    global.JSUtils = JSUtils || {};
}(this);
