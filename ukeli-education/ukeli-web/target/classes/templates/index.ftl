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
<!-- banner -->
<div class="banner page-block">
    <div class="wrap">
    <#if userDo??>
        <!-- 登录后 -->
        <div class="banner-box bg-white radius-5 fr">
            <div class="my-info clearfix">
                <a href="#" class="fl my-img">
                    <span class="my-msg-count bg-red radius-round fz-s t-white tc">99</span>
                    <div class="img-box radius-round"><img src="images/user_default.png" alt=""></div>
                </a>

                <div class="fl txt-box">
                    <h4 class="fwb fz-l t-gray my-name">鸡毛匠人</h4>
                    <div class="my-info-other">
                        <span class="my-star"><i class="icon icon-star"></i>40</span>
                        <a href="#" class="btn btn-s radius-3">退出</a>
                    </div>
                </div>
            </div>

            <ul class="my-link-list clearfix">
                <li class="fl my-link01"><a href="${webPath!}user/index.html"><i class="icon icon-user01"></i>个人中心</a></li>
                <li class="fl my-link02"><a href="#"><i class="icon icon-user02"></i>我的课程</a></li>
                <li class="fl my-link03"><a href="#"><i class="icon icon-user03"></i>VIP信息</a></li>
                <li class="fl my-link04"><a href="#"><i class="icon icon-user04"></i>我的答疑</a></li>
            </ul>

        </div>
        <!-- 登录后 end -->
    <#else >
        <!-- 登录前 -->
        <div class="banner-box bg-white radius-5 fr">
            <div class="login">
                <div class="login-txt-uid login-txt">
                    <i class="icon icon-uid"></i>
                    <input type="text" class="db input-txt" id="username" placeholder="请输入手机号码">
                </div>
                <div class="login-txt-pwd login-txt">
                    <i class="icon icon-pwd"></i>
                    <input type="password" class="db input-txt" id="password" placeholder="请输入密码">
                </div>
                <div class="tr fz-s login-getPwd"><a href="#">找回密码</a></div>
                <a href="#" onclick="doLogin();" class="btn btn-l btn-orange db">登 录</a>
                <div class="tc login-goSignup"><a href="#">注册即享20分钟免费试听体验</a></div>
            </div>
        </div>
        <!-- 登录前 end -->
    </#if>
    </div>
</div>
<!-- banner end -->

<!-- 我们的优势 -->
<div class="page-block index-advantage">
    <div class="wrap">
        <div class="page-title t-blue">
            <h4 class="fz-s">OUT ADVANTAGE</h4>
            <h2 class="fz-xl fwb">我们的优势</h2>
            <div class="page-title-under bg-blue"></div>
        </div>

        <div class="tc">
            <div class="dib index-advantage-item">
                <i class="icon-advantage01"></i>
                <h3 class="t-blue fz-m fwb">学习+复习=滚动式<br>学习体验</h3>
                <p>所有知识点和课程对会员开放<br>便于孩子随时复习</p>
            </div>
            <div class="dib index-advantage-item">
                <i class="icon-advantage02"></i>
                <h3 class="t-blue fz-m fwb">时间碎片化<br>学习方式</h3>
                <p>将时间碎片化学习，举一反三<br>孩子边学边巩固</p>
            </div>
            <div class="dib index-advantage-item">
                <i class="icon-advantage03"></i>
                <h3 class="t-blue fz-m fwb">24小时在线答疑<br>解放家长</h3>
                <p>每道题都有留言板，不懂就问<br>随手留言，24小时之内答疑</p>
            </div>
        </div>

    </div>

</div>
<!-- 我们的优势 end -->

