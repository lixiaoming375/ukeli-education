<#macro clazzTree list>
    <#if list??>
        <#list list as clazzvo>
            <#if clazzvo.clazzDo??>
            <tr id="${ clazzvo.clazzDo.id}" pId="${ clazzvo.clazzDo.parentid}">
                <td class="center">${ clazzvo.clazzDo.id!}</td>
                <td>${ clazzvo.clazzDo.name!}</td>
                <td class="center">${ clazzvo.clazzDo.ident!}</td>
                <td  class="center"><#if clazzvo.clazzDo.status==1>
                    <a class="red" href="javascript:;" onclick="onlineChild(${clazzvo.clazzDo.id},0,0)"><button class="btn btn-minier btn-yellow">在线</button></a>
                <#else>
                    <a class="red" href="javascript:;" onclick="onlineChild(${clazzvo.clazzDo.id},1,0)"><button class="btn btn-minier btn-danger">离线</button></a>
                </#if>
                </td>
                <td  class="center">
                    <a href="#" onclick="orderClazz(${clazzvo.clazzDo.id},1);"><i class="blue ace-icon fa fa-arrow-circle-up bigger-200"></i></a>&nbsp;&nbsp;
                    <a href="#" onclick="orderClazz(${clazzvo.clazzDo.id},2);"><i class="blue ace-icon  fa fa-arrow-circle-down bigger-200"></i></a>
                </td>
                <td>
                    <div class="action-buttons">
                        <a class="btn btn-minier btn-success"  href="javascript:show(${clazzvo.clazzDo.id})" title="查看"><i class="ace-icon fa fa-eye"></i>查看</a>
                        <a class="btn btn-minier btn-primary"  href="javascript:editChild(${clazzvo.clazzDo.id})" title="修改"><i class="ace-icon fa fa-pencil"></i>修改</a>
                        <a class="btn btn-minier btn-pink" href="javascript:delClazz(${clazzvo.clazzDo.id},${clazzvo.clazzDo.clazztypeid})" title="删除"><i class="ace-icon fa fa-trash-o"></i>删除</a>
                        <a class="btn btn-minier btn-info" href="javascript:addChild(${clazzvo.clazzDo.id})" title="添加子类"><i class=" fa fa-lock"></i>添加子类</a>
                    </div>
                </td>
            </tr>
            </#if>
            <#if clazzvo.list??>
                <@clazzTree list=clazzvo.list />
            </#if>
        </#list>
    </#if>
</#macro>
<table class="table table-striped table-bordered table-hover" id="treeTable">
    <thead>
    <tr>
        <th class="center">编号</th>
        <th>名称</th>
        <th class="center">标识</th>
        <th class="center">状态</th>
        <th class="center">排序</th>
        <th class="center">操作</th>
    </tr>
    </thead>
    <tbody id="tree_data">
        <@clazzTree list=vo />
    </tbody>
</table>