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
                    <li class="active">用户信息</li>
                </ul><!-- /.breadcrumb -->
                <!-- /section:basics/content.searchbox -->
            </div>
            <div class="page-content">
                <div class="row pb10 wb100">
                    <div class="col-xs-12 col-sm-10">
                        <form class="form-inline">
                            <div class="form-group">
                                <label for="usernameForSearch">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户名：</label>
                                <input type="text" class="form-control" style="width: 150px" id="usernameForSearch" placeholder="用户名">
                            </div>
                            <div class="form-group">
                                <label for="nicknameForSearch">昵称：</label>
                                <input type="text" class="form-control" style="width: 150px" id="nicknameForSearch" placeholder="昵称">
                            </div>
                             <div class="form-group">
                                 <label for="keyword">注册时间段：</label>
			                     <div class="input-prepend input-group">
			                       <span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
			                       <input type="text" style="width: 200px" name="reservation" id="reservationtime" class="form-control"   class="span4"/>
			                    </div>
			                 </div>
                             <div class="form-group">
                                <label for="isvip">是否vip会员：</label>
                                <select name="isvipForSearch"  id="isvipForSearch"  data-placeholder="是否vip会员" style="width: 150px;vertical-align:middle;">
			                        <option value=' ' >-请选择-</option>
				                    <option value='0' >否</option>
				                    <option value='1' >是</option>
								</select>	
                            </div>
                            <div class="form-group">
                                <label for="statusForSearch">在线状态：</label>
                                <select name="statusForSearch"  id="statusForSearch"  data-placeholder="在线状态" style="width: 150px;vertical-align:middle;">
			                     <option value='-1' >-请选择-</option>
			                     <option value='1' >在线</option>
			                     <option value='0' >离线</option>
								</select>	
                            </div>
                            <button type="button" class="btn btn-purple btn-sm" id="searchbtn"><span class="ace-icon fa fa-search icon-on-right bigger-110"></span>搜索</button>
                            <button type="button" class="btn btn-inverse btn-sm" id="researchbtn"><span class="ace-icon fa fa-search icon-on-right bigger-110"></span>重置</button>
                        </form>
                    </div>
                </div>
                <div class="row  pb10 wb100">
	                 <div class="col-sm-12">
	                   <button class="btn btn-sm btn-info" id="addbtn"><i class="ace-icon fa fa-bolt bigger-110"></i>添加</button>
                       <button class="btn btn-sm btn-primary" id="editbtn"><i class="ace-icon fa fa-pencil-square-o bigger-110"></i>修改</button>
                       <button class="btn btn-sm btn-danger" id="delbtn"><i class="ace-icon fa fa-trash-o bigger-110"></i>删除</button>
                       <button class="btn btn-sm btn-add" id="passwordbtn"><i class="ace-icon fa fa-pencil-square-o bigger-110"></i>修改密码</button>
                       <button class="btn btn-sm btn-add" id="vipbtn"><i class="ace-icon fa fa-pencil-square-o bigger-110"></i>修改VIP信息</button>
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
									<th class="hidden-sm hidden-xs">用户名</th>
									<th class="hidden-sm hidden-xs">昵称</th>
									<th class="hidden-sm hidden-xs">真实姓名</th>
									<th class="hidden-sm hidden-xs">出生年月</th>
									<th class="hidden-sm hidden-xs">性别</th>
									<th class="hidden-sm hidden-xs">联系电话</th>
									<th class="hidden-sm hidden-xs">电子邮件</th>
									<th class="hidden-sm hidden-xs">是否vip会员</th>
									<th class="hidden-sm hidden-xs">vip开始时间</th>
									<th class="hidden-sm hidden-xs">vip结束时间</th>
									<th class="hidden-sm hidden-xs">状态</th>
									<th class="hidden-sm hidden-xs">排序</th>
								    <th class="hidden-sm hidden-xs">注册时间</th>
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

<script src="${adminPath}/res/js/user/user.js?ver=${version}"></script>

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