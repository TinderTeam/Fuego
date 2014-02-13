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
				<h1><font  face="微软雅黑">事务操作信息</font></h1>
			</div>
			<div id="breadcrumb">
				<a title="返回主页" class="tip-bottom"><i class="icon-home"></i>主页</a>
				<a class="current">公告栏</a>
			</div>
			<div class="container-fluid">
		 
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-th"></i>
								</span>
								<h5>事务操作信息</h5>
								
							</div>
							
								
								<div class="widget-content nopadding">
									<table class="table table-bordered data-table">
										<thead>
											<tr>
									
												<th>事务号</th>
												<th>事务名称</th>
												<th>操作人</th>
												<th>签名</th>
												<th>操作时间</th>
												<th>操作步骤</th>
												<th>操作结果</th>
												<th>处理意见</th>
											
											</tr>
										</thead>
										<tbody>
											<c:forEach var="i" items= "${transOperInfoList}">    
												  <tr>
													<td>${i.transID}</td>												
													<td>${i.transName}</td>
													<td>${i.userName}</td>
													<td>${i.nickName}</td>
													<td>${i.operTime}</td>
													<td>${i.stepName}</td>
													<td>${i.result}</td>
													<td>${i.transInfo}</td>	
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
