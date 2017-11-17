<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-2"></div>
        <form class="form-horizontal"  id="subjectForm" method="post" action="${adminPath}/subject/save.json">
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="stype"> 试题类型: </label>
                        <div class="col-sm-10">
                            <select name="stype"  id="stype"  data-placeholder="试题类型" style="width: 300px;vertical-align:middle;">
			                        <option value='' >----请选择----</option>
			                        <#if clazzDoList ??>
										<#list clazzDoList as item>
				                            <option value='${item.id}'  <#if subject.stype ?? && subject.stype==item.id >selected="selected"</#if> >${item.name}</option>
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
                <label class="col-sm-2 control-label no-padding-right" for="form-field-1"><span class="red">*</span>题目类别 </label>
                <div class="col-sm-10">
                    <div class="clearfix">
                    <#if clazzDoVtypeList??>
                        <#list clazzDoVtypeList as vtype>
                            <label>
                                <input  class="i-checks"  id="vtype_${vtype.id}" name="vtype" type="checkbox" value="${vtype.id}">
                                <span class="lbl"> ${vtype.name}</span>
                            </label>
                        </#list>
                    </#if>
                    </div>
                </div>
            </div>
           </div>
          </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="name"> 试题名称: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <input name="name"  id="name" class="col-sm-11" readonly value="${subject.name!}" style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="answer"> 试题答案: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <input name="answer"  id="answer" class="col-sm-11" readonly value="${subject.answer!}" style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
             <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="videopath"> 视频路径: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <input name="videopath"  id="videopath" class="col-sm-11" readonly value="${subject.videopath!}" style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="content"> 试题内容: </label>
                        <div class="col-sm-10">
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
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="username"> 试题解析: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <textarea class="layui-textarea" id="analysis" name="analysis" style="display: none">
                                ${subject.analysis!}
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
                        <label class="col-sm-2 control-label no-padding-right" for="detail"> 备注信息: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                 <textarea  id="detail" class="col-sm-12" name="detail" readonly >${subject.detail!}
                                 </textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="space-2"></div>

            <input name="id" id="id" type="hidden" value="${subject.id}" />
        </form>

    </div>
</div>
<script>
    //自定义工具栏
    var layedit = layui.layedit;
    
    layedit.set({
	  uploadImage: {
	    url: '${adminPath}/subject/uploadImag.json' //接口url
	    ,type: 'post' //默认post
	  }
	});
    var index = layedit.build('content',{
       height: 140 //设置编辑器高度
     });
     
     var index2 = layedit.build('analysis',{
       height: 140 //设置编辑器高度
     });
     
      $(document).ready(function () {  
	    <#if vtypes??>
	    <#list vtypes as vtype>
	    $("#vtype_${vtype}").iCheck('check');
		 </#list>
		</#if>
      });
</script>

<script src="${adminPath}/res/js/subject/subject.edit.js?ver=${version}"></script>
