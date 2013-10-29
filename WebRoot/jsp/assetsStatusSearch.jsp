<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
  <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>



	<script type="text/javascript" src="jsp/My97DatePicker/WdatePicker.js"></script>
	
 	<jsp:include page="/jsp/cbb/includeCSS.jsp"/>
 
	<script type="text/javascript">
		function pageChange(page)
		{
			document.getElementById("subID").click();
			
		  
		}
	</script>
<body>
 
     	<jsp:include page="/jsp/cbb/header.jsp"/>
	
		
	
	<div id="content">
			<div id="content-header">
				<h1><font  face="微软雅黑">资产台账查询</font></h1>
			
			</div>
                
			<div id="breadcrumb">
				<a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>主页</a>
				<a href="#" class="current">资产台账查询</a>
			
			</div>
			<div class="container-fluid">
			<form id="searchForm" action="<%=request.getContextPath()%>/AssetsStatusSearch.do?showModifyBtn=${showModifyBtn}"   method="post" >	
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-th"></i>
								</span>
								<h5>实物资产台账查询</h5>							
							</div>
							
							
							<div class="widget-content">
								<div class="control-group">
									<div class="alert alert-info alert-block">
										<a class="close" data-dismiss="alert" href="#">×</a>
										<h4 class="alert-heading">说明！</h4>
										在此处选择资产查询方式，点击查询。
									</div>										
								</div>
				
							</div>
						
							<div class="widget-content">
						
							
								<div class="control-group">
									<span class="label label-info">查询条件</span>
								</div>	
							<div class="widget-content nopadding"  style="overflow-x:auto;" >
								<table class="table table-bordered ">
									<thead>
										<tr>																	
											<th style="width: 130px">资产编号</th>
											<th style="width: 130px">资产名称关键字</th>																					
											<th style="width: 160px">责任部门</th>											
											<th style="width: 160px">资产分类</th>
											<th style="width: 150px">存放地点</th>
											<th style="width: 70px">技术状态</th>										
										</tr>
									</thead>
									<tbody>
										<tr>
										
											<td style="text-align:center"><input type="text" name="assetsID" value="${searchForm.assetsID}" /></td>
											<td style="text-align:center"><input type="text" name="assetsName" value="${searchForm.assetsName}" /></td>
											
											<td style="text-align:center">	
												<select name="duty" style="width: 200px"  >
													<option selected=""  >${searchForm.assetsName}</option>	
													<option selected=""  >全部</option>	
													<c:forEach var="i" items= "${deptList}"> 																								  
														<option id="${i.name}"/>${i.name}																							
													</c:forEach>
												</select>
											</td>
											<td style="text-align:center">	
												<select name="assetsType"  style="width:200px">
												
												    <option selected=""  >全部</option>	
													<c:forEach var="i" items= "${typeList}"> 																								  
														<option id="${i}"/>${i}																							
													</c:forEach>
												</select>
											</td>
											<td style="text-align:center"><input type="text" name="location"/></td>
											<td style="text-align:center">	
												<select name="techState">
												
												<option selected=""  >全部</option>	
													<c:forEach var="i" items= "${techList}"> 																								  
														<option id="${i}"/>${i}																							
													</c:forEach>
												</select>
											</td>
											
										</tr>
										
							
									
									</tbody>
								</table>
															
								<table class="table table-bordered ">
									<thead>
										<tr>																	
											<th  width="50%"  colspan="3">资产购置日期</th>
											<th width="50%" colspan="3">资产到期日期</th>										
											
																		
										</tr>
									</thead>
									<tbody>
									
									
									
								
										<tr>
											
											<td     style="text-align:center" >
											
												<input  type="text"  data-date="" data-date-format="yyyy-mm-dd" value="2010-01-01" onfocus="WdatePicker()" name="startPurchaseDate" />					
											</td>
											
											<td>
												~					
											</td>
									
											<td style="text-align:center">	
												<input  type="text"  data-date="" data-date-format="yyyy-mm-dd" value="2020-01-01" onfocus="WdatePicker()" name="endPurchaseDate" />
											</td>
											
										
											<td style="text-align:center">
												<input type="text" data-date="" data-date-format="yyyy-mm-dd" value="2010-01-01" onfocus="WdatePicker()" name="startDueDate" />													
											</td>
											
											<td>
												~					
											</td>
											
									
											<td style="text-align:center">
												<input type="text" data-date="" data-date-format="yyyy-mm-dd" value="2020-01-01" onfocus="WdatePicker()" name="endDueDate" />
											
											</td>
										</tr>
										
							
									
									</tbody>
								</table>
 
							
							</div>
							
							
							<div class="widget-content">
								<div class="control-group">
									 <button id ="subID" type="submit" class="btn btn-success" name="submit" value = "submit">查询</button>
									<button type="submit" class="btn btn-success" name="submit" value = "download">导出</button>
											第<select name="pageNum" style = "width:70px" onchange="return pageChange(this.value)" >
											<option id="${searchForm.pageNum}">${searchForm.pageNum}</option>	
											<c:forEach var="i" items= "${assetsPage.page.pages}"> 																								  
												<option id="${i}"/>${i}																							
											</c:forEach>
								 </select>页，共${assetsPage.page.count}条记录							 
								</div>
				
							</div>						    


							
					 
						</div>
						
						
						
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-th"></i>
								</span>
								<h5>实物资产台账</h5>							
									</div>

									<jsp:include page="/jsp/cbb/assetsList.jsp"/>

								 </div>

							
						    	</div>
						</div>
				</div>
			</form>
			</div>
			
			<div class="row-fluid">
					<div id="footer" class="span12">
						2013  Copyright Reserved by Tinder
					</div>
				</div>
		</div>
	
	</body>
</html>
