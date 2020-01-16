function doLogin() {
    ajaxUtils.get('login/doLogin.json', {
        loginName : $("#loginName").val(),
        loginPassword : $("#loginPassword").val()
    }).then(function (data) {
        if (data == "success") {
            alert("登录成功");
        }else if (data == "failure") {
            alert("用户名或密码错误");
        }else {
            alert("登录异常");
        }
    }).catch(function (reason) {
        // alertErrorMsg(reason);
    }).finally(function () {
    });
}