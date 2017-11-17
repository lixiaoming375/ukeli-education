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
                    <div class="col-xs-12">
                        <form class="form-inline">
                        
                          <div class="form-group">
                                 <label for="reservationtime">留言时间段：</label>
			                     <div class="input-prepend input-group">
			                       <span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
			                       <input type="text" style="width: 100px" name="reservationtime" id="reservationtime" class="form-control"   class="span4"/>
			                    </div>
			                 </div>
                            <div class="form-group">
                                <label for="keyword">用户昵称：</label>
                                <input type="text" class="form-control" id="nicknameForSearch" name="nicknameForSearch" placeholder="用户昵称" style="width:100px">
                            </div>
                             <div class="form-group">
                                <label for="vtypeForSearch">留言类型：</label>
                                <select name="vtypeForSearch"  id="vtypeForSearch"  data-placeholder="试题类型" style="vertical-align:middle;">
			                     <option value='' >-请选择-</option>
                                <#if clazzDoList??>
                                    <#list clazzDoList as clazz>
                                        <option value="${clazz.id}">${clazz.name}</option>
                                    </#list>
                                </#if>
								</select>	
                            </div>
                             <div class="form-group">
                                <label for="isanswerForSearch">是否回答：</label>
                                <select name="isanswerForSearch"  id="isanswerForSearch"  data-placeholder="在线状态" style="vertical-align:middle;">
			                     <option value='' >-请选择-</option>
			                     <option value='0' >未回答</option>
			                     <option value='1' >已回答</option>
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
                          
			                <!-- 
			                <div class="form-group">
                                <label for="keyword">留言内容：</label>
                                <input type="text" class="form-control" name="questionForSearch" id="questionForSearch">
                            </div>
                            -->
                            <button type="button" class="btn btn-purple btn-sm" id="searchbtn"><span class="ace-icon fa fa-search icon-on-right bigger-110"></span>搜索</button>
                            <button type="button" class="btn btn-inverse btn-sm" id="researchbtn"><span class="ace-icon fa fa-search icon-on-right bigger-110"></span>重置</button>
                        </form>
                    </div>
                </div>
                <div class="row  pb10 wb100">
	                <div class="col-xs-12 ">
	                        <!--<button class="btn btn-sm btn-default" id="addbtn"><i class="ace-icon fa fa-bolt bigger-110"></i>添加</button>-->
	                        <button class="btn btn-sm btn-primary" id="editbtn"><i class="ace-icon fa fa-pencil-square-o bigger-110"></i>回复</button>
	                        <button class="btn btn-sm btn-danger" id="delbtn"><i class="ace-icon fa fa-trash-o bigger-110"></i>删除</button>
	                </div>
                </div>

                <div class="row">
                    <div class="col-xs-12">
                        <div>
                            <table class="table table-striped table-bordered table-hover" id="contentTable">
                                <thead>
                                <tr>
                                   <th class="hidden-xs center"><input type="checkbox" class="i-checks"/></th>
									<th class="hidden-sm hidden-xs">编号</th>
									<th class="hidden-sm hidden-xs">用户编号</th>
									<th class="hidden-sm hidden-xs">用户名</th>
									<!--<th class="hidden-sm hidden-xs">问题内容</th>-->
									<th class="hidden-sm hidden-xs">问题类型</th>
									<th class="hidden-sm hidden-xs">是否回答</th>
									<!--<th class="hidden-sm hidden-xs">教师回复</th>-->
									<th class="hidden-sm hidden-xs">教师回答时间</th>
									<th class="hidden-sm hidden-xs">状态</th>
									<th class="hidden-sm hidden-xs">排序</th>
									<th class="hidden-sm hidden-xs">留言时间</th>
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

<script src="${adminPath}/res/js/question/question.js?ver=${version}"></script>

<script type="text/javascript">
	    $(document).ready(function() {
	         $('#reservationtime').daterangepicker({
	                    timePicker: true,
	                    timePickerIncrement: 30,
	                    format: 'YYYY/MM/DD hh:mm:ss'
	                  }, function(start, end, label) {
	                    console.log(start.toISOString(), end.toISOString(), label);
	                  });
	         });
</script>
</body>
</html>