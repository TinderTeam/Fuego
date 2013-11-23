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
				<h1><font  face="微软雅黑">资产验收状态</font></h1>

			</div>
			<div id="breadcrumb">
				<a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>主页</a>
				<a href="#" class="current">资产验收状态</a>
 
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						
					
						<div class="widget-box">
						
							<div class="widget-title">							
								<h5>${initBo.title}</h5>		
							</div>
						
						     <c:set var="transInfo" value="${receivePlan.transInfo.transInfo}" scope="request"/>
						     <jsp:include page="/jsp/cbb/transaction.jsp"/>
							<div class="widget-content nopadding">
								<table class="table table-bordered data-table">
									<thead>
										<tr>
										<th>责任部门</th>
										<th>验收状态</th>	
										<th>查看</th>	
										</tr>
									</thead>
									<tbody>
									<c:forEach var="i" items= "${receivePlan.transInfo.childTransList}"> 		
										<tr>										
											
 											<td style="text-align:center">${i.transInfo.handleUser}</td>
											<td style="text-align:center">${i.transInfo.state}</td>
											<td style="text-align:center"><a href="<%=request.getContextPath()%>/ImportAssetsSubmitInit.do?transID=${i.transInfo.transID}" title="查看">查看</a></td>
										</tr>
									</c:forEach>		
										
									 
									</tbody>
								
								</table> 
								
							</div>
							
						</div>
						
						<form action="<%=request.getContextPath()%>/AssetsReceive.do?transID=${receivePlan.transInfo.transInfo.transID}" method="post" class="form-horizontal" >
						<c:if test="${'create' == pageDisCtr}"> 
						  <button type="submit" class="btn btn-primary" name="submit" value="confirm">确认</button>
						  <button class="btn  btn-primary" name="submit" value="download">导出</button>
						 </c:if>
						  <c:if test="${'confirm' == pageDisCtr}"> 
							  <button class="btn btn-success"  name="submit" value="finish">完成</button>
 	 					  </c:if>
						  <c:if test="${'view' == pageDisCtr}"> 
						      <button class="btn btn-success"  name="submit" value="view">确定</button>								
 	 					  </c:if>
 						</form>
					</div>
				</div>
							<div class="row-fluid">
					<div id="footer" class="span12">
						2013  Copyright Reserved by Tinder.
					</div>
				</div>	
				
			
			</div>
		</div>
					
 
	</body>
</html>
