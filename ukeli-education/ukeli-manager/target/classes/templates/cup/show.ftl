<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-2"></div>
        <form class="form-horizontal"  id="cupForm" method="post" action="${adminPath}/cup/save.json">

            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="stype"> 年级: </label>
                        <div class="col-sm-10">
                            <select name="gradeid"  id="gradeid"  data-placeholder="所属年级"  style="width: 300px;vertical-align:middle;">
			                        <option value='' >----请选择----</option>
			                        <#if clazzDoList ??>
										<#list clazzDoList as item>
				                            <option value='${item.id}'  <#if cup.gradeid ?? && cup.gradeid==item.id >selected="selected"</#if> >${item.name}</option>
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
                        <label class="col-sm-2 control-label no-padding-right" for="stype"> 杯赛类型: </label>
                        <div class="col-sm-10">
                            <select name="cuptypeid"  id="cuptypeid"  data-placeholder="杯赛类型" style="width: 300px;vertical-align:middle;" aria-invalid="false" class="help-block m-b-none" >
			                        <option value='' >----请选择----</option>
			                        <#if clazzDoListByClazzTypeid ??>
										<#list clazzDoListByClazzTypeid as item>
				                            <option value='${item.id}'  <#if cup.cuptypeid ?? && cup.cuptypeid==item.id >selected="selected"</#if> >${item.name}</option>
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
                        <label class="col-sm-2 control-label no-padding-right" for="name"> 杯赛名称: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <input name="name"  id="name" class="col-sm-11" value="${cup.name!}" readonly style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
           
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="times"> 杯赛时长: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <input name="times"  id="times" class="col-sm-11" value="${cup.times!}" readonly style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
             <div class="space-2"></div>
               <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="times"> 分数: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <input name="score"  id="score" class="col-sm-11" value="${cup.score!}" readonly style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
              <div class="space-2"></div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="remark"> 杯赛介绍: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                           <textarea class="layui-textarea" id="remark" name="remark"   style="display: none">${cup.remark!}
                           </textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
           
             <input name="id" id="id" type="hidden" value="${cup.id!}" />
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
<script src="${adminPath}/res/js/cup/cup.edit.js?ver=${version}"></script>
