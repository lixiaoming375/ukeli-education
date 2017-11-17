
var userknowledgeTemplate ='{{each list as userknowledge index}}'+
    '<tr>'+
    '   <td class="hidden-xs center" height="28">'+
    '       <label class="pos-rel">'+
    '       		<input class="i-checks" type="checkbox" id="{{ userknowledge.id}}">'+
    '       </label>'+
    '   </td>'+
    '   <td class="center">{{ userknowledge.id}}</td>'+
    '   <td>{{ userknowledge.userid}}</td>'+
    '   <td>{{ userknowledge.ext_username}}</td>'+
    '   <td>{{ userknowledge.ext_nickname}}</td>'+
    '   <td>{{ userknowledge.knowledgeid}}</td>'+
    '   <td>{{ userknowledge.ext_nodename}}</td>'+
    '   <td>{{#isdone(userknowledge.isdone)}}</td>'+
    '   <td>{{#isadd(userknowledge.isadd)}}</td>'+
    '   <td class="hidden-xs">{{#status(userknowledge.status)}}</td>'+
    '   <td class="hidden-xs">{{dateFormat(userknowledge.edittime,\'yyyy-MM-dd hh:mm:ss\')}}</td>'+
    '   <td>'+
    '  <div class="action-buttons">' +
    '           {{#show(userknowledge.id)}}' +
    '      <a class="btn btn-minier btn-danger" href="javascript:del({{ userknowledge.id}})" title="删除"><i class="ace-icon fa fa-trash-o"></i>删除</a>'+
    '  </div>' +
    '   </td>'+
    '</tr>'+
    '{{/each}}'+
    '<tr>'+
    '    <td height="50"></td>'+
    '   <td  colspan="12" id="pager">'+
    '       <div class="fl" style="padding-top:7px"></div>'+
    '       <div align="left" class="fr">'+
    '           <ul class="pagination"></ul>'+
    '       </div>'+
    '   </td>'+
    '</tr>';

template.helper('isdone',function (isdone) {
    if(isdone =='0'){
        return '未完成';
    }else if(isdone =='1'){
        return '已完成';
    }else{
        return "未知";
    }
});

template.helper('isadd',function (isadd) {
    if(isadd =='0'){
        return '未加入';
    }else if(isadd =='1'){
        return '已加入';
    }else{
        return "未知";
    }
});

$(function () {
    $("#searchbtn").on('click',search);
    $("#researchbtn").on('click',reSearch);
    $("#addbtn").on('click',adduserknowledge);
    $("#editbtn").on('click',edituserknowledge);
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
    $("#nodenameForSearch").val('');
	$("#isdoneForSearch").val('');
	$("#isaddForSearch").val('');
	$("#statusForSearch").val('');
    search();
}

function page(page){
    var username = $("#usernameForSearch").val();
	var nodename = $("#nodenameForSearch").val();
	var isdone = $("#isdoneForSearch").val();
	var isadd = $("#isaddForSearch").val();
	var status = $("#statusForSearch").val();
    Eymjs.page.show(Eymjs.ukeli.url.userknowledge.LIST,{username:username,nodename:nodename,isdone:isdone,isadd:isadd,status:status,page:page},userknowledgeTemplate);
}

/**
 * 增加一个管理员
 */
function adduserknowledge() {
    Eymjs.dialog.showUrl('增加',Eymjs.ukeli.url.userknowledge.EDIT,{},500,400);
}
/**
 * 修改一条记录
 * @param id
 */
function edit(id) {
    Eymjs.dialog.showUrl('修改',Eymjs.ukeli.url.userknowledge.EDIT,{id:id},500,400);
}
/**
 * 修改按钮
 */
function edituserknowledge() {
    var id= Eymjs.page.selectOne("contentTable");
    if(id){edit(id);}
}
/**
 * 选择多个，删除
 */
function delAll() {
    var ids= Eymjs.page.selectList("contentTable");
    if(ids){
        Eymjs.page.delByids(ids,Eymjs.ukeli.url.userknowledge.DELETE,"您确定要删除您选择的吗")
    }
}

/**
 * 删除一个记录
 * @param id
 */
function del(id) {

    Eymjs.page.delByids(id,Eymjs.ukeli.url.userknowledge.DELETE,"您确定要删除该吗?")
}

/**
 * 查看
 * @returns
 */
function show(id){
	 Eymjs.dialog.showUrl('查看',Eymjs.ukeli.url.userknowledge.SHOW,{id:id},700,500);
}

/**
 * 上线或者下线一条记录,或者锁定以及一个
 * @param id
 * @param status
 */
function online(id,status,type) {
    if(!id){return;}
    var msg = "您确定要下线该吗？";
    if(status==1){
        msg = "您确定要上线该吗？";
    }
    if(type==1){
        msg = "您确定要审核不通过该吗？";
        if(status==1){
            msg = "您确定要审核通过该吗？";
        }
    }

    Eymjs.page.online(Eymjs.ukeli.url.userknowledge.STATUS,{id:id,status:status,type:type},msg)
}