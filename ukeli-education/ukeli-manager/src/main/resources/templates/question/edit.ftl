<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-2"></div>
        <form class="form-horizontal"  id="questionForm" method="post" action="${adminPath}/question/doback.json">
 			
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="username">用户昵称:</label>
		                <div class="col-sm-10">
		                     <div class="clearfix">
                                <input name="name" type="text"  id="name" class="col-sm-11" value="${user.nickname!}" readonly style="width:200px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
		                </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>  
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="question"> 问题内容: </label>
                        <div class="col-sm-10">
                           <div class="clearfix">
                           <textarea class="layui-textarea" id="question" name="question"  style="display: none"  style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;">
                           ${question.question!}
                           </textarea>
                           </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
             <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="answer"> 教师回复: </label>
                        <div class="col-sm-10">
                           <div class="clearfix">
                           <textarea class="layui-textarea" id="answer" name="answer"  style="display: none">
                           ${question.answer!}
                           </textarea>
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
            </div>
            <input name="id" type="hidden" value="${question.id!}">
            <input name="userid" type="hidden" value="${user.id!}">
            
        </form>

    </div>
</div>

<script>
    //自定义工具栏
    var layedit = layui.layedit;
    
    layedit.set({
	  uploadImage: {
	    url: '${adminPath}/uploadfile.html' //接口url
	    ,type: 'post'
	  }
	});
	
    var index = layedit.build('question',{
       height: 140 //设置编辑器高度
     });
     
       
     var index2 = layedit.build('answer',{
       height: 140 //设置编辑器高度
     });
</script>
<script src="${adminPath}/res/js/question/question.edit.js?ver=${version}"></script>
