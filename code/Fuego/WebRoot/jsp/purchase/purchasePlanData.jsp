<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
  <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 
		 
			
 
							
								 
								<div class="widget-content">
								<div class="control-group">
										                第<select name="pageNum" style = "width:70px" onchange="return assetsPageChange('pageChange')"  >
												<option id="${page.currentPage}">${page.currentPage}</option>	
												<c:forEach var="i" items= "${page.pages}"> 																								  
													<option id="${i}"/>${i}																							
												</c:forEach>
									 </select>页，共${page.count}条记录							 
									</div>
							
							    </div>	
								<div class="widget-content nopadding" style="height:500px;overflow-y:auto;" >
									<table class="table table-bordered " >
										<thead>
											<tr>
											<th><input type="checkbox"/>选入</th>
											<th>序号</th>
											<th>资产名称</th>
											<th>品牌</th>
											<th>规格/参数</th>
											<th>部门</th>
											<th>购置数量</th>
											<th>单位</th>											
											<th>已有数量</th>
											<th>不可用数量</th>
											<th>标准配置数量</th>
											<th>效益金额</th>
											<th>预算单价</th>
											<th>金额</th>
								
											<th>说明</th>
											</tr>
										</thead>
										<tbody>
										
											<c:forEach var="item" items= "${page.currentPageData}">  
											<tr>
							
												<td style="text-align:center">
													<input type="checkbox" name="boxes"  value="${item.index}"/>
												</td>
													<td style="text-align:center" >${item.index}</td>												
												
													<td style="text-align:center" >${item.assetsBo.assetsName}</td>												
													<td style="text-align:center">${item.assetsBo.manufacture}</td>
													<td style="text-align:center">${item.assetsBo.spec}</td>
													<td style="text-align:center">${item.assetsBo.duty}</td>
													<td style="text-align:center">${item.assetsBo.quantity}</td>
													
													<td style="text-align:center" >${item.assetsBo.unit}</td>
													<td style="text-align:center">${item.currentQuantity}</td>
													<td style="text-align:center">${item.disableQuantity}</td>
													<td style="text-align:center" >${item.quotaQuantity}</td>
													<td style="text-align:center">${item.price}</td>	
													<td style="text-align:center">${item.price}</td>
													
													<td style="text-align:center" >${item.money}</td>
													<td >${item.assetsBo.note}</td>
					
											</tr>
												</c:forEach >
										</tbody>
									
									</table> 
									
								</div>
							
				 
 				
				
 
