
var usersubjectrightTemplate ='{{each list as usersubjectright index}}'+
    '<tr>'+
    '   <td class="hidden-xs center" height="28">'+
    '       <label class="pos-rel">'+
    '       		<input class="i-checks" type="checkbox" id="{{ usersubjectright.id}}">'+
    '       </label>'+
    '   </td>'+
    '   <td class="center">{{ usersubjectright.id}}</td>'+
    '   <td>{{ usersubjectright.userid}}</td>'+
    '   <td>{{ usersubjectright.ext_userName}}</td>'+
    '   <td>{{ usersubjectright.subjectid}}</td>'+
    '   <td>{{ usersubjectright.ext_subjectName}}</td>'+
    '   <td>{{#vtype(usersubjectright.vtype)}}</td>'+
    '   <td>{{#isanswer(usersubjectright.isanswer)}}</td>'+
    '   <td>{{#iswrong(usersubjectright.iswrong)}}</td>'+
    '   <td class="hidden-xs">{{#status(usersubjectright.status,usersubjectright.id)}}</td>'+
    '   <td class="hidden-xs">{{dateFormat(usersubjectright.edittime,\'yyyy-MM-dd hh:mm:ss\')}}</td>'+
    //'   <td>{{#operate(usersubjectright.id)}}</td>'+
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


//'题目类别|1|1|1|1 例题 2 课堂联系 3 自测卷  4 杯赛试题  5 考试试题',
template.helper('vtype',function (vtype) {
    if(vtype =='1'){
        return '例题';
    }else if(vtype =='2'){
        return '课堂联系';
    }else if(vtype =='3'){
        return "自测卷";
    }else if(vtype =='4'){
        return '杯赛试题';
    }else if(vtype =='5'){
        return "自考试试题";
    }
});


template.helper('isanswer',function (isanswer) {
    if(isanswer =='0'){
        return '未回答';
    }else if(isanswer =='1'){
        return '已经回答';
        }
});

template.helper('isanswer',function (isanswer) {
    if(isanswer =='0'){
        return '未回答';
    }else if(isanswer =='1'){
        return '已经回答';
        }
});
template.helper('iswrong',function (iswrong) {
    if(iswrong =='0'){
        return '正确';
    }else if(iswrong =='1'){
        return '错误';
        }
});


$(function () {
    $("#searchbtn").on('click',search);
    $("#researchbtn").on('click',reSearch);
    $("#addbtn").on('click',addusersubjectright);
    $("#editbtn").on('click',editusersubjectright);
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
    $("#username").val('');
    $("#name").val('');
    search();
}

function page(page){
    var username = $("#username").val();
    var name =$("#name").val();
    Eymjs.page.show(Eymjs.ukeli.url.usersubjectright.LIST,{username:username,name:name,page:page},usersubjectrightTemplate);
}

/**
 * 增加一个管理员
 */
function addusersubjectright() {
    Eymjs.dialog.showUrl('增加',Eymjs.ukeli.url.usersubjectright.EDIT,{},500,400);
}
/**
 * 修改一条记录
 * @param id
 */
function edit(id) {
    Eymjs.dialog.showUrl('修改',Eymjs.ukeli.url.usersubjectright.EDIT,{id:id},500,400);
}
/**
 * 修改按钮
 */
function editusersubjectright() {
    var id= Eymjs.page.selectOne("contentTable");
    if(id){edit(id);}
}
/**
 * 选择多个，删除
 */
function delAll() {
    var ids= Eymjs.page.selectList("contentTable");
    if(ids){
        Eymjs.page.delByids(ids,Eymjs.ukeli.url.usersubjectright.DELETE,"您确定要删除您选择的吗")
    }
}

/**
 * 删除一个记录
 * @param id
 */
function del(id) {

    Eymjs.page.delByids(id,Eymjs.ukeli.url.usersubjectright.DELETE,"您确定要删除该吗?")
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

    Eymjs.page.online(Eymjs.ukeli.url.usersubjectright.STATUS,{id:id,status:status,type:type},msg)
}