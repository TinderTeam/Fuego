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
				<a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>主页</a>
				<a href="#" class="current">资产信息查询</a>
				
			</div>
			<div class="container-fluid">
			<form id="searchForm" action="<%=request.getContextPath()%>/AssetsOperateLog.do"   method="post" >	
				<div class="row-fluid">
					<div class="span12">
						 
						
						<div class="widget-box"  style="overflow-x:auto;">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-th"></i>
								</span>
								<h5>查询条件</h5>							
							</div>
							
						
							<div class="widget-content"  >
								 
								<table class="table table-bordered ">
									<thead>
										<tr>
											<th>操作人</th>
											<th>操作类型</th>
							                <th>事务编号</th>
							                <th>资产编号</th>
											<th>资产名称</th>
 										
										</tr>
									</thead>
									<tbody>
										<tr>
										
											<td style="text-align:center"><input type="text" name="assetsID" value="${searchForm.assetsID}" /></td>
											
											<td style="text-align:center">	
												<select name="operName" style="width: 200px"  >
													<c:forEach var="i" items= "${operateNameList}"> 																								  
												      <c:choose>
														  <c:when test="${searchForm.operName == i}">
													           <option selected=""  >${searchForm.operName}</option>	
														  </c:when>
														  <c:otherwise>  
														       <option id="${i}"/>${i}		
														  </c:otherwise>
													  </c:choose>																		
													</c:forEach>
												</select>
											</td>
											<td style="text-align:center"><input type="text" name="transID" value="${searchForm.transID}" /></td>
											<td style="text-align:center"><input type="text" name="assetsID" value="${searchForm.assetsID}" /></td>
											<td style="text-align:center"><input type="text" name="assetsName" value="${searchForm.assetsName}" /></td>
											
										</tr>
										
							
									
									</tbody>
								</table> 
 
							
							</div>
						</div>	
						</div>	
							<div class="widget-content">
								<div class="control-group">
									
									<button  type="submit" class="btn btn-success" name="submit" value = "submit">查询</button>
 									    第<select name="pageNum" style = "width:70px" onchange="return assetsPageChange('pageChange')" >
											<option id="${operatPageData.currentPage}">${operatPageData.currentPage}</option>	
											<c:forEach var="i" items= "${operatPageData.pages}"> 																								  
												<option id="${i}"/>${i}																							
											</c:forEach>
								      </select>页，共${operatPageData.count}条记录
								    <button id ="pageChange" type="submit" class="btn btn-success" name="submit" value = "pageChange" style="display:none">pageChange</button>
								     									
								</div>
						 
							</div>						    
					 
						</div>
			   
			    </form>			
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
						                <th>事务编号</th>
										
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
										<th>责任人</th>
										
										<th width="100px">责任部门</th>
										<th width="80px">资产分类</th>
										<th width="100px">存放地点</th>
										<th width="80px">技术状态</th>

										</tr>
									</thead>
									<tbody>
									
									<c:forEach var="i" items= "${operatPageData.dataList}">
									<tr>
											<td style="text-align:center"><label style="width:90px">${i.userName}</label></td>
											<td style="text-align:center"><label style="width:150px">${i.operTime}</label></td>
											<td style="text-align:center"><label style="width:80px">${i.operName}</label></td>
											<td style="text-align:center"><label style="width:150px">${i.transID}</label></td>
											
											<td style="text-align:center"><label style="width:150px">${i.assets.assetsID}</label></td>
											<td style="text-align:center"><label style="width:150px">${i.assets.assetsName}</label></td>
											<td style="text-align:center"><label style="width:80px">${i.assets.assetsSRC}</label></td>
											<td style="text-align:center"><label style="width:80px">${i.assets.manufacture}</label></td>
											<td style="text-align:center"><label style="width:80px">${i.assets.spec}</label></td>
											<td style="text-align:center"><label style="width:30px">${i.assets.unit}</label></td>
											
											<td style="text-align:center"><label style="width:80px">${i.assets.quantity}</label></td>
											<td style="text-align:center"><label style="width:100px">${i.assets.purchaseDate}</label></td>
											<td style="text-align:center"><label style="width:80px">${i.assets.originalValue}</label></td>
											<td style="text-align:center"><label style="width:80px">${i.assets.expectYear}</label></td>
											<td style="text-align:center"><label style="width:130px">${i.assets.dueDate}</label></td>
											<td style="text-align:center"><label style="width:100px">${i.assets.dept}</label></td>
											
											<td style="text-align:center"><label style="width:80px">${i.assets.duty}</label></td>
											<td style="text-align:center"><label style="width:80px">${i.assets.assetsType}</label></td>
											<td style="text-align:center"><label style="width:120px">${i.assets.location}</label></td>
											<td style="text-align:center"><label style="width:80px">${i.assets.techState}</label></td>
											
										</tr>	
									</c:forEach>
									
									
									</tbody>
								
								</table> 
								
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
		</div>
       
	</body>
</html>
