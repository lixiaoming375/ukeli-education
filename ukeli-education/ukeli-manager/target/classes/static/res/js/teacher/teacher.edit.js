$(document).ready(function () {
    $('#teacherForm .i-checks').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue'
    });
    $('#teacherForm').validate({
        ignore: "",
        rules:{
        	name:{required: true},
        	typename:{required: true},
        	imageurl:{required: true},
        	detail:{required: true}
        	
        },
        messages:{
        	name:{required:'教师名称不能为空'},
        	typename:{required:'科目名称不能为空'},
        	imageurl:{required:'图片地址不能为空'},
        	detail:{required:'教师介绍不能为空'}
        },
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                success: Eymjs.form.saveComplate
            });
        }
    });
});