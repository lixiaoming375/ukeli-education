$(document).ready(function () {
	
	
	// 手机号码验证
    jQuery.validator.addMethod("isPhone", function(value, element) {
        var length = value.length;
        return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(value));
    }, "请正确填写您的手机号码。");
 
    // 电话号码验证
    jQuery.validator.addMethod("isTel", function(value, element) {
        var tel = /^(\d{3,4}-)?\d{7,8}$/g; // 区号－3、4位 号码－7、8位
        return this.optional(element) || (tel.test(value));
    }, "请正确填写您的电话号码。");
    // 匹配密码，以字母开头，长度在6-12之间，必须包含数字和特殊字符。
    jQuery.validator.addMethod("isPwd", function(value, element) {
        var str = value;
        if (str.length < 6 || str.length > 18)
            return false;
        if (!/^[a-zA-Z]/.test(str))
            return false;
        if (!/[0-9]/.test(str))
            return fasle;
        return this.optional(element) || /[^A-Za-z0-9]/.test(str);
    }, "以字母开头，长度在6-12之间，必须包含数字和特殊字符。");
	
   // 数字号码验证
    jQuery.validator.addMethod("isnumber", function(value, element) {
         var r = /^\+?[1-9][0-9]*$/;　　//正整数 
    	//var r=/^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
    	//var r=/(^[1-9]([0-9]+)?(\,[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\,[0-9]([0-9])?$)/;
        return this.optional(element)| r.test(value.replace(',', '').trim());
    }, "请正确填写价格格式。");
    
	
    $('#productForm .i-checks').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue'
    });
    $('#productForm').validate({
        ignore: "",
        rules:{
        	productname:{required: true},
        	producttype:{required: true},
        	ident:{required: true},
        	marketprice:{required: true,isnumber:true},
        	price:{required: true,isnumber:true},
        	vipprice:{required: true,isnumber:true},
        	datetype:{required: true,isnumber:true},
        	datecount:{required: true,isnumber:true}
            
        },
        messages:{
        	productname:{required:'产品名称不能为空'},
        	producttype:{required: '产品类型不能为空'},
        	ident:{required:'唯一标识不能为空'},
        	marketprice:{required: '市场价不能为空'},
        	price:{required: '商城价不能为空'},
        	vipprice:{required: ' VIP会员价不能为空'},
        	datetype:{required: '日期类型不能为空'},
        	datecount:{required: ' 日期数量不能为空'}
        	
        },
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                success: Eymjs.form.saveComplate
            });
        }
    });
});