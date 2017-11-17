<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>优科力</title>
    <#include "../common/style.ftl"/>
</head>
<body>
<#include "../common/header.ftl"/>

<!-- 页头 -->
<div class="page-block bg-blue-img page-header">
    <div class="wrap clearfix">
        <div class="fl page-header-title">
            <i class="fl icon icon-blackboard"></i>
            <h1 class="fl fz-xl fwb t-white">${knowledge.nodename!}</h1>
        </div>
        <#include "../common/usercenter.ftl"/>
    </div>
</div>
<!-- 页头 end -->

<!-- 知识点说明 -->
<div class="bg-gray page-block course-description">
    <div class="wrap bg-gray-t radius-5 t-white box-z">
    <!--
        <h2 class="fz-m fwb">本知识点介绍：</h2>
      -->
      ${knowledge.remark}
    </div>
</div>
<!-- 知识点说明 end -->

<!-- 二级页内容 -->
<div class="page-main bg-gray-img page-block">
    <div class="wrap clearfix">
        <div class="fl page-aside">
            <div class="bg-blue radius-5 mb10 page-aside-menu">
            <#if userKnowledge??&&userKnowledge.isdone=='1'>
             <a href="${webPath}knowledge/${knowledge.id}.html" id="knowledge_" class="btn btn-blue-l btn-l db hover over">知识点本源</a>
            <#else>
            <a href="${webPath}knowledge/${knowledge.id}.html" id="knowledge_" class="btn btn-blue-l btn-l db hover ">知识点本源</a>
            </#if>
               
            <#if subjectList??>
	            <#list subjectList as subject>
	               <#if subject.ext_isdone??&&subject.ext_isdone=='1'>
	               <a  href="${webPath}knowledge/subject/${subject.id}.html?knowledgeid=${knowledge.id}" class="btn btn-blue-l btn-l db over ">例${subject_index+1}-${subject.name}</a>
	               <#else>
	               <a  href="${webPath}knowledge/subject/${subject.id}.html?knowledgeid=${knowledge.id}" class="btn btn-blue-l btn-l db ">例${subject_index+1}-${subject.name}</a>
	               </#if>
	            </#list>
            </#if>
            </div>
            
            <div class="bg-green radius-5 mb20 page-aside-menu">
              <a href="${webPath}knowledge/allsubject/${knowledge.id!}.html" class="btn btn-green-l btn-l db hover">${knowledge.nodename}测试卷</a>
            </div>
           <#if userKnowledge??&&"${userKnowledge.isadd}"=='1'>  
               <a href="javascript:;" class="btn btn-orange btn-xl fz-m db btn-addCourse disabled"><i class="icon icon-addCourse"></i><span>已加入改课程</span></a>
            <#else> 
               <a href="javascript:;" class="btn btn-orange btn-xl fz-m db btn-addCourse"><i class="icon icon-addCourse"></i><span>加入我的课程</span></a>
		    </#if> 
        </div>
        <div class="fr page-content">
            <div class="radius-5 bg-white ovh mb10">
                <div class="video-box">
                    <div class="video-inner"></div>
                </div>
                <div class="page-content-box">
                    <div class="page-content-title clearfix">
                        <h2 class="fl bg-blue t-white fwb fz-m">视频描述</h2>
                     <#if  userKnowledge??&&userKnowledge.isdone=='1'>  
                        <a href="javascript:;" class="fr btn btn-m btn-gray btn-learnOver">取消完成该视频标记</a>
                        <input type="hidden" id="isdone" name="isdone" value="1"/>
                      <#else> 
		                <a href="javascript:;" class="fr btn btn-m btn-red btn-learnOver">完成该视频学习</a>
                        <input type="hidden" id="isdone" name="isdone" value="0"/>
		              </#if> 
                    </div>
                    <div class="page-content-description">
                        ${knowledge.videodetail !}
                    </div>
                </div>
            </div>
            <div class="tr mb40"><a href="javacript:;" class="btn btn-t btn-m"><i class="icon icon-print"></i>打印知识本源</a></div>
            
            <input type="hidden" id="userid" name="userid" value="${userid !}">
            <input type="hidden" id="knowledgeid" name="knowledgeid" value="${knowledge.id !}">
            <input type="hidden" id="vtype" name="vtype" value="${questionVtype !}">
            <div class="radius-5 bg-gray03 ovh mb10">
                <div class="page-content-title clearfix page-content-box">
                    <h2 class="fl bg-blue t-white fwb fz-m">在线留言</h2>
                </div>
                <textarea id="txt-comment" style="display: none;"></textarea>
            </div>
            <div class="tr mb20"><a href="" class="btn btn-blue fz-m btn-m saveQuestion">提 交</a></div>
            <ul class="comment-list ovh" id="questionul">
            </ul>
        </div>
    </div>
