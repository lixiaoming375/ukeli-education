<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-4"></div>
        <form class="form-horizontal" id="roleForm" method="post" action="${adminPath}/manager/role/save.json">
 			<div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1"> 角色名称：  </label>
                <div class="col-sm-8">
                    <div class="clearfix">
                        <input type="text" name="role" class="col-xs-10 col-sm-10" value="${role.role!}">
                    </div>
                </div>
            </div>
            <div class="space-4"></div>


            <div class="clearfix form-actions">
                <div class="center">
                    <input name="id" type="hidden"  value="${role.id!}">
                    <button class="btn btn-sm btn-primary"><i class="ace-icon fa fa-check bigger-110"></i>保存</button>
                    &nbsp; &nbsp; &nbsp;
                    <button class="btn btn-sm btn-inverse" type="reset"><i class="ace-icon fa fa-undo bigger-110"></i>重置</button>
                </div>
            </div>
        </form>

    </div>
</div>
<script>
    //模态框状态
    $(document).ready(function () {
        $('#roleForm').validate({
            rules:{
                role:{required: true}
            },
            messages:{
                role:{required:'角色不能为空'}
            },
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    success: Eymjs.form.saveComplate
                });
            }
        });
    });
</script>