<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-4"></div>
        <form class="form-horizontal" id="adminForm" method="post" action="${adminPath}/manager/admin/save.json">
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="form-field-1"><span class="red">*</span>隶属角色： </label>
                <div class="col-sm-10">
                    <div class="clearfix">
                    <#if rolelist??>
                        <#list rolelist as role>
                            <label>
                                <input  class="i-checks"  id="roleid_${role.id}" name="roleid" type="checkbox" value="${role.id}">
                                <span class="lbl"> ${role.role}</span>
                            </label>
                        </#list>
                    </#if>
                    </div>
                </div>
            </div>
            <div class="space-4"></div>
            <#if edit>
                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-right" for="username"><span class="red">*</span>用户名:</label>
                    <div class="col-sm-10">
                        <div class="clearfix">
                            <input type="text" name="username" id="username" placeholder="必填:用户名" class="col-sm-11" value="${admin.username!}" required="">
                        </div>
                    </div>
                </div>
            <#else>
                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-right" for="username"><span class="red">*</span>用户名:</label>
                    <div class="col-sm-4">
                        <div class="clearfix">
                            <input type="text" name="username" id="username" placeholder="必填:用户名" class="col-sm-11" value="${admin.username!}" required="">
                        </div>
                    </div>
                    <label class="col-sm-2 control-label no-padding-right" for="password"><span class="red">*</span>密码：</label>
                    <div class="col-sm-4">
                        <div class="clearfix">
                            <input type="password" name="password" id="password" placeholder="必填:密码"  class="col-sm-10" required="">
                        </div>
                    </div>
                </div>
            </#if>

            <div class="space-4"></div>

            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="realname"><span class="red">*</span>真实姓名:</label>
                <div class="col-sm-4">
                    <div class="clearfix">
                        <input type="text" name="realname" id="realname" placeholder="必填:真实姓名" class="col-sm-12" value="${admin.realname!}" required="">
                    </div>
                </div>
                <label class="col-sm-2 control-label no-padding-right" for="sex"><span class="red">*</span>性别：</label>
                <div class="col-sm-4">
                    <div class="clearfix">
                        <label style="padding-top:5px">
                            <input name="sex" type="radio" id="sex_2" class="i-checks" value="2">
                            <span class="lbl">男</span>
                        </label>
                        <label style="padding-top:5px">
                            <input name="sex" type="radio" id="sex_1" class="i-checks"  value="1">
                            <span class="lbl">女</span>
                        </label>
                        <label style="padding-top:5px">
                            <input name="sex" type="radio" id="sex_0"  class="i-checks"  value="0">
                            <span class="lbl">保密</span>
                        </label>
                    </div>
                </div>
            </div>
            <div class="space-4"></div>

            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="telephone">联系电话:</label>
                <div class="col-sm-4">
                    <div class="clearfix">
                        <input type="text"  name="telephone" id="telephone" placeholder="联系电话" class="col-sm-12" value="${admin.telephone!}"/>
                    </div>
                </div>
                <label class="col-sm-2 control-label no-padding-right" for="sex">联系手机：</label>
                <div class="col-sm-4">
                    <div class="clearfix">
                        <input type="text"  name="mobile" id="mobile" placeholder="联系手机" class="col-sm-10" value="${admin.mobile!}">
                    </div>
                </div>
            </div>
            <div class="space-4"></div>

            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="email">电子邮件:</label>
                <div class="col-sm-10">
                    <div class="clearfix">
                        <input type="email" name="email" id="email" placeholder="电子邮件" class="col-sm-11" value="${admin.email!}">
                    </div>
                </div>
            </div>
            <div class="space-4"></div>

            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="telephone">联系QQ:</label>
                <div class="col-sm-4">
                    <div class="clearfix">
                        <input type="text"   name="qq" id="qq" placeholder="联系QQ" class="col-sm-12" value="${admin.qq!}">
                    </div>
                </div>
                <label class="col-sm-2 control-label no-padding-right" for="sex">联系微信：</label>
                <div class="col-sm-4">
                    <div class="clearfix">
                        <input  type="text"  name="wechat" id="wechat" placeholder="联系微信" class="col-sm-10" value="${admin.wechat!}">
                    </div>
                </div>
            </div>
            <div class="space-4"></div>

            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 简介： </label>
                <div class="col-sm-10">
                    <textarea name="detail" id="detail" placeholder="简介" class="col-sm-11">${admin.detail!}</textarea>
                </div>
            </div>
            <div class="space-4"></div>

            <div class="clearfix form-actions">
                <div class="center">
                    <input name="id" type="hidden"  value="${admin.id!}">
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
        $('#adminForm .i-checks').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue'
        });
        <#if edit>
            $("#sex_${admin.sex}").iCheck('check');
            <#if roleids??>
                <#list roleids as roleid>
                    $("#roleid_${roleid}").iCheck('check');
                </#list>
            </#if>
        </#if>
        $('#adminForm').validate({
            ignore: "",
            rules:{
                roleid:{required: true},
                username:{required: true, minlength:4, maxlength:30},
                password:{required: true, minlength:6,maxlength:30},
                realname:{required: true},
                email:{email:true}
            },
            messages:{
                roleid:{required:'请选择角色'},
                username:{required:'登录用户名不能为空', minlength:'登录用户名长度不能小于6位', maxlength:'登录用户名长度不能大于30位'},
                password:{required:'登录密码不能为空', minlength:'登录密码长度不能小于4位', maxlength:'登录密码长度不能大于30位'},
                realname:{required:'真实姓名不能为空'},
                email:{email:"请输入正确的邮件格式"}
            },
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    success: Eymjs.form.saveComplate
                });
            }
        });
    });
</script>
