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
				<h1><font  face="微软雅黑">密码重置</font></h1>
				
			</div>
			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/IndexInit.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i> 主页</a>
				<a href="#" class="tip-bottom">用户账户管理</a>
				
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-align-justify"></i>									
								</span>
								<h5>用户密码重置</h5>
							</div>
							<div class="widget-content nopadding">
								<form action="<%=request.getContextPath()%>/PasswordResetup.do" method="post" class="form-horizontal" />
									<div class="control-group">
										<div class="alert alert-info">
											<button class="close" data-dismiss="alert">×</button>
											<strong>说明</strong> 选择需要密码重置的用户进行重置操作！
										</div>
									
									</div>
									
                                                               
								<div class="control-group">
										<label class="control-label">选择用户账户</label>
										<div class="controls">
											<select name="userName" style="width:200px">									
													<c:forEach var="i" items= "${userList}"> 																								  
														<option id="${i}" value = "${i}"/>${i}																							
													</c:forEach>
											</select>											
										</div>
									</div>
									
									
									
									<div class="form-actions">
										<button type="submit" class="btn btn-primary" name="submit" value="submit1">重置密码</button>
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
