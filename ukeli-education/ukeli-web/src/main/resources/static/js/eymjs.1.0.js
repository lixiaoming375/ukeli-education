/**
 * Created by guming on 2017/1/6.
 */
var Eymjs;
window.Eymjs = Eymjs = {};
Eymjs.VERSION = "1.0.0";

Eymjs.common ={
    http:{
        POST : 'post',
        GET :'get'
    },
    dataType :  {
        JSON :'json'
    },
    info  :{
        MESSAGE : '消息提示',
        ERROR_MESSAGE: '错误提示信息'
    }
};

Eymjs.url={
    user:{
        login : webPath+'login.json'
    },
    question:{
        SAVE : webPath+'question/save.json',
        LIST : webPath+'question/list.json'
    },
    subject:{
    	ADDCOURSE:webPath+'subject/addcourse.json',
    	DONESUBJECT:webPath+'subject/donesubject.json'
    },
    knowledge:{
    	ADDCOURSE:webPath+'knowledge/addcourse.json',
    	DONESUBJECT:webPath+'knowledge/doneknowledge.json'
    }
};

/**
 * 提交数据用的
 * @type {{ajaxJson: Eymjs.data.ajaxJson}}
 */
Eymjs.data = {
    /**
     * 封装了一个ajax的请求数据
     * @param url 请求地址
     * @param params 参数
     * @param callback 会掉函数
     * @param type  类型，默认为POST
     * @param dataType  数据类型。默认为json
     * @param isLoading  是否战士load
     * @param loadid
     */
    ajax:function(url,params,callback,isLoading,type,dataType){
        type =type ||Eymjs.common.http.POST;
        dataType =dataType || Eymjs.common.dataType.JSON;
        isLoading =isLoading || false;
        var index=0;
        $.ajax({
            url:url,
            data:params,
            type:type,
            dataType:dataType,
            beforeSend:function(jqXHR, textStatus){
                if(isLoading){
                    index =layer.load(1,{shade: [0.7, '#333'],maxWidth :37});
                }
            },
            complete:function(jqXHR, textStatus){
                if(isLoading){
                    layer.close(index);
                }
            },
            success:function(data){
                try{
                    callback && callback.call(this,data);
                }catch(e){}
            },
            error:function(data){
                console.log("data="+data);
            }
        });
    }
};


/**
 * 定义的基础函数，主要是提供一些基础的方法
 * @type {{gaptcha: gaptcha}}
 */
Eymjs.fun = {
    gaptcha :function (imgid,adminPath) {
        var sign="";
        for(var i=0;i<40;i++){
            sign = sign + Math.round(Math.random());
        };
        $("#"+imgid).attr("src",adminPath+'/getvm.html?sign='+sign);
    }
};

Eymjs.page = {
    /**
     * 初始化代码
     * @param url
     * @param params
     * @param tplid
     */
    show:function (url,params,tplid) {
        Eymjs.data.ajax(url,params,function (data) {
            var render = template.compile(tplid);
            var html = render(data);
            document.getElementById('ajax-data').innerHTML = html;
            Eymjs.form.checkInit();
            Eymjs.page.page(data.totalRecords,data.currentPage,data.totalPage);
        },true);
    },
    page:function (totalRecord,page,totalPage) {
        $("#pager .fl").html("总共 "+totalRecord+" 条数据，当前第 "+page+" 页，总共 "+totalPage+" 页");
        var pageHtml ="";
        var iPage = parseInt(page);
        var iTotalPage =parseInt(totalPage);
        var start =page-5;
        var end = page +5;
        if(start < 0){
            start =1;
        }
        if(end > iTotalPage){
            end =iTotalPage;
        }
        if((iPage -1)>0){
            pageHtml += '<li><a href="javascript:page('+(iPage -1)+');">«</a></li>';
        }else{
            pageHtml += '<li class="disabled"><span>«</span></li>';
        }
        for(var i= start;i<=end;i++){
            if(i ==iPage){
                pageHtml +='<li class="active"><span>'+i+'</span></li>';
            }else{
                pageHtml +='<li><a href="javascript:page('+i+');">'+i+'</a></li>';
            }
        }
        if(iPage == iTotalPage){
            pageHtml += '<li class="disabled"><span>»</span></li>';
        }else{
            var nextPage = iPage+1;
            if(iTotalPage >= nextPage){
                pageHtml += '<li><a href="javascript:page('+nextPage+');">»</a></li>';
            }else{
                pageHtml += '<li class="disabled"><span>»</span></li>';
            }
        }
        $(".pagination").html(pageHtml);
    },
    init : function () {
        //初始化右侧右侧列表展示
        var url =location.pathname;
        try{
            var teamurl =url.replace(adminPath,"");
            if(teamurl.startsWith("/")){
                teamurl =teamurl.substr(1);
            }
            if(teamurl.indexOf(".") > 0){
                teamurl =teamurl.substr(0,teamurl.indexOf("."));
            }
            teamurl =teamurl.split('/').join('-');
            var obj = $("#"+teamurl);
            obj.addClass("active");
            try{
                obj.parent("ul").show();
                obj.parent("ul").parent("li").addClass("open");
            }catch(e){}
        }catch(e){
        }
    }
};

