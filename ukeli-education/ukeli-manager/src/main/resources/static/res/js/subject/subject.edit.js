$(document).ready(function () {
    $('#subjectForm .i-checks').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue'
    });
    
    
    $('#subjectForm').validate({
        ignore: "",
        rules:{
        	stype:{required: true},
        	content:{required: true},
        	analysis:{required: true},
        	name:{required: true},
        },
        messages:{
        	stype:{required:'试题类型不能为空'},
        	content:{required:'试题内容不能为空'},
        	analysis:{required: '试题解析不能为空'},
        	name:{required: '试题名称不能为空'},
        },
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                success: Eymjs.form.saveComplateNext
            });
        }
    });
});