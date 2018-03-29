<#assign base=request.contextPath />
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="generator" content="" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
<link href="${base}/css/haiersoft.css" rel="stylesheet" type="text/css" media="screen,print" />
<link href="${base}/css/print.css" rel="stylesheet" type="text/css" media="print" />
<script src="${base}/js/jquery-1.10.1.min.js"></script>
<script src="${base}/js/side.js" type="text/javascript"></script>

<!--[if lt IE 9]>
<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->
</head>

<body>


<!-- MainForm -->
<div id="MainForm">
<div class="form_boxA">
<h2>会议室预定记录列表</h2>
<table cellpadding="0" cellspacing="0">
<tr>
<th>房间号</th>
<th>预定人姓名</th>
<th>预定人部门号</th>
<th>预定人手机号</th>
<th>预定人部门信息</th>
<th>参与人数</th>
<th>占用开始时间</th>
<th>占用结束时间</th>
<th>会议室审核状态</th>
    <th>操作</th>
</tr>

     <#list recordList as p>
     <tr>
     <td>${p.roomNo}</td>
     <td>${p.stuName}</td>
     <td>${p.stuNo}</td>
     <td>${p.phone}</td>
     <td>${p.classInfo}</td>
     <td>${p.inNum}</td>
     <td>${p.reserveStartTimeDesc}</td>
     <td>${p.reserveEndTimeDesc}</td>
     <td>${p.statusDesc}</td>
         <td>
         <#if p.status == 0>
             <a href="checkNotPass?id=${p.id}&roomId=${p.roomId}"> 审核不通过</a>
             <a href="checkPass?id=${p.id}&roomId=${p.roomId}"> 审核通过</a>
         </#if>
         </td>
     </tr>
     </#list>

</table>
</div>
</div>
<!-- /MainForm -->

</body>
</html>