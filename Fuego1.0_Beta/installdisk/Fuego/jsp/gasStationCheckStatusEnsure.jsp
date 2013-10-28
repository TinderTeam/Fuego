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
				<h1>Common Form Elements</h1>

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
									<i class="icon-th"></i>
								</span>
								<h5>加油站盘点确认</h5>							
							</div>
							
						
							<div class="widget-content">
								
							
								<div class="control-group">
									<span class="label label-info">${statusEnsureInitBo.title}</span>
								</div>	
								
								<table class="table table-bordered table-striped with-check">
									<thead>
										
									  <tr>							
 
										<th width="15%">资产名称</th>
										<th width="20%">资产型号（生产厂家）</th>
										<th width="7.5%">规格型号</th>
										<th width="7.5%">单位</th>
										<th width="7.5%">数量</th>									
										<th width="10%">实际数量</th>	
										<th width="10%">盘点状态</th>				
							 
											
										</tr>
 
										
									</thead>
									<tbody>
										
										
										<c:forEach var="plan" items= "${checkPlan.planInfo}"> 		
 
											  <tr>
                                                    <td style="text-align:center" >${plan.assets.assetsName}</td>												
													<td style="text-align:center">${plan.assets.manufacture}</td>
													<td style="text-align:center">${plan.assets.spec}</td>
													<td style="text-align:center">${plan.assets.quantity}</td>
													
													<td style="text-align:center" >${plan.assets.unit}</td>
													
													<td style="text-align:center">${plan.checkCnt}</td>
													
													<td style="text-align:center" >${plan.checkState}</td>
											 
					
											 </tr>
 
									</c:forEach>
									</tbody>
								</table>
								<div class="form-actions">
									<form action="<%=request.getContextPath()%>//GasStationCheckStatusEnsure.do" method="post" class="form-horizontal" />
											<button type="submit" class="btn btn-primary" name="submit" value="submit2">盘点确认</button>
											<button type="submit" class="btn btn-primary" name="submit" value="submit1">返回</button>
									</form>
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
