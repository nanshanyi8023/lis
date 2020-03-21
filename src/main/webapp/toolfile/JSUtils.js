/**
 * 封装常用的js工具类
 */

!function (global) {
    'use strict';
    //判空
    function isEmpty(obj){
        if(typeof obj == "undefined" || obj == null || obj == "" || obj.length <= 0){
            return true;
        }else{
            return false;
        }
    }
    //判断非空
    function isNotEmpty(obj) {
        return !this.isEmpty(obj);
    }

    var JSUtils = function(){};
    JSUtils.isEmpty = isEmpty;
    JSUtils.isNotEmpty = isNotEmpty;

    global.JSUtils = JSUtils||{};
}(this);
