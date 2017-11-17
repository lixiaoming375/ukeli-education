
var knowledgeTemplate ='{{each list as knowledge index}}'+
    '<tr>'+
    '   <td class="hidden-xs center" height="28">'+
    '       <label class="pos-rel">'+
    '       		<input class="i-checks" name="radioname" type="radio" id="{{knowledge.id}}">'+
    '       </label>'+
    '   </td>'+
    '   <td class="hidden-sm hidden-xs">{{ knowledge.id}}</td>'+
    '   <td class="hidden-sm hidden-xs">{{ knowledge.ext_knowtype}}</td>'+
    '   <td class="hidden-sm hidden-xs">{{ knowledge.nodename}}</td>'+
    '   <td class="hidden-sm hidden-xs">{{ knowledge.ext_grade}}</td>'+
    '   <td class="hidden-sm hidden-xs">{{ knowledge.totalexam}}</td>'+
    '   <td class="hidden-sm hidden-xs">{{ knowledge.videopath}}</td>'+
    '   <td class="hidden-xs">{{#status(knowledge.status,knowledge.id)}}</td>'+
   // '   <td class="hidden-xs">{{#slock_staus(knowledge.slock,knowledge.id)}}</td>'+
    // '   <td class="hidden-xs">{{dateFormat(knowledge.edittime,\'yyyy-MM-dd hh:mm:ss\')}}</td>'+
    '   <td>'+
    '<a class="btn btn-minier btn-primary"  href="javascript:show({{ knowledge.id}},)" title="查看"><i class="ace-icon fa fa-pencil"></i>查看</a>  '+
    '<a class="btn btn-minier btn-primary"  href="javascript:edit({{ knowledge.id}})" title="修改"><i class="ace-icon fa fa-pencil"></i>修改</a>  '+
    '<a class="btn btn-minier btn-pink" href="javascript:del({{ knowledge.id}})" title="删除"><i class="ace-icon fa fa-trash-o"></i>删除</a>'+
    '<a class="btn btn-minier btn-default"  href="javascript:showForBind({{ knowledge.id}})" title="绑定题目"><i class="ace-icon fa fa-bolt"></i>绑定题目</a>'+
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
    $("#addbtn").on('click',addknowledge);
    $("#editbtn").on('click',editknowledge);
    $("#delbtn").on('click',delAll);
    Eymjs.page.init();
    search();
    var indexKnowledgeId;;
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
    Eymjs.page.show(Eymjs.ukeli.url.knowledge.LIST,{keyword:keyword,page:page},knowledgeTemplate,function () {
        $('#contentTable tbody tr td input.i-checks').on('ifChecked', function(event){      //ifCreated 事件应该在插件初始化之前绑定
        	indexKnowledgeId =$(this).attr("id");
            console.info("选中"+indexKnowledgeId);
            initRightValue(indexKnowledgeId);
        });
    });
}

/**
 * 初始化题目列表
 * @param typeid
 */
function initRightValue(id){
	pageForRightVal(1,id);
}

function pageForRightVal(page,id){
	 Eymjs.page.showRight(Eymjs.ukeli.url.subject.LISTBYKNOW,{page:page,knowledgeId:id},subjectTemplate);
}



/**
 * 增加一个知识点
 */
function addknowledge() {
    Eymjs.dialog.showUrl('增加知识点',Eymjs.ukeli.url.knowledge.EDIT,{},600,500);
}
/**
 * 修改一条记录
 * @param id
 */
function edit(id) {
    Eymjs.dialog.showUrl('修改知识点',Eymjs.ukeli.url.knowledge.EDIT,{id:id},600,500);
}
/**
 * 修改按钮
 */
function editknowledge() {
    var id= Eymjs.page.selectOne("contentTable");
    if(id){edit(id);}
}
/**
 * 选择多个，删除
 */
function delAll() {
    var ids= Eymjs.page.selectList("contentTable");
    if(ids){
        Eymjs.page.delByids(ids,Eymjs.ukeli.url.knowledge.DELETE,"您确定要删除您选择的知识点吗")
    }
}

/**
 * 删除一个记录
 * @param id
 */
function del(id) {

    Eymjs.page.delByids(id,Eymjs.ukeli.url.knowledge.DELETE,"您确定要删除该知识点吗?")
}


/**
 * 查看
 * @returns
 */
function show(id){
	 Eymjs.dialog.showUrl('查看知识点',Eymjs.ukeli.url.knowledge.SHOW,{id:id},600,450);
}


/**
 * 调整被绑定的题目排序
 * @param id
 * @param type
 */
function orderserial(id,type) {
    Eymjs.page.online(Eymjs.ukeli.url.knowledge.ORDERSERIAL,{id:id,knowledgeId:indexKnowledgeId,type:type},'您确定要调整排序吗？')
}




/**
 * 上线或者下线一条记录,或者锁定以及一个知识点
 * @param id
 * @param status
 */
function online(id,status,type) {
    if(!id){return;}
    var msg = "您确定要下线该知识点吗？";
    if(status==1){
        msg = "您确定要上线该知识点吗？";
    }
    if(type==1){
        msg = "您确定要审核不通过该知识点吗？";
        if(status==1){
            msg = "您确定要审核通过该知识点吗？";
        }
    }

    Eymjs.page.online(Eymjs.ukeli.url.knowledge.STATUS,{id:id,status:status,type:type},msg)
}


/**
 * 弹框 为考试绑定题目 
 * @returns
 */
function showForBind(id){
	 Eymjs.dialog.showUrl('绑定题目',Eymjs.ukeli.url.knowledge.BINDDIALOG,{id:id},750,650);
}

/**
 * 解除绑定
 * @param attrId
 * @returns
 */
function cancelBind(subjectId){
	console.info(indexKnowledgeId+'------'+subjectId)
	Eymjs.page.bindFunction(indexKnowledgeId,subjectId,Eymjs.ukeli.url.knowledge.CANCELBIND,"您确定对该试题解除绑定吗?")
}
