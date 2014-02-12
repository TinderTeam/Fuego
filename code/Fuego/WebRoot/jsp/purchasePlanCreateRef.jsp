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
				<h1><font  face="微软雅黑">资产采购参考计划</font></h1>
				
			</div>
			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/IndexInit.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i>主页</a>
				<a href="#" class="tip-bottom">资产采购参考计划</a>				
			</div>
			
			
			<div class="container-fluid">
				
				<div class="row-fluid">
					<div class="span12">
					<div class="widget-box">
						
							<div class="widget-title">
							
								<h5>参考计划</h5>
									
							</div>
						<form action="<%=request.getContextPath()%>/PurchaseSelect.do"  name="RefPlanActionForm" method="post" >
							
							
							<c:set var="page" value="${purchasePlan.selectPageBo.page}" scope="request"/>
						 
					        <jsp:include page="/jsp/purchase/purchasePlanData.jsp"/>
						    <button id ="pageChange" type="submit" class="btn btn-success" name="submit" value = "pageChange" style="display:none">pageChange</button>
							
							<button type="submit" class="btn btn-primary" name="submit" value="select_mark">所选项</button>				
							<button type="submit" class="btn btn-success" name="submit" value="select_page">当前页</button>
							<button type="submit" class="btn btn-success" name="submit" value="select_all">所有项</button>
						</form>
					</div>
				</div>
				
				<div class="row-fluid">			    
					<div class="span12">
						<form action="<%=request.getContextPath()%>/RefPlanCreate.do"  name="RefPlanActionForm" method="get" >
							<c:set var="page" value="${purchasePlan.purchasePageBo.page}" scope="request"/>
						 
					        <jsp:include page="/jsp/purchase/purchasePlanData.jsp"/>
						    
	                        <button id ="pageChange" type="submit" class="btn btn-success" name="submit" value = "pageChange" style="display:none">pageChange</button>
							
							<button type="submit" class="btn btn-success" name="submit" value="submit">选定资产</button>
							<button type="submit" class="btn btn-primary" name="submit" value="back">返回</button>			
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
