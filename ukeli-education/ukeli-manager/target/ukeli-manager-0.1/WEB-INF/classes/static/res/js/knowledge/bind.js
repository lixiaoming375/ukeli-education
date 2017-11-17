
var bindSubjectTemplate ='{{each list as subject index}}'+
    '<tr>'+
    '   <td class="hidden-xs center" height="28">'+
    '       <label class="pos-rel">'+
    '       		<input class="i-checks" type="checkbox" id="{{subject.id}}">'+
    '       </label>'+
    '   </td>'+
    '   <td>{{ subject.iorder}}</td>'+
    '   <td>{{ subject.ext_stype}}</td>'+
    '   <td>{{ subject.name}}</td>'+
    '   <td class="hidden-xs">{{dateFormat(subject.edittime,\'yyyy-MM-dd hh:mm:ss\')}}</td>'+
    //'   <td class="hidden-xs">{{#is_bind(subject.ext_isbind,subject.id)}}</td>'+
    //'   <td>{{#operatediffer(subject.id,subject.ext_isbind)}}</td>'+
    '</tr>'+
    '{{/each}}'+
    '<tr>'+
    '    <td height="50"></td>'+
    '   <td  colspan="12" id="pagerForBind">'+
    '       <div class="fl" style="padding-top:7px"></div>'+
    '       <div align="left" class="fr">'+
    '           <ul class="pagination"></ul>'+
    '       </div>'+
    '   </td>'+
    '</tr>';




var knowledgeId="";

$(function () {
    $("#searchbtnForBind").on('click',searchbtnForBind);
    $("#researchbtnForBind").on('click',reSearchForBind);
    $("#bindBatch").on('click',bindBatch);
    knowledgeId=$("#knowledgeId").val();
    Eymjs.page.init();
    searchbtnForBind();
});

/**
 * 搜索
 */
function searchbtnForBind() {
	pageForBind(1);
}
/**
 * 重置搜索
 */
function reSearchForBind() {
    $("#keywordForBind").val('');
    $("#vtype").val('');
    searchbtnForBind();
}

function pageForBind(page){
    var keywordForBind = $("#keywordForBind").val();
    var vtype =$("#vtype option:selected").val();
    console.info("vtype="+vtype)
    
    Eymjs.page.showForBind(Eymjs.ukeli.url.subject.BINDSUBLISTFORKNOW,{keywordForBind:keywordForBind,vtype:vtype,page:page,status:-1,slock:0,knowledgeId:knowledgeId},bindSubjectTemplate,function(){
    	$('#contentTableForBind tbody tr td input.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue'
        });
        $('#contentTableForBind thead tr th input.i-checks').on('ifChecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定
            $('#contentTableForBind tbody tr td input.i-checks').iCheck('check');
        });

        $('#contentTableForBind thead tr th input.i-checks').on('ifUnchecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定
            $('#contentTableForBind tbody tr td input.i-checks').iCheck('uncheck');
        });
    });
}


/**
 * 选择多个，绑定
 */
function bindBatch() {
    var ids= Eymjs.page.selectList("contentTableForBind");
    console.info(knowledgeId+'------'+ids)
    if(ids){
        //Eymjs.page.delByids(ids,Eymjs.ukeli.url.knowledge.DELETE,"您确定要删除您选择的吗")
      Eymjs.page.bindFunction(knowledgeId,ids,Eymjs.ukeli.url.knowledge.BINDBYID,"您确定要绑定试题吗?")
    }
}

/**
 * 绑定一个记录
 * @param id
 */
function bind(subjectId) {
	console.info(knowledgeId+'------'+subjectId)
    Eymjs.page.bindFunction(knowledgeId,subjectId,Eymjs.ukeli.url.knowledge.BINDBYID,"您确定要绑定该试题吗?")
}





