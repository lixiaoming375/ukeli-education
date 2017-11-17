$(document).ready(function () {
    $('#userForm .i-checks').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue'
    });
    
    $('#sex').bootstrapSwitch({  
    	onText:"女",  
        offText:"男",  
        onColor:"success",  
        offColor:"info",  
        size:"small",  
        onSwitchChange:function(event,state){  
            if(state==true){  
                $('#sex').val("1");  
            }else{  
                $('#sex').val("2");  
            }  
        }  
    })  
    
    $('#isvip').bootstrapSwitch({  
    	onText:"是",  
        offText:"否",  
        onColor:"success",  
        offColor:"info",  
        size:"small",  
        onSwitchChange:function(event,state){  
            if(state==true){  
                $('#isvip').val("1");  
            }else{  
                $('#isvip').val("0");  
            }  
        }  
    })  
    
    
    // 手机号码验证
    jQuery.validator.addMethod("isPhone", function(value, element) {
        var length = value.length;
        return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(value));
    }, "请正确填写您的用户名格式。");
    
    
 // 手机号码验证
    jQuery.validator.addMethod("isEmail", function(value, element) {
        var length = value.length;
        return this.optional(element) || (/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(value));
    }, "请正确填写邮箱地址。");
    
    $('#userForm').validate({
        ignore: "",
        rules:{
        	username:{required: true,isPhone:true},
        	password:{required: true, minlength:6,maxlength:30},
        	nickname:{required: true},
        	realname:{required: true},
        	bidthday:{required: true},
        	telephone:{required: true},
        	mobile:{required: true,isPhone:true},
        	email:{required: true,isEmail:true},
        	address:{required: true},
        	detail:{required: true}
        },
        messages:{
        	username:{required:'用户名不能为空'},
        	password:{required:'登录密码不能为空', minlength:'登录密码长度不能小于6位', maxlength:'登录密码长度不能大于30位'},
        	nickname:{required:'昵称不能为空'},
        	realname:{required:'真实姓名不能为空'},
        	bidthday:{required:'出生年月不能为空'},
        	telephone:{required:'联系电话不能为空'},
        	mobile:{required:' 手机号码不能为空'},
        	email:{required:'电子邮件不能为空'},
        	address:{required:'地址信息不能为空'},
        	detail:{required:'备注不能为空'}
        },
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                success: Eymjs.form.saveComplate
            });
        }
    });
});