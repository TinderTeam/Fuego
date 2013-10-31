<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
			<form action="<%=request.getContextPath()%>/AssetsStatusSearch.do"  name="myForm" method="post" class="form-horizontal">	
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">						
							<div class="widget-content">
 								<div class="control-group">
									<span class="label label-info">查询条件</span>
								</div>	
								
								<table class="table table-bordered table-striped with-check">
									<thead>
										<tr>																	
											<th>资产编号</th>
											<th>资产名称关键字</th>										
											
											<th>责任部门</th>											
											<th>资产分类</th>
											<th>存放地点</th>
											<th>技术状态</th>										
										</tr>
									</thead>
									<tbody>
										<tr>
											<td style="text-align:center"><input type="text"/></td>
											<td style="text-align:center"><input type="text"/></td>
											
											<td style="text-align:center">	
												<select name="deptList">
													<option selected="" >全部</option>	
													<c:forEach var="i" items= "${deptInfoList}"> 																								  
														<option id="${i}"/>${i}																							
													</c:forEach>
												</select>
											</td>
											<td style="text-align:center">	
												<select name="typeList">
												<option selected=""  />全部	
													<c:forEach var="i" items= "${typeList}"> 																								  
														<option id="${i}"/>${i}																							
													</c:forEach>
												</select>
											</td>
											<td style="text-align:center"><input type="text"/></td>
											<td style="text-align:center">	
												<select name="techList">
 												<option selected=""  />全部	
													<c:forEach var="i" items= "${techList}"> 																								  
														<option id="${i}"/>${i}																							
													</c:forEach>
												</select>
											</td>
 										</tr>
   									</tbody>
								</table>
								
								<div class="control-group">
									<span class="label label-info">创建/到期查询条件</span>
								</div>	
								
								<table class="table table-bordered table-striped with-check">
									<thead>
										<tr>																	
											<th  width="50%">资产创建时间条件</th>
											<th width="50%">资产到期时间条件</th>										
 										</tr>
									</thead>
									<tbody>
										<tr>
 											<td style="text-align:center">
												<div class="control-group">
													<label class="control-label">创建时间条件</label>
													<div class="controls">
															<input type="text" data-date="2013-03-04" data-date-format="yyyy-mm-dd" value="2013-04-09" class="datepicker" />
													</div>
													 <div class="controls">
														<input type="text" data-date="2013-03-04" data-date-format="yyyy-mm-dd" value="2013-04-09" class="datepicker" />
													</div>
												</div>
											</td>
											<td style="text-align:center">
											
											<div class="control-group">
													<label class="control-label">到期时间条件</label>
													<div class="controls">
															<input type="text" data-date="2013-03-04" data-date-format="yyyy-mm-dd" value="2013-04-09" class="datepicker" />
													</div>
													 <div class="controls">
														<input type="text" data-date="2013-03-04" data-date-format="yyyy-mm-dd" value="2013-04-09" class="datepicker" />
													</div>
												</div>
											</td>
										</tr>
 
									</tbody>
								</table>
 
									<div class="form-actions">
												<button type="submit" class="btn btn-success" name="submit" value = "query">查询</button>
									</div>	
 
							</div>
						</div>
					</div>
				</div>
			</form>


 
