
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
            <h1 class="fl fz-xl fwb t-white">${examDo.name!}</h1>
        </div>
        <a href="javascript:goBack()" class="fr btn btn-l btn-blue-t bor-w-2">返回考卷首页</a>
    </div>
</div>
<!-- 页头 end -->

<!-- 二级页内容 -->
<div class="page-main bg-gray-img page-block">
    <div class="wrap clearfix">
        <div class="fl page-aside">
            <div class="bg-blue radius-5 mb10 page-aside-menu page-aside-exam">
                <div class="bg-green radius-5 mb10 time-box">
                    <div class="bg-green-t radius-5 t-white tc time-box-inner">
                        <b class="time-h fz-xl">1</b>小时
                        <b class="time-m fz-xl">30</b>分
                        <b class="time-s fz-xl">00</b>秒
                    </div>
                </div>
                <div class="bg-blue-l t-white exam-info mb10 radius-5">
                    <p>本试卷共有<b class="t-orange">${examDo.totalexam!}</b>道题目</p>
                    <p>考试时间为<b class="t-orange">${examDo.times!}</b>分钟</p>
                    <p>共计<b class="t-orange">100</b>分</p>
                </div>
                <!--<a href="javascript:;" class="btn btn-t btn-l db mb10"><i class="icon icon-print"></i>打印本套试卷</a>-->
            </div>
        </div>
        <div class="fr page-content">
            <#if list??>
            <#list list as examvo>
                <div class="radius-5 bg-white ovh mb20 page-content-box quiz-box">
                    <div class="page-content-title clearfix">
                        <h2 class="fl bg-blue t-white fwb fz-l"><em>${examvo_index+1}</em></h2>
                    </div>
                    <div class="quiz-content">
                       <div class="quiz-txt fz-l">${examvo.subjectDo.content}</div>
                       <span style="color: rgb(255, 0, 0);"><#if (examvo.subjectDo.detail)??&&(examvo.subjectDo.detail)!="" >提示：${examvo.subjectDo.detail!} </#if></span>
                       <table>
                        <#list examvo.list as option>
	                       <tr class="quiz-txt fz-l"> <td>${option.optiionname !} :</td> <td>${option.optioncontext !}</td> </tr>
                        </#list>
                       </table>
                    </div>
                    <div class="quiz-form clearfix">
                        <input type="text" name="answer${examvo.subjectDo.id}" class="input-txt fl" placeholder="请输入您的正确答案">
                        <!--
                        <a href="javascript:;" class="btn btn-l fl btn-checkAnswer">查看答案</a>
                        <a href="javascript:;" class="btn btn-orange btn-l fl btn-addQuiz">加入好题本</a>
                        -->
                    </div>
                </div>
            </#list>

            </#if>

            <!--
            <div class="radius-5 bg-white ovh mb20 page-content-box quiz-box">
                <div class="page-content-title clearfix">
                    <h2 class="fl bg-blue t-white fwb fz-l"><em>2</em></h2>
                </div>
                <div class="quiz-content">
                    <div class="quiz-txt fz-l">小明妈妈今年32岁，小明去年4岁。妈妈比小明大_____岁？</div>
                </div>
                <div class="quiz-form clearfix">
                    <input type="text" class="input-txt fl" placeholder="请输入您的正确答案">
                    <a href="javascript:;" class="btn btn-l fl btn-checkAnswer">查看答案</a>
                    <a href="javascript:;" class="btn btn-orange btn-l fl btn-addQuiz">加入好题本</a>
                </div>
            </div>

            <div class="radius-5 bg-white ovh mb20 page-content-box quiz-box">
                <div class="page-content-title clearfix">
                    <h2 class="fl bg-blue t-white fwb fz-l"><em>3</em></h2>
                </div>
                <div class="quiz-content">
                    <div class="quiz-txt fz-l">找规律填数：7、10、17、27、_______、71、115。</div>
                </div>
                <div class="quiz-form clearfix">
                    <input type="text" class="input-txt fl" placeholder="请输入您的正确答案">
                    <a href="javascript:;" class="btn btn-l fl btn-checkAnswer">查看答案</a>
                    <a href="javascript:;" class="btn btn-orange btn-l fl btn-addQuiz">加入好题本</a>
                </div>
            </div>

            <div class="radius-5 bg-white ovh mb20 page-content-box quiz-box">
                <div class="page-content-title clearfix">
                    <h2 class="fl bg-blue t-white fwb fz-l"><em>4</em></h2>
                </div>
                <div class="quiz-content">
                    <div class="quiz-txt fz-l">在下面算式中的所有空格内各填入一个数码，使得算式成立。<span class="t-red">（从上到下依次填写空格内的答案，答案间用空格隔开）</span></div>
                    <img src="images/quiz.png" alt="">
                </div>
                <div class="quiz-form clearfix">
                    <input type="text" class="input-txt fl" placeholder="请输入您的正确答案">
                    <a href="javascript:;" class="btn btn-l fl btn-checkAnswer">查看答案</a>
                    <a href="javascript:;" class="btn btn-orange btn-l fl btn-addQuiz">加入好题本</a>
                </div>
            </div>
            -->

            <div class="tc mb40"><a href="javascript:;" class="btn btn-xl btn-red btn-submitExam">提交你的答案</a></div>


            <input type="hidden" id="examid" name="examid" value="${examDo.id}">
            <input type="hidden" id="vtype" name="vtype" value="${questionVtype}">
            <div class="radius-5 bg-gray03 ovh mb10">
                <div class="page-content-title clearfix page-content-box">
                    <h2 class="fl bg-blue t-white fwb fz-m">在线留言</h2>
                </div>
                <textarea id="question" style="display: none;"></textarea>
            </div>
            <div class="tr mb20"><a href=""  class="btn btn-blue fz-m btn-m saveQuestion">提 交</a></div>
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

        // 加入好题本
        $('.btn-addQuiz').one('click',function(){
            if(!$(this).hasClass('disabled')){
                $(this).addClass('disabled').text('已加入好题本');
            }

        });
         
