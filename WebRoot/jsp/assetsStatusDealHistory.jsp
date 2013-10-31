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
				<h1><font  face="微软雅黑">台账操作记录查询</font></h1>
				
			</div>
			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/IndexInit.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i> 主页</a>
				<a href="#" class="tip-bottom">台账操作记录查询</a>				
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-th"></i>
								</span>
								<h5>台账操作记录查询</h5>							
							</div>
							<div class="widget-content">
								<div class="control-group">
									<div class="alert alert-info alert-block">
										<a class="close" data-dismiss="alert" href="#">×</a>
										<h4 class="alert-heading">说明！</h4>
										台账操作记录包含各账户对实物资产台账进行操作的历史信息。管理员可根据先关筛选条件对历史操作记录进行查询。
									</div>
										
								</div>
				
							</div>
						
							<div class="widget-content" style="overflow-x:auto;">
							<form action="<%=request.getContextPath()%>/AssetsStatusDealHistorySearchResultInit.do"  name="myForm" method="post" >
							
								<div class="control-group">
									<span class="label label-info">查询条件</span>
								</div>	
							
									<table class="table table-bordered " >
									<thead>
										<tr>																	
											<th width=80px>操作人</th>
											<th width=50px>操作类型</th>																					
											<th style="width: 130px">资产编号</th>											
											<th style="width: 130px">资产名称</th>
											<th colspan="2">操作日期</th>
											<th style="width: 80px">资产分类</th>											
											<th width=80px>存放地点</th>
											<th style="width: 50px">技术状态</th>										
										</tr>
									</thead>
									<tbody>
										
										<tr>
										 
											<td style="text-align:center">
												<select style="width: 200px" >		
													<option id="全部" value = "全部"/>	全部											
													<c:forEach var="i" items= "${userNameList}"> 																								  
														<option id="${i}" value = "${i}"/>${i}																							
													</c:forEach>
												</select>
											</td>
											<td style="text-align:center  width:5	0px">
												<select style="width:70px">							
													<option id="全部" value = "全部"/>	全部	
													<c:forEach var="i" items= "${operationTypeList}"> 																								  
														<option id="${i}" value = "${i}"/>${i}																							
													</c:forEach>
												</select>
											</td>
																																			  																						
				
											<td style="text-align:center"><input type="text" name="assetsID"  style="width: 80px"/></td>
											<td style="text-align:center"><input type="text" name="assetsName" style="width: 80px" /></td>
											<td style="text-align:center">
												<input  style="width:70px" type="text"  data-date="" data-date-format="yyyy-mm-dd" value="0000-00-00" class="datepicker" name="opStartDate" />	
											</td>
											<td style="text-align:center">
												<input  style="width:70px" type="text"  data-date="" data-date-format="yyyy-mm-dd" value="0000-00-00" class="datepicker" name="opEndDate" />	
											</td>
								
											<td style="text-align:center">	
												<select style="width:120px">
												<option id="全部" value = "全部"/>		全部
												<c:forEach var="i" items= "${assetsTypeList}"> 													  
													<option id="${i}" value = "${i}"/>${i}																							
													</c:forEach>
												</select>
											</td>
								
											<td style="text-align:center"><input type="text" name="assetsName"  style="width:100px"/></td>
								
											<td style="text-align:center">	
												<select style="width:80px">
												<option id="全部" value = "全部"/>		全部
													<c:forEach var="i" items= "${techStateList}"> 																								  
														<option id="${i}" value = "${i}"/>${i}																							
													</c:forEach>
												</select>
											</td>
											
										</tr>
										
							
									
									</tbody>
								</table>
								
							
									
								
									
						
							
									<div class="form-actions">
												<button type="submit" class="btn btn-success" name="submit" value="submit">查询</button>
									
									</div>	
						
							</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			
				<div class="row-fluid">
					<div id="footer" class="span12">
						2013  Copyright Reserved by Tinder
					</div>
				</div>
		</div>

	</body>
</html>
