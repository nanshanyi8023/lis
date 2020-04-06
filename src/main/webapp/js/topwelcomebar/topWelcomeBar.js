//系统顶部工具栏
signOut = function () {
    window.location.href="systemHome/signOut.htm";
};

getToday = function () {
    var day = new Date();
    day.setTime(day.getTime());
    var count = 0;
    var s = day.getFullYear()+"-" + (day.getMonth()+1) + "-" + day.getDate() + ": 今日共检验" + count + "个样本";
    $("#topHeader-today").html(s);
};