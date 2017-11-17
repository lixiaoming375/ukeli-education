<!-- #section:basics/sidebar -->
<div id="sidebar" class="sidebar responsive ace-save-state">
    <div class="sidebar-shortcuts" id="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
            <button class="btn btn-success">
                <i class="ace-icon fa fa-signal"></i>
            </button>
            <button class="btn btn-info">
                <i class="ace-icon fa fa-pencil"></i>
            </button>
            <!-- #section:basics/sidebar.layout.shortcuts -->
            <button class="btn btn-warning">
                <i class="ace-icon fa fa-users"></i>
            </button>
            <button class="btn btn-danger">
                <i class="ace-icon fa fa-cogs"></i>
            </button>
            <!-- /section:basics/sidebar.layout.shortcuts -->
        </div>

        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span>
            <span class="btn btn-info"></span>
            <span class="btn btn-warning"></span>
            <span class="btn btn-danger"></span>
        </div>
    </div><!-- /.sidebar-shortcuts -->
    <ul class="nav nav-list">
        <li id="index">
            <a href="${adminPath}/index.html">
                <i class="menu-icon fa fa-tachometer"></i>
                <span class="menu-text">首页</span>
            </a>
            <b class="arrow"></b>
        </li>
        <#if treeItemlist??>
            <#list treeItemlist as item>
                <#if item.list??>
                    <li class="" id="${item.permissionDo.url!?replace("/","-")}">
                        <a href="${adminPath}/${item.permissionDo.url!}.html" class="dropdown-toggle">
                            <i class="menu-icon fa ${item.permissionDo.icons!}"></i>
                            <span class="menu-text">${item.permissionDo.name!}</span>
                            <b class="arrow fa fa-angle-down" ></b>
                        </a>
                        <@itemTree list=item.list />
                    </li>
                <#else>
                    <li class="">
                        <a href="${adminPath}/${item.permissionDo.url!}.html" class="dropdown-toggle">
                            <i class="menu-icon fa ${item.permissionDo.icons!}"></i>
                            <span class="menu-text">${item.permissionDo.name!}</span>
                        </a>
                    </li>
                </#if>
            </#list>

        </#if>

    </ul><!-- /.nav-list -->

    <!-- #section:basics/sidebar.layout.minimize -->
    <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
        <i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
    </div>

    <!-- /section:basics/sidebar.layout.minimize -->
</div>

<#macro itemTree list>
    <#if list??>
    <ul class="submenu">
        <#list list as item>
            <#assign isChild = false/>
            <#if (item.list??)  && (item.list?size > 0)>
               <#assign isChild =true/>
            </#if>
            <#if item.permissionDo??>
            <li class="" id="${item.permissionDo.url!?replace("/","-")}">
                <#if isChild>
                    <a href="#"  class="dropdown-toggle"><i class="menu-icon fa fa-caret-right"></i>${item.permissionDo.name!}
                        <b class="arrow fa fa-angle-down" ></b>
                    </a>
                <#else >
                    <a href="${adminPath}/${item.permissionDo.url!}.html"><i class="menu-icon fa fa-caret-right"></i>${item.permissionDo.name!}
                    </a>
                </#if>
                <b class="arrow " ></b>
                <#if (item.list??)  && (item.list?size > 0)>
                    <@itemTree list=item.list />
                </#if>
            </li>
            </#if>
        </#list>
    </ul>
    </#if>
</#macro>