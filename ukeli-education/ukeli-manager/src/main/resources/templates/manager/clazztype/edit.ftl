<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-6"></div>
        <form class="form-horizontal"  id="clazztypeForm" method="post" action="${adminPath}/manager/clazztype/save.json">
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 名称: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input type="text" name="name"  id="name" class="col-sm-11" value="${clazztype.name!}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>

            <div class="clearfix form-actions">
                <div class="center">
                    <input name="id" type="hidden" value="${clazztype.id!}">
                    <button class="btn btn-sm btn-primary"><i class="ace-icon fa fa-check bigger-110"></i>保存</button>
                    &nbsp; &nbsp; &nbsp;
                    <button class="btn btn-sm btn-inverse" type="reset"><i class="ace-icon fa fa-undo bigger-110"></i>重置</button>
                </div>
            </div>
        </form>

    </div>
</div>

<script src="${adminPath}/res/js/manager/clazztype.edit.js?ver=${version}"></script>
