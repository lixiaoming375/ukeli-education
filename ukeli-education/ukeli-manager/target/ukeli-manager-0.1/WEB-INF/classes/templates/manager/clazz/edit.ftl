<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-6"></div>
        <form class="form-horizontal"  id="clazzForm" method="post" action="${adminPath}/manager/clazz/save.json">
            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="username"> 名称: </label>
                <div class="col-sm-8">
                    <div class="clearfix">
                        <input type="text" name="name"  id="name" class="col-sm-11" value="${clazz.name!}"/>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="username"> 唯一标识: </label>
                <div class="col-sm-8">
                    <div class="clearfix">
                        <input type="text" name="ident"  id="ident" class="col-sm-11" value="${clazz.ident!}"/>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>

            <div class="clearfix form-actions">
                <div class="col-md-offset-4 col-md-8">
                    <button class="btn btn-info" type="submit"><i class="ace-icon fa fa-check bigger-110"></i>保存</button>
                    &nbsp; &nbsp; &nbsp;
                    <button class="btn" type="reset"><i class="ace-icon fa fa-undo bigger-110"></i>重置</button>
                </div>
            </div>
            <input name="id" type="hidden" value="${clazz.id!}">
            <input name="clazztypeid" type="hidden" value="${clazz.clazztypeid!}">
            <input name="parentid" type="hidden" value="${clazz.parentid!}">
        </form>

    </div>
</div>

<script src="${adminPath}/res/js/manager/clazz.edit.js?ver=${version}"></script>
