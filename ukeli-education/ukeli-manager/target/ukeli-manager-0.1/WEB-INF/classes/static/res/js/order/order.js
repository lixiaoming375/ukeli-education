
var orderTemplate ='{{each list as order index}}'+
    '<tr>'+
    '   <td class="hidden-xs center" height="28">'+
    '       <label class="pos-rel">'+
    '       		<input class="i-checks" type="checkbox" id="{{ order.id}}">'+
    '       </label>'+
    '   </td>'+
    '   <td class="center">{{ order.id}}</td>'+
    '   <td>{{ order.ordernumber}}</td>'+
    '   <td>{{ order.username}}</td>'+
    '   <td>{{ order.nickname}}</td>'+
    '   <td>{{ order.productname}}</td>'+
    '   <td>{{ order.amount}}</td>'+
    '   <td>{{ order.totalmarketprice}}</td>'+
    '   <td>{{ order.totalprice}}</td>'+
    '   <td>{{ order.payprice}}</td>'+
    '   <td>{{dateFormat(order.ordertime,\'yyyy-MM-dd hh:mm:ss\')}}</td>'+
    '   <td>{{#orderstatus(order.orderstatus)}}</td>'+
    '   <td>{{#paystatus(order.paystatus)}}</td>'+
    '   <td>{{dateFormat(order.paytime,\'yyyy-MM-dd hh:mm:ss\')}}</td>'+
    '   <td>{{#paytype(order.paytype)}}</td>'+
    '   <td>{{ order.payorder}}</td>'+
    '   <td class="hidden-xs">{{#status(order.status,order.id)}}</td>'+
    '   <td class="hidden-xs">{{dateFormat(order.edittime,\'yyyy-MM-dd hh:mm:ss\')}}</td>'+
    '   <td>'+
    '<a class="btn btn-minier btn-primary"  href="javascript:show({{ order.id}},)" title="查看"><i class="ace-icon fa fa-pencil"></i>查看</a>  '+
    '<a class="btn btn-minier btn-primary"  href="javascript:edit({{ order.id}})" title="修改"><i class="ace-icon fa fa-pencil"></i>修改</a>  '+
    '<a class="btn btn-minier btn-pink" href="javascript:del({{ order.id}})" title="删除"><i class="ace-icon fa fa-trash-o"></i>删除</a>'+
    '</td>'+
    '</tr>'+
    '{{/each}}'+
    '<tr>'+
    '    <td height="50"></td>'+
    '   <td  colspan="19" id="pager">'+
    '       <div class="fl" style="padding-top:7px"></div>'+
    '       <div align="left" class="fr">'+
    '           <ul class="pagination"></ul>'+
    '       </div>'+
    '   </td>'+
    '</tr>';



/**
 *  `orderstatus`'订单状态|1|3|0| 0 未处理 1 已经审核 2 已经发货 3 用户收货 4 完单   9 用户取消',
 */
template.helper('orderstatus',function (orderstatus) {
    if(orderstatus =='0'){
        return '未处理';
    }else if(orderstatus =='1'){
        return '已经审核';
    }else if(orderstatus =='2'){
        return '已经发货';
    }else if(orderstatus =='3'){
        return '用户收货';
    }else if(orderstatus =='4'){
        return '完单';
    }else if(orderstatus =='9'){
        return "用户取消";
    }
});

/**
 *    `paystatus` DEFAULT '0' COMMENT '支付状态|1|3|0| 0 未支付  1 已经支付  9 退款',
 */
template.helper('paystatus',function (paystatus) {
    if(paystatus =='0'){
        return '未支付';
    }else if(paystatus =='1'){
        return '已经支付';
    }else if(paystatus =='9'){
        return '退款';
    }
});

/**
 *  `paytype`  DEFAULT '0' COMMENT '支付类型|1|3|0| 0 默认  1 支付宝  2 微信  3 银联  4 货到汇款 5 其他  9 未知',
 */
template.helper('paytype',function (paytype) {
    if(paytype =='0'){
        return '默认';
    }else if(paytype =='1'){
        return '支付宝';
    }else if(paytype =='2'){
        return '微信';
    }else if(paytype =='3'){
        return '银联';
    }else if(paytype =='4'){
        return '货到汇款';
    }else if(paytype =='5'){
        return "其他";
    }else if(paytype =='9'){
        return "未知";
    }
});

$(function () {
    $("#searchbtn").on('click',search);
    $("#researchbtn").on('click',reSearch);
    $("#addbtn").on('click',addorder);
    $("#editbtn").on('click',editorder);
    $("#delbtn").on('click',delAll);
    Eymjs.page.init();
    search();
});

/**
 * 搜索
 */
function search() {
	page(1);
}
/**
 * 重置搜索
 */
function reSearch() {
    $("#usernameForSearch").val('');
    $("#ordernumberForSearch").val('');
    $("#reservationtime").val('');
    $("#productidForSearch").val('');
    $("#paystatusForSearch").val('');
    search();
}

function page(page){
    var username = $("#usernameForSearch").val();
    var ordernumber = $("#ordernumberForSearch").val();
    var reservationtime = $("#reservationtime").val();
    var productid = $("#productidForSearch").val();
    var paystatus = $("#paystatusForSearch").val();
    Eymjs.page.show(
    		Eymjs.ukeli.url.order.LIST,
    		{username:username,ordernumber:ordernumber,reservationtime:reservationtime,productid:productid,paystatus:paystatus,page:page},
            orderTemplate
            );
}

/**
 * 增加一个管理员
 */
function addorder() {
    Eymjs.dialog.showUrl('订单产品增加',Eymjs.ukeli.url.order.EDIT,{},650,420);
}
/**
 * 修改一条记录
 * @param id
 */
function edit(id) {
    Eymjs.dialog.showUrl('修改订单产品',Eymjs.ukeli.url.order.EDIT,{id:id},650,420);
}
/**
 * 修改按钮
 */
function editorder() {
    var id= Eymjs.page.selectOne("contentTable");
    if(id){edit(id);}
}
/**
 * 选择多个，删除
 */
function delAll() {
    var ids= Eymjs.page.selectList("contentTable");
    if(ids){
        Eymjs.page.delByids(ids,Eymjs.ukeli.url.order.DELETE,"您确定要删除您选择的订单产品吗")
    }
}

/**
 * 删除一个记录
 * @param id
 */
function del(id) {

    Eymjs.page.delByids(id,Eymjs.ukeli.url.order.DELETE,"您确定要删除该订单产品吗?")
}

/**
 * 查看
 * @returns
 */
function show(id){
	 Eymjs.dialog.showUrl('查看',Eymjs.ukeli.url.order.SHOW,{id:id},650,400);
}


/**
 * 上线或者下线一条记录,或者锁定以及一个订单产品
 * @param id
 * @param status
 */
function online(id,status,type) {
    if(!id){return;}
    var msg = "您确定要下线该订单产品吗？";
    if(status==1){
        msg = "您确定要上线该订单产品吗？";
    }
    if(type==1){
        msg = "您确定要审核不通过该订单产品吗？";
        if(status==1){
            msg = "您确定要审核通过该订单产品吗？";
        }
    }

    Eymjs.page.online(Eymjs.ukeli.url.order.STATUS,{id:id,status:status,type:type},msg)
}