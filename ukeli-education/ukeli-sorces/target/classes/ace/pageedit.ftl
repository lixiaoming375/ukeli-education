<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-2"></div>
        <form class="form-horizontal"  id="${pojo?lower_case}Form" method="post" action="${r'${adminPath}'}<#if model??>/${model}</#if>/${pojo?lower_case}/save.json">
 			<#list editlist as column>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> ${column.desp}: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="${column.name?lower_case}"  id="${column.name?lower_case}" class="col-sm-11" value="${r'${'}${pojo?lower_case}.${column.name?lower_case}${r'!}'}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
            </#list> 

            <div class="clearfix form-actions">
                <div class="col-md-offset-4 col-md-8">
                    <button class="btn btn-info" type="submit"><i class="ace-icon fa fa-check bigger-110"></i>保存zw</button>
                    &nbsp; &nbsp; &nbsp;
                    <button class="btn" type="reset"><i class="ace-icon fa fa-undo bigger-110"></i>重置</button>
                </div>
            </div>
            <#list hiddenlist as column>
             	<input name="${column.name?lower_case}" type="hidden" value="${r'${'}${pojo?lower_case}.${column.name?lower_case}${r'!}'}">
		   	</#list>
        </form>

    </div>
</div>

<script src="${r'${adminPath}'}/res/js/<#if model??>${model}/</#if>${pojo?lower_case}.edit.js?ver=${r'${version}'}"></script>
