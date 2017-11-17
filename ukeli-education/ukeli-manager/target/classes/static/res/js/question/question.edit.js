$(document).ready(function () {
    $('#questionForm .i-checks').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue'
    });
    $('#questionForm').validate({
        ignore: "",
        rules:{
        	answer:{required: true}
        },
        messages:{
        	answer:{required:'回复内容不能为空'}
        },
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                success: Eymjs.form.saveComplate
            });
        }
    });
});