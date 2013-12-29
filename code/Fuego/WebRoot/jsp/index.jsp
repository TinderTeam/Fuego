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
				<h1><font  face="微软雅黑">待办事项</font></h1>
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
									<i class="icon-th-list"></i>
								</span>
								<h5>重要通知</h5>
							</div>
							<div class="widget-content">
								${basicNotice}
							</div>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-th"></i>
								</span>
								<h5>待办事项及通知</h5>
								
							</div>
							
								
								<div class="widget-content nopadding">
									<table class="table table-bordered data-table">
										<thead>
											<tr>
									
												<th>事务号</th>
												<th>事务名</th>
												<th>发起时间</th>
												<th>发起人</th>
												<th>当前处理人</th>
												<th>停留天数</th>
												<th>当前状态</th>
												<th>操作信息</th>
												<th>删除</th>
											
											</tr>
										</thead>
										<tbody>
											<c:forEach var="i" items= "${transInfoList}">    
												  <tr>
													
													<c:if test="${i.expendTime >10}">
													   <td bgColor="#ff0000">${i.transID}</td>	
													</c:if>			
													<c:if test="${i.expendTime <=10}">
													    <td>${i.transID}</td>	
													</c:if>									
													<td><a href="${i.url}">${i.transName}</a></td>
													<td>${i.createTime}</td>
													<td>${i.createUser}</td>
													<td>${i.handleUser}</td>
													<td>${i.expendTime}</td>
													<td>${i.state}</td>	
													<td><a href="<%=request.getContextPath()%>/QueryTransOperInfo.do?transID=${i.transID}">操作信息</a></td>
													
													<td><a href="<%=request.getContextPath()%>/Index.do?transID=${i.transID}">删除</a></td>
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
