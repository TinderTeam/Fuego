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
				<h1><font  face="微软雅黑">实物资产验收</font></h1>
				
			</div>
			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/IndexInit.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i> 主页</a>
				<a href="#" class="tip-bottom">实物资产验收</a>				
			</div>
			
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-th"></i>
								</span>
								<h5>实物资产验收</h5>							
							</div>
 
						 <form action="<%=request.getContextPath()%>/AssetsReceive.do?transID=${receivePlan.transInfo.transInfo.transID}" method="post" >
						
							<div class="widget-content">
								<h3>实物资产验收</h3>
								
								<c:set var="transInfo" value="${receivePlan.transInfo.transInfo}" scope="request"/>
								<jsp:include page="/jsp/cbb/transaction.jsp"/>
									
								<c:set var="assetsPage" value="${receivePlan.planInfo.assetsPage}" scope="request"/>
								<jsp:include page="/jsp/cbb/assetsList.jsp"/>
								<div class="form-actions">
								
								 	 <c:if test="${'create' == pageDisCtr}"> 
									  <button class="btn btn-success"  name="submit" value="confirm">确定</button>
									  <button class="btn  btn-inverse" name="submit" value="cancel">取消</button>		 	 								 </c:if>
 	 				  	 			 <c:if test="${'confirm' == pageDisCtr}"> 
									  <button class="btn btn-success"  name="submit" value="finish">完成</button>
 	 								 </c:if>
								 	  <c:if test="${'view' == pageDisCtr}"> 
										<button class="btn btn-success"  name="submit" value="view">确定</button>								
 	 								 </c:if>
						
								</div>	
					          </div>
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
