
$(document).ready(function() {
    Eymjs.form.checkInit();
    var tableTree =  $("#contentTable").treeTable({expandLevel :2 ,column:1}).show();
    $("#addbtn").on('click',add);
    $("#editbtn").on('click',editPermission);
    //$("#delbtn").on('click',delAll);
    Eymjs.page.init();
});

/**
 * 增加一个管理员
 */
function add() {
    Eymjs.dialog.showUrl('增加权限',Eymjs.ukeli.url.permission.EDIT,{},600,600);
}
/**
 * 修改一个角色
 */
function editPermission() {
    var id= Eymjs.page.selectOne("contentTable");
    if(id){edit(id);}
}

/**
 * 修改一条记录
 * @param id
 */
function edit(id) {
    Eymjs.dialog.showUrl('修改权限',Eymjs.ukeli.url.permission.EDIT,{id:id},600,600);
}
/*
function  delAll() {
    var ids= Eymjs.page.selectList("contentTable");
    if(ids){
        Eymjs.page.delByids(ids,Eymjs.ukeli.url.permission.DELETE,"您确定要删除您选择的权限吗")
    }
}
*/

/**
 * 删除一个记录
 * @param id
 */
function del(id) {
    Eymjs.page.delByids(id,Eymjs.ukeli.url.permission.DELETE,"您确定要删除该权限吗?",function (data) {
        window.location.reload();
    });
}
/**
 * 上线或者下线一条记录
 * @param id
 * @param status
 */
function online(id,status,type) {
    if(!id){return;}
    var msg = "您确定要下线权限吗？";
    if(status==1){
        msg = "您确定要上线该权限吗？";
    }
    if(type ==1){
        msg = "您确定要隐藏该权限吗？";
        if(status==1){
            msg = "您确定要显示该权限吗？";
        }
    }
    Eymjs.page.online(Eymjs.ukeli.url.permission.STATUS,{id:id,status:status,type:type},msg,function (data) {
        window.location.reload();
    });
}

/**
 * 调整排序
 * @param id
 * @param type
 */
function order(id,type) {
    Eymjs.page.online(Eymjs.ukeli.url.permission.ORDER,{id:id,type:type},'您确定要调整排序吗？',function (data) {
        window.location.reload();
    });
}