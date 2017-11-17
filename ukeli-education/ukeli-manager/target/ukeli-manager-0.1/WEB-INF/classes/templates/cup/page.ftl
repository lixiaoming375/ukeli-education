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


                <div class="row">
                    <div class="col-xs-7">
                    <div class="widget-box">
                       <div class="widget-header">
                          <h5 class="widget-title">杯赛列表</h5>
                       </div>
                       <div class="widget-body">
                       <div class="widget-main">
                       
	                <div class="row pb10 wb100">
	                    <div class="col-xs-12 col-sm-8">
	                        <form class="form-inline">
	                            <div class="form-group">
	                                <label for="keyword">关键字：</label>
	                                <input type="text" class="form-control" id="keyword" placeholder="杯赛名称">
	                            </div>
	                            <button type="button" class="btn btn-purple btn-sm" id="searchbtn"><span class="ace-icon fa fa-search icon-on-right bigger-110"></span>搜索</button>
	                            <button type="button" class="btn btn-purple btn-sm" id="researchbtn"><span class="ace-icon fa fa-search icon-on-right bigger-110"></span>重置</button>
	                        </form>
	                    </div>
	                    <div class="col-xs-12 col-sm-4 t_ar">
	                        <button class="btn btn-sm btn-default" id="addbtn"><i class="ace-icon fa fa-bolt bigger-110"></i>添加</button>
	                        <button class="btn btn-sm btn-danger" id="delbtn"><i class="ace-icon fa fa-trash-o bigger-110"></i>删除</button>
	                    </div>
	                </div>
                    
                        <div>
                            <table class="table table-striped table-bordered table-hover" id="contentTable">
                                <thead>
                                <tr>
                                  <th class="hidden-xs center"></th>
                                    <th class="hidden-sm hidden-xs">编号</th>
                                    <th class="hidden-sm hidden-xs">杯赛名称</th>
                                    <th class="hidden-sm hidden-xs">试题总量</th>
                                    <th class="hidden-sm hidden-xs">时长</th>
                                    <th class="hidden-sm hidden-xs">分数</th>
                                    <th class="hidden-sm hidden-xs">年级</th>
									<th class="hidden-sm hidden-xs">状态</th>
									<!--<th class="hidden-sm hidden-xs">排序</th>-->
                                    <th style="border-right:#CCC solid 1px;">操作</th>
                                </tr>
                                </thead>

                                <tbody id="ajax-data">

                                </tbody>
                            </table>

							</div>
							</div>
							</div>
                        </div>
                    </div> <!--col-xs-7-->
                    
                      <!--题目列表 -->
                    <div class="col-sm-5">
                     <div class="widget-box">
                      <div class="widget-header">
                           <h5 class="widget-title"> 题目列表</h5>
                      </div>
                       <div class="widget-body">
                            <div class="widget-main" id="widget_sub">
                               <div>
		                            <table class="table table-striped table-bordered table-hover" id="contentTableForSubject">
		                              <thead>
		                               <tr>
		                                <th class="hidden-sm hidden-xs">编号</th>
										<th class="hidden-sm hidden-xs">试题序号</th>
										<th class="hidden-sm hidden-xs">试题类型</th>
										<th class="hidden-sm hidden-xs">试题名称</th>
										<!--<th class="hidden-sm hidden-xs">状态</th>-->
										<th class="hidden-sm hidden-xs">排序</th>
	                                    <th style="border-right:#CCC solid 1px;">操作</th>
		                               </tr>
		                              </thead>
		
		                              <tbody id="ajax-data-right">
		
		                              </tbody>
		                            </table>
		                        </div>     
                            </div>
                       </div>
                     </div>
                    </div>
                </div><!-- /.row -->

            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->

<#include "../common/bottom.ftl"/>

</div><!-- /.main-container -->
<#include "../common/script.ftl"/>

<script src="${adminPath}/res/js/cup/cup.js?ver=${version}"></script>
</body>
</html>