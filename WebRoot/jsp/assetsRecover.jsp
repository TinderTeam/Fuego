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
				<h1><font  face="微软雅黑">实物资产回收</font></h1>
				
			</div>
			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/IndexInit.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i>主页</a>
				<a href="#" class="tip-bottom">实物资产回收筛选</a>				
			</div>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-align-justify"></i>									
								</span>
								<h5>实物资产回收</h5>
							</div>
							<div class="widget-content">
								<div class="control-group">
									<div class="alert alert-info alert-block">
										<a class="close" data-dismiss="alert" href="#">×</a>
										<h4 class="alert-heading">Info!</h4>
										Best check yo self, you're not looking too good. Nulla vitae elit libero, a pharetra augue. Praesent commodo cursus magna, vel scelerisque nisl consectetur et.
									</div>
										
								</div>			
							</div>
							<div class="widget-content nopadding">
								<form action="<%=request.getContextPath()%>/AssetsRecover.do" method="post" class="form-horizontal" />
								
									<div class="control-group">
                                        <div class="control-group">
											<label class="control-label">选择需要进行回收的加油站</label>
										
											<div class="controls">
											<select name="gasName">									
													<c:forEach var="i" items= "${gasNameList}"> 																								  
														<option id="${i}" value = "${i}"/>${i}																							
													</c:forEach>
										    </select>
											</div>
										</div>
									</div>	
								<div class="control-group">
                                      
                                                               
                                    <div class="control-group">
										<label class="control-label">选择实物资产类型</label>
										<div class="controls">
											<select name="assetsType">									
													<c:forEach var="i" items= "${assetsTypeList}"> 																								  
														<option id="${i}" value = "${i}"/>${i}																							
													</c:forEach>
										    </select>
										</div>
									</div>
									
									<div class="form-actions">
									<form action="<%=request.getContextPath()%>/AssetsRecover.do" method="post" class="form-horizontal" />
										<button type="submit" class="btn btn-primary">下一步</button>
									</form>	
									</div>
								</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="container-fluid">
				
					
			<div class="row-fluid">
					<div id="footer" class="span12">
						2013  Copyright Reserved by Tinder
					</div>
				</div>
			</div>
			</div>
		</div>
		
		
            
            
	</body>
</html>
