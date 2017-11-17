<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>${productName}后台管理系统</title>
    <#include "../../common/style.ftl"/>

    <link rel="stylesheet" href="${resWebUrl}plugin/treeTable/themes/vsStyle/treeTable.css?ver=${version}" />
</head>
<body class="no-skin">
<#include "../../common/top.ftl"/>
<!-- /section:basics/navbar.layout -->
<div class="main-container ace-save-state" id="main-container">
<#include "../../common/nav.ftl"/>
    <!-- /section:basics/sidebar -->
    <div class="main-content">
        <div class="main-content-inner">
            <!-- #section:basics/content.breadcrumbs -->
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="${adminPath}/index.html">首页管理</a>
                    </li>
                    <li class="active">权限信息</li>
                </ul><!-- /.breadcrumb -->
                <!-- /section:basics/content.searchbox -->
            </div>
            <div class="page-content">
                <div class="row pb10 wb100">
                    <div class="col-xs-12 col-sm-8">
                        <button class="btn btn-sm btn-default" id="addbtn"><i class="ace-icon fa fa-bolt bigger-110"></i>添加</button>
                        <button class="btn btn-sm btn-primary" id="editbtn"><i class="ace-icon fa fa-pencil-square-o bigger-110"></i>修改</button>
                        <!--<button class="btn btn-sm btn-danger" id="delbtn"><i class="ace-icon fa fa-trash-o bigger-110"></i>删除</button>-->
                    </div>
                    <div class="col-xs-12 col-sm-4 t_ar"></div>
                </div>

                <div class="row">
                    <div class="col-xs-12">
                        <div>
                            <table class="table table-striped table-bordered table-hover" id="contentTable">
                                <thead>
                                <tr>
                                    <th class="center">
                                        <input type="checkbox" class="i-checks"/>
                                    </th>
									<th>权限名称</th>
									<th>权限唯一标识</th>
									<th class="hidden-sm hidden-xs">地址</th>
									<th class="hidden-sm hidden-xs">图标字段</th>
                                    <th>类型</th>
                                    <th>显示</th>
                                    <th>排序</th>
									<th class="hidden-sm hidden-xs">最后修改时间</th>
                                    <th class="center">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <@bpTree list=vo />
                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>

            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->
<#macro bpTree list>
    <#if list??>
        <#list list as permissionvo>
            <#if permissionvo.permissionDo??>
                <tr id="${ permissionvo.permissionDo.id}" pId="${ permissionvo.permissionDo.parentid}">
                    <td class="center" height="28">
                        <input class="i-checks" type="checkbox" id="${permissionvo.permissionDo.id}">
                   </td>
                   <td>${ permissionvo.permissionDo.name!}</td>
                   <td>${ permissionvo.permissionDo.ident!}</td>
                   <td class="hidden-sm hidden-xs">${ permissionvo.permissionDo.url!}</td>
                   <td class="hidden-sm hidden-xs"><i id="iconIcon" class="ace-icon fa ${permissionvo.permissionDo.icons!}"></i>${permissionvo.permissionDo.icons!}</td>
                    <td><#if (permissionvo.permissionDo.type??) && (permissionvo.permissionDo.type==2)>
                        <span class="text-primary">权限</span>
                    <#else>
                        <span class="text-danger">列表</span>
                    </#if>
                    </td>
                   <td><#if permissionvo.permissionDo.isshow=="1">
                        <a class="red" href="javascript:;" onclick="online(${permissionvo.permissionDo.id},0,1)"><button class="btn btn-minier btn-yellow">显示</button></a>
                    <#else>
                        <a class="red" href="javascript:;" onclick="online(${permissionvo.permissionDo.id},1,1)"><button class="btn btn-minier btn-danger">隐藏</button></a>
                    </#if>
                   </td>
                    <td class="hidden-xs">
                        <a href="#" onclick="order(${permissionvo.permissionDo.id},1);"><i class="blue ace-icon fa fa-arrow-circle-up bigger-200"></i></a>&nbsp;&nbsp;
                        <a href="#" onclick="order(${permissionvo.permissionDo.id},2);"><i class="blue ace-icon  fa fa-arrow-circle-down bigger-200"></i></a>
                    </td>
                   <td class="hidden-xs">${permissionvo.permissionDo.edittime!}</td>
                   <td class="center">
                       <div class="action-buttons">
                          <a class="btn btn-minier btn-success"  href="javascript:show(${permissionvo.permissionDo.id})" title="查看"><i class="ace-icon fa fa-eye"></i>查看</a>
                          <a class="btn btn-minier btn-primary"  href="javascript:edit(${permissionvo.permissionDo.id})" title="修改"><i class="ace-icon fa fa-pencil"></i>修改</a>
                          <a class="btn btn-minier btn-pink" href="javascript:del(${permissionvo.permissionDo.id})" title="删除"><i class="ace-icon fa fa-trash-o"></i>删除</a>
                       </div>
                   </td>
                </tr>
            </#if>
            <#if permissionvo.list??>
                <@bpTree list=permissionvo.list />
            </#if>
        </#list>
    </#if>
</#macro>
<#include "../../common/bottom.ftl"/>

</div><!-- /.main-container -->
<#include "../../common/script.ftl"/>

<script src="${resWebUrl}plugin/treeTable/jquery.treeTable.js?ver=${version}"></script>
<script src="${resWebUrl}plugin/ztree/jquery.ztree.all.min.js?ver=${version}"></script>
<script src="${resWebUrl}res/js/manager/permission.js?ver=${version}"></script>
</body>
</html>