<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-2"></div>
        <form class="form-horizontal"  id="knowledgetypeForm" method="post" action="${adminPath}/knowledge/knowledgetype/save.json">
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 知识点类型名称: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="typename"  id="typename" class="col-sm-11" value="${knowledgetype.typename!}"/>
                            </div>
                        </div>
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
                
                <input type="hidden" id="id" name="id" value="${knowledgetype.id!}"/>
            </div>
        </form>

    </div>
</div>

<script src="${adminPath}/res/js/knowledge/knowledgetype.edit.js?ver=${version}"></script>
