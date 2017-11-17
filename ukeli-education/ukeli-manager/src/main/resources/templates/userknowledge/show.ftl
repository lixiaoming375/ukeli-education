<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-2"></div>
        <form class="form-horizontal"  id="userknowledgeForm" method="post" action="${adminPath}/userknowledge/save.json">
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 用户编号: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="userid"  id="userid" class="col-sm-11" value="${userknowledge.userid!}" readonly style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
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
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 知识点编号: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="knowledgeid"  id="knowledgeid" class="col-sm-11" value="${userknowledge.knowledgeid!}" readonly style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 知识点名称: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="knowledgeid"  id="knowledgeid" class="col-sm-11" value="${knowledge.nodename!}" readonly style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="content"> 知识点介绍: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <textarea class="layui-textarea" id="remark" name="remark"  style="display: none">${knowledge.remark!}
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
							    <#if (userknowledge.isdone??) && (userknowledge.isdone=='1')>
							      <input name="isdone"  id="isdone" class="ace ace-switch ace-switch-5" value="${userknowledge.isdone!}"    type="checkbox"    checked />
							    <#else>
							       <input name="isdone"  id="isdone" class="ace ace-switch ace-switch-5" value="${userknowledge.isdone!}"   type="checkbox" />
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
							    <#if (userknowledge.isadd??) && (userknowledge.isadd=='1')>
							      <input name="isadd"  id="isadd" class="ace ace-switch ace-switch-5" value="${userknowledge.isadd!}"    type="checkbox"    checked />
							    <#else>
							       <input name="isadd"  id="isadd" class="ace ace-switch ace-switch-5" value="${userknowledge.isadd!}"   type="checkbox" />
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
    
   
    var index = layedit.build('remark',{
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


