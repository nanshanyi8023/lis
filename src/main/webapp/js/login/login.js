function doLogin() {
    ajaxUtils.get('login/doLogin.json', {
        loginName : $("#loginName").val(),
        loginPassword : $("#loginPassword").val()
    }).then(function (data) {
        if (data.length <= 0){
            alert("用户名或密码错误");
            return;
        }
        window.location.href="systemHome/getView.htm";   //跳转到系统主页面
    }).catch(function (reason) {
        alert(reason);
    }).finally(function () {
    });
}