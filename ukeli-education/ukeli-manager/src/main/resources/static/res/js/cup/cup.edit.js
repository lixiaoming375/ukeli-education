$(document).ready(function () {
    $('#cupForm .i-checks').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue'
    });
    
    
    // 数字号码验证
    jQuery.validator.addMethod("isnumber", function(value, element) {
         var r = /^\+?[1-9][0-9]*$/;　　//正整数 
        return this.optional(element)| r.test(value);
    }, "请输入数字格式。");
    
    $('#cupForm').validate({
        ignore: "",
        rules:{
        	gradeid:{required: true},
        	name:{required: true},
        	times:{required: true,isnumber:true},
        	score:{required: true,isnumber:true},
    
        },
        messages:{
        	gradeid:{required:'年级不能为空'},
        	name:{required:'杯赛名称不能为空'},
        	times:{required:'杯赛时长不能为空'},
        	score:{required: "分数不能为空"}
        	
        },
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                success: Eymjs.form.saveComplate
            });
        }
    });
});