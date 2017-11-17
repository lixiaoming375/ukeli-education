<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-2"></div>
        <form class="form-horizontal"  id="knowledgeForm" method="post" action="${adminPath}/knowledge/save.json">
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="typeid"> 知识点类型: </label>
                        <div class="col-sm-10">
								<select name="typeid"  id="typeid"   style="width: 400px;vertical-align:middle;" aria-invalid="false" class="help-block m-b-none" >
			                        <option value='' >----请选择----</option>
			                        <#if knowledgeClazzDoList ??>
										<#list knowledgeClazzDoList as item>
				                            <option value='${item.id}'  <#if knowledge.typeid ?? && knowledge.typeid==item.id >selected="selected"</#if> >${item.name}</option>
										</#list>
									</#if>
								</select>	
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 		    <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="gradeid"> 年级: </label>
                        <div class="col-sm-10">
                            <select name="gradeid"  id="gradeid"   style="width: 400px;vertical-align:middle;" aria-invalid="false" class="help-block m-b-none" >
			                        <option value='' >----请选择----</option>
			                        <#if clazzDoList ??>
										<#list clazzDoList as item>
				                            <option value='${item.id}'  <#if knowledge.gradeid ?? && knowledge.gradeid==item.id >selected="selected"</#if> >${item.name}</option>
										</#list>
									</#if>
						   </select>	
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="知识点名称"> 知识点名称: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <input name="nodename" type="text"  id="nodename" class="col-sm-11" value="${knowledge.nodename!}" style="width:400px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="视频路径"> 视频路径: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <input name="videopath"  type="text" id="videopath" class="col-sm-11" value="${knowledge.videopath!}" style="width:400px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
            
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="remark"> 知识点介绍: </label>
                        <div class="col-sm-10">
                           <div class="clearfix">
                           <textarea class="layui-textarea" id="remark" name="remark"  style="display: none">
                            ${knowledge.remark!}
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
                        <label class="col-sm-2 control-label no-padding-right" for="videodetail"> 视频描述: </label>
                        <div class="col-sm-10">
                           <div class="clearfix">
                           <textarea class="layui-textarea" id="videodetail" name="videodetail"  style="display: none">
                           ${knowledge.videodetail!}
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
            
            <input name="id" type="hidden" value="${knowledge.id!}">
        </form>

    </div>
</div>


<script>
    //自定义工具栏
    var layedit = layui.layedit;
    var index = layedit.build('remark',{
       height: 140 //设置编辑器高度
     });
     
       
     var index2 = layedit.build('videodetail',{
       height: 140 //设置编辑器高度
     });
</script>
<script src="${adminPath}/res/js/knowledge/knowledge.edit.js?ver=${version}"></script>
