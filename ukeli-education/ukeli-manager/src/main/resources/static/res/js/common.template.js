/**
 * 定义一个基础的模块，用来提示改变用户性别
 */
template.helper('sex',function (sex) {
    if(sex =='1'){
        return '女';
    }else if(sex =='2'){
        return '男';
    }else{
        return "保密";
    }
});

/**
 * 定义一个基础的模块，用来提示改变是否
 */
template.helper('trueorfalse',function (trueorfalse) {
    if(trueorfalse =='0'){
        return '否';
    }else if(trueorfalse =='1'){
        return '是';
    }else{
        return "未知";
    }
});


/**
 * 支付项目管理的payitems收费类型
 */
template.helper('itemtype',function (itemtype) {
    if(itemtype =='1'){
        return '高层收费';
    }else if(itemtype =='2'){
        return '普通物业收费';
    }else if(itemtype =='3'){
        return '商住';
    }else if(itemtype =='4'){
        return '停车费';
    }else{
        return "其他费用";
    }
});

/**
 * 计价单位
 */
template.helper('iprecision',function (iprecision) {
    if(iprecision ==1){
        return '分';
    }else if(iprecision ==2){
        return '角';
    }else if(iprecision ==3){
        return '元';
    }else{
        return "其他单位";
    }
});
/**
 * 收费资金分类
 */
template.helper('pricetype',function (pricetype) {
    if(pricetype ==1){
        return '正常';
    }else if(pricetype ==2){
        return '押金';
    }else if(pricetype ==3){
        return '预收款';
    }else{
        return "其他类型";
    }
});
/**
 * 计费周期单位
 */
template.helper('periodunit',function (periodunit) {
    if(periodunit ==1){
        return '日';
    }else if(periodunit ==2){
        return '月';
    }else if(periodunit ==3){
        return '年';
    }else{
        return "其他类型";
    }
});
/**
 * 计费方式
 */
template.helper('paypattern',function (paypattern) {
    if(paypattern ==1){
        return '按照面积';
    }else if(paypattern ==2){
        return '一次性';
    }else {
        return "其他计费方式";
    }
});
/**
 * 定义基础的管理员状态
 */
template.helper('status',function (status,id) {
    if(status == 1 ){
        return '<a class="red" href="javascript:;" onclick="online('+id+',0,0)"><button class="btn btn-minier btn-yellow">在线</button></a>';
    }else{
        return '<a class="red" href="javascript:;" onclick="online('+id+',1,0)"><button class="btn btn-minier btn-danger">离线</button></a>'
    }
});


/**
 * 定义是否审核
 */
template.helper('audit',function (audit,id) {
    if(audit == '1'){
        return '<a class="red" href="javascript:;" onclick="online('+id+',0,1)"><button class="btn btn-minier btn-yellow">已审核</button></a>';
    }else{
        return '<a class="red" href="javascript:;" onclick="online('+id+',1,1)"><button class="btn btn-minier btn-danger">未审核</button></a>';
    }
});

/**
 * 定义数据的锁定状态
 */
template.helper('slock_staus',function (audit,id) {
    if(audit == 1){
        return '<span class="red">已锁定</span>';
    }else if(audit == 2){
        return '<span class="red" >已删除</span>';
    }else{
        return '<span class="green" >正常</span>';
    }
});


template.helper('yes_no',function (audit,id) {
    if(audit == '1'){
        return '<span class="green">是</span>';
    }else{
        return '<span class="red" >否</span>';
    }
});


template.helper('is_bind',function (audit,id) {
    if(audit == 'y'){
        return '<span class="green">已绑定</span>';
      
    }else{
        return '<span class="red" >未绑定</span>';
    }
});


template.helper('type_value',function (audit,id) {
    if(audit == '1'){
        return '<span >MP4</span>';
      
    }else if(audit == '2'){
        return '<span >m3u8</span>';
    }
});


template.helper('dateFormat', function (date, format) {
    if(!date) return;
    date = new Date(date);
    var map = {
        "M": date.getMonth() + 1, //月份
        "d": date.getDate(), //日
        "h": date.getHours(), //小时
        "m": date.getMinutes(), //分
        "s": date.getSeconds(), //秒
        "S": date.getMilliseconds() //毫秒
    };
    format = format.replace(/([yMdhmsS])+/g, function(all, t){
        var v = map[t];
        if(v !== undefined){
            if(all.length > 1){
                v = '0' + v;
                v = v.substr(v.length-2);
            }
            return v;
        }
        else if(t === 'y'){
            return (date.getFullYear() + '').substr(4 - all.length);
        }
        return all;
    });
    return format;
});

