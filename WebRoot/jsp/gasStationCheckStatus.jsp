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
				<h1><font  face="微软雅黑">盘点情况统计</font></h1>

			</div>
			<div id="breadcrumb">
				<a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>主页</a>
				<a href="#" class="current">盘点管理</a>
				<a href="#" class="current">盘点情况统计</a>
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						
					
						<div class="widget-box">
						
							<div class="widget-title">							
								<h5>${initBo.title}</h5>		
							</div>
						
							<div class="widget-content nopadding">
								<table class="table table-bordered data-table">
									<thead>
										<tr>
										<th>加油站</th>
										<th>盘点状态</th>	
										<th>查看</th>	
										</tr>
									</thead>
									<tbody>
									<c:forEach var="i" items= "${checkPlan.transInfo.childTransList}"> 		
										<tr>										
											
 											<td style="text-align:center">${i.transInfo.handleUser}</td>
											<td style="text-align:center">${i.transInfo.state}</td>
											<td style="text-align:center"><a href="<%=request.getContextPath()%>/GasStationCheckStatusEnsureInit.do?transID=${i.transInfo.transID}" title="查看">查看</a></td>
										</tr>
									</c:forEach>		
										
									 
									</tbody>
								
								</table> 
								
							</div>
							
						</div>
						
						<form action="<%=request.getContextPath()%>/GasStationCheck.do" method="post" class="form-horizontal" >
						<button type="submit" class="btn btn-primary" name="submit" value="submit1">返回</button>
						
						<button type="submit" class="btn btn-primary" name="submit" value="submit2">完成盘点（Danger）</button>
						</form>
					</div>
				</div>
							<div class="row-fluid">
					<div id="footer" class="span12">
						2013  Copyright Reserved by Tinder.
					</div>
				</div>	
				
			
			</div>
		</div>
					
 
	</body>
</html>
