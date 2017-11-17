
var usersubjectwrongTemplate ='{{each list as usersubjectwrong index}}'+
    '<tr>'+
    '   <td class="hidden-xs center" height="28">'+
    '       <label class="pos-rel">'+
    '       		<input class="i-checks" type="checkbox" id="{{ usersubjectwrong.id}}">'+
    '       </label>'+
    '   </td>'+
    '   <td class="center">{{ usersubjectwrong.id}}</td>'+
    '   <td>{{ usersubjectwrong.userid}}</td>'+
    '   <td>{{ usersubjectwrong.ext_userName}}</td>'+
    '   <td>{{ usersubjectwrong.subjectid}}</td>'+
    '   <td>{{ usersubjectwrong.ext_subjectName}}</td>'+
    '   <td>{{#vtype(usersubjectwrong.vtype)}}</td>'+
    '   <td>{{ #ismod(usersubjectwrong.ismod)}}</td>'+
    '   <td class="hidden-xs">{{#status(usersubjectwrong.status,usersubjectwrong.id)}}</td>'+
    '   <td class="hidden-xs">{{dateFormat(usersubjectwrong.edittime,\'yyyy-MM-dd hh:mm:ss\')}}</td>'+
   // '   <td>{{#operate(usersubjectwrong.id)}}</td>'+
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

//'题目类别|1|1|1|1 例题 2 课堂联系 3 自测卷  4 杯赛试题  5 考试试题',
template.helper('vtype',function (vtype) {
    if(vtype =='1'){
        return '例题';
    }else if(vtype =='2'){
        return '课堂联系';
    }else if(vtype =='3'){
        return "自测卷";
    }else if(vtype =='4'){
        return '杯赛试题';
    }else if(vtype =='5'){
        return "自考试试题";
    }
});

template.helper('ismod',function (ismod) {
    if(ismod =='0'){
        return '否';
    }else if(ismod =='1'){
        return '是';
        }
});

$(function () {
    $("#searchbtn").on('click',search);
    $("#researchbtn").on('click',reSearch);
    $("#addbtn").on('click',addusersubjectwrong);
    $("#editbtn").on('click',editusersubjectwrong);
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
	$("#statusForSearch").val('');
    search();
}

function page(page){
	var username = $("#usernameForSearch").val();
	var name = $("#nameForSearch").val();
	var status = $("#statusForSearch").val();
    Eymjs.page.show(Eymjs.ukeli.url.usersubjectwrong.LIST,{username:username,name:name,status:status,page:page},usersubjectwrongTemplate);
}

/**
 * 增加一个管理员
 */
function addusersubjectwrong() {
    Eymjs.dialog.showUrl('增加',Eymjs.ukeli.url.usersubjectwrong.EDIT,{},500,400);
}
/**
 * 修改一条记录
 * @param id
 */
function edit(id) {
    Eymjs.dialog.showUrl('修改',Eymjs.ukeli.url.usersubjectwrong.EDIT,{id:id},500,400);
}
/**
 * 修改按钮
 */
function editusersubjectwrong() {
    var id= Eymjs.page.selectOne("contentTable");
    if(id){edit(id);}
}
/**
 * 选择多个，删除
 */
function delAll() {
    var ids= Eymjs.page.selectList("contentTable");
    if(ids){
        Eymjs.page.delByids(ids,Eymjs.ukeli.url.usersubjectwrong.DELETE,"您确定要删除您选择的吗")
    }
}

/**
 * 删除一个记录
 * @param id
 */
function del(id) {

    Eymjs.page.delByids(id,Eymjs.ukeli.url.usersubjectwrong.DELETE,"您确定要删除该吗?")
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

    Eymjs.page.online(Eymjs.ukeli.url.usersubjectwrong.STATUS,{id:id,status:status,type:type},msg)
}