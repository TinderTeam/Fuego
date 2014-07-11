<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
  <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


    <head>
        <title>惠州石油资产管理系统</title>
		<meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/unicorn.login.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/html5.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/fullcalendar.css" />	
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/unicorn.main.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/unicorn.grey.css" class="skin-color" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    　　
        <script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>  
        <script src="<%=request.getContextPath()%>/js/unicorn.login.js"></script> 
  <script src="<%=request.getContextPath()%>//js/html5.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>

<script type="text/javascript">
//判断浏览器是否支持 placeholder属性
function isPlaceholder(){
	var input = document.createElement('input');
	return 'placeholder' in input;
}

if (!isPlaceholder()) {//不支持placeholder 用jquery来完成
	$(document).ready(function() {
	    if(!isPlaceholder()){
	        $("input").not("input[type='password']").each(//把input绑定事件 排除password框
	            function(){
	                if($(this).val()=="" && $(this).attr("placeholder")!=""){
	                    $(this).val($(this).attr("placeholder"));
	                    $(this).focus(function(){
	                        if($(this).val()==$(this).attr("placeholder")) $(this).val("");
	                    });
	                    $(this).blur(function(){
	                        if($(this).val()=="") $(this).val($(this).attr("placeholder"));
	                    });
	                }
	        });
	        //对password框的特殊处理1.创建一个text框 2获取焦点和失去焦点的时候切换
	        var pwdField	= $("input[type=password]");
	        var pwdVal		= pwdField.attr('placeholder');
	        pwdField.after('<input id="pwdPlaceholder" type="text" value='+pwdVal+' autocomplete="off" />');
	        var pwdPlaceholder = $('#pwdPlaceholder');
	        pwdPlaceholder.show();
	        pwdField.hide();
	        
	        pwdPlaceholder.focus(function(){
	        	pwdPlaceholder.hide();
	        	pwdField.show();
	        	pwdField.focus();
	        });
	        
	        pwdField.blur(function(){
	        	if(pwdField.val() == '') {
	        		pwdPlaceholder.show();
	        		pwdField.hide();
	        	}
	        });
	        
	    }
	});
	
}
</script>
    </head>

    <body>
        <div id="logo">
            <img src="<%=request.getContextPath()%>/img/logo.png" alt="" />
        </div>
        <div id="loginbox">            
            <form id="loginform" class="form-vertical" action="<%=request.getContextPath()%>/Login.do"  name="myForm" method="post">
		
				<p>${loginException}  请输入您的用户名和密码。</p>
                <div class="control-group">
                    <div class="controls">
                        <div class="input-prepend">
                            <span class="add-on"><i class="icon-user"></i></span><input type="text" name="username" placeholder="请输入用户名" />
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="input-prepend">
                            <span class="add-on"><i class="icon-lock"></i></span>
                          <input type="password" name="password" placeholder="请输入密码" />
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                   
                          <span class="pull-center"><a href="<%=request.getContextPath()%>/DownloadTool.do" class="flip-link" >建议使用谷歌浏览器，点击下载。</a></span>
                  
                      </div>
                 </div>  
                <div class="form-actions">
                    <span class="pull-left"><a href="#" class="flip-link"  onClick="javascript:alert('请联系财务资产部系统管理员重置你的密码');">忘记密码？</a></span>
                    
                    <span class="pull-right"><input type="submit" name="submit" class="btn btn-success" value="登录" /></span>
                </div>

            
            </form>
        </div>
        

    </body>
</html>
