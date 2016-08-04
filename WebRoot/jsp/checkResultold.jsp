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
				<h1>加油站实物资产盘点结果</h1>

			</div>
			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/IndexInit.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i> Home</a>
				<a href="#" class="tip-bottom">Form elements</a>
				<a href="#" class="current">Common elements</a>
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
							<div class="widget-box">
											<div class="widget-title">
													<span class="icon">
														<i class="icon-align-justify"></i>									
													</span>
													<h5>加油站实物资产申请</h5>
											</div>
							
											<div class="widget-content">
															<h3>待处置资产查询</h3>
															<div class="control-group">
																		<div class="alert alert-info alert-block">
																			<a class="close" data-dismiss="alert" href="#">×</a>
																			<h4 class="alert-heading">Info!</h4>
																			Best check yo self, you're not looking too good. Nulla vitae elit libero, a pharetra augue. Praesent commodo cursus magna, vel scelerisque nisl consectetur et.
																		</div>
																	
															</div>			
											</div>
							<div class="widget-content nopadding">
								<form action="<%=request.getContextPath()%>/CheckResult.do" method="post" class="form-horizontal" >
									
											<div class="control-group">
																<div class="control-group">
																	<label class="control-label">加油站</label>
																
																				<div class="controls">

																					<select multiple="" name="gasStation">	
																						<option selected="" />全部
																							<c:forEach var="i" items= "${checkResultBo.checkBo.gasStation}"> 																								  
																								<option id="${i}" value = "${i}"/>${i}																							
																							</c:forEach>
																					</select>

																				</div>
																</div>
											</div>	
										<div class="control-group">
											<div class="control-group">
												<label class="control-label">经管部</label>
											
												<div class="controls">
													<select multiple="" name="deptName">
												
														<option selected="" />全部
															<c:forEach var="i" items= "${checkResultBo.checkBo.deptName}"> 																								  
																<option id="${i}" value = "${i}"/>${i}																							
															</c:forEach>
													</select>
												</div>
											</div>
										</div>	
										<div class="control-group">
											<div class="control-group">
												<label class="control-label">资产类型</label>
											
												<div class="controls">
													<select multiple="" name="assetsType">
												
														<option selected="" />全部
															<c:forEach var="i" items= "${checkResultBo.checkBo.assetsType}">
																<option id="${i}" value = "${i}"/>${i}																							
															</c:forEach>
													</select>
												</div>
											</div>
										</div>	
										
										<div class="form-actions">
											<button type="submit" class="btn btn-primary" name="submit" value="submit1">查询待处置资产</button>
										</div>
								</form>
							</div>
							
						</div>					
						<div class="widget-content">
								<h3>盘点结果查询</h3>
								<table class="table table-bordered data-table">
									<thead>
								
										<tr>							
											<th>序号</th>
											<th>加油站</th>											
											<th>经管部</th>
											<th>资产</th>
											<th>资产分类</th>
											
											<th>管理部门</th>								
											<th>账面数量</th>											
											<th>实际数量</th>
											
											<th>差异</th>
											<th>差异说明</th>								
								
										</tr>
									</thead>
									<tbody>
											
     										<c:forEach var="i" items= "${checkResultBo.resultBo}"> 		
												<tr>										
														
														<td style="text-align:center">${i.number}</td>
														<td style="text-align:center">${i.gasName}</td>
														<td style="text-align:center">${i.deptName}</td>
														<td style="text-align:center">${i.assetsName}</td>
												</tr>
											</c:forEach>
									</tbody>
								</table>
								<div class="form-actions">
									<form action="<%=request.getContextPath()%>/CheckResult.do" method="post" class="form-horizontal">
											<button type="submit" class="btn btn-success" name="submit" value="submit2">提交</button>
											<button type="submit" class="btn btn-primary" name="submit" value="submit3">导出盘点结果</button>
											<button type="submit" class="btn btn-primary" name="submit" value="submit4">返回</button>
									</form>
								</div>	
							</div>
					</div>
				</div>
				
			</div>
						<div class="container-fluid">
						
							<div class="row-fluid">
								<div id="footer" class="span12">
									2012 &copy; Unicorn Admin. Brought to you by <a href="https://wrapbootstrap.com/user/diablo9983">diablo9983</a>
								</div>
							</div>
						</div>
		</div>
		
		
            
          
	</body>
</html>
