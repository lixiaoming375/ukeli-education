
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>优科力</title>
    <#include "common/style.ftl"/>
</head>
<body>

<#include "common/header.ftl"/>

<!-- 二级页内容 -->
<div class="page-main bg-gray-img page-block page-error page-forbidden">
    <div class="wrap">

        <p class="fz-xl fwb tc mb40">对不起，您的账号可能在其他地方登陆了，请您确认您的账号安全性</p>

        <div class="tc"><!--<a href="index.html" class="btn btn-orange bor-w-2 btn-xl" style="margin-right:40px;">成为会员</a>--><a href="${webPath!}index.html" class="btn bor-w-2 btn-xl">返回首页</a></div>

    </div>
</div>
<!-- 二级页内容 end -->


<!-- 答疑 end -->
<#include "common/bottom.ftl"/>
<#include "common/scitpt.ftl"/>
<script src="${resWebUrl!}js/web/index.js"></script>
</body>
</html>