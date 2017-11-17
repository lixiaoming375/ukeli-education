
var knowledgetypeTemplate ='{{each list as knowledgetype index}}'+
    '<tr>'+
    '   <td class="hidden-xs center" height="28">'+
    '       <label class="pos-rel">'+
    '       		<input class="i-checks" type="checkbox" id="{{ knowledgetype.id}}">'+
    '       </label>'+
    '   </td>'+
    '   <td>{{ knowledgetype.typename}}</td>'+
    '   <td class="hidden-xs">{{#slock_staus(knowledgetype.slock,knowledgetype.id)}}</td>'+
    '   <td class="hidden-xs">{{dateFormat(knowledgetype.edittime,\'yyyy-MM-dd hh:mm:ss\')}}</td>'+
    '   <td>'+
    '   <a class="btn btn-minier btn-primary"  href="javascript:edit({{ knowledgetype.id}})" title="修改"><i class="ace-icon fa fa-pencil"></i>查看修改</a>'+
    '   <a class="btn btn-minier btn-pink" href="javascript:del({{ knowledgetype.id}})" title="删除"><i class="ace-icon fa fa-trash-o"></i>删除</a>'+
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

$(function () {
    $("#searchbtn").on('click',search);
    $("#researchbtn").on('click',reSearch);
    $("#addbtn").on('click',addknowledgetype);
    $("#editbtn").on('click',editknowledgetype);
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

function page(page){
    var keyword = $("#keyword").val();
    Eymjs.page.show(Eymjs.ukeli.url.knowledgetype.LIST,{keyword:keyword,page:page},knowledgetypeTemplate);
}

/**
 * 增加一个管理员
 */
function addknowledgetype() {
    Eymjs.dialog.showUrl('知识点类型增加',Eymjs.ukeli.url.knowledgetype.EDIT,{},500,400);
}
/**
 * 修改一条记录
 * @param id
 */
function edit(id) {
    Eymjs.dialog.showUrl('修改知识点类型',Eymjs.ukeli.url.knowledgetype.EDIT,{id:id},500,400);
}
/**
 * 修改按钮
 */
function editknowledgetype() {
    var id= Eymjs.page.selectOne("contentTable");
    if(id){edit(id);}
}
/**
 * 选择多个，删除
 */
function delAll() {
    var ids= Eymjs.page.selectList("contentTable");
    if(ids){
        Eymjs.page.delByids(ids,Eymjs.ukeli.url.knowledgetype.DELETE,"您确定要删除您选择的知识点类型吗")
    }
}

/**
 * 删除一个记录
 * @param id
 */
function del(id) {

    Eymjs.page.delByids(id,Eymjs.ukeli.url.knowledgetype.DELETE,"您确定要删除该知识点类型吗?")
}

/**
 * 上线或者下线一条记录,或者锁定以及一个知识点类型
 * @param id
 * @param status
 */
function online(id,status,type) {
    if(!id){return;}
    var msg = "您确定要下线该知识点类型吗？";
    if(status==1){
        msg = "您确定要上线该知识点类型吗？";
    }
    if(type==1){
        msg = "您确定要审核不通过该知识点类型吗？";
        if(status==1){
            msg = "您确定要审核通过该知识点类型吗？";
        }
    }

    Eymjs.page.online(Eymjs.ukeli.url.knowledgetype.STATUS,{id:id,status:status,type:type},msg)
}