/**
 * 消息信息
 * @type {{success: success, error: error, confirm: confirm}}
 */
Eymjs.message ={
    /**
     * 提示成功信息，如果url存在，则整个地址跳转
     * @param message 消息
     * @param url
     */
    success: function(message,url){
        layer.msg(message, {icon: 6,time:2000}, function(index){
            layer.close(index);
            if(url){window.location.href=url;}
        })
    },
    /**
     * 错误提示信息
     * @param message 提示信息
     */
    error: function(message) {
        layer.msg(message, {icon: 5,time:2000}, function(index){
            layer.close(index);
        });
    }

};

/**
 * 弹出层
 * @type {{show: show}}
 */
Eymjs.dialog ={
    /**
     * 展示html信息
     * @param title
     * @param content
     * @param width
     * @param height
     */
    show : function (title,content,width,height) {
        if(!width){width=600;}
        if(!height){height=500}
        layer.open({
            type: 1,
            area: [width+'px', height+'px'],
            skin: 'layui-layer-rim', //加上边框
            title: title,
            shadeClose: true,
            content: content
        });
    },

    showid : function (title,objid,width,height) {
        if(!width){width=600;}
        if(!height){height=500}
        layer.open({
            type: 1,
            area: [width+'px', height+'px'],
            skin: 'layui-layer-rim', //加上边框
            title: title,
            shadeClose: true,
            content: $('#'+objid)
        });
    },
    showUrl : function (title,url,params,width,height) {
        Eymjs.data.ajax(url,params,function (data) {
            layer.open({
                type: 1,
                area: [width+'px', height+'px'],
                skin: 'layui-layer-rim', //加上边框
                title: title,
                shadeClose: true,
                content: data
            });
        },true,Eymjs.common.http.POST,'html');
    }
}


