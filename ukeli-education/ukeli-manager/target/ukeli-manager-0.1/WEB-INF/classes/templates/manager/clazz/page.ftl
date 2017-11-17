<!DOCTYPE html>
<html lang="zh-CN">
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
                    <li class="active">分类</li>
                </ul><!-- /.breadcrumb -->
                <!-- /section:basics/content.searchbox -->
            </div>
            <div class="page-content">
                <div class="row">
                    <!-- 分类类别-->
                    <div class="col-sm-5">
                        <div class="widget-box">
                            <div class="widget-header">
                                <h5 class="widget-title">分类类别</h5>
                            </div>
                            <div class="widget-body">
                                <div class="widget-main">
                                    <div class="row pb10 wb100">
                                        <div class="col-xs-12 col-sm-8">
                                            <button class="btn btn-sm btn-default" id="addtypebtn"><i class="ace-icon fa fa-bolt bigger-110"></i>添加</button>
                                            <button class="btn btn-sm btn-primary" id="edittypebtn"><i class="ace-icon fa fa-pencil-square-o bigger-110"></i>修改</button>
                                            <!--<button class="btn btn-sm btn-danger" id="deltypebtn"><i class="ace-icon fa fa-trash-o bigger-110"></i>删除</button>-->
                                        </div>
                                    </div>
                                    <table class="table table-striped table-bordered table-hover" id="contentTable">
                                        <thead>
                                        <tr>
                                            <th class="center">

                                            </th>
                                            <th class="center">编号</th>
                                            <th class="center">名称</th>
                                            <th class="center">状态</th>
                                            <th class="center">排序</th>
                                            <th class="center">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="ajax-data">

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--分类树 -->
                    <div class="col-sm-7">
                        <div class="widget-box">
                            <div class="widget-header">
                                <h5 class="widget-title"> 分类数据</h5>
                            </div>
                            <div class="widget-body">
                                <div class="widget-main" id="widget_tree">

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->

<#include "../../common/bottom.ftl"/>

</div><!-- /.main-container -->
<#include "../../common/script.ftl"/>

<script src="${resWebUrl}plugin/treeTable/jquery.treeTable.js?ver=${version}"></script>
<script src="${adminPath}/res/js/manager/clazz.js?ver=${version}"></script>
</body>
</html>