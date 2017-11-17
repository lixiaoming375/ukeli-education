$(document).ready(function () {
    $('#subjectForm .i-checks').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue'
    });
    
    
    $('#subjectForm').validate({
        ignore: "",
        rules:{
        	stype:{required: true},
        	videopath:{required: true},
        	content:{required: true},
        	analysis:{required: true},
        	name:{required: true},
        	answer:{required: true}
        },
        messages:{
        	stype:{required:'试题类型不能为空'},
        	videopath:{required:'视频路径不能为空'},
        	content:{required:'试题内容不能为空'},
        	analysis:{required: '试题解析不能为空'},
        	name:{required: '试题名称不能为空'},
        	answer:{required: '试题答案不能为空'}
        },
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                success: Eymjs.form.saveComplate
            });
        }
    });
});