Eymjs.gitter ={
    num : 1000,
    closeTime :3000,   // 自动关闭延时时间(毫秒)
    fadeTime : 1000,    // 出入场动画时间(毫秒)

    alert : function(title, msg, level, position) {
        var option = {
            title : title,
            msg : msg,
            position : position || 'right-bottom'
        };
        var $alert = {};
        switch (level) {
            case "success":// 成功
                $alert = this.success(option);
                break;
            case "error":// 错误
                //option['position'] = position || 'center-center';   // 错误提示默认居中
                option['mask'] = true;                              // 错误框默认添加遮罩层
                $alert = this.error(option);
                break;
            case "info":// 普通信息
                $alert = this.info(option);
                break;
            case "warning":// 警告
                option['mask'] = true;      // 警告框框默认添加遮罩层
                $alert = this.warn(option);
                break;
            default:// 默认为普通提示
                $alert = this.info(option);
                break;
        }
        return $alert;

    },
    close : function(obj) {
        if(obj){
            $(obj).fadeOut(this.fadeTime, function() {
                $(obj).remove();
            });
        }
        return this;
    },

    create : function(option) {
        var class_name = '';
        switch (option['level']) {
            case "info" :class_name = "gritter-light gritter-info";break;
            case "confirm":class_name = "gritter-light gritter-warning alert-confirm";break;
            case "warning":class_name = "gritter-light gritter-warning  alert-confirm";break;
            case "error" :class_name = " gritter-light gritter-error  alert-confirm";break;
            case "success":class_name = "gritter-light gritter-success";break;
        }
        $.gritter.add({
            title:option.title,
            text:option.msg,
            class_name:class_name,
            sticky:false        //不自动关闭
        });
        var ok = false;
        $alert = $('.alert-confirm');
        var $closeIcon = $('.gritter-close',$alert).on('click',function(){
        });
        $('.alert-btn-box > .ok').on('click',function(){
            ok = true;
            $closeIcon.trigger('click');
        });
        $('.alert-btn-box > .cancel').on('click',function(){
            ok = false;
            $closeIcon.trigger('click');
        });
        $("#gritter-notice-wrapper").css("z-index",10020);//弹框z-index
        return $alert;
    },
    success : function(option) {		          // 成功提示框
        if (option != undefined) {
            if (typeof option === "string") {
                option = {
                    msg : option
                };
            }
        } else {
            option = {};
        }
        if (!option['title']) {
            option['title'] = Eymjs.common.info.MESSAGE;
        }
        if (!option['msg']) {
            option['msg'] = "操作成功!";
        }
        option['title'] = '<i class="ace-icon fa fa-check"></i>'+ option['title'];
        option['level'] = 'success';
        return this.create(option);
    },
    error : function(option) {// 错误提示框
        if (option != undefined) {
            if (typeof option === "string") {
                option = {
                    msg : option
                };
            }
        } else {
            option = {};
        }
        if (!option['title']) {
            option['title'] = Eymjs.common.info.ERROR_MESSAGE;
        }
        if (!option['msg']) {
            option['msg'] = "操作失败!";
        }
        option['title'] = '<i class="ace-icon fa fa-times"></i>' + option['title'];
        option['level'] = 'error';
        return this.create(option);
    },
    warn : function(option) {           // 警告提示框
        if (option != undefined) {
            if (typeof option === "string") {
                option = {
                    msg : option
                };
            }
        } else {
            option = {};
        }
        if (!option['title']) {
            option['title'] = "警告!";
        }
        if (!option['msg']) {
            option['msg'] = "操作警告！";
        }
        option['level'] = 'warning';
        return this.create(option);
    },
    info : function(option) {// 信息提示框
        if (option != undefined) {
            if (typeof option === "string") {
                option = {
                    msg : option
                };
            }
        } else {
            option = {};
        }
        if (!option['title']) {
            option['title'] = "提示";
        }
        if (!option['msg']) {
            option['msg'] = "操作提示！";
        }
        option['level'] = 'info';
        return this.create(option);
    }
};

Eymjs.form = {
    
    checkInit :function () {
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue'
        });
        $('#contentTable thead tr th input.i-checks').on('ifChecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定
            console.log("0----");
            $('#contentTable tbody tr td input.i-checks').iCheck('check');
        });

        $('#contentTable thead tr th input.i-checks').on('ifUnchecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定
            $('#contentTable tbody tr td input.i-checks').iCheck('uncheck');
        });
    },
    /**
     * 表单保存完成时候
     * @param data
     * @returns {boolean}
     */
    saveComplate :function (data) {
        if (data.success) {
            layer.closeAll();
            Eymjs.gitter.success('恭喜您保存成功');
            if(data.flushType==0){
                try{search();}catch(e){console.log(e)};
            }else if(data.flushType ==1){
                window.location.reload();
            }
        } else {
            Eymjs.message.error(data.errorinfo);
            return false;
        }
    }
}