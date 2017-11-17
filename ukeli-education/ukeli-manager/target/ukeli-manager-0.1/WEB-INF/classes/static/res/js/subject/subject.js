
var subjectTemplate ='{{each list as subject index}}'+
    '<tr>'+
    '   <td class="hidden-xs center" height="28">'+
    '       <label class="pos-rel">'+
    '       		<input class="i-checks" type="checkbox" id="{{ subject.id}}">'+
    '       </label>'+
    '   </td>'+
    '   <td>{{ subject.iorder}}</td>'+
    '   <td>{{ subject.ext_stype}}</td>'+
    '   <td>{{ subject.name}}</td>'+
    '   <td>{{ subject.viewtimes}}</td>'+
    '   <td>{{ subject.studytimes}}</td>'+
    '   <td class="hidden-xs">{{#status(subject.status,subject.id)}}</td>'+
    '   <td class="hidden-xs">{{#order(subject.id)}}</td>'+
    '   <td class="hidden-xs">{{dateFormat(subject.edittime,\'yyyy-MM-dd hh:mm:ss\')}}</td>'+
    '   <td>'+
    '<a class="btn btn-minier btn-primary"  href="javascript:show({{ subject.id}},)" title="查看"><i class="ace-icon fa fa-pencil"></i>查看</a>  '+
    '<a class="btn btn-minier btn-primary"  href="javascript:edit({{ subject.id}})" title="修改"><i class="ace-icon fa fa-pencil"></i>修改</a>  '+
    '<a class="btn btn-minier btn-pink" href="javascript:del({{ subject.id}})" title="删除"><i class="ace-icon fa fa-trash-o"></i>删除</a>'+
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
    $("#keyword").val('');
    search();
}

function page(page){
    var keyword = $("#keyword").val();
    Eymjs.page.show(Eymjs.ukeli.url.subject.LIST,{keyword:keyword,page:page},subjectTemplate);
}

/**
 * 增加一个管理员
 */
function addsubject() {
    Eymjs.dialog.showUrl('题目增加',Eymjs.ukeli.url.subject.EDIT,{},760,700);
}
/**
 * 修改一条记录
 * @param id
 */
function edit(id) {
    Eymjs.dialog.showUrl('修改题目',Eymjs.ukeli.url.subject.EDIT,{id:id},760,700);
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
	 Eymjs.dialog.showUrl('查看题目',Eymjs.ukeli.url.subject.SHOW,{id:id},700,600);
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


