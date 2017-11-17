<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-2"></div>
        <form class="form-horizontal"  id="usersubjectForm" method="post" action="${adminPath}/usersubject/save.json">
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 用户编号: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="userid"  id="userid" class="col-sm-11" value="${usersubject.userid!}" readonly style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
          <div class="space-2"></div>
           <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username">用户名称: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="userid"  id="userid" class="col-sm-11" value="${user.username!}" readonly style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
             <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="nickname"> 用户昵称: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="nickname"  id="nickname" readonly  class="col-sm-11" value="${user.nickname!}"  readonly style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 题目编号: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="subjectid"  id="subjectid" class="col-sm-11" value="${subject.id!}" readonly style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 题目名称: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="name"  id="name" class="col-sm-11" value="${subject.name!}" readonly style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="content"> 题目介绍: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <textarea class="layui-textarea" id="content" name="content"  style="display: none">${subject.content!}
                                </textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
            <div class="row">
                <div class="col-sm-12">
                     <div class="form-group" id="issku_formgroup">
		                <label class="col-sm-4 control-label no-padding-right" for="sex">是否完成:</label>
		                <div class="col-sm-8">
		                    <label style="padding-top: 5px">
							    <#if (usersubject.isdone??) && (usersubject.isdone=='1')>
							      <input name="isdone"  id="isdone" class="ace ace-switch ace-switch-5" value="${usersubject.isdone!}"    type="checkbox"    checked />
							    <#else>
							       <input name="isdone"  id="isdone" class="ace ace-switch ace-switch-5" value="${usersubject.isdone!}"   type="checkbox" />
							    </#if>
		                    </label>
		                </div>
		            </div>
		            <div class="space-4" id="user_formspace"  style="display: none"></div>
                </div>
            </div>
            <div class="space-2"></div>
             <div class="row">
                <div class="col-sm-12">
                     <div class="form-group" id="issku_formgroup">
		                <label class="col-sm-4 control-label no-padding-right" for="sex">是否已加入我的课程:</label>
		                <div class="col-sm-8">
		                    <label style="padding-top: 5px">
							    <#if (usersubject.isadd??) && (usersubject.isadd=='1')>
							      <input name="isadd"  id="isadd" class="ace ace-switch ace-switch-5" value="${usersubject.isadd!}"    type="checkbox"    checked />
							    <#else>
							       <input name="isadd"  id="isadd" class="ace ace-switch ace-switch-5" value="${usersubject.isadd!}"   type="checkbox" />
							    </#if>
		                    </label>
		                </div>
		            </div>
		            <div class="space-4" id="user_formspace"  style="display: none"></div>
                </div>
            </div>
            <div class="space-2"></div>
            

            <div class="clearfix form-actions">
                
            </div>
        </form>

    </div>
</div>
<script>

    //自定义工具栏
    var layedit = layui.layedit;
    
   
    var index = layedit.build('content',{
       height: 160 //设置编辑器高度
     });
     
 $('#isdone').bootstrapSwitch({  
    	onText:"是",  
        offText:"否",  
        onColor:"success",  
        offColor:"info",  
        size:"small",  
        onSwitchChange:function(event,state){  
            if(state==true){  
                $('#isdone').val("1");  
            }else{  
                $('#isdone').val("0");  
            }  
        }  
    });
    
    
     $('#isadd').bootstrapSwitch({  
    	onText:"是",  
        offText:"否",  
        onColor:"success",  
        offColor:"info",  
        size:"small",  
        onSwitchChange:function(event,state){  
            if(state==true){  
                $('#isadd').val("1");  
            }else{  
                $('#isadd').val("0");  
            }  
        }  
    });
</script>


