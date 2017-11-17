<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-4"></div>
        <form class="form-horizontal" id="adminPassForm" method="post" action="${adminPath}/manager/admin/password.json">
            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="username">用户名:</label>
                <div class="col-sm-8">
                    <label style="padding-top: 8px;">${admin.username}</label>
                </div>
            </div>

            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="password">管理员密码：</label>
                <div class="col-sm-8">
                    <input type="password" name="password" id="password" placeholder="必填:管理员密码"  class="col-sm-11" required="">
                </div>
            </div>

            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="password">重复输入：</label>
                <div class="col-sm-8">
                    <input type="password" name="password1" id="password1" placeholder="必填:重复输入密码"  class="col-sm-11 required="">
                </div>
            </div>

            <div class="space-4"></div>

            <div class="clearfix form-actions">
                <div class="center">
                    <input name="id" type="hidden"  value="${admin.id!}">
                    <button class="btn btn-info" type="submit"><i class="ace-icon fa fa-check bigger-110"></i>保存</button>
                    &nbsp; &nbsp; &nbsp;
                    <button class="btn" type="reset"><i class="ace-icon fa fa-undo bigger-110"></i>重置</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    //模态框状态
    $(document).ready(function () {
        $('#adminPassForm').ajaxForm({
            beforeSubmit:checkPasswordForm,
            success: savePassComplete
        });
    });
    //admin表单检查
    function checkPasswordForm() {
        var password = $.trim($("#password").val());
        var password1 = $.trim($("#password1").val());
        if(password == ''){
            layer.alert('密码不能为空', {icon: 5}, function (index) {
                layer.close(index);
                $('#password').focus();
            });
            return false;
        }
        if(password1 == ''){
            layer.alert('重复密码不能为空', {icon: 5}, function (index) {
                layer.close(index);
                $('#password1').focus();
            });
            return false;
        }
        if(password != password1){
            layer.alert('两次输入的密码不一致！', {icon: 5}, function (index) {
                layer.close(index);
                $('#password').val('');
                $('#password1').val('');
                $('#password').focus();
            });
            return false;
        }
    }

    function savePassComplete(data) {
        if (data.success) {
            layer.closeAll();
            Eymjs.toastr.success('恭喜您!修改密码成功');
        } else {
            Eymjs.message.error(data.errorinfo);
            return false;
        }
    }
</script>
