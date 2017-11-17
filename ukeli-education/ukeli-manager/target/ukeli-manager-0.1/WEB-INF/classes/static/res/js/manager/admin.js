
var adminTemplate ='{{each list as admin index}}'+
    '<tr>'+
    '   <td class="center">'+
    '       <input class="i-checks" type="checkbox" id="{{ admin.id}}">'+
    '   </td>'+
    '   <td class="center" height="28" >{{ admin.id}}</td>'+
    '   <td>{{ admin.username}}</td>'+
    '   <td>{{ admin.realname}}</td>'+
    '   <td>{{ admin.sex | sex}}</td>'+
    '   <td>{{ admin.telephone}}</td>'+
    '   <td>{{ admin.mobile}}</td>'+
    '   <td>{{ admin.ext_rolename}}</td>'+
    '   <td class="hidden-sm hidden-xs">{{ admin.logintimes}}</td>'+
    '   <td class="hidden-sm hidden-xs">{{dateFormat(admin.lastlogintime,\'yyyy-MM-dd hh:mm:ss\')}}</td>'+
    '   <td class="hidden-sm hidden-xs">{{ admin.lastloginip}}</td>'+
    '   <td class="center">{{#status(admin.status,admin.id)}}</td>'+
    '   <td class="center">{{#audit(admin.auditstatus,admin.id)}}</td>'+
    '   <td class="center">{{#order(admin.id)}}</td>'+
    '   <td class="center">' +
    '       <div class="action-buttons">' +
    '           {{#show(admin.id)}}{{#operate(admin.id)}}' +
    '       </div>' +
    '   </td>'+
    '</tr>'+
    '{{/each}}'+
    '<tr>'+
    '   <td colspan="15" id="pager">'+
    '       <div class="fl" style="padding-top:7px"></div>'+
    '       <div align="left" class="fr">'+
    '           <ul class="pagination"></ul>'+
    '       </div>'+
    '   </td>'+
    '</tr>';

$(function () {
    $("#searchbtn").on('click',search);
    $("#researchbtn").on('click',reSearch);
    $("#addbtn").on('click',addAdmin);
    $("#editbtn").on('click',editAdmin);
    $("#delbtn").on('click',delAll);
    $("#passwordbtn").on('click',renewpass);
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
    $("#keyword").val('');
    search();
}

function page(page) {
    var keyword = $("#keyword").val();
    Eymjs.page.show(Eymjs.ukeli.url.admin.LIST,{keyword:keyword,page:page},adminTemplate);
}
/**
 * 增加一个管理员
 */
function addAdmin() {
    Eymjs.dialog.showUrl('管理员增加',Eymjs.ukeli.url.admin.EDIT,{},700,530)
}
/**
 * 修改一条记录
 * @param id
 */
function edit(id) {
    Eymjs.dialog.showUrl('修改管理员',Eymjs.ukeli.url.admin.EDIT,{id:id},700,530);
}
/**
 * 修改密码
 * @param id
 */
function password(id) {
    Eymjs.dialog.showUrl('修改管理员',Eymjs.ukeli.url.admin.PASSWORD,{id:id},400,340);
}

/**
 * 修改按钮
 */
function editAdmin() {
    var id= Eymjs.page.selectOne("contentTable");
    if(id){edit(id);}
}

function renewpass() {
    var id= Eymjs.page.selectOne("contentTable");
    if(id){password(id);}
}

/**
 * 选择多个，删除
 */
function delAll() {
    var ids= Eymjs.page.selectList("contentTable");
    if(ids){
        Eymjs.page.delByids(ids,Eymjs.ukeli.url.admin.DELETE,"您确定要删除您选择的管理员吗")
    }
}

/**
 * 删除一个记录
 * @param id
 */
function del(id) {
    Eymjs.page.delByids(id,Eymjs.ukeli.url.admin.DELETE,"您确定要删除该管理员吗?")
}

/**
 * 上线或者下线一条记录,或者锁定以及一个管理员
 * @param id
 * @param status
 */
function online(id,status,type) {
    if(!id){return;}
    var msg = "您确定要下线该管理员吗？";
    if(status==1){
        msg = "您确定要上线该管理员吗？";
    }
    if(type ==1){
        msg = "您确定要审核不通过该管理员吗？";
        if(status==1){
            msg = "您确定要审核通过该管理员吗？";
        }
    }
    Eymjs.page.online(Eymjs.ukeli.url.admin.STATUS,{id:id,status:status,type:type},msg)
}

/**
 * 调整排序
 * @param id
 * @param type
 */
function order(id,type) {
    Eymjs.page.online(Eymjs.ukeli.url.admin.ORDER,{id:id,type:type},'您确定要调整排序吗？')
}

/**
 * 展示用户信息
 * @param id
 */
function show(id) {
    Eymjs.dialog.showUrl('管理员查看',Eymjs.ukeli.url.admin.SHOW,{id:id},900,500);
}