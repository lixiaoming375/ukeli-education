<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-2"></div>
       <form class="form-horizontal"  id="userForm" method="post" action="${adminPath}/user/save.json">
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 用户名: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="username"  id="username" readonly class="col-sm-11" value="${user.username!}" style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="nickname"> 昵称: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="nickname"  id="nickname" readonly  class="col-sm-11" value="${user.nickname!}" style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 真实姓名: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="realname"  id="realname" readonly class="col-sm-11" value="${user.realname!}" style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="bidthday"> 出生年月: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="bidthday"  id="bidthday"  readonly class="form_datetime form-control" type="text" value="${user.bidthday!}"  style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
            <div class="row">
                <div class="col-sm-12">
                     <div class="form-group" id="issku_formgroup">
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
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 联系电话: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="telephone"  id="telephone" readonly class="col-sm-11" value="${user.telephone!}" style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 手机号码: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="mobile"  id="mobile" readonly class="col-sm-11" value="${user.mobile!}" style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 电子邮件: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="email"  id="email" readonly class="col-sm-11" value="${user.email!}" style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
              <div class="row">
                <div class="col-sm-12">
                     <div class="form-group" id="issku_formgroup">
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
                </div>
            </div>
            
            <div class="space-2"></div>
            
              <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="vipstarttime">vip开始时间: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="vipstarttime"  id="vipstarttime" class="form_datetime form-control" type="text" value="${user.vipstarttime!}"  style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
             <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> vip结束时间: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                            <input name="vipendtime"  id="vipendtime" class="form_datetime form-control" type="text" value="${user.vipendtime!}"  style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="username"> 邮编: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="postcode" readonly  id="postcode" class="col-sm-11" value="${user.postcode!}" style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
             <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="address"> 地址信息: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                               <textarea  id="address" rows="3" class="col-sm-12" name="address"  >${user.address!}
                                </textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="detail"> 备注: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <textarea  id="detail" rows="3" class="col-sm-12" name="detail"  >${user.detail!}
                                </textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
            <input name="id" id="id" type="hidden" value="${user.id !}" />
        </form>


    </div>
</div>


<script src="${adminPath}/res/js/user/user.edit.js?ver=${version}"></script>


