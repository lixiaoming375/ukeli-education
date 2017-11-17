<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>优科力 -- 在线答疑 </title>
    <#include "common/style.ftl"/>
</head>
<body>

<#include "common/header.ftl"/>
<!-- 页头 -->
<div class="page-block bg-blue-img page-header">
    <div class="wrap clearfix">
        <div class="fl page-header-title">
            <i class="fl icon icon-blackboard"></i>
            <h1 class="fl fz-xl fwb t-white">在线答疑</h1>
        </div>
        <#include "common/usercenter.ftl"/>
    </div>
</div>
<!-- 页头 end -->

<div class="page-block bg-gray online-q-block">
    <div class="wrap">
        <div class="online-q-wrap radius-5 ovh bg-white mb20">
            <div class="online-q-wrap-txt">
                <textarea placeholder="在线留言" id="question"></textarea>
            </div>

        </div>
        <div class="tr"><a href="javascript:;" onclick="saveQuestion();" class="btn btn-red btn-l bor-w-2">提 交</a></div>
        <input type="hidden" id="vtype" name="vtype" value="${questionVtype !}">
    </div>

</div>

<!-- 二级页内容 -->
<div class="page-main bg-gray-img page-block">
    <div class="wrap">
        <ul class="comment-list ovh" id="questionul">
        <!--
            <#if list??>

            <#list list as question>

                <li>
                    <div class="q-box">
                        <h4 class="fwb t-blue">${question.ext_realname!}：</h4>
                        <p>${question.question!}</p>
                    </div>
                    <#if question.isanswer =="1">
                    <div class="q-box">
                        <h4 class="fwb t-blue">老师回答：</h4>
                        <p>${question.answer!}</p>
                    </div>
                    </#if>
                </li>
            </#list>

            </#if>
           --> 
        </ul>

        <!-- 分页 
        <div class="pageNav tc"><div id="pageNav01"></div></div>
        -->
        <div class="pageNav tc" id="pageNav"></div>

    </div>
</div>
<!-- 二级页内容 end -->
<#include "common/bottom.ftl"/>
<#include "common/scitpt.ftl"/>
<script src="${resWebUrl!}laypage/laypage.js"></script>
<script src="${resWebUrl!}js/web/question.js"></script>

</body>
</html>