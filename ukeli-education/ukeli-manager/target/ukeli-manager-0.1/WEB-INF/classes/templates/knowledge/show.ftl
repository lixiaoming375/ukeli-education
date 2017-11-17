<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-2"></div>
        <form class="form-horizontal"  id="knowledgeForm" method="post" action="${adminPath}/knowledge/save.json">
             
	             <div class="row">
	                <div class="col-sm-12">
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="stype"> 知识点类型: </label>
	                        <div class="col-sm-10">
	                           <select name="typeid"  id="typeid"   style="width: 400px;vertical-align:middle;">
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
                        <label class="col-sm-2 control-label no-padding-right" for="stype"> 年级: </label>
                        <div class="col-sm-10">
                            <select name="gradeid"  id="gradeid"  data-placeholder="所属年级" style="width: 400px;vertical-align:middle;">
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
                                <input name="nodename"  id="nodename" class="col-sm-11" readonly value="${knowledge.nodename!}" style="width:400px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;"/>
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
                                <input name="videopath"  id="videopath" class="col-sm-11" readonly value="${knowledge.videopath!}" style="width:400px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
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

            <input name="id" id="id" type="hidden" value="${knowledge.id}" />
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
