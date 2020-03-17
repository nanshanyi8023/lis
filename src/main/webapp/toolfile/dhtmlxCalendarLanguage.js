// 将dhtmlx中的calendar由英文转为中文
!function (global) {
    'use strict';

var setChinese = function () {
    dhtmlXCalendarObject.prototype.lang = "cn";
    dhtmlXCalendarObject.prototype.langData["cn"] = {
        // date format for inputs
        dateformat: "%Y-%m-%d",
        // header format
        hdrformat: "%Y %F",
        // full names of months
        monthesFNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
        // short names of months
        monthesSNames: ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"],
        // full names of days
        daysFNames: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
        // short names of days
        daysSNames: ["日", "一", "二", "三", "四", "五", "六"],
        // starting day of a week. Number from 1(Monday) to 7(Sunday)
        weekstart: 1,
        // the title of the week number column
        weekname: "周",
        // name of the "Today" button
        today: "今天",
        // name of the "Clear" button
        clear: "清除"
    };
};

var dhtmlxCalendarLanage = function(){};
    dhtmlxCalendarLanage.setChinese = setChinese;
global.dhtmlxCalendarLanage = dhtmlxCalendarLanage||{};
}(this);