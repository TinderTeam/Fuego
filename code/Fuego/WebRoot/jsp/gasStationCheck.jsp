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
				<h1><font  face="微软雅黑">下发实物资产盘点</font></h1>

			</div>
			<div id="breadcrumb">
				<a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>主页</a>
				<a href="#" class="current">盘点管理</a>
				<a href="#" class="current">下发实物资产盘点</a>
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-align-justify"></i>									
								</span>
								<h5>盘点情况查询</h5>
							</div>
							<div class="widget-content nopadding">
								<form action="<%=request.getContextPath()%>/GasStationCheck.do" method="post" class="form-horizontal" >
									<div class="control-group">
										<div class="alert alert-info">
											<button class="close" data-dismiss="alert">×</button>
											<strong>说明</strong> 盘点表查询说明
										</div>
									
									</div>
									
									<div class="control-group">
										<label class="control-label">盘点月份</label>					
										<div class="controls">
											<select name="monthList">									
													<c:forEach var="i" items= "${checkInitBo.monthList}"> 																								  
														<option id="${i}" value = "${i}"/>${i}																							
													</c:forEach>
										    </select>
										</div>
									</div>
                                     <div class="control-group">
                                        <label class="control-label">盘点事务ID</label>
                                        <label class="control-label">${checkInitBo.transID}</label> 																								  
										
                                    </div>                          
                                  
									
									<div class="form-actions">
										<button type="submit" class="btn btn-primary" name="submit" value="submit1">查询盘点情况</button>
									</div>
								</form>
							</div>
						</div>
							
					</div>
					
						<div class="widget-box">
						
							<div class="widget-title">
							
								<h5>下发本月实物资产盘点表</h5>
									
							</div>
						
							<div class="widget-content nopadding">
								<table class="table table-bordered data-table">
									<thead>
										<tr>
										<th><input type="checkbox"/></th>
										<th>经营管理部</th>
										<th>加油站</th>									
										</tr>
									</thead>
									<tbody>

										<c:forEach var="i" items= "${checkInitBo.checkList}"> 		
									<tr>										
											
											<td style="text-align:center"><input type="checkbox" name="" value="${i.gasName}"/></td>
											<td style="text-align:center">${i.dept}</td>
											<td style="text-align:center">${i.gasName}</td>
											
									</tr>
									</c:forEach>	
									</tbody>
								
								</table> 
								
							</div>
						
						</div>
						
						
						<form action="<%=request.getContextPath()%>/GasStationCheck.do" method="post" class="form-horizontal" >
						       <button type="submit" class="btn btn-primary" name="submit" value="submit2">向加油站下发本月盘点表</button>
					    </form>
						
					</div>
				</div>
				
				
				<div class="row-fluid">
					<div id="footer" class="span12">
						2013  Copyright Reserved by Tinder.
					</div>
				</div>
			</div>
 
								
		

	</body>
</html>
