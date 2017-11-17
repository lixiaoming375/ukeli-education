$(document).ready(function () {
    $('#videoForm .i-checks').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue'
    });
    $('#videoForm').validate({
        ignore: "",
        rules:{
            //username:{required: true, minlength:4, maxlength:30}
        },
        messages:{
            //username:{required:'登录用户名不能为空', minlength:'登录用户名长度不能小于6位', maxlength:'登录用户名长度不能大于30位'}
        },
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                success: Eymjs.form.saveComplate
            });
        }
    });
});