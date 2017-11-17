
var userTemplate ='{{each list as user index}}'+
    '<tr>'+
    '   <td class="hidden-xs center" height="28">'+
    '       <label class="pos-rel">'+
    '       		<input class="i-checks" type="checkbox" id="{{ user.id}}">'+
    '       </label>'+
    '   </td>'+
    '   <td class="center">{{ user.id}}</td>'+
    '   <td>{{ user.username}}</td>'+
    '   <td>{{ user.password}}</td>'+
    '   <td>{{ user.nickname}}</td>'+
    '   <td>{{ user.realname}}</td>'+
    '   <td>{{dateFormat(user.bidthday,\'yyyy-MM-dd\')}}</td>'+
    '   <td>{{#sex(user.sex)}}</td>'+
    '   <td>{{ user.telephone}}</td>'+
    '   <td>{{ user.email}}</td>'+
    '   <td>{{#trueorfalse(user.isvip)}}</td>'+
    '   <td>{{ user.vipendtime}}</td>'+
    '   <td class="hidden-xs">{{#status(user.status,user.id)}}</td>'+
    '   <td class="hidden-xs">{{dateFormat(user.edittime,\'yyyy-MM-dd hh:mm:ss\')}}</td>'+
    '   <td>'+
    '<a class="btn btn-minier btn-primary"  href="javascript:show({{ user.id}},)" title="查看"><i class="ace-icon fa fa-pencil"></i>查看</a>  '+
    '<a class="btn btn-minier btn-primary"  href="javascript:edit({{ user.id}})" title="修改"><i class="ace-icon fa fa-pencil"></i>修改</a>  '+
    '<a class="btn btn-minier btn-pink" href="javascript:del({{ user.id}})" title="删除"><i class="ace-icon fa fa-trash-o"></i>删除</a>'+
    '   </td>'+
    '</tr>'+
    '{{/each}}'+
    '<tr>'+
    '    <td height="50"></td>'+
    '   <td  colspan="14" id="pager">'+
    '       <div class="fl" style="padding-top:7px"></div>'+
    '       <div align="left" class="fr">'+
    '           <ul class="pagination"></ul>'+
    '       </div>'+
    '   </td>'+
    '</tr>';

$(function () {
    $("#searchbtn").on('click',search);
    $("#researchbtn").on('click',reSearch);
    $("#addbtn").on('click',adduser);
    $("#editbtn").on('click',edituser);
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
    $("#nickname").val('');
    $("#reservationtime").val('');
    $("#isvipForsearch").val('');
    search();
}

function page(page){
    var username = $("#username").val();
    var nickname = $("#nickname").val();
    var reservationtime = $("#reservationtime").val();
    var isvip =$("#isvipForsearch option:selected").val();
    console.info("username"+username+"nickname="+nickname+"reservationtime="+reservationtime+"isvip="+isvip)
    Eymjs.page.show(Eymjs.ukeli.url.user.LIST,{username:username,nickname:nickname,reservationtime:reservationtime,isvip:isvip,page:page},userTemplate);
}

/**
 * 增加一个管理员
 */
function adduser() {
    Eymjs.dialog.showUrl('用户信息增加',Eymjs.ukeli.url.user.EDIT,{},750,600);
}
/**
 * 修改一条记录
 * @param id
 */
function edit(id) {
    Eymjs.dialog.showUrl('修改用户信息',Eymjs.ukeli.url.user.EDIT,{id:id},750,600);
}
/**
 * 修改按钮
 */
function edituser() {
    var id= Eymjs.page.selectOne("contentTable");
    if(id){edit(id);}
}
/**
 * 选择多个，删除
 */
function delAll() {
    var ids= Eymjs.page.selectList("contentTable");
    if(ids){
        Eymjs.page.delByids(ids,Eymjs.ukeli.url.user.DELETE,"您确定要删除您选择的用户信息吗")
    }
}

/**
 * 删除一个记录
 * @param id
 */
function del(id) {

    Eymjs.page.delByids(id,Eymjs.ukeli.url.user.DELETE,"您确定要删除该用户信息吗?")
}


/**
 * 查看
 * @returns
 */
function show(id){
	 Eymjs.dialog.showUrl('查看',Eymjs.ukeli.url.user.SHOW,{id:id},750,600);
}

/**
 * 上线或者下线一条记录,或者锁定以及一个用户信息
 * @param id
 * @param status
 */
function online(id,status,type) {
    if(!id){return;}
    var msg = "您确定要下线该用户信息吗？";
    if(status==1){
        msg = "您确定要上线该用户信息吗？";
    }
    if(type==1){
        msg = "您确定要审核不通过该用户信息吗？";
        if(status==1){
            msg = "您确定要审核通过该用户信息吗？";
        }
    }

    Eymjs.page.online(Eymjs.ukeli.url.user.STATUS,{id:id,status:status,type:type},msg)
}