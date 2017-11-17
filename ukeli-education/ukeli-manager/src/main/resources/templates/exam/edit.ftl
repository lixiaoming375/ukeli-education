<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-2"></div>
        <form class="form-horizontal"  id="examForm" method="post" action="${adminPath}/exam/save.json">

            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="stype"> 年级: </label>
                        <div class="col-sm-10">
                            <select name="gradeid"  id="gradeid"  data-placeholder="所属年级" style="width: 300px;vertical-align:middle;" aria-invalid="false" class="help-block m-b-none" >
			                        <option value='' >----请选择----</option>
			                        <#if clazzDoList ??>
										<#list clazzDoList as item>
				                            <option value='${item.id}'  <#if exam.gradeid ?? && exam.gradeid==item.id >selected="selected"</#if> >${item.name}</option>
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
                        <label class="col-sm-2 control-label no-padding-right" for="name"> 考试名称: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <input name="name"  id="name"  type="text" class="col-sm-11" value="${exam.name!}" style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
           
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="times"> 考试时长: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <input name="times"  id="times" type="text" class="col-sm-11" value="${exam.times!}"  placeholder="单位分钟" style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
            
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="remark"> 考试介绍: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                           <textarea class="layui-textarea" id="remark" name="remark"  style="display: none">${exam.remark!}</textarea>
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
            
             <input name="id" id="id" type="hidden" value="${exam.id!}" />
        </form>

    </div>
</div>

<script>
    //自定义工具栏
    var layedit = layui.layedit;
    var index = layedit.build('remark',{
       height: 140 //设置编辑器高度
     }); 
</script>
<script src="${adminPath}/res/js/exam/exam.edit.js?ver=${version}"></script>
