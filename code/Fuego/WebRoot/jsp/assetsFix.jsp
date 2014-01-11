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

		<form action="<%=request.getContextPath()%>/assetsFix.do"  name="assetsFixForm" method="post" >	
	
		<div id="content">
			<div id="content-header"> 
				<h1>资产维修台账</h1>
			
			</div>
			<div id="breadcrumb">
				<a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>主页</a>
				<a href="#" class="current">实物资产管理</a>
				<a href="#" class="current">资产维修台账</a>
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
				
						<div class="widget-box">
						
							<div class="widget-title">
							
								<h5>维修台账</h5>
									
							</div>
						
							<div class="widget-content nopadding">
								<table class="table table-bordered data-table">
									<thead>
										<tr>
										<th>序号</th>
										<th>维修内容</th>
										<th>维修油站名称</th>
										<th>主办部门</th>
										<th>经办人</th>
										<th>预算金额</th>
										<th>送审时间</th>
										<th>开工时间</th>
										<th>竣工时间</th>										
										<th>结算金额</th>
										<th>结算时间</th>
										<th>审结金额差</th>
										<th>已付工程款</th>
										<th>未付工程款</th>
										<th>备注</th>
										
										</tr>
									</thead>
									<tbody>
									<c:forEach var="i" items="${purchasePlan.purchasePageBo.assetsList}"> 
 
											  <tr>
												<td style="text-align:center" >${i.assetsBo.assetsName}</td>
												<td style="text-align:center" >${i.assetsBo.assetsType}</td>
												<td style="text-align:center">${i.assetsBo.manufacture}</td>
												
												<td style="text-align:center">${i.assetsBo.spec}</td>
												<td style="text-align:center">${i.assetsBo.duty}</td>		
												<td style="text-align:center">${i.assetsBo.quantity}</td>
												<td style="text-align:center">${i.assetsBo.unit}</td>
												
												<td style="text-align:center">￥ ${i.price}</td>
												<td style="text-align:center">￥ ${i.money}</td>									
												<td style="text-align:center">${i.assetsBo.note}</td>
												<td style="text-align:center">${i.assetsBo.duty}</td>		
												<td style="text-align:center">${i.assetsBo.quantity}</td>
												<td style="text-align:center">${i.assetsBo.unit}</td>
												
												<td style="text-align:center">￥ ${i.price}</td>
												<td style="text-align:center">￥ ${i.money}</td>									
												<td style="text-align:center">${i.assetsBo.note}</td>												
											 </tr>
								 
									</c:forEach>	
									
									</tbody>
								
								</table> 
								
							</div>
						
						</div>
						
						
					</div>
				</div>
				
				<div class="widget-box">
								
							
								<div class="widget-content">
									<button class="btn  btn-inverse" name="submit" value="download">导出Excel表</button>
									<button class="btn btn-success"  name="submit" value="view">确定</button>								
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
