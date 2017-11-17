
var userexamTemplate ='{{each list as userexam index}}'+
    '<tr>'+
    '   <td class="hidden-xs center" height="28">'+
    '       <label class="pos-rel">'+
    '       		<input class="i-checks" type="checkbox" id="{{ userexam.id}}">'+
    '       </label>'+
    '   </td>'+
    '   <td class="center">{{ userexam.id}}</td>'+
    '   <td>{{ userexam.userid}}</td>'+
    '   <td>{{ userexam.ext_userName}}</td>'+
    '   <td>{{ userexam.examid}}</td>'+
    '   <td>{{ userexam.ext_examName}}</td>'+
    '   <td>{{ userexam.usetime}}</td>'+
    '   <td>{{ userexam.score}}</td>'+
    '   <td>{{ userexam.vtime}}</td>'+
    '   <td class="hidden-xs">{{#status(userexam.status,userexam.id)}}</td>'+
    '   <td class="hidden-xs">{{dateFormat(userexam.edittime,\'yyyy-MM-dd hh:mm:ss\')}}</td>'+
   // '   <td>{{#operate(userexam.id)}}</td>'+
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

$(function () {
    $("#searchbtn").on('click',search);
    $("#researchbtn").on('click',reSearch);
    $("#addbtn").on('click',adduserexam);
    $("#editbtn").on('click',edituserexam);
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
    $("#username").val('');
    $("#name").val('');
    search();
}

function page(page){
    var username = $("#username").val();
    var name = $("#name").val();
    Eymjs.page.show(Eymjs.ukeli.url.userexam.LIST,{username:username,name:name,page:page},userexamTemplate);
}

/**
 * 增加一个管理员
 */
function adduserexam() {
    Eymjs.dialog.showUrl('增加',Eymjs.ukeli.url.userexam.EDIT,{},500,400);
}
/**
 * 修改一条记录
 * @param id
 */
function edit(id) {
    Eymjs.dialog.showUrl('修改',Eymjs.ukeli.url.userexam.EDIT,{id:id},500,400);
}
/**
 * 修改按钮
 */
function edituserexam() {
    var id= Eymjs.page.selectOne("contentTable");
    if(id){edit(id);}
}
/**
 * 选择多个，删除
 */
function delAll() {
    var ids= Eymjs.page.selectList("contentTable");
    if(ids){
        Eymjs.page.delByids(ids,Eymjs.ukeli.url.userexam.DELETE,"您确定要删除您选择的吗")
    }
}

/**
 * 删除一个记录
 * @param id
 */
function del(id) {

    Eymjs.page.delByids(id,Eymjs.ukeli.url.userexam.DELETE,"您确定要删除该吗?")
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

    Eymjs.page.online(Eymjs.ukeli.url.userexam.STATUS,{id:id,status:status,type:type},msg)
}