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


            <div class="radius-5 bg-white ovh mb20">
                <div class="page-content-title clearfix page-content-box">
                    <h2 class="fl bg-red t-white fwb fz-l">会员到期时间：2018-09-27 23:59:00</h2>
                </div>
            </div>

            <ul class="card-list clearfix">
                <li class="fl hover-motion bg-white radius-5 e-trans ovh vip-card">
                    <h3 class="vip-card-type fwb t-white bg-gray tc fz-m">年会员</h3>
                    <div class="vip-card-prize tc">
                        <h4>¥<b class="fz-xxl">2600.00</b></h4>
                        <h6>原价：¥7920.00</h6>
                        <h5 class="t-red">折扣33%</h5>
                    </div>
                    <ul class="vip-service-list">
                        <li class="bg-gray02"><i class="bg-gray dib radius-round"></i>观看全部课程</li>
                        <li class="bg-gray03"><i class="bg-gray dib radius-round"></i>在线考试</li>
                        <li class="bg-gray04"><i class="bg-gray dib radius-round"></i>在线答疑</li>
                    </ul>
                    <a href="#" class="btn btn-green btn-m btn-round db">立即购买</a>
                </li>
                <li class="fl hover-motion bg-white radius-5 e-trans ovh vip-card">
                    <h3 class="vip-card-type fwb t-white bg-gray tc fz-m">半年会员</h3>
                    <div class="vip-card-prize tc">
                        <h4>¥<b class="fz-xxl">1860.00</b></h4>
                        <h6>原价：¥3960.00</h6>
                        <h5 class="t-red">折扣47%</h5>
                    </div>
                    <ul class="vip-service-list">
                        <li class="bg-gray02"><i class="bg-gray dib radius-round"></i>观看全部课程</li>
                        <li class="bg-gray03"><i class="bg-gray dib radius-round"></i>在线考试</li>
                        <li class="bg-gray04"><i class="bg-gray dib radius-round"></i>在线答疑</li>
                    </ul>
                    <a href="#" class="btn btn-green btn-m btn-round db">立即购买</a>
                </li>
                <li class="fl hover-motion bg-white radius-5 e-trans ovh vip-card">
                    <h3 class="vip-card-type fwb t-white bg-gray tc fz-m">3月会员</h3>
                    <div class="vip-card-prize tc">
                        <h4>¥<b class="fz-xxl">1260.00</b></h4>
                        <h6>原价：¥1980.00</h6>
                        <h5 class="t-red">折扣64%</h5>
                    </div>
                    <ul class="vip-service-list">
                        <li class="bg-gray02"><i class="bg-gray dib radius-round"></i>观看全部课程</li>
                        <li class="bg-gray03"><i class="bg-gray dib radius-round"></i>在线考试</li>
                        <li class="bg-gray04"><i class="bg-gray dib radius-round"></i>在线答疑</li>
                    </ul>
                    <a href="#" class="btn btn-green btn-m btn-round db">立即购买</a>
                </li>
                <li class="fl hover-motion bg-white radius-5 e-trans ovh vip-card">
                    <h3 class="vip-card-type fwb t-white bg-gray tc fz-m">月会员</h3>
                    <div class="vip-card-prize tc">
                        <h4>¥<b class="fz-xxl">660.00</b></h4>
                        <h6>原价：¥660.00</h6>
                        <h5 class="t-red">折扣100%</h5>
                    </div>
                    <ul class="vip-service-list">
                        <li class="bg-gray02"><i class="bg-gray dib radius-round"></i>观看全部课程</li>
                        <li class="bg-gray03"><i class="bg-gray dib radius-round"></i>在线考试</li>
                        <li class="bg-gray04"><i class="bg-gray dib radius-round"></i>在线答疑</li>
                    </ul>
                    <a href="#" class="btn btn-green btn-m btn-round db">立即购买</a>
                </li>
            </ul>


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