</div>
<!-- 二级页内容 end -->


<#include "../common/bottom.ftl"/>
<#include "../common/scitpt.ftl"/>
<script>
    layui.use(['layer','layedit'],function(){
        var layer=layui.layer,
                $=layui.jquery,
                layedit = layui.layedit;

       var knowledgeid=$("#knowledgeid").val();
       var userid=$("#userid").val();
        // 加入我的课程
        $('.btn-addCourse').one('click',function(){
           addCourse(knowledgeid,userid);
        });

        // 完成该视频学习
        $('.btn-learnOver').on('click',function(){
           doneKnowledge(knowledgeid,userid);
        })

        // 建立富文本编辑器
       var index=layedit.build('txt-comment',{
            tool:['strong', 'italic', '|' , 'left', 'center', 'right' , '|' , 'image']
        });

        // 查看答案
        $('.btn-checkAnswer').on('click',function(){
            layer.open({
                type: 1,
                title: ['课程解析'],
                skin: 'layui-layer-rim', //加上边框
                area: ['600px', '400px'], //宽高
                content: 'html内容'
            });
        })
        
         // 保存咨询问题
        $('.saveQuestion').on('click',function() {
			    var question = layedit.getText(index);
			   console.info(question);
			    var relationid=$("#knowledgeid").val();
		        var vtype=$("#vtype").val();
			    if(!question){
			        Eymjs.message.error("对不起，请输入您的问题！");
			        return;
			    }
			    Eymjs.data.ajax(Eymjs.url.question.SAVE,{question:question,relationid:relationid,vtype:vtype},function (data) {
			        if(data.success){
			            Eymjs.message.success(data.errorinfo,window.location.reload());
			            return;
			        }else{
			            Eymjs.message.error(data.errorinfo);
			        }
			    });
			});
        
    })
    
     $(function () {
       var relationid=$("#knowledgeid").val();
       var vtype=$("#vtype").val();
       page(vtype,relationid,1);
    })
    
   
    function page(vtype,relationid,page){
	    Eymjs.data.ajax(Eymjs.url.question.LIST,{vtype:vtype,relationid:relationid,page:page},function (data) {
	        if(data.success){
	        var questionhtml='';
	              for(var i=0;i<(data.obj).length;i++){
		              var isanswer=data.obj[i].isanswer;
		              questionhtml=questionhtml+'<li>'+
		                    '<div class="q-box"> '+
		                       ' <h4 class="fwb t-blue">'+data.obj[i].ext_realname+'</h4>'+
		                        '<p>'+data.obj[i].question+'</p>'+
		                    '</div>'
		             if(isanswer==1){
		                     questionhtml=questionhtml+'<div class="q-box">'+
		                       '<h4 class="fwb t-blue">老师回答：</h4>'+
		                       ' <p>'+data.obj[i].answer+'</p>'+
		                    '</div>'+
			                   '</li>';
		                  }
	               }
		         $("#questionul").append(questionhtml);
	        }else{
	        }
	   });
    }
    
     function addCourse(knowledgeid,userid){
		    Eymjs.data.ajax(Eymjs.url.knowledge.ADDCOURSE,{knowledgeid:knowledgeid,userid:userid},function (data) {
				        if(data.success){
				         if(! $('.btn-addCourse').hasClass('disabled')){
				                 $('.btn-addCourse').addClass('disabled').find('span').text('已加入该课程');
				                layer.tips('加入课程成功！请您移步到 <a href="my_course.html">个人中心 - 我的课程</a> 去查看', $('.btn-addCourse'));
				            }
				        }
			   })
	    };
    
    
    function doneKnowledge(knowledgeid,userid){
     var isdone=$("#isdone").val(); 
     Eymjs.data.ajax(Eymjs.url.knowledge.DONESUBJECT,{knowledgeid:knowledgeid,userid:userid,isdone:isdone},function (data) {
	        if(data.success){
	            if($('.btn-learnOver').hasClass('btn-red')){
                    $('.btn-learnOver').addClass('btn-gray').removeClass('btn-red').text('取消完成该视频标记');
                    $("#knowledge_").addClass('over');
                    $("#isdone").val(1); 
	            }else{
	                $('.btn-learnOver').addClass('btn-red').removeClass('btn-gray').text('完成该视频学习');
	                $("#knowledge_").removeClass('over');
	                $("#isdone").val(0); 
	            }
	        }
	   })
    };
    
</script>
</body>
</html>