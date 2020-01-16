function doLogin() {

    alert(loginName+loginPassword);
    ajaxUtils.get('li', {
        loginName : $("#loginName").val(),
        loginPassword : $("#loginPassword").val()
    }).then(function (data) {
        
    }).catch(function (reason) {
        alertErrorMsg(reason);
    }).finally(function () {
    });
}