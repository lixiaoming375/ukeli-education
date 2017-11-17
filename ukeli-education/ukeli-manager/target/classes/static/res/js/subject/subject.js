
var subjectTemplate ='{{each list as subject index}}'+
    '<tr>'+
    '   <td class="hidden-xs center" height="28">'+
    '       <label class="pos-rel">'+
    '       		<input class="i-checks" type="checkbox" id="{{ subject.id}}">'+
    '       </label>'+
    '   </td>'+
    '   <td>{{ subject.iorder}}</td>'+
    '   <td>{{ subject.ext_stype}}</td>'+
    '   <td>{{ subject.ext_vtype}}</td>'+
    '   <td>{{ subject.name}}</td>'+
    //'   <td>{{ subject.imgurl}}</td>'+
    '   <td>{{ subject.viewtimes}}</td>'+
    '   <td>{{ subject.studytimes}}</td>'+
    '   <td class="hidden-xs">{{#status(subject.status,subject.id)}}</td>'+
    '   <td class="hidden-xs">{{#order(subject.id)}}</td>'+
    '   <td class="hidden-xs">{{subject.edittime}}</td>'+
    '   <td>'+
    '       <div class="action-buttons">' +
    '           {{#show(subject.id)}}{{#operate(subject.id)}}' +
    '           <a class="btn btn-minier btn-pink" href="javascript:video({{subject.id}})" title="浏览视频"><i class="ace-icon fa fa-lock"></i>浏览视频</a>'+
    '       </div>' +
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
    $("#addbtn").on('click',addsubject);
    $("#editbtn").on('click',editsubject);
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
    $("#nameForSearch").val('');
    $("#stypeForSearch").val('');
    $("#vtypeForSearch").val('');
    $("#statusForSearch").val('');
    $("#reservationtime").val('');
    search();
}

function page(page){
	var nameForSearch=$("#nameForSearch").val();
	var stypeForSearch=$("#stypeForSearch").val();
	var vtypeForSearch=$("#vtypeForSearch").val();
	var statusForSearch=$("#statusForSearch").val();
	var reservationtime=$("#reservationtime").val();
    Eymjs.page.show(Eymjs.ukeli.url.subject.LIST,{stype:stypeForSearch,name:nameForSearch,vtype:vtypeForSearch,status:statusForSearch,reservationtime:reservationtime,page:page},subjectTemplate);
}

/**
 * 增加一个管理员
 */
function addsubject() {
    Eymjs.dialog.showUrl('题目增加',Eymjs.ukeli.url.subject.EDIT,{},760,650);
}
/**
 * 修改一条记录
 * @param id
 */
function edit(id) {
    Eymjs.dialog.showUrl('修改题目',Eymjs.ukeli.url.subject.EDIT,{id:id},800,650);
}
/**
 * 修改按钮
 */
function editsubject() {
    var id= Eymjs.page.selectOne("contentTable");
    if(id){edit(id);}
}

/**
 * 查看
 * @returns
 */
function show(id){
	 Eymjs.dialog.showUrl('查看题目',Eymjs.ukeli.url.subject.SHOW,{id:id},800,600);
}

/**
 * 选择多个，删除
 */
function delAll() {
    var ids= Eymjs.page.selectList("contentTable");
    if(ids){
        Eymjs.page.delByids(ids,Eymjs.ukeli.url.subject.DELETE,"您确定要删除您选择的题目吗")
    }
}

/**
 * 删除一个记录
 * @param id
 */
function del(id) {
    Eymjs.page.delByids(id,Eymjs.ukeli.url.subject.DELETE,"您确定要删除该题目吗?")
}


/**
 * 调整排序
 * @param id
 * @param type
 */
function order(id,type) {
    Eymjs.page.online(Eymjs.ukeli.url.subject.ORDER,{id:id,type:type},'您确定要调整排序吗？')
}

/**
 * 上线或者下线一条记录,或者锁定以及一个题目
 * @param id
 * @param status
 */
function online(id,status,type) {
    if(!id){return;}
    var msg = "您确定要下线该题目吗？";
    if(status==1){
        msg = "您确定要上线该题目吗？";
    }
    if(type==1){
        msg = "您确定要审核不通过该题目吗？";
        if(status==1){
            msg = "您确定要审核通过该题目吗？";
        }
    }
    Eymjs.page.online(Eymjs.ukeli.url.subject.STATUS,{id:id,status:status,type:type},msg)
}

/**
 * 查看
 * @returns
 */
function video(id){
    Eymjs.dialog.showUrl('浏览视频',Eymjs.ukeli.url.subject.VIDEO,{id:id},670,400);
}

