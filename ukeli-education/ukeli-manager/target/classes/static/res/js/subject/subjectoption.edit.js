$(document).ready(function () {
    $('#nextForm .i-checks').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue'
    });
    
    
    $('#nextForm').validate({
        ignore: "",
        rules:{
        },
        messages:{
        	
        },
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                success: Eymjs.form.saveComplate
            });
        }
    });
});