<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-2"></div>
        <form class="form-horizontal"  id="subjectForm" method="post" action="${adminPath}/subject/save.json">
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="stype"> 试题类型: </label>
                        <div class="col-sm-10">
                            <select name="stype"  id="stype"  data-placeholder="试题类型" style="width: 300px;vertical-align:middle;">
			                        <option value='' >----请选择----</option>
			                        <#if clazzDoList ??>
										<#list clazzDoList as item>
				                            <option value='${item.id}'  <#if subject.stype ?? && subject.stype==item.id >selected="selected"</#if> >${item.name}</option>
										</#list>
									</#if>
						   </select>	
                        </div>
                    </div>
                </div>
            </div>
             <div class="space-2"></div>
             
              <div class="row">
             <div class="col-sm-12">
              <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="form-field-1"><span class="red">*</span>题目类别 </label>
                <div class="col-sm-10">
                    <div class="clearfix">
                    <#if clazzDoVtypeList??>
                        <#list clazzDoVtypeList as vtype>
                            <label>
                                <input  class="i-checks"  id="vtype_${vtype.id}" name="vtype" type="checkbox" value="${vtype.id}">
                                <span class="lbl"> ${vtype.name}</span>
                            </label>
                        </#list>
                    </#if>
                    </div>
                </div>
            </div>
           </div>
          </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="name"> 试题名称: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <input name="name"  id="name" class="col-sm-11" readonly value="${subject.name!}" style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row"  name="answerdiv"  style="display: none">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="answer"> 填空答案: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <input name="answer"  id="answer" class="col-sm-11" readonly value="${subject.answer!}" style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
             <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="videopath"> 视频路径: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <input name="videopath"  id="videopath" class="col-sm-11" readonly value="${subject.videopath!}" style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
             <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                     <div id="fileList" class="uploader-list" ></div>
                        <label class="col-sm-2 control-label no-padding-right" for="imgurl">题目图片：</label>
                        <div class="col-sm-10">
                            <div class="input-group  clearfix">
                                <!--<input name="imgurl" id="imgurl" class="form-control" value="${subject.imgurl!}" >-->
                                
                                <img src="${subject.imgurl!}"  alt="题目图片"  />
                                <!--
                                <span class="input-group-btn">
                                        <div id="filePicker" class="filePicker"  style="font-size：12px;">上传图片</div>
                                </span>
                                -->
                             </div>
                         </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="content"> 试题内容: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <textarea class="layui-textarea" id="content" name="content"  style="display: none">${subject.content!}
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
                        <label class="col-sm-2 control-label no-padding-right" for="username"> 试题解析: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <textarea class="layui-textarea" id="analysis" name="analysis" style="display: none">
                                ${subject.analysis!}
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
                        <label class="col-sm-2 control-label no-padding-right" for="detail"> 备注信息: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                 <textarea  id="detail" rows="3" cols="20"  class="col-sm-12" name="detail" readonly >${subject.detail!}
                                 </textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="space-2"></div>
            <div class="clearfix form-actions">
                <div class="col-md-offset-8 col-md-4">
                    <button class="btn btn-info" id="submittype2" type="submit" style="display: none"><i class="ace-icon fa fa-check bigger-110"></i>查看选项</button>
                </div>
            </div>

            <input name="id" id="id" type="hidden" value="${subject.id}" />
        </form>

    </div>
