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
<div class="page-block bg-blue-img page-header my-header">
    <div class="wrap clearfix">
        <div class="fl page-header-title">
            <i class="fl icon icon-blackboard"></i>
            <h1 class="fl fz-xl fwb t-white">最新课程上线</h1>
        </div>
        <div class="fr swiper-course radius-5 bg-blue-t ovh">
            <div class="swiper-container">
                <div class="swiper-wrapper">
                    <div class="swiper-slide"><a href="#">质数合数质数合数质数合数质数合数质数合数质数合数质数合数质数合数质数合数质数合数质数合数（1）</a></div>
                    <div class="swiper-slide"><a href="#">质数合数（2）</a></div>
                    <div class="swiper-slide"><a href="#">质数合数（3）</a></div>
                </div>

                <!-- 如果需要导航按钮 -->
                <div class="swiper-button-prev e-trans"><i class="icon icon-up"></i></div>
                <div class="swiper-button-next e-trans"><i class="icon icon-down"></i></div>
            </div>
        </div>
    </div>
</div>
<!-- 页头 end -->

<!-- 二级页内容 -->
<div class="page-main bg-gray-img page-block">
    <div class="wrap clearfix">
        <#include "left.ftl"/>
        <div class="fr page-content">

            <div class="radius-5 bg-white ovh mb20 page-content-box">
                <div class="page-content-title clearfix">
                    <h2 class="fl bg-blue t-white fwb fz-l">修改密码</h2>
                </div>

                <div class="my-form">
                    <div class="my-form-row mb20 clearfix">
                        <label class="my-form-label fl">旧密码：</label>
                        <input type="text" class="input-txt fl">
                    </div>
                    <div class="my-form-row mb20 clearfix">
                        <label class="my-form-label fl">新密码：</label>
                        <input type="text" class="input-txt fl">
                    </div>
                    <div class="my-form-row mb20 clearfix">
                        <label class="my-form-label fl">再次输入：</label>
                        <input type="text" class="input-txt fl">
                    </div>
                    <div class="my-form-row">
                        <a href="javascript:;" class="btn btn-blue btn-l">确认修改</a>
                    </div>
                </div>
            </div>


        </div>

    </div>
</div>
<!-- 二级页内容 end -->


<#include "../common/bottom.ftl"/>
<#include "../common/scitpt.ftl"/>
<script>
    var mySwiper = new Swiper ('.swiper-course .swiper-container', {
        direction: 'vertical',
        loop: true,
        autoplay: 3000,
        // 如果需要前进后退按钮
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
    })
</script>
</body>
</html>