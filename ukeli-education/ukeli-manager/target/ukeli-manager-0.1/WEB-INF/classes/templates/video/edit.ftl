<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-2"></div>
        <form class="form-horizontal"  id="videoForm" method="post" action="${adminPath}/video/save.json">
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="videoname"> 视频名称: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="videoname"  id="videoname" class="col-sm-11" value="${video.videoname!}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="videotype"> 视频类型: </label>
                        <div class="col-sm-8">
                              <div class="search_input">
								<select name="videotype" id="videotype" data-placeholder="请选择" style="min-width: 120px;vertical-align:middle;">
										<option value='' >请选择</option>
										<option value='1' <#if video.videotype ?? && video.videotype ==1>selected="selected"</#if> >MP4</option>
										<option value='2' <#if video.videotype ?? && video.videotype ==2>selected="selected"</#if> >m3u8</option>
								</select>
							</div>
                            
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="videourl"> 视频URL: </label>
                        <div class="col-sm-8">
                              <div class="clearfix">
                                <input name="videourl"  id="videourl" class="col-sm-11" value="${video.videourl!}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="videotime"> 视频时长: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <input name="videotime"  id="videotime" class="col-sm-11" value="${video.videotime!}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
             <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right" for="remark"> 视频描述: </label>
                        <div class="col-sm-8">
                            <div class="clearfix">
                                <textarea  name="detail"  id="detail" class="col-sm-11" >
                                ${video.detail!}
                                </textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="space-2"></div>

            <div class="clearfix form-actions">
                <div class="col-md-offset-4 col-md-8">
                    <button class="btn btn-info" type="submit"><i class="ace-icon fa fa-check bigger-110"></i>保存</button>
                    &nbsp; &nbsp; &nbsp;
                    <button class="btn" type="reset"><i class="ace-icon fa fa-undo bigger-110"></i>重置</button>
                </div>
            </div>
            
            <input name="id" type="hidden" value="${video.id!}">
        </form>

    </div>
</div>

<script src="${adminPath}/res/js/video/video.edit.js?ver=${version}"></script>
