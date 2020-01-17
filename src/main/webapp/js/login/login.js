function doLogin() {
    /*ajaxUtils.get('login/doLogin.json', {
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
         alert(reason);
    }).finally(function () {
    });*/

    $.ajax({
        url:"login/doLogin.json",
        type:"get",
        data:{
            loginName : $("#loginName").val(),
            loginPassword : $("#loginPassword").val()
        },
        dataType:"text",
        success:function(data){
            if (data == "success") {
                alert("登录成功");
            }else if (data == "failure") {
                alert("用户名或密码错误");
            }else {
                alert("登录异常");
            }
        },
        error:function(e){
            alert(e);
        }
    });
}