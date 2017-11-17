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
    
    $('#userForm').validate({
        ignore: "",
        rules:{
        	username:{required: true},
        	password:{required: true},
        	nickname:{required: true},
        	realname:{required: true},
        	bidthday:{required: true},
        	telephone:{required: true},
        	mobile:{required: true},
        	email:{required: true},
        	address:{required: true},
        	detail:{required: true}
        },
        messages:{
        	username:{required:'用户名不能为空'},
        	password:{required:'密码不能为空'},
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