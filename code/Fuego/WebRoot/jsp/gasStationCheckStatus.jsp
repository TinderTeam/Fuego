<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
  <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
 	<jsp:include page="/jsp/cbb/includeCSS.jsp"/>
		<script type="text/javascript">
		function submitFuction()
		{
			document.getElementById("subID").click();
		}	
		</script>
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
						
						<form action="<%=request.getContextPath()%>/GasStationCheckStatus.do" method="post" class="form-horizontal" >
					  <c:if test="${'create' == pageDisCtr}"> 
						
						<button type="submit" class="btn btn-primary" name="submit" value="confirm">确定</button>	
						<button  id = "subID" type="submit" class="btn btn-primary"  name ="submit" value="download" style="display:none">导出</button>	
		
	
						   </c:if>
						   <c:if test="${'confirm' == pageDisCtr}">
						   
							  <button class="btn btn-success"  name="submit" value="confirm">完成</button>
						  	   <button  id = "subID" type="submit" class="btn btn-primary"  name ="submit" value="download" style="display:none">导出</button>	
							  
 	 					  </c:if>
						  <c:if test="${'finish' == pageDisCtr}">
						   
							  <button class="btn btn-success"  name="submit" value="finish">完成</button>
						  	   <button  id = "subID" type="submit" class="btn btn-primary"  name ="submit" value="download" style="display:none">导出</button>	
							  
 	 					  </c:if>
						  <c:if test="${'view' == pageDisCtr}"> 
						      <button class="btn btn-success"  name="submit" value="view">确定</button>								
 	 					  </c:if>	
 	 					  							
											<a href="#myAlert" data-toggle="modal" class="btn btn-primary" style="width:150px" onclick="submitFuction()">导出</a>		
											<div id="myAlert" class="modal hide">
												<div class="modal-header">													
													<h3>正在导出全部盘点信息（请耐心等候）</h3>
												</div>
												<div class="modal-body">
													<div class="span12 center" style="text-align: center;">	
														<img border="0" alt="请等待" src="<%=request.getContextPath()%>/img/loading.gif" style="center"/></a>	
															</div>
												</div>											
											</div>				
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
