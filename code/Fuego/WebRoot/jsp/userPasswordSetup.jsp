<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
  <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
 	<jsp:include page="/jsp/cbb/includeCSS.jsp"/>
<body>
 
     	<jsp:include page="/jsp/cbb/header.jsp"/>
    
		
            
		
		

		
		<div id="content">
			<div id="content-header">
				<h1><font  face="微软雅黑">修改用户密码</font></h1>
				
			</div>
			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/IndexInit.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i> 主页</a>
				<a href="#" class="tip-bottom">用户账户管理</a>	
				<a href="#" class="tip-bottom">修改用户密码</a>					
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-align-justify"></i>									
								</span>
								<h5>用户密码更改</h5>
							</div>
							<div class="widget-content nopadding">
								<form action="<%=request.getContextPath()%>/UserPasswordSetup.do" method="post" class="form-horizontal" />
									<div class="control-group">
										<div class="alert alert-info">
											<button class="close" data-dismiss="alert">×</button>
											<strong>说明</strong> 密码输入建议不超过26个数字或英文，并请输入正确的旧密码且保持两次新密码输入一致。
										</div>
									
									</div>
									
                                                               
									<div class="control-group">
                                        <label class="control-label">请输入旧密码</label>
                                        <div class="controls">
                                            <input type="password" name="oldpassword"/>
                                        </div>
                                    </div>
									<div class="control-group">
                                        <label class="control-label">请输入新密码</label>
                                        <div class="controls">
                                                <input type="password" name="newpassword1" />
                                        </div>
                                    </div>
										<div class="control-group">
                                        <label class="control-label">请再次输入新密码</label>
                                        <div class="controls">
                                               <input type="password" name="newpassword2" />
                                        </div>
                                    </div>
									
									<div class="form-actions">
										<button type="submit" class="btn btn-primary" name="submit" value="submit">更改密码</button>
										<h5 align="center" ><font color="red">${exception}</font></h5>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div id="footer" class="span12">
						2013  Copyright Reserved by Tinder
					</div>
				</div>
			</div>
		</div>
		
		
            
            
	</body>
</html>
