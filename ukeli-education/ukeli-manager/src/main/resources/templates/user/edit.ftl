<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-2"></div>
        <form class="form-horizontal"  id="userForm" method="post" action="${adminPath}/user/save.json">
                    
           <#if edit>
            <div class="form-group">
              <label class="col-sm-4 control-label no-padding-right" for="username"> 用户名: </label>
               <div class="col-sm-8">
	                 <div class="clearfix">
	                  <input type="text" name="username"  id="username" class="col-sm-11" value="${user.username!}"  placeholder="必填:用户名为手机号码" style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
	                 </div>
                </div>
            </div>
                
            <#else>
            
            <div class="form-group">
              <label class="col-sm-4 control-label no-padding-right" for="username"> 用户名: </label>
               <div class="col-sm-8">
	                 <div class="clearfix">
	                  <input type="text" name="username"  id="username" class="col-sm-11" value="${user.username!}"  placeholder="必填:用户名为手机号码" style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
	                 </div>
                </div>
            </div>

            <div class="space-2"></div>
             <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="password"> 密码: </label>
                    <div class="col-sm-8">
                       <div class="clearfix">
                          <input type="password" name="password" id="password" placeholder="必填:密码"  class="col-sm-10"  style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                       </div>
                    </div>
             </div>
            </#if>          
                    

            <div class="space-2"></div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="nickname"> 昵称: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input type="text" name="nickname"  id="nickname" class="col-sm-11" value="${user.nickname!}" style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
             
            <div class="space-2"></div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="realname"> 真实姓名: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input type="text" name="realname"  id="realname" class="col-sm-11" value="${user.realname!}" style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>

            <div class="space-2"></div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="bidthday"> 出生年月: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="bidthday"  id="bidthday" class="form_datetime1 form-control" type="text" value="${user.bidthday!}"  style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" readonly/>
                            </div>
                        </div>
                    </div>

            <div class="space-2"></div>
                     <div class="form-group">
		                <label class="col-sm-4 control-label no-padding-right" for="sex">性别:</label>
		                <div class="col-sm-8">
		                    <label style="padding-top: 5px">
							    <#if (user.sex??) && (user.sex=='1')>
							      <input name="sex"  id="sex" class="ace ace-switch ace-switch-5" value="${user.sex!}"    type="checkbox"    checked />
							    <#else>
							       <input name="sex"  id="sex" class="ace ace-switch ace-switch-5" value="${user.sex!}"   type="checkbox" />
							    </#if>
		                    </label>
		                </div>
		            </div>
		            <div class="space-4" id="user_formspace"  style="display: none"></div>

            <div class="space-2"></div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="telephone"> 联系电话: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input type="text" name="telephone"  id="telephone" class="col-sm-11" value="${user.telephone!}" style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>

            <div class="space-2"></div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="mobile"> 手机号码: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input type="text" name="mobile"  id="mobile" class="col-sm-11" value="${user.mobile!}" style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>

            <div class="space-2"></div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="email"> 电子邮件: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input type="text" name="email"  id="email" class="col-sm-11" value="${user.email!}" style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                    
                    
            <#if edit>
           
            <#else>
           <div class="space-2"></div>
                     <div class="form-group">
		                <label class="col-sm-4 control-label no-padding-right" for="isvip">是否vip会员:</label>
		                <div class="col-sm-8">
		                    <label style="padding-top: 5px">
							      <input name="isvip"  id="isvip" class="ace ace-switch ace-switch-5" value="${user.isvip!}"    type="checkbox"    checked />
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
            </#if>                   
                    

            
            <div class="space-2"></div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="postcode"> 邮编: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input type="text" name="postcode"  id="postcode" class="col-sm-11" value="${user.postcode!}" style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;"/>
                            </div>
                        </div>
                    </div>
            <div class="space-2"></div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="address"> 地址信息: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                               <textarea  id="address" rows="3" class="col-sm-12" name="address"  >${user.address!}
                                </textarea>
                            </div>
                        </div>
                    </div>
            <div class="space-2"></div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="detail"> 备注: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <textarea  id="detail" rows="3" class="col-sm-12" name="detail"  >${user.detail!}
                                </textarea>
                            </div>
                        </div>
                    </div>
            <div class="space-2"></div>
            <div class="clearfix form-actions">
                <div class="col-md-offset-6 col-md-6">
                    <button class="btn btn-info" type="submit"><i class="ace-icon fa fa-check bigger-110"></i>保存</button>
                    &nbsp; &nbsp; &nbsp;
                    <button class="btn" type="reset"><i class="ace-icon fa fa-undo bigger-110"></i>重置</button>
                </div>
            </div>
            
            <input name="id" id="id" type="hidden" value="${user.id !}" />
        </form>

    </div>
</div>

<script src="${adminPath}/res/js/user/user.edit.js?ver=${version}"></script>
<script type="text/javascript">
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
	 
    $(".form_datetime1").datetimepicker({
        format: "yyyy-mm-dd",
		 autoclose: true,
		 todayBtn: true,
		 todayHighlight: true,
		 showMeridian: true,
		 pickerPosition: "bottom-left",
		 language: 'zh-CN',//中文，需要引用zh-CN.js包
		 startView: 2,//月视图
		 minView: 2//日期时间选择器所能够提供的最精确的时间选择视图
    });
    
    
   var d = new Date();
	var startTime = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate()+" " + d.getHours() + ":" + d.getMinutes()+ ":" + d.getSeconds();
	var endTime = d.getFullYear()+"-"+(d.getMonth()+2)+"-"+d.getDate()+" " + d.getHours() + ":" + d.getMinutes()+ ":" + d.getSeconds();
    
    $("#vipstarttime").val(startTime);
    $("#vipendtime").val(endTime);
    
</script>
