<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
 	<jsp:include page="/jsp/cbb/includeCSS.jsp"/>
<body>
 
     	<jsp:include page="/jsp/cbb/header.jsp"/>
    
		
		<div id="content">
			
			<div id="content-header">
				<h1><font  face="微软雅黑">创建实产调拨计划</font></h1>
				
			</div>
			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/IndexInit.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i> 主页</a>
				<a href="#" class="tip-bottom">创建实产调拨计划</a>				
			</div>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-th"></i>
								</span>
								<h5>实物资产调拨</h5>							
							</div>
						<div class="widget-content">
							<div class="control-group">
								<div class="alert alert-info alert-block">
									<a class="close" data-dismiss="alert" href="#">×</a>
									<h4 class="alert-heading">说明</h4>
									请点击添加增加需要进行调配的资产，完成后点击确认。
								</div>
									
							</div>
			
						</div>
						
						 <form action="<%=request.getContextPath()%>/AssignCreate.do" method="post"  >
							
								<div class="widget-content">
									<h3>实物资产调拨单</h3>
										
											<jsp:include page="/jsp/assign/assignList.jsp"/>
										
									</div>
									<div class="form-actions">
											<button type="submit" class="btn btn-success" name="submit" value="confirm">确认</button>
											<button type="submit" class="btn btn-primary" name ="submit" value="add">添加</button>
											<button type="submit" class="btn btn-primary" name ="submit" value="cancel" >取消</button>
							
								</div>
				
						</form>
					
					
				</div>
				
				
				<div class="row-fluid">
					<div id="footer" class="span12">
						2013  Copyright Reserved by Tinder
					</div>
				</div>
				
			</div>
 
		</div>
 


	</body>
</html>