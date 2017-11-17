
var questionTemplate ='{{each list as question index}}'+
    '<tr>'+
    '   <td class="hidden-xs center" height="28">'+
    '       <label class="pos-rel">'+
    '       		<input class="i-checks" type="checkbox" id="{{ question.id}}">'+
    '       </label>'+
    '   </td>'+
    '   <td class="center">{{ question.iorder}}</td>'+
    '   <td>{{ question.userid}}</td>'+
    '   <td>{{ question.ext_realname}}</td>'+
   // '   <td>{{ question.question}}</td>'+
    '   <td>{{ question.ext_vtype}}</td>'+
    '   <td>{{ #isanswer(question.isanswer)}}</td>'+
   // '   <td>{{ question.answer}}</td>'+
    '   <td>{{ dateFormat(question.answer_time,\'yyyy-MM-dd hh:mm:ss\')}}</td>'+
    '   <td class="hidden-xs">{{#status(question.status,question.id)}}</td>'+
    '   <td class="hidden-xs">{{#order(question.id)}}</td>'+
    '   <td class="hidden-xs">{{dateFormat(question.ext_addtime,\'yyyy-MM-dd hh:mm:ss\')}}</td>'+
    '   <td class="hidden-xs">{{dateFormat(question.edittime,\'yyyy-MM-dd hh:mm:ss\')}}</td>'+
    '   <td>'+
    '       <div class="action-buttons">' +
    '           {{#show(question.id)}}' +
    '            <a class="btn btn-minier btn-primary"  href="javascript:edit({{ question.id}})" title="回复"><i class="ace-icon fa fa-pencil"></i>回复</a>'+
    '            <a class="btn btn-minier btn-danger" href="javascript:del({{ question.id}})" title="删除"><i class="ace-icon fa fa-trash-o"></i>删除</a>'+
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

/**
 * 是否回答
 */
template.helper('isanswer',function (isanswer) {
    if(isanswer ==0){
        return '<span class="red" >未回答</span>';
    }else if(isanswer ==1){
        return '<span class="green">已回答</span>';
    }else {
        return "未知";
    }
});


$(function () {
    $("#searchbtn").on('click',search);
    $("#researchbtn").on('click',reSearch);
    $("#addbtn").on('click',addquestion);
    $("#editbtn").on('click',editquestion);
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
	    $("#nicknameForSearch").val('');
	    $("#vtypeForSearch").val('');
	    $("#isanswerForSearch").val('');
	    $("#statusForSearch").val('');
	    $("#reservationtime").val('');
	    $("#questionForSearch").val('');
    search();
}

function page(page){
    var nickname=$("#nicknameForSearch").val();
	var isanswer=$("#isanswerForSearch").val();
	var vtype=$("#vtypeForSearch").val();
	var status=$("#statusForSearch").val();
	var reservationtime=$("#reservationtime").val();
	var question=$("#questionForSearch").val();
    Eymjs.page.show(Eymjs.ukeli.url.question.LIST,{nickname:nickname,isanswer:isanswer,vtype:vtype,status:status,reservationtime:reservationtime,question:question,page:page},questionTemplate);
}

/**
 * 增加一个管理员
 */
function addquestion() {
    Eymjs.dialog.showUrl('增加',Eymjs.ukeli.url.question.EDIT,{},500,400);
}
/**
 * 修改一条记录
 * @param id
 */
function edit(id) {
    Eymjs.dialog.showUrl('修改',Eymjs.ukeli.url.question.EDIT,{id:id},700,600);
}
/**
 * 修改按钮
 */
function editquestion() {
    var id= Eymjs.page.selectOne("contentTable");
    if(id){edit(id);}
}

/**
 * 查看
 * @returns
 */
function show(id){
	 Eymjs.dialog.showUrl('查看题目',Eymjs.ukeli.url.question.SHOW,{id:id},800,600);
}

/**
 * 选择多个，删除
 */
function delAll() {
    var ids= Eymjs.page.selectList("contentTable");
    if(ids){
        Eymjs.page.delByids(ids,Eymjs.ukeli.url.question.DELETE,"您确定要删除您选择的吗")
    }
}

/**
 * 删除一个记录
 * @param id
 */
function del(id) {
    Eymjs.page.delByids(id,Eymjs.ukeli.url.question.DELETE,"您确定要删除该吗?")
}


/**
 * 调整排序
 * @param id
 * @param type
 */
function order(id,type) {
    Eymjs.page.online(Eymjs.ukeli.url.question.ORDER,{id:id,type:type},'您确定要调整排序吗？')
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

    Eymjs.page.online(Eymjs.ukeli.url.question.STATUS,{id:id,status:status,type:type},msg)
}