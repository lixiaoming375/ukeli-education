
var ${pojo?lower_case}Template ='{{each list as ${pojo?lower_case} index}}'+
    '<tr>'+
    '   <td class="hidden-xs center" height="28">'+
    '       <label class="pos-rel">'+
    '       		<input class="i-checks" type="checkbox" id="{{ ${pojo?lower_case}.id}}">'+
    '       </label>'+
    '   </td>'+
    <#list pageList as column>
    	<#if column.name == "id">
    '   <td class="center">{{ ${pojo?lower_case}.id}}</td>'+
    	<#elseif  column.name == "slock"> 
    '   <td class="hidden-xs">{{#slock_staus(${pojo?lower_case}.slock,${pojo?lower_case}.id)}}</td>'+
    	<#elseif  column.name == "status"> 
    '   <td class="hidden-xs">{{#status_admin(${pojo?lower_case}.status,${pojo?lower_case}.id)}}</td>'+
    	<#else>
    '   <td>{{ ${pojo?lower_case}.${column.name}}}</td>'+
    	</#if>
	</#list>
    '   <td class="hidden-xs">{{dateFormat(${pojo?lower_case}.edittime,\'yyyy-MM-dd hh:mm:ss\')}}</td>'+
    '   <td>{{#operate(${pojo?lower_case}.id)}}</td>'+
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
    $("#addbtn").on('click',add${pojo?lower_case});
    $("#editbtn").on('click',edit${pojo?lower_case});
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
    Eymjs.page.show(Eymjs.shop.url.${pojo?lower_case}.LIST,{keyword:keyword,page:page},${pojo?lower_case}Template);
}

/**
 * 增加一个管理员
 */
function add${pojo?lower_case}() {
    Eymjs.dialog.showUrl('${tabdesp}增加',Eymjs.shop.url.${pojo?lower_case}.EDIT,{},500,400);
}
/**
 * 修改一条记录
 * @param id
 */
function edit(id) {
    Eymjs.dialog.showUrl('修改${tabdesp}',Eymjs.shop.url.${pojo?lower_case}.EDIT,{id:id},500,400);
}
/**
 * 修改按钮
 */
function edit${pojo?lower_case}() {
    var id= Eymjs.page.selectOne("contentTable");
    if(id){edit(id);}
}
/**
 * 选择多个，删除
 */
function delAll() {
    var ids= Eymjs.page.selectList("contentTable");
    if(ids){
        Eymjs.page.delByids(ids,Eymjs.shop.url.${pojo?lower_case}.DELETE,"您确定要删除您选择的${tabdesp}吗")
    }
}

/**
 * 删除一个记录
 * @param id
 */
function del(id) {

    Eymjs.page.delByids(id,Eymjs.shop.url.${pojo?lower_case}.DELETE,"您确定要删除该${tabdesp}吗?")
}

/**
 * 上线或者下线一条记录,或者锁定以及一个${tabdesp}
 * @param id
 * @param status
 */
function online(id,status,type) {
    if(!id){return;}
    var msg = "您确定要下线该${tabdesp}吗？";
    if(status==1){
        msg = "您确定要上线该${tabdesp}吗？";
    }
    if(type==1){
        msg = "您确定要审核不通过该${tabdesp}吗？";
        if(status==1){
            msg = "您确定要审核通过该${tabdesp}吗？";
        }
    }

    Eymjs.page.online(Eymjs.shop.url.${pojo?lower_case}.STATUS,{id:id,status:status,type:type},msg)
}