//	    layedit.set({
//		  uploadImage: {
//		    url: '${webPath}/uploadfile.html' //接口url
//		    ,type: 'post'
//		  }
//		});

        // 建立富文本编辑器
       var index=layedit.build('question',{
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

        // 考试侧边栏悬浮
        examAside();
        $(window).on('scroll',function(){
            examAside();
        });
        function examAside(){
            var sT=$(document).scrollTop();
            if(sT>200){
                $('.page-aside-exam').css({
                    'position':'fixed',
                    'top':'20px',
                    'left':'50%',
                    'margin-left':'-500px'
                });
            }else{
                $('.page-aside-exam').css({
                    'position':'static',
                    'margin-left':0
                });
            }
        }

        // 考试倒计时
        var timer=setInterval(function(){
            var eHour=$('.time-h'),
                    eMin=$('.time-m'),
                    eSec=$('.time-s'),
                    hour=parseInt(eHour.text()),
                    min=parseInt(eMin.text()),
                    sec=parseInt(eSec.text());
            // console.log(hour+' '+min+' '+sec);
            if(sec==0){
                sec=59;
                if(min==0){
                    min=59;
                    if(hour!=0){
                        hour=hour-1;
                    }
                }else{
                    min=min-1;
                }
            }else{
                sec=sec-1;

            }
            if(min<10){
                min='0'+String(min);
            }
            if(sec<10){
                sec='0'+String(sec);
            }

            eHour.text(hour);
            eMin.text(min);
            eSec.text(sec);

            if(hour==0&&min=='00'&&sec=='00'){
                clearInterval(timer);
                layer.alert('考试时间到！！')
            }
        },1000);
        
        
        // 保存咨询问题
        $('.saveQuestion').on('click',function() {
			    var question = layedit.getText(index);
			   console.info(question);
			    var relationid=$("#examid").val();
		        var vtype=$("#vtype").val();
			    if(!question){
			        Eymjs.message.error("对不起，请输入您的问题！");
			        return
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
       var relationid=$("#examid").val();
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
     
      function goBack(){window.history.back();}
    
    
</script>
</body>
</html>