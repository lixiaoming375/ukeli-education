    <script src="${resWebUrl!}layui/layui.js?ver=${version}"></script>
    <script src="${resWebUrl!}js/jquery.min.js?ver=${version}"></script>
    <script src="${resWebUrl!}js/swiper.min.js?ver=${version}"></script>
    <script src="${resWebUrl!}js/eymjs.1.0.js?ver=${version}"></script>
    <script>
        var webPath='${webPath!}';
        layui.config({
            base: '${resWebUrl!}js/' //你的模块目录
        }).use('commen'); //加载入口
    </script>