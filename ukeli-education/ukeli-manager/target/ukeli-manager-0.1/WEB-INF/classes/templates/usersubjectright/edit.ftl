<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-2"></div>
        <form class="form-horizontal"  id="usersubjectrightForm" method="post" action="${adminPath}/usersubjectright/save.json">
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 主键: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="id"  id="id" class="col-sm-11" value="${usersubjectright.id!}"/>
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
                                <input name="userid"  id="userid" class="col-sm-11" value="${usersubjectright.userid!}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 例题编号: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="subjectid"  id="subjectid" class="col-sm-11" value="${usersubjectright.subjectid!}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 题目类别: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="vtype"  id="vtype" class="col-sm-11" value="${usersubjectright.vtype!}"/>
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
                                <input name="isanswer"  id="isanswer" class="col-sm-11" value="${usersubjectright.isanswer!}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 是否错误: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="iswrong"  id="iswrong" class="col-sm-11" value="${usersubjectright.iswrong!}"/>
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

<script src="${adminPath}/res/js/usersubjectright.edit.js?ver=${version}"></script>
