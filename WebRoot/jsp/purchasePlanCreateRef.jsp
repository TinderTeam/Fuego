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
						<form action="<%=request.getContextPath()%>/RefPlanCreate.do"  name="RefPlanActionForm" method="get" >
							<div class="widget-box">
							
								<div class="widget-title">
								
									<h5>采购计划参考表</h5>
										
								</div>
							
								<div class="widget-content nopadding" style="height:500px;overflow-y:auto;" >
									<table class="table table-bordered " >
										<thead>
											<tr>
											<th><input type="checkbox"/>选入</th>
											<th>资产名称</th>
											<th>品牌</th>
											<th>规格/参数</th>
											<th>购置数量</th>
											<th>单位</th>
											<th>预算单价</th>
											<th>金额</th>
											<th>说明</th>
											</tr>
										</thead>
										<tbody>
										
											<c:forEach var="item" items= "${refList}">  
											<tr>
							
												<td style="text-align:center">
													<input type="checkbox" name="boxes"  value="${item.index}"/>
												</td>
													<td style="text-align:center" >${item.assetsBo.assetsName}</td>												
													<td style="text-align:center">${item.assetsBo.manufacture}</td>
													<td style="text-align:center">${item.assetsBo.spec}</td>
													<td style="text-align:center">${item.assetsBo.quantity}</td>
													
													<td style="text-align:center" >${item.assetsBo.unit}</td>
													
													<td style="text-align:center">${item.price}</td>
													
													<td style="text-align:center" >${item.money}</td>
													<td >${item.assetsBo.note}</td>
					
											</tr>
												</c:forEach >
										</tbody>
									
									</table> 
									
								</div>
							
							</div>
							  
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
