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
                    <li class="active">订单产品</li>
                </ul><!-- /.breadcrumb -->
                <!-- /section:basics/content.searchbox -->
            </div>
            <div class="page-content">
               <div class="row pb10 wb100">
                    <div class="col-xs-12 col-xs-10">
                        <form class="form-inline">
                            <div class="form-group">
                                <label for="username">手机号码：</label>
                                <input type="text" class="form-control" style="width: 150px" id="usernameForSearch" placeholder="手机号码">
                            </div>
                             <div class="form-group">
                                <label for="ordernumber">&nbsp;&nbsp;&nbsp;&nbsp;订单号：</label>
                                <input type="text" class="form-control" style="width: 150px" id="ordernumberForSearch" placeholder="订单号">
                            </div>
                              <div class="form-group">
                                 <label for="ordertime">下单时间段：</label>
			                     <div class="input-prepend input-group">
			                       <span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
			                       <input type="text" style="width: 200px" name="reservation" id="reservationtime" class="form-control"   class="span4"/>
			                    </div>
			                 </div>
                           <div class="form-group">
                                <label for="productid">产品名称：</label>
                                <select name="productidForSearch"  id="productidForSearch"  data-placeholder="产品名称" style="width: 150px;vertical-align:middle;">
			                        <option value=' ' >-请选择-</option>
				                    <option value='1' >年会员</option>
				                    <option value='2' >半年会员</option>
				                    <option value='3' >3月会员</option>
				                    <option value='4' >月会员</option>
								</select>	
                            </div>
                             <div class="form-group">
                                 <label for="paystatus">支付状态：</label>
			                      <select name="paystatusForSearch"  id="paystatusForSearch"  data-placeholder="支付状态" style="width: 150px;vertical-align:middle;">
			                        <option value=' ' >-请选择-</option>
				                    <option value='0' >未支付</option>
				                    <option value='1' >已经支付</option>
				                    <option value='9' >退款</option>
								</select>	
			                 </div>
                            <button type="button" class="btn btn-purple btn-sm" id="searchbtn"><span class="ace-icon fa fa-search icon-on-right bigger-110"></span>搜索</button>
                            <button type="button" class="btn btn-purple btn-sm" id="researchbtn"><span class="ace-icon fa fa-search icon-on-right bigger-110"></span>重置</button>
                        </form>
                    </div>
                    <div class="col-xs-12 col-sm-2 t_ar">
                        <button class="btn btn-sm btn-default" id="addbtn"><i class="ace-icon fa fa-bolt bigger-110"></i>添加</button>
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
									<th class="hidden-sm hidden-xs">订单号</th>
									<th class="hidden-sm hidden-xs">用户名</th>
									<th class="hidden-sm hidden-xs">用户昵称</th>
									<th class="hidden-sm hidden-xs">产品名称</th>
									<th class="hidden-sm hidden-xs">购买数量</th>
									<th class="hidden-sm hidden-xs">总市场价</th>
									<th class="hidden-sm hidden-xs">总价</th>
									<th class="hidden-sm hidden-xs">支付价格</th>
									<th class="hidden-sm hidden-xs">下单时间</th>
									<th class="hidden-sm hidden-xs">订单状态</th>
									<th class="hidden-sm hidden-xs">支付状态</th>
									<th class="hidden-sm hidden-xs">支付时间</th>
									<th class="hidden-sm hidden-xs">支付类型</th>
									<th class="hidden-sm hidden-xs">支付订单号</th>
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

<script src="${adminPath}/res/js/order/order.js?ver=${version}"></script>

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