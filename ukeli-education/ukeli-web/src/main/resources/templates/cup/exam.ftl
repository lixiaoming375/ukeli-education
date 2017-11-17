
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
            <h1 class="fl fz-xl fwb t-white">${cup.name!}</h1>
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
                    <p>本试卷共有<b class="t-orange">${cup.totalsubject!}</b>道题目</p>
                    <p>考试时间为<b class="t-orange">${cup.times!}</b>分钟</p>
                    <p>共计<b class="t-orange">100</b>分</p>
                </div>
                <!--<a href="javascript:;" class="btn btn-t btn-l db mb10"><i class="icon icon-print"></i>打印本套试卷</a>-->
            </div>
        </div>
        <div class="fr page-content">
            <#if list??>
            <#list list as cupvo>
                <div class="radius-5 bg-white ovh mb20 page-content-box quiz-box">
                    <div class="page-content-title clearfix">
                        <h2 class="fl bg-blue t-white fwb fz-l"><em>${cupvo_index+1}</em></h2>
                    </div>
                    <div class="quiz-content">
                       <div class="quiz-txt fz-l">${cupvo.subjectDo.content}</div>
	                       <span style="color: rgb(255, 0, 0);"><#if (cupvo.subjectDo.detail)??&&(cupvo.subjectDo.detail)!="" >提示：${cupvo.subjectDo.detail!} </#if></span>
	                       <table>
	                        <#list cupvo.list as option>
		                       <tr class="quiz-txt fz-l"> <td>${option.optiionname !} :</td> <td>${option.optioncontext !}</td> </tr>
	                        </#list>
	                       </table>
                    </div>
                    <div class="quiz-form clearfix">
                        <input type="text" name="answer${cupvo.subjectDo.id}" class="input-txt fl" placeholder="请输入您的正确答案">
                    </div>
                </div>
            </#list>

            </#if>

            <div class="tc mb40"><a href="javascript:;" class="btn btn-xl btn-red btn-submitExam">提交你的答案</a></div>


            <input type="hidden" id="examid" name="examid" value="${cup.id}">
           
            <div class="tr mb20"><a href="javacript:;" class="btn btn-blue fz-m btn-m">提 交</a></div>
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

        // 建立富文本编辑器
        layedit.build('txt-comment',{
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


    })
    
    $(function () {
       var relationid=$("#examid").val();
       var vtype=$("#vtype").val();
      // page(vtype,relationid,1);
    })
    
    
   
    function page(vtype,relationid,page){
	    Eymjs.data.ajax(Eymjs.url.question.LIST,{vtype:vtype,relationid:relationid,page:page},function (data) {
	        if(data.success){
	        var questionhtml='';
	              for(var i=0;i<(data.obj).length;i++){
	               questionhtml=questionhtml+'<li>'+
	                    '<div class="q-box"> '+
	                       ' <h4 class="fwb t-blue">'+data.obj[i].ext_realname+'</h4>'+
	                        '<p>'+data.obj[i].question+'</p>'+
	                    '</div>'+
	                    '<div class="q-box">'+
	                       '<h4 class="fwb t-blue">老师回答：</h4>'+
	                       ' <p>'+data.obj[i].answer+'</p>'+
	                    '</div>'+
	                   '</li>';
	              }
	               $("#questionul").append(questionhtml);
	        }else{
	        }
	   });
    };
    
    function goBack(){window.history.back();}
    
</script>
</body>
</html>