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
<h2>用户列表</h2>
<table cellpadding="0" cellspacing="0">
<tr>
<th>用户ID</th>
<th>姓名</th>
<th>手机号</th>
<th>身份</th>
    <th>操作</th>
</tr>

     <#list memberList as p>
     <tr>
     <td>${p.memberId}</td>
     <td>${p.name}</td>
     <td>${p.phone}</td>
     <td>${p.identity}</td>
         <td>
         <a href="edit?memberId=${p.memberId}">编辑</a>
         <a href="delete?memberId=${p.memberId}"> 删除</a>
         </td>
     </tr>
     </#list>

</table>
</div>
</div>
<!-- /MainForm -->

</body>
</html>