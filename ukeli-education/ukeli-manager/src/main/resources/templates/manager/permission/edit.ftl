<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-4"></div>
        <form class="form-horizontal" id="permissionForm" method="post" action="${adminPath}/manager/permission/save.json" >

            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="realname"><span class="red">*</span>隶属父类:</label>
                <div class="col-sm-10">
                    <input type="hidden" name="parentid" id="parentid" required="" value="${permission.parentid!}">
                    <div class="input-group col-sm-11">
                        <input type="text" id="parentName"  class="col-sm-12" placeholder="父类" value="${parentName!}" />
                        <span class="input-group-btn">
                            <button class="btn btn-sm" type="button" onclick="showTree();"><i class="ace-icon fa fa-search"></i></button>
                        </span>
                    </div>
                </div>
            </div>
            <div class="space-4"></div>

            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="telephone"><span class="red">*</span>权限名称:</label>
                <div class="col-sm-9">
                    <input type="text" name="name" id="name" placeholder="权限名称" class="col-sm-12" value="${permission.name!}" required=""/>
                </div>
            </div>
            <div class="space-4"></div>

            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="sex"><span class="red">*</span>唯一标示：</label>
                <div class="col-sm-9">
                    <input type="text" name="ident" id="ident" placeholder="权限唯一标示" class="col-sm-12" value="${permission.ident!}" required="">
                </div>
            </div>
            <div class="space-4"></div>


            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="telephone">类型:</label>
                <div class="col-sm-4">
                    <label style="padding-top:5px">
                        <input name="type" type="radio" id="type_1" class="i-checks"  value="1">
                        <span class="lbl">列表</span>
                    </label>
                    <label style="padding-top:5px">
                        <input name="type" type="radio" id="type_2" class="i-checks" value="2">
                        <span class="lbl">权限</span>
                    </label>
                </div>
            </div>
            <div class="space-4"></div>



            <div class="form-group" id="isshow_formgroup" style="display: none">
                <label class="col-sm-2 control-label no-padding-right" for="telephone">是否展示:</label>
                <div class="col-sm-4">
                    <label style="padding-top: 5px">
                        <input name="isshow" value="1" class="ace ace-switch ace-switch-5" type="checkbox" />
                        <span class="lbl"></span>
                    </label>
                </div>
            </div>
            <div class="space-4" id="isshow_formspace"  style="display: none"></div>



            <div class="form-group" id="url_formgroup" style="display: none">
                <label class="col-sm-2 control-label no-padding-right" for="telephone">链接地址:</label>
                <div class="col-sm-9">
                    <input type="text" name="url" id="url" placeholder="链接地址" class="col-sm-12" value="${permission.url!}" required=""/>
                </div>
            </div>
            <div class="space-4" id="url_formgroup" style="display: none"></div>



            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="sex">权限图标：</label>
                <div class="col-sm-10">
                    <label style="margin-top: 5px">
                        <i id="iconIcon" class="ace-icon fa ${permission.icons!} <#if !(permission.icons?exists)>hidden</#if>"></i>&nbsp;<span id="iconIconLabel"><#if permission.icons??>${permission.icons}<#else >无</#if></span>&nbsp;

                    </label>
                    <input type="hidden" name="icons" id="icons" value="${permission.icons!}" class="col-xs-10 col-sm-2">
                    <button class="btn btn-info btn-xs" id="selecticons" type="button">选择</button>
                    <button class="btn btn-info btn-xs" id="clearicons" type="button">清除</button>
                </div>
            </div>
            <div class="space-4"></div>

            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 备注：  </label>
                <div class="col-sm-9">
                    <input type="text" name="remark1" placeholder="备注信息" class=" col-sm-12" value="${permission.remark1!}">
                </div>
            </div>


            <div class="clearfix form-actions">
                <div class="center">
                    <input name="id" type="hidden" value="${permission.id!}">
                    <button class="btn btn-sm btn-primary"><i class="ace-icon fa fa-check bigger-110"></i>保存</button>
                    &nbsp; &nbsp; &nbsp;
                    <button class="btn btn-sm btn-inverse" type="reset"><i class="ace-icon fa fa-undo bigger-110"></i>重置</button>
                </div>
            </div>

        </form>

    </div>
</div>

<script>
<!--
function showicons() {
    layer.open({
        type: 2,
        area: ["700px","400px"],
        skin: 'layui-layer-rim',
        title: "显示图标",
        shadeClose: true,
        content:adminPath+"/icons.html",
        btn: ['确定', '关闭'],
        yes: function(index, layero){ //或者使用btn1
            var icon = layero.find("iframe")[0].contentWindow.$("#icon").val();
            $("#iconIcon").attr("class", "fa "+icon);
            $("#iconIconLabel").text(icon);
            $("#icons").val(icon);
            top.layer.close(index);
        },
        cancel: function(index){}
    });
}
function clearicons() {
    $("#iconIcon").attr("class", "icon- hide");
    $("#iconIconLabel").text("无");
    $("#icon").val("");
}

function showTree() {
    layer.open({
        type: 2,
        area: ["250px","500px"],
        skin: 'layui-layer-rim',
        title: "选择权限",
        shadeClose: true,
        content:adminPath+"/tree.html?path="+encodeURIComponent("manager/permission")+"&type=0&params="+encodeURIComponent("{time:"+new Date().getTime()+"}"),
        btn: ['确定', '关闭'],
        yes: function(index, layero){ //或者使用btn1
            var tree = layero.find("iframe")[0].contentWindow.tree;//h.find("iframe").contents();
            var ids = [], names = [], nodes = [];
            if ("" == "true"){
                nodes = tree.getCheckedNodes(true);
            }else{
                nodes = tree.getSelectedNodes();
            }
            for(var i=0; i<nodes.length; i++) {//
                ids.push(nodes[i].id);
                names.push(nodes[i].name);//
                break;
            }
            $("#parentid").val(ids.join(",").replace(/u_/ig,""));
            $("#parentName").val(names.join(","));
            $("#parentName").focus();
            top.layer.close(index);
        },
        cancel: function(index){}
    });
}

//-->
$(document).ready(function() {
    $("#selecticons").on('click',showicons);
    $("#clearicons").on('click',clearicons);
    $('#permissionForm').ajaxForm({
        success: Eymjs.form.saveComplate
    });
    $("#type_1").on('click',function () {
        typecheck(1);
    });
    $("#type_2").on('click',function () {
        typecheck(2);
    });
<#if permission??>
    <#if (permission.type??) && (permission.type==1)>
        typecheck(1);
        $("#type_1").attr('checked','checked');
    <#else >
        $("#type_2").attr('checked','checked');
    </#if>
</#if>

});

function typecheck(type) {
    if(type==1){
        $("#isshow_formgroup").show();
        $("#isshow_formspace").show();
        $("#url_formgroup").show();
        $("#url_formspace").show();
    }else{
        $("#isshow_formgroup").hide();
        $("#isshow_formspace").hide();
        $("#url_formgroup").hide();
        $("#url_formspace").hide();
    }
}

</script>