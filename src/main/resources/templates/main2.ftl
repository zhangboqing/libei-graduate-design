<#assign base=request.contextPath />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>公告</title>
    <link rel="stylesheet" type="text/css" href="${base}/css/public.css" />
    <script type="text/javascript" src="${base}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/js/public.js"></script>
    <style type="text/css">
        body { width: 700px; margin: 40px auto; border: 1px solid #999; padding: 0 20px; border-radius:10px;}
        li{list-style: none;}
        h2 {text-align: center;}
    </style>
</head>
<body>
<div>公告：</div>
<h2></h2>
<p></p>
<ul>
<#list noticeList as p>
    <li> 公告内容： ${p.content} ，发布时间：  ${p.releaseTimeDesc}</li>
</#list>
</ul>
<p></p>
</body>
</html>