<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-4"></div>
        <form class="form-horizontal" id="userVipForm" method="post" action="${adminPath}/user/editvip.json">
            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="username">用户名:</label>
                <div class="col-sm-8">
                    <label style="padding-top: 8px;">${user.username}</label>
                </div>
            </div>
            <div class="space-4"></div>
            <div class="space-2"></div>
                     <div class="form-group">
		                <label class="col-sm-4 control-label no-padding-right" for="isvip">是否vip会员:</label>
		                <div class="col-sm-8">
		                    <label style="padding-top: 5px">
		                     <#if (user.isvip??) && (user.isvip=='1')>
							   <input name="isvip"  id="isvip" class="ace ace-switch ace-switch-5" value="${user.isvip!}"    type="checkbox"    checked />
							 <#else>
							   <input name="isvip"  id="isvip" class="ace ace-switch ace-switch-5" value="${user.isvip!}"   type="checkbox" />
							 </#if>
		                    </label>
		                </div>
		            </div>
		            <div class="space-4" id="isvip_formspace"  style="display: none"></div>
            
            <div class="space-2"></div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="vipstarttime">vip开始时间: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="vipstarttime"  id="vipstarttime" class="form_datetime form-control" type="text" value="${user.vipstarttime!}"  style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" readonly/>
                            </div>
                        </div>
                    </div>
             <div class="space-2"></div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="vipendtime"> vip结束时间: </label>
                        <div class="col-sm-8">
                           <div class="clearfix">
							    <input name="vipendtime"  id="vipendtime" class="form_datetime form-control" type="text" value="${user.vipendtime!}"  style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;"  readonly>
							</div>
                        </div>
                    </div>

            <div class="clearfix form-actions">
                <div class="center">
                    <input name="id" type="hidden"  value="${user.id!}">
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
        $('#userVipForm').ajaxForm({
          //  beforeSubmit:checkPasswordForm,
            success: saveVipComplete
        });
    });
   
    $(".form_datetime").datetimepicker({
	 format: "yyyy-mm-dd hh:mm:ss",
	 autoclose: true,
	 todayBtn: true,
	 todayHighlight: true,
	 showMeridian: true,
	 pickerPosition: "bottom-left",
	 language: 'zh-CN',//中文，需要引用zh-CN.js包
	 startView: 2,//月视图
	 minView: 0//日期时间选择器所能够提供的最精确的时间选择视图
	 }); 

    function saveVipComplete(data) {
        if (data.success) {
            layer.closeAll();
            Eymjs.toastr.success('恭喜您!修改Vip信息成功');
             if(data.flushType==0){
                try{search();}catch(e){console.log(e)};
            }
        } else {
            Eymjs.message.error(data.errorinfo);
            return false;
        }
    }
    
      $('#isvip').bootstrapSwitch({  
    	onText:"是",  
        offText:"否",  
        onColor:"success",  
        offColor:"info",  
        size:"small",  
        onSwitchChange:function(event,state){  
            if(state==true){  
                $('#isvip').val("1");  
            }else{  
                $('#isvip').val("0");  
            }  
        }  
    })  
    
</script>
