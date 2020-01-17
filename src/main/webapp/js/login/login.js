function doLogin() {
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
                window.location.href="systemHome/getView.htm";   //跳转到系统主页面
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