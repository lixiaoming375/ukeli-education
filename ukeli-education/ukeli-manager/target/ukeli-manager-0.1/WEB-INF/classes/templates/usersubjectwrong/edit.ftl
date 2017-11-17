<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-2"></div>
        <form class="form-horizontal"  id="usersubjectwrongForm" method="post" action="${adminPath}/usersubjectwrong/save.json">
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 主键: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="id"  id="id" class="col-sm-11" value="${usersubjectwrong.id!}"/>
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
                                <input name="userid"  id="userid" class="col-sm-11" value="${usersubjectwrong.userid!}"/>
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
                                <input name="subjectid"  id="subjectid" class="col-sm-11" value="${usersubjectwrong.subjectid!}"/>
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
                                <input name="vtype"  id="vtype" class="col-sm-11" value="${usersubjectwrong.vtype!}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 是否修改: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="ismod"  id="ismod" class="col-sm-11" value="${usersubjectwrong.ismod!}"/>
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

<script src="${adminPath}/res/js/usersubjectwrong.edit.js?ver=${version}"></script>
