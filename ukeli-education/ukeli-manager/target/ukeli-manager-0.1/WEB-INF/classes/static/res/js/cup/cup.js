
var cupTemplate ='{{each list as cup index}}'+
    '<tr>'+
    '   <td class="hidden-xs center" height="28">'+
    '       <label class="pos-rel">'+
    '       		<input class="i-checks" type="radio" name="radioname" id="{{ cup.id}}">'+
    '       </label>'+
    '   </td>'+
    '   <td class="hidden-xs">{{cup.iorder}}</td>'+
    '   <td class="hidden-xs">{{cup.name}}</td>'+
    '   <td class="hidden-xs">{{cup.totalsubject}}</td>'+
    '   <td class="hidden-xs">{{cup.times}}</td>'+
    '   <td class="hidden-xs">{{cup.score}}</td>'+
    '   <td class="hidden-xs">{{cup.ext_grade}}</td>'+
    '   <td class="hidden-xs">{{#status(cup.status,cup.id)}}</td>'+
   // '   <td class="hidden-xs">{{#order(cup.id)}}</td>'+
    '   <td>'+
    '<a class="btn btn-minier btn-primary"  href="javascript:show({{ cup.id}},)" title="查看"><i class="ace-icon fa fa-pencil"></i>查看</a>  '+
    '<a class="btn btn-minier btn-primary"  href="javascript:edit({{ cup.id}})" title="修改"><i class="ace-icon fa fa-pencil"></i>修改</a>  '+
    '<a class="btn btn-minier btn-pink" href="javascript:del({{ cup.id}})" title="删除"><i class="ace-icon fa fa-trash-o"></i>删除</a>'+
    '<a class="btn btn-minier btn-default"  href="javascript:showForBind({{ cup.id}})" title="绑定题目"><i class="ace-icon fa fa-bolt"></i>绑定题目</a>'+
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

var subjectTemplate ='{{each list as subject index}}'+
	'<tr>'+
	'   <td>{{ subject.id}}</td>'+
	'   <td>{{ subject.ext_serial}}</td>'+
	'   <td>{{ subject.ext_stype}}</td>'+
	'   <td>{{ subject.name}}</td>'+
	//'   <td>{{ subject.viewtimes}}</td>'+
	//'   <td>{{ subject.studytimes}}</td>'+
	//'   <td class="hidden-xs">{{#status(subject.status,subject.id)}}</td>'+
	'   <td class="hidden-xs">{{#orderserial(subject.id)}}</td>'+
	'   <td>'+
	'<a class="btn btn-minier btn-pink"  href="javascript:cancelBind({{subject.id}})" title="解绑"><i class="ace-icon fa fa-trash-o"></i>解绑</a>'+
	'</td>'+
	'</tr>'+
	'{{/each}}'+
	'<tr>'+
	'    <td height="50"></td>'+
	'   <td  colspan="12" id="pagerRight">'+
	'       <div class="fl" style="padding-top:7px"></div>'+
	'       <div align="left" class="fr">'+
	'           <ul class="pagination"></ul>'+
	'       </div>'+
	'   </td>'+
	'</tr>';

/**
 * 展示排序的编号
 */
template.helper('orderserial',function (id) {
    var html ='<a href="#" onclick="orderserial('+id+',1);"><i class="blue ace-icon fa fa-arrow-circle-up bigger-200"></i></a>&nbsp;&nbsp;';
        html +='<a href="#" onclick="orderserial('+id+',2);"><i class="blue ace-icon  fa fa-arrow-circle-down bigger-200"></i></a>';
    return html;
});

$(function () {
    $("#searchbtn").on('click',search);
    $("#researchbtn").on('click',reSearch);
    $("#addbtn").on('click',addcup);
    $("#editbtn").on('click',editcup);
    $("#delbtn").on('click',delAll);
    Eymjs.page.init();
    search();
    var indexCupId;
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
    console.info("第--"+page+"--页");
    var keyword = $("#keyword").val();
    Eymjs.page.show(Eymjs.ukeli.url.cup.LIST,{keyword:keyword,page:page},cupTemplate,function () {
        $('#contentTable tbody tr td input.i-checks').on('ifChecked', function(event){      //ifCreated 事件应该在插件初始化之前绑定
        	indexCupId =$(this).attr("id");
            console.info("选中"+indexCupId);
            initRightValue(indexCupId);
        });
    });
}


/**
 * 初始化属性列表
 * @param typeid
 */
function initRightValue(id){
	pageForRightVal(1,id);
}

function pageForRightVal(page,id){
	 Eymjs.page.showRight(Eymjs.ukeli.url.subject.LISTBYCUP,{page:page,cupId:id},subjectTemplate);
}



/**
 * 增加一个管理员
 */
function addcup() {
    Eymjs.dialog.showUrl('增加',Eymjs.ukeli.url.cup.EDIT,{},700,550);
}
/**
 * 修改一条记录
 * @param id
 */
function edit(id) {
    Eymjs.dialog.showUrl('修改',Eymjs.ukeli.url.cup.EDIT,{id:id},700,550);
}
/**
 * 修改按钮
 */
function editcup() {
    var id= Eymjs.page.selectOne("contentTable");
    if(id){edit(id);}
}
/**
 * 选择多个，删除
 */
function delAll() {
    var ids= Eymjs.page.selectList("contentTable");
    if(ids){
        Eymjs.page.delByids(ids,Eymjs.ukeli.url.cup.DELETE,"您确定要删除您选择的吗")
    }
}

/**
 * 删除一个记录
 * @param id
 */
function del(id) {

    Eymjs.page.delByids(id,Eymjs.ukeli.url.cup.DELETE,"您确定要删除该吗?")
}

/**
 * 调整排序
 * @param id
 * @param type
 */
function order(id,type) {
    Eymjs.page.online(Eymjs.ukeli.url.cup.ORDER,{id:id,type:type},'您确定要调整排序吗？')
}

/**
 * 调整被绑定的题目排序
 * @param id
 * @param type
 */
function orderserial(id,type) {
    Eymjs.page.online(Eymjs.ukeli.url.cup.ORDERSERIAL,{id:id,cupId:indexCupId,type:type},'您确定要调整排序吗？')
}



/**
 * 查看
 * @returns
 */
function show(id){
	 Eymjs.dialog.showUrl('查看杯赛',Eymjs.ukeli.url.cup.SHOW,{id:id},700,500);
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

    Eymjs.page.online(Eymjs.ukeli.url.cup.STATUS,{id:id,status:status,type:type},msg)
}


/**
 * 弹框 为考试绑定题目 
 * @returns
 */
function showForBind(id){
	 Eymjs.dialog.showUrl('绑定题目',Eymjs.ukeli.url.cup.BINDDIALOG,{id:id},900,600);
}

/**
 * 解除绑定
 * @param attrId
 * @returns
 */
function cancelBind(subjectId){
	console.info(indexCupId+'------'+subjectId)
	Eymjs.page.bindFunction(indexCupId,subjectId,Eymjs.ukeli.url.cup.CANCELBIND,"您确定对该试题解除绑定吗?")
}
