<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-2"></div>
        <form class="form-horizontal"  id="nextForm" method="post" action="${adminPath}/subject/saveForOption.json">
             
         <div class="space-2"></div>
 		 <div class="row" name="optiondiv">
                <div class="col-sm-12">
                     <table class="table  table-hover" id="optiionTable">
                       <thead>
                        <tr>
                        <th class="hidden-sm hidden-xs">选项名称</th>
						<th class="hidden-sm hidden-xs">选项内容</th>
						<th class="hidden-sm hidden-xs"> <a class='btn btn-minier btn-default' name='addoption'  title='增加选项'><i class='ace-icon fa fa-pencil'></i>增加选项</a></th>
                        </tr>
                       </thead>
                       <tbody id="ajax-data-option">
                         <#if subjectOptionsDoList ??>
							<#list subjectOptionsDoList as item>
							<tr id="tr_${item_index}">
	                        <td>
	                        <input name="optiionname_${item_index}" id="optiionname_${item_index}"  type="text" class="col-sm-11" value="${item.optiionname}" placeholder="选项名称" style=" height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
	                        </td>
	                        <td>
	                        <textarea class="layui-textarea"  name="optioncontext_${item_index}" id="optioncontext_${item_index}"   style="display: none" placeholder="选项内容">${item.optioncontext}
                            </textarea>
	                        </td>
	                        <td>
	                        <a class='btn btn-minier btn-default' name='deleteoption_old'  title='${item_index}'><i class='ace-icon fa fa-pencil'></i>删除选项</a>
	                        </td>
	                        </tr>
							</#list>
						    
						</#if>
						
						<#list 1..10 as item>
                       <tr  id="tr_none_${item}" style="display: none" >
	                        <td>
	                        <input name="optiionname_none_${item}" id="optiionname_none_${item}" type="text" class="col-sm-11" value="" placeholder="选项名称" style=" height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
	                        </td>
	                        <td>
	                        <textarea class="layui-textarea"  name="optioncontext_none_${item}" id="optioncontext_none_${item}"   style="display: none" placeholder="选项内容">
                            </textarea>
	                        </td>
	                        <td>
	                        <a class='btn btn-minier btn-default' name='deleteoption'   title='${item}'><i class='ace-icon fa fa-pencil'></i>删除选项</a>
	                        </td>
	                   </tr> 
	                   </#list>
                        
                       </tbody>
                     </table>
                     
                     <div class="space-2"></div>
                     
 			 <div class="row"  name="answerdiv" >
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="answer"> 正确答案: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <input name="answer" type="text" id="answer"   class="col-sm-11" value="${answer !}" style=" height:30px;vertical-align:middle; border:1px solid #d1d1d2;" placeholder="请输入选择题正确答案" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
             </div>
          </div>
            
            <input name="subjectid" id="subjectid" type="hidden" value="${subjectid !}" />
        </form>

    </div>
</div>
<script>

 //自定义工具栏
    var index=1;
    var layedit = layui.layedit;
    layui.use('element', function(){
	  var element = layui.element;
	  
	  //一些事件监听
	  element.on('tab(layedit)', function(data){
	    //console.log(data);
	  });
	});
    
    layedit.set({
	  uploadImage: {
	    url: '${adminPath}/subject/uploadImag.json' //接口url
	    ,type: 'post' //默认post
	  }
	});
	
     <#if subjectOptionsDoList??>
	    <#list subjectOptionsDoList as item>
	        var index_${item_index} = layedit.build('optioncontext_${item_index}',{
		       height: 100 //设置编辑器高度
		     });
		 </#list>
	</#if>
	
	<#list 1..10 as item>
		var index_none_${item} = layedit.build('optioncontext_none_${item}',{
		       height: 100 //设置编辑器高度
		     }); 
	 </#list>



   $(document).ready(function () {  
   
        $("[name='addoption']").on('click',function(){
           if(index==10){
             index=1;
           }
           $("#tr_none_"+index).show();
           index=index+1;
        })
        
        $("[name='deleteoption']").on('click',function(e){
           var indexval=$(e.target).attr("title");
           $("#optiionname_none_"+indexval).val('');
           $("#optioncontext_none_"+indexval).parent().find('body').empty();
           $("#tr_none_"+indexval).hide();
        })
        
        $("[name='deleteoption_old']").on('click',function(e){
           var indexval=$(e.target).attr("title");
           $("#optiionname_"+indexval).val('');
            $("#optioncontext_"+indexval).parent().find('body').empty();
           $("#tr_"+indexval).hide();
        })
        
        
    });
    
</script>

<script src="${adminPath}/res/js/subject/subjectoption.edit.js?ver=${version}"></script>


