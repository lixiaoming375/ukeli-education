
var teacherTemplate ='{{each list as teacher index}}'+
    '<tr>'+
    '   <td class="hidden-xs center" height="28">'+
    '       <label class="pos-rel">'+
    '       		<input class="i-checks" type="checkbox" id="{{ teacher.id}}">'+
    '       </label>'+
    '   </td>'+
    '   <td class="center">{{ teacher.id}}</td>'+
    '   <td>{{ teacher.name}}</td>'+
    '   <td>{{ teacher.typename}}</td>'+
    '   <td>{{ teacher.imageurl}}</td>'+
    '   <td class="hidden-xs">{{#status(teacher.status,teacher.id)}}</td>'+
    '   <td class="hidden-xs">{{dateFormat(teacher.edittime,\'yyyy-MM-dd hh:mm:ss\')}}</td>'+
    '   <td>'+
    '<a class="btn btn-minier btn-primary"  href="javascript:show({{ teacher.id}},)" title="查看"><i class="ace-icon fa fa-pencil"></i>查看</a>  '+
    '<a class="btn btn-minier btn-primary"  href="javascript:edit({{ teacher.id}})" title="修改"><i class="ace-icon fa fa-pencil"></i>修改</a>  '+
    '<a class="btn btn-minier btn-pink" href="javascript:del({{ teacher.id}})" title="删除"><i class="ace-icon fa fa-trash-o"></i>删除</a>'+
    '  </td>'+
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
    $("#addbtn").on('click',addteacher);
    $("#editbtn").on('click',editteacher);
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
    Eymjs.page.show(Eymjs.ukeli.url.teacher.LIST,{keyword:keyword,page:page},teacherTemplate);
}

/**
 * 增加一个管理员
 */
function addteacher() {
    Eymjs.dialog.showUrl('增加',Eymjs.ukeli.url.teacher.EDIT,{},600,450);
}
/**
 * 修改一条记录
 * @param id
 */
function edit(id) {
    Eymjs.dialog.showUrl('修改',Eymjs.ukeli.url.teacher.EDIT,{id:id},600,450);
}
/**
 * 修改按钮
 */
function editteacher() {
    var id= Eymjs.page.selectOne("contentTable");
    if(id){edit(id);}
}
/**
 * 选择多个，删除
 */
function delAll() {
    var ids= Eymjs.page.selectList("contentTable");
    if(ids){
        Eymjs.page.delByids(ids,Eymjs.ukeli.url.teacher.DELETE,"您确定要删除您选择的吗")
    }
}

/**
 * 删除一个记录
 * @param id
 */
function del(id) {

    Eymjs.page.delByids(id,Eymjs.ukeli.url.teacher.DELETE,"您确定要删除该吗?")
}



/**
 * 查看
 * @returns
 */
function show(id){
	 Eymjs.dialog.showUrl('查看',Eymjs.ukeli.url.teacher.SHOW,{id:id},600,400);
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

    Eymjs.page.online(Eymjs.ukeli.url.teacher.STATUS,{id:id,status:status,type:type},msg)
}