
var roleTemplate ='{{each list as role index}}'+
    '<tr>'+
    '   <td class="center" height="28">'+
    '       <input class="i-checks" type="checkbox" id="{{ role.id}}">'+
    '   </td>'+
    '   <td class="center">{{ role.id}}</td>'+
    '   <td>{{ role.role}}</td>'+
    '   <td class="hidden-xs">{{dateFormat(role.edittime,\'yyyy-MM-dd hh:mm:ss\')}}</td>'+
    '   <td class="center">{{#status(role.status,role.id)}}</td>'+
    '   <td class="center">{{#order(role.id)}}</td>'+
    '   <td class="center">' +
    '       <div class="action-buttons">' +
    '           {{#show(id)}}{{#operate(role.id)}}' +
    '           <a class="btn btn-minier btn-info" href="javascript:permission({{role.id}})" title="赋予权限"><i class="ace-icon fa fa-lock"></i>赋予权限</a>'+
    '       </div>' +
    '   </td>'+
    '</tr>'+
    '{{/each}}'+
    '<tr>'+
    '   <td  colspan="7" id="pager">'+
    '       <div class="fl" style="padding-top:7px"></div>'+
    '       <div align="left" class="fr">'+
    '           <ul class="pagination"></ul>'+
    '       </div>'+
    '   </td>'+
    '</tr>';

$(function () {
    $("#searchbtn").on('click',search);
    $("#researchbtn").on('click',reSearch);
    $("#addbtn").on('click',addrole);
    $("#editbtn").on('click',editRole);
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
    $("#keyword").val('');
    search();
}

function page(page) {
    var keyword = $("#keyword").val();
    Eymjs.page.show(Eymjs.ukeli.url.role.LIST,{keyword:keyword,page:page},roleTemplate);
}

/**
 * 增加一个管理员
 */
function addrole() {
    Eymjs.dialog.showUrl('角色增加',Eymjs.ukeli.url.role.EDIT,{},500,200);
}
/**
 * 修改角色
 * @param id
 */
function edit(id) {
    Eymjs.dialog.showUrl('角色修改',Eymjs.ukeli.url.role.EDIT,{id:id},500,200);
}
/**
 * 修改按钮
 */
function editRole() {
    var id= Eymjs.page.selectOne("contentTable");
    if(id){edit(id);}
}

/**
 * 删除一个记录
 * @param id
 */
function del(id) {
    Eymjs.page.delByids(id,Eymjs.ukeli.url.role.DELETE,"您确定要删除该角色吗?")
}


function  delAll() {
    var ids= Eymjs.page.selectList("contentTable");
    if(ids){
        Eymjs.page.delByids(ids,Eymjs.ukeli.url.role.DELETE,"您确定要删除您选择的角色吗")
    }
}

/**
 * 上线或者下线一条记录
 * @param id
 * @param status
 */
function online(id,status) {
    if(!id){return;}
    var msg = "您确定要下线该角色吗？";
    if(status==1){
        msg = "您确定要上线该角色吗？";
    }
    Eymjs.page.online(Eymjs.ukeli.url.role.STATUS,{id:id,status:status},msg)
}
/**
 * 调整排序
 * @param id
 * @param type
 */
function order(id,type) {
    Eymjs.page.online(Eymjs.ukeli.url.role.ORDER,{id:id,type:type},'您确定要调整排序吗？')
}
/**
 * 选择或者赋予权限
 * @param roleid
 */
function permission(roleid) {
    layer.open({
        type: 2,
        area: ["250px","500px"],
        skin: 'layui-layer-rim',
        title: "选择权限",
        shadeClose: true,
        content:adminPath+"/tree.html?path="+encodeURIComponent("manager/permission")+"&type=1&params="+encodeURIComponent("{roleid:"+roleid+",time:"+new Date().getTime()+"}"),
        btn: ['确定', '关闭'],
        yes: function(index, layero){ //或者使用btn1
            var tree = layero.find("iframe")[0].contentWindow.tree;
            var ids = [];
            var nodes = tree.getCheckedNodes(true);

            for(var i=0; i<nodes.length; i++) {
                if(nodes[i].id > 0){
                    ids.push(nodes[i].id);
                }
            }
            var permissionids = ids.join(",").replace(/u_/ig,"");
            Eymjs.data.ajax(Eymjs.ukeli.url.role.PERMISSION,{roleid:roleid,permissionids:permissionids},function (data) {
                if (data.success) {
                    layer.closeAll();
                    Eymjs.toastr.success('恭喜您!赋予权限成功');
                    search();
                } else {
                    Eymjs.message.error(data.errorinfo);
                    return false;
                }
            },true);
        },
        cancel: function(index){}
    });
}
/**
 * 展示用户信息
 * @param id
 */
function show(id) {
    Eymjs.dialog.showUrl('角色',Eymjs.ukeli.url.role.SHOW,{id:id},300,300);
}
