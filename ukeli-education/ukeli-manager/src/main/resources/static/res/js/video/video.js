
var videoTemplate ='{{each list as video index}}'+
    '<tr>'+
    '   <td class="hidden-xs center" height="28">'+
    '       <label class="pos-rel">'+
    '       		<input class="i-checks" type="checkbox" id="{{ video.id}}">'+
    '       </label>'+
    '   </td>'+
    '   <td>{{ video.videoname}}</td>'+
    '   <td>{{ video.videotype}}</td>'+
    '   <td>{{ video.videotime}}</td>'+
   // '   <td class="hidden-xs">{{#status_admin(video.status,video.id)}}</td>'+
    '   <td class="hidden-xs">{{#slock_staus(video.slock,video.id)}}</td>'+
    '   <td class="hidden-xs">{{dateFormat(video.edittime,\'yyyy-MM-dd hh:mm:ss\')}}</td>'+
    '   <td>'+
    '   <a class="btn btn-minier btn-primary"  href="javascript:edit({{ video.id}})" title="查看修改"><i class="ace-icon fa fa-pencil"></i>查看修改</a>'+
    '   <a class="btn btn-minier btn-pink" href="javascript:del({{ video.id}})" title="删除"><i class="ace-icon fa fa-trash-o"></i>删除</a>'+
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
    $("#addbtn").on('click',addvideo);
    $("#editbtn").on('click',editvideo);
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
    Eymjs.page.show(Eymjs.ukeli.url.video.LIST,{keyword:keyword,page:page},videoTemplate);
}

/**
 * 增加一个管理员
 */
function addvideo() {
    Eymjs.dialog.showUrl('视频增加',Eymjs.ukeli.url.video.EDIT,{},600,500);
}
/**
 * 修改一条记录
 * @param id
 */
function edit(id) {
    Eymjs.dialog.showUrl('修改视频',Eymjs.ukeli.url.video.EDIT,{id:id},600,500);
}
/**
 * 修改按钮
 */
function editvideo() {
    var id= Eymjs.page.selectOne("contentTable");
    if(id){edit(id);}
}
/**
 * 选择多个，删除
 */
function delAll() {
    var ids= Eymjs.page.selectList("contentTable");
    if(ids){
        Eymjs.page.delByids(ids,Eymjs.ukeli.url.video.DELETE,"您确定要删除您选择的视频吗")
    }
}

/**
 * 删除一个记录
 * @param id
 */
function del(id) {

    Eymjs.page.delByids(id,Eymjs.ukeli.url.video.DELETE,"您确定要删除该视频吗?")
}

/**
 * 上线或者下线一条记录,或者锁定以及一个视频
 * @param id
 * @param status
 */
function online(id,status,type) {
    if(!id){return;}
    var msg = "您确定要下线该视频吗？";
    if(status==1){
        msg = "您确定要上线该视频吗？";
    }
    if(type==1){
        msg = "您确定要审核不通过该视频吗？";
        if(status==1){
            msg = "您确定要审核通过该视频吗？";
        }
    }

    Eymjs.page.online(Eymjs.ukeli.url.video.STATUS,{id:id,status:status,type:type},msg)
}