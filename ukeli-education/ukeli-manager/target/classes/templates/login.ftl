<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>${productName}后台管理系统--登陆</title>
    <meta name="description" content="User login page" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <#include "common/style.ftl"/>
</head>

<body class="login-layout blur-login">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="center">
                        <h1>
                            <i class="ace-icon fa fa-leaf green"></i>
                            <span class="red">${productName}</span>
                            <span class="white" id="id-text2">后台管理系统</span>
                        </h1>
                    </div>

                    <div class="space-6"></div>

                    <div class="position-relative">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger"><i class="ace-icon fa fa-coffee green"></i>管理员登陆</h4>
                                    <div class="space-6"></div>
                                    <div id="errorInfo" class="alert alert-danger" style="padding: 5px;display:none"></div>
                                    <form>
                                        <fieldset>
                                            <label class="block clearfix">
                                                <span class="block input-icon input-icon-right">
                                                    <input type="text" class="form-control" placeholder="用户名" id="username" />
                                                    <i class="ace-icon fa fa-user"></i>
                                                </span>
                                            </label>

                                            <label class="block clearfix">
                                                <span class="block input-icon input-icon-right">
                                                    <input type="password" class="form-control" placeholder="密码" id="password"/>
                                                    <i class="ace-icon fa fa-lock"></i>
                                                </span>
                                            </label>
                                            <label class="block clearfix">
                                                <span class="block input-icon input-icon-right">
                                                    <span style="float: left"><input type="text" class="form-control" placeholder="验证码" style="width: 170px;" name="vcode" id="vcode"/></span>
                                                    <span style="margin-left: 10px"><a href="javascript:Eymjs.fun.gaptcha('gaptchaImg','${adminPath}')"><img id="gaptchaImg" src="${adminPath}/getvm.html?sign=${validatecode}" width="110" height="34"></a></span>
                                                </span>
                                            </label>

                                            <div class="space"></div>

                                            <div class="clearfix">
                                                <label class="inline">
                                                    <input type="checkbox" class="ace" id="recommend"/>
                                                    <span class="lbl">记住信息</span>
                                                </label>

                                                <button type="button" class="width-35 pull-right btn btn-sm btn-primary" id="doLogin">
                                                    <i class="ace-icon fa fa-key"></i>
                                                    <span class="bigger-110">登陆</span>
                                                </button>
                                            </div>

                                            <div class="space-4"></div>
                                        </fieldset>
                                    </form>

                                </div><!-- /.widget-main -->
                            </div><!-- /.widget-body -->
                        </div><!-- /.login-box -->
                    </div>
                </div>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.main-content -->
</div><!-- /.main-container -->
<#include "common/script.ftl"/>

<script src="${resWebUrl}eymjs/eymjs.login.js?ver=${version}"></script>
</body>
</html>