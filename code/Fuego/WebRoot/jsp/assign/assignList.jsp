<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		
		<div class="control-group">
			 <span class="label label-info">调拨单信息</span>
		 </div>							
		 <table class="table table-bordered table-striped with-check">
			<thead>
			  <tr>							
					<th>事物ID</th>
					<th>创建时间</th>
					<th>创建者</th>				
					<th>调出部门</th>
					<th>调入部门</th>							
					
			  </tr>
			</thead>
			<tbody>
			  <tr>	
				  <td style="text-align:center">${assignPlan.transInfo.transInfo.transID}</td>
				  <td style="text-align:center">${assignPlan.transInfo.transInfo.createTime}</td>
				  <td style="text-align:center">${assignPlan.transInfo.transInfo.createUser}</td>
				  <td style="text-align:center">${assignPlan.transInfo.outDept}</td>
				  <td style="text-align:center">${assignPlan.transInfo.inDept}</td>
		
			  </tr>
			 </tbody>
		</table>
	
	 <div class="widget-box" >
		<div class="widget-title">
			<h5>调拨单内容</h5>							
		</div>
	
	<c:set var="assetsPage" value="${assignPlan.assetsPage}" scope="request"/>
	<jsp:include page="/jsp/cbb/assetsList.jsp"/>
	</div>	
 
