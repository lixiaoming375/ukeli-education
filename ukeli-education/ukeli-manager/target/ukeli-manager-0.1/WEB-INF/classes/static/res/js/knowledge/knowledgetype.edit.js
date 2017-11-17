$(document).ready(function () {
    $('#knowledgetypeForm .i-checks').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue'
    });
    $('#knowledgetypeForm').validate({
        ignore: "",
        rules:{
        	typename:{required: true}
        },
        messages:{
        	typename:{required:'知识点类型不能为空'}
        },
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                success: Eymjs.form.saveComplate
            });
        }
    });
});