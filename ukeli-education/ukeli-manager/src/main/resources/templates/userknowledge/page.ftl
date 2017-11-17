<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>${productName}后台管理系统</title>
    <#include "../common/style.ftl"/>
</head>
<body class="no-skin">
<#include "../common/top.ftl"/>
<!-- /section:basics/navbar.layout -->
<div class="main-container ace-save-state" id="main-container">
<#include "../common/nav.ftl"/>
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
                    <li class="active"></li>
                </ul><!-- /.breadcrumb -->
                <!-- /section:basics/content.searchbox -->
            </div>
            <div class="page-content">
                <div class="row pb10 wb100">
                     <div class="row pb10 wb100">
                    <div class="col-xs-12 ">
                        <form class="form-inline">
                            <div class="form-group">
                                <label for="keyword">用户名称：</label>
                                <input type="text" class="form-control" id="usernameForSearch" name="usernameForSearch" placeholder="用户名称" style="width:100px">
                            </div>
                            <div class="form-group">
                                <label for="keyword">知识点名称：</label>
                                <input type="text" class="form-control" id="nodenameForSearch" name="nodenameForSearch" placeholder="知识点名称" style="width:100px">
                            </div>
                            <div class="form-group">
                                <label for="isdoneForSearch">是否完成：</label>
                                <select name="isdoneForSearch"  id="isdoneForSearch"  data-placeholder="是否完成" style="vertical-align:middle;">
			                     <option value='' >-请选择-</option>
			                     <option value='0' >未完成</option>
			                     <option value='1' >已完成</option>
								</select>	
                            </div>
                            <div class="form-group">
                                <label for="isaddForSearch">是否加入我的课程：</label>
                                <select name="isaddForSearch"  id="isaddForSearch"  data-placeholder="是否加入我的课程" style="vertical-align:middle;">
			                     <option value='' >-请选择-</option>
			                     <option value='0' >未加入</option>
			                     <option value='1' >已加入</option>
								</select>	
                            </div>
                             <div class="form-group">
                                <label for="statusForSearch">在线状态：</label>
                                <select name="statusForSearch"  id="statusForSearch"  data-placeholder="在线状态" style="vertical-align:middle;">
			                     <option value='' >-请选择-</option>
			                     <option value='1' >在线</option>
			                     <option value='0' >离线</option>
								</select>	
                            </div>
                            <button type="button" class="btn btn-purple btn-sm" id="searchbtn"><span class="ace-icon fa fa-search icon-on-right bigger-110"></span>搜索</button>
                            <button type="button" class="btn btn-purple btn-sm" id="researchbtn"><span class="ace-icon fa fa-search icon-on-right bigger-110"></span>重置</button>
                        </form>
                    </div>
                </div>
                
                <!--
                <div class="row pb10 wb100">
                 <div class="col-xs-12">
                        <button class="btn btn-sm btn-default" id="addbtn"><i class="ace-icon fa fa-bolt bigger-110"></i>添加</button>
                        <button class="btn btn-sm btn-primary" id="editbtn"><i class="ace-icon fa fa-pencil-square-o bigger-110"></i>修改</button>
                        <button class="btn btn-sm btn-danger" id="delbtn"><i class="ace-icon fa fa-trash-o bigger-110"></i>删除</button>
                 </div>
                </div>
                -->
                <div class="row">
                    <div class="col-xs-12">
                        <div>
                            <table class="table table-striped table-bordered table-hover" id="contentTable">
                                <thead>
                                <tr>
                                   <th class="hidden-xs center"><input type="checkbox" class="i-checks"/></th>
									<th class="hidden-sm hidden-xs">编号</th>
									<th class="hidden-sm hidden-xs">用户编号</th>
									<th class="hidden-sm hidden-xs">用户名称</th>
									<th class="hidden-sm hidden-xs">用户昵称</th>
									<th class="hidden-sm hidden-xs">知识点编号</th>
									<th class="hidden-sm hidden-xs">知识点名称</th>
									<th class="hidden-sm hidden-xs">是否完成</th>
									<th class="hidden-sm hidden-xs">是否加入</th>
									<th class="hidden-sm hidden-xs">状态</th>
									<th class="hidden-sm hidden-xs">最后修改时间</th>
                                    <th style="border-right:#CCC solid 1px;">操作</th>
                                </tr>
                                </thead>

                                <tbody id="ajax-data">

                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>

            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->

<#include "../common/bottom.ftl"/>

</div><!-- /.main-container -->
<#include "../common/script.ftl"/>

<script src="${adminPath}/res/js/userknowledge/userknowledge.js?ver=${version}"></script>
</body>
</html>