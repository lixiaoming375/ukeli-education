/**
 * 首页登录
 */

function doLogin() {
    var username = $("#username").val();
    var password = $("#password").val();
    if(username==''){
        Eymjs.message.error("对不起，请输入用户名！");
        return
    }
    if(password==''){
        Eymjs.message.error("对不起，请输入密码！");
        return
    }

    Eymjs.data.ajax(Eymjs.url.user.login,{username:username,password:password},function (data) {
        if(data.success){
            window.location.href=webPath +"index.html";
            return;
        }else{
            layer.alert(data.errorinfo, {icon: 5});
        }
    });

}