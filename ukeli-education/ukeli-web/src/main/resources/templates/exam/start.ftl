<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>优科力</title>
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
            <h1 class="fl fz-xl fwb t-white"><#if clazzDo??>${clazzDo.name!} >> </#if>${examDo.name!} 考试</h1>
        </div>
        <#include "../common/usercenter.ftl"/>
    </div>
</div>
<!-- 页头 end -->

<!-- 二级页内容 -->
<div class="page-main bg-gray-img page-block">
    <div class="wrap clearfix">
        <div class="fl page-aside">
            <div class="bg-blue radius-5 mb10 page-aside-menu">
                <#if list??>
                    <#list  list as exam>
                        <a href="${webPath!}exam/start/${exam.id!}.html" class="btn btn-blue-t btn-l db <#if exam.id==examDo.id> hover</#if>">${exam.name!}</a>
                    </#list>
                </#if>
            </div>
        </div>
        <div class="fr page-content">

            <div class="radius-5 bg-white ovh mb20 page-content-box exam-enter-box">
                <div class="page-content-title clearfix">
                    <h2 class="fl bg-blue t-white fwb fz-l">${examDo.name!}</h2>
                </div>
                <div class="page-content-description">
                    ${examDo.remark!}
                </div>
            </div>
            <div class="tc mb10"><a href="${webPath!}exam/user/${examDo.id}.html" class="btn btn-xl btn-red btn-enterExam">开始考试</a></div>
            <div class="tc mb40">共计 <span class="t-red">${examDo.totalexam!}</span> 题，考试时间 <span class="t-red">${examDo.times!}</span> 分钟</div>


        </div>
    </div>
</div>
<!-- 二级页内容 end -->

<#include "../common/bottom.ftl"/>
<#include "../common/scitpt.ftl"/>
<script src="${resWebUrl!}js/web/index.js"></script>

</body>
</html>