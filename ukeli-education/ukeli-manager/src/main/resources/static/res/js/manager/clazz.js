var clazzTypeTemplate ='{{each list as clazztype index}}'+
    '<tr>'+
    '   <td class="hidden-xs center" height="28">'+
    '     <input class="i-checks"  name="clazztype" type="radio" id="{{clazztype.id}}">'+
    '   </td>'+
    '   <td class="center">{{ clazztype.id}}</td>'+
    '   <td class="center">{{ clazztype.name}}</td>'+
    '   <td class="center">{{#status(clazztype.status,clazztype.id)}}</td>'+
    '   <td class="center">{{#order(clazztype.id)}}</td>'+
    '   <td>' +
    '       <div class="action-buttons">' +
    '           {{#show(clazztype.id)}}{{#operate(clazztype.id)}}' +
    '           <a class="btn btn-minier btn-info" href="javascript:addChildByType({{clazztype.id}})" title="添加子类"><i class=" fa fa-lock"></i>添加子类</a>'+
    '       </div>' +
    '   </td>'+
    '</tr>'+
    '{{/each}}'+
    '<tr>'+
    '   <td  colspan="6" id="pager">'+
    '       <div class="fl" style="padding-top:7px"></div>'+
    '       <div align="left" class="fr">'+
    '           <ul class="pagination"></ul>'+
    '       </div>'+
    '   </td>'+
    '</tr>';

$(function () {
    $("#addtypebtn").on('click',addclazzType);
    $("#edittypebtn").on('click',editClazzType);
    Eymjs.page.init();
    search();

});

/**
 * 搜索
 */
function search() {
	page(1);
}

function page(page){
    Eymjs.page.show(Eymjs.ukeli.url.clazzttype.LIST,{page:page},clazzTypeTemplate,function () {
        $('#contentTable tbody tr td input.i-checks').on('ifChecked', function(event){      //ifCreated 事件应该在插件初始化之前绑定
            var id =$(this).attr("id");
            initClazzTree(id);
        });
    });
}

/**
 * 增加一个管理员
 */
function addclazzType() {
    Eymjs.dialog.showUrl('分类类别增加',Eymjs.ukeli.url.clazzttype.EDIT,{},400,250);
}
/**
 * 修改一条记录
 * @param id
 */
function edit(id) {
    Eymjs.dialog.showUrl('修改分类类别',Eymjs.ukeli.url.clazzttype.EDIT,{id:id},400,250);
}
/**
 * 修改按钮
 */
function editClazzType() {
    var id= Eymjs.page.selectOne("contentTable");
    if(id){edit(id);}
}
/**
 * 删除一个记录
 * @param id
 */
function del(id) {
    Eymjs.page.delByids(id,Eymjs.ukeli.url.clazzttype.DELETE,"您确定要删除该分类类别吗?")
}
/**
 * 调整排序
 * @param id
 * @param type
 */
function order(id,type) {
    Eymjs.page.online(Eymjs.ukeli.url.clazzttype.ORDER,{id:id,type:type},'您确定要调整排序吗？')
}
/**
 * 上线或者下线一条记录,或者锁定以及一个管理员
 * @param id
 * @param status
 */
function online(id,status) {
    if(!id){return;}
    var msg = "您确定要下线该分类类别吗？";
    if(status==1){
        msg = "您确定要上线该分类类别吗？";
    }
    Eymjs.page.online(Eymjs.ukeli.url.clazzttype.STATUS,{id:id,status:status},msg)
}

function addChildByType(typeid) {
    Eymjs.dialog.showUrl('分类添加',Eymjs.ukeli.url.clazz.EDIT,{typeid:typeid},400,300);
}

/**
 * 初始化展示树
 * @param typeid
 */
function initClazzTree(typeid){
    document.getElementById('widget_tree').innerHTML = '';
    Eymjs.data.ajax(Eymjs.ukeli.url.clazz.TREETABLE,{clazztypeid:typeid},function (data) {
        document.getElementById('widget_tree').innerHTML = data;
        $("#treeTable").treeTable({expandLevel :2 ,column:1}).show();
    },true,Eymjs.common.http.POST,Eymjs.common.dataType.HTML);
}

function addChild(id) {
    Eymjs.dialog.showUrl('分类添加',Eymjs.ukeli.url.clazz.EDIT,{parentid:id},400,300);
}


function editChild(id) {
    Eymjs.dialog.showUrl('分类修改',Eymjs.ukeli.url.clazz.EDIT,{id:id},400,300);
}

function onlineChild(id,status) {
    if(!id){return;}
    var msg = "您确定要下线该分类吗？";
    if(status==1){
        msg = "您确定要上线该分类吗？";
    }
    Eymjs.page.onlineCallBack(Eymjs.ukeli.url.clazz.STATUS,{id:id,status:status},msg,function (data) {
        if(data.success){
            layer.closeAll();
            initClazzTree(data.input);
        }else{
            layer.closeAll();
            Eymjs.toastr.error(data.errorinfo);
            return false;
        }
    })
}

function delClazz(id,clazztypeid) {
    Eymjs.page.delByidsCallBack(id,Eymjs.ukeli.url.clazz.DELETE,"您确定要删除该分类吗?",function (data) {
        if(data.success){
            layer.closeAll();
            initClazzTree(clazztypeid);
        }else{
            layer.closeAll();
            Eymjs.toastr.error(data.errorinfo);
            return false;
        }
    })
}

function orderClazz(id,type) {
    Eymjs.page.onlineCallBack(Eymjs.ukeli.url.clazz.ORDER,{id:id,type:type},'您确定要调整排序吗？',function (data) {
        if(data.success){
            layer.closeAll();
            initClazzTree(clazztypeid);
        }else{
            layer.closeAll();
            Eymjs.toastr.error(data.errorinfo);
            return false;
        }
    })
    
}