</div>
<script>
    //自定义工具栏
    var layedit = layui.layedit;
    
    layedit.set({
	  uploadImage: {
	    url: '${adminPath}/subject/uploadImag.json' //接口url
	    ,type: 'post' //默认post
	  }
	});
    var index = layedit.build('content',{
       height: 140 //设置编辑器高度
     });
     
     var index2 = layedit.build('analysis',{
       height: 140 //设置编辑器高度
     });
     
      $(document).ready(function () {  
	    
	    <#if subject??>
		    <#if (subject.stype??) && (subject.stype==1||subject.stype==2)>
		        typecheck(1);
		    <#else >
		        typecheck(2);
		    </#if>
		</#if>
	    
	    <#if vtypes??>
	    <#list vtypes as vtype>
	    $("#vtype_${vtype}").iCheck('check');
		 </#list>
		</#if>
		
		 $("#stype").on('change',function () {
	         stypeval=$(this).val(); 
	         console.info("stypeval="+stypeval);
	         if(stypeval==1||stypeval==2){
	          typecheck(1)
	         }else{
	          typecheck(2)
	         }
	    });
		
      });
      
      
       function typecheck(type) {
	    if(type==1){ //选择题 下一步
	        $("[name='answerdiv']").hide();
	        $("#submittype2").show();
	    }else{
	        $("[name='answerdiv']").show();
	        $("#submittype2").hide();
	    }
     }
   
      function nextedit(subjectid){
         console.info(subjectid);
         Eymjs.dialog.showUrl('答案选项',Eymjs.ukeli.url.subject.NEXTEDIT,{subjectid:subjectid,isshow:'true'},760,650);
      }
    
     var $ = jQuery,
       $list = $('#fileList'),
       ratio = window.devicePixelRatio || 1,
       thumbnailWidth = 100 * ratio,
       thumbnailHeight = 100 * ratio;
    
    var uploader = WebUploader.create({
        auto: true,
        swf: '${resWebUrl}/plugin/webuploader/Uploader.swf',
        server: '${adminPath}/uploadfile.html',
        pick: '#filePicker',
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/jpg,image/jpeg,image/png,image/gif'
        }
    });
    
    // 当有文件添加进来的时候
	uploader.on( 'fileQueued', function( file ) {
	    var $li = $(
	            '<div id="' + file.id + '" class="file-item thumbnail">' +
	                '<img>' +
	                '<div class="info">' + file.name + '</div>' +
	            '</div>'
	            ),
	        $img = $li.find('img');
	
	    // $list为容器jQuery实例
	     $list.append( $li );
	
	    // 创建缩略图
	    // 如果为非图片文件，可以不用调用此方法。
	    // thumbnailWidth x thumbnailHeight 为 100 x 100
	    uploader.makeThumb( file, function( error, src ) {
	        if ( error ) {
	            $img.replaceWith('<span>不能预览</span>');
	            return;
	        }
	        $img.attr( 'src', src );
	    }, thumbnailWidth, thumbnailHeight );
	});

	// 文件上传过程中创建进度条实时显示。
	uploader.on( 'uploadProgress', function( file, percentage ) {
	    var $li = $( '#'+file.id ),
	        $percent = $li.find('.progress span');
	    // 避免重复创建
	    if ( !$percent.length ) {
	        $percent = $('<p class="progress"><span></span></p>')
	                .appendTo( $li )
	                .find('span');
	    }
	    $percent.css( 'width', percentage * 100 + '%' );
	});

   // 文件上传成功，给item添加成功class, 用样式标记上传成功。
    uploader.on( 'uploadSuccess', function( file,resporse) {
        if(resporse.success){
    	  $( '#'+file.id ).addClass('upload-state-done');
            $("#vpicurl").val(resporse.url);
            console.info(resporse.url);
            var url=$("#imgurl").val();
            $("#imgurl").val(url+";"+resporse.url);
           // $("#imgLogo").attr('src',imagePath +resporse.url);
        }
    });

	// 文件上传失败，显示上传出错。
	uploader.on( 'uploadError', function( file ) {
	    var $li = $( '#'+file.id ),
	        $error = $li.find('div.error');
	    // 避免重复创建
	    if ( !$error.length ) {
	        $error = $('<div class="error"></div>').appendTo( $li );
	    }
	    $error.text('上传失败');
	});
	
	// 完成上传完了，成功或者失败，先删除进度条。
	uploader.on( 'uploadComplete', function( file ) {
	    $( '#'+file.id ).find('.progress').remove();
	});
</script>

<script src="${adminPath}/res/js/subject/subject.edit.js?ver=${version}"></script>
