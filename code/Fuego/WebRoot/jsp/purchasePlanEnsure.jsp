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

		<form action="<%=request.getContextPath()%>/PurchasePlanEnsure.do"  name="PurchasePlanCreateForm" method="post" >	
	
		<div id="content">
			<div id="content-header">
				<h1>创建资产采购计划（确认）</h1>
			
			</div>
			<div id="breadcrumb">
				<a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>主页</a>
				<a href="#" class="current">实物资产管理</a>
				<a href="#" class="current">创建资产采购计划</a>
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
				
						<div class="widget-box">
						
							<div class="widget-title">
							
								<h5>采购计划</h5>
									
							</div>
							<c:set var="page" value="${purchasePlan.purchasePageBo.page}" scope="request"/>
						 
					        <jsp:include page="/jsp/purchase/purchasePlanData.jsp"/>
						    <button id ="pageChange" type="submit" class="btn btn-success" name="submit" value = "pageChange" style="display:none">pageChange</button>
							
						</div> 
						
						
					</div>
				</div>
				
				<div class="widget-box">
								<div class="widget-title">
									<h5>采购计划确认</h5>
								</div>
							
								<div class="widget-content">
								
								 <c:if test="${'create' == pageDisCtr}"> 
									<jsp:include page="/jsp/cbb/transCreateInfo.jsp"/>								 
									<button class="btn btn-success"  name="submit" value="submit">提交并送审</button>								
									<button class="btn  btn-primary" name="submit" value="cancel">取消</button>	
									<button class="btn  btn-inverse" name="submit" value="download">导出Excel审批会签表</button>
								 </c:if>
								 <c:if test="${'approval' == pageDisCtr}"> 
								 	<jsp:include page="/jsp/cbb/transOperateInfo.jsp"/>
								 
									<button class="btn btn-success"  name="submit" value="agree">同意</button>								
									<button class="btn  btn-primary" name="submit" value="refuse">拒绝</button>
									<button class="btn  btn-primary" name="submit" value="download">导出</button>
										
 								 </c:if>
								 <c:if test="${'confirm' == pageDisCtr}"> 
										<button class="btn btn-success"  name="submit" value="finish">完成</button>
										<button class="btn  btn-primary" name="submit" value="download">导出</button>
																		
								</c:if>
								 <c:if test="${'finish' == pageDisCtr}"> 
							        <button class="btn btn-success"  name="submit" value="finish">完成</button>
							        <button class="btn  btn-primary" name="submit" value="download">导出</button>
 	 					         </c:if>
                                <c:if test="${'view' == pageDisCtr}"> 
										<button class="btn btn-success"  name="submit" value="view">确定</button>
										<button class="btn  btn-primary" name="submit" value="download">导出</button>
										<a class="btn btn-primary"   href="javascript:history.go(-1);">返回</a>								
 	 							</c:if>
								</div>
				</div>	
				
				<div class="row-fluid">
					<div id="footer" class="span12">
						2013  Copyright Reserved by Tinder
					</div>
				</div>
			</div>
		</div>
		</form>					
	</body>
</html>
