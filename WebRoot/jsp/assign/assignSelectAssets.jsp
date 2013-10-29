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
				<h1><font  face="微软雅黑">实物资产选择</font></h1>
				
			</div>
			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/IndexInit.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i> 主页</a>
				<a href="#" class="tip-bottom">实物资产选择</a>				
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						 
						<form action="<%=request.getContextPath()%>/AssignSelectAssets.do" method="post"  >
						
						  <div class="widget-box">
							<div class="widget-title">
							
								<h5>实物资产选择</h5>
							</div>
							 
							<jsp:include page="/jsp/cbb/assetsList.jsp"/>

							  
							 
						   </div>
						   <button type="submit" class="btn btn-success" name="submit" value="confirm">确认</button>
						   <button type="submit" class="btn btn-primary" name ="submit" value="cancel" >取消</button>
						</form>
					</div>
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
