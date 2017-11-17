$(document).ready(function () {
    $('#knowledgeForm .i-checks').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue'
    });
    
   // $('#knowledgeForm :first').hide();
    
    // 数字验证
    jQuery.validator.addMethod("isnumber", function(value, element) {
         var r = /^\+?[1-9][0-9]*$/;　　//正整数 
        return this.optional(element)| r.test(value);
    }, "请输入数字格式。");
    
    $('#knowledgeForm').validate({
        ignore: "",
        rules:{
        	typeid:{required:true},
        	gradeid:{required:true},
        	nodename:{required:true},
        	remark:{required:true}
        },
        messages:{
            //username:{required:'登录用户名不能为空', minlength:'登录用户名长度不能小于6位', maxlength:'登录用户名长度不能大于30位'}
        	typeid:{required:" 知识点类型不能为空"},
        	gradeid:{required:"年级不能为空"},
        	nodename:{required:"知识点名称不能为空"},
        	remark:{required:"知识点介绍不能为空"}
        },
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                success: Eymjs.form.saveComplate
            });
        }
    });
});