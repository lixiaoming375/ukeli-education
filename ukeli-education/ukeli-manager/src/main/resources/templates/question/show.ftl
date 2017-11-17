<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-2"></div>
       <form class="form-horizontal"  id="questionForm" method="post" action="${adminPath}/question/save.json">
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="nickname"> 用户昵称: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <input name="nickname"  id="nickname" readonly class="col-sm-11" value="${user.nickname!}" style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="addtime"> 留言时间: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <input name="addtime"  id="addtime"  readonly class="form_datetime form-control" type="text" value="${question.addtime!}"  style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
           <div class="space-2"></div>
            <div class="row">
                <div class="col-sm-12">
                     <div class="form-group" id="isanswer_formgroup">
		                <label class="col-sm-2 control-label no-padding-right" for="isanswer">是否回答:</label>
		                <div class="col-sm-10">
		                    <label style="padding-top: 5px">
							    <#if (question.isanswer??) && (question.isanswer=='1')>
							      <input name="isanswer"  id="isanswer" class="ace ace-switch ace-switch-5" value="${question.isanswer!}"    type="checkbox"    checked />
							    <#else>
							       <input name="isanswer"  id="isanswer" class="ace ace-switch ace-switch-5" value="${question.isanswer!}"   type="checkbox" />
							    </#if>
		                    </label>
		                </div>
		            </div>
		            <div class="space-4" id="question_formspace"  style="display: none"></div>
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
                        <label class="col-sm-2 control-label no-padding-right" for="vipstarttime">回复时间: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <input name="answer_time"  id="answer_time" class="form_datetime form-control" type="text" value="${question.answer_time!}" readonly style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
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
            <input name="id" id="id" type="hidden" value="${question.id !}" />
        </form>


    </div>
</div>


<script>

    //自定义工具栏
    var layedit = layui.layedit;
    var index = layedit.build('question',{
       height: 140 //设置编辑器高度
     });
     
       
     var index2 = layedit.build('answer',{
       height: 140 //设置编辑器高度
     });

$(document).ready(function () {
    $('#userForm .i-checks').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue'
    });
    
    $('#isanswer').bootstrapSwitch({  
    	onText:"是",  
        offText:"否",  
        onColor:"success",  
        offColor:"info",  
        size:"small",  
        onSwitchChange:function(event,state){  
            if(state==true){  
                $('#isanswer').val("0");  
            }else{  
                $('#isanswer').val("1");  
            }  
        }  
    })  
    
  })     
    

</script>


