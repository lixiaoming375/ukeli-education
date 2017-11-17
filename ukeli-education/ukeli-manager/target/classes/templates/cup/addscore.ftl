<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-4"></div>
        <form class="form-horizontal" id="addscoreForm" method="post" action="${adminPath}/cup/bindSubject.json">
            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="name">试题名称:</label>
                <div class="col-sm-8">
                    <label style="padding-top: 8px;">${subject.name}</label>
                </div>
            </div>
             <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="name">试题分数:</label>
                <div class="col-sm-8">
	                 <div class="clearfix">
	                   <input name="score"  id="score"  type="text"   style="width:200px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
	                 </div>
                </div>
            </div>
            
             <div class="clearfix form-actions">
                <div class="col-md-offset-4 col-md-8">
                    <input name="cupId" id="cupId" type="hidden" value="${cupId!}" />
                    <input name="subjectId" id="subjectId" type="hidden" value="${subject.id!}" />
                    <button class="btn btn-info" type="submit"><i class="ace-icon fa fa-check bigger-110"></i>保存</button>
                    &nbsp; &nbsp; &nbsp;
                    <button class="btn" type="reset"><i class="ace-icon fa fa-undo bigger-110"></i>重置</button>
                </div>
            </div>
            
             
        </form>
        
    </div>
</div>
<script>
   //模态框状态
    $(document).ready(function () {
    
         // 数字号码验证
    jQuery.validator.addMethod("isnumber", function(value, element) {
         var r = /^\+?[1-9][0-9]*$/;　　//正整数 
        return this.optional(element)| r.test(value.replace(',', '').trim());
    }, "请填写正确格式。");
	
	    $('#addscoreForm').validate({
	        ignore: "",
	        rules:{
	        	score:{required: true,isnumber:true}
	        },
	        messages:{
	        	score:{required:'试题分数不能为空'}
	        },
	        submitHandler: function (form) {
	            $(form).ajaxSubmit({
	                success: addscoreComplete
	            });
	        }
	    });
    });
    
      function addscoreComplete(data) {
               if (data.success) {
                	layer.close(layer.index);
                    Eymjs.toastr.success('恭喜您!绑定试题成功');
                    try{
                        search();
                        initRightValue(data.input);
                        if(data.ireturn!=9){
                        	searchbtnForBind();
                        }
                    }catch(e){}
                    try{callback && callback.call(this,data);}catch(e){}
                } else {
                    layer.close(layer.index);
                    Eymjs.toastr.error(data.errorinfo);
                    return false;
                }
         }
    
</script>
