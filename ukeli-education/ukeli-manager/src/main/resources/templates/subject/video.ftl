<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>视频浏览</title>
    <script src="${resWebUrl}plugin/html5media/html5media.min.js?ver=${version}"></script>
</head>
<body>
<div class="row margin0">
    <div class="col-sm-12">
        <video class="video" poster="http://media.html5media.info/poster.jpg" width="640" height="320" autoplay controls>
            <source src="${videopath!}"/>
        </video>
    </div>
</div>
