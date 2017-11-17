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
        return this.optional(element)| r.test(value.replace(',', '').trim());
    }, "请正确填写价格格式。");
	
    $('#orderForm .i-checks').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue'
    });
    $('#orderForm').validate({
        ignore: "",
        rules:{
        	ordernumber:{required: true},
        	username:{required: true,isPhone:true},
        	nickname:{required: true},
        	productname:{required: true},
        	totalmarketprice:{required: true,isnumber:true},
        	totalprice:{required: true,isnumber:true},
        	payprice:{required: true,isnumber:true},
        	ordertime:{required: true},
        	amount:{required: true,isnumber:true}
        },
        messages:{
        	ordernumber:{required:'订单号不能为空'},
        	username:{required: '用户名不能为空'},
        	nickname:{required:'用户昵称不能为空'},
        	productname:{required: '产品名称不能为空'},
        	totalmarketprice:{required: '总市场价不能为空'},
        	totalprice:{required: ' 总价不能为空'},
        	payprice:{required: '支付价格不能为空'},
        	ordertime:{required: ' 下单时间不能为空'},
        	amount:{required: '购买数量不能为空'}
        },
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                success: Eymjs.form.saveComplate
            });
        }
    });
});