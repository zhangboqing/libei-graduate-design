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
<#--<tr>-->

<#--<td><div class="txtbox floatL" style="width:100px;">-->
    <input hidden="true" name="roomId" id="roomId" type="text" size="5" value="${meetingRoom.roomId}">
    <tr>
    <th width="100">房间号 <span class="f_cB">*</span></th>
        <td><div class="txtbox floatL" style="width:80px;">
            <!--<input hidden="true" name="id" id="id" type="text" size="20" >-->
        ${meetingRoom.roomNo}
    </tr>


    <tr>
        <th width="100">房间所能容纳的人数 <span class="f_cB">*</span></th>
        <td><div class="txtbox floatL" style="width:80px;">
            <!--<input hidden="true" name="id" id="id" type="text" size="20" >-->
            <input name="roomCanInNumber" id="roomCanInNumber" type="text" size="5"  value="${meetingRoom.roomCanInNumber}" ></div></td>
    </tr>

    <tr>
        <th width="100">所具有的设备 <span class="f_cB">*</span></th>
        <td><div class="txtbox floatL" style="width:500px;">
            <!--<input hidden="true" name="id" id="id" type="text" size="20" >-->
            <input name="roomFacility" id="roomFacility" type="text" size="60" value="${meetingRoom.roomFacility}" ></div></td>
    </tr>
</tr>

</table>
</div>
</div>

<div id="BtmBtn">
<div class="btn_box floatR"><input onclick="checkAndSubmit()"  name="" type="button" value="提交" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'"></div>
</div>
<!-- /Popup -->
</body>


<script type="text/javascript">
    function checkAndSubmit(){
        var roomId = document.getElementById('roomId').value.trim();
        var roomCanInNumber = document.getElementById('roomCanInNumber').value.trim();
        var roomFacility = document.getElementById('roomFacility').value.trim();

        if (roomCanInNumber == null || roomCanInNumber == '') {
            alert("填写房间可容纳的人数！！！")
            return;
        } else if (roomFacility == null || roomFacility == '') {
            alert("请填写房间所具备的设备！！！")
            return;
        }

        $.post("update",{"roomId":roomId,"roomCanInNumber":roomCanInNumber,"roomFacility":roomFacility},function(result){
            alert("更新成功");
            window.location.href = "list";
        });
    }

</script>
</html>