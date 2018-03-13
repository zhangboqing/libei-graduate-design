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
<!-- Popup -->
<div id="Popup">

<!-- SubPopup -->
<div id="SubPopup">
<script type="text/javascript">
$(function(){
$(".select").each(function(){
var s=$(this);
var z=parseInt(s.css("z-index"));
var dt=$(this).children("dt");
var dd=$(this).children("dd");
var _show=function(){dd.slideDown(200);dt.addClass("cur");s.css("z-index",z+1);};
var _hide=function(){dd.slideUp(200);dt.removeClass("cur");s.css("z-index",z);};
dt.click(function(){dd.is(":hidden")?_show():_hide();});
dd.find("a").click(function(){dt.html($(this).html());_hide();});
$("body").click(function(i){ !$(i.target).parents(".select").first().is(s) ? _hide():"";});})})
</script>
<div class="form_boxC">
<p>"<span class="f_cB">*</span>"号为必填项目</p>
<table cellpadding="0" cellspacing="0">
<tr>
<th width="100">姓名 <span class="f_cB">*</span></th>
<td><div class="txtbox floatL" style="width:100px;">
    <input hidden="true" name="name" id="memberId" type="text" size="5" value="${member.memberId}">
    <input name="name" id="name" type="text" size="5" value="${member.name}"></div></td>
</tr>
<tr>
<th>手机号 <span class="f_cB">*</span></th>
<td><div class="txtbox floatL" style="width:100px;"><input name="phone" id="phone" type="text" size="5" value="${member.phone}"></div></td>
</tr>
    <tr>
        <th>身份 <span class="f_cB">*</span></th>
        <td><div class="txtbox floatL" style="width:100px;"><input name="identity" id="identity" type="text" size="5" value="${member.identity}"></div></td>
    </tr>
</table>
</div>
</div>

<div id="BtmBtn">
<div class="btn_box floatR"><input onclick="checkAndSubmit()"  name="" type="button" value="提交" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'"></div>
</div>
</div>
<!-- /Popup -->
</body>


<script type="text/javascript">
    function checkAndSubmit(){
        var name = document.getElementById('name').value.trim();
        var phone = document.getElementById('phone').value.trim();
        var identity = document.getElementById('identity').value.trim();
        var memberId = document.getElementById('memberId').value.trim();

        if (name == null || name == '') {
            alert("姓名不能为空！！！")
            return;
        } else if (phone == null || phone == '') {
            alert("手机号不能为空！！！")
            return;
        } else if (identity == null || identity == '') {
            alert("身份不能为空！！！")
            return;
        }

        $.post("update",{"memberId":memberId,"name":name,"phone":phone,"identity":identity},function(result){
            alert("更新成功");
            window.location.href = "list";
        });
    }

</script>
</html>