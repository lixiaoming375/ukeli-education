<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-2"></div>
        <form class="form-horizontal"  id="userknowledgeForm" method="post" action="${adminPath}/userknowledge/save.json">
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 主键: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="id"  id="id" class="col-sm-11" value="${userknowledge.id!}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 用户编号: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="userid"  id="userid" class="col-sm-11" value="${userknowledge.userid!}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 知识点编号: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="knowledgeid"  id="knowledgeid" class="col-sm-11" value="${userknowledge.knowledgeid!}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 是否答题: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="isdown"  id="isdown" class="col-sm-11" value="${userknowledge.isdown!}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>

            <div class="clearfix form-actions">
                <div class="col-md-offset-4 col-md-8">
                    <button class="btn btn-info" type="submit"><i class="ace-icon fa fa-check bigger-110"></i>保存zw</button>
                    &nbsp; &nbsp; &nbsp;
                    <button class="btn" type="reset"><i class="ace-icon fa fa-undo bigger-110"></i>重置</button>
                </div>
            </div>
        </form>

    </div>
</div>

<script src="${adminPath}/res/js/userknowledge.edit.js?ver=${version}"></script>
