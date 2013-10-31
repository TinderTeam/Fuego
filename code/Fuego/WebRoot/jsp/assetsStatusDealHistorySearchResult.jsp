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
				<h1>Tables</h1>

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
							
								<h5>实物资产台账操作历史记录</h5>
									
							</div>
						
							<div class="widget-content nopadding"  style="overflow-x:auto;">
								<table class="table table-bordered data-table">
									<thead>
										<tr>

										<th>操作人</th>
										<th>操作时间</th>
										<th>操作类型</th>
										<th>资产编号</th>
										<th>资产名称</th>
										<th>资产来源</th>
										<th>生产厂家</th>
										<th>型号</th>
										<th>单位</th>										
										
										<th width="30px">数量</th>
										<th width="100px">购建日期</th>
										<th width="80px">原值</th>
										<th>使用年限</th>
										<th>到期日</th>
										<th>责任部门</th>
										
										<th width="100px">责任人</th>
										<th width="80px">资产分类</th>
										<th width="100px">存放地点</th>
										<th width="80px">技术状态</th>

										</tr>
									</thead>
									<tbody>
									<tr>
									<c:forEach var="i" items= "${adHistoryResult}">
											<td style="text-align:center"><label style="width:50px">${i.userName}</label></td>
											<td style="text-align:center"><label style="width:150px">${i.operationDate}</td>
											<td style="text-align:center"><label style="width:80px">${i.operationType}</td>
											<td style="text-align:center"><label style="width:80px">${i.bo.assetsID}</td>
											<td style="text-align:center"><label style="width:80px">${i.bo.assetsName}</td>
											<td style="text-align:center"><label style="width:80px">${i.bo.assetsSRC}</td>
											<td style="text-align:center"><label style="width:80px">${i.bo.manufacture}</td>
											<td style="text-align:center"><label style="width:80px">${i.bo.spec}</td>
											<td style="text-align:center"><label style="width:30px">${i.bo.unit}</td>
											<td style="text-align:center"><label style="width:80px">${i.bo.quantity}</td>
											<td style="text-align:center"><label style="width:100px">${i.bo.purchaseDate}</td>
											<td style="text-align:center"><label style="width:80px">${i.bo.originalValue}</td>
											<td style="text-align:center"><label style="width:80px">${i.bo.expectYear}</td>
											<td style="text-align:center"><label style="width:130px">${i.bo.dueDate}</td>
											<td style="text-align:center"><label style="width:100px">${i.bo.dept}</td>
											<td style="text-align:center"><label style="width:80px">${i.bo.duty}</td>
											<td style="text-align:center"><label style="width:80px">${i.bo.assetsType}</td>
											<td style="text-align:center"><label style="width:120px">${i.bo.location}</td>
											<td style="text-align:center"><label style="width:80px">${i.bo.techState}</td>
											
											
									</c:forEach>
									</tr>
									
									</tbody>
								
								</table> 
								
							</div>
						
						</div>
						
						
					</div>
				</div>
				
				<div class="widget-box">
								<div class="widget-title">
									<h5>采购计划确认</h5>
								</div>
							
								<div class="widget-content">
								
													
									<button class="btn  btn-primary">返回</button>	
									<button class="btn  btn-inverse">导出Excel</button>
								
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
