function doLogin() {
   /* $.ajax({
        url:"login/doLogin.json",
        type:"get",
        data:{
            loginName : $("#loginName").val(),
            loginPassword : $("#loginPassword").val()
        },
        //dataType:"text",
        success:function(data){
            window.location.href="systemHome/getView.htm";   //跳转到系统主页面
        },
        error:function(e){
            alert(e);
        }
    });*/

    ajaxUtils.get('login/doLogin.json', {
        loginName : $("#loginName").val(),
        loginPassword : $("#loginPassword").val()
    }).then(function (data) {
        if (!data){
            alert("用户名或密码错误");
        } else {
            window.location.href="systemHome/getView.htm";   //跳转到系统主页面
        }

    }).catch(function (reason) {
        alert(reason);
    }).finally(function () {
    });
}