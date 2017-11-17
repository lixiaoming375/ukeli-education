<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-2"></div>
        <form class="form-horizontal"  id="teacherForm" method="post" action="${adminPath}/teacher/save.json">
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="name"> 教师名称: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <input name="name"  id="name" class="col-sm-11" value="${teacher.name!}" readonly  style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
             <div class="space-2"></div>
             <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="typename"> 科目名称: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <input name="typename"  id="typename" class="col-sm-11" value="${teacher.typename!}" readonly style="width:300px; height:30px;vertical-align:middle; border:1px solid #d1d1d2;" />
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
                     
                        <label class="col-sm-2 control-label no-padding-right" for="imageurl">教师图片：</label>
                        <div class="col-sm-10">
                             <img src="${teacher.imageurl!}"  alt="教师图片"  />
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
                                 <textarea  id="detail" rows="8" class="col-sm-12" name="detail" readonly >${teacher.detail!}
                                 </textarea>
                            </div>
                        </div>
                    </div>
	                </div>
	           </div>
             <div class="space-2"></div>
 			
            <input name="id" id="id" type="hidden" value="${teacher.id}" />
        </form>

    </div>
    
</div>


<script src="${adminPath}/res/js/teacher/teacher.edit.js?ver=${version}"></script>

<script>
 
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
            var url=$("#imageurl").val();
            $("#imageurl").val(url+";"+resporse.url);
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

