<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>树管理</title>
    <link rel="stylesheet" href="${resWebUrl}plugin/ztree/metroStyle/metroStyle.css?ver=${version}" />
</head>
<body>
<div id="permissionTree" style="padding:10px;">
    <ul id="roleTree" class="ztree" ></ul>
</div>
</body>
<!--[if !IE]> -->
<script src="${resWebUrl}plugin/jquery/jquery.min.js?ver=${version}"></script>
<!-- <![endif]-->
<!--[if IE]>
<script src="${resWebUrl}plugin/jquery.1x/jquery.js?ver=${version}"></script>
<![endif]-->
<script src="${resWebUrl}plugin/ztree/jquery.ztree.all.min.js?ver=${version}"></script>
<script src="${resWebUrl}eymjs/eymjs.1.0.js?ver=${version}"></script>
<script type="text/javascript">
<!--
var tree;
var setting = {
    view:{
        selectedMulti:false,
        <#if type==1>
            showIcon: false,
        </#if>
        dblClickExpand:false
    },
    check:{
        <#if type==1>
            enable:"true",
        <#else>
            enable:"",
        </#if>
        nocheckInherit:true
    },
    data:{
        simpleData: {
            enable: true
        },
        callback:{
            onClick:function(event, treeId, treeNode){
                tree.expandNode(treeNode);
            },onCheck: function(e, treeId, treeNode){
                var nodes = tree.getCheckedNodes(true);
                for (var i=0, l=nodes.length; i<l; i++) {
                    tree.expandNode(nodes[i], true, false, false);
                }
                return false;
            },onAsyncSuccess: function(event, treeId, treeNode, msg){
                var nodes = tree.getNodesByParam("pId", treeNode.id, null);
                for (var i=0, l=nodes.length; i<l; i++) {
                    try{tree.checkNode(nodes[i], treeNode.checked, true);}catch(e){}
                    //tree.selectNode(nodes[i], false);
                }
                //selectCheckNode();
            },onDblClick: function(){//
                top.$.jBox.getBox().find("button[value='ok']").trigger("click");
                //$("input[type='text']", top.mainFrame.document).focus();//
            }
        }
    }
};

$(document).ready(function () {
    Eymjs.data.ajax('${adminPath!}/${path!}/tree.json',${params},function (data) {
        tree = $.fn.zTree.init($("#roleTree"), setting,data);
    });
});
//-->
</script>
</html>