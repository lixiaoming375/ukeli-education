var login ={
   doLogin :function () {
        var username = $("#username").val();
        var vcode =$("#vcode").val();
        var password =$("#password").val();
        var isrecomend =0;
        if(username == ''){
            login.showMsg('对不起，请输入用户名！')
            $("#username").focus();
            return;
        }
        if(username.length >50){
            login.showMsg('对不起，请您输入正确的用户名！')
            $("#username").focus();
            return;
        }
        if(password == ''){
            login.showMsg('对不起，请您输入密码！')
            $("#password").focus();
            return;
        }
        if(vcode == ''){
            login.showMsg('对不起，请您输入验证码！')
            $("#vcode").focus();
            return;
        }
        if(vcode.length != 4){
            login.showMsg('对不起，请您输入正确的验证码！')
            $("#vcode").focus();
            return;
        }
        if($('#recommend').is(':checked')){
            isrecomend=1;
        }
       login.showMsg('登录中!请稍等',"alert-success");
       Eymjs.data.ajax(adminPath +'/login.json',{username:username,password:password,vcode:vcode,isrecomend:isrecomend},function (data) {
           if(data.success){
               window.location.href=adminPath +"/index.html";
               return;
           }else{
               login.hideMsg();
               $("#vcode").val('');
               $("#gaptchaImg").click();
               layer.alert(data.errorinfo, {icon: 5});
           }
       },true);
   },
    showMsg:function (msg,css) {
        if(msg == ''){return;}
        if(!css){css ="alert-danger"}
        $("#errorInfo").html(msg);
        $("#errorInfo").removeClass().addClass('alert ' +css)
        $("#errorInfo").show();
    },
    hideMsg:function () {
        $("#errorInfo").hide();
    }
};

$(function () {
    $("#doLogin").on('click',login.doLogin);
});