<!-- 精选课程 -->
<div class="page-block index-course bg-orange-img">
    <div class="wrap">
        <div class="page-title t-white">
            <h4 class="fz-s">EXCELLENT COURSES</h4>
            <h2 class="fz-xl fwb">精选课程</h2>
            <div class="page-title-under bg-white"></div>
        </div>

        <ul class="card-list clearfix">
            <li class="fl hover-motion bg-white radius-5 e-trans">
                <span class="card-tag bg-red bor-w-1 radius-3 t-white">HOT</span>
                <a href="#" class="card-img radius-5 db ovh">
                    <div class="img-box"><img src="images/img_default.jpg" alt=""></div>
                    <h5 class="tc">摸彩球</h5>
                </a>
            </li>
            <li class="fl hover-motion bg-white radius-5 e-trans">
                <span class="card-tag bg-green bor-w-1 radius-3 t-white">NEW</span>
                <a href="#" class="card-img radius-5 db ovh">
                    <div class="img-box"><img src="images/img_default.jpg" alt=""></div>
                    <h5 class="tc">摸彩球</h5>
                </a>
            </li>
            <li class="fl hover-motion bg-white radius-5 e-trans">
                <a href="#" class="card-img radius-5 db ovh">
                    <div class="img-box"><img src="images/img_default.jpg" alt=""></div>
                    <h5 class="tc">摸彩球</h5>
                </a>
            </li>
            <li class="fl hover-motion bg-white radius-5 e-trans">
                <a href="#" class="card-img radius-5 db ovh">
                    <div class="img-box"><img src="images/img_default.jpg" alt=""></div>
                    <h5 class="tc">摸彩球</h5>
                </a>
            </li>
            <li class="fl hover-motion bg-white radius-5 e-trans">
                <a href="#" class="card-img radius-5 db ovh">
                    <div class="img-box"><img src="images/img_default.jpg" alt=""></div>
                    <h5 class="tc">摸彩球</h5>
                </a>
            </li>
            <li class="fl hover-motion bg-white radius-5 e-trans">
                <a href="#" class="card-img radius-5 db ovh">
                    <div class="img-box"><img src="images/img_default.jpg" alt=""></div>
                    <h5 class="tc">摸彩球</h5>
                </a>
            </li>
            <li class="fl hover-motion bg-white radius-5 e-trans">
                <a href="#" class="card-img radius-5 db ovh">
                    <div class="img-box"><img src="images/img_default.jpg" alt=""></div>
                    <h5 class="tc">摸彩球</h5>
                </a>
            </li>
            <li class="fl hover-motion bg-white radius-5 e-trans">
                <a href="#" class="card-img radius-5 db ovh">
                    <div class="img-box"><img src="images/img_default.jpg" alt=""></div>
                    <h5 class="tc">摸彩球</h5>
                </a>
            </li>
        </ul>

    </div>

</div>
<!-- 精选课程 end -->

<!-- 会员购买 -->
<div class="page-block index-course bg-gray-img">
    <div class="wrap">
        <div class="page-title t-gray">
            <h4 class="fz-s">VIP PURCHASE</h4>
            <h2 class="fz-xl fwb">会员购买</h2>
            <div class="page-title-under bg-gray"></div>
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
<!-- 会员购买 end -->

<!-- 答疑 -->
<div class="page-block index-q bg-blue">
    <div class="wrap">
        <div class="q-box">
            <h4 class="fz-m t-white">Q1：孩子学不会咋办？讲到你会为止!</h4>
            <p class="t-blue-white">A1：不懂就问，随手留言，24小时内答疑，让孩子不带疑问的扎扎实实学好每一个知识点。</p>
        </div>
        <div class="q-box">
            <h4 class="fz-m t-white">Q2：如何知道孩子学没学啊？</h4>
            <p class="t-blue-white">A2：我们每个知识点课程都有自测卷A/B，晚上回家打印自测卷就可以检查孩子成果，一看便知。我们还有各年级月考，以及竞赛前的模拟考，都可以检验孩子是否学了，是否学会了。</p>
        </div>
        <div class="q-box">
            <h4 class="fz-m t-white">Q3：怎么学啊？</h4>
            <p class="t-blue-white">A3：我们会针对不同程度的孩子分享课表，也有经验分享论坛供大家交流学习经验。</p>
        </div>
        <div class="q-box">
            <h4 class="fz-m t-white">Q4：对孩子学成啥水平心里没底啊？</h4>
            <p class="t-blue-white">A4：我们所有随堂练习、自测卷、月考都有正确率统计，可以随时看到孩子在所有学员中的位置，而且该数据也能鼓励孩子一路挑战前行。</p>
        </div>

        <a href="#" class="btn btn-xl btn-green bor-w-2">我要提问</a>

    </div>
</div>
<!-- 答疑 end -->
<#include "common/bottom.ftl"/>
<#include "common/scitpt.ftl"/>
<script src="${resWebUrl!}js/web/index.js"></script>
</body>
</html>