/*
template.helper('operate',function (id,type) {
	var html='<div class="action-buttons">';
    if(type ==3){
        html+=' <a class="btn btn-minier btn-success"  href="javascript:view('+id+')" title="查看"><i class="ace-icon fa fa-eye"></i>查看</a>'
        html+='   <a class="btn btn-minier btn-primary"  href="javascript:edit('+id+')" title="修改"><i class="ace-icon fa fa-pencil"></i>修改</a>'+
        '   <a class="btn btn-minier btn-pink" href="javascript:del('+id+')" title="删除"><i class="ace-icon fa fa-trash-o"></i>删除</a>';
    }else if(type ==2){
    	  html+='   <a class="btn btn-minier btn-primary"  href="javascript:edit('+id+')" title="修改"><i class="ace-icon fa fa-pencil"></i>修改</a>'+
          '   <a class="btn btn-minier btn-pink" href="javascript:del('+id+')" title="删除"><i class="ace-icon fa fa-trash-o"></i>删除</a>';
        html+=' <a class="btn btn-minier btn-danger" href="javascript:permission('+id+')" title="赋予权限"><i class="ace-icon fa fa-lock"></i>赋予权限</a>'
    }else if(type ==5){
        html='<div class="action-buttons">'+'<a class="btn btn-minier btn-pink" href="javascript:del('+id+')" title="删除"><i class="ace-icon fa fa-trash-o"></i>删除</a>';
    }else if(type ==6){
    	  html+='   <a class="btn btn-minier btn-primary"  href="javascript:edit('+id+')" title="修改"><i class="ace-icon fa fa-pencil"></i>修改</a>'+
          '   <a class="btn btn-minier btn-pink" href="javascript:del('+id+')" title="删除"><i class="ace-icon fa fa-trash-o"></i>删除</a>';
    	 html+='<a class="btn btn-minier btn-default" href="javascript:addAttrForAttrs('+id+')" title="绑定属性"><i class="ace-icon fa fa-bolt"></i>绑定属性</a>';
    }else if(type ==7){
	    html+=' <a class="btn btn-minier btn-default" href="javascript:bind('+id+')" title="绑定"><i class="ace-icon fa fa-bolt"></i>绑定</a>';
    }else{
        html+='   <a class="btn btn-minier btn-primary"  href="javascript:edit('+id+')" title="修改"><i class="ace-icon fa fa-pencil"></i>修改</a>'+
            '   <a class="btn btn-minier btn-pdangerink" href="javascript:del('+id+')" title="删除"><i class="ace-icon fa fa-trash-o"></i>删除</a>';
    }
    html=html+'</div>';
    return html;
});
*/
template.helper('operate',function (id) {
    var html='<a class="btn btn-minier btn-primary"  href="javascript:edit('+id+')" title="修改"><i class="ace-icon fa fa-pencil"></i>修改</a>'+
        '<a class="btn btn-minier btn-danger" href="javascript:del('+id+')" title="删除"><i class="ace-icon fa fa-trash-o"></i>删除</a>';
    return html;
});


/**
 * 根据传入的数据输出对应的按钮
 * 1.增加
 * 2.删除
 * 3.修改
 * 4.查看
 * 5.赋予权限
 */
template.helper('operate1',function (id,type) {
    var buttonType=type;
    var html='<div class="action-buttons">';
    var button=new Array();
    button[1]="";
    button[2]='<a class="btn btn-minier btn-danger" href="javascript:del('+id+')" title="删除"><i class="ace-icon fa fa-trash-o"></i>删除</a>';
    button[3]='<a class="btn btn-minier btn-primary"  href="javascript:edit('+id+')" title="修改"><i class="ace-icon fa fa-pencil"></i>修改</a>';
    button[4]='<a class="btn btn-minier btn-success"  href="javascript:show('+id+')" title="查看"><i class="ace-icon fa fa-eye"></i>查看</a>';
    button[5]='<a class="btn btn-minier btn-danger" href="javascript:permission('+id+')" title="赋予权限"><i class="ace-icon fa fa-lock"></i>赋予权限</a>'
    for(var i=0;i<type.length;i++){
        html+=button[buttonType.charAt(i)];
    }

    html=html+'</div>';
    return html;
});


template.helper('operatediffer',function (id,value) {
		 var html='<div class="action-buttons">';
		    if(value=='y'){
		    	html+='<a class="btn btn-minier btn-pink" href="javascript:cancelBind('+id+')" title="解绑"><i class="ace-icon fa fa-trash-o"></i>解绑</a>';
		    }else{
		    	html+='<a class="btn btn-minier btn-default" href="javascript:bind('+id+')" title="绑定"><i class="ace-icon fa fa-bolt"></i>绑定</a>';
		    }
		    html=html+'</div>';
    return html;
});


/**
 * 展示排序的编号
 */
template.helper('order',function (id) {
    var html ='<a href="#" onclick="order('+id+',1);"><i class="blue ace-icon fa fa-arrow-circle-up bigger-200"></i></a>&nbsp;&nbsp;';
        html +='<a href="#" onclick="order('+id+',2);"><i class="blue ace-icon  fa fa-arrow-circle-down bigger-200"></i></a>';
    return html;
});
/**
 * 仅是查看
 */
template.helper('show',function (id){
    return '<a class="btn btn-minier btn-success"  href="javascript:show('+id+')" title="查看"><i class="ace-icon fa fa-eye"></i>查看</a>';
});