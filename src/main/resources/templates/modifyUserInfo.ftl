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
<th width="100">真实姓名 <span class="f_cB">*</span></th>
<td><div class="txtbox floatL" style="width:100px;">
    <input name="realName" id="realName" type="text" size="9" value="${userInfo.stuName}"></div></td>
</tr>
    <tr>
        <th>部门号 <span class="f_cB">*</span></th>
        <td><div class="txtbox floatL" style="width:200px;"><input name="stuNo" id="stuNo" type="text" size="20" value="${userInfo.stuNo}"></div></td>
    </tr>
    <tr>
        <th>手机号码 <span class="f_cB">*</span></th>
        <td><div class="txtbox floatL" style="width:200px;"><input name="phone" id="phone" type="text" size="20" value="${userInfo.phone}"></div></td>
    </tr>
<tr>
<th>年龄 <span class="f_cB">*</span></th>
<td><div class="txtbox floatL" style="width:70px;"><input name="age" id="age" type="text" size="5" value="${userInfo.age}"></div></td>
</tr>
    <tr>
        <th>性别 <span class="f_cB">*</span></th>
        <td><div>
            男：<input type="radio" name="sex" value="0" layout_weight="1" <#if userInfo.sex == 0>
                   checked
            </#if>>
            女：<input type="radio" name="sex" value="1" layout_weight="1" <#if userInfo.sex == 1>
                   checked
            </#if>>
        </div></td>
    </tr>
    <tr>
        <th>部门信息 <span class="f_cB">*</span></th>
        <td><div class="txtbox floatL" style="width:400px;"><input name="classInfo" id="classInfo" type="text" size="50" value="${userInfo.classInfo}"></div></td>
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
        var realName = document.getElementById('realName').value.trim();
        var age = document.getElementById('age').value.trim();
        var classInfo = document.getElementById('classInfo').value.trim();
        var stuNo = document.getElementById('stuNo').value.trim();
        var phone = document.getElementById('phone').value.trim();

        var sex = $('input[name="sex"]:checked ').val();


        if (realName == null || realName == '') {
            alert("真实姓名不能为空！！！")
            return;
        }else if (age == null || age == '') {
            alert("年龄不能为空！！！")
            return;
        } else if (phone == null || phone == '') {
            alert("手机号码不能为空！！！")
            return;
        } else if (sex == null || sex == '') {
            alert("请选择性别！！！")
            return;
        } else if (classInfo == null || classInfo == '') {
            alert("专业及班级信息不能为空！！！")
            return;
        } else if (stuNo == null || stuNo == '') {
            alert("学号不能为空！！！")
            return;
        }

        $.post("${base}/user/info/modify",{"realName":realName,"phone":phone,"age":age,"sex":sex,"classInfo":classInfo,"stuNo":stuNo},function(result){
            alert("更新成功");
            window.location.href = "${base}/user/modify/info/page";
        });
    }

</script>
</html>