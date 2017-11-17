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
        JSON :'json',
        HTML : 'html'
    },
    info  :{
        MESSAGE : '消息提示',
        ERROR_MESSAGE: '错误提示信息'
    }
}

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
            url:url, data:params, type:type, dataType:dataType,
            beforeSend:function(jqXHR, textStatus){if(isLoading){index =layer.load(1,{shade: [0.7, '#333'],maxWidth :37});}},
            complete:function(jqXHR, textStatus){if(isLoading){layer.close(index);}},
            success:function(data){try{callback && callback.call(this,data);}catch(e){ }},
            error:function(data){console.log("data="+data);}
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
        for(var i=0;i<40;i++){sign = sign + Math.round(Math.random());};
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
    show:function (url,params,tplid,callback) {
        Eymjs.data.ajax(url,params,function (data) {
            var render = template.compile(tplid);
            var html = render(data);
            document.getElementById('ajax-data').innerHTML = html;
            Eymjs.form.checkInit();
            Eymjs.page.page(data.totalRecords,data.currentPage,data.totalPage);
            try{callback && callback.call(this,data);}catch(e){ }
        },true);
    },
    /**
     * 初始化代码
     * @param url
     * @param params
     * @param tplid
     */
    showRight:function (url,params,tplid,callback) {
        Eymjs.data.ajax(url,params,function (data) {
            var render = template.compile(tplid);
            var html = render(data);
            document.getElementById('ajax-data-right').innerHTML = html;
            try{callback && callback.call(this,data);}catch(e){ }
        },true);
    },
    /**
     * 初始化代码 
     * @param url
     * @param params
     * @param tplid
     */
    showForBind :function (url,params,tplid,callback) {
        Eymjs.data.ajax(url,params,function (data) {
            var render = template.compile(tplid);
            var html = render(data);
            document.getElementById('ajax-data-forbind').innerHTML = html;
            Eymjs.form.checkInitForBind();
            Eymjs.page.pageForBind(data.totalRecords,data.currentPage,data.totalPage);
            try{callback && callback.call(this,data);}catch(e){ }
        },true);
    },

    treeTable : function (url,params,tplid,conid,callback) {
        Eymjs.data.ajax(url,params,function (data) {
            var render = template.compile(tplid);
            var html = render(data);
            document.getElementById(conid).innerHTML = html;
            $("#treeTable").treeTable({expandLevel :2 ,column:1}).show();
            try{callback && callback.call(this,data);}catch(e){}
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
    pageForBind:function (totalRecord,page,totalPage) {
        $("#pagerForBind .fl").html("总共 "+totalRecord+" 条数据，当前第 "+page+" 页，总共 "+totalPage+" 页");
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
            pageHtml += '<li><a href="javascript:pageForBind('+(iPage -1)+');">«</a></li>';
        }else{
            pageHtml += '<li class="disabled"><span>«</span></li>';
        }
        for(var i= start;i<=end;i++){
            if(i ==iPage){
                pageHtml +='<li class="active"><span>'+i+'</span></li>';
            }else{
                pageHtml +='<li><a href="javascript:pageForBind('+i+');">'+i+'</a></li>';
            }
        }
        if(iPage == iTotalPage){
            pageHtml += '<li class="disabled"><span>»</span></li>';
        }else{
            var nextPage = iPage+1;
            if(iTotalPage >= nextPage){
                pageHtml += '<li><a href="javascript:pageForBind('+nextPage+');">»</a></li>';
            }else{
                pageHtml += '<li class="disabled"><span>»</span></li>';
            }
        }
        $("#pagerForBind .pagination").html(pageHtml);
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
    },
    /**
     * 获取一个id
     * @param tabid
     * @returns {*|jQuery}
     */
    selectOne :function (tabid) {
        var size = $("#"+tabid+" tbody tr td input.i-checks:checked").size();
        if(size == 0 ){
            layer.alert('请至少选择一条数据!', {icon: 0, title:'警告'});
            return;
        }
        if(size > 1 ){
            layer.alert('只能选择一条数据!', {icon: 0, title:'警告'});
            return;
        }
        return $("#"+tabid+" tbody tr td input.i-checks:checked").attr("id");
    },
    /**
     * 获取所有的列表
     * @param tabid
     * @returns {string}
     */
    selectList:function (tabid) {
        var str="";
        var ids="";
        $("#"+tabid+" tbody tr td input.i-checks").each(function(){
            if(true == $(this).is(':checked')){
                str+=$(this).attr("id")+",";
            }
        });
        if(str.substr(str.length-1)== ','){
            ids = str.substr(0,str.length-1);
        }
        return ids;
    },
    bindFunction :function (idfirst,idsecond,url,message,callback) {
        layer.confirm(message, {
            icon: 3,
            btn: ['确定','取消'] //按钮
        }, function(index){
            Eymjs.data.ajax(url,{idfirst:idfirst,idsecond:idsecond},function (data) {
                if (data.success) {
                	layer.close(index);
                    Eymjs.toastr.success('恭喜您!操作成功');
                    try{
                        search();
                        initRightValue(data.input);
                        if(data.ireturn!=9){
                        	searchbtnForBind();
                        }
                    }catch(e){}
                    try{callback && callback.call(this,data);}catch(e){}
                } else {
                    layer.close(index);
                    Eymjs.toastr.error(data.errorinfo);
                    return false;
                }
            },true);
        }, function(index){
            layer.close(index);
        });
    },
    
    /**
     * 删除信息
     * @param ids
     * @param url
     * @param message
     * @param callback
     */
    delByids :function (ids,url,message,callback) {
        if(!ids){return;}
        layer.confirm(message, {
            icon: 3,
            btn: ['确定','取消'] //按钮
        }, function(){
            Eymjs.data.ajax(url,{ids:ids},function (data) {
                if (data.success) {
                    layer.closeAll();
                    Eymjs.toastr.success('恭喜您!删除成功');
                    try{search();}catch(e){}
                    try{callback && callback.call(this,data);}catch(e){}
                } else {
                    layer.closeAll();
                    Eymjs.toastr.error(data.errorinfo);
                    return false;
                }
            },true);
        }, function(){
            layer.closeAll();
        });
    },
    delByidsCallBack :function (ids,url,message,callback) {
        if(!ids){return;}
        layer.confirm(message, {
            icon: 3,
            btn: ['确定','取消'] //按钮
        }, function(){
            Eymjs.data.ajax(url,{ids:ids},function (data) {
                try{callback && callback.call(this,data);}catch(e){}
            },true);
        }, function(){
            layer.closeAll();
        });
    },
    /**
     * 上线一个记录
     * @param url
     * @param params
     * @param message
     * @param callback
     */
    online:function (url,params,message,callback) {
        layer.confirm(message, {
            icon: 3,
            btn: ['确定','取消'] //按钮
        }, function(){
            Eymjs.data.ajax(url,params,function (data) {
                if (data.success) {
                    layer.closeAll();
                    Eymjs.toastr.success('恭喜您!操作成功');
                    try{
                    if(null!=data.input&&(data.input).trim()!=""){
                    	initRightValue(data.input);
                    }else{
                    	 search();
                    }
                    }catch(e){}
                    try{callback && callback.call(this,data);}catch(e){}
                } else {
                    layer.closeAll();
                    Eymjs.toastr.error(data.errorinfo);
                    return false;
                }
            },true);
        }, function(){
            layer.closeAll();
        });
    },
    onlineCallBack:function (url,params,message,callback) {
        layer.confirm(message, {
            icon: 3,
            btn: ['确定','取消'] //按钮
        }, function(){
            Eymjs.data.ajax(url,params,function (data) {
                try{callback && callback.call(this,data);}catch(e){}
            },true);
        }, function(){
            layer.closeAll();
        });
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

/**
 * 信息提示
 * @type {{info: info, success: success, warning: warning, error: error}}
 */
Eymjs.toastr = {
    /**
     * 提示信息
     * @param contet 内容
     * @param title  标题
     */
    info :function (contet,title) {
        if(!title){title='提示信息';}
        toastr.options = {
            "closeButton": true,
            "debug": false,
            "positionClass": "toast-top-right",
            "onclick": null,
            "showDuration": "300",
            "hideDuration": "1000",
            "timeOut": "5000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        };
        toastr.info(contet,title);
    },

    /**
     * 成功提示信息
     * @param contet 内容
     * @param title  标题
     */
    success:function (contet,title) {
        if(!title){title='成功提示';}
        toastr.options = {
            "closeButton": true,
            "debug": false,
            "positionClass": "toast-top-right",
            "onclick": null,
            "showDuration": "300",
            "hideDuration": "1000",
            "timeOut": "5000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        };
        toastr.success(contet,title);
    },

    /**
     * 警告提示信息
     * @param contet 内容
     * @param title  标题
     */
    warning:function (contet,title) {
        if(!title){title='警告提示';}
        toastr.options = {
            "closeButton": true,
            "debug": false,
            "positionClass": "toast-top-right",
            "onclick": null,
            "showDuration": "300",
            "hideDuration": "1000",
            "timeOut": "5000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        };
        toastr.warning(contet,title);
    },
    /**
     * 错误提示信息
     * @param contet 内容
     * @param title  标题
     */
    error:function (contet,title) {
        if(!title){title='错误提示';}
        toastr.options = {
            "closeButton": true,
            "debug": false,
            "positionClass": "toast-top-right",
            "onclick": null,
            "showDuration": "300",
            "hideDuration": "1000",
            "timeOut": "5000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        };
        toastr.error(contet,title);
    }
};

Eymjs.form = {
    
    checkInit :function () {
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue'
        });
        $('#contentTable thead tr th input.i-checks').on('ifChecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定
            $('#contentTable tbody tr td input.i-checks').iCheck('check');
        });

        $('#contentTable thead tr th input.i-checks').on('ifUnchecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定
            $('#contentTable tbody tr td input.i-checks').iCheck('uncheck');
        });
    },
    checkInitForBind :function () {
    	$('#contentTableForBind thead tr th input.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue'
        });
        $('#contentTableForBind thead tr th input.i-checks').on('ifChecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定
            $('#contentTableForBind tbody tr td input.i-checks').iCheck('check');
        });

        $('#contentTableForBind thead tr th input.i-checks').on('ifUnchecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定
            $('#contentTableForBind tbody tr td input.i-checks').iCheck('uncheck');
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
            Eymjs.toastr.success('恭喜您保存成功');
            if(data.input>0){
            	search()
            	initRightValue(data.input);
            }else if(data.flushType==0){
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