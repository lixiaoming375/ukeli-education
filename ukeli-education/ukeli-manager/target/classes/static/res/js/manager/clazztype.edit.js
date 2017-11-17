$(document).ready(function () {
    $('#clazztypeForm').validate({
        ignore: "",
        rules:{
            name:{required: true}
        },
        messages:{
            name:{required:'分类类别名称不能为空'}
        },
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                success: Eymjs.form.saveComplate
            });
        }
    });
});