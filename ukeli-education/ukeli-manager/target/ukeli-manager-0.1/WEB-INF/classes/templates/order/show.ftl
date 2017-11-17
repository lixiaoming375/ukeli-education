<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-2"></div>
        <form class="form-horizontal"  id="orderForm" method="post" action="${adminPath}/order/save.json">
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="username"> 订单号: </label>
                        <div class="col-sm-4">
                            <div class="clearfix">
                                <input name="ordernumber"  type="text" id="ordernumber" readonly class="col-sm-11" value="${order.ordernumber!}" placeholder="订单号"/>
                            </div>
                        </div>
                        
                        <label class="col-sm-2 control-label no-padding-right" for="username"> 用户名: </label>
                        <div class="col-sm-4">
                            <div class="clearfix">
                                <input name="username" type="text"  id="username" readonly class="col-sm-11" value="${order.username!}" placeholder="用户名为用户手机号"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
           
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="nickname"> 用户昵称: </label>
                        <div class="col-sm-4">
                            <div class="clearfix">
                                <input name="nickname" type="text"  id="nickname" readonly class="col-sm-11" value="${order.nickname!}" placeholder="用户名昵称"/>
                            </div>
                        </div>
                         <label class="col-sm-2 control-label no-padding-right" for="productid"> 产品名称: </label>
                        <div class="col-sm-4">
                            <div class="clearfix">
                               <select name="productid"  id="productid"  data-placeholder="产品名称" style="width: 180px;vertical-align:middle;">
			                        <option value='' >----请选择----</option>
			                        <#if productList ??>
										<#list productList as product>
				                            <option value='${product.id}'  <#if order.productid ?? && order.productid ==product.id >selected="selected"</#if> >${product.productname}</option>
										</#list>
									</#if>
								</select>	
                            </div>
                        </div>
                    </div>
                </div>
            </div>
             <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                         <label class="col-sm-2 control-label no-padding-right" for="orderstatus"> 订单状态: </label>
                        <div class="col-sm-4">
                            <div class="clearfix">
                               <select name="orderstatus"  id="orderstatus"  data-placeholder="订单状态" style="width: 180px;vertical-align:middle;">
			                        <option value='0' >----请选择----</option>
				                    <option value='1'  <#if order.orderstatus ?? && order.orderstatus == 1>selected="selected"</#if> >已经审核</option>
				                    <option value='2'  <#if order.orderstatus ?? && order.orderstatus == 2>selected="selected"</#if> >已经发货</option>
				                    <option value='3'  <#if order.orderstatus ?? && order.orderstatus == 3>selected="selected"</#if> >用户收货</option>
				                    <option value='4'  <#if order.orderstatus ?? && order.orderstatus == 4>selected="selected"</#if> >完单</option>
				                    <option value='9'  <#if order.orderstatus ?? && order.orderstatus == 9>selected="selected"</#if> >用户取消</option>
								</select>	
                            </div>
                        </div>
                        <label class="col-sm-2 control-label no-padding-right" for="paystatus"> 支付状态: </label>
                        <div class="col-sm-4">
                             <div class="clearfix">
                              <select name="paystatus"  id="paystatus"  data-placeholder="订单状态" style="width: 180px;vertical-align:middle;">
				                    <option value='0'  <#if order.paystatus ?? && order.paystatus == 0>selected="selected"</#if> >未支付</option>
				                    <option value='1'  <#if order.paystatus ?? && order.paystatus == 1>selected="selected"</#if> >已经支付</option>
				                    <option value='9'  <#if order.paystatus ?? && order.paystatus == 9>selected="selected"</#if> >退款</option>
								</select>	
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="username"> 总市场价: </label>
                        <div class="col-sm-4">
                            <div class="clearfix">
	                             <div class="input-group">
	                              <span class="input-group-addon">¥</span>
						          <input type="text" name="totalmarketprice"  id="totalmarketprice" readonly class="col-sm-11 form-control" value="${order.totalmarketprice!}"/>
						          <span class="input-group-addon">.00</span>
	                            </div>
                            </div>
                        </div>
                        <label class="col-sm-2 control-label no-padding-right" for="totalprice"> 总价: </label>
                        <div class="col-sm-4">
                             <div class="input-group">
                              <span class="input-group-addon">¥</span>
					          <input type="text" name="totalprice"  id="totalprice" readonly class="col-sm-11 form-control" value="${order.totalprice!}"/>
					          <span class="input-group-addon">.00</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                         <label class="col-sm-2 control-label no-padding-right" for="payprice"> 支付价格: </label>
                        <div class="col-sm-4">
                             <div class="input-group">
                              <span class="input-group-addon">¥</span>
					          <input type="text" name="payprice"  id="payprice" readonly class="col-sm-11 form-control" value="${order.payprice!}"/>
					          <span class="input-group-addon">.00</span>
                            </div>
                        </div>
                        <label class="col-sm-2 control-label no-padding-right" for="paytime"> 支付时间: </label>
                        <div class="col-sm-4">
                             <div class="clearfix">
                                <input name="paytime"  type="text" id="paytime"  class="form_datetime form-control"  value="${order.ordertime!}"  style="width:200px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" readonly/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
             <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                     <label class="col-sm-2 control-label no-padding-right" for="paytype"> 支付类型: </label>
                        <div class="col-sm-4">
                            <div class="clearfix">
                              <select name="paytype"  id="paytype"   data-placeholder="订单状态" style="width: 180px;vertical-align:middle;">
				                    <option value='0' >----请选择----</option>
				                    <option value='1'  <#if order.paytype ?? && order.paytype == 1>selected="selected"</#if> >支付宝</option>
				                    <option value='2'  <#if order.paytype ?? && order.paytype == 2>selected="selected"</#if> >微信</option>
				                    <option value='3'  <#if order.paytype ?? && order.paytype == 3>selected="selected"</#if> >银联</option>
				                    <option value='4'  <#if order.paytype ?? && order.paytype == 4>selected="selected"</#if> >货到汇款</option>
				                    <option value='5'  <#if order.paytype ?? && order.paytype == 5>selected="selected"</#if> >其他</option>
				                    <option value='9'  <#if order.paytype ?? && order.paytype == 9>selected="selected"</#if> >未知</option>
							  </select>	
                            </div>
                        </div>
                        <label class="col-sm-2 control-label no-padding-right" for="amount"> 购买数量: </label>
                        <div class="col-sm-4">
                            <div class="clearfix">
                                <input name="amount" type="text" id="amount" class="col-sm-11"  readonly value="${order.amount!}" placeholder="正整数"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
            
             <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="amount"> 支付订单号: </label>
                        <div class="col-sm-4">
                            <div class="clearfix">
                                <input name="payorder" type="text" id="payorder" class="col-sm-11" readonly value="${order.payorder!}" placeholder="正整数"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
            
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="detail"> 备注: </label>
                        <div class="col-sm-10">
                             <div class="clearfix">
                                 <textarea  id="detail" rows="4" class="col-sm-12" name="detail"  >${order.detail!}
                                 </textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
             	<input name="id" type="hidden" value="${order.id!}">
        </form>

    </div>
</div>

<script src="${adminPath}/res/js/order/order.edit.js?ver=${version}"></script>
<script type="text/javascript">
	 $(".form_datetime").datetimepicker({
	 format: "yyyy-mm-dd hh:mm:ss",
	 autoclose: true,
	 todayBtn: true,
	 todayHighlight: true,
	 showMeridian: true,
	 pickerPosition: "bottom-left",
	 language: 'zh-CN',//中文，需要引用zh-CN.js包
	 startView: 2,//月视图
	 minView: 0//日期时间选择器所能够提供的最精确的时间选择视图
	 }); 
	
</script>
