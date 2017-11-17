<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-2"></div>
        <form class="form-horizontal"  id="userexamForm" method="post" action="${adminPath}/userexam/save.json">
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 主键: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="id"  id="id" class="col-sm-11" value="${userexam.id!}"/>
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
                                <input name="userid"  id="userid" class="col-sm-11" value="${userexam.userid!}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 考试编号: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="examid"  id="examid" class="col-sm-11" value="${userexam.examid!}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 使用时间: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="usetime"  id="usetime" class="col-sm-11" value="${userexam.usetime!}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 得分情况: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="score"  id="score" class="col-sm-11" value="${userexam.score!}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 考试次数: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="vtime"  id="vtime" class="col-sm-11" value="${userexam.vtime!}"/>
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

<script src="${adminPath}/res/js/userexam.edit.js?ver=${version}"></script>
