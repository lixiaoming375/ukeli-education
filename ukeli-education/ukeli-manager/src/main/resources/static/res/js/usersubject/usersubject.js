
var usersubjectTemplate ='{{each list as usersubject index}}'+
    '<tr>'+
    '   <td class="hidden-xs center" height="28">'+
    '       <label class="pos-rel">'+
    '       		<input class="i-checks" type="checkbox" id="{{ usersubject.id}}">'+
    '       </label>'+
    '   </td>'+
    '   <td class="center">{{ usersubject.id}}</td>'+
    '   <td>{{ usersubject.userid}}</td>'+
    '   <td>{{ usersubject.ext_username}}</td>'+
    '   <td>{{ usersubject.ext_nickname}}</td>'+
    '   <td>{{ usersubject.subjectid}}</td>'+
    '   <td>{{ usersubject.ext_subjectname}}</td>'+
    '   <td>{{#isdone(usersubject.isdone)}}</td>'+
    '   <td>{{#isadd(usersubject.isadd)}}</td>'+
    '   <td class="hidden-xs">{{#status(usersubject.status,usersubject.id)}}</td>'+
    '   <td class="hidden-xs">{{dateFormat(usersubject.edittime,\'yyyy-MM-dd hh:mm:ss\')}}</td>'+
	    '<td>'+
	    '  <div class="action-buttons">' +
	    '           {{#show(usersubject.id)}}' +
	    '      <a class="btn btn-minier btn-danger" href="javascript:del({{ usersubject.id}})" title="删除"><i class="ace-icon fa fa-trash-o"></i>删除</a>'+
	    '  </div>' +
	    '</td>'+
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
    $("#addbtn").on('click',addusersubject);
    $("#editbtn").on('click',editusersubject);
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
    $("#nameForSearch").val('');
	$("#isdoneForSearch").val('');
	$("#isaddForSearch").val('');
	$("#statusForSearch").val('');
    search();
}

function page(page){
	var username = $("#usernameForSearch").val();
	var subjectname = $("#nameForSearch").val();
	var isdone = $("#isdoneForSearch").val();
	var isadd = $("#isaddForSearch").val();
	var status = $("#statusForSearch").val();
    Eymjs.page.show(Eymjs.ukeli.url.usersubject.LIST,{username:username,subjectname:subjectname,isdone:isdone,isadd:isadd,status:status,page:page},usersubjectTemplate);
}

/**
 * 增加一个管理员
 */
function addusersubject() {
    Eymjs.dialog.showUrl('增加',Eymjs.ukeli.url.usersubject.EDIT,{},500,400);
}
/**
 * 修改一条记录
 * @param id
 */
function edit(id) {
    Eymjs.dialog.showUrl('修改',Eymjs.ukeli.url.usersubject.EDIT,{id:id},500,400);
}
/**
 * 修改按钮
 */
function editusersubject() {
    var id= Eymjs.page.selectOne("contentTable");
    if(id){edit(id);}
}
/**
 * 选择多个，删除
 */
function delAll() {
    var ids= Eymjs.page.selectList("contentTable");
    if(ids){
        Eymjs.page.delByids(ids,Eymjs.ukeli.url.usersubject.DELETE,"您确定要删除您选择的吗")
    }
}

/**
 * 删除一个记录
 * @param id
 */
function del(id) {

    Eymjs.page.delByids(id,Eymjs.ukeli.url.usersubject.DELETE,"您确定要删除该吗?")
}

/**
 * 查看
 * @returns
 */
function show(id){
	 Eymjs.dialog.showUrl('查看',Eymjs.ukeli.url.usersubject.SHOW,{id:id},700,500);
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

    Eymjs.page.online(Eymjs.ukeli.url.usersubject.STATUS,{id:id,status:status,type:type},msg)
}