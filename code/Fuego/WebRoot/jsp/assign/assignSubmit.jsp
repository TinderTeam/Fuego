<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
 	<jsp:include page="/jsp/cbb/includeCSS.jsp"/>
<body>
 
     	<jsp:include page="/jsp/cbb/header.jsp"/>
    
	<div id="content">
			
			<div id="content-header">
				<h1><font  face="微软雅黑">资产调拨计划确认</font></h1>
				
			</div>
			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/IndexInit.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i> 主页</a>
				<a href="#" class="tip-bottom">资产调拨计划确认</a>				
			</div>
			
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-th"></i>
								</span>
								<h5>实物资产调拨</h5>							
							</div>
						<!----
						<div class="widget-content">
				
							<div class="control-group">
								<div class="alert alert-info alert-block">
									<a class="close" data-dismiss="alert" href="#">×</a>
									<h4 class="alert-heading">Info!</h4>
									Best check yo self, you're not looking too good. Nulla vitae elit libero, a pharetra augue. Praesent commodo cursus magna, vel scelerisque nisl consectetur et.
								</div>
									
							</div>
	
						</div>
							---->
						 <form action="<%=request.getContextPath()%>/AssignSubmit.do" method="post" >
						
							<div class="widget-content">
								<h3>实物资产调拨单</h3>
								<jsp:include page="/jsp/assign/assignList.jsp"/>
								<div class="form-actions">
	 			                           <div class="control-group">
													    <label class="control-label">执行人</label >                                   
															 <div class="controls">
																   <c:choose>	
																	 <c:when test="${'create' == pageDisCtr}">
																	 	  <input id="executeName" type="text" name="executeName" value="${assignPlan.transInfo.transInfo.executeName}"/>
																	 	 											           
																	  </c:when>
																	  <c:otherwise>  
																		  <input id="executeName"  type="text" name="executeName" value="${assignPlan.transInfo.transInfo.executeName}" disabled=true/>
															
																
																	  </c:otherwise>	
													                 </c:choose>
																  </div>                          
														 </div>
									<c:if test="${'create' == pageDisCtr}">
						
										<jsp:include page="/jsp/cbb/transCreateInfo.jsp"/> 
										<button class="btn btn-success"  onclick="return validateEmpty('executeName','执行人不能为空');" name="submit" value="submit">提交</button>								
										<button class="btn  btn-primary" name="submit" value="download">导出</button>	
										<button class="btn  btn-inverse" name="submit" value="cancel">取消</button>
									 </c:if>
									 <c:if test="${'approval' == pageDisCtr}">
									    <jsp:include page="/jsp/cbb/transOperateInfo.jsp"/>
									  
										<button class="btn btn-success"  name="submit" value="agree">同意</button>								
										<button class="btn  btn-primary" name="submit" value="refuse">拒绝</button>	
										<button class="btn  btn-primary" name="submit" value="download">导出</button>	
	 								 </c:if>
	 								  <c:if test="${'confirm' == pageDisCtr}"> 
										<button class="btn btn-success"  name="submit" value="confirm">确定</button>		
										<button class="btn  btn-primary" name="submit" value="download">导出</button>							
 	 								 </c:if>
 	 								 <c:if test="${'finish' == pageDisCtr}"> 
							            <button class="btn btn-success"  name="submit" value="finish">完成</button>
							            <button class="btn  btn-primary" name="submit" value="download">导出</button>
 	 					             </c:if>
 	 								 <c:if test="${'view' == pageDisCtr}"> 
										<button class="btn btn-success"  name="submit" value="view">确定</button>
										<button class="btn  btn-primary" name="submit" value="download">导出</button>									
 	 								 </c:if>
								</div>	
					        </div>
					     </form>
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
