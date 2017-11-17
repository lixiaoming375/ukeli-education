$(document).ready(function () {
    $('#examForm .i-checks').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue'
    });
    
    
    // 数字号码验证
    jQuery.validator.addMethod("isnumber", function(value, element) {
         var r = /^\+?[1-9][0-9]*$/;　　//正整数 
        return this.optional(element)| r.test(value.replace(',', '').trim());
    }, "请正确填写价格格式。");
	
    
    
    $('#examForm').validate({
        ignore: "",
        rules:{
        	gradeid:{required: true},
        	name:{required: true},
        	times:{required: true,isnumber:true}
        },
        messages:{
        	gradeid:{required:'所属年级不能为空'},
        	name:{required:'姓名不能为空'},
        	times:{required:'考试时长不能为空'}
        },
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                success: Eymjs.form.saveComplate
            });
        }
    });
});