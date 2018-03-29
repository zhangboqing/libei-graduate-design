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
    <script type="text/javascript" src="${base}/My97DatePicker/WdatePicker.js"></script>
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
<p>会议室预定</p>
    <p>"<span class="f_cB">*</span>"号为必填项目</p>
<table cellpadding="0" cellspacing="0">
<tr>
<th width="100">房间号 </th>
<td><div class="txtbox floatL" style="width:100px;">
    <input hidden="true" name="roomId" id="roomId" type="text" size="5" value="${meetingRoom.roomId}">
    ${meetingRoom.roomNo}
</div></td>
</tr>
    <tr>
        <th width="100">选择预定用户 <span class="f_cB">*</span></th>
        <td><div class="txtbox floatL" style="width:100px;">
            <select id="userId"  name="userId">
                <#list memberList as p>
                    <option value="${p.userId}" <#--<#if p.userId =  meetingRoom.userId >selected</#if>-->>${p.userName}-${p.userId}</option>
                </#list>
            </select>
        </div></td>
    </tr>
    <tr>
        <th width="100">参与人数 <span class="f_cB">*</span></th>
        <td><div class="txtbox floatL" style="width:100px;">
                <input  name="num" id="num" type="text" size="5">
        </div></td>
    </tr>
    <tr>
        <th width="100">选择使用时间区间 <span class="f_cB">*</span></th>
        <td><div class="txtbox floatL" style="width:200px;">
           开始时间： <input id="reserveStartTimeDesc"  readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2008-03-08 11:30:00',maxDate:'2100-03-10 20:59:30'})"

        </div>

        <div class="txtbox floatL" style="width:200px;">
            结束时间： <input id="reserveEndTimeDesc"  readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2008-03-08 11:30:00',maxDate:'2100-03-10 20:59:30'})"

        </div>
        </td>

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
        var roomId = document.getElementById('roomId').value.trim();
        var userId = document.getElementById('userId').value.trim();
        var num = document.getElementById('num').value.trim();
        var reserveStartTimeDesc = document.getElementById('reserveStartTimeDesc').value.trim();
        var reserveEndTimeDesc = document.getElementById('reserveEndTimeDesc').value.trim();

        if (userId == null || userId == '') {
            alert("请选择用户！！！")
            return;
        } else  if (num == null || num == '') {
            alert("请输入使用人数！！！")
            return;
        }else  if (reserveStartTimeDesc == null || reserveStartTimeDesc == '') {
            alert("请输入使用开始时间！！！")
            return;
        }else  if (reserveEndTimeDesc == null || reserveEndTimeDesc == '') {
            alert("请输入使用结束时间！！！")
            return;
        }

    //2把字符串格式转换为日期类
        var startTime = new Date(Date.parse(reserveStartTimeDesc));
        var endTime = new Date(Date.parse(reserveEndTimeDesc));
        //3进行比较
        if (startTime >= endTime) {
            alert("开始时间不能大于或等于结束时间！！！")
            return;
        }

        $.post("order",{"roomId":roomId,"userId":userId,"num":num,"reserveStartTimeDesc":reserveStartTimeDesc,"reserveEndTimeDesc":reserveEndTimeDesc},function(result){

            if (result == "0") {
                alert("预约成功");
                window.location.href = "list";
            } else if (result == "1") {
                alert("使用人数太大")
            } else if (result == "2") {
                alert("该会议室在当前时间范围类已经被预定")
            }

        });
    }

</script>
</html>