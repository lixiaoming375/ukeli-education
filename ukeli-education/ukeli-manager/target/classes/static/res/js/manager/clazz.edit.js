$(document).ready(function () {
    $('#clazzForm').validate({
        ignore: "",
        rules:{
            name:{required: true},
            ident:{required: true}
        },
        messages:{
            name:{required:'分类名称不能为空'},
            ident:{required:'唯一标示不能为空'}
        },
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                success: function (data) {
                    if(data.success){
                        layer.closeAll();
                        initClazzTree(data.input);
                    }else{
                        Eymjs.message.error(data.errorinfo);
                        return false;
                    }
                }
            });
        }
    });
});